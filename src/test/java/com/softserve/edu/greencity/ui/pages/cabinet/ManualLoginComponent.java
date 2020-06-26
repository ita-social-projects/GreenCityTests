package com.softserve.edu.greencity.ui.pages.cabinet;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ManualLoginComponent extends LoginComponent {

    protected WebElement title;
    protected WebElement subtitle;

    protected WebElement emailField;
    protected WebElement passwordField;
    protected WebElement signInButton;


    private final String EMAIL_ID = "email";
    private final String PASSWORD_ID = "password";
    private final String LOGIN_BUTTON_XPATH = "//button[@type='submit' and @class='primary-global-button']";

    private final WebDriver driver;

    public ManualLoginComponent(WebDriver driver) {
        super(driver);
        this.driver = driver;

    }

// titleField


    protected WebElement getTitle() {
        this.title = driver
                .findElement(By.cssSelector("div.right-side h1"));
        return title;
    }

    protected boolean isDisplayedTitleField() {
        return getTitle().isDisplayed();
    }


    public String getTitleString(){

        return this.getTitle().getText();
    }

    // subtitle


    protected WebElement getSubtitle() {
        this.subtitle = driver
                .findElement(By.cssSelector("div.right-side h2"));
        return subtitle;
    }


    public String getSubtitleString(){

        return this.getSubtitle().getText();
    }

   //email

   public WebElement getEmailField() {
       this.emailField = driver
               .findElement(By.id(EMAIL_ID));
       return emailField;
   }

    public ManualLoginComponent inputEmail(String email) {
        this.getEmailField().sendKeys(email);
        return this;
    }

    //passwordField

    public WebElement getPasswordField() {
        this.passwordField = driver
                .findElement(By.id(PASSWORD_ID));
        return passwordField;
    }

    public ManualLoginComponent inputPassword(String password) {
        this.getPasswordField().sendKeys(password);
        return this;
    }

    //sign in button
    public WebElement getSignInButton() {
        this.signInButton = driver
                .findElement(By.xpath(LOGIN_BUTTON_XPATH));
        return passwordField;
    }

    public ManualLoginComponent clickSignInButton() {
        this.getSignInButton().click();
        return this;
    }
}
