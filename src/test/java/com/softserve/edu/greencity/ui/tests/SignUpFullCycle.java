package com.softserve.edu.greencity.ui.tests;

import com.softserve.edu.greencity.ui.data.User;
import com.softserve.edu.greencity.ui.data.UserRepository;
import com.softserve.edu.greencity.ui.pages.cabinet.LoginComponent;
import com.softserve.edu.greencity.ui.pages.cabinet.ManualLoginComponent;
import com.softserve.edu.greencity.ui.pages.cabinet.ManualRegisterComponent;
import com.softserve.edu.greencity.ui.pages.cabinet.RegisterComponent;
import com.softserve.edu.greencity.ui.pages.common.TopGuestComponent;
import com.softserve.edu.greencity.ui.pages.common.TopUserComponent;
import com.softserve.edu.greencity.ui.tools.DBQueries;
import com.softserve.edu.greencity.ui.tools.GMailBox;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class SignUpFullCycle extends GreenCityTestRunner{

    @DataProvider
    public Object[][] successRegistrationUserCreds() {
        return new Object[][]{{UserRepository.get()
                .userCredentialsForRegistration()},};
    }


//    @BeforeMethod
//    public void setUp() {
//
//        driver = new ChromeDriver();
//        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//        driver.manage().window().maximize();
//        driver.get(BASE_URL);
//    }

    @AfterMethod
    public void registerUserCleanUp() throws SQLException {
        loadApplication().signout();

        DBQueries queryObj = new DBQueries();
        queryObj.deleteUserByEmail("GCSignUpUser@gmail.com");


        GMailBox mailBox = new GMailBox(driver);
        ArrayList listOfEmails = mailBox.getAllMails();
        mailBox.deleteAllMails(listOfEmails);


        driver.manage().deleteAllCookies();

//        Set<String> allTabs = driver.getWindowHandles();
//
//       for ( String tab : allTabs){
//           driver.switchTo().window(tab);
//           driver.close();
//       }
       System.out.println("@AfterMethod registerUserCleanUp");
        //driver.quit();
    }


//    @Test
//    public void test(){
//        LoginComponent loginComponent = new TopGuestComponent(driver).clickSignInLink();
//
//        ManualLoginComponent manualLoginComponent = loginComponent.getManualLoginComponent();
//
//        manualLoginComponent.inputEmail("ilchenkoliuba@ukr.net")
//                .inputPassword("!Error911")
//                .clickLoginButton();
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        loadApplication().signout();
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//    }

    /**
     * //     * Test for registration with temporary email and random other credentials
     * //     * with logging and checking displayed user name in the top of the page.
     * //     * @param userLoginCredentials
     * //
     */
    @Test(dataProvider = "successRegistrationUserCreds")
    public void registrationAndLogin(User userLoginCredentials) {
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
    @Test(dataProvider = "successRegistrationUserCreds")
    public void registrationWithoutMailVerif(User userLoginCredentials) {
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

    //GC-513
    //Verify that user receive a verification email about registration in the application to email address after successfully registration.
    @Test(dataProvider = "successRegistrationUserCreds")
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

        logger.info("Enter random credentials and temporary email into the form: ");
        manualRegisterComponent.registerUserCheckIfMailReceived(userLoginCredentials);

    }

}

