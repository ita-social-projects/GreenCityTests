package com.softserve.edu.greencity.ui.pages.common;

import com.softserve.edu.greencity.ui.data.Languages;
import com.softserve.edu.greencity.ui.data.User;
import com.softserve.edu.greencity.ui.pages.cabinet.LoginComponent;
import com.softserve.edu.greencity.ui.pages.cabinet.ManualRegisterComponent;
import com.softserve.edu.greencity.ui.pages.cabinet.MyCabinetPage;
import com.softserve.edu.greencity.ui.pages.econews.EconewsPage;
import com.softserve.edu.greencity.ui.pages.map.MapPage;
import com.softserve.edu.greencity.ui.pages.tipstricks.TipsTricksPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Base Abstract Class of Header and Footer.
 * @author Lv-493.Taqc/Java
 */
public abstract class TopPart {
    public static final String PROFILE_NAME = "Nadiia Steblivets";

    private final int WINDOW_WIDTH_TO_SCROLL = 1024;
    private final int WINDOW_HEIGHT_TO_CLICK_FOOTER = 480;

    protected final String OPTION_NULL_MESSAGE = "DropdownComponent is null";
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    private Select languageSwitcher;
    private WebElement copyright;
    private MainMenuDropdown mainMenuDropdown;
    private TopGuestComponent topGuestComponent;
    private TopUserComponent topUserComponent;

    protected WebDriver driver;

    public TopPart(WebDriver driver) {
        this.driver = driver;
        initElements();
    }

    private void initElements() {
        languageSwitcher = new Select(driver.findElement(By.cssSelector("select.language-switcher")));
        mainMenuDropdown = new MainMenuDropdown(driver);
        copyright = driver.findElement(By.cssSelector("div.bottom-part"));
    }

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

    public WebElement getCopyright() {
        return copyright;
    }

    public String getCopyrightText() {
        return getCopyright().getText();
    }

    public void clickCopyright() {
        getCopyright().click();
    }

    public MainMenuDropdown getMainMenuDropdown() {
        return mainMenuDropdown;
    }

    protected TopGuestComponent getTopGuestComponent() {
        if (topGuestComponent == null) {
            throw new RuntimeException(OPTION_NULL_MESSAGE);
        }
        return topGuestComponent;
    }

    protected TopGuestComponent createTopGuestComponent() {
        topGuestComponent = new TopGuestComponent(driver);
        return getTopGuestComponent();
    }

    protected void clickTopGuestSignin() {
        getTopGuestComponent().clickSignInLink();
    }

    protected void clickTopGuestSignup() {
        getTopGuestComponent().clickSignupLink();
    }

    protected void closeTopGuestComponent() {
        topGuestComponent = null;
    }

    protected TopUserComponent getTopUserComponent() {
        if (topUserComponent == null) {
            throw new RuntimeException(OPTION_NULL_MESSAGE);
        }
        return topUserComponent;
    }

    protected TopUserComponent createTopUserComponent() {
        topUserComponent = new TopUserComponent(driver);
        return getTopUserComponent();
    }

    public String getTopUserName() {
        return createTopUserComponent().getUserNameButtonText();
    }

    protected void clickTopUserFavoritePlaces() {
        getTopUserComponent().clickProfileDropdownFavoritePlaces();
    }

    protected void clickTopUserSettings() {
        getTopUserComponent().clickProfileDropdownUserSettings();
    }

    protected void clickTopUserSignout() {
        getTopUserComponent().clickProfileDropdownSignout();
    }

    protected void closeTopUserComponent() {
        topUserComponent = null;
    }

    protected void scrollToElementByAction(final WebElement element) {
        final Actions actions = new Actions(driver);
        actions.moveToElement(element);
        actions.build().perform();
    }

    protected void chooseLanguage(Languages language) {
        clickLanguageSwitcher();
        setLanguageSwitcher(language.toString());
    }

    protected boolean isMenuClickable() {
        return driver.manage().window().getSize().height > WINDOW_HEIGHT_TO_CLICK_FOOTER;
    }

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

    public MyCabinetPage navigateMenuMyCabinet() {
        logger.debug("go to MyCabinet");
        logger.trace("click MyCabinet link");
        getMainMenuDropdown().clickMenuMyCabinet();
        return new MyCabinetPage(driver);
    }

    public LoginComponent navigateMenuMyCabinetGuest() {
        logger.debug("go to Login Page as Guest");
        logger.trace("click MyCabinet link as Guest");
        logger.info("go to Login Page as a Guest");
        getMainMenuDropdown().clickMenuMyCabinet();
        return new LoginComponent(driver);
    }

    public AboutPage navigateMenuAbout() {
        logger.debug("go to About page");
        logger.trace("click About link");
        getMainMenuDropdown().clickMenuAbout();
        return new AboutPage(driver);
    }

    public LoginComponent signin() {
        logger.debug("start signin()");
        logger.trace("click Signin link");
        createTopGuestComponent().clickSignInLink();
        return new LoginComponent(driver);
    }

    public ManualRegisterComponent signup() {
        logger.debug("start signup()");
        logger.trace("click Signup link");
        logger.info("go to RegisterDropdown");
        createTopGuestComponent().clickSignupLink();
        return new ManualRegisterComponent(driver);
    }

    public TipsTricksPage signout() {
        logger.debug("start signout()");
        logger.trace("click Signout link from ProfileDropdown");
        createTopUserComponent().clickProfileDropdownSignout();
        logger.trace("close TopUserComponent");
        closeTopUserComponent();
        logger.trace("create TopGuestComponent");
        createTopGuestComponent();
        return new TipsTricksPage(driver);
    }

    public MyCabinetPage loginIn(User user) {
        signin()
                .getManualLoginComponent()
                .successfullyLogin(user);

        return new MyCabinetPage(driver);
    }
}
