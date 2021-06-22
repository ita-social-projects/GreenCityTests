package com.softserve.edu.greencity.ui.pages.common;

import com.softserve.edu.greencity.ui.locators.comments.CommentPopUpComponentLocators;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import static com.softserve.edu.greencity.ui.locators.comments.CommentPopUpComponentLocators.*;

public class CommentPopUpComponent extends TopPart {

    public CommentPopUpComponent(WebDriver driver) {
        super(driver);
        checkElements();
    }

    private void checkElements() {
        waitsSwitcher.setExplicitWait(10, ExpectedConditions.visibilityOf(getWarningTitle()));
    }

    public WebElement getWarningTitle() {
        return searchElementByCss(WARNING_TITLE.getPath());
    }

    public WebElement getCancelButton() {
        return searchElementByCss(CANCEL_BUTTON.getPath());
    }

    public WebElement getConfirmButton() {
        return searchElementByCss(CONFIRM_BUTTON.getPath());
    }

    public void clickCancelButton() {
        getCancelButton().click();
    }

    public void clickConfirmButton() {
        getConfirmButton().click();
    }
}

