package com.softserve.edu.greencity.ui.locators;

import org.openqa.selenium.By;

/**
 * The default interface for all locator enums. Each enum with locators should implement this
 */
public interface Locator {
    public By getPath();
}
