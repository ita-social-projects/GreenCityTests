package com.softserve.edu.greencity.ui.tools;

import com.softserve.edu.greencity.ui.tools.engine.StableWebElementSearch;
import com.softserve.edu.greencity.ui.tools.engine.WaitsSwitcher;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.invisibilityOfElementLocated;

public class ElementsCustomMethods implements StableWebElementSearch {

    private WebDriver driver;

    public ElementsCustomMethods(WebDriver driver) {
        this.driver = driver;
    }

    public boolean waitTillElementGone(WebDriver driver, By locator, int wait) {
        try {
            WaitsSwitcher waitsSwitcher = new WaitsSwitcher(driver);
            waitsSwitcher.setExplicitWait(wait, invisibilityOfElementLocated(locator));
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
