package com.softserve.edu.greencity.ui.tools.engine;


import com.google.common.base.Function;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * convenient way to search Web Elements
 */
public interface StableWebElementSearch {
    /**
     * set Webdriver that should to be used
     * @return WebDriver
     */
    WebDriver setDriver();

    @Step
    @Description("Find element By CSS")
    default public WebElement searchElementByCss(By locator){
        return explicitSearch(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    @Step
    @Description("Find element By xpath")
    default public WebElement searchElementByXpath(By xPath){
        return explicitSearch(ExpectedConditions.visibilityOfElementLocated(xPath));
    }

    @Step
    @Description("short explicit wait visibility Of element")
    default public <V> V explicitSearch(Function<? super WebDriver, V> condition){
        setDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        V element = (new WebDriverWait(setDriver(),5)).until(condition);
        setDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return element;
    }

}

