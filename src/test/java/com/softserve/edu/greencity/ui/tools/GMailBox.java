package com.softserve.edu.greencity.ui.tools;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

public class GMailBox {

    private WebDriver driver;
    public final static String GMAIL_URL = "https://accounts.google.com/signin/v2/identifier?" +
            "continue=https%3A%2F%2Fmail.google.com%2Fmail%2F&service=mail&sacu=1&rip=1&" +
            "flowName=GlifWebSignIn&flowEntry=ServiceLogin";
    private Properties property = new Properties();

    private WebElement loginEmail;
    private WebElement nextButton;
    private WebElement loginPassword;
    private WebElement signInButton;

    private WebElement mailHeader;
    private WebElement topUnreadMail;
    private ArrayList<WebElement> allMails;
    private WebElement verifyEmailButton;

    public GMailBox(WebDriver driver) {

        this.driver = driver;
        driver.get(GMAIL_URL);

        try {
            final FileInputStream fis = new FileInputStream("src/test/resources/credentials.properties");
            property.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private GMailBox enterEmailAddress() {
        loginEmail = driver.findElement(By.id("identifierId"));
        loginEmail.sendKeys(property.getProperty("emailForRegistration"));
        return this;
    }

    private GMailBox submitEmailAddress() {
        nextButton = driver.findElement(By.id("identifierNext"));
        nextButton.click();
        return this;
    }

    private GMailBox enterPassword() {
        loginPassword = driver.findElement(By.name("password"));
        loginPassword.sendKeys(property.getProperty("passwordToGmailBox"));
        return this;
    }


    private GMailBox submitPassword() {
        signInButton = driver.findElement(By.id("passwordNext"));
        signInButton.click();
        return this;
    }

    /**
     * Thread.sleep() is used in the following method because of the flow of initialisation of web elements
     * on the GMailBox page: they are dynamically loaded. It means explicit wait won't work
     * properly when working with GMailBox elements and that's why the use of Thread.sleep() was needed.
     */
    public GMailBox logInGMail() {
        enterEmailAddress()
                .submitEmailAddress()
                .enterPassword()
                .submitPassword();
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return this;
    }

    public ArrayList<WebElement> getAllMails() {
        allMails = (ArrayList<WebElement>) driver.findElements(
                By.className("zA"));
        return allMails;

    }

    public WebElement getTopUnreadEmail() {
        topUnreadMail = driver.findElement(By.className("zE"));
        return topUnreadMail;

    }

    /**
     * Thread.sleep() is used in the following method because of the flow of initialisation of web elements
     * on the GMailBox page: they are dynamically loaded. It means explicit wait won't work
     * properly when working with GMailBox elements and that's why the use of Thread.sleep() was needed.
     */
    public void openTopUnreadEmail() {
        getTopUnreadEmail().click();
        ElementsCustomMethods.threadSleep(5000);

    }

    public WebElement getMailHeader(WebElement email) {
        mailHeader = email.findElement(By.className("bqe"));
        return mailHeader;

    }

    public String readHeader(WebElement email) {
        return getMailHeader(email).getAttribute("innerText");

    }

    public WebElement getVerifyEmailButton() {
        verifyEmailButton = driver.findElement(
                By.cssSelector("[href*='verifyEmail']"));
        return verifyEmailButton;

    }

    public void clickVerifyEmailButton() {
        getVerifyEmailButton().click();

    }


    public void openEmailClickLink() {
        WebElement email = getTopUnreadEmail();
        if (readHeader(email).equals("Verify your email address")) {
            openTopUnreadEmail();
            clickVerifyEmailButton();
        }

    }

    /**
     * Thread.sleep() is used in the following method because when clicking without pausing, click happens on
     * a delete button of an already deleted mail, causing a throw of exception.
     */
    public void deleteAllMails(ArrayList<WebElement> mails) {

        Actions action = new Actions(driver);

        for (WebElement mailRow : mails) {
            ElementsCustomMethods.threadSleep(5000);
            action.moveToElement(mailRow).perform();
            mailRow.findElement(
                    By.cssSelector("li.bqX.bru"))
                    .click();
        }

    }


}
