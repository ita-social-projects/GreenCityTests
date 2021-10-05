package com.softserve.edu.greencity.ui.locators.UbsAdminLocators;

import com.softserve.edu.greencity.ui.locators.Locator;
import org.openqa.selenium.By;

public enum UBSAdminViewTableComponentLocator implements Locator {
    CHECK_BOX_NAME(By.xpath("//li[@class='ng-star-inserted']")),
    CLEAR_BUTTON(By.xpath("//ul/button[@class='btn btn-info']"));

    private By path;
    UBSAdminViewTableComponentLocator(By path){
        this.path = path;
    }
    @Override
    public By getPath() {
        return null;
    }
}
