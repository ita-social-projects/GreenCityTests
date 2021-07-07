package com.softserve.edu.greencity.ui.locators.ubs;

import com.softserve.edu.greencity.ui.locators.Locator;
import org.openqa.selenium.By;

public enum PersonalDataPageLocators implements Locator {
    CANCEL(By.xpath("//app-ubs-personal-information//button[contains(@class, 'cansel')]")),
    NEXT(By.xpath("//app-ubs-personal-information//div[contains(@class, 'content-end')]//button[@type = 'submit']")),
    BACK(By.xpath("//app-ubs-personal-information//button[contains(@class, 'back')]"));

    private By path;

    PersonalDataPageLocators(By path) {
        this.path = path;
    }

    public By getPath() {
        return path;
    }
}
