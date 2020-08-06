package com.softserve.edu.greencity.ui.tests.login;

import com.softserve.edu.greencity.ui.data.User;
import com.softserve.edu.greencity.ui.data.UserRepository;
import com.softserve.edu.greencity.ui.pages.cabinet.GoogleLoginPage;
import com.softserve.edu.greencity.ui.pages.tipstricks.TipsTricksPage;
import com.softserve.edu.greencity.ui.tests.GreenCityTestRunner;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class LoginByGoogleTests extends GreenCityTestRunner {
    @Test(testName = "GC-218")
    public void signUpByGoogle() {
        User user = UserRepository.get().gMailUserCredentialsSignIn();

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
        User user = UserRepository.get().gMailUserCredentialsSignIn();

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
        User user = UserRepository.get().gMailUserCredentialsSignIn();
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
