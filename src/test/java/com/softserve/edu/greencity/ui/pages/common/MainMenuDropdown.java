package com.softserve.edu.greencity.ui.pages.common;

import com.softserve.edu.greencity.ui.tools.engine.StableWebElementSearch;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainMenuDropdown implements StableWebElementSearch {

    private WebDriver driver;
    protected WebDriverWait wait;
    private By naviconButton = By.cssSelector("div.menu-icon");
    private By menuEcoNews = By.cssSelector("div.navigation-menu-left > ul > li > a[href*='/news']");
    private By menuTipsTricks = By.cssSelector("div.navigation-menu-left > ul > li > a[href*='/tips']");
    private By menuMap = By.cssSelector("div.navigation-menu-left > ul > li > a[href*='/map']");
    private By menuAbout = By.cssSelector("div.navigation-menu-left > ul > li > a[href*='/about']");
    private By menuMyHabits = By.cssSelector(".navigation-menu-left > ul > li > a[href*='/profile']");
    private By footerEcoNews = By.cssSelector("app-footer a[href*='/news']");
    private By footerTipsTricks = By.xpath("//div[@class = 'links']//a[contains(text(),'Tips & Tricks')]");
    private By footerPlaces = By.cssSelector("app-footer a[href*='/map']");
    private By footerMyHabits = By.cssSelector("app-footer a[href*='/profile']");
    private By footerAbout = By.cssSelector("app-footer a[href*='/about']");

    public MainMenuDropdown(WebDriver driver) {
        this.driver = driver;
        checkElements();
    }
    @Step
    private void checkElements() {
        wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(getMenuEcoNews()));
    }
    @Step
    public WebElement getNaviconButton() {
        return searchElementByCss(naviconButton);
    }
    @Step
    public String getNaviconButtonText() {
        return getNaviconButton().getText();
    }
    @Step
    public void clickNaviconButton() {
        if (isDisplayedNaviconButton()) {
            getNaviconButton().click();
        }
    }
    @Step
    public boolean isDisplayedNaviconButton() {
        return getNaviconButton().isDisplayed();
    }
    @Step
    public WebElement getMenuEcoNews() {
        return searchElementByCss(menuEcoNews);
    }
    @Step
    public String getMenuEcoNewsText() {
        return getMenuEcoNews().getText();
    }
    @Step
    public void clickMenuEcoNews() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.navigation-menu-left > ul > li > a[href*='/news']")));
        getMenuEcoNews().click();
    }
    @Step
    public boolean isDisplayedMenuEcoNews() {
        return getMenuEcoNews().isDisplayed();
    }
    @Step
    public WebElement getMenuTipsTricks() {
        return searchElementByCss(menuTipsTricks);
    }
    @Step
    public String getMenuTipsTricksText() {
        return getMenuTipsTricks().getText();
    }
    @Step
    public void clickMenuTipsTricks() {

        getMenuTipsTricks().click();
    }
    @Step
    public boolean isDisplayedMenuTipsTricks() {
        return getMenuTipsTricks().isDisplayed();
    }
    @Step
    public WebElement getMenuMap() {
        return searchElementByCss(menuMap);
    }
    @Step
    public String getMenuMapText() {
        return getMenuMap().getText();
    }
    @Step
    public void clickMenuMap() {
        getMenuMap().click();
    }
    @Step
    public boolean isDisplayedMenuMap() {
        return getMenuMap().isDisplayed();
    }
    @Step
    public WebElement getMenuMyHabits() {
        if (!isDisplayedMenuMyCabinet()) {
            clickNaviconButton();
        }
        return searchElementByCss(menuMyHabits);
    }
    @Step
    public String getMenuMyCabinetText() {
        return getMenuMyHabits().getText();
    }
    @Step
    public void clickMenuMyCabinet() {
        getMenuMyHabits().click();
    }
    @Step
    public boolean isDisplayedMenuMyCabinet() {
        return getMenuMyHabits().isDisplayed();
    }
    @Step
    public WebElement getMenuAbout() {
        return searchElementByCss(menuAbout);
    }
    @Step
    public String getMenuAboutText() {
        return getMenuAbout().getText();
    }
    @Step
    public void clickMenuAbout() {
        getMenuAbout().click();
    }
    @Step
    public boolean isDisplayedMenuAbout() {
        return getMenuAbout().isDisplayed();
    }
    @Step
    public WebElement getFooterEcoNews() {
        return searchElementByCss(footerEcoNews);
    }
    @Step
    public String getFooterEcoNewsText() {
        return getFooterEcoNews().getText();
    }
    @Step
    public void clickFooterEcoNews() {
        getFooterEcoNews().click();
    }
    @Step
    public WebElement getFooterTipsTricks() {
        return searchElementByCss(footerTipsTricks);
    }
    @Step
    public String getFooterTipsTricksText() {
        return getFooterTipsTricks().getText();
    }
    @Step
    public void clickFooterTipsTricks() {
        getFooterTipsTricks().click();
    }
    @Step
    public WebElement getFooterPlaces() {
        return searchElementByCss(footerPlaces);
    }
    @Step
    public String getFooterPlacesText() {
        return getFooterPlaces().getText();
    }
    @Step
    public void clickFooterPlaces() {
        getFooterPlaces().click();
    }
    @Step
    public WebElement getFooterMyHabits() {
        return searchElementByCss(footerMyHabits);
    }
    @Step
    public String getFooterMyCabinetText() {
        return getFooterMyHabits().getText();
    }
    @Step
    public void clickFooterMyCabinet() {
        getFooterMyHabits().click();
    }
    @Step
    public WebElement getFooterAbout() {
        return searchElementByCss(footerAbout);
    }
    @Step
    public String getFooterAboutText() {
        return getFooterAbout().getText();
    }
    @Step
    public void clickFooterAbout() {
        getFooterAbout().click();
    }
    @Step
    public void closeNaviconButton() {
        if (isDisplayedNaviconButton()
                && (isDisplayedMenuEcoNews() || isDisplayedMenuTipsTricks())) {
            clickNaviconButton();
        }
    }

    @Override
    public WebDriver setDriver() {
        return this.driver;
    }

}
