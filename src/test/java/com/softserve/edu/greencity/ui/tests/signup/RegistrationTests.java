package com.softserve.edu.greencity.ui.tests.signup;

import com.softserve.edu.greencity.ui.api.mail.GoogleMailAPI;
import com.softserve.edu.greencity.data.users.User;
import com.softserve.edu.greencity.data.users.UserRepository;
import com.softserve.edu.greencity.ui.pages.cabinet.ManualLoginComponent;
import com.softserve.edu.greencity.ui.pages.cabinet.ManualRegisterComponent;
import com.softserve.edu.greencity.ui.pages.cabinet.RegisterComponent;
import com.softserve.edu.greencity.ui.pages.cabinet.*;
import com.softserve.edu.greencity.ui.pages.common.TopGuestComponent;
import com.softserve.edu.greencity.ui.tests.runner.GreenCityTestRunner;
import com.softserve.edu.greencity.ui.tools.engine.WaitsSwitcher;
import io.qameta.allure.Description;
import lombok.SneakyThrows;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.softserve.edu.greencity.ui.tests.signin.SignInTexts.ADD_NEW_HABIT_BUTTON_TEXT;

//TODO add DB check
public class RegistrationTests extends GreenCityTestRunner {
    private final String SIGN_IN_TITLE = "Welcome back!";

    @DataProvider
    public Object[][] successRegistrationUserCreds() {
        return new Object[][]{{UserRepository.get()
                .userCredentialsForRegistration()},};
    }


    @Test(dataProvider = "successRegistrationUserCreds", testName = "GC-199, GC-206", description = "GC-199, GC-206")
    @Description("GC-199 - Verify that unregistered user can register after entering valid values in registration form" +
            "GC-206 - Verify that Password must contain at least one digit, special character, upper and lowercase letter when user registered")
    @SneakyThrows
    public void registrationAndLogin(User userLoginCredentials) {
        logger.info("Start test registration and login");
        new GoogleMailAPI().clearMail(userLoginCredentials.getEmail(), userLoginCredentials.getPassword());
        loadApplication();
        RegisterComponent registerComponent = new TopGuestComponent(driver).clickSignUpLink();
        ManualRegisterComponent manualRegisterComponent = registerComponent.getManualRegisterComponent();
        manualRegisterComponent.registrationNewUserVerified(userLoginCredentials);
        //There is no redirect to GreenCity after email verification. Clarify requirements!

        loadApplication()
                .signIn()
                .getManualLoginComponent()
                .successfullyLogin(userLoginCredentials);
        Assert.assertTrue(isLogInNow());
    }

    @Test(testName = "GC-200", description = "GC-200")
    @Description("Verify that unregistered user sees popup window 'Sign in' after clicking on the “My habits” button")
    public void signInAfterMyhabits() {
        logger.info("Starting signInAfterMyhabits");
        String titleString = loadApplication()
                .clickMyHabitsUnsignedLink()
                .getTitleText();

        Assert.assertEquals(titleString, SIGN_IN_TITLE);
    }

    @Test(dataProvider = "successRegistrationUserCreds", testName = "GC-512", description = "GC-512")
    @Description("Verify that user is not registered if he didn’t confirm email address in the mailbox.")
    public void registrationWithoutMailVerif(User userLoginCredentials) {
        logger.info("Start test registration without mail verifying");
        loadApplication();
        logger.info("Starting registrationWithoutMailVerif. Input values = "
                + userLoginCredentials.toString());

        RegisterComponent registerComponent = new TopGuestComponent(driver).clickSignUpLink();

        logger.info("Get a title text of the modal window: "
                + registerComponent.getTitleString());

        softAssert.assertEquals("Hello!", registerComponent.getTitleString(),
                "This is not a register modal:(");


        ManualRegisterComponent manualRegisterComponent = registerComponent.getManualRegisterComponent();

        logger.info("Enter credentials into the form");
        manualRegisterComponent.registrationUser(userLoginCredentials);
        WaitsSwitcher waitsSwitcher = new WaitsSwitcher(driver);
        waitsSwitcher.setExplicitWait(7, ExpectedConditions.visibilityOf(registerComponent.getCongratsModal()));

        softAssert.assertTrue(registerComponent.getCongratsModal().isDisplayed());

        //TMind that now the login window doesn't appear automatically
        waitsSwitcher.setExplicitWait(10, ExpectedConditions.invisibilityOf(registerComponent.getCongratsModal()));
        ManualLoginComponent manualLoginComponent = (new TopGuestComponent(driver)).clickSignInLink().getManualLoginComponent();

        manualLoginComponent.unsuccessfullyLogin(userLoginCredentials);

        softAssert.assertEquals(manualLoginComponent.getWrongEmailOrPassErrorText(),
                "Bad email or password",
                "The validation message is not equal to the expected one");
        softAssert.assertAll();
    }

