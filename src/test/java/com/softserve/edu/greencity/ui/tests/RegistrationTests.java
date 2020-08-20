package com.softserve.edu.greencity.ui.tests;

import com.softserve.edu.greencity.ui.data.User;
import com.softserve.edu.greencity.ui.data.UserRepository;
import com.softserve.edu.greencity.ui.pages.cabinet.*;
import com.softserve.edu.greencity.ui.pages.common.TopGuestComponent;
import com.softserve.edu.greencity.ui.tools.api.mail.GoogleMailAPI;
import io.qameta.allure.Step;
import lombok.SneakyThrows;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class RegistrationTests extends GreenCityTestRunner{

    @DataProvider
    @Step("DataProvider get user Credentials For Registration")
    public Object[][] successRegistrationUserCreds() {
        return new Object[][]{{UserRepository.get()
                .userCredentialsForRegistration()},};
    }

    @Test(dataProvider = "successRegistrationUserCreds", description = "registration And Login \t GC-199, GC-206")
    @SneakyThrows
    public void registrationAndLogin(User userLoginCredentials) {
        GoogleMailAPI.clearMail();
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

    @Test(dataProvider = "successRegistrationUserCreds", description = "registration Without Mail Verifying \t GC-512")
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

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector((LoginComponent.MODAL_WINDOW_CSS))));

        ManualLoginComponent manualLoginComponent = new ManualLoginComponent(driver);

        manualLoginComponent.unsuccessfullyLogin(userLoginCredentials);

        Assert.assertEquals(manualLoginComponent.getWrongEmailOrPassErrorText(),
                "Bad email or password",
                "The validation message is not equal to the expected one");
    }

    @Test(dataProvider = "successRegistrationUserCreds", description = "registration Check If Mail Received \t GC-513")
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

}

