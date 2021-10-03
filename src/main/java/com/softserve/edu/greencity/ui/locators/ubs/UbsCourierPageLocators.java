package com.softserve.edu.greencity.ui.locators.ubs;

import com.softserve.edu.greencity.ui.locators.Locator;
import org.openqa.selenium.By;


public enum UbsCourierPageLocators implements Locator {
    CALL_UP_THE_COURIER_BUTTON(By.xpath("(//button[@class='button primary-global-button'])[1]"));
    private By path;

    UbsCourierPageLocators(By path) {
        this.path = path;
    }

    @Override
    public By getPath() {
        return path;
    }
}
