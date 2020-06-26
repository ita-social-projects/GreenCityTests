package com.softserve.edu.greencity.ui.tools;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GMailBox {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private WebDriver driver;

    public GMailBox(WebDriver driver) {
        this.driver = driver;
    }

    private WebElement mailHeader;
    private WebElement refreshButton;
    private WebElement deleteButton;
    private WebElement verifyEmailButton;

    public String readHeader(){
        mailHeader = driver.findElement(By.className("bqe"));
        return mailHeader.getAttribute("innerText");

    }

    public void openEmailClickLink() {
        logger.debug("start openEmailClickLink()");
        if (readHeader() == "Verify your email address"){
            mailHeader.click();
            verifyEmailButton = driver.findElement(
                    By.xpath("//*[contains(@href,'openEmailClickLink')]"));
        }
       //refresh

        //click Verify
    }

}
