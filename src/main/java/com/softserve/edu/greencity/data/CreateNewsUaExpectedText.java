package com.softserve.edu.greencity.data;

import org.openqa.selenium.By;

/**
 * It is important that all enum have appropriate locator in
 * com.softserve.edu.greencity.ui.locators.CreateNewsPageLocators
 * with name without _UA_LANG
 */

public enum CreateNewsUaExpectedText {
    CREATE_NEWS_MAIN_TITLE_UA_LANG("Створити новину"),
    CANCEL_BUTTON_UA_LANG("Вийти"),
    PREVIEW_BUTTON_UA_LANG("Переглянути "),
    PUBLISH_BUTTON_UA_LANG("Опублікувати"),
    TAGS_DESCRIPTION_UA_LANG("Оберіть не більше 3-х тегів"),
    NAME_TITLE_LABEL_UA_LANG("Назва"),
    TAGS_TITLE_LABEL_UA_LANG("Будь ласка, оберіть один чи більше тегів"),
    PICTURE_TITLE_LABEL_UA_LANG("Зображення (не обов'язково)"),

    SOURCE_TITLE_LABEL_UA_LANG("Джерело (не обов'язково)"),
    CONTENT_TITLE_LABEL_UA_LANG("Зміст"),
    DATE_TITLE_LABEL_UA_LANG("Дата:"),
    AUTHOR_TITLE_LABEL("Автор: "),
    ;

    private final String expectedResult;

    CreateNewsUaExpectedText(String expectedResult) {
        this.expectedResult = expectedResult;
    }

    public String getString() {
        return expectedResult;
    }
}
