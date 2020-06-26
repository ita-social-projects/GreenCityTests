package com.softserve.edu.greencity.ui.pages.cabinet;

import com.softserve.edu.greencity.ui.data.User;
import com.softserve.edu.greencity.ui.pages.common.LoginPart;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginManualComponent extends LoginPart {
    private final String EMAIL_ID = "email";
    private final String PASSWORD_ID = "password";
    private final String SIGN_IN_BUTTON_XPATH = "//button[@type='submit' and @class='primary-global-button']";
    private final String GOOGLE_LOGIN_BUTTON_CLASS = "google-sign-in";
    private final String FORGOT_PASSWORD_LINK_CLASS = "forgot-password";
    private final String WRONG_EMAIL_OR_PASS_ERROR_CLASS = ".alert-general-error.ng-star-inserted";

    private WebElement title;
    private WebElement subtitle;
    private WebElement emailField;
    private WebElement passwordField;
    private WebElement signInButton;
    private WebElement singUpLink;
    private WebElement wrongEmailOrPassError;

    private WebDriver driver;
    private WebDriverWait wait;

    public LoginManualComponent(WebDriver driver) {
        this.driver = driver;
    }

    public RegisterComponent goToRegisterPage() {
        getSignUpLink().click();
        return new RegisterComponent(driver);
    }

    private LoginManualComponent setTitle() {
        this.title = driver
                .findElement(By.cssSelector("div.right-side h1"));
        return this;
    }

    protected WebElement getTitle() {
        setTitle();
        return title;
    }

    protected boolean isDisplayedTitleField() {
        return getTitle().isDisplayed();
    }

    public String getTitleString() {
        return this.getTitle().getText();
    }

    protected LoginManualComponent setSubtitle() {
        this.subtitle = driver
                .findElement(By.cssSelector("div.right-side h2"));
        return this;
    }

    protected WebElement getSubtitle() {
        setSubtitle();
        return subtitle;
    }

    public WebElement getEmailField() {
        return emailField = driver.findElement(By.id(EMAIL_ID));
    }

    public WebElement getPasswordField() {
        return passwordField = driver.findElement(By.id(PASSWORD_ID));
    }

    public WebElement getSignInButton() {
        return signInButton = driver.findElement(By.xpath(SIGN_IN_BUTTON_XPATH));
    }

    public String getSubtitleString() {
        return this.getSubtitle().getText();
    }

    public WebElement getWrongEmailOrPassError() {
        wait = new WebDriverWait(driver, 4);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(WRONG_EMAIL_OR_PASS_ERROR_CLASS)));

        return wrongEmailOrPassError = driver.findElement(By.cssSelector(WRONG_EMAIL_OR_PASS_ERROR_CLASS));
    }

    public LoginManualComponent inputEmail(String email) {
        getEmailField().sendKeys(email);
        return this;
    }

    public LoginManualComponent inputPassword(String password) {
        getPasswordField().sendKeys(password);
        return this;
    }

    public LoginManualComponent clickLoginButton() {
        getSignInButton().click();
        return this;
    }

    protected void fillFields(User user) {
        inputEmail(user.getEmail());
        inputPassword(user.getPassword());
    }

    public MyCabinetPage successfullyLogin(User user) {
        fillFields(user);
        clickLoginButton();
        return new MyCabinetPage(driver);
    }

    public LoginManualComponent unsuccessfullyLogin(User user) {
        fillFields(user);
        clickLoginButton();
        return this;
    }
}
