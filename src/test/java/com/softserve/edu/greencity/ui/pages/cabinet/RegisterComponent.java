package com.softserve.edu.greencity.ui.pages.cabinet;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.softserve.edu.greencity.ui.pages.common.RegisterPart;
import com.softserve.edu.greencity.ui.tools.GetMail10MinTools;

/**
 * RegisterComponent class
 * @author Serg
 */
public class RegisterComponent extends RegisterPart {

    //
    private WebDriver driver;
    //
    protected WebElement registrationValidator;
    private WebElement lastNameField;
    private WebElement firstNameValidator;
//    private WebElement lastNameValidator; // not exist
    //
    protected String confirmURL = "";
    //
    private final String EMAIL_VALIDATOR_SELECTOR = "//div[@id='validation-error']/div";
    private final String REGISTRATION_VALIDATOR_SELECTOR = "app-sign-up input#email + div";
    private final String FIRST_NAME_VALIDATOR_SELECTOR = "div[class='field-wrapper-left'] div[class='ng-star-inserted']";
//    private final String LAST_NAME_VALIDATOR_SELECTOR = ""; // not exist
    private final String PASSWORD_VALIDATOR_SELECTOR = "div.password-wrapper + div";
//    private final String PASSWORD_CONFIRM_VALIDATOR_SELECTOR = "app-sign-up form div#img-confirm + div[class*='validation-error'] div";
    private final String PASSWORD_CONFIRM_VALIDATOR_SELECTOR = "app-sign-up form div#seterror";
    private final String SUBMIT_EMAIL_SELECTOR = "app-submit-email div.submit-email";

    /**
     * RegisterComponent constructor.
     * @param driver WebDriver
     */
    public RegisterComponent(WebDriver driver) {
        this.driver = driver;
        initElements();
    }

    /**
     * Initialize web elements
     */
    private void initElements() {
        // init elements
        titlePage = driver.getTitle();
        firstNameField = driver.findElement(
                By.cssSelector("form label[for='first-name'] + input"));
        lastNameField = driver.findElement(
                By.cssSelector("form label[for='last-name'] + input"));
        emailField = driver
                .findElement(By.cssSelector("form label[for='email'] + input"));
        passwordField = driver
                .findElement(By.cssSelector("div.password-wrapper input"));
        showPasswordButton = driver
                .findElement(By.cssSelector("div.password-wrapper div"));
        passwordConfirmField = driver.findElement(By.cssSelector(
                "form label[for='password'] + input#password-confirm"));
        showPasswordConfirmButton = driver
                .findElement(By.cssSelector("div.img-confirm"));
        signUpButton = driver.findElement(
                By.xpath("//form[@name='inputform']/button[@type='submit']"));
        signInLink = driver.findElement(By.cssSelector("a[href*='/auth']"));
        googleSignUpButton = driver
                .findElement(By.cssSelector("button[class='google']"));

        this.setEmailField(emailField)
                .setFirstNameField(firstNameField)
                .setGoogleSignUpButton(googleSignUpButton)
                .setPasswordConfirmField(passwordConfirmField)
                .setPasswordField(passwordField)
                .setShowPasswordButton(showPasswordButton)
                .setShowPasswordConfirmButton(showPasswordConfirmButton)
                .setSignInLink(signInLink).setSignUpButton(signUpButton);
    }
    
    @Override
    protected WebElement getTitleField() {
        titleField = driver
                .findElement(By.cssSelector("form[name='inputform'] h1"));
        this.setTitleField(titleField);
        return titleField;
    }

    /**
     * Get verify URL from the post in temp mailbox.
     * @return String
     */
    protected String getVerifyURLText() {
        return this.confirmURL;
    }
    
    // Page Object
//  lastNameField
    /**
     * Returns a WebElement of the 'LastName' field.
     * @return WebElement
     */
    protected WebElement getLastNameField() {
        return lastNameField;
    }

    /**
     * Inserting some text on the 'LastName' field.
     * @param lastName String
     * @return RegisterComponent
     */
    public RegisterComponent inputLastName(String lastName) {
        this.getLastNameField().sendKeys(lastName);
        return this;
    }

    /**
     * Clearing the 'LastName' field.
     * @return RegisterComponent
     */
    public RegisterComponent clearLastName() {
        this.getLastNameField().clear();
        return this;
    }

    /**
     * Click on LastName field.
     * @param driver WebDriver
     * @return RegisterComponent
     */
    protected RegisterComponent clickLastName(WebDriver driver) {
        if (isDisplayedFirstNameField()) {
            this.getLastNameField().click();
            Actions action = new Actions(driver);
            action.contextClick(getLastNameField()).sendKeys(Keys.LEFT)
                    .sendKeys(Keys.RIGHT);
        }
        return this;
    }

