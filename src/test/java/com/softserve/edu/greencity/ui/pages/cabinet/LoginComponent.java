package com.softserve.edu.greencity.ui.pages.cabinet;

import com.softserve.edu.greencity.ui.pages.common.LoginPart;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.softserve.edu.greencity.ui.pages.common.LoginPart;

public class LoginComponent extends LoginPart {

    protected WebElement title;
    protected WebElement subtitle;
    private final String EMAIL_ID = "email";
    private final String PASSWORD_ID = "password";
    private final String LOGIN_BUTTON_XPATH = "//button[@type='submit' and @class='primary-global-button']";
    private final String GOOGLE_LOGIN_BUTTON_CLASS = "google-sign-in";
    private final String FORGOT_PASSWORD_LINK_CLASS = "forgot-password";
    //private final String REGISTRATION_LINK_CLASS = "signup-link";

    private final WebDriver driver;

    private WebElement singUpLink;

    public LoginComponent(WebDriver driver) {
        this.driver = driver;
        initElements();
    }

    private void initElements() {
        this.setEmailField(driver.findElement(By.id(EMAIL_ID)))
                .setPasswordField(driver.findElement(By.id(PASSWORD_ID)))
                .setForgotPasswordLink(driver.findElement(By.className(FORGOT_PASSWORD_LINK_CLASS)))
                .setGoogleSignInButton(driver.findElement(By.className(GOOGLE_LOGIN_BUTTON_CLASS)))
                .setSignInButton(driver.findElement(By.xpath(LOGIN_BUTTON_XPATH)));
    }

    public RegisterComponent gotoRegisterPage() {
        getSignUpLink().click();
        return new RegisterComponent(driver);
    }
// titleField

    /**
     * Taking a WebElement and set it to a private WebElement
     *
     * @return RegisterPart
     */
    protected LoginComponent setTitle() {
        this.title = driver
                .findElement(By.cssSelector("div.right-side h1"));
        return this;
    }

    /**
     * Returns a WebElement of the on the top page.
     *
     * @return WebElement titleField
     */
    protected WebElement getTitle() {

        setTitle();
        return title;
    }

    /**
     * Returns boolean if displayed the 'Title' field.
     *
     * @return boolean
     */
    protected boolean isDisplayedTitleField() {
        return getTitle().isDisplayed();
    }


    public String getTitleString(){

        return this.getTitle().getText();
    }

    // subtitle

    /**
     * Taking a WebElement and set it to a private WebElement
     * @return RegisterPart
     */
    protected LoginComponent setSubtitle() {
        this.subtitle = driver
                .findElement(By.cssSelector("div.right-side h2"));
        return this;
    }

    /**
     * Returns a WebElement of the on the top page.
     *
     * @return WebElement subtitle
     */
    protected WebElement getSubtitle() {

        setSubtitle();
        return subtitle;
    }


    public String getSubtitleString(){

        return this.getSubtitle().getText();
    }

}
