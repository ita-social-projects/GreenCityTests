package com.softserve.edu.greencity.ui.tests;

import com.softserve.edu.greencity.ui.data.User;
import com.softserve.edu.greencity.ui.data.UserRepository;
import com.softserve.edu.greencity.ui.pages.cabinet.LoginComponent;
import com.softserve.edu.greencity.ui.pages.cabinet.ManualRegisterComponent;
import com.softserve.edu.greencity.ui.pages.cabinet.RegisterComponent;
import com.softserve.edu.greencity.ui.pages.common.TopGuestComponent;
import com.softserve.edu.greencity.ui.tools.ElementsCustomMethods;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SignUpTests extends GreenCityTestRunner {

    //GC-501
    @Test
    public void navigateFromSignInToSignUp() {

        loadApplication();
        logger.info("Starting navigateFromSignInToSignUp");

        logger.info("Click on Sign in button");
        LoginComponent loginComponent = new TopGuestComponent(driver).clickSignInLink();

        Assert.assertEquals("Welcome back!", loginComponent.getTitleString(),
                "This is not a login modal:(");

        Assert.assertEquals("Please enter your details to sign in", loginComponent.getSubtitleString(),
                "This is not a login modal:(");


        logger.info("Click on Sign up button");
        RegisterComponent registerComponent = loginComponent.clickSignUpLink();

        logger.info("Get a title text of the modal window: "
                + registerComponent.getTitleString());

        Assert.assertEquals("Hello!", registerComponent.getTitleString(),
                "This is not a register modal:(");

        Assert.assertEquals("Please enter your details to sign up", registerComponent.getSubtitleString(),
                "This is not a register modal:(");

    }

    //GC-509
    @Test(dataProvider = "invalidEmail")
    public void invalidEmailRegistration(User invalidEmailCredentials) {
        loadApplication();
        logger.info("Starting checkInvalidFieldsValidation. Input values = "
                + invalidEmailCredentials.toString());

        logger.info("Click on Sign up button");
        ManualRegisterComponent manualRegisterComponent = new TopGuestComponent(driver)
                .clickSignUpLink()
                .getManualRegisterComponent();

        logger.info("Enter invalid email and valid name and password into the form: ");
        manualRegisterComponent.fillFieldsWithoutRegistration(invalidEmailCredentials);

        Assert.assertEquals(manualRegisterComponent.getEmailValidatorText(),
                "Please check that your e-mail address is indicated correctly",
                "The validation message is not equal to the expected one");

        Assert.assertTrue(manualRegisterComponent.signUpIsDisabled());

    }

    @DataProvider
    public Object[][] invalidEmail() {
        return new Object[][]{
                {UserRepository.get().invalidEmailUserCredentials()},};
    }

    //GC-519
    @Test(dataProvider = "invalidConfirmPass")
    public void invalidConfirmPassRegistration(User userLoginCredentials) {
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
        manualRegisterComponent.fillFieldsWithoutRegistration(userLoginCredentials);

        Assert.assertEquals(manualRegisterComponent.getPasswordConfirmValidatorText(),
                "Passwords do not match",
                "The validation message is not equal to the expected one");

        Assert.assertTrue(manualRegisterComponent.signUpIsDisabled());

    }

    @DataProvider
    public Object[][] invalidConfirmPass() {
        return new Object[][]{
                {UserRepository.get().invalidConfirmPassCredentials()},};
    }

    //Password
    @Test(dataProvider = "invalidPassUppercaseUserCreds")
    public void invalidPassUppercaseValidation(User userLoginCredentials) {
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
        manualRegisterComponent.fillFieldsWithoutRegistration(userLoginCredentials);

        Assert.assertEquals(manualRegisterComponent.getPasswordValidatorText(),
                "Password has contain at least one character of Uppercase letter (A-Z), " +
                        "Lowercase letter (a-z), Digit (0-9), Special character (~`!@#$%^&*()+=_-{}[]|:;”’?/<>,.)",
                "The validation message is not equal to the expected one");

        Assert.assertTrue(manualRegisterComponent.signUpIsDisabled());

    }

    @DataProvider
    public Object[][] invalidPassUppercaseUserCreds() {
        return new Object[][]{
                {UserRepository.get().invalidPassUppercaseUserCreds()},};
    }

    @Test(dataProvider = "invalidPassDigitUserCreds")
    public void invalidPassDigitValidation(User userLoginCredentials) {
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
        manualRegisterComponent.fillFieldsWithoutRegistration(userLoginCredentials);

        Assert.assertEquals(manualRegisterComponent.getPasswordValidatorText(),
                "Password has contain at least one character of Uppercase letter (A-Z), " +
                        "Lowercase letter (a-z), Digit (0-9), Special character (~`!@#$%^&*()+=_-{}[]|:;”’?/<>,.)",
                "The validation message is not equal to the expected one");

        Assert.assertTrue(manualRegisterComponent.signUpIsDisabled());

    }

    @DataProvider
    public Object[][] invalidPassDigitUserCreds() {
        return new Object[][]{
                {UserRepository.get().invalidPassDigitUserCreds()},};
    }

    @Test(dataProvider = "invalidPassLowercaseUserCreds")
    public void invalidPassLowercaseValidation(User userLoginCredentials) {
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
        manualRegisterComponent.fillFieldsWithoutRegistration(userLoginCredentials);

        Assert.assertEquals(manualRegisterComponent.getPasswordValidatorText(),
                "Password has contain at least one character of Uppercase letter (A-Z), " +
                        "Lowercase letter (a-z), Digit (0-9), Special character (~`!@#$%^&*()+=_-{}[]|:;”’?/<>,.)",
                "The validation message is not equal to the expected one");

        Assert.assertTrue(manualRegisterComponent.signUpIsDisabled());

    }

    @DataProvider
    public Object[][] invalidPassLowercaseUserCreds() {
        return new Object[][]{
                {UserRepository.get().invalidPassLowercaseUserCreds()},};
    }

    @Test(dataProvider = "invalidPassSpecCharUserCreds")
    public void invalidPassSpecCharValidation(User userLoginCredentials) {
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
        manualRegisterComponent.fillFieldsWithoutRegistration(userLoginCredentials);


        Assert.assertEquals(manualRegisterComponent.getPasswordValidatorText(),
                "Password has contain at least one character of Uppercase letter (A-Z), " +
                        "Lowercase letter (a-z), Digit (0-9), Special character (~`!@#$%^&*()+=_-{}[]|:;”’?/<>,.)",
                "The validation message is not equal to the expected one");

        Assert.assertTrue(manualRegisterComponent.signUpIsDisabled());


    }

    @DataProvider
    public Object[][] invalidPassSpecCharUserCreds() {
        return new Object[][]{
                {UserRepository.get().invalidPassSpecCharUserCreds()},};
    }

    //GC-198
    @Test(dataProvider = "invalidPassLengthUserCreds")
    public void invalidPassLengthValidation(User userLoginCredentials) {
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
        manualRegisterComponent.fillFieldsWithoutRegistration(userLoginCredentials);

        Assert.assertEquals(manualRegisterComponent.getPasswordValidatorText(),
                "Password must be at least 8 characters in length",
                "The validation message is not equal to the expected one");

        Assert.assertTrue(manualRegisterComponent.signUpIsDisabled());

    }

    @DataProvider
    public Object[][] invalidPassLengthUserCreds() {
        return new Object[][]{
                {UserRepository.get().invalidPassLengthUserCreds()},};
    }

    @Test(dataProvider = "invalidPassSpaceUserCreds")
    public void invalidPassSpaceValidation(User userLoginCredentials) {
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
        manualRegisterComponent.fillFieldsWithoutRegistration(userLoginCredentials);

        Assert.assertEquals(manualRegisterComponent.getPasswordValidatorText(),
                "Password must be at least 8 characters long without spaces",
                "The validation message is not equal to the expected one");

        Assert.assertFalse(manualRegisterComponent.signUpIsDisabled());

    }

    @DataProvider
    public Object[][] invalidPassSpaceUserCreds() {
        return new Object[][]{
                {UserRepository.get().invalidPassSpaceUserCreds()},};
    }

    //GC-1214
    @Test
    public void checkCloseRegisterModalButton() {
        loadApplication();
        logger.info("Starting checkCloseRegisterModalButton:");

        logger.info("Click on Sign up button");
        RegisterComponent registerComponent = new TopGuestComponent(driver).clickSignUpLink();

        logger.info("Get a title text of the modal window: "
                + registerComponent.getTitleString());

        Assert.assertEquals("Hello!", registerComponent.getTitleString(),
                "This is not a register modal:(");

        ElementsCustomMethods custMethObj = new ElementsCustomMethods(driver);
        Boolean isPresent = custMethObj.isElementPresent(By.cssSelector(RegisterComponent.MODAL_WINDOW_CSS));

        Assert.assertTrue(isPresent);

        registerComponent.closeRegisterComponentModal();

        Boolean isGone = custMethObj.waitTillElementGone(driver, By.cssSelector(RegisterComponent.MODAL_WINDOW_CSS), 6000, 2000);
        Assert.assertTrue(isGone);


    }

    @Test(description = "GC-527")
    public void checkPasswordIsHidden() {
        loadApplication();
        logger.info("Starting checkInvalidFieldsValidation:");

        logger.info("Click on Sign up button");
        RegisterComponent registerComponent = new TopGuestComponent(driver).clickSignUpLink();

        logger.info("Get a title text of the modal window: "
                + registerComponent.getTitleString());

        Assert.assertEquals("Hello!", registerComponent.getTitleString(),
                "This is not a register modal:(");

        ManualRegisterComponent manualRegisterComponent = registerComponent.getManualRegisterComponent();

        logger.info("Enter invalid values into the form: ");
        manualRegisterComponent.fillPasswordFieldPassHidden("Valid!1");

        Assert.assertEquals(manualRegisterComponent.getPasswordField().getAttribute("type"),
                "password",
                "The password is not hidden.");

        Assert.assertTrue(manualRegisterComponent.signUpIsDisabled());

    }


    //GC-205
    //Verify that user is not registered with too long 'User name'
    //GC-204
    //Verify that Email must be existence and unique while new user registration
    //GC-200
    //Verify that unregistered user sees popup window 'Sign up' after clicking on the “My habits” button
    //GC-203
    //Verify that User is redirected to My habits as a Registered User after he has entered valid credentials
   //GC-216
    // Verify 'Sign up' page UI


    //GC-485
    //Verify that background behind the Registration form is dimmed out
    @Test
    public void checkBackgroundIsDimmed() {

        logger.info("Starting checkBackgroundIsDimmed:");
        loadApplication();
        logger.info("Click on Sign up button");

        Assert.assertTrue(driver.findElement(By.id("b_bb_b_dsin")).getAttribute("aria-hidden") == null);
        RegisterComponent registerComponent = new TopGuestComponent(driver).clickSignUpLink();
        Assert.assertTrue(driver.findElement(By.id("b_bb_b_dsin")).getAttribute("aria-hidden") != null);
    }

    //GC-487
    //Verify UI of the Registration form on different screen resolutions

}
