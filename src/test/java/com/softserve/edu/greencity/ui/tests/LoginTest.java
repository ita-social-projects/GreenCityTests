package com.softserve.edu.greencity.ui.tests;

import io.qameta.allure.Description;
import lombok.SneakyThrows;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteExecuteMethod;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.html5.RemoteWebStorage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
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

    @Test(testName = "GC-224")
    @Description("Verify that user can sign in with valid credentials")
    public void signInWithValidCredentials() {
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
        SoftAssert softAssert = new SoftAssert();

        User user = UserRepository.get().temporary();

        TopGuestComponent topGuestComponent = loadApplication()
                .signIn()
                .getManualLoginComponent()
                .successfullyLogin(user)
                .signOut()
                .createTopGuestComponent();

        softAssert.assertTrue(topGuestComponent.isDisplayedSignInLink());
        softAssert.assertTrue(topGuestComponent.isDisplayedSignUpLink());
        softAssert.assertAll();
    }

    @Test(testName = "GC-228")
    @Description("Verify 'Sign in' form UI")
    public void signInFormValidation() {
        SoftAssert softAssert = new SoftAssert();

        LoginComponent loginComponent = loadApplication().signIn();
        softAssert.assertEquals(loginComponent.getTitleText(), SIGN_IN_TITLE);
        softAssert.assertEquals(loginComponent.getSubtitleText(), SIGN_IN_SUB_TITLE);
        softAssert.assertTrue(loginComponent.getManualLoginComponent().isDisplayedEmailField());
        softAssert.assertTrue(loginComponent.getManualLoginComponent().isDisplayedPasswordField());
        softAssert.assertTrue(loginComponent.getManualLoginComponent().isDisplayedSignInButton());
        softAssert.assertTrue(loginComponent.isDisplayedForgotPasswordLink());
        softAssert.assertTrue(loginComponent.isDisplayedSignUpLink());
        softAssert.assertTrue(loginComponent.isDisplayedSingInWithGoogleButton());

        loginComponent.changeWindowWidth(800);

        softAssert.assertEquals(loginComponent.getTitleText(), SIGN_IN_TITLE);
        softAssert.assertEquals(loginComponent.getSubtitleText(), SIGN_IN_SUB_TITLE);
        softAssert.assertTrue(loginComponent.getManualLoginComponent().isDisplayedEmailField());
        softAssert.assertTrue(loginComponent.getManualLoginComponent().isDisplayedPasswordField());
        softAssert.assertTrue(loginComponent.getManualLoginComponent().isDisplayedSignInButton());
        softAssert.assertTrue(loginComponent.getManualLoginComponent().isDisplayedForgotPasswordLink());
        softAssert.assertTrue(loginComponent.isDisplayedSignUpLink());
        softAssert.assertTrue(loginComponent.isDisplayedSingInWithGoogleButton());

        loginComponent.maximizeWindow();

        softAssert.assertAll();
    }

    @Test(testName = "GC-229")
    @Description("Verify that user can't sign in leaving required fields empty #616")
    public void singInWithEmptyRequiredFields() {
        SoftAssert softAssert = new SoftAssert();

        User user = UserRepository.get().emptyUserCredentials();

        ManualLoginComponent manualLoginComponent = loadApplication()
                .signIn()
                .getManualLoginComponent()
                .unsuccessfullyLogin(user);

        manualLoginComponent.getEmailValidationError().click();

        String emailBorderColor = manualLoginComponent.getEmailField().getCssValue(cssBorderColorProperty);
        String passwordBorderColor = manualLoginComponent.getPasswordFieldWrapper().getCssValue(cssBorderColorProperty);

        softAssert.assertEquals(emailBorderColor, expectedBorderColorRBG);
        softAssert.assertEquals(passwordBorderColor, expectedBorderColorRBG);
        softAssert.assertTrue(manualLoginComponent.IsDisplayedEmailValidationError());
        softAssert.assertTrue(manualLoginComponent.IsPasswordValidationError());
        softAssert.assertFalse(manualLoginComponent.isEnabledSignInButton());
        softAssert.assertAll();
    }

    @Test(testName = "GC-492")
    @Description("Verify that 'Sign-up' form appears after click on ‘Sign-up’ link at the bottom of the 'Sign in' form")
    public void signUpLinkValidation() {
        String titleString = loadApplication()
                .signIn()
                .clickSignUpLink()
                .getTitleString();

        Assert.assertEquals(titleString, SIGN_UP_TITLE);
    }

    @Test(testName = "GC-497")
    @Description("Verify 'Close' button functionality of the 'Sign in' form")
    public void signInFormCloseButtonValidation() {
        boolean isLoginComponentClosed = loadApplication()
                .signIn()
                .isLoginComponentClosed();

        Assert.assertTrue(isLoginComponentClosed);
    }

    @Test(testName = "GC-522")
    @Description("Verify that user can't sign in leaving 'Email' field empty")
    public void signInWithEmptyEmailFieldValidation() {
        SoftAssert softAssert = new SoftAssert();

        User user = UserRepository.get().userWithEmptyEmailField();

        ManualLoginComponent manualLoginComponent = loadApplication()
                .signIn()
                .getManualLoginComponent()
                .unsuccessfullyLogin(user);

        String emailFieldBorderColor = manualLoginComponent.getEmailField().getCssValue(cssBorderColorProperty);

        softAssert.assertEquals(emailFieldBorderColor, expectedBorderColorRBG);
        softAssert.assertFalse(manualLoginComponent.isEnabledSignInButton());
        softAssert.assertAll();
    }

    @Test(testName = "GC-523")
    @Description("Verify that user can't sign in leaving 'Password' field empty")
    public void signInWithEmptyPasswordFieldValidation() {
        SoftAssert softAssert = new SoftAssert();

        User user = UserRepository.get().userWithEmptyPasswordField();
        ManualLoginComponent manualLoginComponent = loadApplication()
                .signIn()
                .getManualLoginComponent()
                .unsuccessfullyLogin(user);

        manualLoginComponent.clickEmailField();

        String passwordFieldBorderColor = manualLoginComponent.getPasswordFieldWrapper().getCssValue(cssBorderColorProperty);

        softAssert.assertEquals(passwordFieldBorderColor, expectedBorderColorRBG);
        softAssert.assertFalse(manualLoginComponent.isEnabledSignInButton());
        softAssert.assertAll();
    }

    @Test(testName = "GC-524")
    @Description("Verify that user can't sign in with incorrect form of the email and password")
    public void signInWithIncorrectCredentials() {
        SoftAssert softAssert = new SoftAssert();

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

        softAssert.assertEquals(emailBorderColor, expectedBorderColorRBG);
        softAssert.assertEquals(passwordBorderColor, expectedBorderColorRBG);
        softAssert.assertEquals(emailValidationErrorText, SIGN_IN_EMAIL_VALIDATION_ERROR);
        softAssert.assertEquals(passwordValidationErrorText, SIGN_IN_PASSWORD_VALIDATION_ERROR);
        softAssert.assertAll();
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
        SoftAssert softAssert = new SoftAssert();

        ManualLoginComponent manualLoginComponent = loadApplication()
                .signIn()
                .getManualLoginComponent()
                .inputEmail(correctEmail);

        String emailFieldBorderColor = manualLoginComponent.getEmailField().getCssValue(cssBorderColorProperty);

        softAssert.assertEquals(emailFieldBorderColor, "rgb(135, 135, 135)");
        softAssert.assertTrue(manualLoginComponent.isSuccessfulEmailValidation());
        softAssert.assertAll();
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
        SoftAssert softAssert = new SoftAssert();

        ManualLoginComponent manualLoginComponent = loadApplication()
                .signIn()
                .getManualLoginComponent()
                .inputEmail(incorrectEmail);

        String emailFieldBorderColor = manualLoginComponent.getEmailField().getCssValue(cssBorderColorProperty);

        softAssert.assertEquals(emailFieldBorderColor, expectedBorderColorRBG);
        softAssert.assertTrue(manualLoginComponent.isUnsuccessfulEmailValidation());
        softAssert.assertAll();
    }


    @Test(testName = "GC-525")
    @Description("Verify validation of the ‘Email’ field in the ‘Sign in’ form")
    public void clearEmailField() {
        SoftAssert softAssert = new SoftAssert();

        ManualLoginComponent manualLoginComponent = loadApplication()
                .signIn()
                .getManualLoginComponent()
                .inputEmail("incorrectEmail");

        manualLoginComponent.getEmailField().clear();
        String emailFieldBorderColor = manualLoginComponent.getEmailField().getCssValue(cssBorderColorProperty);

        softAssert.assertEquals(emailFieldBorderColor, expectedBorderColorRBG);
        softAssert.assertTrue(manualLoginComponent.isUnsuccessfulEmailValidation());
        softAssert.assertAll();
    }

    @Test(testName = "GC-526")
    @Description("Verify validation of the ‘Password’ field in the ‘Sign in’ form")
    public void correctPasswordValidation() {
        SoftAssert softAssert = new SoftAssert();

        String correctPassword = UserRepository.get().temporary().getPassword();

        ManualLoginComponent manualLoginComponent = loadApplication()
                .signIn()
                .getManualLoginComponent()
                .inputPassword(correctPassword);

        String passwordFieldBorderColor = manualLoginComponent.getPasswordFieldWrapper().getCssValue(cssBorderColorProperty);
        softAssert.assertEquals(passwordFieldBorderColor, "rgb(135, 135, 135)");
        softAssert.assertTrue(manualLoginComponent.isSuccessfulPasswordValidation());
        softAssert.assertAll();
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
        SoftAssert softAssert = new SoftAssert();

        ManualLoginComponent manualLoginComponent = loadApplication()
                .signIn()
                .getManualLoginComponent()
                .inputPassword(incorrectPassword);

        String passwordFieldBorderColor = manualLoginComponent.getPasswordFieldWrapper().getCssValue(cssBorderColorProperty);

        softAssert.assertEquals(passwordFieldBorderColor, expectedBorderColorRBG);
        softAssert.assertEquals(manualLoginComponent.getPasswordValidationErrorText(), SIGN_IN_PASSWORD_VALIDATION_ERROR);
        softAssert.assertAll();
    }

    @Test(testName = "GC-211")
    @Description("Verify that modal window with 'Sign in' form appears after unregistered user clicks on the 'Sign in' button")
    public void signInModalValidation() {
        String titleString = loadApplication()
                .signIn().getTitleText();

        Assert.assertEquals(titleString, SIGN_IN_TITLE);
    }
    //TODO auth in google via api before clickSingInWithGoogleButton()
    @Test(testName = "GC-218")
    @Description("Verify that Unregistered user can Sign Up with Google account")
    public void signUpByGoogle() {
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
//TODO auth in google via api before clickSingInWithGoogleButton()
    @Test(testName = "GC-220")
    @Description("Verify that Unregistered user can Sign In with Google account")
    public void signInByGoogle() {
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

    //TODO auth in google via api before clickSingInWithGoogleButton()
    @Test(testName = "GC-234")
    @Description("Verify that user can't sign in with Google Account credentials on 'Sign in' pop-up window")
    public void signInByGoogleCredentialsOnManualSignInPopUp() {
        User user = UserRepository.get().googleUserCredentials();
        SoftAssert softAssert = new SoftAssert();

        WelcomePage welcomePage = loadApplication()
                .signUp()
                .clickGoogleSignUpButton()
                .successfulLoginByGoogle(user);
        softAssert.assertEquals(welcomePage.getTopUserName(), TOP_USER_NAME);

        welcomePage.signOut().googleAccountSignOut();
        WebElement wrongEmailOrPasswordError = loadApplication()
                .signIn()
                .getManualLoginComponent()
                .unsuccessfullyLogin(user)
                .getWrongEmailOrPassError();
        softAssert.assertTrue(wrongEmailOrPasswordError.isDisplayed());
        softAssert.assertAll();
    }
}
