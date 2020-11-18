package com.softserve.edu.greencity.ui.tests.runner;

import com.softserve.edu.greencity.ui.api.google.sheets.ValueProvider;
import com.softserve.edu.greencity.ui.pages.common.WelcomePage;
import com.softserve.edu.greencity.ui.tools.CredentialProperties;
import com.softserve.edu.greencity.ui.tools.DateUtil;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import lombok.SneakyThrows;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteExecuteMethod;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.html5.RemoteWebStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.concurrent.TimeUnit;


/**
 * A base class for UI tests. All test classes should extend this one.
 */
public abstract class GreenCityTestRunner {
    private static final String BASE_URL = ValueProvider.getBaseUrl();
    private static int left = 160; //Total amount of UI tests
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    protected RemoteWebDriver driver;
    protected SoftAssert softAssert;
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
    public void setUpBeforeClass() throws MalformedURLException {
        if (remote) {
            /*<==========================Selenoid logs==========================>*/
            String className = this.getClass().getName();
            int index = className.lastIndexOf('.');
            className = className.substring(index + 1);
            String logName = (className + "-" + DateUtil.getCurrentYearMonthDateTime())
                    .replaceAll("\\s", "-")
                    .replaceAll("\\:", "-") + ".log";
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

        } else {
            /*<============================Local============================>*/
            driver = new RemoteWebDriver(
                    new URL("http://localhost:4444/wd/hub"), options);
        }
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(65, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        /*<============================Local============================>*/
    }

    @AfterClass(alwaysRun = true)
    public void tearDownAfterClass() {
        if (driver != null) {
            driver.quit();
        }
    }

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

    /**
     * The first method your tests should start with.
     * @return WelcomePage page object
     */
    protected WelcomePage loadApplication() {
        if (!driver.getCurrentUrl().equals(BASE_URL)) {
            driver.get(BASE_URL);
            /*Sometimes loadApplication() is called not in the beginning of a test,
            so this may be necessary*/
        }
        return new WelcomePage(driver);
    }

    @Step("verifying that user is not login")
    protected boolean isLogInNow() {
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

    public void loggerTest() {
        left = left - 1;


        logger.info("logging from thread " + Thread.currentThread().getId());
        logger.info("||||||  UI tests left " + (left) + "  |||||");
        logger.info("\n----------------------------------------------------------------------------\n");

    }
}

