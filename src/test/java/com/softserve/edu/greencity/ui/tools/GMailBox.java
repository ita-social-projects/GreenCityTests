package com.softserve.edu.greencity.ui.tools;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Iterator;

public class GMailBox {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private WebDriver driver;

    public GMailBox(WebDriver driver) {
        this.driver = driver;
    }

    private WebElement mailHeader;
    private WebElement topUnreadMail;
    private WebElement oneMail;
    private ArrayList  allMails;
    private int allMailsNumber;
    private WebElement refreshButton;
    private ArrayList deleteButtons;
    private WebElement deleteButton;
    private WebElement verifyEmailButton;


    public int getCountOfAllMails(){
        allMails = (ArrayList) driver.findElements(
                By.cssSelector(".zA"));
        allMailsNumber = allMails.size();
        return allMailsNumber;

    }
    public ArrayList<WebElement> getAllMails(){
        allMails = (ArrayList) driver.findElements(
                By.cssSelector(".zA"));
        return allMails;

    }
    public WebElement getTopUnreadEmail(){
        topUnreadMail = driver.findElement(By.className("zE"));
        return topUnreadMail;

    }
    public void openTopUnreadEmail(){
        getTopUnreadEmail().click();

    }

    public WebElement getMailHeader(){
        mailHeader = driver.findElement(By.className("bqe"));
        return mailHeader;

    }
    public String readHeader(){
        return getMailHeader().getAttribute("innerText");

    }

    public WebElement getVerifyEmailButton(){
        verifyEmailButton = driver.findElement(
                By.cssSelector("[href*='verifyEmail']"));
        return verifyEmailButton;

    }

    public void clickVerifyEmailButton(){
        getVerifyEmailButton().click();

    }

    public ArrayList<WebElement> getDeleteButtons(){
        deleteButtons = (ArrayList) driver.findElements(
                By.cssSelector("li.bqX.bru"));
        return deleteButtons;

    }


    public void openEmailClickLink() {

        if (readHeader().equals("Verify your email address")){
            openTopUnreadEmail();
            clickVerifyEmailButton();

        }

    }
    public void deleteAllMails(ArrayList <WebElement> mails){

        Actions action = new Actions(driver);

        for (WebElement mailRow: mails) {
            action.moveToElement(mailRow).perform();
            mailRow.findElement(
                    By.cssSelector("li.bqX.bru"))
                    .click();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}
