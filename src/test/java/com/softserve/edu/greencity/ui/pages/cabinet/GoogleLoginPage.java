package com.softserve.edu.greencity.ui.pages.cabinet;

import com.softserve.edu.greencity.ui.data.User;
import com.softserve.edu.greencity.ui.pages.tipstricks.TipsTricksPage;
import com.softserve.edu.greencity.ui.tools.ElementsCustomMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;

public class GoogleLoginPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private WebElement emailField;
    private WebElement emailNextButton;
    private WebElement passwordField;
    private WebElement passwordNextButton;
    private WebElement loggedInUser;

    private final String EMAIL_FIELD_ID = "identifierId";
    private final String EMAIL_NEXT_BUTTON_XPATH = "//*[@id='identifierNext']/div";

    private final String PASSWORD_FIELD_XPATH = ".//*[@id='password']/div[1]/div/div[1]/input";
    private final String PASSWORD_NEXT_BUTTON_XPATH = "//*[@id='passwordNext']/div";
    private final String LOGGED_IN_USER_CLASS = ".lCoei";

    public GoogleLoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getEmailField() {
        wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(EMAIL_FIELD_ID)));

        return emailField = driver.findElement(By.id(EMAIL_FIELD_ID));
    }

    public WebElement getEmailNextButton() {
        wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(EMAIL_NEXT_BUTTON_XPATH)));

        return emailNextButton = driver.findElement(By.xpath(EMAIL_NEXT_BUTTON_XPATH));
    }

    public WebElement getPasswordField() {
        wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PASSWORD_FIELD_XPATH)));

        return passwordField = driver.findElement(By.xpath(PASSWORD_FIELD_XPATH));
    }

    public WebElement getPasswordNextButton() {
        return passwordNextButton = driver.findElement(By.xpath(PASSWORD_NEXT_BUTTON_XPATH));
    }

    public WebElement getLoggedInUser() {
        return loggedInUser = driver.findElement(By.cssSelector(LOGGED_IN_USER_CLASS));
    }

    private boolean isLoginedUser() {
        ElementsCustomMethods elementsCustomMethods = new ElementsCustomMethods(driver);

        return elementsCustomMethods.isElementPresent(By.cssSelector(LOGGED_IN_USER_CLASS));
    }

    public TipsTricksPage successfulLoginByGoogle(User user) {
        String parentWindow = driver.getWindowHandle();

        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));

        ArrayList<String> windowHandles = new ArrayList();
        windowHandles.addAll(driver.getWindowHandles());

        driver.switchTo().window(windowHandles.get(1));

        if (isLoginedUser()) {
            getLoggedInUser().click();
            getPasswordField().sendKeys(user.getPassword());
            getPasswordNextButton().click();

            driver.switchTo().window(parentWindow);
            return new TipsTricksPage(driver);
        }

        getEmailField().sendKeys(user.getEmail());
        getEmailNextButton().click();

        getPasswordField().sendKeys(user.getPassword());
        getPasswordNextButton().click();

        driver.switchTo().window(parentWindow);

        return new TipsTricksPage(driver);
    }
}
