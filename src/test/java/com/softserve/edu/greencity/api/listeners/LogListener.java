package com.softserve.edu.greencity.api.listeners;

import io.qameta.allure.Attachment;
import io.restassured.RestAssured;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class LogListener implements ITestListener {
    private ByteArrayOutputStream request = new ByteArrayOutputStream();
    private PrintStream requestVar = new PrintStream(request, true);

    private ByteArrayOutputStream response = new ByteArrayOutputStream();
    private PrintStream responseVar = new PrintStream(response, true);
    Logger logger;

    @Override
    public void onStart(ITestContext context) {
        logger = LoggerFactory.getLogger(context.getName());
        RestAssured.filters(new RequestLoggingFilter(LogDetail.ALL, requestVar),
                new ResponseLoggingFilter(LogDetail.ALL, responseVar));
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        String logFileName = "ThreadId_" + Thread.currentThread().getId() + "_" + result.getName() + result.getStartMillis();
        MDC.put("logFileName", logFileName);

        logger.debug(result.getName() + ":\n" + request.toString() + "\n" + response.toString());

        attachLogRequest(request);
        attachLogResponse(response);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        onTestSuccess(result);
    }

    @Attachment(value = "request")
    public byte[] attachLogRequest(ByteArrayOutputStream stream) {
        return attach(stream);
    }

    @Attachment(value = "response")
    public byte[] attachLogResponse(ByteArrayOutputStream stream) {
        return attach(stream);
    }

    private byte[] attach(ByteArrayOutputStream stream) {
        byte[] bytes = stream.toByteArray();
        stream.reset();

        return bytes;
    }

    @Override
    public void onTestStart(ITestResult result) {

    }

    @Override
    public void onTestSkipped(ITestResult result) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onFinish(ITestContext context) {

    }
}
