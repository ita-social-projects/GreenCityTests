package com.softserve.edu.greencity.ui.tests;

import com.softserve.edu.greencity.ui.pages.tipstricks.TipsTricksPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

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
//      ChromeOptions options = new ChromeOptions();
//     String chromeProfilePath = System.getenv("USERPROFILE")
//             + "\\AppData\\Local\\Google\\Chrome\\User Data";
//     System.out.println("chromeProfilePath: " + chromeProfilePath);
//     String chromeChooseProfile = "Profile 1";
//     options.addArguments("user-data-dir=" + chromeProfilePath);
//     options.addArguments("profile-directory=" + chromeChooseProfile);
//   driver = new ChromeDriver(options);
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		//driver.manage().window().setSize(new Dimension(640, 480));
		//driver.manage().window().setSize(new Dimension(480, 640));
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
//		driver.manage().window().maximize();
		Thread.sleep(1000); // For Presentation Only
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

	protected void signOut() {
		driver.get("http://localhost:4200/#/welcome");
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