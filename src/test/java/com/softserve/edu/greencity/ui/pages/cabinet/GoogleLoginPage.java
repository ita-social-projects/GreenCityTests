package com.softserve.edu.greencity.ui.pages.cabinet;

import com.softserve.edu.greencity.ui.data.User;
import com.softserve.edu.greencity.ui.pages.common.WelcomePage;
import com.softserve.edu.greencity.ui.pages.tipstricks.TipsTricksPage;
import com.softserve.edu.greencity.ui.tools.ElementsCustomMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;

public class GoogleLoginPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By emailField = By.id("identifierId");
    private By emailNextButton = By.xpath("//*[@id='identifierNext']/div");
    private By passwordField = By.xpath(".//*[@id='password']/div[1]/div/div[1]/input");
    private By passwordNextButton = By.xpath("//*[@id='passwordNext']/div");
    private By loggedInUser = By.cssSelector(".lCoei");

    public GoogleLoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getEmailField() {
        wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailField));
        return driver.findElement(emailField);
    }

    public WebElement getEmailNextButton() {
        wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.elementToBeClickable(emailNextButton));
        return driver.findElement(emailNextButton);
    }

    public void clickEmailNextButton() {
        getEmailNextButton().click();
    }

    public WebElement getPasswordField() {
        wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField));
        return driver.findElement(passwordField);
    }

    public WebElement getPasswordNextButton() {
        wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.elementToBeClickable(passwordNextButton));
        return driver.findElement(passwordNextButton);
    }

    public void clickPasswordNextButton() {
        getPasswordNextButton().click();
    }

    //Fill email field
    public GoogleLoginPage fillEmail(User user) {
        getEmailField().sendKeys(user.getEmail());
        clickEmailNextButton();
        return this;
    }

    //Fill password field
    public GoogleLoginPage fillPassword(User user) {
        getPasswordField().sendKeys(user.getPassword());
        clickPasswordNextButton();
        return this;
    }

    //Fill email and password fields
    public void fillFields(User user) {
        fillEmail(user);
        fillPassword(user);
    }

    public WebElement getLoggedInUser() {
        return driver.findElement(loggedInUser);
    }

    public void clickLoggedInUser() {
        getLoggedInUser().click();
    }

    private boolean isLoginedUser() {
        ElementsCustomMethods elementsCustomMethods = new ElementsCustomMethods(driver);
        return elementsCustomMethods.isGoogleElementPresent(loggedInUser);
    }

    public WelcomePage successfulLoginByGoogle(User user) {
        String parentWindow = driver.getWindowHandle();

        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));

        ArrayList<String> windowHandles = new ArrayList();
        windowHandles.addAll(driver.getWindowHandles());

        driver.switchTo().window(windowHandles.get(1));

        if (isLoginedUser()) {
            clickLoggedInUser();
            fillPassword(user);

            driver.switchTo().window(parentWindow);
            return new WelcomePage(driver);
        }
        fillFields(user);

        driver.switchTo().window(parentWindow);
        return new WelcomePage(driver);
    }
}