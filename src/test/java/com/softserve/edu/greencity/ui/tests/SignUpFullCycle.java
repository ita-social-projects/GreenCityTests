package com.softserve.edu.greencity.ui.tests;

import com.softserve.edu.greencity.ui.data.User;
import com.softserve.edu.greencity.ui.data.UserRepository;
import com.softserve.edu.greencity.ui.pages.cabinet.ManualLoginComponent;
import com.softserve.edu.greencity.ui.pages.cabinet.ManualRegisterComponent;
import com.softserve.edu.greencity.ui.pages.cabinet.RegisterComponent;
import com.softserve.edu.greencity.ui.pages.common.TopGuestComponent;
import com.softserve.edu.greencity.ui.pages.common.TopUserComponent;
import com.softserve.edu.greencity.ui.tools.DBQueries;
import com.softserve.edu.greencity.ui.tools.GMailBox;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;
import java.util.ArrayList;

public class SignUpFullCycle extends GreenCityTestRunner{
    @DataProvider
    public Object[][] randomValidUserCredentials() {
        return new Object[][]{{UserRepository.get()
                .userCredentialsForRegistration()},};
    }

    @DataProvider
    public Object[][] validUserCredentials() {
        return new Object[][]{{UserRepository.get()
                .userCredentialsForRegistration()},};
    }

    @AfterMethod
    public void registerUserCleanUp() throws SQLException {
        DBQueries queryObj = new DBQueries();
        queryObj.deleteUserByEmail("GCSignUpUser@gmail.com");


        GMailBox mailBox = new GMailBox(driver)
                .logInGMail();
        ArrayList listOfEmails = mailBox.getAllMails();
        mailBox.deleteAllMails(listOfEmails);
    }

    /**
     * //     * Test for registration with temporary email and random other credentials
     * //     * with logging and checking displayed user name in the top of the page.
     * //     * @param userLoginCredentials
     * //
     */
    @Test(dataProvider = "randomValidUserCredentials")
    public void randomCredsRegistrationLogin(User userLoginCredentials) {
        loadApplication();
        logger.info("Starting randomCredsRegistrationLogin. Input values = "
                + userLoginCredentials.toString());

        logger.info("Click on Sign up button");
        RegisterComponent registerComponent = new TopGuestComponent(driver).clickSignUpLink();

        logger.info("Get a title text of the modal window: "
                + registerComponent.getTitleString());

        Assert.assertEquals("Hello!", registerComponent.getTitleString(),
                "This is not a register modal:(");

        ManualRegisterComponent manualRegisterComponent = registerComponent.getManualRegisterComponent();

        logger.info("Enter random credentials and temporary email into the form: ");
        manualRegisterComponent.registrationNewUserVerified(userLoginCredentials);


        ManualLoginComponent manualLoginComponent = new ManualLoginComponent(driver);


        manualLoginComponent.successfullyLogin(userLoginCredentials);

        logger.info("get Title curent page: " + driver.getTitle());
        Assert.assertEquals(driver.getTitle(), "Home",
                "you didn't log in successfully");

        TopUserComponent userComponent = new TopUserComponent(driver);
        logger.info("check TopUserName: " + userComponent.getUserNameButtonText());
        Assert.assertEquals(userComponent.getUserNameButtonText(), userLoginCredentials.getUserName());

    }



    // GC-512
    // Verify that user is not registered if he didn’t confirm email address in the mailbox
    @Test(dataProvider = "validUserCredentials")
    public void registrationWithoutEmaVerfication(User userLoginCredentials) {
        loadApplication();
        logger.info("Starting registrationWithoutEmaVerfication. Input values = "
                + userLoginCredentials.toString());

        logger.info("Click on Sign up button");
        RegisterComponent registerComponent = new TopGuestComponent(driver).clickSignUpLink();

        logger.info("Get a title text of the modal window: "
                + registerComponent.getTitleString());

        Assert.assertEquals("Hello!", registerComponent.getTitleString(),
                "This is not a register modal:(");


        ManualRegisterComponent manualRegisterComponent = registerComponent.getManualRegisterComponent();

        logger.info("Enter random credentials and temporary email into the form: ");
        manualRegisterComponent.registrationUser(userLoginCredentials);

        Assert.assertTrue(registerComponent.getCongratsModal().isDisplayed());

        WebDriverWait wait = new WebDriverWait(driver,6);
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(("app-sign-in div"))));

        ManualLoginComponent manualLoginComponent = new ManualLoginComponent(driver);

        manualLoginComponent.unsuccessfullyLogin(userLoginCredentials);

        Assert.assertEquals(manualLoginComponent.getWrongCredsErrorText(),
                "Bad email or password",
                "The validation message is not equal to the expected one");
    }




}

