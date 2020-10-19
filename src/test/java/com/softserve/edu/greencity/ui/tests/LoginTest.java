package com.softserve.edu.greencity.ui.tests;

import com.softserve.edu.greencity.ui.tests.runner.GreenCityTestRunner;
import io.qameta.allure.Description;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.softserve.edu.greencity.ui.data.User;
import com.softserve.edu.greencity.ui.data.UserRepository;
import com.softserve.edu.greencity.ui.pages.cabinet.LoginComponent;
import com.softserve.edu.greencity.ui.pages.cabinet.ManualLoginComponent;
import com.softserve.edu.greencity.ui.pages.cabinet.MyCabinetPage;
import com.softserve.edu.greencity.ui.pages.common.TopGuestComponent;
import com.softserve.edu.greencity.ui.pages.common.WelcomePage;

public class LoginTest extends GreenCityTestRunner {
    String cssBorderColorProperty;
    String expectedBorderColorRBG;
    private final String SIGN_UP_TITLE = "Hello!";
    private final String SIGN_IN_TITLE = "Welcome back!";
    private final String SIGN_IN_SUB_TITLE = "Please enter your details to sign in";
    private final String ADD_NEW_HABIT_BUTTON_TEXT = "Add new habit";
    private final String SIGN_IN_EMAIL_VALIDATION_ERROR = "Please check that your e-mail address is indicated correctly";
    private final String SIGN_IN_PASSWORD_VALIDATION_ERROR = "Password must be at least 8 characters long";
    private final String WRONG_EMAIL_OR_PASS_ERROR = "Bad email or password";
    private final String TOP_USER_NAME = "nagrebetski";

    @BeforeClass
    public void beforeClass() {
        cssBorderColorProperty = "border-color";
        expectedBorderColorRBG = "rgb(240, 49, 39)";
    }
@BeforeTest
private SoftAssert assertSoftly(){
    return new  SoftAssert();
}
    @Test(testName = "GC-224")
    @Description("Verify that user can sign in with valid credentials")
    public void signInWithValidCredentials() {
        logger.info("Starting signInWithValidCredentials");
        User user = UserRepository.get().temporary();
        MyCabinetPage myCabinetPage = loadApplication()
                .signIn()
                .getManualLoginComponent()
                .successfullyLogin(user);


        String newHabitButton = myCabinetPage
                .getAddNewHabitButton()
                .getText();

        myCabinetPage.signOut();

        Assert.assertEquals(newHabitButton, ADD_NEW_HABIT_BUTTON_TEXT);
    }

    @Test(testName = "GC-225")
    @Description("Verify that user can't sign in with unregistered credentials")
    public void signInWithUnregisteredCredentials() {
        logger.info("Starting signInWithUnregisteredCredentials");
        User user = UserRepository.get().unregisterUser();

        String errorText = loadApplication()
                .signIn()
                .getManualLoginComponent()
                .unsuccessfullyLogin(user)
                .getWrongEmailOrPassErrorText();

        Assert.assertEquals(errorText, WRONG_EMAIL_OR_PASS_ERROR);
    }

    @Test(testName = "GC-30")
    @Description("Verify that user can't sign in with invalid password")
    public void signInWithInvalidPassword() {
        logger.info("Starting signInWithInvalidPassword");
        User user = UserRepository.get().userCredentialsWithInvalidPassword();

        String errorText = loadApplication()
                .signIn()
                .getManualLoginComponent()
                .unsuccessfullyLogin(user)
                .getWrongEmailOrPassErrorText();

        Assert.assertEquals(errorText, WRONG_EMAIL_OR_PASS_ERROR);
    }

