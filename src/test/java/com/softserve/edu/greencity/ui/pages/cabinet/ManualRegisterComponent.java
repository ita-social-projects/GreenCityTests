package com.softserve.edu.greencity.ui.pages.cabinet;

import com.softserve.edu.greencity.ui.data.User;
import com.softserve.edu.greencity.ui.pages.common.TopPart;
import com.softserve.edu.greencity.ui.tools.GetMail10MinTools;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

/**
 * ManualRegisterComponent class
 *
 * @author Serg
 */
public class ManualRegisterComponent extends RegisterComponent {


    private WebDriver driver;

    protected WebElement registrationValidator;
    private WebElement firstNameValidator;
    protected String confirmURL = "";

    private final String EMAIL_VALIDATOR_SELECTOR = "//input[@name='email']/following-sibling::div/div";
    private final String REGISTRATION_VALIDATOR_SELECTOR = "app-sign-up input#email + div";
    private final String FIRST_NAME_VALIDATOR_SELECTOR = "//input[@name='fistName']/following-sibling::div/div";
    private final String PASSWORD_VALIDATOR_SELECTOR = "//div[@class='main-data-input-password wrong-input']/following-sibling::div/div";
    private final String PASSWORD_CONFIRM_VALIDATOR_SELECTOR = "//*[@class = 'form-content-container']/div[last()-1]";
    private final String SUBMIT_EMAIL_SELECTOR = "app-submit-email div.submit-email";

    /**
     * ManualRegisterComponent constructor.
     *
     * @param driver WebDriver
     */
    public ManualRegisterComponent(WebDriver driver) {
        super(driver);
        this.driver = driver;
        initElements();
    }

    /**
     * Initialize web elements
     */
    private void initElements() {
        // init elements
        titlePage = driver.getTitle();
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
                .setPasswordConfirmField(passwordConfirmField)
                .setPasswordField(passwordField)
                .setShowPasswordButton(showPasswordButton)
                .setShowPasswordConfirmButton(showPasswordConfirmButton)
                .setSignUpButton(signUpButton);
    }


    /**
     * Get verify URL from the post in temp mailbox.
     *
     * @return String
     */

    protected String getVerifyURLText() {
        return this.confirmURL;
    }

    // first name field

    /**
     * Taking a WebElement and set it to a private WebElement
     *
     * @param userNameField WebElement
     * @return RegisterPart
     */
    public ManualRegisterComponent setUserNameField(WebElement userNameField) {
        this.userNameField = userNameField;
        return this;
    }

    /**
     * Returns a WebElement of the 'FirstName' field.
     *
     * @return WebElement
     */
    protected WebElement getUserNameField() {
        return userNameField;
    }

    /**
     * Inserting some text on the 'FirstName' field.
     *
     //* @param String firstName
     * @return RegisterPart
     */
    protected ManualRegisterComponent inputFirstName(String firstName) {
        this.getUserNameField().sendKeys(firstName);
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
    protected WebElement getFirstNameValidator() {
        firstNameValidator = driver
                .findElement(By.xpath(FIRST_NAME_VALIDATOR_SELECTOR));
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
     * Taking a WebElement and set it to a private WebElement field.
     *
     * @param emailField WebElement
     * @return RegisterPart
     */
    public ManualRegisterComponent setEmailField(WebElement emailField) {
        this.emailField = emailField;
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
     * Taking a WebElement and set it to a private WebElement field.
     *
     * @param passwordConfirmField WebElement
     * @return RegisterPart
     */
    public ManualRegisterComponent setPasswordConfirmField(
            WebElement passwordConfirmField) {
        this.passwordConfirmField = passwordConfirmField;
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
     * Taking a WebElement and set it to a private WebElement
     *
     * @param showPasswordConfirmButton
     * @return RegisterPart
     */
    public ManualRegisterComponent setShowPasswordConfirmButton(
            WebElement showPasswordConfirmButton) {
        this.showPasswordConfirmButton = showPasswordConfirmButton;
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

//
//    @Override
//    protected boolean sizePasswordConfirmValidator() {
//        return driver
//                .findElements(
//                        By.cssSelector(PASSWORD_CONFIRM_VALIDATOR_SELECTOR))
//                .size() != 0;
//    }

    // Sign Up button

    /**
     * Returns a WebElement of the 'SignUp' button.
     *
     * @return WebElement
     */
    protected WebElement getSignUpButton() {
        return signUpButton;
    }


    /**
     * Taking a WebElement and set it to a private WebElement field.
     *
     * @param signUpButton WebElement
     * @return RegisterPart
     */
    public ManualRegisterComponent setSignUpButton(WebElement signUpButton) {
        this.signUpButton = signUpButton;
        return this;
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
            inputFirstName(firstName);
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
//
//    @Override
//    protected GoogleAccountPage clickSignUpGoogleAccountButton() {
//        String currentTab = driver.getWindowHandle();
//        clickGoogleLoginButton();
//        switchToAnotherTab(currentTab);
//        return new GoogleAccountPage(driver);
//    }
//
    public ManualRegisterComponent clickSignUpButton() {
        if (isDisplayedSignUpButton()) {
            this.getSignUpButton().click();
        }
        return this;
    }
//
//    @Override
//    protected RegisterComponent clickSignInLink() {
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


    protected RegisterComponent verifyTempEmail() {
        String currentTab = driver.getWindowHandle();
        switchToAnotherTab(currentTab);
        GetMail10MinTools tmp = new GetMail10MinTools(driver);
        tmp.verifyEmail();
        driver.switchTo().window(currentTab);
        return this;
    }

    //    Business Logic

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
        fillEmailField(userData.getEmail())
                .fillUserNameField(userData.getFirstName())
                .fillPasswordField(userData.getPassword())
                .fillPasswordConfirmField(userData.getConfirmPassword())
                .clickSignUpButton();
    }
    /**
     * Filling all fields on Register page without registration (without click
     * on SingUp button).
     * @param userData object with user's credentials
     * @return RegisterComponent page
     */
    public void fillFieldsWithoutRegistration(User userData) {
        fillEmailField(userData.getEmail())
                .fillUserNameField(userData.getFirstName())
                .fillPasswordField(userData.getPassword())
                .fillPasswordConfirmField(userData.getPassword());
    }
    // completion of user registration
    /**
     * Filling all fields on Register page and click on SingUp button.
     * @param userData object with user's credentials
     * @return RegisterComponent page
     */
    public void registrationNewRandomUser(User userData) {
        userData.setEmail(getTemporaryEmail());
        fillEmailField(userData.getEmail())
                .fillUserNameField(userData.getFirstName())
                .fillPasswordField(userData.getPassword())
                .fillPasswordConfirmField(userData.getPassword());
        //
        clickSignUpButton().verifyTempEmail();
    }

    /**
     * Test registration user with existing credentials already.
     *
     * @param userData User
     */
    public void registrationUser(User userData) {
        fillEmailField(userData.getEmail())
                .fillUserNameField(userData.getFirstName())
                .fillPasswordField(userData.getPassword())
                .fillPasswordConfirmField(userData.getPassword());
        clickSignUpButton();
    }

    //Weird setters


}
