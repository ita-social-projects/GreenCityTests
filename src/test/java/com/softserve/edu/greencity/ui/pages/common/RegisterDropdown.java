package com.softserve.edu.greencity.ui.pages.common;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.softserve.edu.greencity.ui.data.User;
import com.softserve.edu.greencity.ui.pages.cabinet.GoogleAccountPage;
import com.softserve.edu.greencity.ui.tools.GetMail10MinTools;

/**
 * RegisterDropdown class.
 * @author Serg
 */
public class RegisterDropdown extends RegisterPart {

    private WebDriver driver;
    private WebDriverWait wait;
    //
    private WebElement closeRegisterDropdownButton;
    //
    private final String EMAIL_VALIDATOR_SELECTOR = "app-new-sign-up input[name='email'] + div div";
//    private final String FIRST_NAME_VALIDATOR_SELECTOR = ""; // not exist
    private final String PASSWORD_VALIDATOR_SELECTOR = "app-new-sign-up input[name='fistName'] + label.content-label + div + div div";
    private final String PASSWORD_CONFIRM_VALIDATOR_SELECTOR = "app-new-sign-up label.content-label.under-error + div + div div";
    private final String SUBMIT_EMAIL_SELECTOR = "app-submit-email div.submit-email";
    //

    /**
     * RegisterDropdown constructor
     * @param driver WebDriver
     */
    public RegisterDropdown(WebDriver driver) {
        this.driver = driver;
        initElements();
    }

    /**
     * Initialize web elements
     */
    private void initElements() {
        // init elements
        wait = new WebDriverWait(driver, 10);
        //
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("h1[title-text]")));
        driver.manage().timeouts().implicitlyWait(5,
                TimeUnit.SECONDS);
        //
        titleField = driver.findElement(By.cssSelector("h1[title-text]"));
        emailField = driver.findElement(By.cssSelector("input[name='email']"));
        userNameField = driver
                .findElement(By.cssSelector("input[name='fistName']"));
        passwordField = driver.findElement(
                By.cssSelector("input[name='form-control password']"));
        showPasswordButton = driver.findElement(
                By.xpath("//input[@name='form-control password']/../span/img"));
        passwordConfirmField = driver.findElement(
                By.cssSelector("input[name='form-control password-confirm']"));
        showPasswordConfirmButton = driver.findElement(By.xpath(
                "//input[@name='form-control password-confirm']/../span/img"));
        signUpButton = driver.findElement(By.cssSelector(
                "div[class='form-content-container'] button[class*='global-button']"));
        signInLink = driver.findElement(By.cssSelector("div.exist-account a"));
        googleSignUpButton = driver.findElement(By.cssSelector(
                "div[class='form-content-container'] button[class*='button-google']"));
        closeRegisterDropdownButton = driver.findElement(
                By.cssSelector("app-new-sign-up div.close-btn-img a"));
    }

    // Page Object

//  signInLink
    private LoginDropdown gotoLoginDropdown() {
        return new LoginDropdown(driver);
    }

    // closeRegisterDropdownButton
    private WebElement getCloseRegisterDropdownButton() {
        return closeRegisterDropdownButton;
    }

    private void clickCloseRegisterDropdownButton() {
        if (isDisplayedCloseRegisterDropdownButton()) {
            getCloseRegisterDropdownButton().click();
        }
    }

    private boolean isDisplayedCloseRegisterDropdownButton() {
        return getCloseRegisterDropdownButton().isDisplayed();
    }

//  emailValidator
    @Override
    protected WebElement getEmailValidator() {
        emailValidator = driver
                .findElement(By.cssSelector(EMAIL_VALIDATOR_SELECTOR));
        return emailValidator;
    }

    @Override
    protected boolean sizeEmailValidator() {
        return driver.findElements(By.cssSelector(EMAIL_VALIDATOR_SELECTOR))
                .size() != 0;
    }

//  passwordValidator
    @Override
    protected WebElement getPasswordValidator() {
        passwordValidator = driver
                .findElement(By.cssSelector(PASSWORD_VALIDATOR_SELECTOR));
        return passwordValidator;
    }

    @Override
    protected boolean sizePasswordValidator() {
        return driver.findElements(By.cssSelector(PASSWORD_VALIDATOR_SELECTOR))
                .size() != 0;
    }

