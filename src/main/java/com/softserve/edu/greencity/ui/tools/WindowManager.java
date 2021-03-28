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

    public void changeWindowHeight(int height) {
        Dimension size = driver.manage().window().getSize();
        JavascriptExecutor js= (JavascriptExecutor)driver;
        String windowSize = js.executeScript("return (window.outerHeight - window.innerHeight + "+height+"); ").toString();
        height = Integer.parseInt((windowSize));
        driver.manage().window().setSize(new Dimension(height, size.getWidth()));
    }

    public void maximizeWindow() {
        driver.manage().window().maximize();
    }
    public void setWindowsDimensions(int width, int height){
        Dimension sizeH = driver.manage().window().getSize();
        Dimension sizeW = driver.manage().window().getSize();

        JavascriptExecutor js = (JavascriptExecutor)driver;
        String windowSizeH = js.executeScript("return (window.outerHeight - window.innerHeight + "+height+"); ").toString();
        String windowSizeW = js.executeScript("return (window.outerWidth - window.innerWidth + "+width+"); ").toString();
        height = Integer.parseInt((windowSizeH));
        width = Integer.parseInt((windowSizeW));
        driver.manage().window().setSize(new Dimension(width,height));

    }

}
