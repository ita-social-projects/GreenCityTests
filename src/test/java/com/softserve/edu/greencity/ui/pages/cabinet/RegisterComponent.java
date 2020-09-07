package com.softserve.edu.greencity.ui.pages.cabinet;

import com.softserve.edu.greencity.ui.pages.common.TopPart;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

@Getter
public class RegisterComponent extends TopPart {

    WebDriverWait wait;

    private By closeModalButton = By.cssSelector(".close-btn a");
    public static final By modal_window_css = By.cssSelector("mat-dialog-container");
    private By submit_email_selector = By.cssSelector("app-submit-email div.submit-email");
    private By googleSignUpButtonClass = By.cssSelector(".cta-button-google");
    private By titleCss = By.cssSelector("app-sign-up h1.title-text");
    private By subtitleCss = By.cssSelector(".subtitle-text");
    private By congratsModalCss = By.cssSelector(".main-container .submit-email");
    private By signInLinkCss = By.cssSelector("div.exist-account a");
    private By emailFieldSelector = By.cssSelector("input[name='email']");
    private By userNameFieldSelector = By.cssSelector("input[name='fistName']");
    private By passwordFieldSelector = By.cssSelector("input[name='form-control password']");
    private By passwordConfirmFieldSelector = By.cssSelector("input[name='form-control password-confirm");
    private By closeSingUpButton = By.cssSelector("app-sign-up div.close-btn-img");
    private By signUpImg = By.cssSelector("div.main-image");
    private By signUpWrap = By.cssSelector("app-sign-up div.main-container");
    private By proposeSwitchToSingInText = By.cssSelector("app-sign-up div.exist-account span");
    public RegisterComponent(WebDriver driver) {
        super(driver);
    }


    // title
    @Step
    protected WebElement getTitle() {
        return searchElementByCss(titleCss);
    }

    @Step
    public String getTitleString() {
        String titleString = this.getTitle().getText();
        logger.info("Get a title text of the modal window: " + titleString);
        return titleString;
    }

    @Step
    protected WebElement getSubtitle() {
        return searchElementByCss(subtitleCss);
    }

    @Step
    public String getSubtitleString() {
        String subtitleString = this.getSubtitle().getText();
        logger.info("Get a subtitle text of the modal window: " + subtitleString);
        return subtitleString;
    }

    @Step
    public void closeRegisterComponentModal() {
        searchElementByCss(closeModalButton).click();
    }


    //Register component
    @Step
    public ManualRegisterComponent getManualRegisterComponent() {

        ManualRegisterComponent manualRegisterComponent;
        return new ManualRegisterComponent(driver);
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
        return searchElementByCss(googleSignUpButtonClass);
    }

    @Step
    public WebElement getSignInLink() {
        return searchElementByCss(signInLinkCss);
    }

    @Step
    public ManualLoginComponent clickSignInLink() {
        getSignInLink().click();
        return new ManualLoginComponent(driver);
    }

    @Step
    public WebElement getCongratsModal() {
        return searchElementByCss(congratsModalCss);
    }

    @Step
    public boolean isBackgroundDimmed() {
        boolean isDimmed = driver.findElement(By.cssSelector(".cdk-overlay-backdrop"))
                .getAttribute("class").contains("cdk-overlay-dark-backdrop cdk-overlay-backdrop-showing");
        logger.info("Background is dimmed: " + isDimmed);
        return isDimmed;
    }

    @Step
    public String getEmailFieldAttribute(String attribute) {
        String attributeValue = searchElementByCss(emailFieldSelector).getAttribute(attribute);
        logger.info("get email field " + attribute + "," + attribute +" = " + attributeValue);
        return attributeValue;
    }
    @Step
    public String getUserNameFieldAttribute(String attribute) {
        String attributeValue = searchElementByCss(userNameFieldSelector).getAttribute(attribute);
        logger.info("get userName field " + attribute + "," + attribute + " = " + attributeValue);
        return attributeValue;
    }
    @Step
    public String getPasswordFieldAttribute(String attribute) {
        String attributeValue = searchElementByCss(passwordFieldSelector).getAttribute(attribute);
        logger.info("get password field "  + attribute + "," + attribute + " = " + attributeValue);
        return attributeValue;
    }
    @Step
    public String getConfirmPasswordFieldAttribute(String attribute) {
        String attributeValue = searchElementByCss(passwordConfirmFieldSelector).getAttribute(attribute);
        logger.info("get confirm password field " + attribute + "," + attribute + " = " + attributeValue);
        return attributeValue;
    }
    @Step
    public WebElement getSingUpImage(){
        return searchElementByCss(signUpImg);
    }
    @Step
    public void closeSingUpForm(){
        logger.info("close SingUp form");
        searchElementByCss(closeSingUpButton).click();
    }
    public WebElement getProposeSwitchToSingInText(){
        return searchElementByCss(proposeSwitchToSingInText);
    }
}
