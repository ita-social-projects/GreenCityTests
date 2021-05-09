package com.softserve.edu.greencity.ui.locators;

import org.openqa.selenium.By;

public enum MyHabitLocators implements Locator {
    USERNAME_LABEL(By.cssSelector(".name")),
    CITY_LABEL(By.cssSelector(".location")),
    CREDO_LABEL(By.cssSelector(".credo")),
    SOCIAL_MEDIA_ICON(By.cssSelector(".social.ng-star-inserted")),

    ADD_NEW_HABIT_BUTTON(By.xpath("//*[@id = 'create-button']")),
    EDIT_PROFILE_BUTTON(By.cssSelector(".edit-icon"))
    ;

    private final By path;

    MyHabitLocators(By path) {
        this.path = path;
    }

    @Override
    public By getPath() {
        return path;
    }
}