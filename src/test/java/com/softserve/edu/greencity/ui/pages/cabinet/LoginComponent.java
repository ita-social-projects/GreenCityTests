package com.softserve.edu.greencity.ui.pages.cabinet;

import com.softserve.edu.greencity.ui.pages.common.TopPart;
import org.openqa.selenium.WebDriver;

public class LoginComponent extends TopPart {

    private LoginManualComponent loginManualComponent;

    public LoginComponent(WebDriver driver) {
        super(driver);
    }

    public LoginManualComponent getLoginManualComponent() {
        return loginManualComponent = new LoginManualComponent(driver);
    }

    // proxy methods

    public LoginComponent inputEmail(String email) {
        this.getLoginManualComponent().inputEmail(email);
        return this;
    }

    public LoginComponent inputPassword(String password) {
        this.getLoginManualComponent().inputPassword(password);
        return this;
    }

    public LoginComponent clickLoginButton() {
        this.getLoginManualComponent().clickLoginButton();
        return this;
    }

    public LoginComponent clickGoogleLoginButton() {
        this.getLoginManualComponent().clickGoogleLoginButton();
        return this;
    }

    public GoogleAccountPage loginByGoogle() {
        this.clickGoogleLoginButton();
        return new GoogleAccountPage(driver);
    }

    public TopPart login(String email, String password) {
        this.inputEmail(email)
                .inputPassword(password)
                .clickLoginButton();
        return new TopPart(driver) {
        };
    }


    public RegisterComponent gotoRegisterPage() {
        return getLoginManualComponent().goToRegisterPage();
    }
}
