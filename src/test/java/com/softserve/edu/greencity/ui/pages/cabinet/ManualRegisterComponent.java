package com.softserve.edu.greencity.ui.pages.cabinet;

import com.softserve.edu.greencity.ui.data.User;
import com.softserve.edu.greencity.ui.tools.GMailBox;
import com.softserve.edu.greencity.ui.tools.TabsHandler;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import java.util.Set;


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
    private final String PASSWORD_VALIDATOR_SELECTOR = "//*[@name = 'form-control password']//parent::div//following-sibling::div";
    private final String PASSWORD_CONFIRM_VALIDATOR_SELECTOR = "//*[@name = 'form-control password-confirm']//parent::div//following-sibling::div";


    public ManualRegisterComponent(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public WebElement getUserNameField() {

        this.userNameField = driver
                .findElement(By.cssSelector(USER_NAME_FIELD_SELECTOR));
        return userNameField;
    }


    protected ManualRegisterComponent inputUserName(String userName) {
        this.getUserNameField().sendKeys(userName);
        return this;
    }


    protected ManualRegisterComponent clearFirstName() {
        this.getUserNameField().clear();
        return this;
    }


    protected ManualRegisterComponent clickFirstName(WebDriver driver) {
        if (isDisplayedFirstNameField()) {
            this.getUserNameField().click();
            Actions action = new Actions(driver);
            action.contextClick(getUserNameField()).sendKeys(Keys.LEFT)
                    .sendKeys(Keys.RIGHT);
        }
        return this;
    }


    protected boolean isDisplayedFirstNameField() {
        return getUserNameField().isDisplayed();
    }


    protected RegisterComponent setFirstNameValidator(
            WebElement firstNameValidator) {
        this.firstNameValidator = firstNameValidator;
        return this;
    }


    protected WebElement getUserNameValidator() {
        firstNameValidator = driver
                .findElement(By.xpath(FIRST_NAME_VALIDATOR_SELECTOR));
        return firstNameValidator;
    }


    public String getUserNameValidatorText() {
        return getUserNameValidator().getText();
    }


    protected boolean isDisplayedFirstNameValidator() {
        return getUserNameValidator().isDisplayed();
    }


    protected boolean sizeFirstNameValidator() {
        return driver
                .findElements(By.cssSelector(FIRST_NAME_VALIDATOR_SELECTOR))
                .size() != 0;
    }


    protected RegisterComponent setRegistrationValidator(WebElement registrationValidator) {
        this.registrationValidator = registrationValidator;
        return this;
    }


    protected WebElement getRegistrationValidator() {
        registrationValidator = driver.findElement(By.cssSelector(REGISTRATION_VALIDATOR_SELECTOR));
        return registrationValidator;
    }


    public String getRegistrationValidatorText() {
        return getRegistrationValidator().getText();
    }


    public boolean isDisplayedRegistrationValidator() {
        return getRegistrationValidator().isDisplayed();
    }

    protected boolean sizeRegistrationValidator() {
        return driver.findElements(By.cssSelector(REGISTRATION_VALIDATOR_SELECTOR))
                .size() != 0;
    }


    protected WebElement getEmailField() {
        this.emailField = driver
                .findElement(By.cssSelector(EMAIL_FIELD_SELECTOR));
        return emailField;
    }


    protected RegisterComponent inputEmailField(String email) {
        this.getEmailField().sendKeys(email);
        return this;
    }


    protected RegisterComponent clearEmailField() {
        this.getEmailField().clear();
        return this;
    }


    public RegisterComponent clickEmailField(WebDriver driver) {
        if (isDisplayedEmailField()) {
            this.getEmailField().click();
            Actions action = new Actions(driver);
            action.contextClick(getEmailField()).sendKeys(Keys.LEFT)
                    .sendKeys(Keys.RIGHT);
        }
        return this;
    }


    protected boolean isDisplayedEmailField() {
        return getEmailField().isDisplayed();
    }


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


    public WebElement getPasswordField() {
        this.passwordField = driver
                .findElement(By.cssSelector(PASSWORD_FIELD_SELECTOR));
        return passwordField;
    }


    protected RegisterComponent inputPassword(String password) {
        this.getPasswordField().sendKeys(password);
        return this;
    }


    protected RegisterComponent clearPasswordField() {
        this.getPasswordField().clear();
        return this;
    }


    protected RegisterComponent clickPasswordField(WebDriver driver) {
        if (isDisplayedPasswordField()) {
            this.getPasswordField().click();
            Actions action = new Actions(driver);
            action.contextClick(getPasswordField()).sendKeys(Keys.LEFT)
                    .sendKeys(Keys.RIGHT);
        }
        return this;
    }


    public ManualRegisterComponent setPasswordField(WebElement passwordField) {
        this.passwordField = passwordField;
        return this;
    }


    protected boolean isDisplayedPasswordField() {
        return getPasswordField().isDisplayed();
    }

    protected WebElement getShowPasswordButton() {
        this.showPasswordButton = driver
                .findElement(By.xpath(SHOW_PASSWORD_BUTTON_SELECTOR));
        return showPasswordButton;
    }

    protected RegisterComponent clickShowPasswordButton() {
        if (isDisplayedShowPasswordButton()) {
            this.getShowPasswordButton().click();
        }
        return this;
    }

    public ManualRegisterComponent setShowPasswordButton(WebElement showPasswordButton) {
        this.showPasswordButton = showPasswordButton;
        return this;
    }


    protected boolean isDisplayedShowPasswordButton() {
        return getShowPasswordButton().isDisplayed();
    }


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


    protected boolean sizePasswordValidator() {
        return driver.findElements(By.cssSelector(PASSWORD_VALIDATOR_SELECTOR))
                .size() != 0;
    }


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

    protected WebElement getPasswordConfirmField() {

        this.passwordConfirmField = driver
                .findElement(By.cssSelector(PASSWORD_CONFIRM_FIELD_SELECTOR));
        return passwordConfirmField;
    }


    protected RegisterComponent inputPasswordConfirm(String passwordConfirm) {
        this.getPasswordConfirmField().sendKeys(passwordConfirm);
        return this;
    }


    protected RegisterComponent clearPasswordConfirmField() {
        this.getPasswordConfirmField().clear();
        return this;
    }


    protected RegisterComponent clickPasswordConfirmField(WebDriver driver) {
        if (isDisplayedPasswordConfirmField()) {
            this.getPasswordConfirmField().click();
            Actions action = new Actions(driver);
            action.contextClick(getPasswordConfirmField()).sendKeys(Keys.LEFT)
                    .sendKeys(Keys.RIGHT);
        }
        return this;
    }


    protected boolean isDisplayedPasswordConfirmField() {
        return getPasswordConfirmField().isDisplayed();
    }


    protected WebElement getShowPasswordConfirmButton() {
        this.showPasswordConfirmButton = driver
                .findElement(By.xpath(SHOW_PASSWORD_CONFIRM_BUTTON_SELECTOR));
        return showPasswordConfirmButton;
    }

    protected RegisterComponent clickShowPasswordConfirmButton() {
        if (isDisplayedShowPasswordConfirmButton())
            this.getShowPasswordConfirmButton().click();
        return this;
    }

    protected boolean isDisplayedShowPasswordConfirmButton() {
        return getShowPasswordConfirmButton().isDisplayed();
    }


    protected boolean sizePasswordConfirmValidator() {
        return driver
                .findElements(
                        By.cssSelector(PASSWORD_CONFIRM_VALIDATOR_SELECTOR))
                .size() != 0;
    }

    public WebElement getSignUpButton() {

        this.signUpButton = driver
                .findElement(By.cssSelector(SIGNUP_BUTTON_SELECTOR));
        return signUpButton;
    }

    protected boolean isDisplayedSignUpButton() {
        return getSignUpButton().isDisplayed();
    }

    protected String getSignUpButtonText() {
        return getSignUpButton().getText();
    }

    public boolean signUpIsDisabled() {
        return getSignUpButton().getAttribute("disabled") != null;

    }

    protected ManualRegisterComponent fillEmailField(String email) {
        if (isDisplayedEmailField()) {
            clickEmailField(driver);
            clearEmailField();
            inputEmailField(email);
        }
        return this;
    }

    protected ManualRegisterComponent fillUserNameField(String firstName) {
        if (isDisplayedFirstNameField()) {
            clickFirstName(driver);
            clearFirstName();
            inputUserName(firstName);
        }
        return this;
    }

    protected ManualRegisterComponent fillPasswordFieldPassShown(String password) {
        if (isDisplayedPasswordField()) {
            clickPasswordField(driver);
            clearPasswordField();
            inputPassword(password);
            clickShowPasswordButton();
        }
        return this;
    }

    public ManualRegisterComponent fillPasswordFieldPassHidden(String password) {
        if (isDisplayedPasswordField()) {
            clickPasswordField(driver);
            clearPasswordField();
            inputPassword(password);
        }
        return this;
    }

    protected ManualRegisterComponent fillPasswordConfirmField(String passwordConfirm) {
        if (isDisplayedPasswordConfirmField()) {
            clickPasswordConfirmField(driver);
            clearPasswordConfirmField();
            inputPasswordConfirm(passwordConfirm);
            clickShowPasswordConfirmButton();
        }
        return this;
    }


    public ManualRegisterComponent clickSignUpButton() {
        if (isDisplayedSignUpButton()) {
            this.getSignUpButton().click();
        }
        return this;
    }


    protected RegisterComponent checkVerIfMailReceived() {
        String initialTab = driver.getWindowHandle();
        Set<String> allTabs = driver.getWindowHandles();
        String newlyOpenedTab = TabsHandler.openNewTabAndGetId(driver, allTabs);
        driver.switchTo().window(newlyOpenedTab);

        GMailBox logInGMailPage = new GMailBox(driver);
        logInGMailPage.logInGMail();
        WebElement email = logInGMailPage.getTopUnreadEmail();
        Assert.assertEquals(logInGMailPage.readHeader(email),"Verify your email address");
        logInGMailPage.openTopUnreadEmail();
        Assert.assertTrue(logInGMailPage.getVerifyEmailButton().isDisplayed());

        driver.close();
        driver.switchTo().window(initialTab);
        return this;
    }

    protected RegisterComponent verifyRegistration() {
        String initialTab = driver.getWindowHandle();
        Set<String> allTabs = driver.getWindowHandles();
        String newlyOpenedTab = TabsHandler.openNewTabAndGetId(driver, allTabs);
        driver.switchTo().window(newlyOpenedTab);

        GMailBox logInGMailPage = new GMailBox(driver);
        logInGMailPage.logInGMail()
                .openEmailClickLink();

        driver.close();
        driver.switchTo().window(initialTab);
        return this;
    }

    public void registrationWrongUser(User userData) {
        fillEmailField(userData.getEmail())
                .fillUserNameField(userData.getUserName())
                .fillPasswordFieldPassShown(userData.getPassword())
                .fillPasswordConfirmField(userData.getConfirmPassword())
                .clickSignUpButton();
    }

    public void fillFieldsWithoutRegistration(User userData) {
        fillEmailField(userData.getEmail())
                .fillUserNameField(userData.getUserName())
                .fillPasswordFieldPassShown(userData.getPassword())
                .fillPasswordConfirmField(userData.getConfirmPassword());

    }

    public void registrationNewUserVerified(User userData) {
        fillEmailField(userData.getEmail())
                .fillUserNameField(userData.getUserName())
                .fillPasswordFieldPassShown(userData.getPassword())
                .fillPasswordConfirmField(userData.getConfirmPassword())
                .clickSignUpButton()
                .verifyRegistration();
    }

    public void registrationUser(User userData) {
        fillEmailField(userData.getEmail())
                .fillUserNameField(userData.getUserName())
                .fillPasswordFieldPassShown(userData.getPassword())
                .fillPasswordConfirmField(userData.getConfirmPassword());
        clickSignUpButton();
    }

    public void registerUserCheckIfMailReceived(User userData) {
        fillEmailField(userData.getEmail())
                .fillUserNameField(userData.getUserName())
                .fillPasswordFieldPassShown(userData.getPassword())
                .fillPasswordConfirmField(userData.getConfirmPassword())
                .clickSignUpButton()
                .checkVerIfMailReceived();
    }


}
