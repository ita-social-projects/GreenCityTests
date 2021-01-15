package com.softserve.edu.greencity.ui.locators.comments;

import com.softserve.edu.greencity.ui.locators.Locator;
import org.openqa.selenium.By;

/**
 * Locators for SINGLE COMMENT
 */
public enum CommentLocator implements Locator {
    COMMENT_AUTHOR(By.cssSelector(".author-name")),
    COMMENT_DATE(By.cssSelector(".comment-date-month")),
    COMMENT_CURRENT_TEXT(By.cssSelector(".comment-text")),

    COMMENT_EDIT_BUTTON(By.cssSelector(".cta-btn.edit")),
    COMMENT_EDIT_TEXT_AREA(By.cssSelector(".edit-text-input")),
    COMMENT_SAVE_CHANGES_BUTTON(By.cssSelector(".cta-btn.save-edit")),
    COMMENT_CANCEL_CHANGES_BUTTON(By.cssSelector(".cta-btn.cancel-edit")),

    COMMENT_DELETE_BUTTON(By.cssSelector(".cta-btn.delete")),

    COMMENT_REPLY_BUTTON(By.cssSelector(".cta-btn.reply")),
    ADD_REPLY_TEXTAREA(By.cssSelector(".input-submit textarea")),
    ADD_REPLY_BUTTON(By.cssSelector(".input-submit button")),

    COMMENT_LIKE_BUTTON(By.cssSelector(".cta-btn.like")),
    COMMENT_LIKE_AMOUNT(By.cssSelector(".like-amount")),

    COMMENT_SHOW_REPLIES_BUTTON(By.cssSelector("button.cta-btn.view")),
    COMMENT_REPLIES(By.cssSelector(".comment-body-wrapper.wrapper-reply"));

    private final By path;

    CommentLocator(By path) {
        this.path = path;
    }

    @Override
    public By getPath() {
        return path;
    }
}
