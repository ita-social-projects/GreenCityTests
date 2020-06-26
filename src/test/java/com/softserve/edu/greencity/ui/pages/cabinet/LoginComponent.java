package com.softserve.edu.greencity.ui.pages.cabinet;

import org.openqa.selenium.WebDriver;

import com.softserve.edu.greencity.ui.pages.common.TopPart;
import org.openqa.selenium.WebElement;

public class LoginComponent extends TopPart{

    private ManualLoginComponent manualLoginComponent;
    protected WebElement forgotPasswordLink;
    protected WebElement singUpLink;

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

    public TopPart login(String email, String password) {
        this.inputEmail(email)
                .inputPassword(password)
                .clickLoginButton();
        return new TopPart(driver) {};
    }


//    public RegisterComponent gotoRegisterPage(){
//        return getManualLoginComponent().gotoRegisterPage();
//    }
//    public RegisterComponent gotoRegisterPage() {
//        getSignUpLink().click();
//        return new RegisterComponent(driver);
//    }

}
