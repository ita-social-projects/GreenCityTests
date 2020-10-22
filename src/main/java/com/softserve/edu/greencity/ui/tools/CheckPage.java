package com.softserve.edu.greencity.ui.tools;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckPage {
    public static void waitForLoading(WebDriver driver, WebElement...elements) {
        Wait<WebDriver> wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.visibilityOfAllElements(elements));
    }
}
