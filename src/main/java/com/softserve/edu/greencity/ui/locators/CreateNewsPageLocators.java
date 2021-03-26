package com.softserve.edu.greencity.ui.locators;

import org.openqa.selenium.By;

public enum CreateNewsPageLocators implements Locator {
    CREATE_NEWS_MAIN_TITLE(By.cssSelector(".title h2")),
    TITLE_FIELD(By.cssSelector("textarea[formcontrolname='title']")),
    SOURCE_FIELD(By.cssSelector("input[formcontrolname='source']")),
    CONTENT_FIELD(By.cssSelector("div.textarea-wrapper > textarea")),
    DATE_FIELD(By.cssSelector("div.date > p:first-child > span:last-child")),
    AUTHOR_FIELD(By.cssSelector("div.date > :nth-child(2n) > span")),
    CANCEL_BUTTON(By.cssSelector("div.submit-buttons > :first-child")),
    PREVIEW_BUTTON(By.cssSelector("div.submit-buttons > :first-child+button")),
    PUBLISH_BUTTON(By.cssSelector("div.submit-buttons > button[type='submit']")),
    DROP_AREA(By.cssSelector("div.text-wrapper, div.ng-star-inserted > img")),
    INPUT_ELEMENT(By.cssSelector("input[id='upload']")),
    TITLE_DESCRIPTION(By.cssSelector("input[formcontrolname='title'] + span")),
    TAGS_DESCRIPTION(By.cssSelector("div.tags > button + p")),
    SOURCE_DESCRIPTION(By.cssSelector("input[formcontrolname='source'] + span")),
    CONTENT_DESCRIPTION(By.cssSelector("p.textarea-description")),
    PICTURE_DESCRIPTION(By.xpath("//div[@class = 'text-wrapper']/../../div/../span | //div[@class = 'ng-star-inserted']/../span")),
    CONTENT_ERROR(By.xpath("//*[@class = 'textarea-description']")),
    INVALID_SOURCE_ERROR(By.xpath("//*[@class = 'warning']")),
    INVALID_IMAGE_ERROR(By.cssSelector(".warning.ng-star-inserted")),
    TAGS_ERROR(By.xpath("//p[@class = 'warning']"));

    private final By path;

    CreateNewsPageLocators(By path) {
        this.path = path;
    }

    @Override
    public By getPath() {
        return path;
    }
}
