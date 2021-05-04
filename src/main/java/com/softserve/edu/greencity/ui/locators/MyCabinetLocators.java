package com.softserve.edu.greencity.ui.locators;

import org.openqa.selenium.By;

public enum MyCabinetLocators implements Locator {
    ADD_NEW_HABIT_BUTTON(By.xpath("//*[@id = 'create-button']")),
    EDIT_PROFILE_BUTTON(By.cssSelector(".edit-icon"))
    ;

    private final By path;

    MyCabinetLocators(By path) {
        this.path = path;
    }

    @Override
    public By getPath() {
        return path;
    }
}