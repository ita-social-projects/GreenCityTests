package com.softserve.edu.greencity.ui.tests;

import com.softserve.edu.greencity.ui.data.User;
import com.softserve.edu.greencity.ui.data.UserRepository;
import com.softserve.edu.greencity.ui.pages.cabinet.*;
import com.softserve.edu.greencity.ui.pages.common.TopGuestComponent;
import com.softserve.edu.greencity.ui.tools.api.google.sheets.GoogleSheet;
import com.softserve.edu.greencity.ui.tools.api.mail.GoogleMailAPI;
import lombok.SneakyThrows;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

//TODO add DB check
public class RegistrationTests extends GreenCityTestRunner{

    @DataProvider
    public Object[][] successRegistrationUserCreds() {
        return new Object[][]{{UserRepository.get()
                .userCredentialsForRegistration()},};
    }

    @DataProvider
    public Object[][] invalidPasswordDataProvider() {
        return new Object[][]{
                {UserRepository.get().invalidPassLowercaseUserCreds()},
                {UserRepository.get().invalidPassLowercaseUserCreds()},
                {UserRepository.get().invalidPassDigitUserCreds()},
                {UserRepository.get().invalidPassSpecCharUserCreds()},
                {UserRepository.get().invalidPassSpaceUserCreds()},
        };
    }

    @Test(dataProvider = "successRegistrationUserCreds", description = "GC-199, GC-206")
    @SneakyThrows
    public void registrationAndLogin(User userLoginCredentials) {
        logger.info("Start test registration and login");
       new  GoogleMailAPI().clearMail(userLoginCredentials.getEmail(),userLoginCredentials.getPassword());
        loadApplication();
        RegisterComponent registerComponent = new TopGuestComponent(driver).clickSignUpLink();
        ManualRegisterComponent manualRegisterComponent = registerComponent.getManualRegisterComponent();
        manualRegisterComponent.registrationNewUserVerified(userLoginCredentials);

        loadApplication()
                .signIn()
                .getManualLoginComponent()
                .successfullyLogin(userLoginCredentials);
        Assert.assertTrue(isLogInNow());
    }

    @Test(dataProvider = "successRegistrationUserCreds", description = "GC-512")
    public void registrationWithoutMailVerif(User userLoginCredentials) {
        logger.info("Start test registration without mail verifying");
        loadApplication();
        logger.info("Starting registrationWithoutMailVerif. Input values = "
                + userLoginCredentials.toString());

        RegisterComponent registerComponent = new TopGuestComponent(driver).clickSignUpLink();

        logger.info("Get a title text of the modal window: "
                + registerComponent.getTitleString());

        Assert.assertEquals("Hello!", registerComponent.getTitleString(),
                "This is not a register modal:(");


        ManualRegisterComponent manualRegisterComponent = registerComponent.getManualRegisterComponent();

        logger.info("Enter credentials into the form");
        manualRegisterComponent.registrationUser(userLoginCredentials);

        WebDriverWait wait = new WebDriverWait(driver,6);

        wait.until(ExpectedConditions.visibilityOf(registerComponent.getCongratsModal()));
        Assert.assertTrue(registerComponent.getCongratsModal().isDisplayed());

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector((LoginComponent.MODAL_WINDOW_CSS))));

        ManualLoginComponent manualLoginComponent = new ManualLoginComponent(driver);

        manualLoginComponent.unsuccessfullyLogin(userLoginCredentials);

        Assert.assertEquals(manualLoginComponent.getWrongEmailOrPassErrorText(),
                "Bad email or password",
                "The validation message is not equal to the expected one");
    }

    @Test(dataProvider = "successRegistrationUserCreds", description = "GC-513")
    public void registrationCheckIfMailReceived(User userLoginCredentials) {
        logger.info("Start test registration check if mail received");
        loadApplication();
        logger.info("Starting registrationCheckIfMailReceived. Input values = "
                + userLoginCredentials.toString());

        RegisterComponent registerComponent = new TopGuestComponent(driver).clickSignUpLink();

        logger.info("Get a title text of the modal window: "
                + registerComponent.getTitleString());

        Assert.assertEquals("Hello!", registerComponent.getTitleString(),
                "This is not a register modal:(");

        ManualRegisterComponent manualRegisterComponent = registerComponent.getManualRegisterComponent();

        logger.info("Enter random credentials and temporary email into the form ");
        manualRegisterComponent.registerUserCheckIfMailReceived(userLoginCredentials);
    }

    @Test(dataProvider = "successRegistrationUserCreds", description = "GC-204")
        public void existingUserRegistration(User userLoginCredentials){
        new  GoogleMailAPI().clearMail(userLoginCredentials.getEmail(),userLoginCredentials.getPassword());
        logger.info("Start test existing user registration" + userLoginCredentials.toString());
        loadApplication();
        RegisterComponent registerComponent = new TopGuestComponent(driver).clickSignUpLink();
        ManualRegisterComponent manualRegisterComponent = registerComponent.getManualRegisterComponent();
        manualRegisterComponent.registerUserCheckIfMailReceived(userLoginCredentials);
        Assert.assertNotNull(new GoogleMailAPI().getconfirmURL(userLoginCredentials.getEmail(),userLoginCredentials.getPassword(),20));
        signOutByStorage();
        driver.navigate().refresh();
        new TopGuestComponent(driver).clickSignUpLink();
        manualRegisterComponent.enterDataToSingUpFields(userLoginCredentials);
        manualRegisterComponent.clickSignUpButton();
        Assert.assertEquals(
                manualRegisterComponent
                        .getSignUpErrorsMsg(1),
                "The user already exists by this email",
                "error msg mismatch"
        );
        //The user already exists by this email
    }

    @Test(dataProvider = "invalidPasswordDataProvider", description = "GC-204")
    public void invalidPasswordRegistration(User userLoginCredentials){
        logger.info("Start test invalid password registration"  + userLoginCredentials.toString());
        loadApplication();
        RegisterComponent registerComponent = new TopGuestComponent(driver).clickSignUpLink();
        ManualRegisterComponent manualRegisterComponent = registerComponent.getManualRegisterComponent();
        manualRegisterComponent.enterDataToSingUpFields(userLoginCredentials);
        Assert.assertTrue(manualRegisterComponent.getSignUpButton().isDisplayed());
    }
    @Test
    public void ApiTest() throws IOException, GeneralSecurityException {
        List<List<Object>> a = GoogleSheet.values();
        System.out.println(a.get(0).get(2));
    }
}

