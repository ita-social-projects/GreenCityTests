package com.softserve.edu.greencity.ui.tools;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class GMailBox {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private WebDriver driver;

    public GMailBox(WebDriver driver) {
        this.driver = driver;
    }

    private WebElement mailHeader;
    private WebElement unreadMail;
    private WebElement refreshButton;
    private WebElement deleteButton;
    private WebElement verifyEmailButton;
    private Wait wait;

    public WebElement getMailHeader(){
        mailHeader = driver.findElement(By.className("bqe"));
        return mailHeader;

    }
    public String readHeader(){
        return getMailHeader().getAttribute("innerText");

    }

    public WebElement getTopUnreadEmail(){
        unreadMail = driver.findElement(By.className("zE"));
        return unreadMail;

    }

        public void openEmailClickLink() {

            if (readHeader().equals("Verify your email address")){

                getTopUnreadEmail().click();

                verifyEmailButton = driver.findElement(
                        By.cssSelector("[href*='verifyEmail']"));

                verifyEmailButton.click();

            }



    }

}
