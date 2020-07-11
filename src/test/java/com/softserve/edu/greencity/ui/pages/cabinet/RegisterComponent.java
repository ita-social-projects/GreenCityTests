package com.softserve.edu.greencity.ui.pages.cabinet;

import com.softserve.edu.greencity.ui.pages.common.TopPart;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class RegisterComponent extends TopPart {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    private WebElement title;
    private WebElement subtitle;
    private WebElement closeModalButton;

    private ManualRegisterComponent manualRegisterComponent;

    private WebElement googleSignUpButton;

    private WebElement signInLink;
    private WebElement signInText;

    private WebElement submitEmailText;
    private final String SUBMIT_EMAIL_SELECTOR = "app-submit-email div.submit-email";
    private final String GOOGLE_SIGN_UP_BUTTON_CLASS = ".cta-button-google";

    public RegisterComponent(WebDriver driver) {
        super(driver);
        init();
    }

    private void init() {
        getTitleString();
    }

    protected WebElement getTitle() {
        this.title = driver
                .findElement(By.cssSelector("h1[title-text]"));
        return title;
    }

    public String getTitleString() {
        WebDriverWait wait = new WebDriverWait(driver, 3);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1[title-text]")));

        return this.getTitle().getText();
    }

    protected WebElement getSubtitle() {
        this.subtitle = driver
                .findElement(By.cssSelector(".subtitle-text"));
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

    public ManualRegisterComponent createRegisterComponent() {

        return manualRegisterComponent = new ManualRegisterComponent(driver);
    }

    public GoogleAccountPage clickGoogleSignUpButton() {
        getGoogleSignUpButton().click();

        return new GoogleAccountPage(driver);
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
                .findElement(By.cssSelector("div.exist-account a"));
        return signInLink;
    }

    public ManualLoginComponent clickSignInLink() {
        getSignInLink().click();
        return new ManualLoginComponent(driver);
    }
}
