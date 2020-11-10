package com.softserve.edu.greencity.ui.locators;

import org.openqa.selenium.By;

/**
 * Locators for the page you get to after clicking "Preview" on creating news
 */
public enum PreviewPageLocators implements Locator {
    TITLE_FIELD(By.cssSelector("div.news-title")),
    DATE_FIELD(By.cssSelector("div.news-info-date")),
    AUTHOR_FIELD(By.cssSelector("div.news-info-author")),
    CONTENT_FIELD(By.cssSelector("div.news-text-content")),
    IMG_TWITTER_LINK(By.xpath("//img[contains(@src,'twitter.svg')]")),
    IMG_LINKEDIN_LINK(By.xpath("//img[contains(@src,'linkedin.svg')]")),
    IMG_FACEBOOK_LINK(By.xpath("//img[contains(@src,'facebook.svg')]")),
    BACK_TO_EDITING_BUTTON(By.cssSelector("div.button-text")),
    PUBLISH_BUTTON(By.cssSelector("button[type='submit']")),
    TAGS_FIELDS(By.cssSelector("div.tags > div"));

    private final By path;

    PreviewPageLocators(By path) {
        this.path = path;
    }

    @Override
    public By getPath() {
        return path;
    }
}
