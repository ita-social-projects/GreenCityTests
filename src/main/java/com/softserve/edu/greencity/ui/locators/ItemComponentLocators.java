package com.softserve.edu.greencity.ui.locators;

import org.openqa.selenium.By;

public enum ItemComponentLocators implements Locator {
    TAGS(By.cssSelector(".filter-tag div")),
    TAGS_CONTAINER(By.cssSelector(".filter-tag")),
    IMAGE(By.cssSelector(".list-image-content")),
    TITLE(By.cssSelector(".title-list p")),
    CONTENT(By.cssSelector(".list-text p")),
    CONTENT_WRAP(By.cssSelector(".list-text")),
    DATE_OF_CREATION(By.cssSelector(".user-data-text-date")),
    AUTHOR(By.cssSelector(".user-data-added-news > p:nth-child(2)")),
    DATE_AND_AUTHOR_CONTAINER(By.cssSelector(".user-data-added-news"));
    
    private final By path;
    ItemComponentLocators(By path) {
        this.path = path;
    }

    @Override
    public By getPath() {
        return path;
    }

}
