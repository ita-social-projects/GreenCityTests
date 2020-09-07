package com.softserve.edu.greencity.ui.pages.cabinet;
import com.softserve.edu.greencity.ui.data.User;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;

public class GmailManagerPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private static String GOOGLE_MAIL_URL = "https://mail.google.com/mail/";

    private By gmailLogo = By.cssSelector("img.gb_va");
    private By searchLine = By.name("q");
    private By searchIcon = By.cssSelector(".gb_Df");
    private By numberOfEmails = By.xpath("(//span[@class='Dj']/span[@class='ts'])[2]");
    private By checkbox = By.cssSelector(".T-Jo");
    private By checkboxDropdown = By.cssSelector(".J-J5-Ji .G-asx");
    private By checkboxDropdownAll = By.cssSelector(".J-N-Jz");
    private By deleteIcon = By.cssSelector(".asa");
    private By emptyMail = By.cssSelector(".aRv");
    private By moreCategoriesDropdown = By.cssSelector(".G-asx");
    private By allEmailDropdown = By.cssSelector(".J-N-Jz");
    private By allMailButton = By.xpath("(//div[@class='HwgYue']//div[@class='aim'])[4]");


    public GmailManagerPage(WebDriver driver) {
        this.driver = driver;
    }

    //Gmail logo
    public WebElement getGmailLogo() {
        return driver.findElement(gmailLogo);
    }

    //Search line
    public WebElement getSearchLine() {
        wait = new WebDriverWait(driver, 3);
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchIcon));
        return driver.findElement(searchLine);
    }

    public void fillSearchLine(String text) {
        getSearchLine().sendKeys(text);
    }

    //Search icon
    public WebElement getSearchIcon() {
        wait = new WebDriverWait(driver, 3);
        wait.until(ExpectedConditions.elementToBeClickable(searchIcon));
        return driver.findElement(searchIcon);
    }

    public void clickSearchIcon() {
        getSearchIcon().click();
    }

    //Checkbox
    public WebElement getCheckbox() {
        wait = new WebDriverWait(driver, 3);
        wait.until(ExpectedConditions.elementToBeClickable(checkbox));
        return driver.findElement(checkbox);
    }

    public void clickCheckbox() {
        getCheckbox().click();
    }

    //Checkbox Dropdown
    public WebElement getCheckboxDropdown() {
        return driver.findElement(checkboxDropdown);
    }

    public void clickCheckboxDropdown() {
        getCheckboxDropdown().click();
    }

    //Checkbox Dropdown all category
    public WebElement getCheckboxDropdownAll() {
        return driver.findElement(checkboxDropdownAll);
    }

    public void clickCheckboxDropdownAll() {
        getCheckboxDropdownAll().click();
    }

    //Number of emails
    public WebElement getNumberOfEmails() {
        wait = new WebDriverWait(driver, 3);
        wait.until(ExpectedConditions.visibilityOfElementLocated(numberOfEmails));
        return driver.findElement(numberOfEmails);
    }

    public String getNumberOfEmailsText() {
        return getNumberOfEmails().getText();
    }

    //Delete icon
    public WebElement getDeleteIcon() {
        wait = new WebDriverWait(driver, 3);
        wait.until(ExpectedConditions.elementToBeClickable(deleteIcon));
        return driver.findElement(deleteIcon);
    }

    public void clickDeleteIcon() {
        getDeleteIcon().click();
    }

    public boolean IsDisplayedDeleteIcon() {
        return getDeleteIcon().isDisplayed();
    }

    //Empty mail message
    public WebElement getEmptyMail() {
        return driver.findElement(emptyMail);
    }

    public boolean isDisplayedEmptyMail() {
        return getEmptyMail().isDisplayed();
    }


    //More emails categories
    public WebElement getMoreCategoriesDropdown() {
        wait = new WebDriverWait(driver, 3);
        wait.until(ExpectedConditions.elementToBeClickable(moreCategoriesDropdown));
        return driver.findElement(moreCategoriesDropdown);
    }

    public void clickMoreCategoriesDropdown() {
        getMoreCategoriesDropdown().click();
    }

    //All mail button
    public WebElement getAllMailButton() {
        wait = new WebDriverWait(driver, 3);
        wait.until(ExpectedConditions.elementToBeClickable(allMailButton));
        return driver.findElement(allMailButton);
    }

    public void clickAllEmail() {
        getAllMailButton().click();
    }

    public void googleGmailLogIn(User user) {
        GoogleLoginPage googleLoginPage = new GoogleLoginPage(driver);
        String parentWindow = driver.getWindowHandle();

        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript("window.open('" + GOOGLE_MAIL_URL + "', '_blank')");

        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));

        ArrayList<String> windowHandles = new ArrayList<String>();
        windowHandles.addAll(driver.getWindowHandles());

        driver.switchTo().window(windowHandles.get(1));

        googleLoginPage.fillFields(user);
        wait.until(ExpectedConditions.visibilityOf(getGmailLogo()));

        driver.close();
        driver.switchTo().window(parentWindow);
    }

    public int numberOfEmailBySubject(String searchText) {
        String parentWindow = driver.getWindowHandle();

        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript("window.open('" + GOOGLE_MAIL_URL + "', '_blank')");

        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));

        ArrayList<String> windowHandles = new ArrayList<String>();
        windowHandles.addAll(driver.getWindowHandles());
        driver.switchTo().window(windowHandles.get(1));

        fillSearchLine(searchText);
        clickSearchIcon();
        clickCheckbox();
        int result = 0;
        if (IsDisplayedDeleteIcon()) {
            String countOfEmail = getNumberOfEmailsText();
            result = Integer.parseInt(countOfEmail);
        }
        System.out.println(result);
        driver.close();
        driver.switchTo().window(parentWindow);

        return result;
    }

    public void deleteEmailsBySubject()  {
        String parentWindow = driver.getWindowHandle();

        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript("window.open('" + GOOGLE_MAIL_URL + "', '_blank')");

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));

        ArrayList<String> windowHandles = new ArrayList<String>();
        windowHandles.addAll(driver.getWindowHandles());
        driver.switchTo().window(windowHandles.get(1));

        if (!isDisplayedEmptyMail()) {
            clickCheckbox();
            clickDeleteIcon();
            wait.until(ExpectedConditions.visibilityOf(getEmptyMail()));
        }
        driver.close();
        driver.switchTo().window(parentWindow);
    }
}
