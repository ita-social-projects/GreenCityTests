package com.softserve.edu.greencity.ui.pages.common;

import com.softserve.edu.greencity.ui.locators.menu.MainMenuDropdownLocators;
import com.softserve.edu.greencity.ui.tools.engine.StableWebElementSearch;
import com.softserve.edu.greencity.ui.tools.engine.WaitsSwitcher;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainMenuDropdown implements StableWebElementSearch {

    private WebDriver driver;
    protected WebDriverWait wait;

    public MainMenuDropdown(WebDriver driver) {
        this.driver = driver;
        checkElements();
    }
    @Step
    private void checkElements() {
        WaitsSwitcher waitsSwitcher = new WaitsSwitcher(driver);
        waitsSwitcher.setExplicitWait(10, ExpectedConditions.visibilityOf(getMenuEcoNews()));
    }
    @Step
    public WebElement getNaviconButton() {
        return searchElementByCss(MainMenuDropdownLocators.NAVICON_BUTTON.getPath());
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
        return searchElementByCss(MainMenuDropdownLocators.MENU_ECO_NEWS.getPath());
    }
    @Step
    public String getMenuEcoNewsText() {
        return getMenuEcoNews().getText();
    }
    @Step
    public void clickMenuEcoNews() {
        new WebDriverWait(driver, 20)
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector("ul > li:nth-child(1) > a")));
        getMenuEcoNews().click();
    }
    @Step
    public boolean isDisplayedMenuEcoNews() {
        return getMenuEcoNews().isDisplayed();
    }
    @Step
    public WebElement getMenuTipsTricks() {
        return searchElementByCss(MainMenuDropdownLocators.MENU_TIPS_TRICS.getPath());
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
    public WebElement getMenuPlaces() {
        return searchElementByCss(MainMenuDropdownLocators.MENU_PLACES.getPath());
    }
    @Step
    public String getMenuMapText() {
        return getMenuPlaces().getText();
    }
    @Step
    public void clickMenuMap() {
        getMenuPlaces().click();
    }
    @Step
    public boolean isDisplayedMenuMap() {
        return getMenuPlaces().isDisplayed();
    }
    @Step
    public WebElement getMenuMyHabits() {
        if (!isDisplayedMenuMyCabinet()) {
            clickNaviconButton();
        }
        return searchElementByCss(MainMenuDropdownLocators.MENU_MY_HABITS.getPath());
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
        return searchElementByCss(MainMenuDropdownLocators.MENU_ABOUT.getPath());
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
        return searchElementByCss(MainMenuDropdownLocators.FOOTER_ECO_NEWS.getPath());
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
        return searchElementByCss(MainMenuDropdownLocators.FOOTER_TIPS_TRICS.getPath());
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
        return searchElementByCss(MainMenuDropdownLocators.FOOTER_PLACES.getPath());
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
        return searchElementByCss(MainMenuDropdownLocators.FOOTER_MY_HABITS.getPath());
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
        return searchElementByCss(MainMenuDropdownLocators.FOOTER_ABOUT.getPath());
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
