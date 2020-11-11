package com.softserve.edu.greencity.ui.locators;

import org.openqa.selenium.By;

public enum EcoNewsCommentLocators implements Locator {
    COMMENT_AREA(By.cssSelector("textarea")),
    COMMENT_BUTTON(By.cssSelector("button.primary-global-button")),
    DELETE_BUTTON(By.cssSelector("button.cta-btn.delete")),
    EDIT_BUTTON(By.cssSelector("button.cta-btn.edit")),
    COMMENT_TEXT(By.cssSelector("p.comment-text")),
    COMMENTS_LIST(By.cssSelector("app-com.softserve.edu.greencity.api.comments-list > div")),
    COMMENT_REPLY(By.cssSelector("app-add-comment")),
    REPLY_BUTTON(By.cssSelector("button.cta-btn.reply"));

    private final By path;

    EcoNewsCommentLocators(By path) {
        this.path = path;
    }

    @Override
    public By getPath() {
        return path;
    }

}