    /**
     * Taking a WebElement and set it to a private WebElement.
     * @param lastNameField WebElement
     * @return RegisterComponent
     */
    public RegisterComponent setLastNameField(WebElement lastNameField) {
        this.lastNameField = lastNameField;
        return this;
    }

    /**
     * Returns boolean if displayed the 'LastName' field.
     * @return boolean
     */
    protected boolean isDisplayedLastNameField() {
        return getLastNameField().isDisplayed();
    }
    

//  firstNameValidator
    /**
     * Taking a WebElement and set it to a private WebElement.
     * @param firstNameValidator WebElement
     * @return RegisterPart
     */
    protected RegisterPart setFirstNameValidator(
            WebElement firstNameValidator) {
        this.firstNameValidator = firstNameValidator;
        return this;
    }

    /**
     * Returns a WebElement of the 'FirstNameValidator' field.
     * @return WebElement
     */
    protected WebElement getFirstNameValidator() {
        firstNameValidator = driver
                .findElement(By.cssSelector(FIRST_NAME_VALIDATOR_SELECTOR));
        return firstNameValidator;
    }

    /**
     * Returns a text which displayed on the 'FirstNameValidator' field.
     * @return String
     */
    protected String getFirstNameValidatorText() {
        return getFirstNameValidator().getText();
    }

    /**
     * Returns boolean if displayed the 'FirstNameValidator' field.
     * @return boolean
     */
    protected boolean isDisplayedFirstNameValidator() {
        return getFirstNameValidator().isDisplayed();
    }

    /**
     * Returns boolean result: size element the 'FirstNameValidator' not equal zero.
     * @return boolean
     */
    protected boolean sizeFirstNameValidator() {
        return driver
                .findElements(By.cssSelector(FIRST_NAME_VALIDATOR_SELECTOR))
                .size() != 0;
    }

//  lastNameValidator

//  registrationValidator
    /**
     * Taking a WebElement and set it to a private WebElement.
     * @param registrationValidator WebElement
     * @return RegisterPart
     */
    protected RegisterPart setRegistrationValidator(WebElement registrationValidator) {
        this.registrationValidator = registrationValidator;
        return this;
    }

    /**
     * Returns a WebElement of the 'RegistrationValidator' field.
     * @return WebElement
     */
    protected WebElement getRegistrationValidator() {
        registrationValidator = driver.findElement(By.cssSelector(REGISTRATION_VALIDATOR_SELECTOR));
        return registrationValidator;
    }

    /**
     * Returns a text which displayed on the 'RegistrationValidator' field.
     * @return String
     */
    public String getRegistrationValidatorText() {
        return getRegistrationValidator().getText();
    }

    /**
     * Returns boolean if displayed the 'RegistrationValidator' field.
     * @return boolean
     */
    public boolean isDisplayedRegistrationValidator() {
        return getRegistrationValidator().isDisplayed();
    }

    /**
     * Returns boolean result: size element the 'RegistrationValidator' not equal zero.
     * @return boolean
     */
    protected boolean sizeRegistrationValidator() {
        return driver.findElements(By.cssSelector(REGISTRATION_VALIDATOR_SELECTOR))
                .size() != 0;
    }
    
//  emailValidator
    @Override
    protected String getEmailValidatorText() {
        return getEmailValidator().getText();
    }
    
    @Override
    protected WebElement getEmailValidator() {
        emailValidator = driver.findElement(By.xpath(EMAIL_VALIDATOR_SELECTOR));
        return emailValidator;
    }

    @Override
    protected boolean sizeEmailValidator() {
        return driver.findElements(By.xpath(EMAIL_VALIDATOR_SELECTOR))
                .size() != 0;
    }

//  passwordValidator
    @Override
    protected boolean isDisplayedPasswordValidator() {
        return getPasswordValidator().isDisplayed();
    }
    
    @Override
    protected String getPasswordValidatorText() {
        return getPasswordValidator().getText();
    }
    
    @Override
    protected WebElement getPasswordValidator() {
        passwordValidator = driver
                .findElement(By.cssSelector(PASSWORD_VALIDATOR_SELECTOR));
        return passwordValidator;
    }

    @Override
    protected boolean sizePasswordValidator() {
        return driver.findElements(By.cssSelector(PASSWORD_VALIDATOR_SELECTOR))
                .size() != 0;
    }

//  passwordConfirmValidator
    @Override
    protected boolean isDisplayedPasswordConfirmValidator() {
        return getPasswordConfirmValidator().isDisplayed();
    }
    
    @Override
    protected String getPasswordConfirmValidatorText() {
        return getPasswordConfirmValidator().getText().trim();
    }
    
    @Override
    protected WebElement getPasswordConfirmValidator() {
        passwordConfirmValidator = driver.findElement(
                By.cssSelector(PASSWORD_CONFIRM_VALIDATOR_SELECTOR));
        return passwordConfirmValidator;
    }

