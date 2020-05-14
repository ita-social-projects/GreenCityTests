package com.softserve.edu.greencity.ui.pages.cabinet;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * GoogleAccountPage class.
 * @author Serg
 */
public class GoogleAccountPage {

    private WebDriver driver;
    private WebDriverWait wait;
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    //
//    private WebElement chosenGoogleAccountButton;
//    private WebElement useAnotherAccountButton;
    private WebElement emailField;
    private WebElement emailNextButton;
//    private WebElement forgotEmailLink;
    private WebElement passwordField;
//    private WebElement showPasswordGoogleAccountButton;
//    private WebElement forgotPasswordGoogleAccountLink;
    private WebElement passwordNextButton;

    private String titleGoogleAccount;
//    private String emailGoogleAccount;

    /**
     * GoogleAccountPage constructor.
     * @param driver
     */
    public GoogleAccountPage(WebDriver driver) {
        this.driver = driver;
        initElements();
    }

    private void initElements() {
        // init elements
        wait = new WebDriverWait(driver, 10);
        //
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("div[role='presentation'] #identifierId")));
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        //
        titleGoogleAccount = driver.getTitle();
        emailField = driver.findElement(
                By.cssSelector("div[role='presentation'] #identifierId"));
//        emailField = driver.findElement(By.cssSelector("input[type='email']"));
        emailNextButton = driver.findElement(
                By.cssSelector("div[role='presentation'] #identifierNext"));
    }

    // Page Object
    /**
     * Get title Google Account page.
     * @return String
     */
    public String getTitleGoogleAccount() {
        logger.debug("start getTitleGoogleAccount()");
        logger.trace("returns GoogleAccount page title");
        logger.info("GoogleAccountPage title: "
                + titleGoogleAccount);
        return titleGoogleAccount;
    }

    // emailField
    private WebElement getEmailField() {
        return emailField;
    }

    private void clearEmailField() {
        getEmailField().clear();
    }

    private void clickEmailField() {
        if (isDisplayedEmailField()) {
            Actions action = new Actions(driver);
            action.contextClick(getEmailField()).sendKeys(Keys.LEFT)
                    .sendKeys(Keys.RIGHT);
            getEmailField().click();
        }
    }

    private void setEmailField(String emailGoogleAccount) {
        getEmailField().sendKeys(emailGoogleAccount);
    }

    private boolean isDisplayedEmailField() {
        return getEmailField().isDisplayed();
    }

    // emailNextButton
    private WebElement getEmailNextButton() {
        return emailNextButton;
    }

    private String getEmailNextButtonText() {
        return getEmailNextButton().getText();
    }

    private void clickEmailNextButton() {
        if (isDisplayedEmailNextButton()) {
            getEmailNextButton().click();
        }
    }

    private boolean isDisplayedEmailNextButton() {
        return getEmailNextButton().isDisplayed();
    }

//           passwordGoogleAccountField
    private WebElement getPasswordField() {
        passwordField = driver.findElement(
                By.cssSelector("div#password input[name='password']"));
        return passwordField;
    }

    private void clearPasswordField() {
        getPasswordField().clear();
    }

    private void clickPasswordField() {
        getPasswordField().click();
    }

    private void setPasswordField(String passwordGoogleAccount) {
        getPasswordField().sendKeys(passwordGoogleAccount);
    }

    private boolean isDisplayedPasswordField() {
        return getPasswordField().isDisplayed();
    }

//           nextButton
    private WebElement getPasswordNextButton() {
        passwordNextButton = driver.findElement(
                By.cssSelector("div[role='presentation'] #passwordNext"));
        return passwordNextButton;
    }

    private String getPasswordNextButtonText() {
        return getPasswordNextButton().getText();
    }

    private void clickPasswordNextButton() {
        if (isDisplayedPasswordNextButton()) {
            getPasswordNextButton().click();
        }
    }

    private boolean isDisplayedPasswordNextButton() {
        return getPasswordNextButton().isDisplayed();
    }

    // Functional

    // Business Logic

    /**
     * Enter an email.
     * @param email String
     * @return GoogleAccountPage
     */
    public GoogleAccountPage enterEmail(String email) {
        logger.debug("start GoogleAccountPage enterEmail()");
        logger.trace("find email in the field");
        if (isDisplayedEmailField()) {
            clickEmailField();
            clearEmailField();
            logger.info("Email Google page. Entered email: " + email);
            setEmailField(email);
        }
        return this;
    }

    /**
     * Click on "Next" button on the email page.
     * @return GoogleAccountPage
     */
    public GoogleAccountPage clickEmailNext() {
        logger.debug("start GoogleAccountPage clickEmailNext()");
        logger.trace("clicking on 'Next' button");
        if (isDisplayedEmailNextButton()) {
            logger.info("Email Google page. Click on : "
                    + getEmailNextButtonText() + " button");
            clickEmailNextButton();
        }
        return this;
    }

    /**
     * Enter a password.
     * @param password String
     * @return GoogleAccountPage
     */
    public GoogleAccountPage enterPassword(String password) {
        logger.debug("start GoogleAccountPage enterPassword()");
        logger.trace("find password in the field");
        if (isDisplayedPasswordField()) {
            clickPasswordField();
            clearPasswordField();
            logger.info(
                    "Password Google page. Entered a password: " + password);
            setPasswordField(password);
        }
        return this;
    }

    /**
     * Click on "Next" button on the password page.
     * @return GoogleAccountPage
     */
    public GoogleAccountPage clickPasswordNext() {
        logger.debug("start GoogleAccountPage clickPasswordNext()");
        logger.trace("clicking on 'Next' button");
        if (isDisplayedPasswordNextButton()) {
            logger.info("Password Google page. Click on "
                    + getPasswordNextButtonText() + " button");
            clickPasswordNextButton();
        }
        return this;
    }
}