    @Test(testName = "GC-35")
    @Description("Verify that user can sign out")
    public void signOutValidation() {
        logger.info("Starting signOutValidation");
        User user = UserRepository.get().temporary();

        TopGuestComponent topGuestComponent = loadApplication()
                .signIn()
                .getManualLoginComponent()
                .successfullyLogin(user)
                .signOut()
                .createTopGuestComponent();

        assertSoftly().assertTrue(topGuestComponent.isDisplayedSignInLink());
        assertSoftly().assertTrue(topGuestComponent.isDisplayedSignUpLink());
        assertSoftly().assertAll();
    }

    @Test(testName = "GC-228")
    @Description("Verify 'Sign in' form UI")
    public void signInFormValidation() {
        logger.info("Starting signInFormValidation");
        LoginComponent loginComponent = loadApplication().signIn();
        assertSoftly().assertEquals(loginComponent.getTitleText(), SIGN_IN_TITLE);
        assertSoftly().assertEquals(loginComponent.getSubtitleText(), SIGN_IN_SUB_TITLE);
        assertSoftly().assertTrue(loginComponent.getManualLoginComponent().isDisplayedEmailField());
        assertSoftly().assertTrue(loginComponent.getManualLoginComponent().isDisplayedPasswordField());
        assertSoftly().assertTrue(loginComponent.getManualLoginComponent().isDisplayedSignInButton());
        assertSoftly().assertTrue(loginComponent.isDisplayedForgotPasswordLink());
        assertSoftly().assertTrue(loginComponent.isDisplayedSignUpLink());
        assertSoftly().assertTrue(loginComponent.isDisplayedSingInWithGoogleButton());

        loginComponent.changeWindowWidth(800);

        assertSoftly().assertEquals(loginComponent.getTitleText(), SIGN_IN_TITLE);
        assertSoftly().assertEquals(loginComponent.getSubtitleText(), SIGN_IN_SUB_TITLE);
        assertSoftly().assertTrue(loginComponent.getManualLoginComponent().isDisplayedEmailField());
        assertSoftly().assertTrue(loginComponent.getManualLoginComponent().isDisplayedPasswordField());
        assertSoftly().assertTrue(loginComponent.getManualLoginComponent().isDisplayedSignInButton());
        assertSoftly().assertTrue(loginComponent.getManualLoginComponent().isDisplayedForgotPasswordLink());
        assertSoftly().assertTrue(loginComponent.isDisplayedSignUpLink());
        assertSoftly().assertTrue(loginComponent.isDisplayedSingInWithGoogleButton());

        loginComponent.maximizeWindow();

        assertSoftly().assertAll();
    }

    @Test(testName = "GC-229")
    @Description("Verify that user can't sign in leaving required fields empty #616")
    public void singInWithEmptyRequiredFields() {
        logger.info("Starting singInWithEmptyRequiredFields");
        User user = UserRepository.get().emptyUserCredentials();

        ManualLoginComponent manualLoginComponent = loadApplication()
                .signIn()
                .getManualLoginComponent()
                .unsuccessfullyLogin(user);

        manualLoginComponent.getEmailValidationError().click();

        String emailBorderColor = manualLoginComponent.getEmailField().getCssValue(cssBorderColorProperty);
        String passwordBorderColor = manualLoginComponent.getPasswordFieldWrapper().getCssValue(cssBorderColorProperty);

        assertSoftly().assertEquals(emailBorderColor, expectedBorderColorRBG);
        assertSoftly().assertEquals(passwordBorderColor, expectedBorderColorRBG);
        assertSoftly().assertTrue(manualLoginComponent.IsDisplayedEmailValidationError());
        assertSoftly().assertTrue(manualLoginComponent.IsPasswordValidationError());
        assertSoftly().assertFalse(manualLoginComponent.isEnabledSignInButton());
        assertSoftly().assertAll();
    }

    @Test(testName = "GC-492")
    @Description("Verify that 'Sign-up' form appears after click on ‘Sign-up’ link at the bottom of the 'Sign in' form")
    public void signUpLinkValidation() {
        logger.info("Starting signUpLinkValidation");
        String titleString = loadApplication()
                .signIn()
                .clickSignUpLink()
                .getTitleString();

        Assert.assertEquals(titleString, SIGN_UP_TITLE);
    }

