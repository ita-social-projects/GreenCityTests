package com.softserve.edu.greencity.ui.tests.signUp;

import com.google.api.services.gmail.Gmail;
import com.softserve.edu.greencity.ui.data.User;
import com.softserve.edu.greencity.ui.data.UserRepository;
import com.softserve.edu.greencity.ui.pages.cabinet.LoginComponent;
import com.softserve.edu.greencity.ui.pages.cabinet.ManualLoginComponent;
import com.softserve.edu.greencity.ui.pages.cabinet.ManualRegisterComponent;
import com.softserve.edu.greencity.ui.pages.cabinet.RegisterComponent;
import com.softserve.edu.greencity.ui.pages.common.TopGuestComponent;
import com.softserve.edu.greencity.ui.pages.common.TopUserComponent;
import com.softserve.edu.greencity.ui.tests.GreenCityTestRunner;
import com.softserve.edu.greencity.ui.tools.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.security.GeneralSecurityException;

public class RegistrationTests extends GreenCityTestRunner {

    @DataProvider
    public Object[][] successRegistrationUserCreds() {
        return new Object[][]{{UserRepository.get()
                .userCredentialsForRegistration()},};
    }


    @AfterMethod(alwaysRun = true)
    public void registerUserCleanUp() {
        CookiesAndStorageHelper help = new CookiesAndStorageHelper(driver);
        help.cleanGreenCityCookiesAndStorages();
        help.cleanGMailCookiesAndStorages();

        DBQueries queryObj = new DBQueries();
        User user = UserRepository.get().userCredentialsForRegistration();
        String email = user.getEmail();
        queryObj.deleteUserByEmail(email);

    }

    @AfterClass(alwaysRun = true)
    public void mailBoxCleanUp() {

        try {
            Gmail service = GMailAPILogin.getService();
            GMailMethods gmailDo = new GMailMethods();
            gmailDo.batchDelete(service);
        } catch (GeneralSecurityException | IOException e) {
            e.printStackTrace();
        }
        CookiesAndStorageHelper help = new CookiesAndStorageHelper(driver);
        help.cleanCookiesAndStorages();
    }

    @Test(dataProvider = "successRegistrationUserCreds",retryAnalyzer = RetryAnalyzer.class,
            description = "GC-199, GC-206")
    public void registrationAndLogin(User userLoginCredentials) {
        loadApplication();
        logger.info("Starting registrationAndLogin. Input values = "
                + userLoginCredentials.toString());

        logger.info("Click on Sign up button");
        RegisterComponent registerComponent = new TopGuestComponent(driver).clickSignUpLink();

        logger.info("Get a title text of the modal window: "
                + registerComponent.getTitleString());
        Assert.assertEquals("Hello!", registerComponent.getTitleString(),
                "This is not a register modal:(");

        logger.info("Enter credentials into the form");
        ManualRegisterComponent manualRegisterComponent = registerComponent.getManualRegisterComponent();
        manualRegisterComponent.registrationUser(userLoginCredentials);

        logger.info("Wait till success message is displayed");
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(registerComponent.getCongratsModal()));
        Assert.assertTrue(registerComponent.getCongratsModal().isDisplayed());

        logger.info("Wait till login window is displayed");
        LoginComponent loginComp = new LoginComponent(driver);
        wait.until(ExpectedConditions.visibilityOf(loginComp.getLoginModalWindow()));
        Assert.assertTrue(loginComp.getLoginModalWindow().isDisplayed());
        Assert.assertEquals("Welcome back!", loginComp.getTitleString(),
                "This is not a login modal:(");

        logger.info("Verify registration");
        manualRegisterComponent.verifyRegistration();

        logger.info("Enter credentials in login window and click Sign Up");
        ManualLoginComponent manualLoginComponent = new ManualLoginComponent(driver);
        manualLoginComponent.successfullyLogin(userLoginCredentials);

        logger.info("Get Title curent page: " + driver.getTitle());
        Assert.assertEquals(driver.getTitle(), "Home",
                "you didn't log in successfully");

