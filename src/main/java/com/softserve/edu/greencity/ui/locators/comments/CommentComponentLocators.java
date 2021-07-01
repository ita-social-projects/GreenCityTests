package com.softserve.edu.greencity.ui.locators.comments;

import com.softserve.edu.greencity.ui.locators.Locator;
import org.openqa.selenium.By;

public enum CommentComponentLocators implements Locator {
    NUMBERS_OF_COMMENTS(By.xpath("//div[@class='counter ng-star-inserted']/div[@class='wrapper']/p/following-sibling::p")),
    ADD_COMMENT_TEXTAREA(By.cssSelector(".input-submit textarea")),
    ADD_COMMENT_BUTTON(By.cssSelector(".input-submit button")),
    COMMENTS_BLOCK(By.cssSelector("app-comments-list")),
    COMMENTS_LIST(By.cssSelector("app-comments-list > div"));

    private final By path;

    CommentComponentLocators(By path) {
        this.path = path;
    }

    @Override
    public By getPath() {
        return path;
    }
}

