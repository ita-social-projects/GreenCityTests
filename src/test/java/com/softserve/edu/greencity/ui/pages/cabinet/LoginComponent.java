package com.softserve.edu.greencity.ui.pages.cabinet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.softserve.edu.greencity.ui.pages.common.TopPart;
import org.openqa.selenium.WebElement;

public class LoginComponent extends TopPart{

    protected WebElement title;
    protected WebElement subtitle;
    protected WebElement forgotPasswordLink;
    protected WebElement singUpLink;

    private ManualLoginComponent manualLoginComponent;
    private final String FORGOT_PASSWORD_LINK_CLASS = "forgot-password";
    private final String REGISTRATION_LINK_CLASS = "signup-link";
    private final String GOOGLE_LOGIN_BUTTON_CLASS = "google-sign-in";

    public LoginComponent(WebDriver driver) {
        super(driver);
    }

    public ManualLoginComponent getManualLoginComponent() {
        return manualLoginComponent = new ManualLoginComponent(driver);
    }

    protected WebElement getTitle() {
        this.title = driver
                .findElement(By.cssSelector("div.right-side h1"));
        return title;
    }

    protected boolean isDisplayedTitleField() {
        return getTitle().isDisplayed();
    }


    public String getTitleString(){

        return this.getTitle().getText();
    }

    protected WebElement getSubtitle() {
        this.subtitle = driver
                .findElement(By.cssSelector("div.right-side h2"));
        return subtitle;
    }


    public String getSubtitleString(){

        return this.getSubtitle().getText();
    }








}