    @Test(dataProvider = "successRegistrationUserCreds", testName = "GC-513", description = "GC-513")
    @Description("Verify that user receive a verification email about registration in the application to email address after successfully registration.")
    public void registrationCheckIfMailReceived(User userLoginCredentials) {
        logger.info("Start test registration check if mail received");
        loadApplication();
        logger.info("Starting registrationCheckIfMailReceived. Input values = "
                + userLoginCredentials.toString());

        RegisterComponent registerComponent = new TopGuestComponent(driver).clickSignUpLink();

        logger.info("Get a title text of the modal window: "
                + registerComponent.getTitleString());

        Assert.assertEquals("Hello!", registerComponent.getTitleString(),
                "This is not a register modal:(");

        ManualRegisterComponent manualRegisterComponent = registerComponent.getManualRegisterComponent();

        logger.info("Enter random credentials and temporary email into the form ");
        manualRegisterComponent.registerUserCheckIfMailReceived(userLoginCredentials);
    }

    @Test(dataProvider = "successRegistrationUserCreds", testName = "GC-204", description = "GC-204")
    @Description("Verify that Email must be existence and unique while new user registration")
    public void existingUserRegistration(User userLoginCredentials) {
        new GoogleMailAPI().clearMail(userLoginCredentials.getEmail(), userLoginCredentials.getPassword());
        logger.info("Start test existing user registration" + userLoginCredentials.toString());
        loadApplication();
        RegisterComponent registerComponent = new TopGuestComponent(driver).clickSignUpLink();
        ManualRegisterComponent manualRegisterComponent = registerComponent.getManualRegisterComponent();
        manualRegisterComponent.registerUserCheckIfMailReceived(userLoginCredentials);
        softAssert.assertNotNull(new GoogleMailAPI().getconfirmURL(userLoginCredentials.getEmail(), userLoginCredentials.getPassword(), 20));
        signOutByStorage();
        driver.navigate().refresh();
        new TopGuestComponent(driver).clickSignUpLink();
        manualRegisterComponent.enterDataToSingUpFields(userLoginCredentials);
        manualRegisterComponent.clickSignUpButton();
        softAssert.assertEquals(
                manualRegisterComponent
                        .getSignUpErrorsMsg(1),
                "The user already exists by this email",
                "error msg mismatch"
        );
        softAssert.assertAll();
    }

    //TODO modify after bug fixed/requirements changed
    @Test(dataProvider = "successRegistrationUserCreds", testName = "GC-203", description = "GC-203")  // bug, there is no redirection now
    @Description("Verify that User is redirected to My habits as a Registered User after he has entered valid credentials")
    public void registrationCheckIsUserRedirected(User userLoginCredentials) {
        logger.info("Starting registrationCheckIsUserRedirected");
        String newHabitButtonText = loadApplication()
                .signUp()
                .getManualRegisterComponent()
                .registerUser(userLoginCredentials)
                .getMyCabinetPage()
                .getAddNewHabitButton()
                .getText();

        Assert.assertEquals(newHabitButtonText, ADD_NEW_HABIT_BUTTON_TEXT.getText());
        new MyCabinetPage(driver).signOut();


    }
}

