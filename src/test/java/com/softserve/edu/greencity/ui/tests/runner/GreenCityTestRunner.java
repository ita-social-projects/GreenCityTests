package com.softserve.edu.greencity.ui.tests.runner;

import com.softserve.edu.greencity.ui.pages.common.WelcomePage;
import com.softserve.edu.greencity.ui.tools.CredentialProperties;
import com.softserve.edu.greencity.ui.tools.DateUtil;
import com.softserve.edu.greencity.ui.tools.api.google.sheets.ValueProvider;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import lombok.SneakyThrows;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteExecuteMethod;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.html5.RemoteWebStorage;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.net.URI;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.invisibilityOfElementLocated;

public abstract class GreenCityTestRunner {
    private static final String BASE_URL = ValueProvider.getBaseUrl();

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    protected RemoteWebDriver driver;
    boolean remote = ValueProvider.remote();
    ChromeOptions options = new ChromeOptions();
    @SneakyThrows
    @BeforeSuite
    public void beforeSuite() {
       new CredentialProperties().checkCredentialsExist();
        new DriverSetup().optionsArguments();
        WebDriverManager.chromedriver().setup();
    }

    @SneakyThrows
    @BeforeClass
    public void setUpBeforeClass() {
        if (remote) {
            /*<==========================Selenoid logs==========================>*/
            String className = this.getClass().getName();
            int index=className.lastIndexOf('.');
            className =className.substring(index+1);
            String logName = (className + "-" + DateUtil.getCurrentYearMonthDateTime())
                    .replaceAll("\\s","-")
                    .replaceAll("\\:","-") + ".log";
            logger.info("http://35.198.124.146:4444/logs");
            logger.info("Selenoid logs:http://35.198.124.146:4444//logs/" + logName);
            /*<==========================Selenoid logs==========================>*/

            /*<=======================Remote capabilities=======================>*/
            DesiredCapabilities capabilities = DesiredCapabilities.chrome();
            capabilities.setBrowserName("chrome");
            capabilities.setVersion("84.0");
            capabilities.setCapability("enableVNC", true);
            capabilities.setCapability("enableVideo", false);
            capabilities.setCapability("name", className);
            capabilities.setCapability("enableLog", true);
            capabilities.setCapability("logName", logName);
            capabilities.setCapability(ChromeOptions.CAPABILITY, options);
            driver = new RemoteWebDriver(
                    URI.create("http://35.198.124.146:4444/wd/hub").toURL(),
                    capabilities);
            /*<=======================Remote capabilities=======================>*/

        }else {
            /*<============================Locale============================>*/
            driver = new RemoteWebDriver(
                    new URL("http://localhost:4444/wd/hub"),options);
        }
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
            /*<============================Locale============================>*/
    }

    @AfterClass(alwaysRun = true)
    public void tearDownAfterClass() {
        if (driver != null) {
            driver.quit();
        }
    }

    @BeforeMethod
    public void setUp() {
        driver.get(BASE_URL);
    }


    @AfterMethod
    public void tearDown(ITestResult result) {
        if (!result.isSuccess()) {
            logger.warn("Test " + result.getName() + " ERROR");
        }
        if (isLogInNow()) {
            signOutByStorage();
        }
        //System.out.println("@AfterMethod tearDown");
        loggerTest();
    }

    protected WelcomePage loadApplication() {
        return new WelcomePage(driver);
    }

    @Step("verifying that user is not login")
    protected boolean isLogInNow() {
        new WebDriverWait(driver, 10).until(invisibilityOfElementLocated(By.id("form.sign-in-form")));
        RemoteExecuteMethod executeMethod = new RemoteExecuteMethod((RemoteWebDriver) driver);
        RemoteWebStorage webStorage = new RemoteWebStorage(executeMethod);
        return !((webStorage.getLocalStorage().getItem("name")) == null);
    }

    @Step
    protected void signOutByStorage() {
        RemoteExecuteMethod executeMethod = new RemoteExecuteMethod((RemoteWebDriver) driver);
        RemoteWebStorage webStorage = new RemoteWebStorage(executeMethod);
        webStorage.getLocalStorage().clear();
        driver.navigate().refresh();
    }

    @Step("download All WebDrivers")
    protected void downloadAllDrivers() {
        WebDriverManager.chromedriver().setup();
        WebDriverManager.firefoxdriver().setup();
        WebDriverManager.edgedriver().setup();
        WebDriverManager.operadriver().setup();
        WebDriverManager.phantomjs().setup();
        WebDriverManager.iedriver().setup();
        WebDriverManager.chromiumdriver().setup();
    }
   private static int left  = 77;
    public void loggerTest() {
         left = left -1;


        logger.info("logging from thread " + Thread.currentThread().getId());
        logger.info("||||||  Tests left " + (left) + "  |||||" );
        logger.info("\n----------------------------------------------------------------------------\n");

    }
}

