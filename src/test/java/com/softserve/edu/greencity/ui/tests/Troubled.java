package com.softserve.edu.greencity.ui.tests;


import com.softserve.edu.greencity.ui.data.User;
import com.softserve.edu.greencity.ui.data.UserRepository;
import com.softserve.edu.greencity.ui.pages.cabinet.ManualRegisterComponent;
import com.softserve.edu.greencity.ui.pages.cabinet.RegisterComponent;
import com.softserve.edu.greencity.ui.pages.common.TopGuestComponent;
import com.softserve.edu.greencity.ui.pages.tipstricks.TipsTricksPage;
import com.softserve.edu.greencity.ui.tools.DBQueries;
import com.softserve.edu.greencity.ui.tools.GMailBox;
import org.openqa.selenium.JavascriptExecutor;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

public class Troubled extends GreenCityTestRunner {


    //GC-513
    //Verify that user receive a verification email about registration in the application to email address after successfully registration.
    @Test(dataProvider = "successRegistrationUserCreds")
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
        manualRegisterComponent.registerUserCheckIfMailReceived(userLoginCredentials);

    }

    @DataProvider
    public Object[][] successRegistrationUserCreds() {
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

}





