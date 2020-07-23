package com.softserve.edu.greencity.ui.tests.login;

import com.softserve.edu.greencity.ui.data.User;
import com.softserve.edu.greencity.ui.data.UserRepository;
import com.softserve.edu.greencity.ui.pages.cabinet.ManualLoginComponent;
import com.softserve.edu.greencity.ui.tests.GreenCityTestRunner;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class LoginNegativeTests extends GreenCityTestRunner {
    String cssBorderColorProperty;
    String expectedBorderColorRBG;

    @BeforeClass
    public void beforeClass() {
        cssBorderColorProperty = "border-color";
        expectedBorderColorRBG = "rgb(240, 49, 39)";
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
}
