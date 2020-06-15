package com.softserve.edu.greencity.ui.pages.cabinet;

import com.softserve.edu.greencity.ui.data.User;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

/**
 * RegisterComponent class
 *
 * @author Serg
 */
public class RegisterComponent extends RegisterPage {

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
    private final String EMAIL_VALIDATOR_SELECTOR = "//input[@name='email']/following-sibling::div/div";
    private final String REGISTRATION_VALIDATOR_SELECTOR = "app-sign-up input#email + div";
    private final String FIRST_NAME_VALIDATOR_SELECTOR = "div[class='field-wrapper-left'] div[class='ng-star-inserted']";
    private final String PASSWORD_VALIDATOR_SELECTOR = "//div[@class='main-data-input-password wrong-input']/following-sibling::div/div";
    //    private final String PASSWORD_CONFIRM_VALIDATOR_SELECTOR = "app-sign-up form div#img-confirm + div[class*ss='validation-error'] div";
    private final String PASSWORD_CONFIRM_VALIDATOR_SELECTOR = "//*[@class = 'form-content-container']/div[last()-1]";
    private final String SUBMIT_EMAIL_SELECTOR = "app-submit-email div.submit-email";

    /**
     * RegisterComponent constructor.
     *
     * @param driver WebDriver
     */
    public RegisterComponent(WebDriver driver) {
        super(driver);
        this.driver = driver;
        initElements();
    }

    /**
     * Initialize web elements
     */
    private void initElements() {
        // init elements
        //titlePage = driver.getTitle();
        userNameField = driver
                .findElement(By.cssSelector("input[name='fistName']"));
        emailField = driver
                .findElement(By.cssSelector("input[name='email']"));
        passwordField = driver.findElement(
                By.cssSelector("input[name='form-control password']"));
        showPasswordButton = driver.findElement(
                By.xpath("//input[@name='form-control password']/../span/img"));
        passwordConfirmField = driver.findElement(
                By.cssSelector("input[name='form-control password-confirm']"));
        showPasswordConfirmButton = driver.findElement(By.xpath(
                "//input[@name='form-control password-confirm']/../span/img"));
        signUpButton = driver.findElement(By.cssSelector(
                "div[class='form-content-container'] button[class*='global-button']"));
        signInLink = driver.findElement(By.cssSelector("div.exist-account a"));
        googleSignUpButton = driver.findElement(By.cssSelector(
                "div[class='form-content-container'] button[class*='button-google']"));

        this.setEmailField(emailField)
                .setUserNameField(userNameField)
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
                .findElement(By.cssSelector("h1[title-text]"));
        this.setTitleField(titleField);
        return titleField;
    }


    public String getTitlePageText(){

        return this.getTitleField().getText();
    }
    /**
     * Get verify URL from the post in temp mailbox.
     *
     * @return String
     */
    protected String getVerifyURLText() {
        return this.confirmURL;
    }

    // Page Object
//  lastNameField

    /**
     * Returns a WebElement of the 'LastName' field.
     *
     * @return WebElement
     */
    protected WebElement getLastNameField() {
        return lastNameField;
    }

    /**
     * Inserting some text on the 'LastName' field.
     *
     * @param lastName String
     * @return RegisterComponent
     */
    public RegisterComponent inputLastName(String lastName) {
        this.getLastNameField().sendKeys(lastName);
        return this;
    }

    /**
     * Clearing the 'LastName' field.
     *
     * @return RegisterComponent
     */
    public RegisterComponent clearLastName() {
        this.getLastNameField().clear();
        return this;
    }

    /**
     * Click on LastName field.
     *
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
     *
     * @param lastNameField WebElement
     * @return RegisterComponent
     */
    public RegisterComponent setLastNameField(WebElement lastNameField) {
        this.lastNameField = lastNameField;
        return this;
    }

    /**
     * Returns boolean if displayed the 'LastName' field.
     *
     * @return boolean
     */
    protected boolean isDisplayedLastNameField() {
        return getLastNameField().isDisplayed();
    }


//  firstNameValidator

    /**
     * Taking a WebElement and set it to a private WebElement.
     *
     * @param firstNameValidator WebElement
     * @return RegisterPart
     */
    protected RegisterPage setFirstNameValidator(
            WebElement firstNameValidator) {
        this.firstNameValidator = firstNameValidator;
        return this;
    }

    /**
     * Returns a WebElement of the 'FirstNameValidator' field.
     *
     * @return WebElement
     */
    protected WebElement getFirstNameValidator() {
        firstNameValidator = driver
                .findElement(By.cssSelector(FIRST_NAME_VALIDATOR_SELECTOR));
        return firstNameValidator;
    }

    /**
     * Returns a text which displayed on the 'FirstNameValidator' field.
     *
     * @return String
     */
    public String getFirstNameValidatorText() {
        return getFirstNameValidator().getText();
    }

    /**
     * Returns boolean if displayed the 'FirstNameValidator' field.
     *
     * @return boolean
     */
    protected boolean isDisplayedFirstNameValidator() {
        return getFirstNameValidator().isDisplayed();
    }

    /**
     * Returns boolean result: size element the 'FirstNameValidator' not equal zero.
     *
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
     *
     * @param registrationValidator WebElement
     * @return RegisterPart
     */
    protected RegisterPage setRegistrationValidator(WebElement registrationValidator) {
        this.registrationValidator = registrationValidator;
        return this;
    }

    /**
     * Returns a WebElement of the 'RegistrationValidator' field.
     *
     * @return WebElement
     */
    protected WebElement getRegistrationValidator() {
        registrationValidator = driver.findElement(By.cssSelector(REGISTRATION_VALIDATOR_SELECTOR));
        return registrationValidator;
    }

