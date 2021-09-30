package com.softserve.edu.greencity.ui.locators.UbsAdminLocators;

import org.openqa.selenium.By;

public enum UbsAdminTableComponentLocator {
    ROW(By.xpath("//tbody/mat-row[@role='row']")),
    TABLE(By.xpath("//table[contains(@class,'mat-table')]")),
    COLUMN_ORDER_ID(By.xpath("//mat-cell[2]")),
    COLUMN_DISTRICT(By.xpath("//mat-cell[9]")),
    COLUMN_EMAIL(By.xpath("//mat-cell[7]"));


    private final By path;

    UbsAdminTableComponentLocator(By path) {
        this.path = path;
    }


    public By getPath() {
        return path;
    }
}
