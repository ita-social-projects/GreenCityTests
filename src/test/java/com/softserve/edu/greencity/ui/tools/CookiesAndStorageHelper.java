package com.softserve.edu.greencity.ui.tools;

import com.softserve.edu.greencity.ui.tests.GreenCityTestRunner;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.html5.WebStorage;


public class CookiesAndStorageHelper {
    private WebDriver driver;


    public CookiesAndStorageHelper(WebDriver driver) {
        this.driver = driver;
    }


    public void cleanCookiesAndStorages() {

        driver.manage().deleteAllCookies();

        if (!(driver instanceof WebStorage)) {
            throw new IllegalArgumentException("This test expects the driver to implement WebStorage");
        }
        WebStorage webStorage = (WebStorage) driver;
        webStorage.getSessionStorage().clear();
        webStorage.getLocalStorage().clear();


    }

    public void cleanGreenCityCookiesAndStorages() {

        driver.get(GreenCityTestRunner.BASE_URL);
        driver.manage().deleteAllCookies();

        if (!(driver instanceof WebStorage)) {
            throw new IllegalArgumentException("This test expects the driver to implement WebStorage");
        }
        WebStorage webStorage = (WebStorage) driver;
        webStorage.getSessionStorage().clear();
        webStorage.getLocalStorage().clear();



    }

    public void cleanGMailCookiesAndStorages() {
        driver.get(GMailBox.GMAIL_URL);

        driver.manage().deleteAllCookies();
        if (!(driver instanceof WebStorage)) {
            throw new IllegalArgumentException("This test expects the driver to implement WebStorage");
        }
        WebStorage webStorage = (WebStorage) driver;
        webStorage.getSessionStorage().clear();
        webStorage.getLocalStorage().clear();

    }
}
