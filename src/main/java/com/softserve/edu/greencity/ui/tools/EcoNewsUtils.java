package com.softserve.edu.greencity.ui.tools;

import com.softserve.edu.greencity.ui.pages.econews.ItemsContainer;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.softserve.edu.greencity.ui.locators.EcoNewsPageLocator.DISPLAYED_ARTICLES;

public class EcoNewsUtils {

    protected static RemoteWebDriver driver;

    public EcoNewsUtils(RemoteWebDriver driver) {
        this.driver = driver;
    }

    public static int getNumberOfNewsNotCreatedBy(String username, ItemsContainer news) {
        for (int i = 0; i < news.getItemComponentsCount(); i++) {
            String name = news.chooseNewsByNumber(i).getAuthorText();
            if (!name.equals(username)) {
                return i;
            }
            if (i == news.getItemComponentsCount() - 1) {
                ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
                WebDriverWait wait = new WebDriverWait(driver, 3);
                try {
                    wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(DISPLAYED_ARTICLES.getPath(), i + 1));
                } catch (TimeoutException timeoutException) {
                    throw new RuntimeException("Not found news another authors");
                }
            }
        }
        throw new RuntimeException("Not found news another authors");
    }
}
