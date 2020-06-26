package com.softserve.edu.greencity.ui.tools;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.util.concurrent.TimeUnit;

public class GMailLogin {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private WebDriver driver;

    private WebElement goToLoginButton;
    private WebElement loginEmail;
    private WebElement nextButton;
    private WebElement loginPassword;
    private WebElement signInButton;



    public final static String URL = "https://accounts.google.com/signin/v2/identifier?continue=https%3A%2F%2Fmail.google.com%2Fmail%2F&service=mail&sacu=1&rip=1&flowName=GlifWebSignIn&flowEntry=ServiceLogin";


    private final String MAIL_GEEN_CITY = "mail.sender.greencity@gmail.com";

    /**
     * Constructor
     * @param driver WebDriver
     */
    public GMailLogin(WebDriver driver) {
        this.driver = driver;
    }


    public void goToLogin() throws InterruptedException {
        goToLoginButton = driver.findElement(By.cssSelector("[href*='mail.google']"));
        Thread.sleep(5000);
        goToLoginButton.click();


    }
   public void enterEmail(){
        loginEmail = driver.findElement(By.id("identifierId"));
       loginEmail.sendKeys("GCSignUpUser@gmail.com");

   }
   public void clickNext(){
       nextButton = driver.findElement(By.id("identifierNext"));
       nextButton.click();
   }

    public void enterPassword(){
        loginEmail = driver.findElement(By.name("password"));
        loginEmail.sendKeys("Error911");

    }
    public void clickNextPass(){
        signInButton = driver.findElement(By.id("passwordNext"));
        signInButton.click();
    }
    public GMailBox GMailDoLogin() throws InterruptedException {
         driver.get(URL);
        enterEmail();
        clickNext();
        enterPassword();
         clickNextPass();
        return new GMailBox(driver);

    }
}
