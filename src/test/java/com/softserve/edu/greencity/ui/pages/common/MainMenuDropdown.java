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
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.numberOfElementsToBeLessThan(By.cssSelector("form [type='submit'"), 1));
        initElements();
    }

    private void initElements() {
        menuEcoNews = driver.findElement(By.cssSelector(".navigation-menu a[href*='/news']"));
        menuTipsTricks = driver.findElement(By.cssSelector(".navigation-menu a[href*='/welcome']"));
        menuMap = driver.findElement(By.cssSelector(".navigation-menu a[href*='/map']"));
        menuMyHabits = driver.findElement(By.cssSelector(".navigation-menu a[href*='/profile']"));
        menuAbout = driver.findElement(By.cssSelector(".navigation-menu-left a[href*='/about']"));
        //
        footerEcoNews = driver.findElement(By.cssSelector("div.app-footer a[href*='/news']"));
        footerTipsTricks = driver.findElement(By.cssSelector(".router-links[href='']"));
        footerPlaces = driver.findElement(By.cssSelector(".app-footer a[href*='/map']"));
        footerMyHabits = driver.findElement(By.cssSelector(".app-footer a[href*='/profile']"));
        footerAbout = driver.findElement(By.cssSelector(".app-footer a[href*='/about']"));
    }

    // Page Object




    public WebElement getMenuEcoNews() {
        return menuEcoNews;
    }

    public String getMenuEcoNewsText() {
        return getMenuEcoNews().getText();
    }

    public void clickMenuEcoNews() {
        getMenuEcoNews().click();
    }

    public boolean isDisplayedMenuEcoNews() {
        return menuEcoNews.isDisplayed();
    }

    // menuTipsTricks

    public WebElement getMenuTipsTricks() {
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

        return menuMyHabits;
    }

    public String getMenuMyCabinetText() {
        return getMenuMyHabits().getText();
    }

    public void clickMenuMyCabinet() {
        getMenuMyHabits().click();
    }

    public boolean isDisplayedMenuMyCabinet() {

        return menuMyHabits.isDisplayed();
    }

    // menuAbout

    public WebElement getMenuAbout() {

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
    // Business Logic
}
