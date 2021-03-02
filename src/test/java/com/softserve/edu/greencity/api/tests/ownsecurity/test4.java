package com.softserve.edu.greencity.api.tests.ownsecurity;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import com.softserve.edu.greencity.ui.tests.runner.GreenCityTestRunner;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class test4 extends GreenCityTestRunner{
    @Test
    public void greenCityTest() throws InterruptedException {
        driver.findElement(By.xpath("//div[@class='header_navigation-menu']/nav/ul/li[last()]/a")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//button[@class='google-sign-in']")).click();
        Thread.sleep(3000);
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        WebElement element = driver.findElement(By.xpath("//input[@type='email']"));
//        Thread.sleep(3000);
        element.sendKeys("u.mybright@gmail.com");
        driver.findElement(By.cssSelector("#identifierNext > div > button")).click();
}}
