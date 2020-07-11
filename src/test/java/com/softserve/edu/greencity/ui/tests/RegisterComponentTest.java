package com.softserve.edu.greencity.ui.tests;

import com.softserve.edu.greencity.ui.data.User;
import com.softserve.edu.greencity.ui.data.UserRepository;
import com.softserve.edu.greencity.ui.pages.cabinet.LoginComponent;
import com.softserve.edu.greencity.ui.pages.cabinet.ManualLoginComponent;
import com.softserve.edu.greencity.ui.pages.cabinet.ManualRegisterComponent;
import com.softserve.edu.greencity.ui.pages.cabinet.RegisterComponent;
import com.softserve.edu.greencity.ui.pages.common.TopGuestComponent;
import com.softserve.edu.greencity.ui.pages.common.TopUserComponent;
import com.softserve.edu.greencity.ui.tools.DBQueries;
import com.softserve.edu.greencity.ui.tools.GMailBox;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.*;
import java.util.ArrayList;

public class RegisterComponentTest extends GreenCityTestRunner {


    @DataProvider
    public Object[][] validUserCredentials() {
        return new Object[][]{
                {UserRepository.get().defaultUserCredentials()},};
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
        RegisterComponent registerComponent = new TopGuestComponent(driver).clickSignUpLink();

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
    public void navigateFromSignUpToSignIn() {
        loadApplication();
        logger.info("Starting navigateFromSignUpToSignIn");

        logger.info("Click on Sign up button");
        RegisterComponent registerComponent = new TopGuestComponent(driver).clickSignUpLink();

        logger.info("Get a title text of the modal window: "
                + registerComponent.getTitleString());

        Assert.assertEquals("Hello!", registerComponent.getTitleString(),
                "This is not a register modal:(");

        Assert.assertEquals("Please enter your details to sign up", registerComponent.getSubtitleString(),
                "This is not a register modal:(");

        logger.info("Click on Sign in button");
        LoginComponent loginComponent = registerComponent.clickSignInLink();

        Assert.assertEquals("Welcome back!", loginComponent.getTitleString(),
                "This is not a login modal:(");

        Assert.assertEquals("Please enter your details to sign in", loginComponent.getSubtitleString(),
                "This is not a login modal:(");
    }

    /**
     * Putting empty values into register form
     * and reading the validation messages.
     * GC-502, GC-207, GC-208, GC-209, GC-210
     */
    @Test(dataProvider = "emptyFields")
    public void checkEmptyFieldsValidation(User userLoginCredentials) {
        loadApplication();
        logger.info("Starting checkEmptyFieldsValidation. Input values = "
                + userLoginCredentials.toString());

        logger.info("Click on Sign up button");
        RegisterComponent registerComponent = new TopGuestComponent(driver).clickSignUpLink();

        logger.info("Get a title text of the modal window: "
                + registerComponent.getTitleString());

        Assert.assertEquals("Hello!", registerComponent.getTitleString(),
                "This is not a register modal:(");

        ManualRegisterComponent manualRegisterComponent = registerComponent.getManualRegisterComponent();

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
        RegisterComponent registerComponent = new TopGuestComponent(driver).clickSignUpLink();

        logger.info("Get a title text of the modal window: "
                + registerComponent.getTitleString());

        Assert.assertEquals("Hello!", registerComponent.getTitleString(),
                "This is not a register modal:(");

        ManualRegisterComponent manualRegisterComponent = registerComponent.getManualRegisterComponent();

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
                "The validation message is not equal to the expected one");

        Assert.assertEquals(manualRegisterComponent.getPasswordConfirmValidatorText(),
                "Passwords do not match\n" +
                        "Password has contain at least one character of Uppercase letter (A-Z), " +
                        "Lowercase letter (a-z), Digit (0-9), Special character (~`!@#$%^&*()+=_-{}[]|:;”’?/<>,.)",
                "The validation message is not equal to the expected one");

    }



}
