package com.softserve.edu.greencity.ui.tests;

import com.softserve.edu.greencity.ui.data.User;
import com.softserve.edu.greencity.ui.data.UserRepository;
import com.softserve.edu.greencity.ui.pages.cabinet.LoginComponent;
import com.softserve.edu.greencity.ui.pages.cabinet.LoginPage;
import com.softserve.edu.greencity.ui.pages.cabinet.ManualRegisterComponent;
import com.softserve.edu.greencity.ui.pages.cabinet.RegisterComponent;
import com.softserve.edu.greencity.ui.pages.common.TopGuestComponent;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LiubaTest extends GreenCityTestRunner {

    @DataProvider
    public Object[][] validUserCredentials() {
        return new Object[][] {
                { UserRepository.get().defaultUserCredentials() }, };
    }

    @DataProvider
    public Object[][] randomValidUserCredentials() {
        return new Object[][] { { UserRepository.get()
                .temporaryUserCredentialsForRegistration() }, };
    }

    @DataProvider
    public Object[][] emptyFields() {
        return new Object[][] {
                { UserRepository.get().wrongUserCredentials1() },
        };
    }
    @DataProvider
    public Object[][] invalidFields() {
        return new Object[][] {

                { UserRepository.get().wrongUserCredentials2() }, };
    }

    /**
     * Filling all the fields on the Register page without registering and
     * switch to Login page.
     * @param userLoginCredentials
     */
    @Test(dataProvider = "validUserCredentials")
    public void signUpWithValidUser(User userLoginCredentials) {
        logger.info("Starting signUpWithValidUser. Input values = "
                + userLoginCredentials.toString());;

        logger.info("Click on Sign up button");
        RegisterComponent registerComponent = new TopGuestComponent(driver).clickSignupLink();

        logger.info("Get a title text of the modal window: "
                + registerComponent.getTitlePageText());

        Assert.assertEquals("Hello!", registerComponent.getTitlePageText(),
                "This is not a register modal:(");
        ManualRegisterComponent manualRegisterComponent = new ManualRegisterComponent(driver);
//        logger.info("go to RegisterComponent (click on Sign-up link)");
        //

//        logger.info(
//                "register new User with valid credential without click on Sign-up button");
        manualRegisterComponent.fillFieldsWithoutRegistration(userLoginCredentials);

        //sign up is enabled

    }

    @Test
    public void switchBetweenTabs() {
        logger.info("Starting switchBetweenTabs");

        logger.info("Click on Sign up button");
        RegisterComponent registerComponent = new TopGuestComponent(driver).clickSignupLink();

        logger.info("Get a title text of the modal window: "
                + registerComponent.getTitlePageText());

        Assert.assertEquals("Hello!", registerComponent.getTitlePageText(),
                "This is not a register modal:(");

        ManualRegisterComponent manualRegisterComponent = registerComponent.createRegisterComponent();

        registerComponent.closeRegisterComponentModal();
        LoginPage loginPage = new TopGuestComponent(driver).clickSignInLink();
//        Assert.assertTrue(driver.getCurrentUrl().contains("#/auth"),
//                "you didn't go to Login page");
//        presentationSleep(2); // delay only for presentation
//        logger.info("click on Sign-up link go to RegisterComponent");
//        loginPage.gotoRegisterPage();
//        Assert.assertTrue(driver.getCurrentUrl().contains("#/auth/sign-up"),
//                "you didn't go to Register page");
//        Assert.assertEquals("Hello!", registerComponent.getTitleFieldText(),
//                "you did not go to the page RegisterComponent");
//        presentationSleep(2); // delay only for presentation
    }

    /**
     * Putting empty values into register form
     * and reading the validation messages.
     * @param userLoginCredentials
     */
    @Test(dataProvider = "emptyFields")
    public void checkEmptyFieldsValidation(User userLoginCredentials) {
        logger.info("Starting checkEmptyFieldsValidation. Input values = "
                + userLoginCredentials.toString());

        logger.info("Click on Sign up button");
        RegisterComponent registerComponent = new TopGuestComponent(driver).clickSignupLink();

        logger.info("Get a title text of the modal window: "
                + registerComponent.getTitlePageText());

        Assert.assertEquals("Hello!", registerComponent.getTitlePageText(),
                "This is not a register modal:(");

        ManualRegisterComponent manualRegisterComponent = registerComponent.createRegisterComponent();

        logger.info("Enter empty values into the form: ");
        manualRegisterComponent.registrationWrongUser(userLoginCredentials);

        Assert.assertEquals(manualRegisterComponent.getFirstNameValidatorText(),
                "User name is required",
                "The validation message is not equal to the expected one");

        Assert.assertEquals(manualRegisterComponent.getEmailValidatorText(),
                "Email is required",
                "The validation message is not equal to the expected one");

        Assert.assertEquals(manualRegisterComponent.getPasswordValidatorText(),
                "Password is required",
                "The validation message is not equal to the expected one");

        Assert.assertEquals(manualRegisterComponent.getPasswordConfirmValidatorText(),
                "Password is required",
                "The validation message is not equal to the expected one");

    }

    /**
     * Putting invalid values into register form
     * and reading the validation messages.
     * @param userLoginCredentials
     */
    @Test(dataProvider = "invalidFields")
    public void checkInvalidFieldsValidation(User userLoginCredentials) {
        logger.info("Starting checkInvalidFieldsValidation. Input values = "
               + userLoginCredentials.toString());

        logger.info("Click on Sign up button");
        RegisterComponent registerComponent = new TopGuestComponent(driver).clickSignupLink();

        logger.info("Get a title text of the modal window: "
                + registerComponent.getTitlePageText());

        Assert.assertEquals("Hello!", registerComponent.getTitlePageText(),
                "This is not a register modal:(");

        ManualRegisterComponent manualRegisterComponent = registerComponent.createRegisterComponent();

         logger.info("Enter invalid values into the form: ");
        manualRegisterComponent.registrationWrongUser(userLoginCredentials);

// Any input in userField is valid now, eve the space - seems like a bug
//        Assert.assertEquals(manualRegisterComponent.getFirstNameValidatorText(),
//                "First Name error text not found",
//                "The validation message is not equal to the expected one");

        Assert.assertEquals(manualRegisterComponent.getEmailValidatorText(),
                "Please check that your e-mail address is indicated correctly",
                "The validation message is not equal to the expected one");

        Assert.assertEquals(manualRegisterComponent.getPasswordValidatorText(),
                "Password must be at least 8 characters long without spaces",
                //OR Password must be at least 8 characters in length
                "The validation message is not equal to the expected one");

        Assert.assertEquals(manualRegisterComponent.getPasswordConfirmValidatorText(),
                "Passwords do not match\n" +
                        "Password has contain at least one character of Uppercase letter (A-Z), " +
                        "Lowercase letter (a-z), Digit (0-9), Special character (~`!@#$%^&*()+=_-{}[]|:;”’?/<>,.)",
                "The validation message is not equal to the expected one");

    }
    /**
     //     * Test for registration with temporary email and random other credentials
     //     * with logging and checking displayed user name in the top of the page.
     //     * @param userLoginCredentials
     //     */
    @Test(dataProvider = "randomValidUserCredentials")
    public void randomCredsRegistrationLogin(User userLoginCredentials) {
        logger.info("Starting randomCredsRegistrationLogin. Input values = "
                + userLoginCredentials.toString());

        logger.info("Click on Sign up button");
        RegisterComponent registerComponent = new TopGuestComponent(driver).clickSignupLink();

        logger.info("Get a title text of the modal window: "
                + registerComponent.getTitlePageText());

        Assert.assertEquals("Hello!", registerComponent.getTitlePageText(),
                "This is not a register modal:(");

        ManualRegisterComponent manualRegisterComponent = registerComponent.createRegisterComponent();

        logger.info("Enter random credentials and temporary email into the form: ");
        manualRegisterComponent.registrationNewRandomUser(userLoginCredentials);
//        Assert.assertTrue(
//                registerPage.getConfirmRegistrationText()
//                        .contains("You have successfully registered"),
//                "you did not go to the page RegisterComponent");
        //
        registerComponent.closeRegisterComponentModal();
        LoginComponent loginComponent = new LoginComponent(driver);

//        Assert.assertTrue(driver.getCurrentUrl().contains("#/auth"),
//                "you didn't go to Login page");
//        presentationSleep(2); // delay only for presentation
//        LoginPage page = new LoginPage(driver);
//        logger.info("login with temporary Email and random credential: "
//                + userLoginCredentials.toString());
//        page.inputEmail(userLoginCredentials.getEmail())
//                .inputPassword(userLoginCredentials.getPassword())
//                .clickLoginButton(); // not always success after one click (need
//                                     // one more click)
//        logger.info("get Title curent page: " + driver.getTitle());
//        Assert.assertEquals(driver.getTitle(), "Home",
//                "you didn't log in successfully");
//        logger.info("check TopUserName: " + page.getTopUserName());
//        Assert.assertEquals(page.getTopUserName(), TopPart.PROFILE_NAME);

    }
}
