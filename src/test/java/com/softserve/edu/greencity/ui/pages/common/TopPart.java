package com.softserve.edu.greencity.ui.pages.common;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Coordinates;
import org.openqa.selenium.interactions.Locatable;
import org.openqa.selenium.support.ui.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.softserve.edu.greencity.ui.data.Languages;
import com.softserve.edu.greencity.ui.data.User;
import com.softserve.edu.greencity.ui.pages.cabinet.LoginPage;
import com.softserve.edu.greencity.ui.pages.cabinet.MyCabinetPage;
import com.softserve.edu.greencity.ui.pages.econews.EconewsPage;
import com.softserve.edu.greencity.ui.pages.map.MapPage;
import com.softserve.edu.greencity.ui.pages.tipstricks.TipsTricksPage;

/**
 * Base Abstract Class of Header and Footer.
 *
 * @author Lv-493.Taqc/Java
 */
public abstract class TopPart {
    // TODO to delete
    public static final String PROFILE_NAME = "Nadiia Steblivets";
    //
    private final int WINDOW_WIDTH_TO_SCROLL = 1024;
    private final int WINDOW_HEIGHT_TO_CLICK_FOOTER = 480;
    //
    protected final String OPTION_NULL_MESSAGE = "DropdownComponent is null";
    //protected final String OPTION_NOT_FOUND_MESSAGE = "Option %s not found in %s";
    //protected final String PAGE_DO_NOT_EXIST="Page do not exist!!!";
    //
    //protected final String TAG_ATTRIBUTE_VALUE = "value";
    //protected final String TAG_ATTRIBUTE_SRC = "src";
    //
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    protected WebDriver driver;
    //
    private Select languageSwitcher;
    private WebElement copyright;
    //
    private MainMenuDropdown mainMenuDropdown;
    private TopGuestComponent topGuestComponent;
    private TopUserComponent topUserComponent;

    private JavascriptExecutor javascriptExecutor;

    //
    //private LoginDropdown loginDropdown;
    //private RegisterDropdown registerDropdown;

    public TopPart(WebDriver driver) {
        this.driver = driver;
//        closeAlertIfPresent();
        initElements();
        //initComponents();
    }

