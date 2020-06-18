package com.softserve.edu.greencity.ui.pages.cabinet;

import com.softserve.edu.greencity.ui.data.User;
import com.softserve.edu.greencity.ui.tools.GetMail10MinTools;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;


public class ManualRegisterComponent extends RegisterComponent {

    private WebDriver driver;
    private WebElement userNameField;
    private WebElement emailField;
    private WebElement passwordField;
    private WebElement showPasswordButton;
    private WebElement passwordConfirmField;
    private WebElement showPasswordConfirmButton;
    private WebElement signUpButton;

    private WebElement emailValidator;
    private WebElement passwordValidator;
    private WebElement passwordConfirmValidator;
    private WebElement registrationValidator;
    private WebElement firstNameValidator;
    private String confirmURL = "";

    private final String USER_NAME_FIELD_SELECTOR = "input[name='fistName']";
    private final String EMAIL_FIELD_SELECTOR = "input[name='email']";
    private final String PASSWORD_FIELD_SELECTOR = "input[name='form-control password']";
    private final String SHOW_PASSWORD_BUTTON_SELECTOR = "//input[@name='form-control password']/../span/img";
    private final String PASSWORD_CONFIRM_FIELD_SELECTOR = "input[name='form-control password-confirm']";
    private final String SHOW_PASSWORD_CONFIRM_BUTTON_SELECTOR = "//input[@name='form-control password-confirm']/../span/img";
    private final String SIGNUP_BUTTON_SELECTOR = "div[class='form-content-container'] button[class*='global-button']";


    private final String EMAIL_VALIDATOR_SELECTOR = "//input[@name='email']/following-sibling::div/div";
    private final String REGISTRATION_VALIDATOR_SELECTOR = "app-sign-up input#email + div";
    private final String FIRST_NAME_VALIDATOR_SELECTOR = "//input[@name='fistName']/following-sibling::div/div";
    private final String PASSWORD_VALIDATOR_SELECTOR = "//div[@class='main-data-input-password wrong-input']/following-sibling::div/div";
    private final String PASSWORD_CONFIRM_VALIDATOR_SELECTOR = "//*[@class = 'form-content-container']/div[last()-1]";


    /**
     * ManualRegisterComponent constructor.
     *
     * @param driver WebDriver
     */
    public ManualRegisterComponent(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }


    // user name field

      protected WebElement getUserNameField() {

        this.userNameField = driver
                .findElement(By.cssSelector(USER_NAME_FIELD_SELECTOR));
        return userNameField;
    }

    /**
     * Inserting some values in the 'FirstName' field.
     *
     * @param userName
     * @return RegisterPart
     */
    protected ManualRegisterComponent inputUserName(String userName) {
        this.getUserNameField().sendKeys(userName);
        return this;
    }

    /**
     * Clearing the 'FirstName' field.
     *
     * @return RegisterPart
     */
    protected ManualRegisterComponent clearFirstName() {
        this.getUserNameField().clear();
        return this;
    }

    /**
     * Click on FirstName field
     *
     * @param driver WebDriver
     * @return RegisterPart
     */
    protected ManualRegisterComponent clickFirstName(WebDriver driver) {
        if (isDisplayedFirstNameField()) {
            this.getUserNameField().click();
            Actions action = new Actions(driver);
            action.contextClick(getUserNameField()).sendKeys(Keys.LEFT)
                    .sendKeys(Keys.RIGHT);
        }
        return this;
    }

    /**
     * Returns boolean if displayed the 'FirstName' field.
     *
     * @return boolean
     */
    protected boolean isDisplayedFirstNameField() {
        return getUserNameField().isDisplayed();
    }

//  firstNameValidator

    /**
     * Taking a WebElement and set it to a private WebElement.
     *
     * @param firstNameValidator WebElement
     * @return RegisterPart
     */
    protected RegisterComponent setFirstNameValidator(
            WebElement firstNameValidator) {
        this.firstNameValidator = firstNameValidator;
        return this;
    }

