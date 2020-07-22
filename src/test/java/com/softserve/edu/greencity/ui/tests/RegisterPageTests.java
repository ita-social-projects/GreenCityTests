package com.softserve.edu.greencity.ui.tests;

import com.softserve.edu.greencity.ui.data.User;
import com.softserve.edu.greencity.ui.data.UserRepository;
import com.softserve.edu.greencity.ui.pages.cabinet.LoginComponent;
import com.softserve.edu.greencity.ui.pages.cabinet.ManualRegisterComponent;
import com.softserve.edu.greencity.ui.pages.cabinet.RegisterComponent;
import com.softserve.edu.greencity.ui.pages.common.TopGuestComponent;
import com.softserve.edu.greencity.ui.tools.ElementsCustomMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class RegisterPageTests extends GreenCityTestRunner {

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

    @DataProvider
    public Object[][] invalidEmail() {
        return new Object[][]{
                {UserRepository.get().invalidEmailUserCredentials()},};
    }

    @DataProvider
    public Object[][] invalidPassUppercaseUserCreds() {
        return new Object[][]{
                {UserRepository.get().invalidPassUppercaseUserCreds()},};
    }

    @DataProvider
    public Object[][] invalidPassDigitUserCreds() {
        return new Object[][]{
                {UserRepository.get().invalidPassDigitUserCreds()},};
    }

    @DataProvider
    public Object[][] invalidPassLowercaseUserCreds() {
        return new Object[][]{
                {UserRepository.get().invalidPassLowercaseUserCreds()},};
    }

    @DataProvider
    public Object[][] invalidPassSpecCharUserCreds() {
        return new Object[][]{
                {UserRepository.get().invalidPassSpecCharUserCreds()},};
    }

    @DataProvider
    public Object[][] invalidPassLengthUserCreds() {
        return new Object[][]{
                {UserRepository.get().invalidPassLengthUserCreds()},};
    }

    @DataProvider
    public Object[][] invalidPassSpaceUserCreds() {
        return new Object[][]{
                {UserRepository.get().invalidPassSpaceUserCreds()},};
    }

    @DataProvider
    public Object[][] invalidNameCredentials() {
        return new Object[][]{
                {UserRepository.get().invalidNameCredentials()},};
    }

    @DataProvider
    public Object[][] invalidConfirmPass() {
        return new Object[][]{
                {UserRepository.get().invalidConfirmPassCredentials()},};
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
     */
    @Test(dataProvider = "emptyFields", description = "GC-502, GC-207, GC-208, GC-209, GC-210")
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

        logger.info("Enter empty values into the form");
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

        logger.info("Enter invalid values into the form");
        manualRegisterComponent.registrationWrongUser(userLoginCredentials);

        Assert.assertEquals(manualRegisterComponent.getEmailValidatorText(),
                "Please check that your e-mail address is indicated correctly",
                "The validation message is not equal to the expected one");

        Assert.assertEquals(manualRegisterComponent.getPasswordValidatorText(),
                "Password must be at least 8 characters in length",
                "The validation message is not equal to the expected one");

        Assert.assertEquals(manualRegisterComponent.getPasswordConfirmValidatorText(),
                "Password has contain at least one character of Uppercase letter (A-Z), " +
                        "Lowercase letter (a-z), Digit (0-9), Special character (~`!@#$%^&*()+=_-{}[]|:;”’?/<>,.)",
                "The validation message is not equal to the expected one");

    }


    @Test(description = "GC-501")
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


    @Test(dataProvider = "invalidEmail", description = "GC-509")
    public void invalidEmailRegistration(User invalidEmailCredentials) {
        loadApplication();
        logger.info("Starting checkInvalidFieldsValidation. Input values = "
                + invalidEmailCredentials.toString());

        logger.info("Click on Sign up button");
        ManualRegisterComponent manualRegisterComponent = new TopGuestComponent(driver)
                .clickSignUpLink()
                .getManualRegisterComponent();

        logger.info("Enter invalid email and valid name and password into the form");
        manualRegisterComponent.fillFieldsWithoutRegistration(invalidEmailCredentials);

        Assert.assertEquals(manualRegisterComponent.getEmailValidatorText(),
                "Please check that your e-mail address is indicated correctly",
                "The validation message is not equal to the expected one");

        Assert.assertTrue(manualRegisterComponent.signUpIsDisabled());

    }


    @Test(dataProvider = "invalidConfirmPass", description = "GC-519")
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

        logger.info("Enter invalid values into the form ");
        manualRegisterComponent.fillFieldsWithoutRegistration(userLoginCredentials);

        Assert.assertEquals(manualRegisterComponent.getPasswordConfirmValidatorText(),
                "Passwords do not match",
                "The validation message is not equal to the expected one");

        Assert.assertTrue(manualRegisterComponent.signUpIsDisabled());

    }


    @Test(dataProvider = "invalidPassUppercaseUserCreds", description = "GC-517")
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

        logger.info("Enter invalid values into the form ");
        manualRegisterComponent.fillFieldsWithoutRegistration(userLoginCredentials);

        Assert.assertEquals(manualRegisterComponent.getPasswordValidatorText(),
                "Password has contain at least one character of Uppercase letter (A-Z), " +
                        "Lowercase letter (a-z), Digit (0-9), Special character (~`!@#$%^&*()+=_-{}[]|:;”’?/<>,.)",
                "The validation message is not equal to the expected one");

        Assert.assertTrue(manualRegisterComponent.signUpIsDisabled());

    }


    @Test(dataProvider = "invalidPassDigitUserCreds", description = "GC-517")
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

        logger.info("Enter invalid values into the form");
        manualRegisterComponent.fillFieldsWithoutRegistration(userLoginCredentials);

        Assert.assertEquals(manualRegisterComponent.getPasswordValidatorText(),
                "Password has contain at least one character of Uppercase letter (A-Z), " +
                        "Lowercase letter (a-z), Digit (0-9), Special character (~`!@#$%^&*()+=_-{}[]|:;”’?/<>,.)",
                "The validation message is not equal to the expected one");

        Assert.assertTrue(manualRegisterComponent.signUpIsDisabled());

    }

    @Test(dataProvider = "invalidPassLowercaseUserCreds", description = "GC-517")
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

        logger.info("Enter invalid values into the form");
        manualRegisterComponent.fillFieldsWithoutRegistration(userLoginCredentials);

        Assert.assertEquals(manualRegisterComponent.getPasswordValidatorText(),
                "Password has contain at least one character of Uppercase letter (A-Z), " +
                        "Lowercase letter (a-z), Digit (0-9), Special character (~`!@#$%^&*()+=_-{}[]|:;”’?/<>,.)",
                "The validation message is not equal to the expected one");

        Assert.assertTrue(manualRegisterComponent.signUpIsDisabled());

    }


    @Test(dataProvider = "invalidPassSpecCharUserCreds", description = "GC-517")
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

        logger.info("Enter invalid values into the form ");
        manualRegisterComponent.fillFieldsWithoutRegistration(userLoginCredentials);


        Assert.assertEquals(manualRegisterComponent.getPasswordValidatorText(),
                "Password has contain at least one character of Uppercase letter (A-Z), " +
                        "Lowercase letter (a-z), Digit (0-9), Special character (~`!@#$%^&*()+=_-{}[]|:;”’?/<>,.)",
                "The validation message is not equal to the expected one");

        Assert.assertTrue(manualRegisterComponent.signUpIsDisabled());


    }



    @Test(dataProvider = "invalidPassLengthUserCreds", description = "GC-198, GC-517")
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

        logger.info("Enter invalid values into the form ");
        manualRegisterComponent.fillFieldsWithoutRegistration(userLoginCredentials);

        Assert.assertEquals(manualRegisterComponent.getPasswordValidatorText(),
                "Password must be at least 8 characters in length",
                "The validation message is not equal to the expected one");

        Assert.assertTrue(manualRegisterComponent.signUpIsDisabled());

    }


    @Test(dataProvider = "invalidPassSpaceUserCreds", description = "GC-517")
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

        logger.info("Enter invalid values into the form ");
        manualRegisterComponent.fillFieldsWithoutRegistration(userLoginCredentials);

        Assert.assertEquals(manualRegisterComponent.getPasswordValidatorText(),
                "Password must be at least 8 characters long without spaces",
                "The validation message is not equal to the expected one");

        Assert.assertTrue(manualRegisterComponent.signUpIsDisabled());

    }

    @Test(description = "GC-499")
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
        boolean isPresent = custMethObj.isElementPresent(By.cssSelector(RegisterComponent.MODAL_WINDOW_CSS));

        Assert.assertTrue(isPresent);

        registerComponent.closeRegisterComponentModal();

        boolean isGone = custMethObj.waitTillElementGone(driver, By.cssSelector(RegisterComponent.MODAL_WINDOW_CSS), 6000, 2000);
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

        logger.info("Enter invalid values into the form ");
        manualRegisterComponent.fillPasswordFieldPassHidden("Valid!1");

        Assert.assertEquals(manualRegisterComponent.getPasswordField().getAttribute("type"),
                "password",
                "The password is not hidden.");

        Assert.assertTrue(manualRegisterComponent.signUpIsDisabled());

    }


    @Test(description = "GC-485")
    public void checkBackgroundIsDimmed() {

        logger.info("Starting checkBackgroundIsDimmed:");
        loadApplication();
        logger.info("Click on Sign up button");

        RegisterComponent registerComponent = new TopGuestComponent(driver).clickSignUpLink();

        Assert.assertTrue(driver.findElement(By.cssSelector(".cdk-overlay-backdrop"))
                .getAttribute("class").contains("cdk-overlay-dark-backdrop cdk-overlay-backdrop-showing"));
    }


        @Test(dataProvider = "invalidNameCredentials", description = "GC-205")
    public void checkUserFieldMaxLength(User userLoginCredentials) {
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

        logger.info("Entering values into the form ");
        manualRegisterComponent.registrationWrongUser(userLoginCredentials);

        String userFieldValue = manualRegisterComponent.getUserNameField().getAttribute("value");

        Assert.assertEquals(userFieldValue,
                "21CharString21CharSt",
                "The invalid string is not concatenated");

    }

}
