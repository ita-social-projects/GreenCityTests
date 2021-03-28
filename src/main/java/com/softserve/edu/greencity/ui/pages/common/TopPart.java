package com.softserve.edu.greencity.ui.pages.common;

import com.softserve.edu.greencity.data.Languages;
import com.softserve.edu.greencity.data.users.User;
import com.softserve.edu.greencity.ui.pages.cabinet.GoogleAccountManagerPage;
import com.softserve.edu.greencity.ui.pages.cabinet.LoginComponent;
import com.softserve.edu.greencity.ui.pages.cabinet.MyCabinetPage;
import com.softserve.edu.greencity.ui.pages.cabinet.RegisterComponent;
import com.softserve.edu.greencity.ui.pages.econews.EcoNewsPage;
import com.softserve.edu.greencity.ui.pages.map.MapPage;
import com.softserve.edu.greencity.ui.pages.tipstricks.TipsTricksPage;
import com.softserve.edu.greencity.ui.tools.WindowManager;
import com.softserve.edu.greencity.ui.tools.engine.StableWebElementSearch;
import com.softserve.edu.greencity.ui.tools.engine.WaitsSwitcher;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.qameta.allure.Step;

/**
 * Base Abstract Class of Header and Footer.
 * All page classes should extend this class
 *
 * @author Lv-493.Taqc/Java
 */
public abstract class TopPart implements StableWebElementSearch {
    private final int WINDOW_WIDTH_TO_SCROLL = 1024;
    private final int WINDOW_HEIGHT_TO_CLICK_FOOTER = 480;

    protected final String OPTION_NULL_MESSAGE = "DropdownComponent is null";
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    private Select languageSwitcher;
    private WebElement copyright;
    private MainMenuDropdown mainMenuDropdown;
    private TopGuestComponent topGuestComponent;
    private TopUserComponent topUserComponent;
    private GoogleAccountManagerPage googleAccountManagerPage;

    protected WebDriver driver;
    protected WaitsSwitcher waitsSwitcher;

    public TopPart(WebDriver driver) {
        this.driver = driver;
        this.waitsSwitcher = new WaitsSwitcher(driver, 5, 10);
    }

    public Select getLanguageSwitcher() {
        return languageSwitcher = new Select(driver.findElement(By.cssSelector("select.language-switcher")));
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
        return copyright = driver.findElement(By.cssSelector(".footer_bottom-part"));
    }

    public String getCopyrightText() {
        return getCopyright().getText();
    }

    public void clickCopyright() {
        getCopyright().click();
    }

    public MainMenuDropdown getMainMenuDropdown() {
        return mainMenuDropdown = new MainMenuDropdown(driver);
    }

    public TopGuestComponent getTopGuestComponent() {
        if (topGuestComponent == null) {
            throw new RuntimeException(OPTION_NULL_MESSAGE);
        }
        return topGuestComponent;
    }

    public TopGuestComponent createTopGuestComponent() {
        topGuestComponent = new TopGuestComponent(driver);
        return getTopGuestComponent();
    }

    protected void clickTopGuestSignin() {
        getTopGuestComponent().clickSignInLink();
    }

