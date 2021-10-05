package com.softserve.edu.greencity.ui.pages.ubsadmin.UbsAdminPage;

import com.softserve.edu.greencity.ui.locators.UbsAdminLocators.UbsAdminTableComponentLocator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class UbsAdminTableComponent extends UbsAdminOrders {
    private List<UbsAdminRowTableComponent> rows;



    public UbsAdminTableComponent(WebDriver driver) {
        super(driver);
    }

    public List<UbsAdminRowTableComponent> getRows() {
        rows = new ArrayList<>();
        List<WebElement> webElements = driver.findElements(UbsAdminTableComponentLocator.ROW.getPath());
        for (WebElement element: webElements) {
            rows.add(new UbsAdminRowTableComponent(driver, element));
        }
        return rows;
    }
    public List<String> getColumnOrderID() {
        List<String>rows = new ArrayList<>();
        List<WebElement> webElements = driver.findElements(UbsAdminTableComponentLocator.COLUMN_ORDER_ID.getPath());
        for (WebElement element: webElements) {
            rows.add(element.getText());
        }
        return rows;
    }
    public List<String> getColumnEmail(){
        List<String> column = new ArrayList<>();
        List<WebElement> webElem = driver.findElements(UbsAdminTableComponentLocator.COLUMN_EMAIL.getPath());
        for (WebElement elem: webElem){
            column.add(elem.getText());
        }
        return column;
    }
    public List<String> getColumnDistrict() {
        List<String> rows = new ArrayList<>();
        List<WebElement> webElements = driver.findElements(UbsAdminTableComponentLocator.COLUMN_DISTRICT.getPath());
        for (WebElement element: webElements) {
            rows.add(element.getText());
        }
        return rows;
    }
}
