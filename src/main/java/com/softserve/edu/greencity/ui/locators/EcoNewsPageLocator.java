package com.softserve.edu.greencity.ui.locators;

import org.openqa.selenium.By;

/**
 * Locators for the list of all eco news: https://ita-social-projects.github.io/GreenCityClient/#/news
 */
public enum EcoNewsPageLocator implements Locator {
    CREATE_NEWS_BUTTON(By.id("create-button")),
    FOUND_ITEMS(By.cssSelector("div.main-wrapper")),
    HEADER(By.cssSelector("H1")),
    TAGS_FILTER_BLOCK(By.cssSelector("app-tag-filter")),
    TAGS_FILTER_LABEL(By.cssSelector("app-tag-filter>div.wrapper>span")),
    TAGS(By.cssSelector("app-tag-filter>div.wrapper>ul>a")),
    ACTIVE_TAGS(By.cssSelector("li.global-tag-clicked")),
    UNCHECK_TAG_BUTTONS(By.cssSelector(".global-tag-close-icon")),
    ARTICLES_FOUND_COUNTER(By.cssSelector("app-remaining-count>h2")),
    DISPLAYED_ARTICLES(By.cssSelector("ul.list>li")),
    DISPLAYED_ARTICLES_TITLES(By.xpath("//div[@class = 'title-list word-wrap']")),
    ARTICLE_IMAGE(By.cssSelector (".list-gallery img.list-image-content")),
            //("div.eco-news_list-view-wrp>img")),

    ARTICLE_ECO_BUTTON(By.cssSelector("div.filter-tag>div.ul-eco-buttons")),
    ARTICLE_TITLE(By.cssSelector("div.added-data>div.title-list>h3")),
            //("div.eco-news_list-content-title.one-row>p")),

    ARTICLE_TEXT(By.xpath("//div[@class='list-text word-wrap']/p")),
            //(By.cssSelector("div.added-data>div.list-text>p")),
            //("div.filter-tag+div")),

    ARTICLE_CREATION_DATE(By.cssSelector("div.user-data-added-news>p:first-child")),
            //("div.eco-news_list-autors>p.eco-news_data-text-date")),

    ARTICLE_AUTHOR_NAME(By.xpath("//div[@class='user-data-added-news']//p//img[@alt='user icon']")),
            //("div.user-data-added-news>p:last-child")),

    GALLERY_VIEW_BUTTON(By.cssSelector(".btn-tiles")),
    GALLERY_VIEW_BUTTON_HOVER(By.cssSelector(".btn-tiles>em")),
    GALLERY_VIEW_WRAPPER(By.cssSelector(".btn-tiles-active")),
    LIST_VIEW_BUTTON(By.cssSelector(".btn-bars")),
    LIST_VIEW_BUTTON_HOVER(By.cssSelector(".btn-bars>em")),
    LIST_VIEW_WRAPPER(By.cssSelector(".btn-bars-active")),
    OPEN_TOPICS_TAGS(By.cssSelector("div.tags>div.tags-item")),
    NEWS_TITLE(By.cssSelector("div.news-title")),
    NEWS_INFO_DATE(By.cssSelector("div.news-info>div.news-info-date")),
    NEWS_INFO_DOT(By.cssSelector("div.news-info>div.news-info-dot")),
    NEWS_INFO_AUTHOR(By.cssSelector("div.news-info>div.news-info-author")),
    NEWS_INFO_IMAGE(By.cssSelector("div>img.news-image-img")),
    NEWS_INFO_SOCIAL_LINKS_IMG(By.cssSelector("div.news-links-images")),
    NEWS_INFO_TEXT(By.cssSelector("div.news-text-content")),
    NEWS_INFO_SOURCE(By.cssSelector("div.source-field")),
    LANGUAGE_BUTTONS(By.cssSelector("ul.header_lang-switcher-wrp.header_navigation-menu-right-list li")),
    LANGUAGE_SWITCHER(By.cssSelector("ul.header_lang-switcher-wrp.header_navigation-menu-right-list"));

    EcoNewsPageLocator(By path) {
        this.path = path;
    }

    private By path;

    @Override
    public By getPath() {
        return path;
    }
}
