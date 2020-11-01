package com.softserve.edu.greencity.ui.locators;

import org.openqa.selenium.By;

public enum CommentComponentLocators implements Locator {
    NUMBERS_OF_COMMENTS(By.cssSelector(".counter.ng-star-inserted > p")),
    COMMENT_FIELD(By.cssSelector(".input-submit.ng-star-inserted > textarea")),
    COMMENT_BUTTON(By.cssSelector("button.primary-global-button")),
    LIKE_BUTTON(By.cssSelector("button.cta-btn.like")),
    REPLY_BUTTON(By.cssSelector("button.cta-btn.reply")),
    REPLY_FIELD(By.cssSelector("textarea[placeholder='Add a reply']")),
    ADD_REPLY_BUTTON(By.cssSelector("textarea[placeholder='Add a reply'] + button")),
    EDIT_COMMENT_BUTTON(By.cssSelector("button.cta-btn.edit")),
    DELETE_COMMENT_BUTTON(By.cssSelector("button.cta-btn.delete")),
    COMMENT_EDIT_FIELD(By.cssSelector(".comment-edit-text.ng-star-inserted")),
    SAVE_EDIT_COMMENT_BUTTON(By.cssSelector("button.cta-btn.save-edit")),
    CANCEL_EDIT_COMMENT_BUTTON(By.cssSelector("button.cta-btn.cancel-edit")),
    COMMENT_LIKES(By.cssSelector(".comment-likes > span")),
    COMMENT_TEXT(By.cssSelector(".comment-text.ng-star-inserted")),
    COMMENT_DATE(By.cssSelector(".comment-date-month")),
    AUTHOR_NAME(By.cssSelector(".author-name > span")),
    COMMENT_AUTHOR_AVATAR(By.cssSelector("img[alt='comment-author']")),
    AVATAR(By.cssSelector("img[alt='avatar']")),
    COMMENTS_COMPONENTS(By.cssSelector("app-comments-list > div")),
    REPLY_COMPONENTS(By.cssSelector(".comment-body-wrapper.wrapper-reply.ng-star-inserted")),
    SHOW_REPLIES(By.cssSelector("button.cta-btn.view.ng-star-inserted"));

    private final By path;

    CommentComponentLocators(By path) {
        this.path = path;
    }

    @Override
    public By getPath() {
        return path;
    }
}

