package com.softserve.edu.greencity.ui.pages.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainMenuDropdown {
    private WebDriver driver;

    private WebElement naviconButton;

    private WebElement menuEcoNews;
    private WebElement menuTipsTricks;
    private WebElement menuMap;
    private WebElement menuMyHabits;
    private WebElement menuAbout;

    private WebElement footerEcoNews;
    private WebElement footerTipsTricks;
    private WebElement footerPlaces;
    private WebElement footerMyHabits;
    private WebElement footerAbout;

    private WebDriverWait wait;

    public MainMenuDropdown(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getMenuEcoNews() {
        menuEcoNews = driver.findElement(By.cssSelector(".navigation-menu a[href*='/news']"));
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

    public WebElement getMenuTipsTricks() {
        menuTipsTricks = driver.findElement(By.cssSelector(".navigation-menu a[href*='/welcome']"));
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
        return getMenuTipsTricks().isDisplayed();
    }

    public WebElement getMenuMap() {
        menuMap = driver.findElement(By.cssSelector(".navigation-menu a[href*='/map']"));
        return menuMap;
    }

    public String getMenuMapText() {
        return getMenuMap().getText();
    }

    public void clickMenuMap() {
        getMenuMap().click();
    }

    public boolean isDisplayedMenuMap() {
        return getMenuMap().isDisplayed();
    }


    public WebElement getMenuMyHabits() {
        menuMyHabits = driver.findElement(By.cssSelector(".navigation-menu a[href*='/profile']"));
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

    public WebElement getMenuAbout() {
        menuAbout = driver.findElement(By.cssSelector(".navigation-menu-left a[href*='/about']"));
        return menuAbout;
    }

    public String getMenuAboutText() {
        return getMenuAbout().getText();
    }

    public void clickMenuAbout() {
        getMenuAbout().click();
    }

    public boolean isDisplayedMenuAbout() {
        return getMenuAbout().isDisplayed();
    }

    public WebElement getFooterEcoNews() {
        footerEcoNews = driver.findElement(By.cssSelector("div.app-footer a[href*='/news']"));
        return footerEcoNews;
    }

    public String getFooterEcoNewsText() {
        return getFooterEcoNews().getText();
    }

    public void clickFooterEcoNews() {
        getFooterEcoNews().click();
    }

    public WebElement getFooterTipsTricks() {
        footerTipsTricks = driver.findElement(By.cssSelector(".router-links[href='']"));
        return footerTipsTricks;
    }

    public String getFooterTipsTricksText() {
        return getFooterTipsTricks().getText();
    }

    public void clickFooterTipsTricks() {
        getFooterTipsTricks().click();
    }

    public WebElement getFooterPlaces() {
        footerPlaces = driver.findElement(By.cssSelector(".app-footer a[href*='/map']"));
        return footerPlaces;
    }

    public String getFooterPlacesText() {
        return getFooterPlaces().getText();
    }

    public void clickFooterPlaces() {
        getFooterPlaces().click();
    }

    public WebElement getFooterMyHabits() {
        footerMyHabits = driver.findElement(By.cssSelector(".app-footer a[href*='/profile']"));
        return footerMyHabits;
    }

    public String getFooterMyCabinetText() {
        return getFooterMyHabits().getText();
    }

    public void clickFooterMyCabinet() {
        getFooterMyHabits().click();
    }

    public WebElement getFooterAbout() {
        footerAbout = driver.findElement(By.cssSelector(".app-footer a[href*='/about']"));
        return footerAbout;
    }

    public String getFooterAboutText() {
        return getFooterAbout().getText();
    }

    public void clickFooterAbout() {
        getFooterAbout().click();
    }
}