    protected void clickTopGuestSignup() {
        getTopGuestComponent().clickSignUpLink();
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


    public GoogleAccountManagerPage getGoogleAccountManagerPage() {
        return googleAccountManagerPage = new GoogleAccountManagerPage(driver);
    }

    protected TopUserComponent createTopUserComponent() {
        topUserComponent = new TopUserComponent(driver);
        return getTopUserComponent();
    }

    public String getTopUserName() {
        return createTopUserComponent().getUserNameButtonText();
    }
    /*Removed from UI
    @Step("click Top User Favorite Places")
    protected void clickTopUserFavoritePlaces() {
        getTopUserComponent().clickProfileDropdownFavoritePlaces();
    }
     */
    @Step("click Top User Settings")
    protected void clickTopUserSettings() {
        getTopUserComponent().clickProfileDropdownUserSettings();
    }
    @Step("click Top User Signout")
    protected void clickTopUserSignout() {
        getTopUserComponent().clickProfileDropdownSignout();
    }

    protected void closeTopUserComponent() {
        topUserComponent = null;
    }
    @Step("scroll To Element By Action moveToElement(element)")
    protected void scrollToElementByAction(final WebElement element) {
        final Actions actions = new Actions(driver);
        actions.moveToElement(element);
        actions.build().perform();
    }
    @Step("choose Language")
    protected void chooseLanguage(Languages language) {
        clickLanguageSwitcher();
        setLanguageSwitcher(language.toString());
    }
    @Step("verifying that Menu Clickable")
    protected boolean isMenuClickable() {
        return driver.manage().window().getSize().height > WINDOW_HEIGHT_TO_CLICK_FOOTER;
    }
    @Step("navigate to Menu EcoNews")
    public EcoNewsPage navigateMenuEcoNews() {
        logger.debug("go to EcoNews page");
        logger.trace("click MenuEcoNews link");
        getMainMenuDropdown().clickMenuEcoNews();
        return new EcoNewsPage(driver);
    }
    @Step("navigate to EcoNewsPage when header is minimized")
    public EcoNewsPage navigateMenuEcoNewsMinimized() {
        logger.debug("go to EcoNewsPage");
        logger.trace("redirect via link");
        driver.navigate().to("https://ita-social-projects.github.io/GreenCityClient/#/news");
        return new EcoNewsPage(driver);
    }
    @Step("navigate to Menu TipsTricks")
    public TipsTricksPage navigateMenuTipsTricks() {
        logger.debug("go to TipsTricks page");
        logger.trace("click TipsTricks link");
        getMainMenuDropdown().clickMenuTipsTricks();
        return new TipsTricksPage(driver);
    }
    @Step("navigate Menu Map")
    public MapPage navigateMenuMap() {
        logger.debug("go to Map page");
        logger.trace("click Map link");
        getMainMenuDropdown().clickMenuMap();
        return new MapPage(driver);
    }
    @Step("navigate to Menu MyCabinetGuest")
    public MyCabinetPage navigateMenuMyCabinet() {
        logger.debug("go to MyCabinet");
        logger.trace("click MyCabinet link");
        getMainMenuDropdown().clickMenuMyCabinet();
        return new MyCabinetPage(driver);
    }
    @Step("navigate to Menu MyCabinet Guest")
    public LoginComponent navigateMenuMyCabinetGuest() {
        logger.debug("go to Login Page as Guest");
        logger.trace("click MyCabinet link as Guest");
        logger.info("go to Login Page as a Guest");
        getMainMenuDropdown().clickMenuMyCabinet();
        return new LoginComponent(driver);
    }
    @Step("navigate to Menu About")
    public AboutPage navigateMenuAbout() {
        logger.debug("go to About page");
        logger.trace("click About link");
        getMainMenuDropdown().clickMenuAbout();
        return new AboutPage(driver);
    }
    @Step("signIn")
    public LoginComponent signIn() {
        logger.debug("start signin()");
        logger.trace("click Signin link");
        createTopGuestComponent().clickSignInLink();
        return new LoginComponent(driver);
    }
    @Step("signUp")
    public RegisterComponent signUp() {
        logger.debug("start signup()");
        logger.info("go to RegisterDropdown");
        createTopGuestComponent().clickSignUpLink();
        return new RegisterComponent(driver);
    }
    @Step("signOut")
    public WelcomePage signOut() {
        logger.debug("start signout()");
        logger.trace("click Signout link from ProfileDropdown");
        createTopUserComponent().clickProfileDropdownSignout();
        logger.trace("close TopUserComponent");
        closeTopUserComponent();
        logger.trace("create TopGuestComponent");
        createTopGuestComponent();
        return new WelcomePage(driver);
    }
    @Step("loginIn")
    public MyCabinetPage loginIn(User user) {
        signIn()
                .getManualLoginComponent()
                .successfullyLogin(user);

        return new MyCabinetPage(driver);
    }
    @Step("google Account SignOut")
    public void googleAccountSignOut() {
        getGoogleAccountManagerPage().googleAccountSignOut();
    }

    @Step("change Window Width")
    public void changeWindowWidth(int width) {
        WindowManager windowManager = new WindowManager(driver);
        windowManager.changeWindowWidth(width);
    }
    @Step("change Window Height")
    public void changeWindowHeight(int height) {
        WindowManager windowManager = new WindowManager(driver);
        windowManager.changeWindowHeight(height);
    }
    @Step("set custom window dimensions")
    public void setWindowsDimensions(int width, int height){
        WindowManager windowManager = new WindowManager(driver);
        windowManager.setWindowsDimensions(width,height);
    }

    @Step("maximize Window")
    public void maximizeWindow() {
        WindowManager windowManager = new WindowManager(driver);
        windowManager.maximizeWindow();
    }
    public int getWindowWidth(int width){
        JavascriptExecutor js= (JavascriptExecutor)driver;
        String windowSize = js.executeScript("return (window.outerWidth - window.innerWidth + "+width+"); ").toString();
        width = Integer.parseInt((windowSize));
        return width;
    }
    @Override
    public WebDriver setDriver() {
        return this.driver;
    }
}
