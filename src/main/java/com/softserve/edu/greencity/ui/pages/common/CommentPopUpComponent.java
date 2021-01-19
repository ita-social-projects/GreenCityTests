package com.softserve.edu.greencity.ui.pages.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CommentPopUpComponent extends TopPart {
    protected By commentPopUpComponent = By.cssSelector(".main-container");
    private final By warningTitle = By.cssSelector(".warning-text>div");
    private final By cancelButton = By.cssSelector(".secondary-global-button");
    private final By confirmButton = By.cssSelector(".buttons-container .primary-global-button");

    public CommentPopUpComponent(WebDriver driver) {
        super(driver);
        checkElements();
    }

    private void checkElements() {
        waitsSwitcher.setExplicitWait(10, ExpectedConditions.visibilityOf(getWarningTitle()));
    }

    public WebElement getWarningTitle() {
        return searchElementByCss(warningTitle);
    }

    public WebElement getCancelButton() {
        return searchElementByCss(cancelButton);
    }

    public WebElement getConfirmButton() {
        return searchElementByCss(confirmButton);
    }

    public void clickCancelButton() {
        getCancelButton().click();
    }

    public void clickConfirmButton() {
        getConfirmButton().click();
    }
}

