package com.softserve.edu.greencity.ui.pages.common;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.softserve.edu.greencity.ui.pages.cabinet.GoogleAccountPage;

/**
 * RegisterPart class. Includes general types of atomic methods and functionality.
 * @author Serg
 */
public abstract class RegisterPart {
    //
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    //
    protected String titlePage;
    //
    protected WebElement topText; // TODO
    protected WebElement bottomText; // TODO
    protected WebElement titleField;
    //
    protected WebElement firstNameField;
    protected WebElement emailField;
    protected WebElement passwordField;
    protected WebElement showPasswordButton;
    protected WebElement passwordConfirmField;
    protected WebElement showPasswordConfirmButton;
    protected WebElement signUpButton;
    protected WebElement googleSignUpButton;
    protected WebElement signInLink;
    //
    protected WebElement submitEmailText;
    //
    protected WebElement emailValidator;
    protected WebElement passwordValidator;
    protected WebElement passwordConfirmValidator;
    //
    protected String confirmURL = "";
//    ValidatorMessages validatorMessages;

    // Page object

    //
    /**
     * Returns a tab title text.
     * @return
     */
    public String getTitleWebSite() {
        return titlePage;
    }

    // submitEmailText
    /**
     * Taking a WebElement submitEmailText and set it to a private WebElement
     * @param submitEmailText
     * @return RegisterPart
     */
    protected RegisterPart setSubmitEmailText(WebElement submitEmailText) {
        this.submitEmailText = submitEmailText;
        return this;
    }

    /**
     * Returns the text after a successful registration.
     * @return String
     */
    public String getSubmitEmailText() {
        return submitEmailText.getText();
    }

    // titleField
    /**
     * Taking a WebElement and set it to a private WebElement
     * @param titleField WebElement
     * @return RegisterPart
     */
    protected RegisterPart setTitleField(WebElement titleField) {
        this.titleField = titleField;
        return this;
    }

    /**
     * Returns a WebElement of the on the top page.
     * @return WebElement titleField
     */
    protected WebElement getTitleField() {
        return titleField;
    }

    /**
     * Returns a text which displayed on the top of the page.
     * @return String
     */
    protected String getTitleFieldText() {
        return getTitleField().getText().trim();
    }

    /**
     * Returns boolean if displayed the 'Title' field.
     * @return boolean
     */
    protected boolean isDisplayedTitleField() {
        return getTitleField().isDisplayed();
    }

    // first name field
    /**
     * Taking a WebElement and set it to a private WebElement
     * @param firstNameField WebElement
     * @return RegisterPart
     */
    public RegisterPart setFirstNameField(WebElement firstNameField) {
        this.firstNameField = firstNameField;
        return this;
    }

    /**
     * Returns a WebElement of the 'FirstName' field.
     * @return WebElement
     */
    protected WebElement getFirstNameField() {
        return firstNameField;
    }

    /**
     * Inserting some text on the 'FirstName' field.
     * @param String firstName
     * @return RegisterPart
     */
    protected RegisterPart inputFirstName(String firstName) {
        this.getFirstNameField().sendKeys(firstName);
        return this;
    }

    /**
     * Clearing the 'FirstName' field.
     * @return RegisterPart
     */
    protected RegisterPart clearFirstName() {
        this.getFirstNameField().clear();
        return this;
    }

    /**
     * Click on FirstName field
     * @param driver WebDriver
     * @return RegisterPart
     */
    protected RegisterPart clickFirstName(WebDriver driver) {
        if (isDisplayedFirstNameField()) {
            this.getFirstNameField().click();
            Actions action = new Actions(driver);
            action.contextClick(getFirstNameField()).sendKeys(Keys.LEFT)
                    .sendKeys(Keys.RIGHT);
        }
        return this;
    }

    /**
     * Returns boolean if displayed the 'FirstName' field.
     * @return boolean
     */
    protected boolean isDisplayedFirstNameField() {
        return getFirstNameField().isDisplayed();
    }

    // email field
    /**
     * Returns a WebElement of the 'Email' field.
     * @return WebElement
     */
    protected WebElement getEmailField() {
        return emailField;
    }

    /**
     * Inserting some text on the 'Email' field.
     * @param email String
     * @return RegisterPart
     */
    protected RegisterPart inputEmailField(String email) {
        this.getEmailField().sendKeys(email);
        return this;
    }

    /**
     * Clearing the 'Email' field.
     * @return RegisterPart
     */
    protected RegisterPart clearEmailField() {
        this.getEmailField().clear();
        return this;
    }

