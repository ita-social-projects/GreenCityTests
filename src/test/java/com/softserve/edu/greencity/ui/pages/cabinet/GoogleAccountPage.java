package com.softserve.edu.greencity.ui.pages.cabinet;

import com.softserve.edu.greencity.ui.data.User;
import com.softserve.edu.greencity.ui.pages.tipstricks.TipsTricksPage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

public class GoogleAccountPage {
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

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    public GoogleAccountPage(WebDriver driver) {
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
        try {
            getLoggedInUser();
            return true;
        } catch (NoSuchElementException | TimeoutException e) {
            return false;
        }
    }

    public TipsTricksPage successfulLoginByGoogle(User user) {
        String parentWindow = driver.getWindowHandle();

        wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));

        Set<String> windowHandles = driver.getWindowHandles();
        for (String nextWindow : windowHandles) {
            if(!parentWindow.equalsIgnoreCase(nextWindow)) {
                driver.switchTo().window(nextWindow);

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
            }
        }

        return new TipsTricksPage(driver);
    }
}
