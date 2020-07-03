package com.softserve.edu.greencity.ui.tests;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.Set;

public class SwitchBetweentabsHelper extends GreenCityTestRunner {


//    @Test
//    public void test() {
//
//        String initialTab = driver.getWindowHandle();
//        System.out.println(initialTab);
//
//        String newlyOpenedTab = openNewTabAndGetId();
//
//        driver.switchTo().window(newlyOpenedTab);
//
//        driver.get("https://google.com");
//
//        test2();
//
//        driver.switchTo().window(newlyOpenedTab);
//
//    }
//
//    public void test2(){
//
//        String thirdTab = openNewTabAndGetId();
//        driver.switchTo().window(thirdTab);
//        driver.get("https://facebook.com");
//
//    }
//
//    public String openNewTabAndGetId() {
//
//        ((JavascriptExecutor) driver).executeScript("window.open()");
//
//        Set<String> allTabs = driver.getWindowHandles();
//
//        String newTab = allTabs.stream().skip(allTabs.size()-1).findFirst().get();
//
//        System.out.println(newTab);
//
//        return newTab;
//
//        }

    @Test
    public void test() {

        String initialTab = driver.getWindowHandle();

        Set<String> allTabs = driver.getWindowHandles();

        String newlyOpenedTab = openNewTabAndGetId(allTabs);

        driver.switchTo().window(newlyOpenedTab);

        driver.get("https://google.com");

        allTabs = driver.getWindowHandles();

        test2(allTabs);

        driver.switchTo().window(newlyOpenedTab);

    }

    public void test2(Set<String> allTabs){

        String thirdTab = openNewTabAndGetId(allTabs);
        driver.switchTo().window(thirdTab);
        driver.get("https://facebook.com");

    }

    public String openNewTabAndGetId(Set<String> allTabs) {

        ((JavascriptExecutor) driver).executeScript("window.open()");

        Set<String> allTabsUpdated = driver.getWindowHandles();

        allTabsUpdated.removeAll(allTabs);

        Iterator iter = allTabsUpdated.iterator();

        String newTab = null;

        if (iter.hasNext()){
            newTab = iter.next().toString();
        }

        System.out.println(newTab);

        return newTab;

    }


}

