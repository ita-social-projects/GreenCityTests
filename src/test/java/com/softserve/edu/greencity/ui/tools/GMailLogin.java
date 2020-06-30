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

    private WebDriver driver;

    private WebElement loginEmail;
    private WebElement nextButton;
    private WebElement loginPassword;
    private WebElement signInButton;



    public final static String URL = "https://accounts.google.com/signin/v2/identifier?" +
            "continue=https%3A%2F%2Fmail.google.com%2Fmail%2F&service=mail&sacu=1&rip=1&" +
            "flowName=GlifWebSignIn&flowEntry=ServiceLogin";


    public GMailLogin(WebDriver driver) {

        this.driver = driver;
        driver.get(URL);
    }


   public GMailLogin enterEmail(){
        loginEmail = driver.findElement(By.id("identifierId"));
        loginEmail.sendKeys("GCSignUpUser@gmail.com");
        return this;
   }

   public GMailLogin clickNext(){
       nextButton = driver.findElement(By.id("identifierNext"));
       nextButton.click();
       return this;
   }

    public GMailLogin enterPassword(){
        loginPassword = driver.findElement(By.name("password"));
        loginPassword.sendKeys("Error911");
        return this;
    }

    /**
     * Thread.sleep() is used in the following method because of the flow of initialisation of web elements
     * on the GMailBox page: they are dynamically loaded. It means explicit wait won't work
     * properly when working with GMailBox elements and that's why the use of Thread.sleep() was needed.
     */
    public GMailLogin clickSignInButton(){
        signInButton = driver.findElement(By.id("passwordNext"));
        signInButton.click();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this;
    }

}
