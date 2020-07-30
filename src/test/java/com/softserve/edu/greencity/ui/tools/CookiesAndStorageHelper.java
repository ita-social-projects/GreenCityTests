package com.softserve.edu.greencity.ui.tools;

import com.softserve.edu.greencity.ui.pages.cabinet.GoogleLoginPage;
import com.softserve.edu.greencity.ui.tests.GreenCityTestRunner;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.html5.WebStorage;

public class CookiesAndStorageHelper {
    private WebDriver driver;
    public static final String GMAIL_URL = "https://accounts.google.com/signin/v2/identifier?" +
            "continue=https%3A%2F%2Fmail.google.com%2Fmail%2F&service=mail&sacu=1&rip=1&" +
           "flowName=GlifWebSignIn&flowEntry=ServiceLogin";
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
        driver.get(GMAIL_URL);

        driver.manage().deleteAllCookies();
        if (!(driver instanceof WebStorage)) {
            throw new IllegalArgumentException("This test expects the driver to implement WebStorage");
        }
        WebStorage webStorage = (WebStorage) driver;
        webStorage.getSessionStorage().clear();
        webStorage.getLocalStorage().clear();
    }

    public void cleanGoogleAuthCookiesAndStorages() {
        driver.get(GoogleLoginPage.GOOGLE_LOGIN_PAGE_URL);

        driver.manage().deleteAllCookies();
        if (!(driver instanceof WebStorage)) {
            throw new IllegalArgumentException("This test expects the driver to implement WebStorage");
        }
        WebStorage webStorage = (WebStorage) driver;
        webStorage.getSessionStorage().clear();
        webStorage.getLocalStorage().clear();
    }
}
