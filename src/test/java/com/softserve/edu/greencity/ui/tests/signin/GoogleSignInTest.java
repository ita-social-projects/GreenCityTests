package com.softserve.edu.greencity.ui.tests.signin;

import com.softserve.edu.greencity.data.users.User;
import com.softserve.edu.greencity.data.users.UserRepository;
import com.softserve.edu.greencity.ui.pages.common.WelcomePage;
import com.softserve.edu.greencity.ui.tests.runner.GreenCityTestRunnerWithLoginLogout;
import io.qameta.allure.Description;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.softserve.edu.greencity.ui.tests.signin.SignInTexts.TOP_USER_NAME;

//TODO auth in google via api before clickSingInWithGoogleButton()
public class GoogleSignInTest extends GreenCityTestRunnerWithLoginLogout {

    @Test(testName = "GC-218", description = "GC-218")
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

        Assert.assertEquals(topUserName, TOP_USER_NAME.getText());
    }

    @Test(testName = "GC-220", description = "GC-220")
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

        Assert.assertEquals(topUserName, TOP_USER_NAME.getText());
    }

    @Test(testName = "GC-234", description = "GC-234")
    @Description("Verify that user can't sign in with Google Account credentials on 'Sign in' pop-up window")
    public void signInByGoogleCredentialsOnManualSignInPopUp() {
        logger.info("Starting signInByGoogleCredentialsOnManualSignInPopUp");
        User user = UserRepository.get().googleUserCredentials();
        WelcomePage welcomePage = loadApplication()
                .signUp()
                .clickGoogleSignUpButton()
                .successfulLoginByGoogle(user);
        softAssert.assertEquals(welcomePage.getTopUserName(), TOP_USER_NAME.getText());

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
