package com.softserve.edu.greencity.ui.locators.ubs;

import com.softserve.edu.greencity.ui.locators.Locator;
import org.openqa.selenium.By;

public enum ChooseLocationPopupLocators implements Locator {
    CHOOSE_LOCATION_CONTINUE_POPUP_BUTTON(By.xpath("//button[@class='btn primary-global-button']"));

    private final By path;

    ChooseLocationPopupLocators(By path) {
        this.path = path;
    }

    @Override
    public By getPath() {
        return path;
    }
}
