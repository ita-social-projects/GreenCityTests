package com.softserve.edu.greencity.ui.pages.cabinet;

import com.softserve.edu.greencity.ui.pages.common.TopPart;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class RegisterComponent extends TopPart {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    protected WebElement title;
    protected WebElement subtitle;
    protected WebElement userNameField;
    protected WebElement emailField;
    protected WebElement passwordField;
    protected WebElement showPasswordButton;
    protected WebElement passwordConfirmField;
    protected WebElement showPasswordConfirmButton;
    protected WebElement signUpButton;
    protected WebElement googleSignUpButton;
    protected WebElement signInLink;
    protected WebElement signInText;
    protected WebElement closeModalButton;

    protected WebElement submitEmailText;

    protected WebElement emailValidator;
    protected WebElement passwordValidator;
    protected WebElement passwordConfirmValidator;


    private ManualRegisterComponent manualRegisterComponent;


    public RegisterComponent(WebDriver driver) {
        super(driver);
        //initElements();
    }


    // titleField

    protected RegisterComponent setTitle() {
        this.title = driver
                .findElement(By.cssSelector("h1[title-text]"));
        return this;
    }

    protected WebElement getTitle() {

        setTitle();
        return title;
    }


    public String getTitleString(){

        return this.getTitle().getText();
    }

    // subtitle

    protected RegisterComponent setSubtitle() {
        this.subtitle = driver
                .findElement(By.cssSelector(".subtitle-text"));
        return this;
    }


    protected WebElement getSubtitle() {

        setSubtitle();
        return subtitle;
    }

    public String getSubtitleString(){

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

        return new ManualRegisterComponent(driver);
    }

    public ManualRegisterComponent setRegisterComponent() {

        return this.manualRegisterComponent = getManualRegisterComponent();
    }

    private ManualRegisterComponent getManualRegisterComponent() {
        return setRegisterComponent();
    }


    // Success message???

    protected RegisterComponent setSubmitEmailText(WebElement submitEmailText) {
        this.submitEmailText = submitEmailText;
        return this;
    }

    /**
     * Returns the text after a successful registration.
     *
     * @return String
     */
    public String getSubmitEmailText() {
        return submitEmailText.getText();
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
    public RegisterComponent setSignInLink() {
        this.signInLink = driver.findElement(By.cssSelector("div.exist-account a"));
        return this;
    }
    protected WebElement getSignInLink() {
        setSignInLink();
        return signInLink;
    }

    public LoginComponent clickSignInLink() {
        getSignInLink().click();
        return new LoginComponent(driver);
    }
}
