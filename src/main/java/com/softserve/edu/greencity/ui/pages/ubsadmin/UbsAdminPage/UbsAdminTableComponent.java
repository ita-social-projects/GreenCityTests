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

    public UbsAdminRowTableComponent getRowById(String id){
        getRows();
        if(rows.size() == 0)
            return null;
        for (UbsAdminRowTableComponent row : rows) {
            if (row.getOrderIdText().equals(id))
                return row;
        }
        return null;
    }

    public long[] getActualAndExpectedAmountDue(){
        UbsAdminRowTableComponent rowTableComponent
                = this
                .getRows()
                .get(0);
        long totalOrderSum = rowTableComponent.getTotalOrderSumValue();
        long orderCertificatePoints = rowTableComponent.getOrderCertificatePointsValue();
        long amountDue = Long.parseLong(rowTableComponent.getAmountDue().getText());
        if(totalOrderSum <= orderCertificatePoints)
            return new long[]{amountDue, 0};
        return new long[] {amountDue, totalOrderSum - orderCertificatePoints};
    }

    public boolean isAmountDueFloatWithTwoDigits(){
        UbsAdminRowTableComponent rowTableComponent
                = this
                .getRows()
                .get(0);
        if(rowTableComponent == null)
            return false;
        String amountDue = rowTableComponent.getAmountDue().getText();
        try {
            Double.parseDouble(amountDue);
        }catch (NumberFormatException e){
            return false;
        }
        if (amountDue.charAt(amountDue.length() - 3) != '.')
            return false;
        return true;
    }

    public boolean isOrderCertificatePointsPositiveInteger(){
        UbsAdminRowTableComponent rowTableComponent
                = this
                .getRows()
                .get(0);
        if(rowTableComponent == null)
            return false;
        String orderCertificatePoints = rowTableComponent.getOrderCertificatePoints().getText();
        try {
            if (Integer.parseInt(orderCertificatePoints) < 0)
                return false;
        }catch (NumberFormatException e){
            return false;
        }
        return true;
    }
}
