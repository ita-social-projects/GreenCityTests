package com.softserve.edu.greencity.ui.pages.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainMenuDropdown {
    private WebDriver driver;
    //
    private WebElement naviconButton;
    //
    //private WebElement menuHome;
    private WebElement menuEcoNews;
    private WebElement menuTipsTricks;
    private WebElement menuMap;
    private WebElement menuMyHabits;
    private WebElement menuAbout;
    //
    private WebElement footerEcoNews;
    private WebElement footerTipsTricks;
    private WebElement footerPlaces;
    private WebElement footerMyHabits;
    private WebElement footerAbout;

    private WebDriverWait wait;

    public MainMenuDropdown(WebDriver driver) {
        this.driver = driver;
        initElements();
    }

    private void initElements() {
        // init elements
        naviconButton = driver.findElement(By.cssSelector("div.menu-icon"));
        //menuHome = driver.findElement(By.cssSelector("ul.menu > li:first-child > a[href*='/welcome']"));
        menuEcoNews = driver.findElement(By.cssSelector("div.navigation-menu-left > ul > li > a[href*='/news']"));
        menuTipsTricks = driver.findElement(By.cssSelector("div.navigation-menu-left > ul > li > a[href*='/welcome']"));
        menuMap = driver.findElement(By.cssSelector("div.navigation-menu-left > ul > li > a[href*='/map']"));
        menuMyHabits = driver.findElement(By.cssSelector(".navigation-menu-left > ul > li > a[href*='/profile']"));
        menuAbout = driver.findElement(By.cssSelector("div.navigation-menu-left > ul > li > a[href*='/about']"));
        //
        footerEcoNews = driver.findElement(By.cssSelector("div.app-footer a[href*='/news']"));
        footerTipsTricks = driver.findElement(By.xpath("//div[@class='app-footer']//a[contains(@href, '/news')]/../following-sibling::li/a[@href='']"));
        footerPlaces = driver.findElement(By.cssSelector("div.app-footer a[href*='/map']"));
        footerMyHabits = driver.findElement(By.cssSelector(".app-footer a[href*='/profile']"));
        footerAbout = driver.findElement(By.cssSelector("div.app-footer a[href*='/about']"));
    }

    // Page Object

    // naviconButton

    public WebElement getNaviconButton() {
        return naviconButton;
    }

    public String getNaviconButtonText() {
        return getNaviconButton().getText();
    }

    public void clickNaviconButton() {
        if (isDisplayedNaviconButton()) {
            getNaviconButton().click();
        }
    }

    public boolean isDisplayedNaviconButton() {
        return getNaviconButton().isDisplayed();
    }

    public WebElement getMenuEcoNews() {
        if (!isDisplayedMenuEcoNews()) {
            clickNaviconButton();
        }
        return menuEcoNews;
    }

    public String getMenuEcoNewsText() {
        return getMenuEcoNews().getText();
    }

    public void clickMenuEcoNews() {
        getMenuEcoNews().click();
    }

    public boolean isDisplayedMenuEcoNews() {
        //return getMenuEcoNews().isDisplayed();
        return menuEcoNews.isDisplayed();
    }

    // menuTipsTricks

    public WebElement getMenuTipsTricks() {
        if (!isDisplayedMenuTipsTricks()) {
            clickNaviconButton();
        }

        return menuTipsTricks;
    }

    public String getMenuTipsTricksText() {
        wait = new WebDriverWait(driver, 3);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.navigation-menu-left > ul > li > a[href*='/welcome']")));

        return getMenuTipsTricks().getText();
    }

    public void clickMenuTipsTricks() {

        getMenuTipsTricks().click();
    }

    public boolean isDisplayedMenuTipsTricks() {
        //return getMenuTipsTricks().isDisplayed();
        return menuTipsTricks.isDisplayed();
    }

    // menuMap

    public WebElement getMenuMap() {
        if (!isDisplayedMenuMap()) {
            clickNaviconButton();
        }
        return menuMap;
    }

    public String getMenuMapText() {
        return getMenuMap().getText();
    }

    public void clickMenuMap() {
        getMenuMap().click();
    }

    public boolean isDisplayedMenuMap() {
        //return getMenuMap().isDisplayed();
        return menuMap.isDisplayed();
    }

    // menuMyCabinet

    public WebElement getMenuMyHabits() {
        if (!isDisplayedMenuMyCabinet()) {
            clickNaviconButton();
        }
        return menuMyHabits;
    }

    public String getMenuMyCabinetText() {
        return getMenuMyHabits().getText();
    }

    public void clickMenuMyCabinet() {
        getMenuMyHabits().click();
    }

    public boolean isDisplayedMenuMyCabinet() {
        //return getMenuMycabinet().isDisplayed();
        return menuMyHabits.isDisplayed();
    }

    // menuAbout

    public WebElement getMenuAbout() {
        if (!isDisplayedMenuAbout()) {
            clickNaviconButton();
        }
        return menuAbout;
    }

    public String getMenuAboutText() {
        return getMenuAbout().getText();
    }

    public void clickMenuAbout() {
        getMenuAbout().click();
    }

    public boolean isDisplayedMenuAbout() {
        //return getMenuAbout().isDisplayed();
        return menuAbout.isDisplayed();
    }

    // footerEcoNews

    public WebElement getFooterEcoNews() {
        return footerEcoNews;
    }

    public String getFooterEcoNewsText() {
        return getFooterEcoNews().getText();
    }

    public void clickFooterEcoNews() {
        getFooterEcoNews().click();
    }

    // footerTipsTricks

    public WebElement getFooterTipsTricks() {
        return footerTipsTricks;
    }

    public String getFooterTipsTricksText() {
        return getFooterTipsTricks().getText();
    }

    public void clickFooterTipsTricks() {
        getFooterTipsTricks().click();
    }

    // footerMap

    public WebElement getFooterPlaces() {
        return footerPlaces;
    }

    public String getFooterPlacesText() {
        return getFooterPlaces().getText();
    }

    public void clickFooterPlaces() {
        getFooterPlaces().click();
    }

    // footerMyCabinet

    public WebElement getFooterMyHabits() {
        return footerMyHabits;
    }

    public String getFooterMyCabinetText() {
        return getFooterMyHabits().getText();
    }

    public void clickFooterMyCabinet() {
        getFooterMyHabits().click();
    }

    // footerAbout

    public WebElement getFooterAbout() {
        return footerAbout;
    }

    public String getFooterAboutText() {
        return getFooterAbout().getText();
    }

    public void clickFooterAbout() {
        getFooterAbout().click();
    }

    // Functional

    public void closeNaviconButton() {
        if (isDisplayedNaviconButton()
                && (isDisplayedMenuEcoNews() || isDisplayedMenuTipsTricks())) {
            clickNaviconButton();
        }
    }

    // Business Logic
}
