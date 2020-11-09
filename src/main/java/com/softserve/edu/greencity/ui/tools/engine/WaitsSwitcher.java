package com.softserve.edu.greencity.ui.tools.engine;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * A class intended to help easily switching between implicit and explicit waits
 */
public final class WaitsSwitcher {
    private WebDriver driver;
    private long defaultImplicitWait;
    private long defaultExplicitWait;

    public WaitsSwitcher(WebDriver driver, long defaultImplicitWait, long defaultExplicitWait) {
        this.driver = driver;
        this.defaultImplicitWait = defaultImplicitWait;
        this.defaultExplicitWait = defaultExplicitWait;
    }
    public WaitsSwitcher(WebDriver driver, long defaultWait) {
        this.driver = driver;
        defaultImplicitWait = defaultWait;
        defaultExplicitWait = defaultWait;
    }
    public WaitsSwitcher(WebDriver driver) {
        this.driver = driver;
        defaultImplicitWait = 5;
        defaultExplicitWait = 10;
    }

    /**
     * A wrap for Thread.sleep
     * @param millis - time in milliseconds (could be decimal)
     */
    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * A wrap for driver.manage().timeouts().implicitlyWait
     * @param seconds - time in seconds
     */
    public void setImplicitWait(long seconds) {
        if (seconds > 0) {
            defaultImplicitWait = seconds;
        }
        driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
    }
    /**
     * A wrap for driver.manage().timeouts().implicitlyWait
     */
    public void setImplicitWait() {
        setImplicitWait(defaultImplicitWait);
    }

    /**
     * Nullifies implicit wait, explicitly waits for a web element or an event, and then restores the implicit wait.
     * Also returns the object waited for
     * @param seconds - time in seconds
     * @param expectedCondition - boolean function, something like ExpectedConditions.presenceOfElementLocated()
     */
    public <V> V setExplicitWait(long seconds, Function<? super WebDriver, V> expectedCondition) {
        defaultExplicitWait = seconds;
        setImplicitWait(0);
        WebDriverWait driverWait = new WebDriverWait(driver, seconds);
        V res = driverWait.until(expectedCondition);
        setImplicitWait(defaultImplicitWait);
        return res;
    }
    /**
     * Nullifies implicit wait, explicitly waits for a web element or an event, and then restores the implicit wait
     * Also returns the object waited for
     * @param expectedCondition - boolean function, something like ExpectedConditions.presenceOfElementLocated()
     */
    public <V> V setExplicitWait(Function<? super WebDriver, V> expectedCondition) {
        return setExplicitWait(defaultExplicitWait, expectedCondition);
    }

    /**
     * The site performs the same GET request twice and redraws page, so StaleElementReferences appear
     * This method retries the explicit search several times
     * No sense to use it with the conditions that re-locate an element
     */
    public <V> V setExplicitWaitWithStaleReferenceWrap(long seconds, Function<? super WebDriver, V> expectedCondition,
                                                       int retries) {
        //The site performs the same GET request twice and redraws page, so StaleElementReferences appear
        Logger logger = LoggerFactory.getLogger("WaitsSwitcher");
        do {
            try {
                return setExplicitWait(seconds, expectedCondition);
            } catch (StaleElementReferenceException error) {
                logger.warn("StaleElementReferenceException caught, retrying...");
                WaitsSwitcher.sleep(100);
            }
            retries--;
        } while (retries > 0);
        return setExplicitWait(seconds, expectedCondition);
    }

    public <V> V setExplicitWaitWithStaleReferenceWrap(long seconds, Function<? super WebDriver, V> expectedCondition) {
        return setExplicitWaitWithStaleReferenceWrap(seconds, expectedCondition, 5);
    }

    private static long secondsToMillis(double seconds) {
        return Math.round(seconds * 1000.0);
    }
}

