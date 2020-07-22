package com.softserve.edu.greencity.ui.tests;

import com.google.api.services.gmail.Gmail;
import com.softserve.edu.greencity.ui.data.User;
import com.softserve.edu.greencity.ui.data.UserRepository;
import com.softserve.edu.greencity.ui.pages.cabinet.LoginComponent;
import com.softserve.edu.greencity.ui.pages.cabinet.ManualLoginComponent;
import com.softserve.edu.greencity.ui.pages.cabinet.ManualRegisterComponent;
import com.softserve.edu.greencity.ui.pages.cabinet.RegisterComponent;
import com.softserve.edu.greencity.ui.pages.common.TopGuestComponent;
import com.softserve.edu.greencity.ui.pages.common.TopUserComponent;
import com.softserve.edu.greencity.ui.tools.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.sql.Connection;
import java.util.ArrayList;

public class RegistrationTests extends GreenCityTestRunner{

    @DataProvider
    public Object[][] successRegistrationUserCreds() {
        return new Object[][]{{UserRepository.get()
                .userCredentialsForRegistration()},};
    }


    @AfterMethod(alwaysRun = true)
    public void registerUserCleanUp() {
        CookiesAndStorageHelper help = new CookiesAndStorageHelper(driver);
        help.cleanGreenCityCookiesAndStorages();
        help.cleanGMailCookiesAndStorages();


        DBQueries queryObj = new DBQueries();
        queryObj.deleteUserByEmail("GCSignUpUser@gmail.com");

    }

    @AfterClass(alwaysRun = true)
    public void mailBoxCleanUp(){

        try {
            Gmail service = GMailAPILogin.getService();
            GMailBatchDelete.batchDelete(service);
        } catch (GeneralSecurityException|IOException e) {
            e.printStackTrace();
        }
        CookiesAndStorageHelper help = new CookiesAndStorageHelper(driver);
        help.cleanCookiesAndStorages();
    }

    @Test(dataProvider = "successRegistrationUserCreds", description = "GC-199, GC-206")
    public void registrationAndLogin(User userLoginCredentials) {
        loadApplication();
        logger.info("Starting registrationAndLogin. Input values = "
                + userLoginCredentials.toString());

        logger.info("Click on Sign up button");
        RegisterComponent registerComponent = new TopGuestComponent(driver).clickSignUpLink();

        logger.info("Get a title text of the modal window: "
                + registerComponent.getTitleString());

        Assert.assertEquals("Hello!", registerComponent.getTitleString(),
                "This is not a register modal:(");

        ManualRegisterComponent manualRegisterComponent = registerComponent.getManualRegisterComponent();

        logger.info("Enter credentials into the form");
        manualRegisterComponent.registrationUser(userLoginCredentials);

        WebDriverWait wait = new WebDriverWait(driver,10);

        wait.until(ExpectedConditions.visibilityOf(registerComponent.getCongratsModal()));
        Assert.assertTrue(registerComponent.getCongratsModal().isDisplayed());

        LoginComponent loginComp = new LoginComponent(driver);
        wait.until(ExpectedConditions.visibilityOf(loginComp.getLoginModalWindow()));
        Assert.assertTrue(loginComp.getLoginModalWindow().isDisplayed());

        manualRegisterComponent.verifyRegistration();

        ManualLoginComponent manualLoginComponent = new ManualLoginComponent(driver);

        manualLoginComponent.successfullyLogin(userLoginCredentials);

        logger.info("Get Title curent page: " + driver.getTitle());
        Assert.assertEquals(driver.getTitle(), "Home",
                "you didn't log in successfully");

        TopUserComponent userComponent = new TopUserComponent(driver);

        logger.info("Check TopUserName: " + userComponent.getUserNameButtonText());
        Assert.assertEquals(userComponent.getUserNameButtonText(), userLoginCredentials.getUserName());

    }

    @Test(dataProvider = "successRegistrationUserCreds", description = "GC-512")
    public void registrationWithoutMailVerif(User userLoginCredentials) {
        loadApplication();
        logger.info("Starting registrationWithoutMailVerif. Input values = "
                + userLoginCredentials.toString());

        logger.info("Click on Sign up button");
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

        LoginComponent loginComp = new LoginComponent(driver);
        wait.until(ExpectedConditions.visibilityOf(loginComp.getLoginModalWindow()));
        Assert.assertTrue(loginComp.getLoginModalWindow().isDisplayed());

        ManualLoginComponent manualLoginComponent = new ManualLoginComponent(driver);

        manualLoginComponent.unsuccessfullyLogin(userLoginCredentials);

        Assert.assertEquals(manualLoginComponent.getWrongEmailOrPassErrorText(),
                "Bad email or password",
                "The validation message is not equal to the expected one");
    }

    @Test(dataProvider = "successRegistrationUserCreds", description = "GC-513")
    public void registrationCheckIfMailReceived(User userLoginCredentials) {
        loadApplication();
        logger.info("Starting registrationCheckIfMailReceived. Input values = "
                + userLoginCredentials.toString());

        logger.info("Click on Sign up button");
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
    public void registrationWithTakenEmail(User userLoginCredentials) {
        loadApplication();
        logger.info("Starting registrationWithoutMailVerif. Input values = "
                + userLoginCredentials.toString());

        logger.info("Click on Sign up button");
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

        LoginComponent loginComp = new LoginComponent(driver);
        wait.until(ExpectedConditions.visibilityOf(loginComp.getLoginModalWindow()));
        Assert.assertTrue(loginComp.getLoginModalWindow().isDisplayed());

        logger.info("Close login pop-up");
        loginComp.closeLoginComponent();

        logger.info("Click on Sign up button");
        registerComponent = new TopGuestComponent(driver).clickSignUpLink();

        logger.info("Get a title text of the modal window: "
                + registerComponent.getTitleString());

        Assert.assertEquals("Hello!", registerComponent.getTitleString(),
                "This is not a register modal:(");


        manualRegisterComponent = registerComponent.getManualRegisterComponent();

        logger.info("Enter already used credentials into the form");
        manualRegisterComponent.registrationUser(userLoginCredentials);

        Assert.assertEquals(manualRegisterComponent.getEmailValidatorText(),
                "The user already exists by this email",
                "The validation message is not equal to the expected one");

        DBQueries db = new DBQueries();
        Assert.assertFalse(db.isUserEmailDuplicated("GCSignUpUser@gmail.com"));
    }


}

