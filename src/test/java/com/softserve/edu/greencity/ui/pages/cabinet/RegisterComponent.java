package com.softserve.edu.greencity.ui.pages.cabinet;

import com.softserve.edu.greencity.ui.pages.common.TopPart;
import com.softserve.edu.greencity.ui.tools.StableWebElementSearch;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class RegisterComponent extends TopPart implements StableWebElementSearch {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    WebDriverWait wait;

    private By closeModalButton = By.cssSelector(".close-btn a");
    public static final By MODAL_WINDOW_CSS = By.cssSelector("mat-dialog-container");
    private final By SUBMIT_EMAIL_SELECTOR = By.cssSelector("app-submit-email div.submit-email");
    private final By GOOGLE_SIGN_UP_BUTTON_CLASS = By.cssSelector(".cta-button-google");
    private final By TITLE_CSS = By.cssSelector("app-sign-up h1.title-text");
    private final By SUBTITLE_CSS = By.cssSelector(".subtitle-text");
    private final By CONGRATS_MODAL_CSS = By.cssSelector(".main-container .submit-email");
    private final By SIGN_IN_LINK_CSS = By.cssSelector("div.exist-account a");
    private ManualRegisterComponent manualRegisterComponent;


    public RegisterComponent(WebDriver driver) {
        super(driver);
        init();
    }

    @Step
    public void init() {
        wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(getTitle()));
    }


    // modal window
    @Step
    public WebElement getRegisterModalWindow() {
        return SearchElementByCss(MODAL_WINDOW_CSS);
    }

    // title
    @Step
    protected WebElement getTitle() {
        return SearchElementByCss(TITLE_CSS);
    }

    @Step
    public String getTitleString() {
        return this.getTitle().getText();
    }

    @Step
    protected WebElement getSubtitle() {
        return SearchElementByCss(SUBTITLE_CSS);
    }

    @Step
    public String getSubtitleString() {

        return this.getSubtitle().getText();
    }

    @Step
    public void closeRegisterComponentModal() {
        SearchElementByCss(closeModalButton).click();
    }


    //Register component
    @Step
    public ManualRegisterComponent getManualRegisterComponent() {

        return manualRegisterComponent = new ManualRegisterComponent(driver);
    }

    @Step
    public GoogleLoginPage clickGoogleSignUpButton() {
        getGoogleSignUpButton().click();

        return new GoogleLoginPage(driver);
    }


    /**
     * Returns a WebElement of the 'GoogleSignUp' button.
     *
     * @return WebElement
     */
    @Step
    protected WebElement getGoogleSignUpButton() {
        return SearchElementByCss(GOOGLE_SIGN_UP_BUTTON_CLASS);
    }

    @Step
    protected WebElement getSignInLink() {
        return SearchElementByCss(SIGN_IN_LINK_CSS);
    }

    @Step
    public ManualLoginComponent clickSignInLink() {
        getSignInLink().click();
        return new ManualLoginComponent(driver);
    }

    @Step
    public WebElement getCongratsModal() {
        return SearchElementByCss(CONGRATS_MODAL_CSS);
    }

    @Override
    public WebDriver setDriver() {
        return this.driver;
    }
}
