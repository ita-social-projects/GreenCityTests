package com.softserve.edu.greencity.ui.pages.cabinet;

import com.softserve.edu.greencity.ui.data.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ManualLoginComponent extends LoginComponent{
    private WebElement emailField;
    private WebElement passwordField;
    private WebElement signInButton;
    private WebElement wrongEmailOrPassError;


    private final String EMAIL_ID = "email";
    private final String PASSWORD_ID = "password";
    private final String SIGN_IN_BUTTON_XPATH = "//button[@type='submit' and @class='primary-global-button']";
    private final String WRONG_EMAIL_OR_PASS_ERROR_CLASS = ".alert-general-error.ng-star-inserted";


    private WebDriver driver;
    private WebDriverWait wait;

    public ManualLoginComponent(WebDriver driver) {
        super(driver);
        this.driver = driver;
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


    public WebElement getWrongEmailOrPassError() {
        wait = new WebDriverWait(driver, 4);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(WRONG_EMAIL_OR_PASS_ERROR_CLASS)));

        return wrongEmailOrPassError = driver.findElement(By.cssSelector(WRONG_EMAIL_OR_PASS_ERROR_CLASS));
    }

    public ManualLoginComponent inputEmail(String email) {
        getEmailField().sendKeys(email);
        return this;
    }

    public ManualLoginComponent inputPassword(String password) {
        getPasswordField().sendKeys(password);
        return this;
    }

    public ManualLoginComponent clickLoginButton() {
        getSignInButton().click();
        return this;
    }

    public void fillFields(User user) {
        inputEmail(user.getEmail());
        inputPassword(user.getPassword());
    }

    public MyCabinetPage successfullyLogin(User user) {
        fillFields(user);
        clickLoginButton();
        return new MyCabinetPage(driver);
    }

    public ManualLoginComponent unsuccessfullyLogin(User user) {
        fillFields(user);
        clickLoginButton();
        return this;
    }
}
