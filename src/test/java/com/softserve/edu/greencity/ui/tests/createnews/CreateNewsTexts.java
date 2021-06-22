package com.softserve.edu.greencity.ui.tests.createnews;

public enum CreateNewsTexts {
    CREATE_NEWS_TITLE("Create news"),
    CONTENT_ERROR("Must be minimum 20 symbols"),
    INVALID_SOURCE_ERROR("Please add the link of original article/news/post. Link must start with http(s)://"),
    IMAGE_ERROR("Download PNG or JPG only. File size should be less than 10MB"),
    VALID_TITLE("Green Day Test"),
    VALID_CONTENT("Content = description"),
    TAGS_ERROR("Only 3 tags can be added"),
    CONFIRMATION_HEADER_MESSAGE("Please wait while loading..."),
    CONFIRMATION_DESCRIPTON_MESSAGE("Your news is loading to website. Please wait until the page refreshes.");

    private final String text;

    CreateNewsTexts(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