    /**
     * Click on Email field
     * @param driver WebDriver
     * @return RegisterPart
     */
    public RegisterPart clickEmailField(WebDriver driver) {
        if (isDisplayedEmailField()) {
            this.getEmailField().click();
            Actions action = new Actions(driver);
            action.contextClick(getEmailField()).sendKeys(Keys.LEFT)
                    .sendKeys(Keys.RIGHT);
        }
        return this;
    }

    /**
     * Taking a WebElement and set it to a private WebElement field.
     * @param emailField WebElement
     * @return RegisterPart
     */
    public RegisterPart setEmailField(WebElement emailField) {
        this.emailField = emailField;
        return this;
    }

    /**
     * Returns boolean if displayed the 'Email' field.
     * @return boolean
     */
    protected boolean isDisplayedEmailField() {
        return getEmailField().isDisplayed();
    }

    // password field
    /**
     * Returns a WebElement of the 'Password' field.
     * @return WebElement
     */
    protected WebElement getPasswordField() {
        return passwordField;
    }

    /**
     * Inserting some text on the 'Password' field.
     * @param password String
     * @return RegisterPart
     */
    protected RegisterPart inputPassword(String password) {
        this.getPasswordField().sendKeys(password);
        return this;
    }

    /**
     * Clearing the 'Password' field.
     * @return RegisterPart
     */
    protected RegisterPart clearPasswordField() {
        this.getPasswordField().clear();
        return this;
    }

    /**
     * Click on the 'Password' field.
     * @param driver WebDriver
     * @return RegisterPart
     */
    protected RegisterPart clickPasswordField(WebDriver driver) {
        if (isDisplayedPasswordField()) {
            this.getPasswordField().click();
            Actions action = new Actions(driver);
            action.contextClick(getPasswordField()).sendKeys(Keys.LEFT)
                    .sendKeys(Keys.RIGHT);
        }
        return this;
    }

    /**
     * Taking a WebElement and set it to a private WebElement field.
     * @param passwordField WebElement
     * @return RegisterPart
     */
    public RegisterPart setPasswordField(WebElement passwordField) {
        this.passwordField = passwordField;
        return this;
    }

    /**
     * Returns boolean if displayed the 'Password' field.
     * @return boolean
     */
    protected boolean isDisplayedPasswordField() {
        return getPasswordField().isDisplayed();
    }

    // showPasswordButton
    /**
     * Returns a WebElement of the 'ShowPassword' button.
     * @return WebElement
     */
    protected WebElement getShowPasswordButton() {
        return showPasswordButton;
    }

    /**
     * Click on the 'ShowPassword' button.
     * @return RegisterPart
     */
    protected RegisterPart clickShowPasswordButton() {
        if (isDisplayedShowPasswordButton()) {
            this.getShowPasswordButton().click();
        }
        return this;
    }

    /**
     * Taking a WebElement and set it to a private WebElement field.
     * @param showPasswordButton WebElement
     * @return RegisterPart
     */
    public RegisterPart setShowPasswordButton(WebElement showPasswordButton) {
        this.showPasswordButton = showPasswordButton;
        return this;
    }

    /**
     * Returns boolean if displayed the 'ShowPassword' button.
     * @return boolean
     */
    protected boolean isDisplayedShowPasswordButton() {
        return getShowPasswordButton().isDisplayed();
    }

    // password confirm field
    /**
     * Returns a WebElement of the 'PasswordConfirm' field.
     * @return WebElement
     */
    protected WebElement getPasswordConfirmField() {
        return passwordConfirmField;
    }

    /**
     * Inserting some text on the 'PasswordConfirm' field.
     * @param passwordConfirm String
     * @return RegisterPart
     */
    protected RegisterPart inputPasswordConfirm(String passwordConfirm) {
        this.getPasswordConfirmField().sendKeys(passwordConfirm);
        return this;
    }

    /**
     * Clearing the 'PasswordConfirm' field.
     * @return RegisterPart
     */
    protected RegisterPart clearPasswordConfirmField() {
        this.getPasswordConfirmField().clear();
        return this;
    }

    /**
     * Click on the 'PasswordConfirm' field.
     * @param driver WebDriver
     * @return RegisterPart
     */
    protected RegisterPart clickPasswordConfirmField(WebDriver driver) {
        if (isDisplayedPasswordConfirmField()) {
            this.getPasswordConfirmField().click();
            Actions action = new Actions(driver);
            action.contextClick(getPasswordConfirmField()).sendKeys(Keys.LEFT)
                    .sendKeys(Keys.RIGHT);
        }
        return this;
    }

