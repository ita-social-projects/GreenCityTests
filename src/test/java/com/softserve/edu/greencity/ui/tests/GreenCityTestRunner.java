package com.softserve.edu.greencity.ui.tests;

import com.softserve.edu.greencity.ui.pages.tipstricks.TipsTricksPage;
import com.softserve.edu.greencity.ui.tools.UIListener;
import com.softserve.edu.greencity.ui.tools.WindowManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

@Listeners(UIListener.class)
public abstract class GreenCityTestRunner {
    public static final String BASE_URL = "https://ita-social-projects.github.io/GreenCityClient/#/welcome";
//    public static final String BASE_URL = "http://localhost:4200/#/welcome";
    public WebDriver getDriver() {
        return driver;
    }

    public static final boolean CHROME_HEADLESS_OPTION = true;
    private final String CHROME_LANGUAGE_OPTION = "en";

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    protected WebDriver driver;

    @BeforeSuite
    public void beforeSuite() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeClass
    public void setUpBeforeClass() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setHeadless(CHROME_HEADLESS_OPTION);
        chromeOptions.addArguments("--window-size=1024,760");
        chromeOptions.addArguments("--lang=" + CHROME_LANGUAGE_OPTION);

        driver = new ChromeDriver(chromeOptions);
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

        System.out.println("@AfterMethod tearDown");
    }

    public TipsTricksPage loadApplication() {
        return new TipsTricksPage(driver);
    }

    public void changeWindowSize(Dimension screenSize) {
        WindowManager winMan = new WindowManager(driver);
        winMan.changeWindowSize(screenSize);
    }

    public boolean isLoginingNow() {
        return driver
                .findElements(By.cssSelector(".sign-up-link .create-button"))
                .size() == 0;
    }
}