//  passwordConfirmValidator
    @Override
    protected WebElement getPasswordConfirmValidator() {
        passwordConfirmValidator = driver.findElement(
                By.cssSelector(PASSWORD_CONFIRM_VALIDATOR_SELECTOR));
        return passwordConfirmValidator;
    }

    @Override
    protected boolean sizePasswordConfirmValidator() {
        return driver
                .findElements(
                        By.cssSelector(PASSWORD_CONFIRM_VALIDATOR_SELECTOR))
                .size() != 0;
    }

    // Functional
    @Override
    protected void switchToAnotherTab(String currentTab) {
        logger.debug("start switchToAnotherTab()");
        for (String current : driver.getWindowHandles()) {
            logger.info("we're in a TAB: " + current);
            if (!current.equals(currentTab)) {
                logger.info("and switch to TAB: " + current);
                driver.switchTo().window(current);
                break;
            }
        }
    }

    @Override
    protected String getTempEmail() {
        driver.get(GetMail10MinTools.URL);
        GetMail10MinTools tmp = new GetMail10MinTools(driver);
        return tmp.getTempEmail();
    }

    // a Google window opens and switches to it
    @Override
    public GoogleAccountPage clickSignUpGoogleAccountButton() {
        logger.info("we're in a TAB: " + driver.getWindowHandle());
        String currentTab = driver.getWindowHandle();
        logger.info("click to GoogleLogin Button");
        clickGoogleLoginButton();
        //
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));
        driver.manage().timeouts().implicitlyWait(5,
                TimeUnit.SECONDS);
        //
        switchToAnotherTab(currentTab);
        return new GoogleAccountPage(driver);
    }

    @Override
    protected String getTemporaryEmail() {
        String currentTab = driver.getWindowHandle();
        ((JavascriptExecutor) driver).executeScript("window.open()");
        switchToAnotherTab(currentTab);
        String email = getTempEmail();
        logger.info("temporary Email address for registration: " + email);
//        System.out
//                .println("temporary Email address for registration: " + email);
        driver.switchTo().window(currentTab);
        return email;
    }

    @Override
    protected RegisterPart verifyTempEmail() {
        String currentTab = driver.getWindowHandle();
        switchToAnotherTab(currentTab);
        GetMail10MinTools tmp = new GetMail10MinTools(driver);
        tmp.verifyEmail();
        driver.switchTo().window(currentTab);
        return this;
    }

    /**
     * Inserting some text on the 'Email' field.
     * @param email String
     * @return RegisterDropdown
     */
    private RegisterDropdown enterEmail(String email) {
        this.clickEmailField(driver).clearEmailField().setEmailField(emailField)
                .inputEmailField(email);
        return this;
    }

    /**
     * Inserting some text on the 'FirstName' field.
     * @param firstName String
     * @return RegisterDropdown
     */
    private RegisterDropdown enterFirstName(String firstName) {
        this.clickFirstName(driver).clearFirstName()
                .setUserNameField(userNameField).inputFirstName(firstName);
        return this;
    }

    /**
     * Inserting some text on the 'Password' field.
     * @param password String
     * @return RegisterDropdown
     */
    private RegisterDropdown enterPassword(String password) {
        this.clickPasswordField(driver).clearPasswordField()
                .setPasswordField(passwordField).inputPassword(password)
                .clickShowPasswordButton();
        return this;
    }

    /**
     * Inserting some text on the 'PasswordConfirm' field.
     * @param passwordConfirm String
     * @return RegisterDropdown
     */
    private RegisterDropdown enterPasswordConfirm(String passwordConfirm) {
        this.clickPasswordConfirmField(driver).clearPasswordConfirmField()
                .setPasswordConfirmField(passwordConfirmField)
                .inputPasswordConfirm(passwordConfirm)
                .clickShowPasswordConfirmButton();
        return this;
    }

    /**
     * Click on the 'SignUp' button.
     * @return TopPart
     */
    private TopPart clickSignupButton() {
        clickSignUpButton();
        return new TopPart(driver) {
        };
    }

//  emailError
    /**
     * Returns a text which displayed on the 'emailValidator' message.
     * @return String
     */
    public String getEmailErrorText() {
        if (sizeEmailValidator() && isDisplayedEmailValidator()) {
            logger.info("error message below Email field: " + getEmailValidatorText());
            return getEmailValidatorText();
        }
        return "email error text not found";
    }