    /**
     * Returns a text which displayed on the 'RegistrationValidator' field.
     *
     * @return String
     */
    public String getRegistrationValidatorText() {
        return getRegistrationValidator().getText();
    }

    /**
     * Returns boolean if displayed the 'RegistrationValidator' field.
     *
     * @return boolean
     */
    public boolean isDisplayedRegistrationValidator() {
        return getRegistrationValidator().isDisplayed();
    }

    /**
     * Returns boolean result: size element the 'RegistrationValidator' not equal zero.
     *
     * @return boolean
     */
    protected boolean sizeRegistrationValidator() {
        return driver.findElements(By.cssSelector(REGISTRATION_VALIDATOR_SELECTOR))
                .size() != 0;
    }

//  emailValidator

    public String getEmailValidatorText() {
        return getEmailValidator().getText();
    }


    protected WebElement getEmailValidator() {
        emailValidator = driver.findElement(By.xpath(EMAIL_VALIDATOR_SELECTOR));
        return emailValidator;
    }


    protected boolean sizeEmailValidator() {
        return driver.findElements(By.xpath(EMAIL_VALIDATOR_SELECTOR))
                .size() != 0;
    }

//  passwordValidator
    protected boolean isDisplayedPasswordValidator() {
        return getPasswordValidator().isDisplayed();
    }


    public String getPasswordValidatorText() {
        return getPasswordValidator().getText();
    }


    protected WebElement getPasswordValidator() {
        passwordValidator = driver
                .findElement(By.xpath(PASSWORD_VALIDATOR_SELECTOR));
        return passwordValidator;
    }
//
//    @Override
//    protected boolean sizePasswordValidator() {
//        return driver.findElements(By.cssSelector(PASSWORD_VALIDATOR_SELECTOR))
//                .size() != 0;
//    }
//
//  passwordConfirmValidator
    protected boolean isDisplayedPasswordConfirmValidator() {
        return getPasswordConfirmValidator().isDisplayed();
    }

    public String getPasswordConfirmValidatorText() {
        return getPasswordConfirmValidator().getText().trim();
    }

    protected WebElement getPasswordConfirmValidator() {
        passwordConfirmValidator = driver.findElement(
                By.xpath(PASSWORD_CONFIRM_VALIDATOR_SELECTOR));
        return passwordConfirmValidator;
    }
//
//    @Override
//    protected boolean sizePasswordConfirmValidator() {
//        return driver
//                .findElements(
//                        By.cssSelector(PASSWORD_CONFIRM_VALIDATOR_SELECTOR))
//                .size() != 0;
//    }

    // Functional

    /**
     * Inserting some text on the 'Email' field.
     *
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
     *
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
     *
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
     *
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
     *
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

//    @Override
//    protected void switchToAnotherTab(String currentTab) {
//        logger.debug("start switchToAnotherTab()");
//        for (String current : driver.getWindowHandles()) {
//            logger.info("we're in a TAB: " + current);
////            System.out.println("TAB: " + current);
//            if (!current.equals(currentTab)) {
//                logger.info("and switch to TAB: " + current);
//                driver.switchTo().window(current);
//                break;
//            }
//        }
//    }
//
//    @Override
//    protected String getTempEmail() {
//        driver.get(GetMail10MinTools.URL);
//        GetMail10MinTools tmp = new GetMail10MinTools(driver);
//        return tmp.getTempEmail();
//    }
//
//    @Override
//    protected GoogleAccountPage clickSignUpGoogleAccountButton() {
//        String currentTab = driver.getWindowHandle();
//        clickGoogleLoginButton();
//        switchToAnotherTab(currentTab);
//        return new GoogleAccountPage(driver);
//    }
//
    @Override
    public RegisterComponent clickSignUpButton() {
        if (isDisplayedSignUpButton()) {
            this.getSignUpButton().click();
        }
        return this;
    }
//
//    @Override
//    protected RegisterPage clickSignInLink() {
//        if (isDisplayedSignInLink()) {
//            this.getSignInLink().click();
//        }
//        return this;
//    }
//
//    /**
//     * Creating LoginPage instance.
//     * @return LoginPage
//     */
//    public LoginPage gotoLoginPage() {
//        return new LoginPage(driver);
//    }
//
//    @Override
//    protected String getTemporaryEmail() {
//        String currentTab = driver.getWindowHandle();
//        String email = "";
//        ((JavascriptExecutor)driver).executeScript("window.open()");
//        switchToAnotherTab(currentTab);
//        email = getTempEmail();
//        logger.info("temporary Email address for registration: " + email);
////        System.out.println("temporary Email address for registration: " + email);
//        driver.switchTo().window(currentTab);
//        return email;
//    }
//
//    @Override
//    protected RegisterPage verifyTempEmail() {
//        String currentTab = driver.getWindowHandle();
//        switchToAnotherTab(currentTab);
//        GetMail10MinTools tmp = new GetMail10MinTools(driver);
//        tmp.verifyEmail();
//        driver.switchTo().window(currentTab);
//        return this;
//    }

    /**
     * Get text which shows after a successful registration.
     *
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

    /**
     * Test registration user with already wrong credentials.
     * @param userData
     */
    public void registrationWrongUser(User userData) {
        setEmailField(userData.getEmail())
                .setFirstNameField(userData.getFirstName())
                .setPasswordField(userData.getPassword())
                .setPasswordConfirmField(userData.getConfirmPassword())
                .clickSignUpButton();
    }
}
