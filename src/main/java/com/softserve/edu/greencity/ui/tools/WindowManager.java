package com.softserve.edu.greencity.ui.tools;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class WindowManager {
    private WebDriver driver;

    public WindowManager(WebDriver driver) {
        this.driver = driver;
    }

    public void changeWindowWidth(int width) {
        Dimension size = driver.manage().window().getSize();
        JavascriptExecutor js= (JavascriptExecutor)driver;
        String windowSize = js.executeScript("return (window.outerWidth - window.innerWidth + "+width+"); ").toString();
        width = Integer.parseInt((windowSize));
        driver.manage().window().setSize(new Dimension(width, size.getHeight()));
    }

    public void maximizeWindow() {
        driver.manage().window().maximize();
    }
}
