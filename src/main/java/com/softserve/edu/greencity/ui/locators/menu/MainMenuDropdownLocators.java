package com.softserve.edu.greencity.ui.locators.menu;

import com.softserve.edu.greencity.ui.locators.Locator;
import org.openqa.selenium.By;

public enum MainMenuDropdownLocators implements Locator {
    NAVICON_BUTTON(By.cssSelector("div.menu-icon")),
    MENU_ECO_NEWS(By.cssSelector(".header_navigation-menu-left > ul> li > a[href*='#/news']")),
    MENU_TIPS_TRICS(By.cssSelector(".header_navigation-menu-left > ul > li > a[href*='/tips']")),
    MENU_PLACES(By.cssSelector(".header_navigation-menu-left > ul > li > a[href*='/map']")), //MENU_MAP
    MENU_ABOUT(By.cssSelector(".header_navigation-menu-left > ul > li > a[href*='/about']")),
    MENU_MY_HABITS(By.cssSelector(".header_navigation-menu-left > ul > li > a[href*='/profile']")),

    FOOTER_ECO_NEWS(By.cssSelector("app-footer a[href*='/news']")),
    FOOTER_TIPS_TRICS(By.cssSelector("app-footer a[href*='/tips']")),
    FOOTER_PLACES(By.cssSelector("app-footer a[href*='/map']")),
    FOOTER_ABOUT(By.cssSelector("app-footer a[href*='/about']")),
    FOOTER_MY_HABITS(By.cssSelector("app-footer a[href*='/profile']"))
    ;

    private final By path;

    MainMenuDropdownLocators(By path) {
        this.path = path;
    }

    @Override
    public By getPath() {
        return path;
    }
}
