package com.softserve.edu.greencity.ui.pages.cabinet;

import org.openqa.selenium.WebDriver;

import com.softserve.edu.greencity.ui.pages.common.TopPart;
import org.openqa.selenium.WebElement;

public class LoginComponent extends TopPart{

    private ManualLoginComponent manualLoginComponent;
    protected WebElement forgotPasswordLink;
    protected WebElement singUpLink;

    private LoginManualComponent loginManualComponent;
    private final String FORGOT_PASSWORD_LINK_CLASS = "forgot-password";
    private final String REGISTRATION_LINK_CLASS = "signup-link";
    private final String GOOGLE_LOGIN_BUTTON_CLASS = "google-sign-in";

    public LoginComponent(WebDriver driver) {
        super(driver);
        initElements();
    }

    private void initElements() {
        manualLoginComponent = new ManualLoginComponent(driver);
    }

    public ManualLoginComponent getManualLoginComponent() {
        return manualLoginComponent;
    public LoginManualComponent getLoginManualComponent() {
        return loginManualComponent = new LoginManualComponent(driver);
    }

    // proxy methods

    public LoginComponent inputEmail(String email) {
        this.getManualLoginComponent().inputEmail(email);
        return this;
    }

    public LoginComponent inputPassword(String password) {
        this.getManualLoginComponent().inputPassword(password);
        return this;
    }

    public LoginComponent clickLoginButton() {
        this.getManualLoginComponent().clickLoginButton();
        return this;
    }

    public LoginComponent clickGoogleLoginButton() {
        this.getManualLoginComponent().clickGoogleLoginButton();
        return this;
    }

     public GoogleAccountPage loginByGoogle(){
        this.clickGoogleLoginButton();
        return new GoogleAccountPage(driver);
     }



}
