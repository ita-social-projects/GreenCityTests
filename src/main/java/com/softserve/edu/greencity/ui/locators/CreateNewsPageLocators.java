package com.softserve.edu.greencity.ui.locators;

import org.openqa.selenium.By;

public enum CreateNewsPageLocators implements Locator {
    CREATE_NEWS_MAIN_TITLE(By.cssSelector(".title h2")),
    TITLE_LABELS(By.xpath("//h3[contains(text(),'Title')]")),
    TAGS_LABELS(By.xpath("//h3[contains(text(),'Pick tags for news')]")),
    SOURCE_LABELS(By.xpath("//h3[contains(text(),'Source')]")),
    PICTURE_LABELS(By.xpath("//h3[contains(text(),'Picture')]")),
    CONTENT_LABELS(By.xpath("//h3[contains(text(),'Content')]")),
    TITLE_FIELD(By.cssSelector("textarea[formcontrolname='title']")),
    SOURCE_FIELD(By.cssSelector("input[formcontrolname='source']")),
    CONTENT_FIELD(By.cssSelector("div.textarea-wrapper > textarea")),
    DATE_FIELD(By.cssSelector("div.date > p:first-child > span:last-child")),
    AUTHOR_FIELD(By.cssSelector("div.date > p:nth-child(2n) > span:last-child")),
    CANCEL_BUTTON(By.cssSelector("div.submit-buttons > :first-child")),
    PREVIEW_BUTTON(By.cssSelector("div.submit-buttons > :first-child+button")),
    PUBLISH_BUTTON(By.cssSelector("div.submit-buttons > button[type='submit']")),
    DROP_AREA(By.cssSelector("div.text-wrapper, div.ng-star-inserted > img")),
    INPUT_ELEMENT(By.cssSelector("input[id='upload']")),
    TAGS_BUTTON(By.cssSelector(".tags button:not(.filters-color)")),
    TITLE_DESCRIPTION(By.cssSelector("textarea[formcontrolname='title'] + span")),
    TAGS_DESCRIPTION(By.cssSelector("div.tags > button + p")),
    SOURCE_DESCRIPTION(By.cssSelector("input[formcontrolname='source'] + span")),
    CONTENT_DESCRIPTION(By.cssSelector("p.textarea-description")),
    PICTURE_DESCRIPTION(By.xpath("//div[@class = 'text-wrapper']/../../div/../span | //div[@class = 'ng-star-inserted']/../span")),
    CONTENT_ERROR(By.xpath("//*[@class = 'textarea-description']")),
    INVALID_SOURCE_ERROR(By.xpath("//*[@class = 'warning']")),
    INVALID_IMAGE_ERROR(By.cssSelector(".warning.ng-star-inserted")),
    TAGS_ERROR(By.xpath("//p[@class = 'warning']")),
    CURRENT_LANGUAGE_BUTTON(By.cssSelector(".header_lang-switcher-wrp li:only-child")),
    LANGUAGE_OPTIONS_BUTTON(By.cssSelector(".header_lang-switcher-wrp li")),
    NAME_TITLE_LABEL(By.cssSelector(".left-form-column .item-block label> h3")),
    TAGS_TITLE_LABEL(By.cssSelector(".left-form-column .item-block  >h3")),
    PICTURE_TITLE_LABEL(By.cssSelector(".form-container .right-form-column h3")),
    DATE_TITLE_LABEL(By.cssSelector(".date :first-child span:first-child")),
    AUTHOR_TITLE_LABEL(By.cssSelector(".date :last-child span:first-child")),
    CONTENT_TITLE_LABEL(By.cssSelector(".textarea-wrapper h3")),
    SOURCE_TITLE_LABEL(By.cssSelector(".left-form-column > div:last-child h3")),
    UPLOAD_IMAGE_INPUT(By.cssSelector("#upload")),
    SUBMIT_PHOTO_BUTTON(By.cssSelector(".cropper-buttons button:first-child")),
    CONFIRMATION_POPUP_HEADER(By.cssSelector("app-post-news-loader > div > p.header")),
    CONFIRMATION_POPUP_DESCRIPTION(By.cssSelector("app-post-news-loader > div > p.description"));

    private final By path;

    CreateNewsPageLocators(By path) {
        this.path = path;
    }

    @Override
    public By getPath() {
        return path;
    }
}
