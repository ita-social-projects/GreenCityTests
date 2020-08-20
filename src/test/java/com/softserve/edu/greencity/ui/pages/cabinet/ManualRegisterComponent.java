package com.softserve.edu.greencity.ui.pages.cabinet;

import com.softserve.edu.greencity.ui.data.User;
import com.softserve.edu.greencity.ui.tools.api.mail.GoogleMailAPI;
import com.softserve.edu.greencity.ui.tools.StableWebElementSearch;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import static org.openqa.selenium.support.ui.ExpectedConditions.invisibilityOfElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;


public class ManualRegisterComponent extends RegisterComponent implements StableWebElementSearch {

    private WebDriver driver;
    private String confirmURL = null;
    private By EMAIL_FIELD_SELECTOR = By.cssSelector("input[name='email']");
    private By USER_NAME_FIELD_SELECTOR = By.cssSelector("input[name='fistName']");
    private By PASSWORD_FIELD_SELECTOR = By.cssSelector("input[name='form-control password']");
    private By PASSWORD_CONFIRM_FIELD_SELECTOR = By.cssSelector("input[name='form-control password-confirm");
    private By SIGN_BUTTON_SELECTOR = By.cssSelector("app-sign-up button.primary-global-button");
    private By EMAIL_VALIDATOR_SELECTOR = By.xpath("//input[@name='email']/following-sibling::div/div");
    private By FIRST_NAME_VALIDATOR_SELECTOR = By.xpath("//input[@name='fistName']/following-sibling::div/div");
    private By PASSWORD_VALIDATOR_SELECTOR = By.xpath("//*[@name = 'form-control password']//parent::div//following-sibling::div");
    private By SHOW_PASSWORD_BUTTON_SELECTOR = By.xpath("//input[@name='form-control password']/../span/img");
    private By SHOW_PASSWORD_CONFIRM_BUTTON_SELECTOR = By.xpath("//input[@name='form-control password-confirm']/../span/img");
    private By PASSWORD_CONFIRM_VALIDATOR_SELECTOR = By.xpath("//*[@name = 'form-control password-confirm']//parent::div//following-sibling::div");