    @Test(testName = "GC-497")
    @Description("Verify 'Close' button functionality of the 'Sign in' form")
    public void signInFormCloseButtonValidation() {
        logger.info("Starting signInFormCloseButtonValidation");
        boolean isLoginComponentClosed = loadApplication()
                .signIn()
                .isLoginComponentClosed();

        Assert.assertTrue(isLoginComponentClosed);
    }

    @Test(testName = "GC-522")
    @Description("Verify that user can't sign in leaving 'Email' field empty")
    public void signInWithEmptyEmailFieldValidation() {
        logger.info("Starting signInWithEmptyEmailFieldValidation");
        User user = UserRepository.get().userWithEmptyEmailField();

        ManualLoginComponent manualLoginComponent = loadApplication()
                .signIn()
                .getManualLoginComponent()
                .unsuccessfullyLogin(user);

        String emailFieldBorderColor = manualLoginComponent.getEmailField().getCssValue(cssBorderColorProperty);

        assertSoftly().assertEquals(emailFieldBorderColor, expectedBorderColorRBG);
        assertSoftly().assertFalse(manualLoginComponent.isEnabledSignInButton());
        assertSoftly().assertAll();
    }

    @Test(testName = "GC-523")
    @Description("Verify that user can't sign in leaving 'Password' field empty")
    public void signInWithEmptyPasswordFieldValidation() {
        logger.info("Starting signInWithEmptyPasswordFieldValidation");
        User user = UserRepository.get().userWithEmptyPasswordField();
        ManualLoginComponent manualLoginComponent = loadApplication()
                .signIn()
                .getManualLoginComponent()
                .unsuccessfullyLogin(user);

        manualLoginComponent.clickEmailField();

        String passwordFieldBorderColor = manualLoginComponent.getPasswordFieldWrapper().getCssValue(cssBorderColorProperty);

        assertSoftly().assertEquals(passwordFieldBorderColor, expectedBorderColorRBG);
        assertSoftly().assertFalse(manualLoginComponent.isEnabledSignInButton());
        assertSoftly().assertAll();
    }

    @Test(testName = "GC-524")
    @Description("Verify that user can't sign in with incorrect form of the email and password")
    public void signInWithIncorrectCredentials() {
        logger.info("Starting signInWithIncorrectCredentials");
        User user = UserRepository.get().invalidUserCredentials();

        ManualLoginComponent manualLoginComponent = loadApplication()
                .signIn()
                .getManualLoginComponent()
                .unsuccessfullyLogin(user);

        manualLoginComponent.getEmailValidationError().click();

        String emailBorderColor = manualLoginComponent.getEmailField().getCssValue(cssBorderColorProperty);
        String passwordBorderColor = manualLoginComponent.getPasswordFieldWrapper().getCssValue(cssBorderColorProperty);
        String emailValidationErrorText = manualLoginComponent.getEmailValidationErrorText();
        String passwordValidationErrorText = manualLoginComponent.getPasswordValidationErrorText();

        assertSoftly().assertEquals(emailBorderColor, expectedBorderColorRBG);
        assertSoftly().assertEquals(passwordBorderColor, expectedBorderColorRBG);
        assertSoftly().assertEquals(emailValidationErrorText, SIGN_IN_EMAIL_VALIDATION_ERROR);
        assertSoftly().assertEquals(passwordValidationErrorText, SIGN_IN_PASSWORD_VALIDATION_ERROR);
        assertSoftly().assertAll();
    }

