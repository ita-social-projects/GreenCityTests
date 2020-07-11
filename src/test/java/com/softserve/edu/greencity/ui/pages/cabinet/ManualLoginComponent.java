package com.softserve.edu.greencity.ui.pages.cabinet;

import com.softserve.edu.greencity.ui.data.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ManualLoginComponent extends LoginComponent {
    private WebElement emailField;
    private WebElement passwordField;
    private WebElement passwordFieldWrapper;
    private WebElement forgotPasswordLink;
    private WebElement signInButton;
    private WebElement wrongEmailOrPassError;
    private WebElement passwordValidator;
    private WebElement emailValidationError;
    private WebElement passwordValidationError;

    private boolean successfulEmailValidation;
    private boolean unsuccessfulEmailValidation;
    private boolean successfulPasswordValidation;
    private boolean unsuccessfulPasswordValidation;


    private final String EMAIL_ID = "email";
    private final String PASSWORD_ID = "password";
    private final String PASSWORD_FIELD_WRAPPER_CLASS = ".password-input-btn-show-hide";

    private final String SIGN_IN_BUTTON_XPATH = "//button[@type='submit' and @class='primary-global-button']";
    private final String WRONG_EMAIL_OR_PASS_ERROR_CLASS = ".alert-general-error.ng-star-inserted";
    private final String FORGOT_PASSWORD_LINK_CLASS = ".forgot-password";
    private final String EMAIL_VALIDATION_ERROR_CLASS = ".validation-email-error";
    private final String PASSWORD_IS_REQUIRED_ERROR_CLASS = ".validation-password-error";
    private final String SUCCESSFUL_EMAIL_VALIDATION_CLASS = ".successful-email-validation";
    private final String UNSUCCESSFUL_EMAIL_VALIDATION_CLASS = ".alert-email-validation";
    private final String SUCCESSFUL_PASSWORD_VALIDATION_CLASS = ".successful-password-validation";
    private final String UNSUCCESSFUL_PASSWORD_VALIDATION_CLASS = ".alert-password-validation";

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

    public WebElement getPasswordFieldWrapper() {
        return passwordFieldWrapper = driver.findElement(By.cssSelector(PASSWORD_FIELD_WRAPPER_CLASS));
    }

    public WebElement getSignInButton() {
        return signInButton = driver.findElement(By.xpath(SIGN_IN_BUTTON_XPATH));
    }

    public WebElement getWrongEmailOrPassError() {
        wait = new WebDriverWait(driver, 4);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(WRONG_EMAIL_OR_PASS_ERROR_CLASS)));

        return wrongEmailOrPassError = driver.findElement(By.cssSelector(WRONG_EMAIL_OR_PASS_ERROR_CLASS));
    }

    public String getWrongEmailOrPassErrorText() {
        return getWrongEmailOrPassError().getText();
    }

    public ManualLoginComponent inputEmail(String email) {
        getEmailField().sendKeys(email);
        return this;
    }

    public ManualLoginComponent inputPassword(String password) {
        getPasswordField().sendKeys(password);
        return this;
    }


    public ManualLoginComponent clickSignInButton() {
        getSignInButton().click();
        return this;
    }

    public WebElement getForgotPasswordLink() {
        return forgotPasswordLink = driver.findElement(By.cssSelector(FORGOT_PASSWORD_LINK_CLASS));
    }

    public WebElement getEmailValidationError() {
        return emailValidationError = driver.findElement(By.cssSelector(EMAIL_VALIDATION_ERROR_CLASS));
    }

    public boolean isSuccessfulEmailValidation() {
        return successfulEmailValidation =
                driver.findElement(By.cssSelector(SUCCESSFUL_EMAIL_VALIDATION_CLASS))
                        .isDisplayed();
    }

    public boolean isUnsuccessfulEmailValidation() {
        return unsuccessfulEmailValidation =
                driver.findElement(By.cssSelector(UNSUCCESSFUL_EMAIL_VALIDATION_CLASS))
                        .isDisplayed();
    }

    public WebElement getPasswordValidationError() {
        return passwordValidationError = driver.findElement(By.cssSelector(PASSWORD_IS_REQUIRED_ERROR_CLASS));
    }

    public boolean isSuccessfulPasswordValidation() {
        return successfulPasswordValidation =
                driver.findElement(By.cssSelector(SUCCESSFUL_PASSWORD_VALIDATION_CLASS))
                        .isDisplayed();
    }

    private void fillFields(User user) {
        inputEmail(user.getEmail());
        inputPassword(user.getPassword());
    }

    public MyCabinetPage successfullyLogin(User user) {
        fillFields(user);
        clickSignInButton();
        return new MyCabinetPage(driver);
    }

    public ManualLoginComponent unsuccessfullyLogin(User user) {
        fillFields(user);
        clickSignInButton();
        return this;
    }

}
