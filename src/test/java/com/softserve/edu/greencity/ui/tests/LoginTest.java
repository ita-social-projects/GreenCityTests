package com.softserve.edu.greencity.ui.tests;

import com.softserve.edu.greencity.ui.data.User;
import com.softserve.edu.greencity.ui.data.UserRepository;
import com.softserve.edu.greencity.ui.pages.cabinet.LoginComponent;
import com.softserve.edu.greencity.ui.pages.cabinet.ManualLoginComponent;
import com.softserve.edu.greencity.ui.pages.cabinet.ManualRegisterComponent;
import com.softserve.edu.greencity.ui.pages.common.TopGuestComponent;
import com.softserve.edu.greencity.ui.pages.tipstricks.TipsTricksPage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class LoginTest extends GreenCityTestRunner {
    SoftAssert softAssert;
    String cssBorderColorProperty;
    String expectedBorderColorRBG;

    @BeforeClass
    public void beforeClass() {
        softAssert = new SoftAssert();
        cssBorderColorProperty = "border-color";
        expectedBorderColorRBG = "rgb(240, 49, 39)";
    }

    @Test(testName = "GC-224")
    public void signInWithValidCredentials() {
        User user = UserRepository.get().temporary();

        String newHabitButton = loadApplication()
                .signin()
                .getManualLoginComponent()
                .successfullyLogin(user)
                .getAddNewHabitButton()
                .getText();

        Assert.assertEquals(newHabitButton, "Add new habit");
    }

    @Test(testName = "GC-225")
    public void signInWithUnregisteredCredentials() {
        User user = UserRepository.get().invalidUserCredentials();

        String errorText = loadApplication()
                .signin()
                .getManualLoginComponent()
                .unsuccessfullyLogin(user)
                .getWrongEmailOrPassError()
                .getText();

        Assert.assertEquals(errorText, "Bad email or password");
    }

    @Test(testName = "GC-30")
    public void signInWithInvalidPassword() {
        User user = UserRepository.get().userCredentialsWithInvalidPassword();

        String errorText = loadApplication()
                .signin()
                .getManualLoginComponent()
                .unsuccessfullyLogin(user)
                .getWrongEmailOrPassError()
                .getText();

        Assert.assertEquals(errorText, "Bad email or password");
    }

    @Test(testName = "GC-35")
    public void signOutValidation() {
        User user = UserRepository.get().temporary();

        TopGuestComponent topGuestComponent = loadApplication()
                .signin()
                .getManualLoginComponent()
                .successfullyLogin(user)
                .signout()
                .createTopGuestComponent();

        boolean signInDisplayed = topGuestComponent.getSigninLink().isDisplayed();
        boolean signUpDisplayed = topGuestComponent.getSignupLink().isDisplayed();

        softAssert.assertTrue(signInDisplayed);
        softAssert.assertTrue(signUpDisplayed);
        softAssert.assertAll();
    }

    @Test(testName = "GC-228")
    public void signInFormValidation() {
        LoginComponent loginComponent = loadApplication().signin();

        softAssert.assertEquals(loginComponent.getTitleString(), "Welcome back!");
        softAssert.assertEquals(loginComponent.getSubtitleString(), "Please, enter your details to sing in");
        softAssert.assertTrue(loginComponent.getManualLoginComponent().getEmailField().isDisplayed());
        softAssert.assertTrue(loginComponent.getManualLoginComponent().getPasswordField().isDisplayed());
        softAssert.assertTrue(loginComponent.getManualLoginComponent().getSignInButton().isDisplayed());
        softAssert.assertTrue(loginComponent.getManualLoginComponent().getForgotPasswordLink().isDisplayed());
        softAssert.assertTrue(loginComponent.getSingUpLink().isDisplayed());
        softAssert.assertTrue(loginComponent.getSingInWithGoogleButton().isDisplayed());

        //Not finished all assertions. need to discus with Liubomyr

        softAssert.assertAll();
    }

    @Test(testName = "GC-229")
    public void singInWithEmptyRequiredFields() {
        User user = UserRepository.get().emptyUserCredentials();
        ManualLoginComponent manualLoginComponent = loadApplication()
                .signin()
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
        ManualRegisterComponent manualRegisterComponent = loadApplication()
                .signin()
                .clickSignUpLink();

        Assert.assertTrue(manualRegisterComponent.getSignUpButton().isDisplayed());
    }

    @Test(testName = "GC-497")
    public void signInFormCloseButtonValidation() {
        TipsTricksPage tipsTricksPage = loadApplication()
                .signin()
                .closeLoginComponent();

        Assert.assertTrue(tipsTricksPage.getStartHabitTop().isDisplayed());
    }

    @Test(testName = "GC-522")
    public void signInWithEmptyEmailFieldValidation() {
        User user = UserRepository.get().userWithEmptyEmailField();

        ManualLoginComponent manualLoginComponent = loadApplication()
                .signin()
                .getManualLoginComponent()
                .unsuccessfullyLogin(user);
        String emailFieldBorderColor = manualLoginComponent.getEmailField().getCssValue(cssBorderColorProperty);

        softAssert.assertEquals(emailFieldBorderColor, expectedBorderColorRBG);
        softAssert.assertFalse(manualLoginComponent.getSignInButton().isEnabled());
    }

    @Test(testName = "GC-523")
    public void signInWithEmptyPasswordFieldValidation() {
        User user = UserRepository.get().userWithEmptyPasswordField();
        ManualLoginComponent manualLoginComponent = loadApplication()
                .signin()
                .getManualLoginComponent()
                .unsuccessfullyLogin(user);

        manualLoginComponent.getEmailField().click();

        String passwordFieldBorderColor = manualLoginComponent.getPasswordFieldWrapper().getCssValue(cssBorderColorProperty);

        softAssert.assertEquals(passwordFieldBorderColor, expectedBorderColorRBG);
        softAssert.assertFalse(manualLoginComponent.getSignInButton().isEnabled());
    }

    @Test(testName = "GC-524")
    public void signInWithIncorrectCredentials() {
        User user = UserRepository.get().invalidUserCredentials();
        ManualLoginComponent manualLoginComponent = loadApplication()
                .signin()
                .getManualLoginComponent()
                .unsuccessfullyLogin(user);

        manualLoginComponent.getEmailValidationError().click();

        String emailBorderColor = manualLoginComponent.getEmailField().getCssValue(cssBorderColorProperty);
        String passwordBorderColor = manualLoginComponent.getPasswordFieldWrapper().getCssValue(cssBorderColorProperty);
        String emailValidationErrorText = manualLoginComponent.getEmailValidationError().getText();
        String passwordValidationErrorText = manualLoginComponent.getPasswordValidationError().getText();

        softAssert.assertEquals(emailBorderColor, expectedBorderColorRBG);
        softAssert.assertEquals(passwordBorderColor, expectedBorderColorRBG);
        softAssert.assertEquals(emailValidationErrorText, "This is not email");
        softAssert.assertEquals(passwordValidationErrorText, "Password must be at least 8 characters long");
        softAssert.assertAll();
    }

    @Test(dataProvider = "getCorrectEmails", testName = "GC-525")
    public void correctEmailValidation(String correctEmail) {
        ManualLoginComponent manualLoginComponent = loadApplication()
                .signin()
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
        ManualLoginComponent manualLoginComponent = loadApplication()
                .signin()
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

    @Test(testName = "GC-526")
    public void correctPasswordValidation() {
        String correctPassword = UserRepository.get().temporary().getPassword();

        ManualLoginComponent manualLoginComponent = loadApplication()
                .signin()
                .getManualLoginComponent()
                .inputPassword(correctPassword);

        String passwordFieldBorderColor = manualLoginComponent.getPasswordFieldWrapper().getCssValue(cssBorderColorProperty);
        softAssert.assertEquals(passwordFieldBorderColor, "rgb(135, 135, 135)");
        softAssert.assertTrue(manualLoginComponent.isSuccessfulPasswordValidation());
        softAssert.assertAll();
    }

    @Test(dataProvider = "getIncorrectPasswords", testName = "GC-526")
    public void incorrectPasswordValidation(String incorrectPassword) {
        ManualLoginComponent manualLoginComponent = loadApplication()
                .signin()
                .getManualLoginComponent()
                .inputPassword(incorrectPassword);

        String passwordFieldBorderColor = manualLoginComponent.getPasswordFieldWrapper().getCssValue(cssBorderColorProperty);

        softAssert.assertEquals(passwordFieldBorderColor, expectedBorderColorRBG);
        softAssert.assertEquals(manualLoginComponent.getPasswordValidationError().getText(),
                "Password has to contain at least one character of Uppercase letter (A-Z), " +
                        "Lowercase letter (a-z), Digit (0-9), " +
                        "Special character (~`!@#$%^&*()+=_-{}[]|:;”’?/<>,.)");

        softAssert.assertAll();
    }

    @DataProvider
    private Object[] getIncorrectPasswords() {
        return new Object[]{
                "Gc#5",
                "green.city#500",
                "GREEN.CITY#500",
                "Green.city#",
                "Greencity500"
        };
    }
}
