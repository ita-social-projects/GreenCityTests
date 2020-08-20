package com.softserve.edu.greencity.ui.tests;

import com.softserve.edu.greencity.ui.pages.common.WelcomePage;
import com.softserve.edu.greencity.ui.tools.CommandLine;
import com.softserve.edu.greencity.ui.tools.CredentialProperties;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import lombok.SneakyThrows;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteExecuteMethod;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.html5.RemoteWebStorage;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.*;

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
    protected WebDriver driver;

    @BeforeSuite
    @Step
    public void beforeSuite() {
        WebDriverManager.chromedriver().setup();
        new CredentialProperties().checkCredentialsExist();
    }

    @SneakyThrows
    @BeforeClass
    @Step
    public void setUpBeforeClass() {
        DesiredCapabilities capability = DesiredCapabilities.chrome();
        driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capability);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }
    @AfterClass(alwaysRun = true)
    @Step
    public void tearDownAfterClass() {
        if (driver != null) {
            driver.quit();
        }
    }

    @BeforeMethod
    @Step
    public void setUp() {
        driver.get(BASE_URL);
    }

    @AfterMethod
    @Step
    public void tearDown(ITestResult result) {
        if (!result.isSuccess()) {
            logger.warn("Test " + result.getName() + " ERROR");
        }
        if (isLogInNow()){
            signOutByStorage();}
        //System.out.println("@AfterMethod tearDown");
    }
    @Step("loadApplication")
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
    @Step("signOut by clear storage")
    private void signOutByStorage(){
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
}