    /**
     * Returns a WebElement of the 'FirstNameValidator' field.
     *
     * @return WebElement
     */
    protected WebElement getUserNameValidator() {
        firstNameValidator = driver
                .findElement(By.xpath(FIRST_NAME_VALIDATOR_SELECTOR));
        return firstNameValidator;
    }

    /**
     * Returns a text which displayed on the 'FirstNameValidator' field.
     *
     * @return String
     */
    public String getUserNameValidatorText() {
        return getUserNameValidator().getText();
    }

    /**
     * Returns boolean if displayed the 'FirstNameValidator' field.
     *
     * @return boolean
     */
    protected boolean isDisplayedFirstNameValidator() {
        return getUserNameValidator().isDisplayed();
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


//  registrationValidator

    /**
     * Taking a WebElement and set it to a private WebElement.
     *
     * @param registrationValidator WebElement
     * @return RegisterPart
     */
    protected RegisterComponent setRegistrationValidator(WebElement registrationValidator) {
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

    // email field

    /**
     * Returns a WebElement of the 'Email' field.
     *
     * @return WebElement
     */
    protected WebElement getEmailField() {
        this.emailField = driver
                .findElement(By.cssSelector(EMAIL_FIELD_SELECTOR));
    return emailField;
    }

    /**
     * Inserting some text on the 'Email' field.
     *
     * @param email String
     * @return RegisterPart
     */
    protected RegisterComponent inputEmailField(String email) {
        this.getEmailField().sendKeys(email);
        return this;
    }

    /**
     * Clearing the 'Email' field.
     *
     * @return RegisterPart
     */
    protected RegisterComponent clearEmailField() {
        this.getEmailField().clear();
        return this;
    }

    /**
     * Click on Email field
     *
     * @param driver WebDriver
     * @return RegisterPart
     */
    public RegisterComponent clickEmailField(WebDriver driver) {
        if (isDisplayedEmailField()) {
            this.getEmailField().click();
            Actions action = new Actions(driver);
            action.contextClick(getEmailField()).sendKeys(Keys.LEFT)
                    .sendKeys(Keys.RIGHT);
        }
        return this;
    }

    /**
     * Returns boolean if displayed the 'Email' field.
     *
     * @return boolean
     */
    protected boolean isDisplayedEmailField() {
        return getEmailField().isDisplayed();
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

    // password field

    /**
     * Returns a WebElement of the 'Password' field.
     *
     * @return WebElement
     */
    protected WebElement getPasswordField() {
        this.passwordField = driver
                .findElement(By.cssSelector(PASSWORD_FIELD_SELECTOR));
                return passwordField;
    }

    /**
     * Inserting some text on the 'Password' field.
     *
     * @param password String
     * @return RegisterPart
     */
    protected RegisterComponent inputPassword(String password) {
        this.getPasswordField().sendKeys(password);
        return this;
    }

    /**
     * Clearing the 'Password' field.
     *
     * @return RegisterPart
     */
    protected RegisterComponent clearPasswordField() {
        this.getPasswordField().clear();
        return this;
    }

    /**
     * Click on the 'Password' field.
     *
     * @param driver WebDriver
     * @return RegisterPart
     */
    protected RegisterComponent clickPasswordField(WebDriver driver) {
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
     *
     * @param passwordField WebElement
     * @return RegisterPart
     */
    public ManualRegisterComponent setPasswordField(WebElement passwordField) {
        this.passwordField = passwordField;
        return this;
    }

    /**
     * Returns boolean if displayed the 'Password' field.
     *
     * @return boolean
     */
    protected boolean isDisplayedPasswordField() {
        return getPasswordField().isDisplayed();
    }

    // showPasswordButton

    /**
     * Returns a WebElement of the 'ShowPassword' button.
     *
     * @return WebElement
     */
    protected WebElement getShowPasswordButton() {
        this.showPasswordButton = driver
                .findElement(By.xpath(SHOW_PASSWORD_BUTTON_SELECTOR));
        return showPasswordButton;
    }

    /**
     * Click on the 'ShowPassword' button.
     *
     * @return RegisterPart
     */
    protected RegisterComponent clickShowPasswordButton() {
        if (isDisplayedShowPasswordButton()) {
            this.getShowPasswordButton().click();
        }
        return this;
    }

    /**
     * Taking a WebElement and set it to a private WebElement field.
     *
     * @param showPasswordButton WebElement
     * @return RegisterPart
     */
    public ManualRegisterComponent setShowPasswordButton(WebElement showPasswordButton) {
        this.showPasswordButton = showPasswordButton;
        return this;
    }

    /**
     * Returns boolean if displayed the 'ShowPassword' button.
     *
     * @return boolean
     */
    protected boolean isDisplayedShowPasswordButton() {
        return getShowPasswordButton().isDisplayed();
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
    // password confirm field

    /**
     * Returns a WebElement of the 'PasswordConfirm' field.
     *
     * @return WebElement
     */
    protected WebElement getPasswordConfirmField() {

        this.passwordConfirmField = driver
                .findElement(By.cssSelector(PASSWORD_CONFIRM_FIELD_SELECTOR));
    return passwordConfirmField;
    }

    /**
     * Inserting some text on the 'PasswordConfirm' field.
     *
     * @param passwordConfirm String
     * @return RegisterPart
     */
    protected RegisterComponent inputPasswordConfirm(String passwordConfirm) {
        this.getPasswordConfirmField().sendKeys(passwordConfirm);
        return this;
    }

    /**
     * Clearing the 'PasswordConfirm' field.
     *
     * @return RegisterPart
     */
    protected RegisterComponent clearPasswordConfirmField() {
        this.getPasswordConfirmField().clear();
        return this;
    }

    /**
     * Click on the 'PasswordConfirm' field.
     *
     * @param driver WebDriver
     * @return RegisterPart
     */
    protected RegisterComponent clickPasswordConfirmField(WebDriver driver) {
        if (isDisplayedPasswordConfirmField()) {
            this.getPasswordConfirmField().click();
            Actions action = new Actions(driver);
            action.contextClick(getPasswordConfirmField()).sendKeys(Keys.LEFT)
                    .sendKeys(Keys.RIGHT);
        }
        return this;
    }

    /**
     * Returns boolean if displayed the 'PasswordConfirm' field.
     *
     * @return boolean
     */
    protected boolean isDisplayedPasswordConfirmField() {
        return getPasswordConfirmField().isDisplayed();
    }

    // showPasswordConfirmButton

    /**
     * Returns a WebElement of the 'ShowPasswordConfirm' button.
     *
     * @return WebElement
     */
    protected WebElement getShowPasswordConfirmButton() {
        this.showPasswordConfirmButton = driver
                .findElement(By.xpath(SHOW_PASSWORD_CONFIRM_BUTTON_SELECTOR));
    return showPasswordConfirmButton;
    }

    /**
     * Click on the 'ShowPasswordConfirm' button.
     *
     * @return RegisterPart
     */
    protected RegisterComponent clickShowPasswordConfirmButton() {
        if (isDisplayedShowPasswordConfirmButton())
            this.getShowPasswordConfirmButton().click();
        return this;
    }

    /**
     * Returns boolean if displayed the 'ShowPasswordConfirm' button.
     *
     * @return boolean
     */
    protected boolean isDisplayedShowPasswordConfirmButton() {
        return getShowPasswordConfirmButton().isDisplayed();
    }



    protected boolean sizePasswordConfirmValidator() {
        return driver
                .findElements(
                        By.cssSelector(PASSWORD_CONFIRM_VALIDATOR_SELECTOR))
                .size() != 0;
    }



    // Sign Up button

    /**
     * Returns a WebElement of the 'SignUp' button.
     *
     * @return WebElement
     */
    protected WebElement getSignUpButton() {

        this.signUpButton = driver
                .findElement(By.cssSelector(SIGNUP_BUTTON_SELECTOR));
    return signUpButton;
    }


    /**
     * Returns boolean if displayed the 'SignUp' button.
     *
     * @return boolean
     */
    protected boolean isDisplayedSignUpButton() {
        return getSignUpButton().isDisplayed();
    }

    /**
     * Returns a text which displayed on the 'SignUp' button.
     *
     * @return String
     */
    protected String getSignUpButtonText() {
        return getSignUpButton().getText();
    }

//    public boolean isClickablSignUpButton() {
//        return new WebDriverWait().until(ExpectedConditions.elementToBeClickable(getSignUpButton())) != null;
//    }

    public boolean signUpIsDisabled() {
        return getSignUpButton().getAttribute("disabled") != null;

    }


    // Functional

    /**
     * Inserting some text on the 'Email' field.
     *
     * @param email String
     * @return ManualRegisterComponent
     */
    protected ManualRegisterComponent fillEmailField(String email) {
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
     * @return ManualRegisterComponent
     */
    protected ManualRegisterComponent fillUserNameField(String firstName) {
        if (isDisplayedFirstNameField()) {
            clickFirstName(driver);
            clearFirstName();
            inputUserName(firstName);
        }
        return this;
    }


    /**
     * Inserting some text on the 'Password' field.
     *
     * @param password String
     * @return ManualRegisterComponent
     */
    protected ManualRegisterComponent fillPasswordField(String password) {
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
     * @return ManualRegisterComponent
     */
    protected ManualRegisterComponent fillPasswordConfirmField(String passwordConfirm) {
        if (isDisplayedPasswordConfirmField()) {
            clickPasswordConfirmField(driver);
            clearPasswordConfirmField();
            inputPasswordConfirm(passwordConfirm);
            clickShowPasswordConfirmButton();
        }
        return this;
    }

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


    protected String getTempEmail() {
        driver.get(GetMail10MinTools.URL);
        GetMail10MinTools tmp = new GetMail10MinTools(driver);
        return tmp.getTempEmail();
    }

    public ManualRegisterComponent clickSignUpButton() {
        if (isDisplayedSignUpButton()) {
            this.getSignUpButton().click();
        }
        return this;
    }

    protected String getTemporaryEmail() {
        String currentTab = driver.getWindowHandle();
        ((JavascriptExecutor)driver).executeScript("window.open()");
        switchToAnotherTab(currentTab);
        String email = getTempEmail();
        logger.info("temporary Email address for registration: " + email);
//        System.out.println("temporary Email address for registration: " + email);
        driver.switchTo().window(currentTab);
        return email;
    }


    protected RegisterComponent verifyTempEmail() {
        String currentTab = driver.getWindowHandle();
        switchToAnotherTab(currentTab);
        GetMail10MinTools tmp = new GetMail10MinTools(driver);
        tmp.verifyEmail();
        driver.switchTo().window(currentTab);
        return this;
    }

    //    Business Logic




    public void registrationWrongUser(User userData) {
        fillEmailField(userData.getEmail())
                .fillUserNameField(userData.getUserName())
                .fillPasswordField(userData.getPassword())
                .fillPasswordConfirmField(userData.getConfirmPassword())
                .clickSignUpButton();
    }
    /**
     * Filling all fields on Register page without registration (without click
     * on SingUp button).
     * @param userData object with user's credentials
     */
    public void fillFieldsWithoutRegistration(User userData) {
        fillEmailField(userData.getEmail())
                .fillUserNameField(userData.getUserName())
                .fillPasswordField(userData.getPassword())
                .fillPasswordConfirmField(userData.getPassword());
    }
    
    // completion of user registration
    /**
     * Filling all fields on Register page and click on SingUp button.
     * @param userData object with user's credentials
     */
    public void registrationNewRandomUser(User userData) {
        userData.setEmail(getTemporaryEmail());
        fillEmailField(userData.getEmail())
                .fillUserNameField(userData.getUserName())
                .fillPasswordField(userData.getPassword())
                .fillPasswordConfirmField(userData.getPassword());
        //
        clickSignUpButton()
                .verifyTempEmail();
    }

    /**
     * Test registration user with existing credentials already.
     *
     * @param userData User
     */
    public void registrationUser(User userData) {
        fillEmailField(userData.getEmail())
                .fillUserNameField(userData.getUserName())
                .fillPasswordField(userData.getPassword())
                .fillPasswordConfirmField(userData.getPassword());
        clickSignUpButton();
    }



}
