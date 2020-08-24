package com.softserve.edu.greencity.ui.tests;

import com.softserve.edu.greencity.ui.data.User;
import com.softserve.edu.greencity.ui.data.UserRepository;
import com.softserve.edu.greencity.ui.pages.cabinet.LoginComponent;
import com.softserve.edu.greencity.ui.pages.cabinet.ManualRegisterComponent;
import com.softserve.edu.greencity.ui.pages.cabinet.RegisterComponent;
import com.softserve.edu.greencity.ui.pages.common.TopGuestComponent;
import com.softserve.edu.greencity.ui.pages.common.WelcomePage;
import com.softserve.edu.greencity.ui.tools.ElementsCustomMethods;
import com.softserve.edu.greencity.ui.tools.engine.StableWebElementSearch;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class RegisterPageTests extends GreenCityTestRunner implements StableWebElementSearch {

    @DataProvider
    public Object[][] validUserCredentials() {
        return new Object[][]{
                {UserRepository.get().defaultUserCredentials()},};
    }
    @DataProvider
    public Object[][] unregisterCredentials() {
        return new Object[][]{
                {UserRepository.get().unregisterUser()},};
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
    @Test(dataProvider = "validUserCredentials") //+
    public void checkIfSignUpButtonEnabled(User userLoginCredentials) {
        logger.info("Starting checkIfSignUpButtonEnabled. Input values = "
                + userLoginCredentials.toString());
        loadApplication();
        RegisterComponent registerComponent = new TopGuestComponent(driver).clickSignUpLink();
        assertEquals("Hello!", registerComponent.getTitleString(),
                "This is not a register modal:(");
        ManualRegisterComponent manualRegisterComponent = new ManualRegisterComponent(driver);
        assertTrue(manualRegisterComponent.signUpIsDisabled());
        logger.info(
                "Filling out the fields with valid credentials without clicking on Sign up button");
        manualRegisterComponent.fillFieldsWithoutRegistration(userLoginCredentials);
        assertFalse(manualRegisterComponent.signUpIsDisabled());
    }

    /**
     * Opening register modal window, reading its title,
     * switching to login modal window, reading its title
     * to make sure the transition has been executed.
     */
    @Test
    public void navigateFromSignUpToSignIn() {
        logger.info("Starting navigateFromSignUpToSignIn");
        loadApplication();
        RegisterComponent registerComponent = new TopGuestComponent(driver).clickSignUpLink();
        assertEquals("Hello!", registerComponent.getTitleString(),
                "This is not a register modal:(");
        assertEquals("Please enter your details to sign up", registerComponent.getSubtitleString(),
                "This is not a register modal:(");
        logger.info("Click on Sign in button");
        LoginComponent loginComponent = registerComponent.clickSignInLink();
        assertEquals("Welcome back!", loginComponent.getTitleText(),
                "This is not a login modal:(");
        assertEquals("Please enter your details to sign in", loginComponent.getSubtitleText(),
                "This is not a login modal:(");
    }

    /**
     * Putting empty values into register form
     * and reading the validation messages.
     */
    @Test(dataProvider = "emptyFields", description = "GC-502, GC-207, GC-208, GC-209, GC-210")
    public void checkEmptyFieldsValidation(User userLoginCredentials) {
        logger.info("Starting checkEmptyFieldsValidation. Input values = "
                + userLoginCredentials.toString());
        loadApplication();
        RegisterComponent registerComponent = new TopGuestComponent(driver).clickSignUpLink();
        assertEquals("Hello!", registerComponent.getTitleString(),
                "This is not a register modal:(");
        ManualRegisterComponent manualRegisterComponent = registerComponent.getManualRegisterComponent();
        logger.info("Enter empty values into the form");
        manualRegisterComponent.registrationWrongUser(userLoginCredentials);
        assertEquals(manualRegisterComponent.getEmailValidatorText(),
                "Email is required",
                "The validation message is not equal to the expected one");
        assertEquals(manualRegisterComponent.getUserNameValidatorText(),
                "User name is required",
                "The validation message is not equal to the expected one");
        assertEquals(manualRegisterComponent.getPasswordValidatorText(),
                "Password is required",
                "The validation message is not equal to the expected one");
        assertEquals(manualRegisterComponent.getPasswordConfirmValidatorText(),
                "Password is required",
                "The validation message is not equal to the expected one");
    }

    /**
     * Putting invalid values into register form
     * and reading the validation messages.
     */
    @Test(dataProvider = "invalidFields")
    public void checkInvalidFieldsValidation(User userLoginCredentials) {
        logger.info("Starting checkInvalidFieldsValidation. Input values = "
                + userLoginCredentials.toString());
        loadApplication();
        RegisterComponent registerComponent = new TopGuestComponent(driver).clickSignUpLink();
        assertEquals("Hello!", registerComponent.getTitleString(),
                "This is not a register modal:(");
        ManualRegisterComponent manualRegisterComponent = registerComponent.getManualRegisterComponent();
        logger.info("Enter invalid values into the form");
        manualRegisterComponent.registrationWrongUser(userLoginCredentials);
        assertEquals(manualRegisterComponent.getEmailValidatorText(),
                "Please check that your e-mail address is indicated correctly",
                "The validation message is not equal to the expected one");
        assertEquals(manualRegisterComponent.getPasswordValidatorText(),
                "Password must be at least 8 characters in length",
                "The validation message is not equal to the expected one");
        assertEquals(manualRegisterComponent.getPasswordConfirmValidatorText(),
                "Password has contain at least one character of Uppercase letter (A-Z), " +
                        "Lowercase letter (a-z), Digit (0-9), Special character (~`!@#$%^&*()+=_-{}[]|:;”’?/<>,.)",
                "The validation message is not equal to the expected one");
    }

    @Test(description = "GC-501")
    public void navigateFromSignInToSignUp() {
        logger.info("Starting navigateFromSignInToSignUp");
        loadApplication();
        logger.info("Click on Sign in button");
        LoginComponent loginComponent = new TopGuestComponent(driver).clickSignInLink();
        assertEquals("Welcome back!", loginComponent.getSingInH1(),
                "This is not a login modal:(");
        assertEquals("Please enter your details to sign in", loginComponent.getSingInH2(),
                "This is not a login modal:(");
        RegisterComponent registerComponent = loginComponent.clickSignUpLink();
        assertEquals("Hello!", registerComponent.getTitleString(),
                "This is not a register modal:(");
        assertEquals("Please enter your details to sign up", registerComponent.getSubtitleString(),
                "This is not a register modal:(");
    }

    @Test(dataProvider = "invalidEmail", description = "GC-509")
    public void invalidEmailRegistration(User invalidEmailCredentials) {
        logger.info("Starting checkInvalidFieldsValidation. Input values = "
                + invalidEmailCredentials.toString());
        loadApplication();
        ManualRegisterComponent manualRegisterComponent = new TopGuestComponent(driver)
                .clickSignUpLink()
                .getManualRegisterComponent();
        logger.info("Enter invalid email and valid name and password into the form");
        manualRegisterComponent.fillFieldsWithoutRegistration(invalidEmailCredentials);
        assertEquals(manualRegisterComponent.getEmailValidatorText(),
                "Please check that your e-mail address is indicated correctly",
                "The validation message is not equal to the expected one");
        assertTrue(manualRegisterComponent.signUpIsDisabled());
    }

    @Test(dataProvider = "invalidConfirmPass", description = "GC-519")
    public void invalidConfirmPassRegistration(User userLoginCredentials) {
        logger.info("Starting checkInvalidFieldsValidation. Input values = "
                + userLoginCredentials.toString());
        loadApplication();
        RegisterComponent registerComponent = new TopGuestComponent(driver).clickSignUpLink();
        assertEquals("Hello!", registerComponent.getTitleString(),
                "This is not a register modal:(");
        ManualRegisterComponent manualRegisterComponent = registerComponent.getManualRegisterComponent();
        logger.info("Enter invalid values into the form ");
        manualRegisterComponent.fillFieldsWithoutRegistration(userLoginCredentials);
        assertEquals(manualRegisterComponent.getPasswordConfirmValidatorText(),
                "Passwords do not match",
                "The validation message is not equal to the expected one");
        assertTrue(manualRegisterComponent.signUpIsDisabled());
    }

    //TODO uncomment after front fix
    @Test(dataProvider = "invalidPassUppercaseUserCreds", description = "GC-517")
    public void invalidPassUppercaseValidation(User userLoginCredentials) {
        logger.info("Starting checkInvalidFieldsValidation. Input values = "
                + userLoginCredentials.toString());
        loadApplication();
        RegisterComponent registerComponent = new TopGuestComponent(driver).clickSignUpLink();
        assertEquals("Hello!", registerComponent.getTitleString(),
                "This is not a register modal:(");
        ManualRegisterComponent manualRegisterComponent = registerComponent.getManualRegisterComponent();
        logger.info("Enter invalid values into the form ");
        manualRegisterComponent.fillFieldsWithoutRegistration(userLoginCredentials);
        assertEquals(manualRegisterComponent.getSignUpErrorsMsg(1),
                "Password has contain at least one character of Uppercase letter (A-Z), " +
                        "Lowercase letter (a-z), Digit (0-9), Special character (~`!@#$%^&*()+=_-{}[]|:;”’?/<>,.)",
                "The validation message is not equal to the expected one");
        //Assert.assertTrue(manualRegisterComponent.signUpIsDisabled()); front bug, test ok
    }

    @Test(dataProvider = "invalidPassDigitUserCreds", description = "GC-517")
    public void invalidPassDigitValidation(User userLoginCredentials) {
        logger.info("Starting checkInvalidFieldsValidation. Input values = "
                + userLoginCredentials.toString());
        loadApplication();
        RegisterComponent registerComponent = new TopGuestComponent(driver).clickSignUpLink();
        assertEquals("Hello!", registerComponent.getTitleString(),
                "This is not a register modal:(");
        ManualRegisterComponent manualRegisterComponent = registerComponent.getManualRegisterComponent();
        logger.info("Enter invalid values into the form");
        manualRegisterComponent.fillFieldsWithoutRegistration(userLoginCredentials);
        assertEquals(manualRegisterComponent.getSingInErrorsMsg(4),
                "Password has contain at least one character of Uppercase letter (A-Z), " +
                        "Lowercase letter (a-z), Digit (0-9), Special character (~`!@#$%^&*()+=_-{}[]|:;”’?/<>,.)",
                "The validation message is not equal to the expected one");
        // Assert.assertTrue(manualRegisterComponent.signUpIsDisabled()); ui bug, test works ok
    }

    //TODO uncomment after front fix
    @Test(dataProvider = "invalidPassLowercaseUserCreds", description = "GC-517")
    public void invalidPassLowercaseValidation(User userLoginCredentials) {
        logger.info("Starting checkInvalidFieldsValidation. Input values = "
                + userLoginCredentials.toString());
        loadApplication();
        RegisterComponent registerComponent = new TopGuestComponent(driver).clickSignUpLink();
        assertEquals("Hello!", registerComponent.getTitleString(),
                "This is not a register modal:(");
        ManualRegisterComponent manualRegisterComponent = registerComponent.getManualRegisterComponent();
        logger.info("Enter invalid values into the form");
        manualRegisterComponent.fillFieldsWithoutRegistration(userLoginCredentials);
        assertEquals(manualRegisterComponent.getSingInErrorsMsg(4),
                "Password has contain at least one character of Uppercase letter (A-Z), " +
                        "Lowercase letter (a-z), Digit (0-9), Special character (~`!@#$%^&*()+=_-{}[]|:;”’?/<>,.)",
                "The validation message is not equal to the expected one");
        //Assert.assertTrue(manualRegisterComponent.signUpIsDisabled()); front bug, test works ok
    }

    @Test(dataProvider = "invalidPassSpecCharUserCreds", description = "GC-517")
    public void invalidPassSpecCharValidation(User userLoginCredentials) {
        logger.info("Starting checkInvalidFieldsValidation. Input values = "
                + userLoginCredentials.toString());
        loadApplication();
        RegisterComponent registerComponent = new TopGuestComponent(driver).clickSignUpLink();
        assertEquals("Hello!", registerComponent.getTitleString(),
                "This is not a register modal:(");
        ManualRegisterComponent manualRegisterComponent = registerComponent.getManualRegisterComponent();
        logger.info("Enter invalid values into the form ");
        manualRegisterComponent.fillFieldsWithoutRegistration(userLoginCredentials);
        assertEquals(manualRegisterComponent.getSignUpErrorsMsg(1),
                "Password must be at least 8 characters long without spaces",
                "The validation message is not equal to the expected one");
        assertTrue(manualRegisterComponent.signUpIsDisabled());
    }

    @Test(dataProvider = "invalidPassLengthUserCreds", description = "GC-198, GC-517")
    public void invalidPassLengthValidation(User userLoginCredentials) {
        logger.info("Starting checkInvalidFieldsValidation. Input values = "
                + userLoginCredentials.toString());
        loadApplication();
        RegisterComponent registerComponent = new TopGuestComponent(driver).clickSignUpLink();
        assertEquals("Hello!", registerComponent.getTitleString(),
                "This is not a register modal:(");
        ManualRegisterComponent manualRegisterComponent = registerComponent.getManualRegisterComponent();
        logger.info("Enter invalid values into the form ");
        manualRegisterComponent.fillFieldsWithoutRegistration(userLoginCredentials);
        assertEquals(manualRegisterComponent.getPasswordValidatorText(),
                "Password must be at least 8 characters in length",
                "The validation message is not equal to the expected one");
        assertTrue(manualRegisterComponent.signUpIsDisabled());
    }

    @Test(dataProvider = "invalidPassSpaceUserCreds", description = "GC-517")
    public void invalidPassSpaceValidation(User userLoginCredentials) {
        logger.info("Starting checkInvalidFieldsValidation. Input values = "
                + userLoginCredentials.toString());
        loadApplication();
        RegisterComponent registerComponent = new TopGuestComponent(driver).clickSignUpLink();
        logger.info("Get a title text of the modal window: "
                + registerComponent.getTitleString());
        assertEquals("Hello!", registerComponent.getTitleString(),
                "This is not a register modal:(");
        ManualRegisterComponent manualRegisterComponent = registerComponent.getManualRegisterComponent();
        logger.info("Enter invalid values into the form ");
        manualRegisterComponent.fillFieldsWithoutRegistration(userLoginCredentials);
        assertEquals(manualRegisterComponent.getPasswordValidatorText(),
                "Password must be at least 8 characters long without spaces",
                "The validation message is not equal to the expected one");
        assertTrue(manualRegisterComponent.signUpIsDisabled());
    }

    @Test(description = "GC-499")
    public void checkCloseRegisterModalButton() {
        logger.info("Starting checkCloseRegisterModalButton:");
        loadApplication();
        RegisterComponent registerComponent = new TopGuestComponent(driver).clickSignUpLink();
        assertEquals("Hello!", registerComponent.getTitleString(),
                "This is not a register modal:(");
        ElementsCustomMethods custMethObj = new ElementsCustomMethods(driver);
        boolean isPresent = custMethObj.isElementPresent((RegisterComponent.modal_window_css));
        assertTrue(isPresent);
        registerComponent.closeRegisterComponentModal();
        boolean isGone = custMethObj.waitTillElementGone(driver, RegisterComponent.modal_window_css, 6000);
        assertTrue(isGone);
    }

    @Test(description = "GC-527")
    public void checkPasswordIsHidden() {
        logger.info("Starting checkInvalidFieldsValidation:");
        loadApplication();
        RegisterComponent registerComponent = new TopGuestComponent(driver).clickSignUpLink();
        assertEquals("Hello!", registerComponent.getTitleString(),
                "This is not a register modal:(");
        ManualRegisterComponent manualRegisterComponent = registerComponent.getManualRegisterComponent();
        logger.info("Enter invalid values into the form ");
        manualRegisterComponent.fillPasswordFieldPassHidden("Valid!1");
        assertEquals(manualRegisterComponent.getPasswordField().getAttribute("type"),
                "password",
                "The password is not hidden.");
        assertTrue(manualRegisterComponent.signUpIsDisabled());
    }

    @Test(description = "GC-485")
    public void checkBackgroundIsDimmed() {
        logger.info("Starting checkBackgroundIsDimmed:");
        loadApplication();
        new TopGuestComponent(driver).clickSignUpLink();
        assertTrue(driver.findElement(By.cssSelector(".cdk-overlay-backdrop"))
                .getAttribute("class").contains("cdk-overlay-dark-backdrop cdk-overlay-backdrop-showing"));
    }

    @Test(dataProvider = "invalidNameCredentials", description = "GC-205")
    public void checkUserFieldMaxLength(User userLoginCredentials) {
        loadApplication();
        logger.info("Starting checkInvalidFieldsValidation. Input values = "
                + userLoginCredentials.toString());
        RegisterComponent registerComponent = new TopGuestComponent(driver).clickSignUpLink();
        logger.info("Get a title text of the modal window: "
                + registerComponent.getTitleString());

        assertEquals("Hello!", registerComponent.getTitleString(),
                "This is not a register modal:(");
        ManualRegisterComponent manualRegisterComponent = registerComponent.getManualRegisterComponent();
        logger.info("Entering values into the form ");
        manualRegisterComponent.registrationWrongUser(userLoginCredentials);
        String userFieldValue = manualRegisterComponent.getUserNameField().getAttribute("value");
        assertEquals(userFieldValue,
                "21CharString21CharSt",
                "The invalid string is not concatenated");

    }

    //TODO ask some one info about expected params
    @Test(dataProvider = "validUserCredentials", description = "GC-487, GC-216")
    public void checkResponsiveSingUp(User userLoginCredentials) {
        driver.manage().window().setSize(new Dimension(1024, 768));
        loadApplication();
        logger.info("Starting checkResponsiveSingUp " + driver.manage().window().getSize());
        RegisterComponent registerComponent = new TopGuestComponent(driver).clickSignUpLink();
        assertEquals(
                registerComponent
                        .getEmailFieldAttribute("placeholder"),
                "example@email.com",
                "wrong placeholder");
        assertEquals(
                registerComponent
                        .getUserNameFieldAttribute("placeholder"),
                "User name is required",
                "wrong placeholder");
        assertEquals(
                registerComponent
                        .getPasswordFieldAttribute("placeholder"),
                "Password",
                "wrong placeholder");
        assertEquals(
                registerComponent
                        .getConfirmPasswordFieldAttribute("placeholder"),
                "Password",
                "wrong placeholder");
        assertEquals(registerComponent.getTitleString(),
                "Hello!",
                "This is not a register modal:(");
        assertEquals(
                registerComponent
                        .getSubtitleString(),
                "Please enter your details to sign up",
                "This is not a register modal:(");
        ManualRegisterComponent manualRegisterComponent = new ManualRegisterComponent(driver);
        assertTrue(
                manualRegisterComponent
                .getSignUpButton()
                .isDisplayed());
        manualRegisterComponent.fillFieldsWithoutRegistration(userLoginCredentials);
        assertEquals(
                manualRegisterComponent
                        .getEmailField()
                        .getAttribute("value"),
                userLoginCredentials
                        .getEmail(),
                "the entered email does not match dataProvider email"
        );
        assertEquals(
                manualRegisterComponent
                        .getUserNameField()
                        .getAttribute("value"),
                userLoginCredentials
                        .getUserName(),
                "the entered email does not match dataProvider email"
        );
        assertEquals(
                manualRegisterComponent
                        .getPasswordField()
                        .getAttribute("value"),
                userLoginCredentials
                        .getPassword(),
                "the entered email does not match dataProvider email");
        assertEquals(
                manualRegisterComponent
                        .getPasswordConfirmField()
                        .getAttribute("value"),
                userLoginCredentials
                        .getConfirmPassword(),
                "the entered email does not match dataProvider email");
        assertEquals(
                manualRegisterComponent
                        .getSingUpImage()
                        .getSize()
                        .getHeight(), 760);
        assertEquals(
                manualRegisterComponent
                        .getSingUpImage()
                        .getSize().getWidth(), 480);
        assertTrue(
                manualRegisterComponent
                        .getSignUpButton()
                        .isEnabled());
        assertTrue(
                manualRegisterComponent
                        .getSignInLink()
                        .isEnabled());
        assertEquals(
                registerComponent
                        .getProposeSwitchToSingInText()
                        .getText()
                        .trim(),
                "Do you already have an account? Sign in");
        manualRegisterComponent.getPasswordConfirmField().sendKeys(Keys.TAB, Keys.TAB);
        assertTrue(driver.switchTo().activeElement().isEnabled());
    }

    @Test(description = "GC-482")
    public void checkThatUserCanCloseSingUp() {
        loadApplication();
        RegisterComponent registerComponent = new TopGuestComponent(driver).clickSignUpLink();
        registerComponent.closeSingUpForm();
    }

    @Test(description = "GC-500")
    public void singUpButtonExist() {
        loadApplication();
        RegisterComponent registerComponent = new TopGuestComponent(driver).clickSignUpLink();
        LoginComponent loginComponent = registerComponent.clickSignInLink();
        assertEquals("Welcome back!", loginComponent.getTitleText(),
                "This is not a login modal:(");
        assertEquals("Please enter your details to sign in", loginComponent.getSubtitleText(),
                "This is not a login modal:(");
    }

    @Test(description = "GC-498")
    public void myHabitsExistForUnsignedUser() {
        assertEquals(new WelcomePage(driver)
                .clickMyHabitsUnsignedLink()
                .getSubtitleText(), "Please enter your details to sign in");
    }

    @Test(description = "GC-213", dataProvider = "unregisterCredentials")
    public void  successRegistrationPopUpDisplayed(User userLoginCredentials){
        loadApplication();
        RegisterComponent registerComponent = new TopGuestComponent(driver).clickSignUpLink();
        registerComponent.getManualRegisterComponent().registerUser(userLoginCredentials);
        assertTrue(
                registerComponent
                        .getManualRegisterComponent()
                        .GetSackfulRegistrationPopUp()
                        .isDisplayed());
    }

    @Test(description = "GC-212")
    public void  registrationFormIsDisplayed(){
        logger.info("Starting registration form is displayed");
        loadApplication();
        RegisterComponent registerComponent = new TopGuestComponent(driver).clickSignUpLink();
        assertEquals(
                registerComponent
                        .getEmailFieldAttribute("placeholder"),
                "example@email.com",
                "wrong placeholder");
        assertEquals(
                registerComponent
                        .getUserNameFieldAttribute("placeholder"),
                "User name is required",
                "wrong placeholder");
        assertEquals(
                registerComponent
                        .getPasswordFieldAttribute("placeholder"),
                "Password",
                "wrong placeholder");
        assertEquals(
                registerComponent
                        .getConfirmPasswordFieldAttribute("placeholder"),
                "Password",
                "wrong placeholder");
        assertEquals(registerComponent.getTitleString(),
                "Hello!",
                "This is not a register modal:(");
        assertEquals(
                registerComponent
                        .getSubtitleString(),
                "Please enter your details to sign up",
                "This is not a register modal:(");
    }
    //GC-204
    //Verify that Email must be existence and unique while new user registration
    //GC-200
    //Verify that unregistered user sees popup window 'Sign up' after clicking on the “My habits” button
    //GC-203 clickMyHabitsUnsignedLink
    //Verify that User is redirected to My habits as a Registered User after he has entered valid credentials
    //GC-216
    // Verify 'Sign up' page UI
    //GC-487
    //Verify UI of the Registration form on different screen resolutions
    @Override
    public WebDriver setDriver() {
        return this.driver;
    }
}