    public ManualRegisterComponent(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    @Step
    public WebElement getUserNameField() {
        return searchElementByCss(USER_NAME_FIELD_SELECTOR);
    }

    @Step
    private ManualRegisterComponent inputUserName(String userName) {
        this.getUserNameField().sendKeys(userName);
        return this;
    }

    @Step
    private ManualRegisterComponent clearFirstName() {
        this.getUserNameField().clear();
        return this;
    }

    @Step
    private ManualRegisterComponent clickFirstName(WebDriver driver) {
        if (isDisplayedFirstNameField()) {
            this.getUserNameField().click();
            Actions action = new Actions(driver);
            action.contextClick(getUserNameField()).sendKeys(Keys.LEFT)
                    .sendKeys(Keys.RIGHT);
        }
        return this;
    }

    @Step
    private boolean isDisplayedFirstNameField() {
        return getUserNameField().isDisplayed();
    }


    @Step
    private WebElement getUserNameValidator() {
        return searchElementByCss(FIRST_NAME_VALIDATOR_SELECTOR);
    }

    @Step
    public String getUserNameValidatorText() {
        return getUserNameValidator().getText();
    }

    @Step
    private WebElement getEmailField() {
        return searchElementByCss(EMAIL_FIELD_SELECTOR);
    }

    @Step
    private RegisterComponent inputEmailField(String email) {
        this.getEmailField().sendKeys(email);
        return this;
    }

    @Step
    private RegisterComponent clearEmailField() {
        this.getEmailField().clear();
        return this;
    }

    @Step
    private RegisterComponent clickEmailField(WebDriver driver) {
        if (isDisplayedEmailField()) {
            this.getEmailField().click();
            Actions action = new Actions(driver);
            action.contextClick(getEmailField()).sendKeys(Keys.LEFT)
                    .sendKeys(Keys.RIGHT);
        }
        return this;
    }

    @Step
    private boolean isDisplayedEmailField() {
        return getEmailField().isDisplayed();
    }

    @Step
    public String getEmailValidatorText() {
        return getEmailValidator().getText();
    }

    @Step
    private WebElement getEmailValidator() {
        return searchElementByCss(EMAIL_VALIDATOR_SELECTOR);
    }

    @Step
    public WebElement getPasswordField() {
        return searchElementByCss(PASSWORD_FIELD_SELECTOR);
    }

    @Step
    private RegisterComponent inputPassword(String password) {
        this.getPasswordField().sendKeys(password);
        return this;
    }

    @Step
    private RegisterComponent clearPasswordField() {
        this.getPasswordField().clear();
        return this;
    }

    @Step
    private RegisterComponent clickPasswordField(WebDriver driver) {
        if (isDisplayedPasswordField()) {
            this.getPasswordField().click();
            Actions action = new Actions(driver);
            action.contextClick(getPasswordField()).sendKeys(Keys.LEFT)
                    .sendKeys(Keys.RIGHT);
        }
        return this;
    }

    @Step
    private boolean isDisplayedPasswordField() {
        return getPasswordField().isDisplayed();
    }

    @Step
    private WebElement getShowPasswordButton() {
        return searchElementByXpath(SHOW_PASSWORD_BUTTON_SELECTOR);
    }

    @Step
    private RegisterComponent clickShowPasswordButton() {
        if (isDisplayedShowPasswordButton()) {
            this.getShowPasswordButton().click();
        }
        return this;
    }


    @Step
    private boolean isDisplayedShowPasswordButton() {
        return getShowPasswordButton().isDisplayed();
    }

    @Step
    public String getPasswordValidatorText() {
        return getPasswordValidator().getText();
    }

    @Step
    private WebElement getPasswordValidator() {
        return searchElementByXpath(PASSWORD_VALIDATOR_SELECTOR);
    }

    @Step
    public String getPasswordConfirmValidatorText() {
        return getPasswordConfirmValidator().getText().trim();
    }

    @Step
    private WebElement getPasswordConfirmValidator() {
        return searchElementByXpath(PASSWORD_CONFIRM_VALIDATOR_SELECTOR);
    }

    @Step
    private WebElement getPasswordConfirmField() {
        return searchElementByCss(PASSWORD_CONFIRM_FIELD_SELECTOR);
    }

    @Step
    private RegisterComponent inputPasswordConfirm(String passwordConfirm) {
        this.getPasswordConfirmField().sendKeys(passwordConfirm);
        return this;
    }

    @Step
    private RegisterComponent clearPasswordConfirmField() {
        this.getPasswordConfirmField().clear();
        return this;
    }

    @Step
    private RegisterComponent clickPasswordConfirmField(WebDriver driver) {
        if (isDisplayedPasswordConfirmField()) {
            this.getPasswordConfirmField().click();
            Actions action = new Actions(driver);
            action.contextClick(getPasswordConfirmField()).sendKeys(Keys.LEFT)
                    .sendKeys(Keys.RIGHT);
        }
        return this;
    }

    @Step
    private boolean isDisplayedPasswordConfirmField() {
        return getPasswordConfirmField().isDisplayed();
    }

    @Step
    private WebElement getShowPasswordConfirmButton() {
        return searchElementByXpath(SHOW_PASSWORD_CONFIRM_BUTTON_SELECTOR);
    }

    @Step
    private RegisterComponent clickShowPasswordConfirmButton() {
        if (isDisplayedShowPasswordConfirmButton())
            this.getShowPasswordConfirmButton().click();
        return this;
    }

    @Step
    private boolean isDisplayedShowPasswordConfirmButton() {
        return getShowPasswordConfirmButton().isDisplayed();
    }

    @Step
    private WebElement getSignUpButton() {
        return searchElementByCss(SIGN_BUTTON_SELECTOR);
    }

    @Step
    private boolean isDisplayedSignUpButton() {
        return getSignUpButton().isDisplayed();
    }

    @Step
    public boolean signUpIsDisabled() {
        return getSignUpButton().getAttribute("disabled") != null;

    }

    @Step
    private ManualRegisterComponent fillEmailField(String email) {
        if (isDisplayedEmailField()) {
            clickEmailField(driver);
            clearEmailField();
            inputEmailField(email);
        }
        return this;
    }

    @Step
    private ManualRegisterComponent fillUserNameField(String firstName) {
        if (isDisplayedFirstNameField()) {
            clickFirstName(driver);
            clearFirstName();
            inputUserName(firstName);
        }
        return this;
    }

    @Step
    private ManualRegisterComponent fillPasswordFieldPassShown(String password) {
        if (isDisplayedPasswordField()) {
            clickPasswordField(driver);
            clearPasswordField();
            inputPassword(password);
            clickShowPasswordButton();
        }
        return this;
    }

    @Step
    public ManualRegisterComponent fillPasswordFieldPassHidden(String password) {
        if (isDisplayedPasswordField()) {
            clickPasswordField(driver);
            clearPasswordField();
            inputPassword(password);
        }
        return this;
    }

    @Step
    private ManualRegisterComponent fillPasswordConfirmField(String passwordConfirm) {
        if (isDisplayedPasswordConfirmField()) {
            clickPasswordConfirmField(driver);
            clearPasswordConfirmField();
            inputPasswordConfirm(passwordConfirm);
            clickShowPasswordConfirmButton();
        }
        return this;
    }

    @Step
    private ManualRegisterComponent clickSignUpButton() {
        if (isDisplayedSignUpButton()) {
            this.getSignUpButton().click();
        }
        return this;
    }
    @Step
    private void getConfirmURL() {
        confirmURL = new GoogleMailAPI().getconfirmURL(10);}
    @Step
    private RegisterComponent checkVerIfMailReceived() {
        if (confirmURL == null) getConfirmURL();
        Assert.assertNotNull(confirmURL);
        return this;}
    @Step
    private RegisterComponent verifyRegistration() {
        driver.get(new GoogleMailAPI().getconfirmURL(10));
        return this;}

    @Step
    public void registrationWrongUser(User userData) {
        driver.get(confirmURL);
    }

    @Step
    public void fillFieldsWithoutRegistration(User userData) {
        fillEmailField(userData.getEmail())
                .fillUserNameField(userData.getUserName())
                .fillPasswordFieldPassShown(userData.getPassword())
                .fillPasswordConfirmField(userData.getConfirmPassword());

    }

    @Step
    public void registrationNewUserVerified(User userData) {
        fillEmailField(userData.getEmail())
                .fillUserNameField(userData.getUserName())
                .fillPasswordFieldPassShown(userData.getPassword())
                .fillPasswordConfirmField(userData.getConfirmPassword())
                .clickSignUpButton()
                .waitSuccessfullRegistrationPopUp()
                .waitSuccessfullRegistrationPopUpDisappear()
                .verifyRegistration();
    }

    @Step
    public void registrationUser(User userData) {
        fillEmailField(userData.getEmail())
                .fillUserNameField(userData.getUserName())
                .fillPasswordFieldPassShown(userData.getPassword())
                .fillPasswordConfirmField(userData.getConfirmPassword());
        clickSignUpButton();
    }

    @Step
    public void registerUserCheckIfMailReceived(User userData) {
        fillEmailField(userData.getEmail())
                .fillUserNameField(userData.getUserName())
                .fillPasswordFieldPassShown(userData.getPassword())
                .fillPasswordConfirmField(userData.getConfirmPassword())
                .clickSignUpButton()
                .waitSuccessfullRegistrationPopUp()
                .waitSuccessfullRegistrationPopUpDisappear()
                .checkVerIfMailReceived();
    }

    private ManualRegisterComponent waitSuccessfullRegistrationPopUp() {
        new WebDriverWait(driver, 10).until(visibilityOfElementLocated(By.id("mat-dialog-1")));
        return this;
    }

    private ManualRegisterComponent waitSuccessfullRegistrationPopUpDisappear() {
        new WebDriverWait(driver, 10).until(invisibilityOfElementLocated(By.id("mat-dialog-1")));
        return this;
    }

    @Override
    public WebDriver setDriver() {
        return this.driver;
    }
}
