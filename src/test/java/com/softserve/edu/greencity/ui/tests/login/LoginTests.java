package com.softserve.edu.greencity.ui.tests.login;

import com.softserve.edu.greencity.ui.data.User;
import com.softserve.edu.greencity.ui.data.UserRepository;
import com.softserve.edu.greencity.ui.pages.cabinet.LoginComponent;
import com.softserve.edu.greencity.ui.pages.cabinet.ManualLoginComponent;
import com.softserve.edu.greencity.ui.pages.cabinet.MyCabinetPage;
import com.softserve.edu.greencity.ui.pages.common.TopGuestComponent;
import com.softserve.edu.greencity.ui.tests.GreenCityTestRunner;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class LoginTests extends GreenCityTestRunner {
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

    @Test(testName = "GC-211")
    public void signInModalValidation() {
        String titleString = loadApplication()
                .signIn().getTitleString();

        Assert.assertEquals(titleString, "Welcome back!");
    }
}
