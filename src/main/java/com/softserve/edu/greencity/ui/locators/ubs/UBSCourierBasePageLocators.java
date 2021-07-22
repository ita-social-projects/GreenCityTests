package com.softserve.edu.greencity.ui.locators.ubs;

import com.softserve.edu.greencity.ui.locators.Locator;
import org.openqa.selenium.By;

public enum UBSCourierBasePageLocators implements Locator {
    ORDER_DETAILS(By.xpath("//mat-step-header[position() = 1]")), // Need to change
    ORDER_DETAILS_ICON_DONE(By.cssSelector("mat-icon.mat-icon.notranslate.text-light")),
    PERSONAL_DATA(By.xpath("//mat-step-header[position() = 2]")), // Need to change
    PAYMENT(By.xpath("//mat-step-header[position() = 3]")); // Need to change

    private By path;

    UBSCourierBasePageLocators(By path) {
        this.path = path;
    }

    @Override
    public By getPath() {
        return path;
    }
}
