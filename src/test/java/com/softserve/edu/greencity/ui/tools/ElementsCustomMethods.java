package com.softserve.edu.greencity.ui.tools;

import com.softserve.edu.greencity.ui.tools.engine.StableWebElementSearch;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.invisibilityOfElementLocated;

public class ElementsCustomMethods implements StableWebElementSearch {

    private WebDriver driver;

    public ElementsCustomMethods(WebDriver driver) {
        this.driver = driver;
    }

    public static void threadSleep(int sleep) {
        try {
            Thread.sleep(sleep);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean waitTillElementGone(WebDriver driver, By locator, int wait) {
        try {
            new WebDriverWait(driver, wait).until(invisibilityOfElementLocated(locator));
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public boolean isElementPresent(By locator) {
        try {
            searchElementByCss(locator);
            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }

    }

    public boolean isGoogleElementPresent(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    @Override
    public WebDriver setDriver() {
        return this.driver;
    }
}