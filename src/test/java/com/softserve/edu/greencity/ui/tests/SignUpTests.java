package com.softserve.edu.greencity.ui.tests;

import com.softserve.edu.greencity.ui.data.User;
import com.softserve.edu.greencity.ui.data.UserRepository;
import com.softserve.edu.greencity.ui.pages.cabinet.LoginComponent;
import com.softserve.edu.greencity.ui.pages.cabinet.ManualRegisterComponent;
import com.softserve.edu.greencity.ui.pages.cabinet.RegisterComponent;
import com.softserve.edu.greencity.ui.pages.common.TopGuestComponent;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SignUpTests extends GreenCityTestRunner{

    //GC-501
    //Verify that pop up 'Sign up' opens after click 'Sign up' link on the ‘Sign in’ pop up.
    @Test
    public void navigateFromSignInToSignUp() {

        loadApplication();
        logger.info("Starting navigateFromSignInToSignUp");

        logger.info("Click on Sign in button");
       LoginComponent loginComponent = new TopGuestComponent(driver).clickSignInLink();

        Assert.assertEquals("Welcome back!", loginComponent.getTitleString(),
                "This is not a login modal:(");

        Assert.assertEquals("Please enter your details to sign in", loginComponent.getSubtitleString(),
                "This is not a login modal:(");


        logger.info("Click on Sign up button");
        RegisterComponent registerComponent = loginComponent.clickSignUpLink();

        logger.info("Get a title text of the modal window: "
                + registerComponent.getTitleString());

        Assert.assertEquals("Hello!", registerComponent.getTitleString(),
                "This is not a register modal:(");

        Assert.assertEquals("Please enter your details to sign up", registerComponent.getSubtitleString(),
                "This is not a register modal:(");

    }

    //GC-509
    //Verify that user is not registered, when he enters invalid e-mail format in ‘Email’ field.
    @Test(dataProvider = "invalidEmail")
    public void invalidEmailRegistration(User invalidEmailCredentials) {
        loadApplication();
        logger.info("Starting checkInvalidFieldsValidation. Input values = "
                + invalidEmailCredentials.toString());

        logger.info("Click on Sign up button");
        ManualRegisterComponent manualRegisterComponent = new TopGuestComponent(driver)
                .clickSignUpLink()
                .getManualRegisterComponent();

//        logger.info("Get a title text of the modal window: "
//                + registerComponent.getTitleString());
//
//        Assert.assertEquals("Hello!", registerComponent.getTitleString(),
//                "This is not a register modal:(");

        logger.info("Enter invalid email and valid name and password into the form: ");
        manualRegisterComponent.fillFieldsWithoutRegistration(invalidEmailCredentials);

        Assert.assertEquals(manualRegisterComponent.getEmailValidatorText(),
                "Please check that your e-mail address is indicated correctly",
                "The validation message is not equal to the expected one");

        Assert.assertTrue(manualRegisterComponent.signUpIsDisabled());

    }



    //GC-519
    //Verify that user is not registered, when he enters invalid e-mail format in ‘Email’ field.
        @Test(dataProvider = "invalidConfirmPass")
    public void invalidConfirmPassRegistration(User userLoginCredentials) {
        loadApplication();
        logger.info("Starting checkInvalidFieldsValidation. Input values = "
                + userLoginCredentials.toString());

        logger.info("Click on Sign up button");
        RegisterComponent registerComponent = new TopGuestComponent(driver).clickSignUpLink();

        logger.info("Get a title text of the modal window: "
                + registerComponent.getTitleString());

        Assert.assertEquals("Hello!", registerComponent.getTitleString(),
                "This is not a register modal:(");

        ManualRegisterComponent manualRegisterComponent = registerComponent.getManualRegisterComponent();

        logger.info("Enter invalid values into the form: ");
        manualRegisterComponent.fillFieldsWithoutRegistration(userLoginCredentials);

            Assert.assertEquals(manualRegisterComponent.getPasswordConfirmValidatorText(),
                    "Passwords do not match",
                    "The validation message is not equal to the expected one");

            Assert.assertTrue(manualRegisterComponent.signUpIsDisabled());

        }

    @DataProvider
    public Object[][] invalidConfirmPass() {
        return new Object[][]{
                {UserRepository.get().invalidConfirmPassCredentials()},};
    }
    


}