    @DataProvider
    private Object[] getCorrectEmails() {
        return new Object[]{
                "s@s.s",
                "tarantino22@gmail.com",
                "qqqqqqqqqqwwwwwwwwwweeeeeeeeeerrrrrrrrrrttttttttttyyyyyyyyyyuuuu" +
                        "@qqqqqqqqqqwwwwwwwwwweeeeeeeeeerrrrrrrrrrttttttttttyyyyyyyyyyuuu" +
                        ".qqqqqqqqqqwwwwwwwwwweeeeeeeeeerrrrrrrrrrttttttttttyyyyyyyyyyuuu"
        };
    }

    @Test(dataProvider = "getCorrectEmails", testName = "GC-525")
    @Description("Verify validation of the ‘Email’ field in the ‘Sign in’ form")
    public void correctEmailValidation(String correctEmail) {
        logger.info("Starting correctEmailValidation");
        ManualLoginComponent manualLoginComponent = loadApplication()
                .signIn()
                .getManualLoginComponent()
                .inputEmail(correctEmail);

        String emailFieldBorderColor = manualLoginComponent.getEmailField().getCssValue(cssBorderColorProperty);

        assertSoftly().assertEquals(emailFieldBorderColor, "rgb(135, 135, 135)");
        assertSoftly().assertTrue(manualLoginComponent.isSuccessfulEmailValidation());
        assertSoftly().assertAll();
    }

    @DataProvider
    private Object[] getIncorrectEmails() {
        return new Object[]{
                "tarantino22gmail.com",
                "qqqqqqqqqqwwwwwwwwwweeeeeeeeeerrrrrrrrrrttttttttttyyyyyyyyyyuuuuu" +
                        "@qqqqqqqqqqwwwwwwwwwweeeeeeeeeerrrrrrrrrrttttttttttyyyyyyyyyyuuuu" +
                        ".qqqqqqqqqqwwwwwwwwwweeeeeeeeeerrrrrrrrrrttttttttttyyyyyyyyyyuuuu",
                " @ . "
        };
    }

    @Test(dataProvider = "getIncorrectEmails", testName = "GC-525")
    @Description("Verify validation of the ‘Email’ field in the ‘Sign in’ form")
    public void incorrectEmailValidation(String incorrectEmail) {
        logger.info("Starting incorrectEmailValidation");
        ManualLoginComponent manualLoginComponent = loadApplication()
                .signIn()
                .getManualLoginComponent()
                .inputEmail(incorrectEmail);

        String emailFieldBorderColor = manualLoginComponent.getEmailField().getCssValue(cssBorderColorProperty);

        assertSoftly().assertEquals(emailFieldBorderColor, expectedBorderColorRBG);
        assertSoftly().assertTrue(manualLoginComponent.isUnsuccessfulEmailValidation());
        assertSoftly().assertAll();
    }


    @Test(testName = "GC-525")
    @Description("Verify validation of the ‘Email’ field in the ‘Sign in’ form")
    public void clearEmailField() {
        logger.info("Starting clearEmailField");
        ManualLoginComponent manualLoginComponent = loadApplication()
                .signIn()
                .getManualLoginComponent()
                .inputEmail("incorrectEmail");

        manualLoginComponent.getEmailField().clear();
        String emailFieldBorderColor = manualLoginComponent.getEmailField().getCssValue(cssBorderColorProperty);

    assertSoftly().assertEquals(emailFieldBorderColor, expectedBorderColorRBG);
    assertSoftly().assertTrue(manualLoginComponent.isUnsuccessfulEmailValidation());
    assertSoftly().assertAll();
    }

    @Test(testName = "GC-526")
    @Description("Verify validation of the ‘Password’ field in the ‘Sign in’ form")
    public void correctPasswordValidation() {
        logger.info("Starting correctPasswordValidation");
        String correctPassword = UserRepository.get().temporary().getPassword();

        ManualLoginComponent manualLoginComponent = loadApplication()
                .signIn()
                .getManualLoginComponent()
                .inputPassword(correctPassword);

        String passwordFieldBorderColor = manualLoginComponent.getPasswordFieldWrapper().getCssValue(cssBorderColorProperty);
        assertSoftly().assertEquals(passwordFieldBorderColor, "rgb(135, 135, 135)");
        assertSoftly().assertTrue(manualLoginComponent.isSuccessfulPasswordValidation());
        assertSoftly().assertAll();
    }

