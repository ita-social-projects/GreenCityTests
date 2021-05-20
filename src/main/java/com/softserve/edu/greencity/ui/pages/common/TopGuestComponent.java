package com.softserve.edu.greencity.ui.pages.common;

import com.softserve.edu.greencity.ui.locators.menu.MenuElementsLocators;
import com.softserve.edu.greencity.ui.pages.cabinet.LoginComponent;
import com.softserve.edu.greencity.ui.pages.cabinet.RegisterComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.softserve.edu.greencity.ui.tools.engine.StableWebElementSearch;
import io.qameta.allure.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Header for unsigned in user
 */
public class TopGuestComponent implements StableWebElementSearch {
    private WebDriver driver;

    public TopGuestComponent(WebDriver driver) {
        this.driver = driver;
    }
    private Logger log = LoggerFactory.getLogger(this.getClass());
    //Sign In link
    @Step
    public WebElement getSignInLink() {
        return  searchElementByXpath(MenuElementsLocators.SIGN_IN.getPath());
    }
    @Step
    public boolean isDisplayedSignInLink() {
        return getSignInLink().isDisplayed();
    }
    @Step
    public LoginComponent clickSignInLink() {
        getSignInLink().click();
        return new LoginComponent(driver);
    }

    //Sign Up link
    @Step
    public WebElement getSignUpLink() {
        return searchElementByCss(MenuElementsLocators.SIGN_UP.getPath());
    }
    @Step
    public boolean isDisplayedSignUpLink() {
        return getSignUpLink().isDisplayed();
    }
    @Step
    public String getSignUpLinkText() {
        return getSignUpLink().getText();
    }
    @Step
    public RegisterComponent clickSignUpLink() {
        log.info("Click on Sign up button");
        getSignUpLink().click();
        return new RegisterComponent(driver);
    }

    @Override
    public WebDriver setDriver() {
        return this.driver;
    }
}
