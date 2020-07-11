package com.softserve.edu.greencity.ui.pages.cabinet;

import com.softserve.edu.greencity.ui.pages.common.TopPart;
import com.softserve.edu.greencity.ui.pages.tipstricks.TipsTricksPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginComponent extends TopPart {
    private WebElement title;
    private WebElement subtitle;
    private WebElement forgotPasswordLink;
    private WebElement singUpLink;
    private WebElement singInWithGoogleButton;
    private WebElement closeFormButton;

    private ManualLoginComponent manualLoginComponent;

    private final String FORGOT_PASSWORD_LINK_CLASS = "forgot-password";
    private final String REGISTRATION_LINK_CLASS = "signup-link";
    private final String SING_IN_WITH_GOOGLE_BUTTON_CLASS = ".google-sign-in";
    private final String SIGN_UP_LINK_CLASS = ".missing-account .sign-up-link";
    private final String CLOSE_BUTTON_CLASS = ".cross-btn";

    public LoginComponent(WebDriver driver) {
        super(driver);
    }

    public ManualLoginComponent getManualLoginComponent() {
        return manualLoginComponent = new ManualLoginComponent(driver);
    }

    protected WebElement getTitle() {
        return title = driver.findElement(By.cssSelector("div.right-side h1"));
    }

    protected boolean isDisplayedTitleField() {
        return getTitle().isDisplayed();
    }

    public String getTitleString() {
        return this.getTitle().getText();
    }

    protected WebElement getSubtitle() {
        return subtitle = driver.findElement(By.cssSelector("div.right-side h2"));
    }

    public String getSubtitleString() {
        return this.getSubtitle().getText();
    }

    public WebElement getSignUpLink() {
        return singUpLink = driver.findElement(By.cssSelector(SIGN_UP_LINK_CLASS));
    }

    public RegisterComponent clickSignUpLink(){
        getSignUpLink().click();
        return new RegisterComponent(driver);
    }

    public WebElement getSingInWithGoogleButton() {
        return singInWithGoogleButton = driver.findElement(By.cssSelector(SING_IN_WITH_GOOGLE_BUTTON_CLASS));
    }

    public WebElement getCloseFormButton() {
        return closeFormButton = driver.findElement(By.cssSelector(CLOSE_BUTTON_CLASS));
    }

    public TipsTricksPage closeLoginComponent() {
        getCloseFormButton().click();
        return new TipsTricksPage(driver);
    }

}
