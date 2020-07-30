package com.softserve.edu.greencity.ui.tools;

import com.softserve.edu.greencity.ui.tests.GreenCityTestRunner;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class UIListener implements ITestListener {
    @Override
    public void onTestStart(ITestResult result) {

    }

    @Override
    public void onTestSuccess(ITestResult result) {

    }

    @Override
    public void onTestFailure(ITestResult result) {
        WebDriver driver = ((GreenCityTestRunner)result.getInstance()).getDriver();
        attachScreenshot(((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES));
    }

    @Attachment(value = "Screenshot")
    public byte[] attachScreenshot(byte[] screenshot) {
        return screenshot;
    }

    @Override
    public void onTestSkipped(ITestResult result) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext context) {

    }

    @Override
    public void onFinish(ITestContext context) {

    }
}
