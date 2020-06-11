package com.softserve.edu.greencity.ui.tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.softserve.edu.greencity.ui.pages.tipstricks.TipsTricksPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public abstract class GreenCityTestRunner {
    private final Long ONE_SECOND_DELAY = 1000L;
    //
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    protected WebDriver driver;

    @BeforeSuite
    public void beforeSuite() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeClass
    public void setUpBeforeClass() throws Exception {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @AfterClass(alwaysRun = true)
    public void tearDownAfterClass() throws Exception {
        presentationSleep(1);
        if (driver != null) {
            driver.quit();
        }
    }

    @BeforeMethod
    public void setUp() throws Exception {
//		driver.get("https://ita-social-projects.github.io/GreenCityClient/#/welcome");
        driver.get("http://localhost:4200/#/welcome");
    }

    @AfterMethod
    public void tearDown(ITestResult result) throws Exception {
        if (!result.isSuccess()) {
            logger.warn("Test " + result.getName() + " ERROR");
            // System.out.println("Test " + result.getName() + " ERROR");
            // Take Screenshot, save sourceCode, save to log, prepare report, Return to previous state, logout, etc.
            // TODO Logout
            //driver.get("https://ita-social-projects.github.io/GreenCityClient/#/welcome");
        }
        // logout, get(urlLogout), delete cookie, delete cache
    }

    public TipsTricksPage loadApplication() {
        return new TipsTricksPage(driver);
        //return new TipsTricksPage(getDriver());
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