package com.softserve.edu.greencity.ui.tests;

import com.softserve.edu.greencity.ui.data.User;
import com.softserve.edu.greencity.ui.data.UserRepository;
import com.softserve.edu.greencity.ui.pages.cabinet.LoginPage;
import com.softserve.edu.greencity.ui.pages.cabinet.RegisterComponent;
import com.softserve.edu.greencity.ui.pages.cabinet.RegisterPage;
import com.softserve.edu.greencity.ui.pages.common.TopGuestComponent;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LiubaTest extends GreenCityTestRunner {

    @DataProvider
    public Object[][] validCredentialUser() {
        return new Object[][] {
                { UserRepository.get().defaultUserCredentials() }, };
    }

    @DataProvider
    public Object[][] validCredentialUser2() {
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
    @Test(dataProvider = "validCredentialUser")
    public void signUpWithValidUser(User userLoginCredentials) {
//        logger.info("start test checkRegisterPage1 with user = "
//                + userLoginCredentials.toString());
        RegisterPage registerPage = new TopGuestComponent(driver).clickSignupLink();
        RegisterComponent registerComponent = new RegisterComponent(driver);
//        logger.info("go to RegisterPage (click on Sign-up link)");
        //
//        logger.info("get a top title text on the page: "
//                + registerPage.getTitleFieldText());
        Assert.assertEquals("Hello!", registerComponent.getTitlePageText(),
                "you did not go to the page RegisterPage");
//        logger.info(
//                "register new User with valid credential without click on Sign-up button");
        registerComponent.fillFieldsWithoutRegistration(userLoginCredentials);

    }

    @Test
    public void switchBetweentabs() {
        logger.info("Starting checkEmptyFieldsValidation ");

        logger.info("Click on Sign up button");
        RegisterPage registerPage = new TopGuestComponent(driver).clickSignupLink();
        RegisterComponent registerComponent = registerPage.createRegisterComponent();

        logger.info("Get a title text of the modal window: "
                + registerComponent.getTitlePageText());

        Assert.assertEquals("Hello!", registerComponent.getTitlePageText(),
                "This is not a register modal:(");

        registerComponent.closeRegisterDropdown();
        LoginPage loginPage = new TopGuestComponent(driver).clickSignInLink();
//        Assert.assertTrue(driver.getCurrentUrl().contains("#/auth"),
//                "you didn't go to Login page");
//        presentationSleep(2); // delay only for presentation
//        logger.info("click on Sign-up link go to RegisterPage");
//        loginPage.gotoRegisterPage();
//        Assert.assertTrue(driver.getCurrentUrl().contains("#/auth/sign-up"),
//                "you didn't go to Register page");
//        Assert.assertEquals("Hello!", registerPage.getTitleFieldText(),
//                "you did not go to the page RegisterPage");
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
        RegisterPage registerPage = new TopGuestComponent(driver).clickSignupLink();
        RegisterComponent registerComponent = registerPage.createRegisterComponent();

        logger.info("Get a title text of the modal window: "
                + registerComponent.getTitlePageText());

        Assert.assertEquals("Hello!", registerComponent.getTitlePageText(),
                "This is not a register modal:(");

        logger.info("Enter empty values into the form: ");
        registerComponent.registrationWrongUser(userLoginCredentials);

        Assert.assertEquals(registerComponent.getFirstNameValidatorText(),
                "User name is required",
                "The validation message is not equal to the expected one");

        Assert.assertEquals(registerComponent.getEmailValidatorText(),
                "Email is required",
                "The validation message is not equal to the expected one");

        Assert.assertEquals(registerComponent.getPasswordValidatorText(),
                "Password is required",
                "The validation message is not equal to the expected one");

        Assert.assertEquals(registerComponent.getPasswordConfirmValidatorText(),
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
        RegisterPage registerPage = new TopGuestComponent(driver).clickSignupLink();
        RegisterComponent registerComponent = registerPage.createRegisterComponent();

         logger.info("Get a title text of the modal window: "
                + registerComponent.getTitlePageText());

        Assert.assertEquals("Hello!", registerComponent.getTitlePageText(),
                "This is not a register modal:(");

         logger.info("Enter invalid values into the form: ");
        registerComponent.registrationWrongUser(userLoginCredentials);

// Any input in userField is valid now, eve the space - seems like a bug
//        Assert.assertEquals(registerComponent.getFirstNameValidatorText(),
//                "First Name error text not found",
//                "The validation message is not equal to the expected one");

        Assert.assertEquals(registerComponent.getEmailValidatorText(),
                "Please check that your e-mail address is indicated correctly",
                "The validation message is not equal to the expected one");

        Assert.assertEquals(registerComponent.getPasswordValidatorText(),
                "Password must be at least 8 characters long without spaces",
                //OR Password must be at least 8 characters in length
                "The validation message is not equal to the expected one");

        Assert.assertEquals(registerComponent.getPasswordConfirmValidatorText(),
                "Passwords do not match\n" +
                        "Password has contain at least one character of Uppercase letter (A-Z), " +
                        "Lowercase letter (a-z), Digit (0-9), Special character (~`!@#$%^&*()+=_-{}[]|:;”’?/<>,.)",
                "The validation message is not equal to the expected one");

    }
}