    /**
     * Taking a WebElement and set it to a private WebElement field.
     * @param passwordConfirmField WebElement
     * @return RegisterPart
     */
    public RegisterPart setPasswordConfirmField(
            WebElement passwordConfirmField) {
        this.passwordConfirmField = passwordConfirmField;
        return this;
    }

    /**
     * Returns boolean if displayed the 'PasswordConfirm' field.
     * @return boolean
     */
    protected boolean isDisplayedPasswordConfirmField() {
        return getPasswordConfirmField().isDisplayed();
    }

    // showPasswordConfirmButton
    /**
     * Returns a WebElement of the 'ShowPasswordConfirm' button.
     * @return WebElement
     */
    protected WebElement getShowPasswordConfirmButton() {
        return showPasswordConfirmButton;
    }

    /**
     * Click on the 'ShowPasswordConfirm' button.
     * @return RegisterPart
     */
    protected RegisterPart clickShowPasswordConfirmButton() {
        if (isDisplayedShowPasswordConfirmButton())
            this.getShowPasswordConfirmButton().click();
        return this;
    }

    /**
     * Taking a WebElement and set it to a private WebElement
     * @param showPasswordConfirmButton
     * @return RegisterPart
     */
    public RegisterPart setShowPasswordConfirmButton(
            WebElement showPasswordConfirmButton) {
        this.showPasswordConfirmButton = showPasswordConfirmButton;
        return this;
    }

    /**
     * Returns boolean if displayed the 'ShowPasswordConfirm' button.
     * @return boolean
     */
    protected boolean isDisplayedShowPasswordConfirmButton() {
        return getShowPasswordConfirmButton().isDisplayed();
    }

    // Sign Up button
    /**
     * Returns a WebElement of the 'SignUp' button.
     * @return WebElement
     */
    protected WebElement getSignUpButton() {
        return signUpButton;
    }

    /**
     * Click on the 'SignUp' button.
     * @return RegisterPart
     */
    protected RegisterPart clickSignUpButton() {
        if (isDisplayedSignUpButton()) {
            this.getSignUpButton().click();
        }
        return this;
    }

    /**
     * Taking a WebElement and set it to a private WebElement field.
     * @param signUpButton WebElement
     * @return RegisterPart
     */
    public RegisterPart setSignUpButton(WebElement signUpButton) {
        this.signUpButton = signUpButton;
        return this;
    }

    /**
     * Returns boolean if displayed the 'SignUp' button.
     * @return boolean
     */
    protected boolean isDisplayedSignUpButton() {
        return getSignUpButton().isDisplayed();
    }

    /**
     * Returns a text which displayed on the 'SignUp' button.
     * @return String
     */
    protected String getSignUpButtonText() {
        return getSignUpButton().getText();
    }

//    public boolean isClickablSignUpButton() {
//        return new WebDriverWait().until(ExpectedConditions.elementToBeClickable(getSignUpButton())) != null;
//    }

    // Sign Up with Google button
    /**
     * Returns a WebElement of the 'GoogleSignUp' button.
     * @return WebElement
     */
    protected WebElement getGoogleSignUpButton() {
        return googleSignUpButton;
    }

    /**
     * Click on the 'GoogleSignUp' button.
     * @return RegisterPart
     */
    protected RegisterPart clickGoogleLoginButton() {
        if (isDisplayedGoogleSignUpButton()) {
            this.getGoogleSignUpButton().click();
        }
        return this;
    }

    /**
     * Taking a WebElement and set it to a private WebElement field.
     * @param googleSignUpButton WebElement
     * @return RegisterPart
     */
    public RegisterPart setGoogleSignUpButton(WebElement googleSignUpButton) {
        this.googleSignUpButton = googleSignUpButton;
        return this;
    }

    /**
     * Returns boolean if displayed the 'GoogleSignUp' button.
     * @return boolean
     */
    protected boolean isDisplayedGoogleSignUpButton() {
        return getGoogleSignUpButton().isDisplayed();
    }

    /**
     * Returns a text which displayed on the 'GoogleSignUp' button.
     * @return String
     */
    protected String getGoogleSignUpButtonText() {
        return getGoogleSignUpButton().getText();
    }

    /**
     * Returns a WebElement of the 'SignIn' link.
     * @return WebElement
     */
    protected WebElement getSignInLink() {
        return signInLink;
    }

    /**
     * Returns a text which displayed on the 'SignIn' link.
     * @return String
     */
    protected String getSignInLinkText() {
        return getSignInLink().getText();
    }

    /**
     * Click on the 'SignIn' link.
     * @return RegisterPart
     */
    protected RegisterPart clickSignInLink() {
        if (isDisplayedSignInLink()) {
            this.getSignInLink().click();
        }
        return this;
    }

