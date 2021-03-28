package com.softserve.edu.greencity.ui.locators;

import org.openqa.selenium.By;

public enum SingleNewsPageLocators implements Locator {
    GO_TO_NEWS(By.cssSelector("div.back-button")),
    EDIT_NEWS_BUTTON(By.cssSelector("div.edit-news")),
    TITLE(By.cssSelector("div.news-title")),
    DATA(By.cssSelector("div.news-info > div.news-info-date")),
    AUTHOR(By.cssSelector("div.news-info > div.news-info-author")),
    PICTURE(By.cssSelector("div.news-content > img.news-image-img")),
    CONTENT(By.cssSelector("div.news-text")),
    TAGS_LIST(By.cssSelector("div.tags > div")),
    SOURCE_TITLE(By.cssSelector("div.source-title")),
    SOURCE_LINK(By.cssSelector("a.source-text"));
    
    private final By path;

    SingleNewsPageLocators(By path) {
        this.path = path;
    }

    @Override
    public By getPath() {
        return path;
    }
}
