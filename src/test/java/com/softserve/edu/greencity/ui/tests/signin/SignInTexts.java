package com.softserve.edu.greencity.ui.tests.signin;

/**
 * Text strings used in sign in and forgot password windows
 */
public enum SignInTexts {
    SIGN_IN_TITLE("Welcome back!"),
    SIGN_IN_SUB_TITLE("Please enter your details to sign in"),
    FORGOT_PASS_TITLE("Problems sign in?"),
    FORGOT_PASS_SUB_TITLE("Enter your email address and we'll send you a link to regain access to your account."),
    EMAIL_PLACEHOLDER_TEXT("example@email.com"),
    FORGOT_PASS_EMAIL_VALIDATION_ERROR("Please check that your e-mail address is indicated correctly"),
    EMPTY_EMAIL_ERROR_MESSAGE("Email is required"),
    WRONG_EMAIL_OR_PASS_ERROR("Bad email or password"),
    NOT_EXISTING_EMAIL_MESSAGE("The user does not exist by this email:"),
    BACK_TO_SIGN_IN_LABEL("Remember your password? Back to Sign-in"),
    RESTORE_EMAIL_ERROR_MESSAGE("Password restore link already sent), please check your email:"),
    FORGOT_PASS_MAIL_SUBJECT("Confirm restoring password"),
    SIGN_IN_EMAIL_VALIDATION_ERROR("Please check that your e-mail address is indicated correctly"),
    SIGN_IN_PASSWORD_VALIDATION_ERROR("Password must be at least 8 characters long"),

    SIGN_UP_TITLE("Hello!"),
    ADD_NEW_HABIT_BUTTON_TEXT("Add new habit"),
    TOP_USER_NAME("Prosto Leleka");

    private final String text;

    SignInTexts(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
