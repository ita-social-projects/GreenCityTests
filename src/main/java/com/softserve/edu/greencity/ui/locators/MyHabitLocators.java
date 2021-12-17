package com.softserve.edu.greencity.ui.locators;

import org.openqa.selenium.By;

public enum MyHabitLocators implements Locator {
    USER_IMAGE_DEFAULT(By.xpath("//app-personal-photo/div/app-user-profile-image/div")),
    USERNAME_LABEL(By.cssSelector(".name")),
    CITY_LABEL(By.cssSelector(".location")),
    CREDO_LABEL(By.cssSelector(".credo")),
    SOCIAL_MEDIA_ICON(By.cssSelector(".social.ng-star-inserted")),
    SHOPPING_LIST_LABEL(By.xpath("//div[@class = 'header-position shopping-list-content']")),
    LOGO(By.cssSelector(".header_logo")),
    ADD_NEW_HABIT_BUTTON(By.xpath("//*[@id = 'create-button']")),
    EDIT_PROFILE_BUTTON(By.cssSelector(".edit-icon")),
    SOCIAL_NETWORK_CONTAINER(By.cssSelector("div.social.ng-star-inserted > a"));

    private final By path;

    MyHabitLocators(By path) {
        this.path = path;
    }

    @Override
    public By getPath() {
        return path;
    }
}