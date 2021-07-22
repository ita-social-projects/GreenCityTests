package com.softserve.edu.greencity.ui.tests.runner;

import com.softserve.edu.greencity.ui.api.google.sheets.ValueProvider;
import com.softserve.edu.greencity.ui.pages.common.WelcomePage;
import com.softserve.edu.greencity.ui.tests.runner.DriverSetup;
import com.softserve.edu.greencity.ui.tools.CredentialProperties;
import com.softserve.edu.greencity.ui.tools.DateUtil;
import com.softserve.edu.greencity.ui.tools.testng.TestNgListeners;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.SneakyThrows;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.concurrent.TimeUnit;

@Listeners(TestNgListeners.class)
public abstract class GreenCityTestRunner extends GreenCityBaseTestRunner{


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
        loggerTest();
    }


}



