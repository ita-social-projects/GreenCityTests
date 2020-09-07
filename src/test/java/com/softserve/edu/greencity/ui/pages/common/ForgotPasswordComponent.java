package com.softserve.edu.greencity.ui.pages.common;

import com.softserve.edu.greencity.ui.data.User;
import com.softserve.edu.greencity.ui.pages.cabinet.LoginComponent;
import com.softserve.edu.greencity.ui.tools.ElementsCustomMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ForgotPasswordComponent extends TopPart {

    private By title = By.cssSelector("app-restore-password h1");
    private By subTitle = By.cssSelector("app-restore-password h2");

    private By emailField = By.cssSelector("app-restore-password input");
    private By emailValidationError = By.cssSelector(".validation-email-error");
    private By successEmailValidation = By.cssSelector(".successful-email-validation");
    private By unSuccessEmailValidation = By.cssSelector(".alert-email-validation");
    private By submitButton = By.cssSelector(".send-btn");
    private By googleSignInButton = By.cssSelector(".google-sign-in");
    private By backLink = By.cssSelector(".mentioned-password .sign-in-link");
    private By backLinkLabel = By.cssSelector(".mentioned-password p");
    private By closeFormButton = By.cssSelector(".cross-btn");
    protected By forgotPasswordComponent = By.cssSelector(".cdk-overlay-pane");

    protected WebDriverWait wait;

    public ForgotPasswordComponent(WebDriver driver) {
        super(driver);
        checkElements();
    }

    private void checkElements() {
        wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(getForgotTitle()));
    }

    // Page Object
    //Title
    public WebElement getForgotTitle() {
        return driver.findElement(title);
    }

    public String getForgotTitleText() {
        return getForgotTitle().getText();
    }

    //SubTitle
    public WebElement getSubTitle() {
        return driver.findElement(subTitle);
    }

    public String getSubTitleText() {
        return getSubTitle().getText();
    }

    //Email Field
    public WebElement getEmailField() {
        return driver.findElement(emailField);
    }

    public ForgotPasswordComponent pressTabEmail() {
        getEmailField().sendKeys(Keys.TAB);
        return this;
    }

    public boolean isDisplayedEmailField() {
        return getEmailField().isDisplayed();
    }
    public String getEmailPlaceholderText(){
        return getEmailField().getAttribute("placeholder");
    }

    //Email Validator
    public WebElement getEmailValidationError() {
        return driver.findElement(emailValidationError);
    }

    public boolean IsDisplayedEmailValidationError() {
        return getEmailValidationError().isDisplayed();
    }

    public String getEmailValidationErrorText() {
        return getEmailValidationError().getText();
    }

    public boolean isSuccessfulEmailValidation() {
        return driver.findElement(successEmailValidation).isDisplayed();
    }

    public boolean isUnsuccessfulEmailValidation() {
        return driver.findElement(unSuccessEmailValidation).isDisplayed();
    }

    //Submit a login link
    public WebElement getSubmitButton() {
        return driver.findElement(submitButton);
    }

    public void clickSubmitButton() {
        getSubmitButton().click();
    }

    public boolean isDisplayedSubmitButton() {
        return getSubmitButton().isDisplayed();
    }

    //Google Sign In Button
    public WebElement getGoogleSigningButton() {
        return driver.findElement(googleSignInButton);
    }

    public void clickGoogleSigningButton() {
        getGoogleSigningButton().click();
    }

    public boolean isDisplayedGoogleSignInButton() {
        return getGoogleSigningButton().isDisplayed();
    }

    //Back Link
    public WebElement getBackLink() {
        return driver.findElement(backLink);
    }

    public LoginComponent clickBackLink() {
        getBackLink().click();
        return new LoginComponent(driver);
    }

    public boolean isDisplayedBackLink() {
        return getBackLink().isDisplayed();
    }

    public WebElement getBackLinkLabel() {
        return driver.findElement(backLinkLabel);
    }

    public String getBackLinkLabelText() {
        return getBackLinkLabel().getText();
    }

    //Close Sign in popup window
    public WebElement getCloseFormButton() {
        return driver.findElement(closeFormButton);
    }

    public void closeForgotPasswordComponent() {
        getCloseFormButton().click();
    }

    public boolean isDisplayedCloseFormButton() {
        return getCloseFormButton().isDisplayed();
    }

    public boolean isForgotPasswordComponentClosed() {
        closeForgotPasswordComponent();
        ElementsCustomMethods elementsCustomMethods = new ElementsCustomMethods(driver);
        return elementsCustomMethods.waitTillElementGone(driver, forgotPasswordComponent, 6000);
    }

    //Fill email field
    public ForgotPasswordComponent inputEmail(String email) {
        getEmailField().sendKeys(email);
        return this;
    }

    public void fillEmail(User user) {
        inputEmail(user.getEmail());
    }


    public WelcomePage successfullySubmit(User user) {
        fillEmail(user);
        clickSubmitButton();
        return new WelcomePage(driver);
    }

    public ForgotPasswordComponent unsuccessfullySubmit(User user) {
        fillEmail(user);
        clickSubmitButton();
        return this;
    }

    public boolean isForgotPassComponentDisappeared() {
        ElementsCustomMethods elementsCustomMethods = new ElementsCustomMethods(driver);
        return elementsCustomMethods.waitTillElementGone(driver, forgotPasswordComponent, 6000);
    }
}


