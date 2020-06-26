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
    private final String BASE_URL = "https://ita-social-projects.github.io/GreenCityClient/#/welcome";
    //    	private final String BASE_URL = "http://localhost:4200/#/welcome";
    //
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    protected WebDriver driver;

    @BeforeSuite
    public void beforeSuite() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeClass
    public void setUpBeforeClass() {
        driver = new ChromeDriver(
                //new ChromeOptions().addArguments("headless")
                );
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
            // System.out.println("Test " + result.getName() + " ERROR");
            // Take Screenshot, save sourceCode, save to log, prepare report, Return to previous state, logout, etc.
            // TODO Logout
        }

        driver.get(BASE_URL);

        // logout, get(urlLogout), delete cookie, delete cache
    }

    protected void signOut() {
        driver.get(BASE_URL);
        loadApplication().signout();
    }

    public TipsTricksPage loadApplication() {
        return new TipsTricksPage(driver);
    }

    protected void presentationSleep() {
        presentationSleep(1);
    }

    protected void presentationSleep(int seconds) {
        try {
            Thread.sleep(seconds * ONE_SECOND_DELAY); // For Presentation ONLY
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}