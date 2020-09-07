package com.softserve.edu.greencity.ui.pages.cabinet;

import com.softserve.edu.greencity.ui.data.User;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;

public class GoogleAccountManagerPage {
    private WebDriver driver;

    private WebDriverWait wait;

    private static String GOOGLE_ACCOUNT_MANAGE_URL = "https://myaccount.google.com/";
    private static String GOOGLE_MAIL_URL = "https://mail.google.com/mail/";

    private By accountButton = By.cssSelector(".gb_La");
    private By signOutButton = By.id("gb_71");

    public GoogleAccountManagerPage(WebDriver driver) {
        this.driver = driver;
    }

    //Account Button
    public WebElement getAccountButton() {
        return driver.findElement(accountButton);
    }

    public void clickAccountButton() {
        getAccountButton().click();
    }

    //Sign out Button
    public WebElement getSignOutButton() {
        return driver.findElement(signOutButton);
    }

    public void clickSignOutButton() {
        getSignOutButton().click();
    }

    public void googleAccountSignOut() {
        String parentWindow = driver.getWindowHandle();

        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript("window.open('" + GOOGLE_ACCOUNT_MANAGE_URL + "', '_blank')");

        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));

        ArrayList<String> windowHandles = new ArrayList<String>();
        windowHandles.addAll(driver.getWindowHandles());

        driver.switchTo().window(windowHandles.get(1));

        clickAccountButton();
        clickSignOutButton();

        driver.close();
        driver.switchTo().window(parentWindow);
    }
}
