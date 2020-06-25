package com.softserve.edu.greencity.ui.pages.cabinet;

import com.softserve.edu.greencity.ui.pages.common.TopPart;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

    public RegisterComponent(WebDriver driver) {
        super(driver);
    }


    // title
    protected WebElement getTitle() {
        this.title = driver
                .findElement(By.cssSelector("h1[title-text]"));
        return title;
    }


    public String getTitleString() {

        return this.getTitle().getText();
    }

    // subtitle
    protected WebElement getSubtitle() {
        this.subtitle = driver
                .findElement(By.cssSelector(".subtitle-text"));
        return subtitle;
    }

    public String getSubtitleString() {

        return this.getSubtitle().getText();
    }

    //Close button
    public void closeRegisterComponentModal() {
        closeModalButton = driver.findElement
                (By.cssSelector(".close-btn a"));
        closeModalButton.click();
    }


    //Register component
    public ManualRegisterComponent createRegisterComponent() {

        return manualRegisterComponent = new ManualRegisterComponent(driver);
    }


    // Success message???
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
    // Sign Up with Google button

    /**
     * Returns a WebElement of the 'GoogleSignUp' button.
     *
     * @return WebElement
     */
    protected WebElement getGoogleSignUpButton() {
        return googleSignUpButton;
    }

    /**
     * Click on the 'GoogleSignUp' button.
     *
     * @return RegisterPart
     */
    protected RegisterComponent clickGoogleLoginButton() {
        if (isDisplayedGoogleSignUpButton()) {
            this.getGoogleSignUpButton().click();
        }
        return this;
    }

    /**
     * Taking a WebElement and set it to a private WebElement field.
     *
     * @param googleSignUpButton WebElement
     * @return RegisterPart
     */
    public RegisterComponent setGoogleSignUpButton(WebElement googleSignUpButton) {
        this.googleSignUpButton = googleSignUpButton;
        return this;
    }

    /**
     * Returns boolean if displayed the 'GoogleSignUp' button.
     *
     * @return boolean
     */
    protected boolean isDisplayedGoogleSignUpButton() {
        return getGoogleSignUpButton().isDisplayed();
    }

    /**
     * Returns a text which displayed on the 'GoogleSignUp' button.
     *
     * @return String
     */
    protected String getGoogleSignUpButtonText() {
        return getGoogleSignUpButton().getText();
    }


    // Sign In button
    protected WebElement getSignInLink() {
        this.signInLink = driver
                .findElement(By.cssSelector("div.exist-account a"));
        return signInLink;
    }

    public LoginManualComponent clickSignInLink() {
        getSignInLink().click();
        return new LoginManualComponent(driver);
    }
}
