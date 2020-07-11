package com.softserve.edu.greencity.ui.pages.common;

import com.softserve.edu.greencity.ui.pages.cabinet.LoginComponent;
import com.softserve.edu.greencity.ui.pages.cabinet.RegisterComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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

    public String getSigninLinkText() {
        return getSigninLink().getText();
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

    public RegisterComponent clickSignupLink() {
        getSignupLink().click();
        return new RegisterComponent(driver);
    }
}
