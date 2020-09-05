package com.softserve.edu.greencity.ui.tests;

import com.softserve.edu.greencity.ui.pages.common.WelcomePage;
import com.softserve.edu.greencity.ui.tools.CommandLine;
import com.softserve.edu.greencity.ui.tools.CredentialProperties;
import com.softserve.edu.greencity.ui.tools.grid.GridHub;
import com.softserve.edu.greencity.ui.tools.grid.RegisterChrome;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import lombok.SneakyThrows;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
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
import java.util.logging.Level;

import static org.openqa.selenium.support.ui.ExpectedConditions.invisibilityOfElementLocated;

public abstract class GreenCityTestRunner {
    private static final String BASE_URL = "https://ita-social-projects.github.io/GreenCityClient/#/welcome";
//    public static final String BASE_URL = "http://localhost:4200/#/welcome";

    private final boolean CHROME_HEADLESS_OPTION = false;
    private final String CHROME_LANGUAGE_OPTION = "en";

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    protected RemoteWebDriver driver;

    private ChromeOptions options = new ChromeOptions();
@SneakyThrows
    @BeforeSuite
    public void beforeSuite() {
    options.addArguments("--disable-gpu");
    options.addArguments("--disable-popup-blocking");
    options.addArguments("--allow-failed-policy-fetch-for-test");
    options.addArguments("--disable-browser-side-navigation");
    options.addArguments("--incognito");
    options.addArguments("--disable-notifications");
    options.addArguments("--window-size=1920,1080","--no-sandbox","'--disable-dev-shm-usage");
    options.addArguments("--headless");
        WebDriverManager.chromedriver().setup();

    }

    /*  DesiredCapabilities desiredCap = DesiredCapabilities.Chrome();
  desiredCap.SetCapability("headless", true);
  desiredCap.SetCapability("platform", "LINUX");
  desiredCap.SetCapability("version", "latest");

      driver = new RemoteWebDriver(
    new Uri("https://hub.testingbot.com/wd/hub/"), desiredCap
  );*/
    @SneakyThrows
    @BeforeClass
    public void setUpBeforeClass() {

        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setBrowserName("chrome");
        capabilities.setVersion("84.0");
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", false);
        capabilities.setCapability(ChromeOptions.CAPABILITY,options);
        driver = new RemoteWebDriver(
                URI.create("http://35.198.124.146:4444/wd/hub").toURL(),
                capabilities
        );

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
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
        if (isLogInNow()){
            signOutByStorage();}
        //System.out.println("@AfterMethod tearDown");
        loggerTest();
    }

    WelcomePage loadApplication() {
        return new WelcomePage(driver);
    }
    //To Do

    /**
     * check sing in status by storage
     * @return
     */
    @Step("verifying that user is not login")
    boolean isLogInNow() {
        new WebDriverWait(driver, 10).until(invisibilityOfElementLocated(By.id("form.sign-in-form")));
        RemoteExecuteMethod executeMethod = new RemoteExecuteMethod((RemoteWebDriver) driver);
        RemoteWebStorage webStorage = new RemoteWebStorage(executeMethod);
        return !((webStorage.getLocalStorage().getItem("name")) == null);
    }

    /**
     * sing out using storage
     * @return
     */
    protected void signOutByStorage(){
        RemoteExecuteMethod executeMethod = new RemoteExecuteMethod((RemoteWebDriver) driver);
        RemoteWebStorage webStorage = new RemoteWebStorage(executeMethod);
        webStorage.getLocalStorage().clear();
        driver.navigate().refresh();
    }
    @Step("download All WebDrivers")
    protected void downloadAllDrivers(){
        WebDriverManager.chromedriver().setup();
        WebDriverManager.firefoxdriver().setup();
        WebDriverManager.edgedriver().setup();
        WebDriverManager.operadriver().setup();
        WebDriverManager.phantomjs().setup();
        WebDriverManager.iedriver().setup();
        WebDriverManager.chromiumdriver().setup();
    }
    public void loggerTest(){
        logger.info("logging from thread " + Thread.currentThread().getId());
        logger.info("\n----------------------------------------------------------------------------\n");
    }
}

