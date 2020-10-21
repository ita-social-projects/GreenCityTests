package com.softserve.edu.greencity.ui.locators;

import org.openqa.selenium.By;

/**
 * Locators for the list of all eco news: https://ita-social-projects.github.io/GreenCityClient/#/news
 */
public enum EcoNewsPageLocator implements Locator {
    CREATE_NEWS_BUTTON(By.id("create-button")),
    FOUND_ITEMS(By.xpath("//*[@class='ng-star-inserted']")),
    HEADER(By.cssSelector("H1")),
    TAGS_FILTER_BLOCK(By.cssSelector("app-filter-news")),
    TAGS_FILTER_LABEL(By.cssSelector("app-filter-news>div.wrapper>span")),
    TAGS(By.cssSelector("app-filter-news>div.wrapper>ul>a")),
    ACTIVE_TAGS(By.cssSelector("app-filter-news>div.wrapper>ul>a>li.clicked-filter-button")),
    UNCHECK_TAG_BUTTONS(By.cssSelector("app-filter-news>div.wrapper>ul>a>li>div.close")),
    ARTICLES_FOUND_COUNTER(By.cssSelector("app-remaining-count>p")),
    DISPLAYED_ARTICLES(By.cssSelector(".gallery-view-li-active.ng-star-inserted")),
    DISPLAYED_ARTICLES_TITLES(By.xpath("//div[@class = 'title-list word-wrap']")),
    ARTICLE_IMAGE(By.cssSelector(" div.list-image>img")),
    ARTICLE_ECO_BUTTON(By.cssSelector("div.filter-tag>div.ul-eco-buttons")),
    ARTICLE_TITLE(By.cssSelector("div.added-data>div.title-list>p")),
    ARTICLE_TEXT(By.cssSelector(" div.added-data>div.list-text>p")),
    ARTICLE_CREATION_DATE(By.cssSelector("div.user-data-added-news>p:first-child")),
    ARTICLE_AUTHOR_NAME(By.cssSelector("div.user-data-added-news>p:last-child")),
    GALLERY_VIEW_BUTTON(By.cssSelector("div.gallery-view")),
    LIST_VIEW_BUTTON(By.cssSelector("div.list-view")),
    OPEN_TOPICS_TAGS(By.cssSelector("div.tags>div.tags-item")),
    NEWS_TITLE(By.cssSelector("div.news-title")),
    NEWS_INFO_DATE(By.cssSelector("div.news-info>div.news-info-date")),
    NEWS_INFO_DOT(By.cssSelector("div.news-info>div.news-info-dot")),
    NEWS_INFO_AUTHOR(By.cssSelector("div.news-info>div.news-info-author")),
    NEWS_INFO_IMAGE(By.cssSelector("div>img.news-image-img")),
    NEWS_INFO_SOCIAL_LINKS_IMG(By.cssSelector("div.news-links-images")),
    NEWS_INFO_TEXT(By.cssSelector("div.news-text-content")),
    NEWS_INFO_SOURCE(By.cssSelector("div.source-field"));

    EcoNewsPageLocator(By path) {
        this.path = path;
    }

    private By path;

    @Override
    public By getPath() {
        return path;
    }
}
