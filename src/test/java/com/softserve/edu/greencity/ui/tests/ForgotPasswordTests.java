package com.softserve.edu.greencity.ui.tests;

import com.softserve.edu.greencity.ui.pages.common.ForgotPasswordComponent;
import com.softserve.edu.greencity.ui.tests.runner.GreenCityTestRunner;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

public class ForgotPasswordTests extends GreenCityTestRunner {
    String cssBorderColorProperty;
    String expectedBorderColorRBG;
    private final String SIGN_IN_TITLE = "Welcome back!";
    private final String FORGOT_PASS_TITLE = "Problems sign in?";
    private final String FORGOT_PASS_SUB_TITLE = "Enter your email address and we'll send you a link to regain access to your account.";
    private final String EMAIL_PLACEHOLDER_TEXT = "example@email.com";
    private final String FORGOT_PASS_EMAIL_VALIDATION_ERROR = "Please check that your e-mail address is indicated correctly";
    private final String EMPTY_EMAIL_ERROR_MESSAGE = "Email is required";
    private final String BAD_EMAIL_ERROR_MESSAGE = "Bad email or password:";
    private final String BACK_TO_SIGN_IN_LABEL = "Remember your password? Back to Sign-in";
    private final String RESTORE_EMAIL_ERROR_MESSAGE ="Password restore link already sent, please check your email:";

    @BeforeClass
    public void beforeClass() {
        cssBorderColorProperty = "border-color";
        expectedBorderColorRBG = "rgb(240, 49, 39)";
    }
    @BeforeTest
    private SoftAssert assertSoftly(){
        return new  SoftAssert();
    }


    @Test(testName = "GC-503")
    @Description("Verify that user can sign in with valid credentials")
    public void isForgotPasswordPopup() {

        String forgotPasswordTitle = loadApplication()
                .signIn()
                .clickForgotPasswordLink()
                .getForgotTitleText();

        Assert.assertEquals(forgotPasswordTitle, FORGOT_PASS_TITLE);
    }

    @Test(testName = "GC-511")
    @Description("Verify that 'Email' field is highlighted and error-message is shown after user leaves 'Email' field empty")
    public void forgotPasswordWithEmptyEmailFieldValidation() {

        String emailErrorMessage = loadApplication()
                .signIn()
                .clickForgotPasswordLink()
                .pressTabEmail()
                .getEmailValidationErrorText();

        Assert.assertEquals(emailErrorMessage, EMPTY_EMAIL_ERROR_MESSAGE);
    }

    @DataProvider
    private Object[] getIncorrectEmails() {
        return new Object[]{
                "qwertygmail.com",
                "qwerty"
        };
    }

    @Test(dataProvider = "getIncorrectEmails", testName = "GC-505")
    @Description("Verify that 'Email' field is highlighted and error-message is shown after user enters not valid e-mail address")
    public void forgotPasswordWithInCorrectEmailFieldValidation(String incorrectEmail) {
        ForgotPasswordComponent forgotPasswordComponent = loadApplication()
                .signIn()
                .clickForgotPasswordLink()
                .inputEmail(incorrectEmail);

        String emailFieldBorderColor = forgotPasswordComponent.getEmailField().getCssValue(cssBorderColorProperty);

        assertSoftly().assertEquals(emailFieldBorderColor, expectedBorderColorRBG);
        assertSoftly().assertEquals(forgotPasswordComponent.getEmailValidationErrorText(), FORGOT_PASS_EMAIL_VALIDATION_ERROR);
        assertSoftly().assertAll();
    }

    @Test( testName = "GC-515")
    @Description("Verify that User is directed back to Sign In page after he clicks on 'Back to Sign in' link")
    public void directedBackToSignInFromForgot() {

        String signInTitle = loadApplication()
                .signIn()
                .clickForgotPasswordLink()
                .clickBackLink()
                .getTitleText();

        Assert.assertEquals(signInTitle, SIGN_IN_TITLE);
    }
    @Test( testName = "GC-518")
    @Description("Verify UI of the 'Forgot Password' popup according to the mock up")
    public void forgotPassFormValidation() {

        ForgotPasswordComponent forgotPasswordComponent = loadApplication()
                .signIn()
                .clickForgotPasswordLink();

        assertSoftly().assertEquals(forgotPasswordComponent.getForgotTitleText(), FORGOT_PASS_TITLE);
        assertSoftly().assertEquals(forgotPasswordComponent.getSubTitleText(), FORGOT_PASS_SUB_TITLE);
        assertSoftly().assertTrue(forgotPasswordComponent.isDisplayedEmailField());
        assertSoftly().assertEquals(forgotPasswordComponent.getEmailPlaceholderText(), EMAIL_PLACEHOLDER_TEXT);
        assertSoftly().assertTrue(forgotPasswordComponent.isDisplayedSubmitButton());
        assertSoftly().assertTrue(forgotPasswordComponent.isDisplayedGoogleSignInButton());
        assertSoftly().assertTrue(forgotPasswordComponent.isDisplayedBackLink());
        assertSoftly().assertEquals(forgotPasswordComponent.getBackLinkLabelText(),BACK_TO_SIGN_IN_LABEL);
        assertSoftly().assertTrue(forgotPasswordComponent.isDisplayedCloseFormButton());

        forgotPasswordComponent.changeWindowWidth(800);

        assertSoftly().assertEquals(forgotPasswordComponent.getForgotTitleText(), FORGOT_PASS_TITLE);
        assertSoftly().assertEquals(forgotPasswordComponent.getSubTitleText(), FORGOT_PASS_SUB_TITLE);
        assertSoftly().assertTrue(forgotPasswordComponent.isDisplayedEmailField());
        assertSoftly().assertEquals(forgotPasswordComponent.getEmailPlaceholderText(), EMAIL_PLACEHOLDER_TEXT);
        assertSoftly().assertTrue(forgotPasswordComponent.isDisplayedSubmitButton());
        assertSoftly().assertTrue(forgotPasswordComponent.isDisplayedGoogleSignInButton());
        assertSoftly().assertTrue(forgotPasswordComponent.isDisplayedBackLink());
        assertSoftly().assertEquals(forgotPasswordComponent.getBackLinkLabelText(),BACK_TO_SIGN_IN_LABEL);
        assertSoftly().assertTrue(forgotPasswordComponent.isDisplayedCloseFormButton());

        forgotPasswordComponent.maximizeWindow();

        assertSoftly().assertAll();

    }

}


