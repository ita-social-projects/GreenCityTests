package com.softserve.edu.greencity.ui.tools;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.util.Iterator;
import java.util.Set;

public class TabsHandler {

    public static String openNewTabAndGetId(WebDriver driver, Set<String> allTabs) {

        ((JavascriptExecutor) driver).executeScript("window.open()");
        Set<String> allTabsUpdated = driver.getWindowHandles();
        allTabsUpdated.removeAll(allTabs);
        Iterator iter = allTabsUpdated.iterator();
        String newTab = null;

        if (iter.hasNext()) {
            newTab = iter.next().toString();
        }
        return newTab;

    }
}
