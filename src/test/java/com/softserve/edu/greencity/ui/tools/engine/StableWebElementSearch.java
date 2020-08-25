package com.softserve.edu.greencity.ui.tools.engine;


import com.google.common.base.Function;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

/**
 * convenient way to search Web Elements
 */
//TODO replace elements search workaround with proper implementation using explicit wait
public interface StableWebElementSearch {
    /**
     * set Webdriver that should to be used
     * @return WebDriver
     */
    WebDriver setDriver();

    @Step
    @Description("Find element By CSS")
    default public WebElement searchElementByCss(By locator){
        return explicitSearch(visibilityOfElementLocated(locator));
    }
    @Step
    @Description("Find element By CSS")
    default public WebElement searchElementByCss(String locator){
        return explicitSearch(visibilityOfElementLocated(By.cssSelector(locator)));
    }
    @Step
    @Description("Find element By xpath")
    default public WebElement searchElementByXpath(By xPath){
        return explicitSearch(visibilityOfElementLocated(xPath));
    }
    @Step
    @Description("Find element By xpath")
    default public WebElement searchElementByXpath(String xPath){
        return explicitSearch(visibilityOfElementLocated(By.xpath(xPath)));
    }

    @Step
    @Description("Find elements By CSS")
    default public List<WebElement> searchElementsByCss(By locator){
        searchElementByCss(locator);
        return setDriver().findElements(locator);
    }

    @Step
    @Description("Find elements By CSS")
    default public List<WebElement> searchElementsByXpath(By locator){
        searchElementByCss(locator);
        return setDriver().findElements(locator);
    }


    @Step
    @Description("Find elements By CSS")
    default public List<WebElement> searchElementsByCss(String locator){
        searchElementByCss(locator);
        return setDriver().findElements(By.cssSelector(locator));
    }

    @Step
    @Description("Find elements By CSS")
    default public List<WebElement> searchElementsXpath(String locator){
        searchElementByCss(locator);
        return setDriver().findElements(By.cssSelector(locator));
    }

    @Step
    @Description("short explicit wait visibility Of element")
    default public <V> V explicitSearch(Function<? super WebDriver, V> condition){
        setDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        V element = (new WebDriverWait(setDriver(),20)).until(condition);
        setDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return element;
    }


}

