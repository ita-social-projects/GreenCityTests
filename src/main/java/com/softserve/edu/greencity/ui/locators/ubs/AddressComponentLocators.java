package com.softserve.edu.greencity.ui.locators.ubs;

import com.softserve.edu.greencity.ui.locators.Locator;
import org.openqa.selenium.By;

public enum AddressComponentLocators implements Locator {
    SELECT_ADDRESS(By.xpath(".//input[@type = 'radio']]")),
    EDIT(By.xpath(".//img[contains(@alt, 'edit')]")),
    CITY(By.xpath(".//p[position() = 1]")),         //Need to change
    STREET(By.xpath(".//p[position() = 2]")),       //Need to change
    DISTRICT(By.xpath(".//p[position() = 3]")),     //Need to change
    DELETE(By.xpath(".//img[contains(@alt, 'delete')]"));

    private By path;

    AddressComponentLocators(By path) {
        this.path = path;
    }

    public By getPath() {
        return path;
    }
}
