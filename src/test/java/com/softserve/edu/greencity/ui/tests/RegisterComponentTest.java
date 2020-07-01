package com.softserve.edu.greencity.ui.tests;

import com.softserve.edu.greencity.ui.data.User;
import com.softserve.edu.greencity.ui.data.UserRepository;
import com.softserve.edu.greencity.ui.pages.cabinet.ManualLoginComponent;
import com.softserve.edu.greencity.ui.pages.cabinet.ManualRegisterComponent;
import com.softserve.edu.greencity.ui.pages.cabinet.RegisterComponent;
import com.softserve.edu.greencity.ui.pages.common.TopGuestComponent;
import com.softserve.edu.greencity.ui.pages.common.TopUserComponent;
import com.softserve.edu.greencity.ui.tools.DBQueries;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.*;

public class RegisterComponentTest extends GreenCityTestRunner {


    @DataProvider
    public Object[][] validUserCredentials() {
        return new Object[][]{
                {UserRepository.get().defaultUserCredentials()},};
    }

    @DataProvider
    public Object[][] randomValidUserCredentials() {
        return new Object[][]{{UserRepository.get()
                .userCredentialsForRegistration()},};
    }

    @DataProvider
    public Object[][] emptyFields() {
        return new Object[][]{
                {UserRepository.get().emptyUserCredentials()},
        };
    }

    @DataProvider
    public Object[][] invalidFields() {
        return new Object[][]{

                {UserRepository.get().invalidUserCredentials()},};
    }

    /**
     * Filling all the fields on the Register page without registering and
     * switch to Login page.
     */
    @Test(dataProvider = "validUserCredentials")
    public void checkIfSignUpButtonEnabled(User userLoginCredentials) {
        loadApplication();
        logger.info("Starting checkIfSignUpButtonEnabled. Input values = "
                + userLoginCredentials.toString());

        logger.info("Click on Sign up button");
        RegisterComponent registerComponent = new TopGuestComponent(driver).clickSignupLink();

        logger.info("Get a title text of the modal window: "
                + registerComponent.getTitleString());

        Assert.assertEquals("Hello!", registerComponent.getTitleString(),
                "This is not a register modal:(");
        ManualRegisterComponent manualRegisterComponent = new ManualRegisterComponent(driver);

        Assert.assertTrue(manualRegisterComponent.signUpIsDisabled());

        logger.info(
                "Filling out the fields with valid credentials without clicking on Sign up button");
        manualRegisterComponent.fillFieldsWithoutRegistration(userLoginCredentials);

        Assert.assertFalse(manualRegisterComponent.signUpIsDisabled());

    }

    /**
     * Opening register modal window, reading its title,
     * switching to login modal window, reading its title
     * to make sure the transition has been executed.
     */
    @Test
    public void switchBetweenTabs() {
        loadApplication();
        logger.info("Starting switchBetweenTabs");

        logger.info("Click on Sign up button");
        RegisterComponent registerComponent = new TopGuestComponent(driver).clickSignupLink();

        logger.info("Get a title text of the modal window: "
                + registerComponent.getTitleString());

        Assert.assertEquals("Hello!", registerComponent.getTitleString(),
                "This is not a register modal:(");

        Assert.assertEquals("Please enter your details to sign up", registerComponent.getSubtitleString(),
                "This is not a register modal:(");

        ManualLoginComponent manualLoginComponent = registerComponent.clickSignInLink();

        Assert.assertEquals("Welcome back!", manualLoginComponent.getTitleString(),
                "This is not a login modal:(");

        Assert.assertEquals("Please enter your details to sign in", manualLoginComponent.getSubtitleString(),
                "This is not a login modal:(");
    }

    /**
     * Putting empty values into register form
     * and reading the validation messages.
     */
    @Test(dataProvider = "emptyFields")
    public void checkEmptyFieldsValidation(User userLoginCredentials) {
        loadApplication();
        logger.info("Starting checkEmptyFieldsValidation. Input values = "
                + userLoginCredentials.toString());

        logger.info("Click on Sign up button");
        RegisterComponent registerComponent = new TopGuestComponent(driver).clickSignupLink();

        logger.info("Get a title text of the modal window: "
                + registerComponent.getTitleString());

        Assert.assertEquals("Hello!", registerComponent.getTitleString(),
                "This is not a register modal:(");

        ManualRegisterComponent manualRegisterComponent = registerComponent.createRegisterComponent();

        logger.info("Enter empty values into the form: ");
        manualRegisterComponent.registrationWrongUser(userLoginCredentials);

        Assert.assertEquals(manualRegisterComponent.getEmailValidatorText(),
                "Email is required",
                "The validation message is not equal to the expected one");

        Assert.assertEquals(manualRegisterComponent.getUserNameValidatorText(),
                "User name is required",
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
     */
    @Test(dataProvider = "invalidFields")
    public void checkInvalidFieldsValidation(User userLoginCredentials) {
        loadApplication();
        logger.info("Starting checkInvalidFieldsValidation. Input values = "
                + userLoginCredentials.toString());

        logger.info("Click on Sign up button");
        RegisterComponent registerComponent = new TopGuestComponent(driver).clickSignupLink();

        logger.info("Get a title text of the modal window: "
                + registerComponent.getTitleString());

        Assert.assertEquals("Hello!", registerComponent.getTitleString(),
                "This is not a register modal:(");

        ManualRegisterComponent manualRegisterComponent = registerComponent.createRegisterComponent();

        logger.info("Enter invalid values into the form: ");
        manualRegisterComponent.registrationWrongUser(userLoginCredentials);

// Any input in userName field is valid now, even the space - seems like a bug
//        Assert.assertEquals(manualRegisterComponent.getUserNameValidatorText(),
//                "User name cannot be empty");

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
     * //     * Test for registration with temporary email and random other credentials
     * //     * with logging and checking displayed user name in the top of the page.
     * //     * @param userLoginCredentials
     * //
     */
    @Test(dataProvider = "randomValidUserCredentials")
    public void randomCredsRegistrationLogin(User userLoginCredentials) {
        loadApplication();
        logger.info("Starting randomCredsRegistrationLogin. Input values = "
                + userLoginCredentials.toString());

        logger.info("Click on Sign up button");
        RegisterComponent registerComponent = new TopGuestComponent(driver).clickSignupLink();

        logger.info("Get a title text of the modal window: "
                + registerComponent.getTitleString());

        Assert.assertEquals("Hello!", registerComponent.getTitleString(),
                "This is not a register modal:(");

        ManualRegisterComponent manualRegisterComponent = registerComponent.createRegisterComponent();

        logger.info("Enter random credentials and temporary email into the form: ");
        manualRegisterComponent.registrationNewRandomUser(userLoginCredentials);


       ManualLoginComponent manualLoginComponent = new ManualLoginComponent(driver);


        manualLoginComponent.inputEmail(userLoginCredentials.getEmail())
                .inputPassword(userLoginCredentials.getPassword())
                .clickLoginButton();

        logger.info("get Title curent page: " + driver.getTitle());
        Assert.assertEquals(driver.getTitle(), "Home",
                "you didn't log in successfully");

        TopUserComponent userComponent = new TopUserComponent(driver);
        logger.info("check TopUserName: " + userComponent.getUserNameButtonText());
        Assert.assertEquals(userComponent.getUserNameButtonText(), userLoginCredentials.getUserName());

    }

    @AfterTest
    public void deleteRegisteredUser() throws SQLException {
        DBQueries queryObj = new DBQueries();
        queryObj.deleteUserByEmail("GCSignUpUser@gmail.com");
    }

}
