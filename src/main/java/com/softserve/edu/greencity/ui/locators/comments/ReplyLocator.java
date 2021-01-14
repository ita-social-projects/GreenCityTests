package com.softserve.edu.greencity.ui.locators.comments;

import com.softserve.edu.greencity.ui.locators.Locator;
import org.openqa.selenium.By;

/**
 * Locators for SINGLE REPLY
 */
public enum ReplyLocator implements Locator {
    REPLY_AUTHOR(By.cssSelector(".author-name")),
    REPLY_DATE(By.cssSelector(".comment-date-month")),
    REPLY_CURRENT_TEXT(By.cssSelector(".comment-text")),

    REPLY_LIKE_BUTTON(By.cssSelector(".cta-btn.like")),
    REPLY_LIKE_AMOUNT(By.cssSelector(".like-amount")),

    REPLY_EDIT_BUTTON(By.cssSelector(".cta-btn.edit")),
    REPLY_EDIT_TEXTAREA(By.cssSelector(".edit-text-input")),
    REPLY_SAVE_CHANGES_BUTTON(By.cssSelector(".cta-btn.save-edit")),
    REPLY_CANCEL_CHANGES_BUTTON(By.cssSelector(".cta-btn.cancel-edit")),
    REPLY_REPLY_BUTTON(By.cssSelector(".cta-btn.reply")),
    REPLY_DELETE_BUTTON(By.cssSelector(".cta-btn.delete"));

    private final By path;

    ReplyLocator(By path) {
        this.path = path;
    }

    @Override
    public By getPath() {
        return path;
    }
}
