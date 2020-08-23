package com.softserve.edu.greencity.ui.pages.cabinet;

import com.softserve.edu.greencity.ui.pages.common.TopPart;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.invisibilityOfElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;


public class RegisterComponent extends TopPart {

    WebDriverWait wait;

    private By closeModalButton = By.cssSelector(".close-btn a");
    public static final By MODAL_WINDOW_CSS = By.cssSelector("mat-dialog-container");
    private final By SUBMIT_EMAIL_SELECTOR = By.cssSelector("app-submit-email div.submit-email");
    private final By GOOGLE_SIGN_UP_BUTTON_CLASS = By.cssSelector(".cta-button-google");
    private final By TITLE_CSS = By.cssSelector("app-sign-up h1.title-text");
    private final By SUBTITLE_CSS = By.cssSelector(".subtitle-text");
    private final By CONGRATS_MODAL_CSS = By.cssSelector(".main-container .submit-email");
    private final By SIGN_IN_LINK_CSS = By.cssSelector("div.exist-account a");
    private By emailFieldSelector = By.cssSelector("input[name='email']");
    private By userNameFieldSelector = By.cssSelector("input[name='fistName']");
    private By passwordFieldSelector = By.cssSelector("input[name='form-control password']");
    private By passwordConfirmFieldSelector = By.cssSelector("input[name='form-control password-confirm");
    private By closeSingUpButton = By.cssSelector("app-sign-up div.close-btn-img");
    private By signUpImg = By.cssSelector("div.main-image");
    private By signUpWrap = By.cssSelector("app-sign-up div.main-container");

    public RegisterComponent(WebDriver driver) {
        super(driver);
    }


    // title
    @Step
    protected WebElement getTitle() {
        return searchElementByCss(TITLE_CSS);
    }

    @Step
    public String getTitleString() {
        String titleString = this.getTitle().getText();
        logger.info("Get a title text of the modal window: " + titleString);
        return titleString;
    }

    @Step
    protected WebElement getSubtitle() {
        return searchElementByCss(SUBTITLE_CSS);
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
        return searchElementByCss(GOOGLE_SIGN_UP_BUTTON_CLASS);
    }

    @Step
    protected WebElement getSignInLink() {
        return searchElementByCss(SIGN_IN_LINK_CSS);
    }

    @Step
    public ManualLoginComponent clickSignInLink() {
        getSignInLink().click();
        return new ManualLoginComponent(driver);
    }

    @Step
    public WebElement getCongratsModal() {
        return searchElementByCss(CONGRATS_MODAL_CSS);
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
    public WebElement getSingUpImg(){
        return searchElementByCss(signUpImg);
    }
    @Step
    public void closeSingUpForm(){
        logger.info("close SingUp form");
        searchElementByCss(closeSingUpButton).click();
    }
}
