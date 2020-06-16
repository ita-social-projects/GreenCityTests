package com.softserve.edu.greencity.ui.pages.cabinet;

import com.softserve.edu.greencity.ui.data.User;
import com.softserve.edu.greencity.ui.pages.common.TopPart;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * RegisterComponent class.
 *
 * @author Serg
 */
public class RegisterComponent extends TopPart {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    protected String titlePage;
    protected WebElement titleField;
    protected WebElement userNameField;
    protected WebElement emailField;
    protected WebElement passwordField;
    protected WebElement showPasswordButton;
    protected WebElement passwordConfirmField;
    protected WebElement showPasswordConfirmButton;
    protected WebElement signUpButton;
    protected WebElement googleSignUpButton;
    protected WebElement signInLink;
    protected WebElement closeModalButton;

    protected WebElement submitEmailText;

    protected WebElement emailValidator;
    protected WebElement passwordValidator;
    protected WebElement passwordConfirmValidator;


    private ManualRegisterComponent manualRegisterComponent;

    /**
     * RegisterComponent constructor.
     *
     * @param driver
     */
    public RegisterComponent(WebDriver driver) {
        super(driver);
        initElements();
    }


    private void initElements() {
        //manualRegisterComponent = new ManualRegisterComponent(driver);
    }

    // titleField

    /**
     * Taking a WebElement and set it to a private WebElement
     *
     * @param titleField WebElement
     * @return RegisterPart
     */
    protected RegisterComponent setTitleField(WebElement titleField) {
        this.titleField = titleField;
        return this;
    }

    /**
     * Returns a WebElement of the on the top page.
     *
     * @return WebElement titleField
     */
    protected WebElement getTitleField() {
        return titleField;
    }

    /**
     * Returns boolean if displayed the 'Title' field.
     *
     * @return boolean
     */
    protected boolean isDisplayedTitleField() {
        return getTitleField().isDisplayed();
    }

    public WebElement getRegisterComponentTitle() {
        titleField = driver
                .findElement(By.cssSelector("h1[title-text]"));
        this.setTitleField(titleField);
        return titleField;
    }


    public String getTitlePageText(){

        return this.getRegisterComponentTitle().getText();
    }

    //Register component
    public ManualRegisterComponent createRegisterComponent() {

        return new ManualRegisterComponent(driver);
    }

    private ManualRegisterComponent getManualRegisterComponent() {
        return manualRegisterComponent;
    }

    //Close button
    public void closeRegisterComponentModal() {
        closeModalButton = driver.findElement
                (By.cssSelector(".close-btn a"));
        closeModalButton.click();
    }

//    public TopPart closeRegisterDropdown() {
//        closeRegisterComponentModal();
//        return new TopPart(driver) {
//        };
//    }

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

    //Sign in with Google component

    // ToDo: transfer to GoogleRegisterComponent

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


}