    @DataProvider
    private Object[] getIncorrectPasswords() {
        return new Object[]{
                "Gc#5",
                "G ",
        };
    }

    @Test(dataProvider = "getIncorrectPasswords", testName = "GC-526")
    @Description("Verify validation of the ‘Password’ field in the ‘Sign in’ form")
    public void incorrectPasswordValidation(String incorrectPassword) {
        logger.info("Starting incorrectPasswordValidation");
        ManualLoginComponent manualLoginComponent = loadApplication()
                .signIn()
                .getManualLoginComponent()
                .inputPassword(incorrectPassword);

        String passwordFieldBorderColor = manualLoginComponent.getPasswordFieldWrapper().getCssValue(cssBorderColorProperty);

        assertSoftly().assertEquals(passwordFieldBorderColor, expectedBorderColorRBG);
        assertSoftly().assertEquals(manualLoginComponent.getPasswordValidationErrorText(), SIGN_IN_PASSWORD_VALIDATION_ERROR);
        assertSoftly().assertAll();
    }

    @Test(testName = "GC-211")
    @Description("Verify that modal window with 'Sign in' form appears after unregistered user clicks on the 'Sign in' button")
    public void signInModalValidation() {
        logger.info("Starting signInModalValidation");
        String titleString = loadApplication()
                .signIn().getTitleText();

        Assert.assertEquals(titleString, SIGN_IN_TITLE);
    }

    @Test(testName = "GC-218")
    @Description("Verify that Unregistered user can Sign Up with Google account")
    public void signUpByGoogle() {
        logger.info("Starting signUpByGoogle");
        User user = UserRepository.get().googleUserCredentials();

        WelcomePage welcomePage = loadApplication();
        welcomePage
                .signUp()
                .clickGoogleSignUpButton()
                .successfulLoginByGoogle(user);
        String topUserName = welcomePage.getTopUserName();

        welcomePage.signOut().googleAccountSignOut();

        Assert.assertEquals(topUserName, TOP_USER_NAME);
    }

    @Test(testName = "GC-220")
    @Description("Verify that Unregistered user can Sign In with Google account")
    public void signInByGoogle() {
        logger.info("Starting signInByGoogle");
        User user = UserRepository.get().googleUserCredentials();

        WelcomePage welcomePage = loadApplication();
        welcomePage
                .signIn()
                .clickSingInWithGoogleButton()
                .successfulLoginByGoogle(user);

        String topUserName = welcomePage.getTopUserName();

        welcomePage.signOut().googleAccountSignOut();

        Assert.assertEquals(topUserName, TOP_USER_NAME);
    }

    @Test(testName = "GC-234")
    @Description("Verify that user can't sign in with Google Account credentials on 'Sign in' pop-up window")
    public void signInByGoogleCredentialsOnManualSignInPopUp() {
        logger.info("Starting signInByGoogleCredentialsOnManualSignInPopUp");
        User user = UserRepository.get().googleUserCredentials();
        WelcomePage welcomePage = loadApplication()
                .signUp()
                .clickGoogleSignUpButton()
                .successfulLoginByGoogle(user);
        assertSoftly().assertEquals(welcomePage.getTopUserName(), TOP_USER_NAME);

        welcomePage.signOut().googleAccountSignOut();
        WebElement wrongEmailOrPasswordError = loadApplication()
                .signIn()
                .getManualLoginComponent()
                .unsuccessfullyLogin(user)
                .getWrongEmailOrPassError();
        assertSoftly().assertTrue(wrongEmailOrPasswordError.isDisplayed());
        assertSoftly().assertAll();
    }
}
