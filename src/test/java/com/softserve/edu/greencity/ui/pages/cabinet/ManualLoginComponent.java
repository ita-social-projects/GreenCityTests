package com.softserve.edu.greencity.ui.pages.cabinet;

import com.softserve.edu.greencity.ui.data.User;
import com.softserve.edu.greencity.ui.tools.engine.StableWebElementSearch;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ManualLoginComponent extends LoginComponent implements StableWebElementSearch {
    private By emailField = By.cssSelector("#email");
    private By passwordField = By.cssSelector("#password");

    private By signInButton = By.xpath("//button[@type='submit' and @class='primary-global-button']");

    private By emailValidationError = By.cssSelector(".validation-email-error");
    private By passwordValidationError = By.cssSelector(".validation-password-error");
    private By wrongEmailOrPassError = By.cssSelector(".alert-general-error.ng-star-inserted");

    private By passwordFieldWrapper = By.cssSelector(".password-input-btn-show-hide");
    private By successEmailValidation = By.cssSelector(".successful-email-validation");
    private By unSuccessEmailValidation = By.cssSelector(".alert-email-validation");
    private By successfulPasswordValidation = By.cssSelector(".successful-password-validation");
    private By unSuccessPasswordValidation = By.cssSelector(".alert-password-validation");

    private WebDriverWait wait;

    public ManualLoginComponent(WebDriver driver) {
        super(driver);
    }

    //Email Field
    @Step
    public WebElement getEmailField() {
        return searchElementByCss(emailField);
    }
    @Step
    public boolean isDisplayedEmailField() {
        return getEmailField().isDisplayed();
    }
    @Step
    public void clickEmailField(){
        getEmailField().click();
    }

    //Password field
    @Step
    public WebElement getPasswordField() {
        return searchElementByCss(passwordField);
    }
    @Step
    public boolean isDisplayedPasswordField() {
        return getPasswordField().isDisplayed();
    }
    @Step
    public WebElement getPasswordFieldWrapper() {
        return searchElementByCss(passwordFieldWrapper);
    }

    //Sign in button
    @Step
    public WebElement getSignInButton() {
        return searchElementByXpath(signInButton);
    }
    @Step
    public boolean isDisplayedSignInButton() {
        return getSignInButton().isDisplayed();
    }
    @Step
    public boolean isEnabledSignInButton() {
        return getSignInButton().isEnabled();
    }
    @Step
    public ManualLoginComponent clickSignInButton() {
        getSignInButton().click();
        return this;
    }

    //Email Or Password Error
    @Step
    public WebElement getWrongEmailOrPassError() {
        wait = new WebDriverWait(driver, 4);
        wait.until(ExpectedConditions.visibilityOfElementLocated(wrongEmailOrPassError));
        return searchElementByCss(wrongEmailOrPassError);
    }
    @Step
    public String getWrongEmailOrPassErrorText() {
        return getWrongEmailOrPassError().getText();
    }


    //Email Validator
    @Step
    public WebElement getEmailValidationError() {
        return searchElementByCss(emailValidationError);
    }
    @Step
    public boolean IsDisplayedEmailValidationError() {
        return getEmailValidationError().isDisplayed();
    }
    @Step
    public boolean isSuccessfulEmailValidation() {
        return searchElementByCss(successEmailValidation).isDisplayed();
    }
    @Step
    public boolean isUnsuccessfulEmailValidation() {
        return searchElementByCss(unSuccessEmailValidation).isDisplayed();
    }

    //Password Validator
    @Step
    public String getEmailValidationErrorText() {
        return getEmailValidationError().getText();
    }
    @Step
    public String getPasswordValidationErrorText() {
        return getPasswordValidationError().getText();
    }
    @Step
    public WebElement getPasswordValidationError() {
        return searchElementByCss(passwordValidationError);
    }
    @Step
    public boolean IsPasswordValidationError() {
        return getPasswordValidationError().isDisplayed();
    }
    @Step
    public boolean isSuccessfulPasswordValidation() {
        return searchElementByCss(successfulPasswordValidation).isDisplayed();
    }
    @Step
    public boolean isUnsuccessfulPasswordValidation() {
        return searchElementByCss(unSuccessPasswordValidation).isDisplayed();
    }

    //Fill email field
    @Step
    public ManualLoginComponent inputEmail(String email) {
        getEmailField().sendKeys(email);
        return this;
    }

    //Fill email field
    @Step
    public ManualLoginComponent inputPassword(String password) {
        getPasswordField().sendKeys(password);
        return this;
    }

    //Fill email and password fields
    @Step
    private void fillFields(User user) {
        inputEmail(user.getEmail());
        inputPassword(user.getPassword());
    }

    //Login???? which page return?
    @Step
    public MyCabinetPage successfullyLogin(User user) {
        fillFields(user);
        clickSignInButton();
        return new MyCabinetPage(driver);
    }
    @Step
    public ManualLoginComponent unsuccessfullyLogin(User user) {
        fillFields(user);
        clickSignInButton();
        return this;
    }

    @Override
    public WebDriver setDriver() {
        return this.driver;
    }
}