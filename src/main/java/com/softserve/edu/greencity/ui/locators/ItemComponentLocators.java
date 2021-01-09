package com.softserve.edu.greencity.ui.locators;

import org.openqa.selenium.By;

public enum ItemComponentLocators implements Locator {
    TAGS(By.cssSelector(".filter-tag div")),
    TAGS_CONTAINER(By.cssSelector(".filter-tag")),
    IMAGE(By.cssSelector(".list-image-content")),
    TITLE(By.cssSelector(".title-list p")),
    CONTENT(By.cssSelector(".list-text p")),
    CONTENT_WRAP(By.cssSelector(".list-text")),
    DATE_OF_CREATION(By.cssSelector(".user-data-added-news > p:nth-of-type(1)")),
    AUTHOR(By.cssSelector(".user-data-added-news > p:nth-of-type(2)")),
    DATE_AND_AUTHOR_CONTAINER(By.cssSelector(".user-data-added-news")),

    TAGS_LISTVIEW(By.cssSelector(".filter-tag span")),
    IMAGE_LISTVIEW(By.cssSelector(".eco-news_list-content")),
    TITLE_LISTVIEW(By.cssSelector(".eco-news_list-content-title p")),
    CONTENT_LISTVIEW(By.cssSelector(".eco-news_list-content-text p")),
    CONTENT_WRAP_LISTVIEW(By.cssSelector(".eco-news_list-content-text")),
    DATE_OF_CREATION_LISTVIEW(By.cssSelector(".eco-news_data-text-date")),
    AUTHOR_LISTVIEW(By.cssSelector(".eco-news_list-autors > p:nth-child(2)")),
    DATE_AND_AUTHOR_CONTAINER_LISTVIEW(By.cssSelector(".eco-news_list-autors"));

    private final By path;

    ItemComponentLocators(By path) {
        this.path = path;
    }

    @Override
    public By getPath() {
        return path;
    }

}
