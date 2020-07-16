package com.softserve.edu.greencity.ui.tests;

import com.softserve.edu.greencity.ui.data.User;
import com.softserve.edu.greencity.ui.data.UserRepository;
import com.softserve.edu.greencity.ui.pages.cabinet.GoogleLoginPage;
import com.softserve.edu.greencity.ui.pages.cabinet.LoginComponent;
import com.softserve.edu.greencity.ui.pages.cabinet.ManualLoginComponent;
import com.softserve.edu.greencity.ui.pages.cabinet.MyCabinetPage;
import com.softserve.edu.greencity.ui.pages.common.TopGuestComponent;
import com.softserve.edu.greencity.ui.pages.tipstricks.TipsTricksPage;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class LoginTest extends GreenCityTestRunner {
    String cssBorderColorProperty;
    String expectedBorderColorRBG;

    @BeforeClass
    public void beforeClass() {
        cssBorderColorProperty = "border-color";
        expectedBorderColorRBG = "rgb(240, 49, 39)";
    }

    @Test(testName = "GC-224")
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

        Assert.assertEquals(newHabitButton, "Add new habit");
    }

    @Test(testName = "GC-225")
    public void signInWithUnregisteredCredentials() {
        User user = UserRepository.get().unregisterUser();

        String errorText = loadApplication()
                .signIn()
                .getManualLoginComponent()
                .unsuccessfullyLogin(user)
                .getWrongEmailOrPassErrorText();

        Assert.assertEquals(errorText, "Bad email or password");
    }

    @Test(testName = "GC-30")
    public void signInWithInvalidPassword() {
        User user = UserRepository.get().userCredentialsWithInvalidPassword();

        String errorText = loadApplication()
                .signIn()
                .getManualLoginComponent()
                .unsuccessfullyLogin(user)
                .getWrongEmailOrPassError()
                .getText();

        Assert.assertEquals(errorText, "Bad email or password");
    }

    @Test(testName = "GC-35")
    public void signOutValidation() {
        SoftAssert softAssert = new SoftAssert();

        User user = UserRepository.get().temporary();

        TopGuestComponent topGuestComponent = loadApplication()
                .signIn()
                .getManualLoginComponent()
                .successfullyLogin(user)
                .signOut()
                .createTopGuestComponent();

        boolean signInDisplayed = topGuestComponent.getSigninLink().isDisplayed();
        boolean signUpDisplayed = topGuestComponent.getSignupLink().isDisplayed();

        softAssert.assertTrue(signInDisplayed);
        softAssert.assertTrue(signUpDisplayed);
        softAssert.assertAll();
    }

    @Test(testName = "GC-228")
    public void signInFormValidation() {
        SoftAssert softAssert = new SoftAssert();

        LoginComponent loginComponent = loadApplication().signIn();

        softAssert.assertEquals(loginComponent.getTitleString(), "Welcome back!");
        softAssert.assertEquals(loginComponent.getSubtitleString(), "Please enter your details to sign in");
        softAssert.assertTrue(loginComponent.getManualLoginComponent().getEmailField().isDisplayed());
        softAssert.assertTrue(loginComponent.getManualLoginComponent().getPasswordField().isDisplayed());
        softAssert.assertTrue(loginComponent.getManualLoginComponent().getSignInButton().isDisplayed());
        softAssert.assertTrue(loginComponent.getManualLoginComponent().getForgotPasswordLink().isDisplayed());
        softAssert.assertTrue(loginComponent.getSignUpLink().isDisplayed());
        softAssert.assertTrue(loginComponent.getSingInWithGoogleButton().isDisplayed());

        loginComponent.changeWindowWidth(800);

        softAssert.assertEquals(loginComponent.getTitleString(), "Welcome back!");
        softAssert.assertEquals(loginComponent.getSubtitleString(), "Please enter your details to sign in");
        softAssert.assertTrue(loginComponent.getManualLoginComponent().getEmailField().isDisplayed());
        softAssert.assertTrue(loginComponent.getManualLoginComponent().getPasswordField().isDisplayed());
        softAssert.assertTrue(loginComponent.getManualLoginComponent().getSignInButton().isDisplayed());
        softAssert.assertTrue(loginComponent.getManualLoginComponent().getForgotPasswordLink().isDisplayed());
        softAssert.assertTrue(loginComponent.getSignUpLink().isDisplayed());
        softAssert.assertTrue(loginComponent.getSingInWithGoogleButton().isDisplayed());

        loginComponent.maximizeWindow();

        softAssert.assertAll();
    }

    @Test(testName = "GC-229")
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
        softAssert.assertTrue(manualLoginComponent.getEmailValidationError().isDisplayed());
        softAssert.assertTrue(manualLoginComponent.getPasswordValidationError().isDisplayed());
        softAssert.assertFalse(manualLoginComponent.getSignInButton().isEnabled());
        softAssert.assertAll();
    }

    @Test(testName = "GC-492")
    public void signUpLinkValidation() {
        String titleString = loadApplication()
                .signIn()
                .clickSignUpLink()
                .getTitleString();

        Assert.assertEquals(titleString, "Hello!");
    }

    @Test(testName = "GC-497")
    public void signInFormCloseButtonValidation() {
        boolean isLoginComponentClosed = loadApplication()
                .signIn()
                .isLoginComponentClosed();

        Assert.assertTrue(isLoginComponentClosed);
    }

    @Test(testName = "GC-522")
    public void signInWithEmptyEmailFieldValidation() {
        SoftAssert softAssert = new SoftAssert();

        User user = UserRepository.get().userWithEmptyEmailField();

        ManualLoginComponent manualLoginComponent = loadApplication()
                .signIn()
                .getManualLoginComponent()
                .unsuccessfullyLogin(user);
        String emailFieldBorderColor = manualLoginComponent.getEmailField().getCssValue(cssBorderColorProperty);

        softAssert.assertEquals(emailFieldBorderColor, expectedBorderColorRBG);
        softAssert.assertFalse(manualLoginComponent.getSignInButton().isEnabled());
    }

    @Test(testName = "GC-523")
    public void signInWithEmptyPasswordFieldValidation() {
        SoftAssert softAssert = new SoftAssert();

        User user = UserRepository.get().userWithEmptyPasswordField();
        ManualLoginComponent manualLoginComponent = loadApplication()
                .signIn()
                .getManualLoginComponent()
                .unsuccessfullyLogin(user);

        manualLoginComponent.getEmailField().click();

        String passwordFieldBorderColor = manualLoginComponent.getPasswordFieldWrapper().getCssValue(cssBorderColorProperty);

        softAssert.assertEquals(passwordFieldBorderColor, expectedBorderColorRBG);
        softAssert.assertFalse(manualLoginComponent.getSignInButton().isEnabled());
    }

    @Test(testName = "GC-524")
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
        String emailValidationErrorText = manualLoginComponent.getEmailValidationError().getText();
        String passwordValidationErrorText = manualLoginComponent.getPasswordValidationError().getText();

        softAssert.assertEquals(emailBorderColor, expectedBorderColorRBG);
        softAssert.assertEquals(passwordBorderColor, expectedBorderColorRBG);
        softAssert.assertEquals(emailValidationErrorText, "Please check that your e-mail address is indicated correctly");
        softAssert.assertEquals(passwordValidationErrorText, "Password must be at least 8 characters long");
        softAssert.assertAll();
    }

    @Test(dataProvider = "getCorrectEmails", testName = "GC-525")
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
    private Object[] getCorrectEmails() {
        return new Object[]{
                "s@s.s",
                "tarantino22@gmail.com",
                "qqqqqqqqqqwwwwwwwwwweeeeeeeeeerrrrrrrrrrttttttttttyyyyyyyyyyuuuu" +
                        "@qqqqqqqqqqwwwwwwwwwweeeeeeeeeerrrrrrrrrrttttttttttyyyyyyyyyyuuu" +
                        ".qqqqqqqqqqwwwwwwwwwweeeeeeeeeerrrrrrrrrrttttttttttyyyyyyyyyyuuu"
        };
    }

    @Test(dataProvider = "getIncorrectEmails", testName = "GC-525")
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

    @Test(testName = "GC-525")
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

    @Test(dataProvider = "getIncorrectPasswords", testName = "GC-526")
    public void incorrectPasswordValidation(String incorrectPassword) {
        SoftAssert softAssert = new SoftAssert();

        ManualLoginComponent manualLoginComponent = loadApplication()
                .signIn()
                .getManualLoginComponent()
                .inputPassword(incorrectPassword);

        String passwordFieldBorderColor = manualLoginComponent.getPasswordFieldWrapper().getCssValue(cssBorderColorProperty);

        softAssert.assertEquals(passwordFieldBorderColor, expectedBorderColorRBG);
        softAssert.assertEquals(manualLoginComponent.getPasswordValidationError().getText(), "Password must be at least 8 characters long");

        softAssert.assertAll();
    }

    @DataProvider
    private Object[] getIncorrectPasswords() {
        return new Object[]{
                "Gc#5",
                "G ",
        };
    }

    @Test(testName = "GC-211")
    public void signInModalValidation() {
        String titleString = loadApplication()
                .signIn().getTitleString();

        Assert.assertEquals(titleString, "Welcome back!");
    }

    @Test(testName = "GC-218")
    public void signUpByGoogle() {
        User user = UserRepository.get().googleUserCredentials();

        TipsTricksPage tipsTricksPage = loadApplication();
        GoogleLoginPage googleLoginPage = tipsTricksPage
                .signUp()
                .clickGoogleSignUpButton();

        googleLoginPage
                .successfulLoginByGoogle(user);

        String topUserName = tipsTricksPage.getTopUserName();

        tipsTricksPage.signOut();


        Assert.assertEquals(topUserName, "Taras Malynovskyi");

        googleLoginPage.clearCookies();
    }

    @Test(testName = "GC-220")
    public void signInByGoogle() {
        User user = UserRepository.get().googleUserCredentials();

        TipsTricksPage tipsTricksPage = loadApplication();

        GoogleLoginPage googleLoginPage = tipsTricksPage
                .signIn()
                .clickGoogleSignInButton();

        googleLoginPage
                .successfulLoginByGoogle(user);

        String topUserName = tipsTricksPage.getTopUserName();

        tipsTricksPage.signOut();

        Assert.assertEquals(topUserName, "Taras Malynovskyi");

        googleLoginPage.clearCookies();
    }

    @Test(testName = "GC-234")
    public void signInByGoogleCredentialsOnManualSignInPopUp() {
        User user = UserRepository.get().googleUserCredentials();
        SoftAssert softAssert = new SoftAssert();
        TipsTricksPage tipsTricksPage = loadApplication();

        GoogleLoginPage googleLoginPage = tipsTricksPage
                .signUp()
                .clickGoogleSignUpButton();

        googleLoginPage
                .successfulLoginByGoogle(user);

        softAssert.assertEquals(tipsTricksPage.getTopUserName(), "Taras Malynovskyi");

        tipsTricksPage.signOut();

        WebElement wrongEmailOrPasswordError = loadApplication()
                .signIn()
                .getManualLoginComponent()
                .unsuccessfullyLogin(user)
                .getWrongEmailOrPassError();

        softAssert.assertTrue(wrongEmailOrPasswordError.isDisplayed());
        softAssert.assertAll();

        googleLoginPage.clearCookies();
    }
}
