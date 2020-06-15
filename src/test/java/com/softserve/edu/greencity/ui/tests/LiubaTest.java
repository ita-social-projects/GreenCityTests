package com.softserve.edu.greencity.ui.tests;

import com.softserve.edu.greencity.ui.data.User;
import com.softserve.edu.greencity.ui.data.UserRepository;
import com.softserve.edu.greencity.ui.pages.cabinet.LoginPage;
import com.softserve.edu.greencity.ui.pages.cabinet.RegisterComponent;
import com.softserve.edu.greencity.ui.pages.cabinet.RegisterPage;
import com.softserve.edu.greencity.ui.pages.common.TopGuestComponent;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LiubaTest extends GreenCityTestRunner {

    @DataProvider
    public Object[][] validCredentialUser() {
        return new Object[][] {
                { UserRepository.get().defaultUserCredentials() }, };
    }

    @DataProvider
    public Object[][] validCredentialUser2() {
        return new Object[][] { { UserRepository.get()
                .temporaryUserCredentialsForRegistration() }, };
    }

    @DataProvider
    public Object[][] invalidCredentialUser() {
        return new Object[][] {
                { UserRepository.get().wrongUserCredentials1() },
                { UserRepository.get().wrongUserCredentials2() }, };
    }

    /**
     * Filling all the fields on the Register page without registering and
     * switch to Login page.
     * @param userLoginCredentials
     */
//    @Test(dataProvider = "validCredentialUser")
//    public void checkRegisterPage1(User userLoginCredentials) {
//        logger.info("start test checkRegisterPage1 with user = "
//                + userLoginCredentials.toString());
//        loadApplication().navigateMenuMyCabinetGuest();
//        logger.info("go to RegisterPage (click on Sign-up link)");
//        RegisterPage registerPage = new LoginPage(driver).gotoRegisterPage();
//        //
//        logger.info("get a top title text on the page: "
//                + registerPage.getTitleFieldText());
//        Assert.assertEquals("Hello!", registerPage.getTitleFieldText(),
//                "you did not go to the page RegisterPage");
//        logger.info(
//                "register new User with valid credential without click on Sign-up button");
//        registerPage.fillFieldsWithoutRegistration(userLoginCredentials);
//        presentationSleep(2); // delay only for presentation
//        LoginPage loginPage = registerPage.clickSignInLink();
//        Assert.assertTrue(driver.getCurrentUrl().contains("#/auth"),
//                "you didn't go to Login page");
//        presentationSleep(2); // delay only for presentation
//        logger.info("click on Sign-up link go to RegisterPage");
//        loginPage.gotoRegisterPage();
//        Assert.assertTrue(driver.getCurrentUrl().contains("#/auth/sign-up"),
//                "you didn't go to Register page");
//        Assert.assertEquals("Hello!", registerPage.getTitleFieldText(),
//                "you did not go to the page RegisterPage");
//        presentationSleep(2); // delay only for presentation
//    }

    /**
     * Filling all the fields on the Register page using the wrong credentials
     * and reading the error message.
     * @param userLoginCredentials
     */
    @Test(dataProvider = "invalidCredentialUser")
    public void checkRegisterPage2(User userLoginCredentials) {
//        logger.info("start test checkRegisterPage1 with user = "
//                + userLoginCredentials.toString());
        RegisterPage registerPage = new TopGuestComponent(driver).clickSignupLink();
        RegisterComponent registerComponent = new RegisterComponent(driver);
//        logger.info("go to RegisterPage (click on Sign-up link)");

//        logger.info("get a top title text on the page: "
//                + registerPage.getTitleFieldText());
        Assert.assertEquals("Hello!", registerComponent.getTitlePageText(),
                "you did not go to the page RegisterPage");

//        logger.info("register new User with wrong credential");
        registerComponent.registrationWrongUser(userLoginCredentials);

//        Assert.assertEquals(registerComponent.getFirstNameValidatorText(),
//                "First Name error text not found",
//                "the error message does not equal the expected result");

        Assert.assertEquals(registerComponent.getEmailValidatorText(),
                "Please check that your e-mail address is indicated correctly",
                "the error message does not equal the expected result");

//        Assert.assertEquals(registerComponent.getPasswordValidatorText(),
//                "Password must be at least 8 characters long without spaces",
//                //OR Password must be at least 8 characters in length
//                "the error message does not equal the expected result");

//        Assert.assertEquals(registerComponent.getPasswordConfirmValidatorText(),
//                "Passwords do not match",
//                "the error message does not equal the expected result");
        //OR Passwords do not match
        //Password has contain at least one character of Uppercase letter (A-Z),
        // Lowercase letter (a-z), Digit (0-9), Special character (~`!@#$%^&*()+=_-{}[]|:;”’?/<>,.)
        driver.navigate().refresh();
    }
}
