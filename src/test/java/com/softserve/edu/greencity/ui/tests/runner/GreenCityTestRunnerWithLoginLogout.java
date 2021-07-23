package com.softserve.edu.greencity.ui.tests.runner;

import com.softserve.edu.greencity.ui.tools.testng.TestNgListeners;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.asserts.SoftAssert;


/**
 * A class for UI tests.
 * if you need to make login/logout before each method you should extend this one.
 */
@Listeners(TestNgListeners.class)
public abstract class GreenCityTestRunnerWithLoginLogout extends GreenCityBaseTestRunner {

    @BeforeMethod
    public void setUp() {
        driver.manage().window().maximize();
        driver.get(BASE_URL);
        softAssert = new SoftAssert();
    }


    @AfterMethod
    public void tearDown(ITestResult result) {
        if (!result.isSuccess()) {
            logger.warn("Test " + result.getName() + " ERROR");
        }
        if (isLogInNow()) {
            signOutByStorage();
        }
        loggerTest();
    }

}

