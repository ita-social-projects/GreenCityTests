package com.softserve.edu.greencity.ui.tests;

import com.softserve.edu.greencity.ui.pages.tipstricks.TipsTricksPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public abstract class GreenCityTestRunner {
    private final Long ONE_SECOND_DELAY = 1000L;
    public static final String BASE_URL = "https://ita-social-projects.github.io/GreenCityClient/#/welcome";
    //    	private final String BASE_URL = "http://localhost:4200/#/welcome";

    private final boolean CHROME_HEADLESS_OPTION = false;

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

        driver = new ChromeDriver(chromeOptions);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        //driver.manage().window().setSize(new Dimension(640, 480));
        //driver.manage().window().setSize(new Dimension(480, 640));
    }

    @AfterClass(alwaysRun = true)
    public void tearDownAfterClass() {
        presentationSleep(1);
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
        //driver.get(BASE_URL);

        System.out.println("@AfterMethod tearDown");
    }

    protected void signOut() {
        loadApplication().signOut();
    }

    public TipsTricksPage loadApplication() {
        return new TipsTricksPage(driver);
    }

    // For Presentation ONLY
    protected void presentationSleep() {
        presentationSleep(1);
    }

    protected void presentationSleep(int seconds) {
        try {
            Thread.sleep(seconds * ONE_SECOND_DELAY);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}