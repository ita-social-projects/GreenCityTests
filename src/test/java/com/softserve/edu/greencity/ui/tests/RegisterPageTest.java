//package com.softserve.edu.greencity.ui.tests;
//
//import org.testng.Assert;
//import org.testng.annotations.DataProvider;
//import org.testng.annotations.Test;
//
//import com.softserve.edu.greencity.ui.data.User;
//import com.softserve.edu.greencity.ui.data.UserRepository;
//import com.softserve.edu.greencity.ui.pages.cabinet.LoginPage;
//import com.softserve.edu.greencity.ui.pages.cabinet.RegisterPage;
//import com.softserve.edu.greencity.ui.pages.common.TopPart;
//
///**
// * RegisterPageTest class.
// * @author Serg
// */
//public class RegisterPageTest extends GreenCityTestRunner {
//
//    @DataProvider
//    public Object[][] validCredentialUser() {
//        return new Object[][] {
//                { UserRepository.get().defaultUserCredentials() }, };
//    }
//
//    @DataProvider
//    public Object[][] validCredentialUser2() {
//        return new Object[][] { { UserRepository.get()
//                .temporaryUserCredentialsForRegistration() }, };
//    }
//
//    @DataProvider
//    public Object[][] invalidCredentialUser() {
//        return new Object[][] {
//                { UserRepository.get().wrongUserCredentials1() },
//                { UserRepository.get().wrongUserCredentials2() }, };
//    }
//
//    /**
//     * Filling all the fields on the Register page without registering and
//     * switch to Login page.
//     * @param userLoginCredentials
//     @Test(dataProvider = "validCredentialUser")
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
//
//    /**
//     * Filling all the fields on the Register page using the wrong credentials
//     * and reading the error message.
//     * @param userLoginCredentials
//     */
//    @Test(dataProvider = "invalidCredentialUser")
//    public void checkRegisterPage2(User userLoginCredentials) {
//        logger.info("start test checkRegisterPage1 with user = "
//                + userLoginCredentials.toString());
//        loadApplication().navigateMenuMyCabinetGuest();
//        logger.info("go to RegisterPage (click on Sign-up link)");
//        RegisterPage registerPage = new LoginPage(driver).gotoRegisterPage();
//        logger.info("get a top title text on the page: "
//                + registerPage.getTitleFieldText());
//        Assert.assertEquals("Hello!", registerPage.getTitleFieldText(),
//                "you did not go to the page RegisterPage");
//        //
//        logger.info("register new User with wrong credential");
//        registerPage.registrationWrongUser(userLoginCredentials);
//
//        Assert.assertEquals(registerPage.getFirstNameErrorText(),
//                "First Name error text not found",
//                "the error message does not equal the expected result");
//        Assert.assertEquals(registerPage.getEmailErrorText(),
//                "This is not email",
//                "the error message does not equal the expected result");
//        Assert.assertEquals(registerPage.getPasswordErrorText(),
//                "Password must be at least 8 characters long",
//                "the error message does not equal the expected result");
//        Assert.assertEquals(registerPage.getPasswordConfirmErrorText(),
//                "Passwords do not match",
//                "the error message does not equal the expected result");
//        presentationSleep(2); // delay only for presentation
//    }
//
//    /**
//     * Test for registration with temporary email and random other credentials
//     * with logging and checking displayed user name in the top of the page.
//     * @param userLoginCredentials
//     */
//    @Test(dataProvider = "validCredentialUser2")
//    public void checkRegistation2(User userLoginCredentials) {
//        logger.info("start test checkRegistation1 with user = "
//                + userLoginCredentials.toString());
//        loadApplication().navigateMenuMyCabinetGuest();
//        logger.info("go to RegisterPage (click on Sign-up link)");
//        RegisterPage registerPage = new LoginPage(driver).gotoRegisterPage();
//        Assert.assertEquals(registerPage.getTitleFieldText(), "Hello!",
//                "you did not go to the page RegisterPage");
//        logger.info("get a top title text on the page: "
//                + registerPage.getTitleFieldText());
//        //
//        logger.info(
//                "register new User with random credential and temporary email");
//        registerPage.registrationNewRandomUser(userLoginCredentials);
//        Assert.assertTrue(
//                registerPage.getConfirmRegistrationText()
//                        .contains("You have successfully registered"),
//                "you did not go to the page RegisterPage");
//        //
//        registerPage.navigateMenuMyCabinetGuest();
//        Assert.assertTrue(driver.getCurrentUrl().contains("#/auth"),
//                "you didn't go to Login page");
//        presentationSleep(2); // delay only for presentation
//        LoginPage page = new LoginPage(driver);
//        logger.info("login with temporary Email and random credential: "
//                + userLoginCredentials.toString());
//        page.inputEmail(userLoginCredentials.getEmail())
//                .inputPassword(userLoginCredentials.getPassword())
//                .clickLoginButton(); // not always success after one click (need
//                                     // one more click)
//        logger.info("get Title curent page: " + driver.getTitle());
//        Assert.assertEquals(driver.getTitle(), "Home",
//                "you didn't log in successfully");
//        logger.info("check TopUserName: " + page.getTopUserName());
//        Assert.assertEquals(page.getTopUserName(), TopPart.PROFILE_NAME);
//        presentationSleep(5); // delay only for presentation
//    }
//}
