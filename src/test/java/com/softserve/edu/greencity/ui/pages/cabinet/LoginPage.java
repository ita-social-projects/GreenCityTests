package com.softserve.edu.greencity.ui.pages.cabinet;

import org.openqa.selenium.WebDriver;

import com.softserve.edu.greencity.ui.pages.common.TopPart;

public class LoginPage extends TopPart{

    private LoginComponent loginComponent;

    public LoginPage(WebDriver driver) {
        super(driver);
        initElements();
    }

    private void initElements() {
        loginComponent = new LoginComponent(driver);
    }

    public LoginComponent getLoginComponent() {
        return loginComponent;
    }

    // proxy methods

    public LoginPage inputEmail(String email) {
        this.getLoginComponent().inputEmail(email);
        return this;
    }

    public LoginPage inputPassword(String password) {
        this.getLoginComponent().inputPassword(password);
        return this;
    }

    public LoginPage clickLoginButton() {
        this.getLoginComponent().clickLoginButton();
        return this;
    }

    public LoginPage clickGoogleLoginButton() {
        this.getLoginComponent().clickGoogleLoginButton();
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


    public RegisterPage gotoRegisterPage(){
        return getLoginComponent().gotoRegisterPage();
    }
}
