package com.softserve.edu.greencity.ui.tools;

import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Gives temporary email for registration and verify email.
 * @author Serg
 */
public final class GetMail10MinTools {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private WebDriver driver;
    private WebDriverWait wait;
    //
    private WebElement tempEmailField;
    private WebElement deleteButton;
    private WebElement refreshButton;
    private WebElement emailButton;
    private WebElement verifyEmailButton;
    private WebElement verifyEmailURL;
    //
    public String title;

//    public final static String URL = "https://www.minuteinbox.com/";
//    public final static String URL = "https://10minutemail.net/";
    /**
     * Site URL for temporary email.
     */
    public final static String URL = "https://temp-mail.org/ru/";

    /**
     * To determine the desired letter, which gets in the mail from GreenCity
     * back-end.
     */
    private final String MAIL_GEEN_CITY = "mailgreencity1@gmail.com";

    /**
     * Constructor
     * @param driver WebDriver
     */
    public GetMail10MinTools(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
        initElements();
    }

    // init elements
    private void initElements() {
        logger.debug("start initElements() in GetMail10MinTools");
        title = driver.getTitle();
        deleteButton = driver.findElement(By.cssSelector("#click-to-delete"));
    }

    // Page Object
    //
    /**
     * Returns a page title of the website.
     * @return String
     */
    public String getTitleWebSite() {
        return title;
    }

    // deleteButton
    private WebElement getDeleteButton() {
        return deleteButton;
    }

    private void clickDeleteButton() {
        getDeleteButton().click();
    }

    private String getDeleteButtonText() {
        return getTempEmailField().getText();
    }

    private boolean isDisplayedDeleteButton() {
        return getDeleteButton().isDisplayed();
    }

    // refreshButton
    private WebElement getRefreshButton() {
        refreshButton = driver.findElement(By.id("click-to-refresh"));
        return refreshButton;
    }

    private void clickRefreshButton() {
        getRefreshButton().click();
    }

    private String getRefreshButtonText() {
        return getRefreshButton().getText();
    }

    private boolean isDisplayedRefreshButton() {
        return getRefreshButton().isDisplayed();
    }

//    tempEmailField
    private WebElement getTempEmailField() {
        tempEmailField = driver.findElement(
                By.cssSelector("div.input-box-col.hidden-xs-sm button"));
        return tempEmailField;
    }

    private void clickTempEmailField() {
        getTempEmailField().click();
    }

    private String getTempEmailFieldText()
            throws Exception, Exception, Exception {
        clickTempEmailField();
//        System.out.println(
//                "(String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor): "
//                        + (String) Toolkit.getDefaultToolkit()
//                                .getSystemClipboard()
//                                .getData(DataFlavor.stringFlavor));
        return (String) Toolkit.getDefaultToolkit().getSystemClipboard()
                .getData(DataFlavor.stringFlavor);
    }

    private boolean isDisplayedTempEmailField() {
        return getTempEmailField().isDisplayed();
    }

//    emailButton
    private WebElement getEmailButton() {
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//a[text()='Verify your email address']")));
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        //
        emailButton = driver.findElement(
                By.xpath("//a[text()='Verify your email address']"));
        return emailButton;
    }

    private void clickEmailButton() {
        getEmailButton().click();
    }

    private String getEmailButtonText() {
        return getEmailButton().getText();
    }

    private boolean isDisplayedEmailButton() {
        return getEmailButton().isDisplayed();
    }

//    verifyEmailButton
    private WebElement getVerifyEmailButton() {
        verifyEmailButton = driver.findElement(
                By.cssSelector("a[href*='greencity.azurewebsites.net']"));
        return verifyEmailButton;
    }

    private void clickVerifyEmailButton() {
        getVerifyEmailButton().click();
    }

    private String getVerifyEmailButtonText() {
        return getVerifyEmailButton().getText();
    }

    private boolean isDisplayedVerifyEmailButton() {
        return getVerifyEmailButton().isDisplayed();
    }

    // verifyEmailURL
    private WebElement getVerifyEmailURL() {
        verifyEmailButton = driver
                .findElement(By.cssSelector("table td p + p")); // for small
                                                                // size window
        return verifyEmailURL;
    }

    private String getVerifyEmailURLText() {
        return getVerifyEmailURL().getText();
    }

    private boolean isDisplayedVerifyEmailURL() {
        return getVerifyEmailURL().isDisplayed();
    }

    // Functional

    private String getEmail() {
        if (isDisplayedTempEmailField()) {
            try {
                return getTempEmailFieldText();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "field mail not found";
    }

    private void getNewMail() {
        if (isDisplayedDeleteButton()) {
            clickDeleteButton();
        }
    }

    private void refreshMail() {
        if (isDisplayedRefreshButton()) {
            clickRefreshButton();
        }
    }

    private void openMail() {
        while (!isDisplayedEmailButton()) {
            refreshMail();
        }
        if (isDisplayedEmailButton()) {
            clickEmailButton();
        }
    }

    private void clickVerifyButton() {
        if (isDisplayedVerifyEmailButton()) {
            clickVerifyEmailButton();
        }
    }

    private String getVerifyURL() {
        if (isDisplayedVerifyEmailURL()) {
            return getVerifyEmailURLText();
        } else {
            return "verify URL not found";
        }
    }

    // Business Logic

    /**
     * Returns an email address.
     * @return String
     */
    public String getTempEmail() {
        logger.debug("start getTempEmail()");
        logger.trace("get teporary New Mail");
        getNewMail();
        logger.trace("wait until a teporary New Mail is displayed");
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        wait.until(ExpectedConditions
                .stalenessOf(driver.findElement(By.cssSelector("#mail"))));
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        return getEmail();
    }

    /**
     * Finding the desired letter, opening and clicking on the button 'Verify'.
     */
    public void verifyEmail() {
        logger.debug("start verifyEmail()");
        logger.trace("click on refresh Mail button and open desired mail");
        openMail();
//        String verifyEmail = getVerifyURL();
        logger.trace("click on VerifyButton");
        clickVerifyButton();
//        return verifyEmail;
    }

}
