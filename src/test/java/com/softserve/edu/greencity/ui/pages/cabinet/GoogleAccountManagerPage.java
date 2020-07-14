package com.softserve.edu.greencity.ui.pages.cabinet;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;

public class GoogleAccountManagerPage {
    private WebDriver driver;

    private static String ACCOUNT_TOP_RIGHT_BUTTON_CLASS = ".gb_Ta";
    private static String SIGN_OUT_BUTTON_ID = "gb_71";
    private static String GOOGLE_ACCOUNT_MANAGE_URL = "https://myaccount.google.com/";

    public GoogleAccountManagerPage(WebDriver driver) {
        this.driver = driver;
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

        driver.findElement(By.cssSelector(ACCOUNT_TOP_RIGHT_BUTTON_CLASS)).click();
        driver.findElement(By.id(SIGN_OUT_BUTTON_ID)).click();

        driver.close();
        driver.switchTo().window(parentWindow);
    }
}