    /**
     * Taking a WebElement and set it to a private WebElement field.
     * @param signInLink WebElement
     * @return RegisterPart class
     */
    public RegisterPart setSignInLink(WebElement signInLink) {
        this.signInLink = signInLink;
        return this;
    }

    /**
     * Returns boolean if displayed the 'SignIn' link.
     * @return boolean
     */
    protected boolean isDisplayedSignInLink() {
        return getSignInLink().isDisplayed();
    }

//  emailValidator
    /**
     * Taking a WebElement and set it to a private WebElement field.
     * @param emailValidator WebElement
     * @return RegisterPart
     */
    protected RegisterPart setEmailValidator(WebElement emailValidator) {
        this.emailValidator = emailValidator;
        return this;
    }

    /**
     * Returns a WebElement of the 'emailValidator' message.
     * @return WebElement
     */
    protected abstract WebElement getEmailValidator();

    /**
     * Returns a text which displayed on the 'emailValidator' message.
     * @return String
     */
    protected String getEmailValidatorText() {
        return getEmailValidator().getText().trim();
    }

    /**
     * Returns boolean if displayed the 'emailValidator' message.
     * @return boolean
     */
    protected boolean isDisplayedEmailValidator() {
        return getEmailValidator().isDisplayed();
    }

    /**
     * Verifying if exists EmailValidator message
     * @return boolean
     */
    protected abstract boolean sizeEmailValidator();

//  passwordValidator
    /**
     * Taking a WebElement and set it to a private WebElement field.
     * @param passwordValidator WebElement
     * @return RegisterPart
     */
    protected RegisterPart setPasswordValidator(WebElement passwordValidator) {
        this.passwordValidator = passwordValidator;
        return this;
    }

    /**
     * Returns a WebElement of the 'PasswordValidator' message.
     * @return WebElement
     */
    protected abstract WebElement getPasswordValidator();

    /**
     * Returns a text which displayed on the 'PasswordValidator' message.
     * @return String
     */
    protected String getPasswordValidatorText() {
        return getPasswordValidator().getText().trim();
    }

    /**
     * Returns boolean if displayed the 'PasswordValidator' message.
     * @return boolean
     */
    protected boolean isDisplayedPasswordValidator() {
        return getPasswordValidator().isDisplayed();
    }

    /**
     * Verifying if exists PasswordValidator message
     * @return boolean
     */
    protected abstract boolean sizePasswordValidator();

//  passwordConfirmValidator
    /**
     * Taking a WebElement and set it to a private WebElement field.
     * @param passwordConfirmValidator WebElement
     * @return RegisterPart
     */
    protected RegisterPart setPasswordConfirmValidator(
            WebElement passwordConfirmValidator) {
        this.passwordConfirmValidator = passwordConfirmValidator;
        return this;
    }

    /**
     * Returns a WebElement of the 'PasswordConfirmValidator' message.
     * @return WebElement
     */
    protected abstract WebElement getPasswordConfirmValidator();

    /**
     * Returns a text which displayed on the 'PasswordConfirmValidator' message.
     * @return String
     */
    protected String getPasswordConfirmValidatorText() {
        return getPasswordConfirmValidator().getText().trim();
    }

    /**
     * Returns boolean if displayed the 'PasswordConfirmValidator' message.
     * @return boolean
     */
    protected boolean isDisplayedPasswordConfirmValidator() {
        return getPasswordConfirmValidator().isDisplayed();
    }

    /**
     * Verifying if exists PasswordConfirmValidator message
     * @return boolean
     */
    protected abstract boolean sizePasswordConfirmValidator();

//// Functional

    /**
     * Returns a top title text on the page.
     * @return String
     */
    public String getTitlePageText() {
        if (isDisplayedTitleField()) {
            return getTitleFieldText();
        } else {
            return "title text not found";
        }
    }
    
    /**
     * In the browser switch to another tab.
     * @param currentTab String
     */
    protected abstract void switchToAnotherTab(String currentTab);
    
    /**
     * Load URL in a new tab and get temporary email.
     * @return String
     */
    protected abstract String getTempEmail();

    /**
     * Click SignUp with Google Account button and a Google window opens and
     * switches to it.
     * @return GoogleAccountPage
     */
    protected abstract GoogleAccountPage clickSignUpGoogleAccountButton();

    /**
     * Getting a temporary Email address for registration
     * @return
     */
    protected abstract String getTemporaryEmail();

    /**
     * Verify a temporary Email and close tab.
     */
    protected abstract RegisterPart verifyTempEmail();
    
}
