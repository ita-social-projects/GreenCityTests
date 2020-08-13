package com.softserve.edu.greencity.ui.pages.cabinet;

import com.softserve.edu.greencity.ui.pages.common.TopPart;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Function;


public class RegisterComponent extends TopPart {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    WebDriverWait wait;
    private WebElement modalWindow;
    public static final String MODAL_WINDOW_CSS = "mat-dialog-container";
    private WebElement title;
    private final String TITLE_CSS = "h1[title-text]";
    private WebElement subtitle;
    private final String SUBTITLE_CSS = ".subtitle-text";
    private WebElement closeModalButton;

    private ManualRegisterComponent manualRegisterComponent;

    private WebElement googleSignUpButton;

    private WebElement signInLink;
    private final String SIGN_IN_LINK_CSS = "div.exist-account a";
    private WebElement signInText;

    private WebElement submitEmailText;
    private final String SUBMIT_EMAIL_SELECTOR = "app-submit-email div.submit-email";
    private final String GOOGLE_SIGN_UP_BUTTON_CLASS = ".cta-button-google";

    private WebElement congratsModal;
    private final String CONGRATS_MODAL_CSS = ".main-container .submit-email";

    public RegisterComponent(WebDriver driver) {

        super(driver);
        init();
    }

    public void init(){
        wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.visibilityOf(getTitle()));

    }


    // modal window
    public WebElement getRegisterModalWindow() {
        this.modalWindow = driver
                .findElement(By.cssSelector(MODAL_WINDOW_CSS));
        return modalWindow;
    }

    // title
    protected WebElement getTitle() {
        this.title = driver
                .findElement(By.cssSelector(TITLE_CSS));
        return title;
    }

    public String getTitleString() {
              return this.getTitle().getText();
    }

    protected WebElement getSubtitle() {
        this.subtitle = driver
                .findElement(By.cssSelector(SUBTITLE_CSS));
        return subtitle;
    }

    public String getSubtitleString() {

        return this.getSubtitle().getText();
    }

    public void closeRegisterComponentModal() {
        closeModalButton = driver.findElement
                (By.cssSelector(".close-btn a"));
        closeModalButton.click();
    }


    //Register component
    public ManualRegisterComponent getManualRegisterComponent() {

        return manualRegisterComponent = new ManualRegisterComponent(driver);
    }

    public GoogleLoginPage clickGoogleSignUpButton() {
        getGoogleSignUpButton().click();

        return new GoogleLoginPage(driver);
    }

    protected RegisterComponent setSubmitEmailText(WebElement submitEmailText) {
        this.submitEmailText = submitEmailText;
        return this;
    }

    public String getSubmitEmailText() {
        return submitEmailText.getText();
    }

    /**
     * Get text which shows after a successful registration.
     *
     * @return String
     */
    protected String getConfirmRegisterationText() {
        logger.debug("start getConfirmRegisterationText()");
        logger.trace("find WebElement submitEmailText");
        submitEmailText = driver.findElement(
                By.cssSelector(SUBMIT_EMAIL_SELECTOR));
        logger.info("get Confirm Registeration text: " + setSubmitEmailText(submitEmailText).getSubmitEmailText());
        return setSubmitEmailText(submitEmailText).getSubmitEmailText();
    }

    /**
     * Returns a WebElement of the 'GoogleSignUp' button.
     *
     * @return WebElement
     */
    protected WebElement getGoogleSignUpButton() {
        return googleSignUpButton = driver.findElement(By.cssSelector(GOOGLE_SIGN_UP_BUTTON_CLASS));
    }

    protected WebElement getSignInLink() {
        this.signInLink = driver
                .findElement(By.cssSelector(SIGN_IN_LINK_CSS));
        return signInLink;
    }

    public ManualLoginComponent clickSignInLink() {
        getSignInLink().click();
        return new ManualLoginComponent(driver);
    }

    public WebElement getCongratsModal() {
        this.congratsModal = driver
                .findElement(By.cssSelector(CONGRATS_MODAL_CSS));
        return congratsModal;
    }
}
