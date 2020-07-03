//package com.softserve.edu.greencity.ui.tools;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.awt.*;
//import java.awt.datatransfer.DataFlavor;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.util.Properties;
//import java.util.concurrent.TimeUnit;
//
//public class GMailLogin {
//
//    private WebDriver driver;
//
//
//
//
//
//    public final static String URL = "https://accounts.google.com/signin/v2/identifier?" +
//            "continue=https%3A%2F%2Fmail.google.com%2Fmail%2F&service=mail&sacu=1&rip=1&" +
//            "flowName=GlifWebSignIn&flowEntry=ServiceLogin";
//
//
//    public GMailLogin(WebDriver driver) {
//
//        this.driver = driver;
//        driver.get(URL);
//
//        try {
//            final FileInputStream fis = new FileInputStream("src/test/resources/credentials.properties");
//            property.load(fis);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//
//
//
//}
