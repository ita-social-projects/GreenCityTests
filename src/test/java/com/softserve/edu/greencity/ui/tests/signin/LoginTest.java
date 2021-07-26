package com.softserve.edu.greencity.ui.tests.signin;

import com.softserve.edu.greencity.data.users.User;
import com.softserve.edu.greencity.data.users.UserRepository;
import com.softserve.edu.greencity.ui.pages.cabinet.LoginComponent;
import com.softserve.edu.greencity.ui.pages.cabinet.ManualLoginComponent;
import com.softserve.edu.greencity.ui.pages.cabinet.MyHabitPage;
import com.softserve.edu.greencity.ui.pages.common.TopGuestComponent;
import com.softserve.edu.greencity.ui.tests.runner.GreenCityTestRunnerWithLoginLogout;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.softserve.edu.greencity.ui.tests.signin.SignInTexts.*;

public class LoginTest extends GreenCityTestRunnerWithLoginLogout {
    String cssBorderColorProperty;
    String expectedBorderColorRBG;

    @BeforeClass
    public void beforeClass() {
        cssBorderColorProperty = "border-color";
        expectedBorderColorRBG = "rgb(240, 49, 39)";
    }

    @Test(testName = "GC-224", description = "GC-224")
    @Description("Verify that user can sign in with valid credentials")
    public void signInWithValidCredentials() {
        logger.info("Starting signInWithValidCredentials");
        User user = UserRepository.get().temporary();
        MyHabitPage myHabitPage = loadApplication()
                .signIn()
                .getManualLoginComponent()
                .successfullyLogin(user);


        String newHabitButton = myHabitPage
                .getAddNewHabitButton()
                .getText();

        myHabitPage.signOut();

        Assert.assertEquals(newHabitButton, ADD_NEW_HABIT_BUTTON_TEXT.getText());
    }

    @Test(testName = "GC-225", description = "GC-225")
    @Description("Verify that user can't sign in with unregistered credentials")
    public void signInWithUnregisteredCredentials() {
        logger.info("Starting signInWithUnregisteredCredentials");
        User user = UserRepository.get().unregisterUser();

        String errorText = loadApplication()
                .signIn()
                .getManualLoginComponent()
                .unsuccessfullyLogin(user)
                .getWrongEmailOrPassErrorText();

        Assert.assertEquals(errorText, WRONG_EMAIL_OR_PASS_ERROR.getText());
    }

    @Test(testName = "GC-30", description = "GC-30")
    @Description("Verify that user can't sign in with invalid password")
    public void signInWithInvalidPassword() {
        logger.info("Starting signInWithInvalidPassword");
        User user = UserRepository.get().userCredentialsWithInvalidPassword();

        String errorText = loadApplication()
                .signIn()
                .getManualLoginComponent()
                .unsuccessfullyLogin(user)
                .getWrongEmailOrPassErrorText();

        Assert.assertEquals(errorText, WRONG_EMAIL_OR_PASS_ERROR.getText());
    }

    @Test(testName = "GC-35", description = "GC-35")
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

