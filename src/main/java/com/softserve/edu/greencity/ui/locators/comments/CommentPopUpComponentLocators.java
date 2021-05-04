package com.softserve.edu.greencity.ui.locators.comments;

import com.softserve.edu.greencity.ui.locators.Locator;
import org.openqa.selenium.By;

public enum CommentPopUpComponentLocators implements Locator {
    COMMENT_POP_UP_COMPONENT_LOCATORS(By.cssSelector(".main-container")),
    WARNING_TITLE(By.cssSelector(".warning-text>div")),
    CANCEL_BUTTON(By.cssSelector(".secondary-global-button")),
    CONFIRM_BUTTON(By.cssSelector(".buttons-container .primary-global-button"))
    ;

    private final By path;

    CommentPopUpComponentLocators(By path) {
        this.path = path;
    }

    @Override
    public By getPath() {
        return path;
    }
}