    private void closeAlertIfPresent() {
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        //driver.switchTo().alert().accept();
        //Duration duration = Duration.ofSeconds(1);
        Duration duration = Duration.ofMillis(20L);
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(duration)
                .ignoring(TimeoutException.class);
        Alert alert = null;
        try {
            alert = wait.until(ExpectedConditions.alertIsPresent());
        } catch (TimeoutException e) {
        }
        if (alert != null) {
            //driver.switchTo().alert().accept();
            alert.accept();
        }
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    private void initElements() {
        languageSwitcher = new Select(driver.findElement(By.cssSelector("select.language-switcher")));
        mainMenuDropdown = new MainMenuDropdown(driver);
        copyright = driver.findElement(By.cssSelector("div.bottom-part"));
    }

//  private void initComponents() {
    // TODO Develop Application Status Class (Singleton)
//      createTopGuestComponent();
//  }

    // Page Object

    // languageSwitcher

    public Select getLanguageSwitcher() {
        return languageSwitcher;
    }

    public WebElement getLanguageSwitcherWebElement() {
        return getLanguageSwitcher().getWrappedElement();
    }

    public String getLanguageSwitcherText() {
        return getLanguageSwitcher().getFirstSelectedOption().getText();
    }

    protected void setLanguageSwitcher(String text) {
        getLanguageSwitcher().selectByVisibleText(text);
    }

    protected void clickLanguageSwitcher() {
        getLanguageSwitcherWebElement().click();
    }

    // copyright

    public WebElement getCopyright() {
        return copyright;
    }

    public String getCopyrightText() {
        return getCopyright().getText();
    }

    public void clickCopyright() {
        getCopyright().click();
    }

    // mainMenuDropdown

    public MainMenuDropdown getMainMenuDropdown() {
        return mainMenuDropdown;
    }

    // Functional

    // topGuestComponent;

    protected TopGuestComponent getTopGuestComponent() {
        if (topGuestComponent == null) {
            // TODO Develop Custom Exception 
            throw new RuntimeException(OPTION_NULL_MESSAGE);
        }
        return topGuestComponent;
    }

    protected TopGuestComponent createTopGuestComponent() {
        topGuestComponent = new TopGuestComponent(driver);
        return getTopGuestComponent();
    }

    protected void clickTopGuestSignin() {
        getTopGuestComponent().clickSigninLink();
        //topGuestComponent = null;
    }

    protected void clickTopGuestSignup() {
        getTopGuestComponent().clickSignupLink();
        //topGuestComponent = null;
    }

    protected void closeTopGuestComponent() {
        //clickSearchTopField();
        topGuestComponent = null;
    }

    // topUserComponent

    protected TopUserComponent getTopUserComponent() {
        if (topUserComponent == null) {
            // TODO Develop Custom Exception 
            throw new RuntimeException(OPTION_NULL_MESSAGE);
        }
        return topUserComponent;
    }

    protected TopUserComponent createTopUserComponent() {
        topUserComponent = new TopUserComponent(driver);
        return getTopUserComponent();
    }

    public String getTopUserName() {
        // TODO
        //getTopUserComponent().getUserNameButtonText();
        return createTopUserComponent().getUserNameButtonText();
    }

    protected void clickTopUserFavoritePlaces() {
        getTopUserComponent().clickProfileDropdownFavoritePlaces();
        //topGuestComponent = null;
    }

    protected void clickTopUserSettings() {
        getTopUserComponent().clickProfileDropdownUserSettings();
        //topGuestComponent = null;
    }

    protected void clickTopUserSignout() {
        getTopUserComponent().clickProfileDropdownSignout();
        //topGuestComponent = null;
    }

    protected void closeTopUserComponent() {
        //clickSearchTopField();
        topUserComponent = null;
    }

    //TODO same method
    protected void scrollToElementByCoordinates(WebElement element) {
        Coordinates cor = ((Locatable) element).getCoordinates();
        cor.inViewPort();
    }

    // language

    protected void chooseLanguage(Languages language) {
        clickLanguageSwitcher();
        setLanguageSwitcher(language.toString());
    }

    protected boolean isMenuClickable() {
        return driver.manage().window().getSize().height > WINDOW_HEIGHT_TO_CLICK_FOOTER;
    }

    // Business Logic

    public EconewsPage navigateMenuEconews() {
        logger.debug("go to EcoNews page");
        logger.trace("click MenuEcoNews link");
        getMainMenuDropdown().clickMenuEcoNews();
        return new EconewsPage(driver);
    }

    public TipsTricksPage navigateMenuTipsTricks() {
        logger.debug("go to TipsTricks page");
        logger.trace("click TipsTricks link");
        getMainMenuDropdown().clickMenuTipsTricks();
        return new TipsTricksPage(driver);
    }

    public MapPage navigateMenuMap() {
        logger.debug("go to Map page");
        logger.trace("click Map link");
        getMainMenuDropdown().clickMenuMap();
        return new MapPage(driver);
    }

    // for Loggined
    public MyCabinetPage navigateMenuMyCabinet() {
        logger.debug("go to MyCabinet");
        logger.trace("click MyCabinet link");
        getMainMenuDropdown().clickMenuMyCabinet();
        return new MyCabinetPage(driver);
    }

    // for not Loggined
    public MyCabinetPage navigateMenuMyCabinet(User user) {
        logger.debug("go to MyCabinet as User");
        logger.trace("click MyCabinet link as User");
        getMainMenuDropdown().clickMenuMyCabinet();
        new LoginPage(driver).getLoginComponent().fillFieldsSubmit(user);
        return new MyCabinetPage(driver);
    }

    // for not Loggined
    public LoginPage navigateMenuMyCabinetGuest() {
        logger.debug("go to Login Page as Guest");
        logger.trace("click MyCabinet link as Guest");
        logger.info("go to Login Page as a Guest");
        getMainMenuDropdown().clickMenuMyCabinet();
        return new LoginPage(driver);
    }

    public AboutPage navigateMenuAbout() {
        logger.debug("go to About page");
        logger.trace("click About link");
        getMainMenuDropdown().clickMenuAbout();
        return new AboutPage(driver);
    }

    public LoginDropdown signin() {
        logger.debug("start signin()");
        logger.trace("click Signin link");
        createTopGuestComponent().clickSigninLink();
        return new LoginDropdown(driver);
    }

    public RegisterDropdown signup() {
        logger.debug("start signup()");
        // TODO
        //getTopGuestComponent().clickSignupLink();
        logger.trace("click Signup link");
        logger.info("go to RegisterDropdown");
        createTopGuestComponent().clickSignupLink();
        return new RegisterDropdown(driver);
    }

    public TipsTricksPage signout() {
        logger.debug("start signout()");
        // TODO
        //getTopUserComponent().clickProfileDropdownSignout();
        logger.trace("click Signout link from ProfileDropdown");
        createTopUserComponent().clickProfileDropdownSignout();
        logger.trace("close TopUserComponent");
        closeTopUserComponent();
        logger.trace("create TopGuestComponent");
        createTopGuestComponent();
        return new TipsTricksPage(driver);
    }

}