    @Override
    protected boolean sizePasswordConfirmValidator() {
        return driver
                .findElements(
                        By.cssSelector(PASSWORD_CONFIRM_VALIDATOR_SELECTOR))
                .size() != 0;
    }

    // Functional
    /**
     * Inserting some text on the 'Email' field.
     * @param email String
     * @return RegisterComponent
     */
    protected RegisterComponent setEmailField(String email) {
        if (isDisplayedEmailField()) {
            clickEmailField(driver);
            clearEmailField();
            inputEmailField(email);
        }
        return this;
    }
    
    /**
     * Inserting some text on the 'FirstName' field.
     * @param firstName String
     * @return RegisterComponent
     */
    protected RegisterComponent setFirstNameField(String firstName) {
        if (isDisplayedFirstNameField()) {
            clickFirstName(driver);
            clearFirstName();
            inputFirstName(firstName);
        }
        return this;
    }
    
    /**
     * Inserting some text on the 'LastName' field.
     * @param lastName String
     * @return RegisterComponent
     */
    protected RegisterComponent setLastNameField(String lastName) {
        if (isDisplayedLastNameField()) {
            clickLastName(driver);
            clearLastName();
            inputLastName(lastName);
        }
        return this;
    }
    
    /**
     * Inserting some text on the 'Password' field.
     * @param password String
     * @return RegisterComponent
     */
    protected RegisterComponent setPasswordField(String password) {
        if (isDisplayedPasswordField()) {
            clickPasswordField(driver);
            clearPasswordField();
            inputPassword(password);
            clickShowPasswordButton();
        }
        return this;
    }
    
    /**
     * Inserting some text on the 'PasswordConfirm' field.
     * @param passwordConfirm String
     * @return RegisterComponent
     */
    protected RegisterComponent setPasswordConfirmField(String passwordConfirm) {
        if (isDisplayedPasswordConfirmField()) {
            clickPasswordConfirmField(driver);
            clearPasswordConfirmField();
            inputPasswordConfirm(passwordConfirm);
            clickShowPasswordConfirmButton();
        }
        return this;
    }
    
    @Override
    protected void switchToAnotherTab(String currentTab) {
        logger.debug("start switchToAnotherTab()");
        for (String current : driver.getWindowHandles()) {
            logger.info("we're in a TAB: " + current);
//            System.out.println("TAB: " + current);
            if (!current.equals(currentTab)) {
                logger.info("and switch to TAB: " + current);
                driver.switchTo().window(current);
                break;
            }
        }
    }
    
    @Override
    protected String getTempEmail() {
        driver.get(GetMail10MinTools.URL);
        GetMail10MinTools tmp = new GetMail10MinTools(driver);
        return tmp.getTempEmail();
    }
    
    @Override
    protected GoogleAccountPage clickSignUpGoogleAccountButton() {
        String currentTab = driver.getWindowHandle();
        clickGoogleLoginButton();
        switchToAnotherTab(currentTab);
        return new GoogleAccountPage(driver);
    }
    
    @Override
    protected RegisterPart clickSignUpButton() {
        if (isDisplayedSignUpButton()) {
            this.getSignUpButton().click();
        }
        return this;
    }
    
    @Override
    protected RegisterPart clickSignInLink() {
        if (isDisplayedSignInLink()) {
            this.getSignInLink().click();
        }
        return this;
    }

    /**
     * Creating LoginPage instance.
     * @return LoginPage
     */
    public LoginPage gotoLoginPage() {
        return new LoginPage(driver);
    }

    @Override
    protected String getTemporaryEmail() {
        String currentTab = driver.getWindowHandle();
        String email = "";
        ((JavascriptExecutor)driver).executeScript("window.open()");
        switchToAnotherTab(currentTab);
        email = getTempEmail();
        logger.info("temporary Email address for registration: " + email);
//        System.out.println("temporary Email address for registration: " + email);
        driver.switchTo().window(currentTab);
        return email;
    }

    @Override
    protected RegisterPart verifyTempEmail() {
        String currentTab = driver.getWindowHandle();
        switchToAnotherTab(currentTab);
        GetMail10MinTools tmp = new GetMail10MinTools(driver);
        tmp.verifyEmail();
        driver.switchTo().window(currentTab);
        return this;
    }
    
    /**
     * Get text which shows after a successful registration.
     * @return String
     */
    protected String getConfirmRegisterationText() {
        logger.debug("start getConfirmRegisterationText()");
        logger.trace("find WebElement submitEmailText");
        submitEmailText = driver.findElement(
                By.cssSelector(SUBMIT_EMAIL_SELECTOR));
        logger.info("get Confirm Registeration text: " + setSubmitEmailText(submitEmailText).getSubmitEmailText());
        return setSubmitEmailText(submitEmailText).getSubmitEmailText();
    }
}
