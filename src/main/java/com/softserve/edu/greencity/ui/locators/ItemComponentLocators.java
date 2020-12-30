package com.softserve.edu.greencity.ui.locators;

import org.openqa.selenium.By;

public enum ItemComponentLocators implements Locator {
    TAGS(By.cssSelector(".filter-tag span")),
    TAGS_CONTAINER(By.cssSelector(".filter-tag")),
    IMAGE(By.cssSelector(".eco-news_list-content")),
    TITLE(By.cssSelector(".eco-news_list-content-title p")),
    CONTENT(By.cssSelector(".eco-news_list-content-text p")),
    CONTENT_WRAP(By.cssSelector(".eco-news_list-content-text")),
    DATE_OF_CREATION(By.cssSelector(".eco-news_data-text-date")),
    AUTHOR(By.cssSelector(".eco-news_list-autors > p:nth-child(2)")),
    DATE_AND_AUTHOR_CONTAINER(By.cssSelector(".eco-news_list-autors"));
    
    private final By path;
    ItemComponentLocators(By path) {
        this.path = path;
    }

    @Override
    public By getPath() {
        return path;
    }

}
