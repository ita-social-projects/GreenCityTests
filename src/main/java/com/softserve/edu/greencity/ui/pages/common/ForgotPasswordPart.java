package com.softserve.edu.greencity.ui.pages.common;

import org.openqa.selenium.WebElement;

public abstract class ForgotPasswordPart {
    WebElement emailField;
    WebElement submitButton;
    WebElement googleSigningButton;
    WebElement backLink;

    public ForgotPasswordPart inputEmail(String email) {
        this.getEmailField().sendKeys(email);
        return this;
    }

    public WebElement getEmailField() {
        return emailField;
    }

    public WebElement getSubmitButton() {
        return submitButton;
    }

    public WebElement getGoogleSigningButton() {
        return googleSigningButton;
    }

    public WebElement getBackLink() {
        return backLink;
    }

    public ForgotPasswordPart setEmailField(WebElement emailField) {
        this.emailField = emailField;
        return this;
    }

    public ForgotPasswordPart setSubmitButton(WebElement submitButton) {
        this.submitButton = submitButton;
        return this;
    }

    public ForgotPasswordPart setGoogleSigningButton(WebElement googleSigningButton) {
        this.googleSigningButton = googleSigningButton;
        return this;
    }

    public ForgotPasswordPart setBackLink(WebElement backLink) {
        this.backLink = backLink;
        return this;
    }
}