package com.softserve.edu.greencity.ui.tools;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ElementsCustomMethods {


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

    public boolean waitTillElementGone(WebDriver driver, By locator, int wait, int polling) {

        Boolean gone = null;
        int a = 0;

        do {
            try {
                threadSleep(polling);
                driver.findElement(locator);
                a += polling;
                gone = false;

            } catch (org.openqa.selenium.NoSuchElementException e) {
                gone = true;
            }

        } while ((a < wait) && (gone == false));

        return gone;

    }


    public boolean isElementPresent(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }
}