        softAssert.assertTrue(topGuestComponent.isDisplayedSignInLink());
        softAssert.assertTrue(topGuestComponent.isDisplayedSignUpLink());
        softAssert.assertAll();
    }

    @Test(testName = "GC-228", description = "GC-228")
    @Description("Verify 'Sign in' form UI")
    public void signInFormValidation() {
        logger.info("Starting signInFormValidation");
        LoginComponent loginComponent = loadApplication().signIn();
        softAssert.assertEquals(loginComponent.getTitleText(), SIGN_IN_TITLE.getText());
        softAssert.assertEquals(loginComponent.getSubtitleText(), SIGN_IN_SUB_TITLE.getText());
        softAssert.assertTrue(loginComponent.getManualLoginComponent().isDisplayedEmailField());
        softAssert.assertTrue(loginComponent.getManualLoginComponent().isDisplayedPasswordField());
        softAssert.assertTrue(loginComponent.getManualLoginComponent().isDisplayedSignInButton());
        softAssert.assertTrue(loginComponent.isDisplayedForgotPasswordLink());
        softAssert.assertTrue(loginComponent.isDisplayedSignUpLink());
        softAssert.assertTrue(loginComponent.isDisplayedSingInWithGoogleButton());

        loginComponent.changeWindowWidth(800);

        softAssert.assertEquals(loginComponent.getTitleText(), SIGN_IN_TITLE.getText());
        softAssert.assertEquals(loginComponent.getSubtitleText(), SIGN_IN_SUB_TITLE.getText());
        softAssert.assertTrue(loginComponent.getManualLoginComponent().isDisplayedEmailField());
        softAssert.assertTrue(loginComponent.getManualLoginComponent().isDisplayedPasswordField());
        softAssert.assertTrue(loginComponent.getManualLoginComponent().isDisplayedSignInButton());
        softAssert.assertTrue(loginComponent.getManualLoginComponent().isDisplayedForgotPasswordLink());
        softAssert.assertTrue(loginComponent.isDisplayedSignUpLink());
        softAssert.assertTrue(loginComponent.isDisplayedSingInWithGoogleButton());

        loginComponent.maximizeWindow();

        softAssert.assertAll();
    }

    @Test(testName = "GC-229", description = "GC-229")
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

        softAssert.assertEquals(emailBorderColor, expectedBorderColorRBG);
        softAssert.assertEquals(passwordBorderColor, expectedBorderColorRBG);
        softAssert.assertTrue(manualLoginComponent.isDisplayedEmailValidationError());
        softAssert.assertTrue(manualLoginComponent.IsPasswordValidationError());
        softAssert.assertFalse(manualLoginComponent.isEnabledSignInButton());
        softAssert.assertAll();
    }

    @Test(testName = "GC-492", description = "GC-492")
    @Description("Verify that 'Sign-up' form appears after click on ‘Sign-up’ link at the bottom of the 'Sign in' form")
    public void signUpLinkValidation() {
        logger.info("Starting signUpLinkValidation");
        String titleString = loadApplication()
                .signIn()
                .clickSignUpLink()
                .getTitleString();

        Assert.assertEquals(titleString, SIGN_UP_TITLE.getText());
    }

    @Test(testName = "GC-497", description = "GC-497")
    @Description("Verify 'Close' button functionality of the 'Sign in' form")
    public void signInFormCloseButtonValidation() {
        logger.info("Starting signInFormCloseButtonValidation");
        boolean isLoginComponentClosed = loadApplication()
                .signIn()
                .isLoginComponentClosed();

        Assert.assertTrue(isLoginComponentClosed);
    }

    @Test(testName = "GC-522", description = "GC-522")
    @Description("Verify that user can't sign in leaving 'Email' field empty")
    public void signInWithEmptyEmailFieldValidation() {
        logger.info("Starting signInWithEmptyEmailFieldValidation");
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

    @Test(testName = "GC-523", description = "GC-523")
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

        softAssert.assertEquals(passwordFieldBorderColor, expectedBorderColorRBG);
        softAssert.assertFalse(manualLoginComponent.isEnabledSignInButton());
        softAssert.assertAll();
    }

    @Test(testName = "GC-524", description = "GC-524")
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

        softAssert.assertEquals(emailBorderColor, expectedBorderColorRBG);
        softAssert.assertEquals(passwordBorderColor, expectedBorderColorRBG);
        softAssert.assertEquals(emailValidationErrorText, SIGN_IN_EMAIL_VALIDATION_ERROR.getText());
        softAssert.assertEquals(passwordValidationErrorText, SIGN_IN_PASSWORD_VALIDATION_ERROR.getText());
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

    @Test(dataProvider = "getCorrectEmails", testName = "GC-525", description = "GC-525")
    @Description("Verify validation of the ‘Email’ field in the ‘Sign in’ form")
    public void correctEmailValidation(String correctEmail) {
        logger.info("Starting correctEmailValidation");
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

    @Test(dataProvider = "getIncorrectEmails", testName = "GC-525", description = "GC-525")
    @Description("Verify validation of the ‘Email’ field in the ‘Sign in’ form")
    public void incorrectEmailValidation(String incorrectEmail) {
        logger.info("Starting incorrectEmailValidation");
        ManualLoginComponent manualLoginComponent = loadApplication()
                .signIn()
                .getManualLoginComponent()
                .inputEmail(incorrectEmail)
                .clickPasswordField();

        String emailFieldBorderColor = manualLoginComponent.getEmailField().getCssValue(cssBorderColorProperty);

        softAssert.assertEquals(emailFieldBorderColor, expectedBorderColorRBG);
        softAssert.assertTrue(manualLoginComponent.isDisplayedEmailValidationError());
        softAssert.assertAll();
    }


    @Test(testName = "GC-525", description = "GC-525")
    @Description("Verify validation of the ‘Email’ field in the ‘Sign in’ form")
    public void clearEmailField() {
        logger.info("Starting clearEmailField");
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

    @Test(testName = "GC-526", description = "GC-526")
    @Description("Verify validation of the ‘Password’ field in the ‘Sign in’ form")
    public void correctPasswordValidation() {
        logger.info("Starting correctPasswordValidation");
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

    @Test(dataProvider = "getIncorrectPasswords", testName = "GC-526", description = "GC-526")
    @Description("Verify validation of the ‘Password’ field in the ‘Sign in’ form")
    public void incorrectPasswordValidation(String incorrectPassword) {
        logger.info("Starting incorrectPasswordValidation");
        ManualLoginComponent manualLoginComponent = loadApplication()
                .signIn()
                .getManualLoginComponent()
                .inputPassword(incorrectPassword)
                .clickEmailField();

        String passwordFieldBorderColor = manualLoginComponent.getPasswordFieldWrapper().getCssValue(cssBorderColorProperty);

        softAssert.assertEquals(passwordFieldBorderColor, expectedBorderColorRBG);
        softAssert.assertEquals(manualLoginComponent.getPasswordValidationErrorText(), SIGN_IN_PASSWORD_VALIDATION_ERROR.getText());
        softAssert.assertAll();
    }

    @Test(testName = "GC-211", description = "GC-211")
    @Description("Verify that modal window with 'Sign in' form appears after unregistered user clicks on the 'Sign in' button")
    public void signInModalValidation() {
        logger.info("Starting signInModalValidation");
        String titleString = loadApplication()
                .signIn().getTitleText();

        Assert.assertEquals(titleString, SIGN_IN_TITLE.getText());
    }
}
