package com.softserve.edu.greencity.ui.locators.UbsAdminLocators;

import org.openqa.selenium.By;

public enum UbsAdminRowTableComponentLocator {
    ADDRESS_CELL(By.className("cdk-column-address")),
    COMMENT_TO_ADDRESS_CELL(By.className("cdk-column-comment_for_order_by_client")),
    CHECKBOX(By.xpath("//tbody//div[@class='mat-checkbox-inner-container mat-checkbox-inner-container-no-side-margin']")),
    TABLE_HEAD_CHECKBOX(By.className("cdk-column-select")),
    CHECKING_STATE_OF_TABLE_HEAD_CHECKBOX(By.xpath("//div[@class='mat-checkbox-inner-container mat-checkbox-inner-container-no-side-margin']/input")),
    CHECKING_STATE_OF_CHECKBOX(By.xpath("//tbody//div[@class='mat-checkbox-inner-container mat-checkbox-inner-container-no-side-margin']/input")),
    TOTAL_ORDER_SUM(By.className("cdk-column-total_order_sum")),
    ORDER_CERTIFICATE_POINTS(By.className("cdk-column-order_certificate_points")),
    AMOUNT_DUE(By.className("cdk-column-amount_due")),
    ORDERDATE(By.className("cdk-column-order_date")),
    CLIENTNAME(By.className("cdk-column-clientname"));

    private final By path;

    UbsAdminRowTableComponentLocator(By path) {
        this.path = path;
    }


    public By getPath() {
        return path;
    }
}