//  passwordError
    /**
     * Returns a text which displayed on the 'PasswordValidator' message.
     * @return String
     */
    public String getPasswordErrorText() {
        if (sizePasswordValidator() && isDisplayedPasswordValidator()) {
            logger.info("error message below Password field: "
                    + getPasswordValidatorText());
            return getPasswordValidatorText();
        }
        return "password error text not found";
    }

    // passwordConfirmError
    /**
     * Returns a text which displayed on the 'PasswordConfirmValidator' message.
     * @return String
     */
    public String getPasswordConfirmErrorText() {
        if (sizePasswordConfirmValidator()
                && isDisplayedPasswordConfirmValidator()) {
            logger.info("error message below PasswordConfirm field: "
                    + getPasswordConfirmValidatorText());
            return getPasswordConfirmValidatorText();
        }
        return "password confirm error text not found";
    }

    // ---!!!! (generic)
    // close register dropdown
    /**
     * Close RegisterDropdown.
     */
    public TopPart closeRegisterDropdown() {
        clickCloseRegisterDropdownButton();
        return new TopPart(driver) {
        };
    }

    /**
     * Close window with ConfirmRegisteration text.
     * Working only when the browser starts on the main display.
     */
    public void closeConfirmRegisterationText() {
        logger.info("close Confirm Registeration window using Robot");
        Point coordinates = driver
                .findElement(By.cssSelector(SUBMIT_EMAIL_SELECTOR))
                .getLocation();
        Robot robot;
        try {
            robot = new Robot();
            robot.mouseMove(coordinates.getX(), coordinates.getY() - 150);
            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
            logger.info("Robot clicks to coordinates: " + coordinates.getX()
            + " : " + coordinates.getY());
        } catch (AWTException e) {
            e.printStackTrace();
        }

    }

// Business Logic

    /**
     * Get text which shows after a successful registration.
     * @return String
     */
    public String getConfirmRegisterationText() {
        logger.debug("start getConfirmRegisterationText()");
        logger.trace("find WebElement submitEmailText");
        submitEmailText = driver
                .findElement(By.cssSelector(SUBMIT_EMAIL_SELECTOR));
        logger.info("get Confirm Registeration text: " + setSubmitEmailText(submitEmailText).getSubmitEmailText());
        return setSubmitEmailText(submitEmailText).getSubmitEmailText();
    }

    /**
     * click on SignIn link and go to the login Dropdown
     * @return LoginDropdown
     */
    public LoginDropdown gotoLoginDropdownUsingLink() {
        this.clickSignInLink();
        return gotoLoginDropdown();
    }

    /**
     * Filling all fields on Register page and click on SingUp button.
     * @param userData object with user's credentials
     */
    public void registrationNewRandomUser(User userData) {
        userData.setEmail(getTemporaryEmail());
        enterEmail(userData.getEmail()).enterFirstName(userData.getUserName())
                .enterPassword(userData.getPassword())
                .enterPasswordConfirm(userData.getConfirmPassword());
        //
        clickSignUpButton();
        verifyTempEmail();
    }

    // completion of user registration
    /**
     * Filling all fields on RegisterDropdown page and click on SingUp button.
     * @param userData object with user's credentials
     * @return TopPart page
     */
    public TopPart registrationNewUser(User userData) {
        enterEmail(userData.getEmail()).enterFirstName(userData.getUserName())
                .enterPassword(userData.getPassword())
                .enterPasswordConfirm(userData.getConfirmPassword());
        return clickSignupButton();
    }
    
    /**
     * Filling all fields on RegisterDropdown page and click on SingUp button.
     * @param userData object with user's credentials
     * @return TopPart page
     */
    public TopPart registrationNewWrongUser(User userData) {
        enterEmail(userData.getEmail()).enterFirstName(userData.getUserName())
                .enterPassword(userData.getPassword())
                .enterPasswordConfirm(userData.getConfirmPassword());
        return clickSignupButton();
    }

    /**
     * Filling all fields on RegisterDropdown page without registration (without
     * click on SingUp button) and close RegisterDropdown page.
     * @param userData object with user's credentials
     * @return TopPart page
     */
    public TopPart fillFieldsWithoutRegistration(User userData) {
        enterEmail(userData.getEmail()).enterFirstName(userData.getUserName())
                .enterPassword(userData.getPassword())
                .enterPasswordConfirm(userData.getPassword());
        //
        return closeRegisterDropdown();
    }

    /**
     * Filling all fields on RegisterDropdown page without registration, click
     * on SingIn link and go to Login page.
     * @param userData object with user's credentials
     * @return LoginPart page
     */
    public LoginPart fillFieldsAndGotoLoginPage(User userData) {
        enterEmail(userData.getEmail()).enterFirstName(userData.getUserName())
                .enterPassword(userData.getPassword())
                .enterPasswordConfirm(userData.getPassword());
        return gotoLoginDropdownUsingLink();
    }

    /**
     * Click SignUp with Google Account button and a Google window opens
     * and switches to it.
     */
    public void clickSignUpGoogle() {
        clickSignUpGoogleAccountButton();
    }

}
