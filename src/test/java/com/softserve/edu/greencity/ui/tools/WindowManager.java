package com.softserve.edu.greencity.ui.tools;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class WindowManager {
    private WebDriver driver;

    public WindowManager(WebDriver driver) {
        this.driver = driver;
    }

    public void changeWindowWidth(int width) {
        Dimension size = driver.manage().window().getSize();

        driver.manage().window().setSize(new Dimension(width, size.getHeight()));
    }

    public void maximizeWindow() {
        driver.manage().window().maximize();
    }

    public static void takeScreenshot(WebDriver driver, String pathToStore) {
        TakesScreenshot scrShot = ((TakesScreenshot) driver);
        File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
        File DestFile = new File(pathToStore);
        try {
            FileUtils.copyFile(SrcFile, DestFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
