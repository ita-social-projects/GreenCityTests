package com.softserve.edu.greencity.ui.locators.UbsAdminLocators;

import com.softserve.edu.greencity.ui.locators.Locator;
import org.openqa.selenium.By;

public enum UBSAdminMenuComponentLocator implements Locator {

    USER_BUTTON(By.xpath("/html/body/app-root/app-ubs-admin/app-ubs-admin-sidebar/app-ubs-base-sidebar/mat-drawer-container/mat-drawer/div/ul/li[1]/a")),
    CERTIFICATES_BUTTON(By.xpath("/html/body/app-root/app-ubs-admin/app-ubs-admin-sidebar/app-ubs-base-sidebar/mat-drawer-container/mat-drawer/div/ul/li[2]/a")),
    ORDERS_BUTTON(By.xpath("/html/body/app-root/app-ubs-admin/app-ubs-admin-sidebar/app-ubs-base-sidebar/mat-drawer-container/mat-drawer/div/ul/li[3]/a")),
    EMPLOYEES_BUTTON(By.xpath("/html/body/app-root/app-ubs-admin/app-ubs-admin-sidebar/app-ubs-base-sidebar/mat-drawer-container/mat-drawer/div/ul/li[4]/a")),
    DOCUMENT_BUTTON(By.xpath("/html/body/app-root/app-ubs-admin/app-ubs-admin-sidebar/app-ubs-base-sidebar/mat-drawer-container/mat-drawer/div/ul/li[6]/a")),
    SCHEDULE_BUTTON(By.xpath("/html/body/app-root/app-ubs-admin/app-ubs-admin-sidebar/app-ubs-base-sidebar/mat-drawer-container/mat-drawer/div/ul/li[6]/a"));

    private final By path;
    UBSAdminMenuComponentLocator(By path) {
        this.path = path;
    }
    @Override
    public By getPath() {
        return path;
    }
}
