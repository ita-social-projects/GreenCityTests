package com.softserve.edu.greencity.ui.pages.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.softserve.edu.greencity.ui.pages.myhabits.LoginComponent;
import com.softserve.edu.greencity.ui.pages.myhabits.RegisterComponent;

public class TopGuestComponent {
    private WebDriver driver;
    private WebElement signinLink;
    private WebElement signupLink;

    public TopGuestComponent(WebDriver driver) {
        this.driver = driver;
    }


    public WebElement getSigninLink() {
        return signinLink = driver.findElement(By.cssSelector("li.sign-in-link.tertiary-global-button a"));
    }


    public LoginComponent clickSignInLink() {
        getSigninLink().click();
        return new LoginComponent(driver);
    }

    public WebElement getSignupLink() {
        return signupLink = driver.findElement(By.cssSelector("li.sign-up-link.ng-star-inserted div"));
    }

    public String getSignupLinkText() {
        return getSignupLink().getText();
    }

    public RegisterComponent clickSignUpLink() {
        getSignupLink().click();
        return new RegisterComponent(driver);
    }
}