        TopUserComponent userComponent = new TopUserComponent(driver);
        logger.info("Check TopUserName: " + userComponent.getUserNameButtonText());
        Assert.assertEquals(userComponent.getUserNameButtonText(), userLoginCredentials.getUserName());

    }

    @Test(dataProvider = "successRegistrationUserCreds", description = "GC-512")
    public void registrationWithoutMailVerif(User userLoginCredentials) {
        loadApplication();
        logger.info("Starting registrationWithoutMailVerif. Input values = "
                + userLoginCredentials.toString());

        logger.info("Click on Sign up button");
        RegisterComponent registerComponent = new TopGuestComponent(driver).clickSignUpLink();

        logger.info("Get a title text of the modal window: "
                + registerComponent.getTitleString());
        Assert.assertEquals("Hello!", registerComponent.getTitleString(),
                "This is not a register modal:(");

        logger.info("Enter credentials into the form");
        ManualRegisterComponent manualRegisterComponent = registerComponent.getManualRegisterComponent();
        manualRegisterComponent.registrationUser(userLoginCredentials);

        logger.info("Wait till the success message is shown");
        WebDriverWait wait = new WebDriverWait(driver, 6);
        wait.until(ExpectedConditions.visibilityOf(registerComponent.getCongratsModal()));
        Assert.assertTrue(registerComponent.getCongratsModal().isDisplayed());

        logger.info("Wait till the login window is shown");
        LoginComponent loginComp = new LoginComponent(driver);
        wait.until(ExpectedConditions.visibilityOf(loginComp.getLoginModalWindow()));
        Assert.assertTrue(loginComp.getLoginModalWindow().isDisplayed());

        logger.info("Enter credentials into login form");
        ManualLoginComponent manualLoginComponent = new ManualLoginComponent(driver);
        manualLoginComponent.unsuccessfullyLogin(userLoginCredentials);
        Assert.assertEquals(manualLoginComponent.getWrongEmailOrPassErrorText(),
                "Bad email or password",
                "The validation message is not equal to the expected one");
    }

    @Test(dataProvider = "successRegistrationUserCreds", description = "GC-513")
    public void registrationCheckIfMailReceived(User userLoginCredentials) {
        loadApplication();
        logger.info("Starting registrationCheckIfMailReceived. Input values = "
                + userLoginCredentials.toString());

        logger.info("Click on Sign up button");
        RegisterComponent registerComponent = new TopGuestComponent(driver).clickSignUpLink();

        logger.info("Get a title text of the modal window: "
                + registerComponent.getTitleString());
        Assert.assertEquals("Hello!", registerComponent.getTitleString(),
                "This is not a register modal:(");

        logger.info("Enter credentials into the form");
        ManualRegisterComponent manualRegisterComponent = registerComponent.getManualRegisterComponent();
        manualRegisterComponent.registrationUser(userLoginCredentials);

        logger.info("Check if the mail with verification link is received");
        Assert.assertTrue(manualRegisterComponent.checkVerIfMailReceived());

    }

    @Test(dataProvider = "successRegistrationUserCreds", description = "GC-204")
    public void registrationWithTakenEmail(User userLoginCredentials) {
        loadApplication();
        logger.info("Starting registrationWithoutMailVerif. Input values = "
                + userLoginCredentials.toString());

        logger.info("Click on Sign up button");
        RegisterComponent registerComponent = new TopGuestComponent(driver).clickSignUpLink();

        logger.info("Get a title text of the modal window: "
                + registerComponent.getTitleString());
        Assert.assertEquals("Hello!", registerComponent.getTitleString(),
                "This is not a register modal:(");

        logger.info("Enter credentials into the form");
        ManualRegisterComponent manualRegisterComponent = registerComponent.getManualRegisterComponent();
        manualRegisterComponent.registrationUser(userLoginCredentials);

        logger.info("Wait till the success message is shown");
        WebDriverWait wait = new WebDriverWait(driver, 6);
        wait.until(ExpectedConditions.visibilityOf(registerComponent.getCongratsModal()));
        Assert.assertTrue(registerComponent.getCongratsModal().isDisplayed());

        logger.info("Wait till the login window is shown");
        LoginComponent loginComp = new LoginComponent(driver);
        wait.until(ExpectedConditions.visibilityOf(loginComp.getLoginModalWindow()));
        Assert.assertTrue(loginComp.getLoginModalWindow().isDisplayed());

        logger.info("Close login pop-up");
        loginComp.closeLoginComponent();

        logger.info("Click on Sign up button");
        registerComponent = new TopGuestComponent(driver).clickSignUpLink();
        logger.info("Get a title text of the modal window: "
                + registerComponent.getTitleString());
        Assert.assertEquals("Hello!", registerComponent.getTitleString(),
                "This is not a register modal:(");

        logger.info("Enter already used credentials into the form");
        manualRegisterComponent = registerComponent.getManualRegisterComponent();
        manualRegisterComponent.registrationUser(userLoginCredentials);
        Assert.assertEquals(manualRegisterComponent.getEmailValidatorText(),
                "The user already exists by this email",
                "The validation message is not equal to the expected one");

        logger.info("Check dublicated user is not created");
        DBQueries db = new DBQueries();
        User user = UserRepository.get().userCredentialsForRegistration();
        String email = user.getEmail();
        Assert.assertFalse(db.isUserEmailDuplicated(email));
    }


}


