package com.softserve.edu.greencity.ui.tools.testng;

import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TestNgListeners implements ITestListener {

    private Logger log = LoggerFactory.getLogger("Listener");

    @Override
    public void onTestFailure(ITestResult result) {
        log.warn("The name of the testcase failed is: {}", result.getName());
        //attachLogFile(result.getInstance().getClass().getSimpleName());
        //attachRequestLogFile(result.getName());
        ITestContext context = result.getTestContext();
        WebDriver driver = (WebDriver) context.getAttribute("driver");
        if (driver != null) {
            attachScreenshot(driver);
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        log.info("The name of the testcase Skipped is: {}", result.getName());
        //attachLogFile(result.getInstance().getClass().getSimpleName());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {

    }

    @Override
    public void onFinish(ITestContext iTestContext) {

    }

    @Override
    public void onTestStart(ITestResult result) {
        log.info("{}.{} test case started",
                result.getInstance().getClass().getSimpleName(), result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        log.info("The name of the testcase passed is: {}", result.getName());
    }

    //---------------------------------------------------------------------------------
    public void takeScreenshotToFile(String testName, WebDriver driver) {
        String filename = System.getProperty("user.dir") + "\\target\\logs\\"
                + testName + ".png";
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scrFile, new File(filename));
            log.info("*** Screenshot taken: " + filename + " ***");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] attachScreenshot(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
/*
    @Attachment(value = "Logs for {className}", type = "text/plain", fileExtension = ".log")
    public byte[] attachLogFile(String className) {
        try {
            Path path = Paths.get(System.getProperty("user.dir") + "\\target\\logs\\"
                    + className + ".log");
            return Files.readAllBytes(path);
        } catch (IOException ignored) {
            log.warn("Logs for " + className + " are unavailable");
        }
        return null;
    }

    @Attachment(value = "Request logs for {testName}", type = "text/plain", fileExtension = ".log")
    public byte[] attachRequestLogFile(String testName) {
        try {
            Path path = Paths.get(System.getProperty("user.dir") + "\\target\\logs\\"
                    + testName + "_requests.log");
            return Files.readAllBytes(path);
        } catch (IOException ignored) {
            log.warn("Request logs for " + testName + " are unavailable");
        }
        return null;
    }
*/
}

