package com.softserve.edu.greencity.ui.tests.signup;

import com.softserve.edu.greencity.data.users.User;
import com.softserve.edu.greencity.data.users.UserRepository;
import com.softserve.edu.greencity.ui.pages.cabinet.LoginComponent;
import com.softserve.edu.greencity.ui.pages.cabinet.ManualRegisterComponent;
import com.softserve.edu.greencity.ui.pages.cabinet.RegisterComponent;
import com.softserve.edu.greencity.ui.pages.common.TopGuestComponent;
import com.softserve.edu.greencity.ui.pages.common.WelcomePage;
import com.softserve.edu.greencity.ui.tests.runner.GreenCityTestRunner;
import com.softserve.edu.greencity.ui.tools.testng.RetryAnalyzerImpl;
import com.softserve.edu.greencity.ui.tools.engine.StableWebElementSearch;
import io.qameta.allure.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class RegisterPageTests extends GreenCityTestRunner implements StableWebElementSearch {

    @DataProvider
    public Object[][] validUserCredentials() {
        return new Object[][]{
                {UserRepository.get().defaultUserCredentials()},};
    }

    @DataProvider
    public Object[][] unregisterCredentials() {
        return new Object[][]{
                {UserRepository.get().userCredentialsForRegistration()},};
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
    public Object[][] invalidPassword(){
        return new Object[][]{
                {UserRepository.get().invalidPassUppercaseUserCreds()},
                {UserRepository.get().invalidPassDigitUserCreds()},
                {UserRepository.get().invalidPassLowercaseUserCreds()},
                {UserRepository.get().invalidPassSpecCharUserCreds()}, //TODO Clarify requirements! Now spaces are allowed, and test fails
                {UserRepository.get().invalidPassLengthUserCreds()},
                {UserRepository.get().invalidPassSpaceUserCreds()},
        };
    }


    @DataProvider
    public Object[][] invalidPassLengthUserCreds() {
        return new Object[][]{
                {UserRepository.get().invalidPassLengthUserCreds()},};
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
        softAssert.assertEquals("Hello!", registerComponent.getTitleString(),
                "This is not a register modal:(");
        ManualRegisterComponent manualRegisterComponent = new ManualRegisterComponent(driver);
        softAssert.assertFalse(manualRegisterComponent.isSignUpSubmitButtonEnabled());
        logger.info(
                "Filling out the fields with valid credentials without clicking on Sign up button");
        manualRegisterComponent.fillFieldsWithoutRegistration(userLoginCredentials);
        softAssert.assertTrue(manualRegisterComponent.isSignUpSubmitButtonEnabled());
        softAssert.assertAll();
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
        softAssert.assertEquals("Hello!", registerComponent.getTitleString(),
                "This is not a register modal:(");
        softAssert.assertEquals("Please enter your details to sign up", registerComponent.getSubtitleString(),
                "This is not a register modal:(");
        logger.info("Click on Sign in button");
        LoginComponent loginComponent = registerComponent.clickSignInLink();
        softAssert.assertEquals("Welcome back!", loginComponent.getTitleText(),
                "This is not a login modal:(");
        softAssert.assertEquals("Please enter your details to sign in", loginComponent.getSubtitleText(),
                "This is not a login modal:(");
        softAssert.assertAll();
    }

    /**
     * Putting empty values into register form
     * and reading the validation messages.
     */
    @Test(dataProvider = "emptyFields", testName = "GC-502, GC-207, GC-208, GC-209, GC-210", description = "GC-502, GC-207, GC-208, GC-209, GC-210")
    @Description("Verifying empty field validation messages")
    public void checkEmptyFieldsValidation(User userLoginCredentials) {
        logger.info("Starting checkEmptyFieldsValidation. Input values = "
                + userLoginCredentials.toString());
        loadApplication();
        RegisterComponent registerComponent = new TopGuestComponent(driver).clickSignUpLink();
        softAssert.assertEquals("Hello!", registerComponent.getTitleString(),
                "This is not a register modal:(");
        ManualRegisterComponent manualRegisterComponent = registerComponent.getManualRegisterComponent();
        logger.info("Enter empty values into the form");
        manualRegisterComponent.registrationWrongUser(userLoginCredentials);
        softAssert.assertEquals(manualRegisterComponent.getEmailValidatorText(),
                "Email is required", // test fails, bug
                "The validation message is not equal to the expected one");
        softAssert.assertEquals(manualRegisterComponent.getUserNameValidatorText(),
                "User name is required",
                "The validation message is not equal to the expected one");
        softAssert.assertEquals(manualRegisterComponent.getPasswordValidatorText(),
                "Password is required", // test fails, bug
                "The validation message is not equal to the expected one");
        softAssert.assertAll();
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
        softAssert.assertEquals("Hello!", registerComponent.getTitleString(),
                "This is not a register modal:(");
        ManualRegisterComponent manualRegisterComponent = registerComponent.getManualRegisterComponent();
        logger.info("Enter invalid values into the form");
        manualRegisterComponent.registrationWrongUser(userLoginCredentials);
        softAssert.assertEquals(manualRegisterComponent.getEmailValidatorText(),
                "Please check that your e-mail address is indicated correctly",
                "The validation message is not equal to the expected one");
        softAssert.assertEquals(manualRegisterComponent.getPasswordValidatorText(),
                "Password must be at least 8 characters in length",
                "The validation message is not equal to the expected one");
        softAssert.assertAll();
    }

    @Test(testName = "GC-501", description = "GC-501")
    @Description("Verify that pop up 'Sign up' opens after click 'Sign up' link on the ‘Sign in’ pop up.")
    public void navigateFromSignInToSignUp() {
        logger.info("Starting navigateFromSignInToSignUp");
        loadApplication();
        logger.info("Click on Sign in button");
        LoginComponent loginComponent = new TopGuestComponent(driver).clickSignInLink();
        softAssert.assertEquals("Welcome back!", loginComponent.getSingInH1(),
                "This is not a login modal:(");
        softAssert.assertEquals("Please enter your details to sign in", loginComponent.getSingInH2(),
                "This is not a login modal:(");
        RegisterComponent registerComponent = loginComponent.clickSignUpLink();
        softAssert.assertEquals("Hello!", registerComponent.getTitleString(),
                "This is not a register modal:(");
        softAssert.assertEquals("Please enter your details to sign up", registerComponent.getSubtitleString(),
                "This is not a register modal:(");
        softAssert.assertAll();
    }

    @Test(dataProvider = "invalidEmail", testName = "GC-509", description = "GC-509")
    @Description("Verify that user is not registered, when he enters invalid e-mail format in ‘Email’ field.")
    public void invalidEmailRegistration(User invalidEmailCredentials) {
        logger.info("Starting checkInvalidFieldsValidation. Input values = "
                + invalidEmailCredentials.toString());
        loadApplication();
        ManualRegisterComponent manualRegisterComponent = new TopGuestComponent(driver)
                .clickSignUpLink()
                .getManualRegisterComponent();
        logger.info("Enter invalid email and valid name and password into the form");
        manualRegisterComponent.fillFieldsWithoutRegistration(invalidEmailCredentials);
        softAssert.assertEquals(manualRegisterComponent.getEmailValidatorText(),
                "Please check that your e-mail address is indicated correctly",
                "The validation message is not equal to the expected one");
        softAssert.assertFalse(manualRegisterComponent.isSignUpSubmitButtonEnabled());
        softAssert.assertAll();
    }

    @Test(dataProvider = "invalidConfirmPass", testName = "GC-519", description = "GC-519")
    @Description ("Verify that user is not registered when 'Confirm password' does not match to 'Password'")
    public void invalidConfirmPassRegistration(User userLoginCredentials) {
        logger.info("Starting checkInvalidFieldsValidation. Input values = "
                + userLoginCredentials.toString());
        loadApplication();
        RegisterComponent registerComponent = new TopGuestComponent(driver).clickSignUpLink();
        softAssert.assertEquals("Hello!", registerComponent.getTitleString(),
                "This is not a register modal:(");
        ManualRegisterComponent manualRegisterComponent = registerComponent.getManualRegisterComponent();
        logger.info("Enter invalid values into the form ");
        manualRegisterComponent.fillFieldsWithoutRegistration(userLoginCredentials);
        softAssert.assertEquals(manualRegisterComponent.getPasswordConfirmValidatorText(),
                "Passwords do not match",
                "The validation message is not equal to the expected one");
        softAssert.assertFalse(manualRegisterComponent.isSignUpSubmitButtonEnabled());
        softAssert.assertAll();
    }


    @Test(dataProvider = "invalidPassword", testName = "GC-517, GC-516", description = "GC-517, GC-516")
    @Description("Verify that user is not registered with password, that does not contain all required characters.")
    public void invalidPassDigitValidation(User userLoginCredentials) {
        logger.info("Starting checkInvalidFieldsValidation. Input values = "
                + userLoginCredentials.toString());
        ManualRegisterComponent manualRegisterComponent = loadApplication()
                .signUp()
                .getManualRegisterComponent()
                .fillFieldsWithoutRegistration(userLoginCredentials);
         Assert.assertFalse(manualRegisterComponent.isSignUpSubmitButtonEnabled(),"Is SignUp Button enabled ?");
    }


    @Test(dataProvider = "invalidPassLengthUserCreds", testName = "GC-198", description = "Verify that user can't register using invalid pass length")
    public void invalidPassLengthValidation(User userLoginCredentials) {
        logger.info("Starting checkInvalidFieldsValidation. Input values = "
                + userLoginCredentials.toString());
        ManualRegisterComponent manualRegisterComponent = loadApplication()
                .signUp()
                .getManualRegisterComponent()
                .fillFieldsWithoutRegistration(userLoginCredentials);
        softAssert.assertFalse(manualRegisterComponent.isSignUpSubmitButtonEnabled(),"Is SignUp Button enabled ?");
        softAssert.assertAll();
    }


    @Test(testName = "GC-499", description = "GC-499")
    @Description ("Verify that pop up ‘Sign up’ form closes after unregistered user click on X at the top-right corner of a window")
    public void checkCloseRegisterModalButton() {
        logger.info("Starting checkCloseRegisterModalButton:");
       RegisterComponent registerComponent = loadApplication().signUp();
        softAssert.assertEquals("Hello!", registerComponent.getTitleString(),
                "This is not a register modal:(");
        softAssert.assertTrue(registerComponent.isSignUpComponentOpen());
        registerComponent.closeRegisterComponentModal();
       softAssert.assertTrue(registerComponent.isSignUpComponentClosed());
        softAssert.assertAll();
    }

    @Test(testName = "GC-527", description = "GC-527")
    @Description ("Verify that symbols entered in 'Password' and 'Confirm password' fields on 'Sign up' pop up transforms into dots.")
    public void checkPasswordIsHidden() {
        logger.info("Starting checkInvalidFieldsValidation:");
        loadApplication();
        RegisterComponent registerComponent = new TopGuestComponent(driver).clickSignUpLink();
        softAssert.assertEquals("Hello!", registerComponent.getTitleString(),
                "This is not a register modal:(");
        ManualRegisterComponent manualRegisterComponent = registerComponent.getManualRegisterComponent();
        logger.info("Enter invalid values into the form ");
        manualRegisterComponent.fillPasswordFieldPassHidden("Valid!1");
        softAssert.assertEquals(manualRegisterComponent.getPasswordField().getAttribute("type"),
                "password",
                "The password is not hidden.");
        softAssert.assertFalse(manualRegisterComponent.isSignUpSubmitButtonEnabled());
        softAssert.assertAll();
    }

    @Test(testName = "GC-485", description = "GC-485")
    @Description("Verify that background behind the Registration form is dimmed out")
    public void checkBackgroundIsDimmed() {
        logger.info("Starting checkBackgroundIsDimmed:");
        loadApplication();
        new TopGuestComponent(driver).clickSignUpLink();
        Assert.assertTrue(driver.findElement(By.cssSelector(".cdk-overlay-backdrop"))
                .getAttribute("class").contains("cdk-overlay-dark-backdrop cdk-overlay-backdrop-showing"));
    }

    @Test(dataProvider = "invalidNameCredentials", testName = "GC-205", description = "GC-205")
    @Description("Verify that user is not registered with too long 'User name'")
    public void checkUserFieldMaxLength(User userLoginCredentials) {
        loadApplication();
        logger.info("Starting checkInvalidFieldsValidation. Input values = "
                + userLoginCredentials.toString());
        RegisterComponent registerComponent = new TopGuestComponent(driver).clickSignUpLink();
        logger.info("Get a title text of the modal window: "
                + registerComponent.getTitleString());

        softAssert.assertEquals("Hello!", registerComponent.getTitleString(),
                "This is not a register modal:(");
        ManualRegisterComponent manualRegisterComponent = registerComponent.getManualRegisterComponent();
        logger.info("Entering values into the form ");
        manualRegisterComponent.registrationWrongUser(userLoginCredentials);
        String userFieldValue = manualRegisterComponent.getUserNameField().getAttribute("value");
        softAssert.assertEquals(userFieldValue,
                "21CharString21CharSt",
                "The invalid string is not concatenated");
        softAssert.assertAll();
    }

    //TODO ask some one info about expected params and refactor
    @Test(dataProvider = "validUserCredentials", testName = "GC-487, GC-216", description = "GC-487, GC-216")
    @Description("Verify UI on Sign-up page")
    public void checkResponsiveSingUp(User userLoginCredentials) {
        driver.manage().window().setSize(new Dimension(1024, 768));
        loadApplication();
        logger.info("Starting checkResponsiveSingUp " + driver.manage().window().getSize());
        RegisterComponent registerComponent = new TopGuestComponent(driver).clickSignUpLink();
        softAssert.assertEquals(
                registerComponent
                        .getEmailFieldAttribute("placeholder"),
                "example@email.com",
                "wrong placeholder");
        softAssert.assertEquals(
                registerComponent
                        .getUserNameFieldAttribute("placeholder"),
                "User name",
                "wrong placeholder");
        softAssert.assertEquals(
                registerComponent
                        .getPasswordFieldAttribute("placeholder"),
                "Password",
                "wrong placeholder");
        softAssert.assertEquals(
                registerComponent
                        .getConfirmPasswordFieldAttribute("placeholder"),
                "Password",
                "wrong placeholder");
        softAssert.assertEquals(registerComponent.getTitleString(),
                "Hello!",
                "This is not a register modal:(");
        softAssert.assertEquals(
                registerComponent
                        .getSubtitleString(),
                "Please enter your details to sign up",
                "This is not a register modal:(");
        ManualRegisterComponent manualRegisterComponent = new ManualRegisterComponent(driver);
        softAssert.assertTrue(
                manualRegisterComponent
                        .getSignUpButton()
                        .isDisplayed());
        manualRegisterComponent.fillFieldsWithoutRegistration(userLoginCredentials);
        softAssert.assertEquals(
                manualRegisterComponent
                        .getEmailField()
                        .getAttribute("value"),
                userLoginCredentials
                        .getEmail(),
                "the entered email does not match dataProvider email"
        );
        softAssert.assertEquals(
                manualRegisterComponent
                        .getUserNameField()
                        .getAttribute("value"),
                userLoginCredentials
                        .getUserName(),
                "the entered email does not match dataProvider email"
        );
        softAssert.assertEquals(
                manualRegisterComponent
                        .getPasswordField()
                        .getAttribute("value"),
                userLoginCredentials
                        .getPassword(),
                "the entered email does not match dataProvider email");
        softAssert.assertEquals(
                manualRegisterComponent
                        .getPasswordConfirmField()
                        .getAttribute("value"),
                userLoginCredentials
                        .getConfirmPassword(),
                "the entered email does not match dataProvider email");
        softAssert.assertEquals(
                manualRegisterComponent
                        .getSingUpImage()
                        .getSize()
                        .getHeight(), 760);
        softAssert.assertEquals(
                manualRegisterComponent
                        .getSingUpImage()
                        .getSize().getWidth(), 480);
        softAssert.assertTrue(
                manualRegisterComponent
                        .getSignUpButton()
                        .isEnabled());
        softAssert.assertTrue(
                manualRegisterComponent
                        .getSignInLink()
                        .isEnabled());
        softAssert.assertEquals(
                registerComponent
                        .getProposeSwitchToSingInText()
                        .getText()
                        .trim(),
                "Do you already have an account? Sign in");
        manualRegisterComponent.getPasswordConfirmField().sendKeys(Keys.TAB, Keys.TAB);
        softAssert.assertTrue(driver.switchTo().activeElement().isEnabled());
        softAssert.assertAll();
    }

    @Test(testName = "GC-482", description = "GC-482")
    @Description("Verify that User can close Registration form by pressing \"x\" in top-right corner of the modal window")
    public void checkThatUserCanCloseSingUp() {
        boolean isSignUpComponentClosed =
                loadApplication()
                .signUp()
                .closeRegisterComponentModal()
                .isSignUpComponentClosed();
        Assert.assertTrue(isSignUpComponentClosed);
    }

    @Test(testName = "GC-500", description = "GC-500")
    @Description("Verify that user sees “Sign up” link on the popup ‘Sign in’ form.")
    public void singUpButtonExist() {
        loadApplication();
        RegisterComponent registerComponent = new TopGuestComponent(driver).clickSignUpLink();
        LoginComponent loginComponent = registerComponent.clickSignInLink();
        softAssert.assertEquals("Welcome back!", loginComponent.getTitleText(),
                "This is not a login modal:(");
        softAssert.assertEquals("Please enter your details to sign in", loginComponent.getSubtitleText(),
                "This is not a login modal:(");
        softAssert.assertAll();
    }

    @Test(testName = "GC-498", description = "GC-498")
    @Description("Verify that, Unregistered user sees My cabinet button, after accesses to the main page of the application")
    public void myHabitsExistForUnsignedUser() {
        Assert.assertEquals(new WelcomePage(driver)
                .clickMyHabitsUnsignedLink()
                .getSubtitleText(), "Please enter your details to sign in");
    }

    @Test(testName = "GC-213", description = "GC-213", dataProvider = "unregisterCredentials", retryAnalyzer = RetryAnalyzerImpl.class)
    @Description("Verify that user sees popup about successfully registration after registration on the site")
    public void successRegistrationPopUpDisplayed(User userLoginCredentials) {
       Boolean isDisplayed = loadApplication()
               .signUp()
               .getManualRegisterComponent()
                .registerUser(userLoginCredentials)
               .getSackfulRegistrationPopUp()
               .isDisplayed();

        Assert.assertTrue(isDisplayed);
    }

    @Test(testName = "GC-212", description = "GC-212")
    @Description("Verify that unregistered user sees popup with registration form after clicking on the 'Sign up' button")
    public void registrationFormIsDisplayed() {
        logger.info("Starting registration form is displayed");
        loadApplication();
        RegisterComponent registerComponent = new TopGuestComponent(driver).clickSignUpLink();
        softAssert.assertEquals(
                registerComponent
                        .getEmailFieldAttribute("placeholder"),
                "example@email.com",
                "wrong placeholder");
        softAssert.assertEquals(
                registerComponent
                        .getUserNameFieldAttribute("placeholder"),
                "User name",
                "wrong placeholder");
        softAssert.assertEquals(
                registerComponent
                        .getPasswordFieldAttribute("placeholder"),
                "Password",
                "wrong placeholder");
        softAssert.assertEquals(
                registerComponent
                        .getConfirmPasswordFieldAttribute("placeholder"),
                "Password",
                "wrong placeholder");
        softAssert.assertEquals(registerComponent.getTitleString(),
                "Hello!",
                "This is not a register modal:(");
        softAssert.assertEquals(
                registerComponent
                        .getSubtitleString(),
                "Please enter your details to sign up",
                "This is not a register modal:(");
        softAssert.assertAll();
    }

    @Override
    public WebDriver setDriver() {
        return this.driver;
    }
}
