package com.softserve.edu.greencity.ui.pages.common;

import com.softserve.edu.greencity.ui.data.User;
import com.softserve.edu.greencity.ui.tools.ElementsCustomMethods;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ForgotPasswordComponent {
    private WebDriver driver;

    private By title = By.cssSelector("div.right-side h1");

    private By subTitle = By.cssSelector("div.right-side h2");

    private By emailField = By.id("email");

    private By emailErrorMassage = By.cssSelector(".validation-email-error .ng-star-inserted");

    private By submitButton = By.cssSelector(".send-btn");
    private By googleSignInButton = By.cssSelector(".google-sign-in");
    private By backLink = By.cssSelector(".mentioned-password .sign-in-link");
    private By closeFormButton = By.cssSelector(".cross-btn");
    protected By forgotPasswordComponent = By.cssSelector(".cdk-overlay-pane");

    protected WebDriverWait wait;

    public ForgotPasswordComponent(WebDriver driver) {
        this.driver = driver;
        checkElements();
    }
    @Step
    private void checkElements() {
        wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(getTitle()));
    }

    // Page Object
    //Title
    @Step
    public WebElement getTitle() {
        return driver.findElement(title);
    }
    @Step
    public String getTitleText() {
        return getTitle().getText();
    }

    //SubTitle
    @Step
    public WebElement getSubTitle() {
        return driver.findElement(subTitle);
    }
    @Step
    public String getSubTitleText() {
        return getSubTitle().getText();
    }

    //Email Field
    @Step
    public WebElement getEmailField() {
        return driver.findElement(emailField);
    }
    @Step
    public void pressTabEmail() {
        getEmailField().sendKeys(Keys.TAB);
    }

    //Email Error Massage
    @Step
    public WebElement getEmailErrorMassage() {
        return driver.findElement(emailErrorMassage);
    }
    @Step
    public String getEmailErrorMassageText() {
        return getEmailErrorMassage().getText();
    }

    //Submit a login link
    @Step
    public WebElement getSubmitButton() {
        return driver.findElement(submitButton);
    }
    @Step
    public void clickSubmitButton() {
        getSubmitButton().click();
    }

    //Google Sign In Button
    @Step
    public WebElement getGoogleSigningButton() {
        return driver.findElement(googleSignInButton);
    }
    @Step
    public void clickGoogleSigningButton() {
        getGoogleSigningButton().click();
    }

    //Back Link
    @Step
    public WebElement getBackLink() {
        return driver.findElement(backLink);
    }
    @Step
    public void clickBackLink() {
        getBackLink().click();
    }

    //Close Sign in popup window
    @Step
    public WebElement getCloseFormButton() {
        return driver.findElement(closeFormButton);
    }
    @Step
    public void closeForgotPasswordComponent() {
        getCloseFormButton().click();
    }
    @Step
    public boolean isForgotPasswordComponentClosed() {
        closeForgotPasswordComponent();
        ElementsCustomMethods elementsCustomMethods = new ElementsCustomMethods(driver);
        return elementsCustomMethods.waitTillElementGone(driver, forgotPasswordComponent, 6000);
    }

    //Fill email field
    @Step
    public ForgotPasswordComponent inputEmail(String email) {
        getEmailField().sendKeys(email);
        return this;
    }
    @Step
    private void fillEmail(User user) {
        inputEmail(user.getEmail());
    }
    @Step
    public WelcomePage setForgotPasswordForm(User user) {
        fillEmail(user);
        clickSubmitButton();
        return new WelcomePage(driver);
    }
}
