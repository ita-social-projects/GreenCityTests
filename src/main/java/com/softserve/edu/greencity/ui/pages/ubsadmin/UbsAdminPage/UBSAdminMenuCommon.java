package com.softserve.edu.greencity.ui.pages.ubsadmin.UbsAdminPage;

import com.softserve.edu.greencity.ui.locators.UbsAdminLocators.UBSAdminMenuComponentLocator;
import com.softserve.edu.greencity.ui.pages.common.TopPart;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class UBSAdminMenuCommon extends TopPart {

    WebElement usersButton;
    WebElement certificatesButton;
    WebElement ordersButton;
    WebElement employeesButton;
    WebElement documentsButton;
    WebElement scheduleButton;

    public UBSAdminMenuCommon(WebDriver driver) {
        super(driver);
        init();
    }

    private void init(){
        usersButton = driver.findElement(UBSAdminMenuComponentLocator.USER_BUTTON.getPath());
        certificatesButton = driver.findElement(UBSAdminMenuComponentLocator.CERTIFICATES_BUTTON.getPath());
        ordersButton = driver.findElement(UBSAdminMenuComponentLocator.ORDERS_BUTTON.getPath());
        employeesButton = driver.findElement(UBSAdminMenuComponentLocator.EMPLOYEES_BUTTON.getPath());
        documentsButton = driver.findElement(UBSAdminMenuComponentLocator.DOCUMENT_BUTTON.getPath());
        scheduleButton = driver.findElement(UBSAdminMenuComponentLocator.SCHEDULE_BUTTON.getPath());
    }
    public UbsAdminOrders getUBSAdminOrders() {
        ordersButton.click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new UbsAdminOrders(driver);
    }

}
