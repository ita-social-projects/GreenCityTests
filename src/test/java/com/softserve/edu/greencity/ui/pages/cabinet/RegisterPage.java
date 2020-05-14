package com.softserve.edu.greencity.ui.pages.cabinet;

import org.openqa.selenium.WebDriver;

import com.softserve.edu.greencity.ui.data.User;
import com.softserve.edu.greencity.ui.pages.common.TopPart;

/**
 * RegisterPage class.
 * @author Serg
 */
public class RegisterPage extends TopPart {

    private RegisterComponent registerComponent;

    /**
     * RegisterPage constructor.
     * @param driver
     */
    public RegisterPage(WebDriver driver) {
        super(driver);
        initElements();
    }

    private void initElements() {
        // init elements
        registerComponent = new RegisterComponent(driver);
    }

    private RegisterComponent getRegisterComponent() {
        return registerComponent;
    }

    // Functional

    /**
     * Returns a text which displayed on the top of the page.
     * @return String
     */
    public String getTitleFieldText() {
        return this.getRegisterComponent().getTitlePageText();
    }

    /**
     * Inserting some text on the 'Email' field.
     * @param email String
     * @return RegisterPage
     */
    private RegisterPage enterEmail(String email) {
        this.getRegisterComponent().setEmailField(email);
        return this;
    }

    /**
     * Inserting some text on the 'Email' field.
     * @param firstName String
     * @return RegisterPage
     */
    private RegisterPage enterFirstName(String firstName) {
        this.getRegisterComponent().setFirstNameField(firstName);
        return this;
    }

    /**
     * Inserting some text on the 'Email' field.
     * @param lastName String
     * @return RegisterPage
     */
    private RegisterPage enterLastName(String lastName) {
        this.getRegisterComponent().setLastNameField(lastName);
        return this;
    }

    /**
     * Inserting some text on the 'Email' field.
     * @param password String
     * @return RegisterPage
     */
    private RegisterPage enterPassword(String password) {
        this.getRegisterComponent().setPasswordField(password);
        return this;
    }

    /**
     * Inserting some text on the 'Email' field.
     * @param passwordConfirm String
     * @return RegisterPage
     */
    private RegisterPage enterPasswordConfirm(String passwordConfirm) {
        this.getRegisterComponent().setPasswordConfirmField(passwordConfirm);
        return this;
    }

    /**
     * Inserting some text on the 'Email' field.
     * @return RegisterPage
     */
    public RegisterPage clickSignUpButton() {
        this.getRegisterComponent().clickSignUpButton();
        return this;
    }

    /**
     * Inserting some text on the 'Email' field.
     * @return LoginPart
     */
    public LoginPage clickSignInLink() {
        logger.debug("start clickSignInLink()");
        logger.trace("click on Sign-in link");
        logger.info("click on Sign-in link go to LoginPage");
        this.getRegisterComponent().clickSignInLink();
        return new LoginPage(driver);
    }

    /**
     * Click SignUp with Google Account button and a Google window opens and
     * switches to it.
     */
    public void clickSignUpGoogleButton() {
        this.getRegisterComponent().clickSignUpGoogleAccountButton();
    }

//RegistrationError
    /**
     * Returns a text which displayed on the 'RegistrationValidator' field.
     * @return String
     */
    public String getRegistrationErrorText() {
        if (getRegisterComponent().sizeRegistrationValidator()
                && getRegisterComponent().isDisplayedRegistrationValidator()) {
            return getRegisterComponent().getRegistrationValidatorText();
        }
        return "Registration error text not found";
    }

//FirstNameError
    /**
     * Returns a text which displayed on the 'RegistrationValidator' field.
     * @return String
     */
    public String getFirstNameErrorText() {
        if (getRegisterComponent().sizeFirstNameValidator()
                && getRegisterComponent().isDisplayedFirstNameValidator()) {
            return getRegisterComponent().getFirstNameValidatorText();
        }
        return "First Name error text not found";
    }

//emailError
    /**
     * Returns a text which displayed on the 'emailValidator' message.
     * @return String
     */
    public String getEmailErrorText() {
        if (getRegisterComponent().sizeEmailValidator()) {
            return getRegisterComponent().getEmailValidatorText();
        }
        return "email error text not found";
    }

//passwordError
    /**
     * Returns a text which displayed on the 'PasswordValidator' message.
     * @return String
     */
    public String getPasswordErrorText() {
        if (getRegisterComponent().sizePasswordValidator()
                && getRegisterComponent().isDisplayedPasswordValidator()) {
            return getRegisterComponent().getPasswordValidatorText();
        }
        return "password error text not found";
    }

//passwordConfirmError
    /**
     * Returns a text which displayed on the 'PasswordValidator' message.
     * @return String
     */
    public String getPasswordConfirmErrorText() {
        if (getRegisterComponent().sizePasswordConfirmValidator()
                && getRegisterComponent()
                        .isDisplayedPasswordConfirmValidator()) {
            return getRegisterComponent().getPasswordConfirmValidatorText();
        }
        return "password confirm error text not found";
    }

    /**
     * Returns a text which shows after a successful registration.
     * @return String
     */
    public String getConfirmRegistrationText() {
        return getRegisterComponent().getConfirmRegisterationText();
    }

    // Business Logic

    // completion of user registration
    /**
     * Filling all fields on Register page and click on SingUp button.
     * @param userData object with user's credentials
     * @return RegisterPage page
     */
    public void registrationNewRandomUser(User userData) {
        userData.setEmail(getRegisterComponent().getTemporaryEmail());
        enterEmail(userData.getEmail()).enterFirstName(userData.getFirstName())
                .enterLastName(userData.getLastName())
                .enterPassword(userData.getPassword())
                .enterPasswordConfirm(userData.getPassword());
        //
        clickSignUpButton();
        getRegisterComponent().verifyTempEmail();
        getRegisterComponent();
    }

    /**
     * Test registration user with existing credentials already.
     * @param userData User
     */
    public void registrationUser(User userData) {
        enterEmail(userData.getEmail()).enterFirstName(userData.getFirstName())
                .enterLastName(userData.getLastName())
                .enterPassword(userData.getPassword())
                .enterPasswordConfirm(userData.getPassword())
                .clickSignUpButton();
    }

    /**
     * Test registration user with already wrong credentials.
     * @param userData
     */
    public void registrationWrongUser(User userData) {
        enterEmail(userData.getEmail()).enterFirstName(userData.getFirstName())
                .enterLastName(userData.getLastName())
                .enterPassword(userData.getPassword())
                .enterPasswordConfirm(userData.getConfirmPassword())
                .clickSignUpButton();
    }

    /**
     * Filling all fields on Register page without registration (without click
     * on SingUp button).
     * @param userData object with user's credentials
     * @return RegisterPage page
     */
    public void fillFieldsWithoutRegistration(User userData) {
        enterEmail(userData.getEmail()).enterFirstName(userData.getFirstName())
                .enterLastName(userData.getLastName())
                .enterPassword(userData.getPassword())
                .enterPasswordConfirm(userData.getPassword());
    }

    /**
     * Filling all fields on Register page without registration, click on SingIn
     * link and go to Login page.
     * @param userData object with user's credentials
     * @return LoginPart page
     */
    public LoginPage fillFieldsAndGotoLoginPage(User userData) {
        enterEmail(userData.getEmail()).enterFirstName(userData.getFirstName())
                .enterLastName(userData.getLastName())
                .enterPassword(userData.getPassword())
                .enterPasswordConfirm(userData.getConfirmPassword());
        return clickSignInLink();
    }
}
