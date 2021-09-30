package com.softserve.edu.greencity.ui.locators.menu;

import com.softserve.edu.greencity.ui.locators.Locator;
import org.openqa.selenium.By;

public enum MenuElementsLocators implements Locator {
    USER_SETTINGS(By.cssSelector("li.tertiary-global-button > a")),
    USER_SIGNOUT(By.cssSelector("#header_user-wrp > li:nth-child(3) > a")),
    USER_NAME_MENU(By.cssSelector("#header_user-wrp")),
    UBS_ADMIN_BUTTON(By.cssSelector("#header_user-wrp > li:nth-child(5) > a")),
    COPYRIGHT(By.cssSelector(".footer_bottom-part")),

    SIGN_IN(By.cssSelector(".header_sign-in-link")),
    SIGN_UP(By.xpath("//div[@class='header_sign-up-btn secondary-global-button']"))
    ;

    private final By path;

    MenuElementsLocators(By path) {
        this.path = path;
    }

    @Override
    public By getPath() {
        return path;
    }
}
