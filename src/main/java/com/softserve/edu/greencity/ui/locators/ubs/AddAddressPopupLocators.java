package com.softserve.edu.greencity.ui.locators.ubs;
import com.softserve.edu.greencity.ui.locators.Locator;
import org.openqa.selenium.By;

public enum AddAddressPopupLocators implements Locator{
    CANCEL_BUTTON(By.className(".secondary-global-button.btn.m-0.mr-2")),
    ADD_ADDRESS_BUTTON(By.xpath("//*[@id='mat-dialog-6']/app-ubs-add-address-pop-up/div/button[2]")),
    CITY_INPUT(By.cssSelector(".shadow-none.form-control.ng-pristine.ng-valid.ng-touched")),
    DISTRICT_INPUT(By.xpath("//*[@id='mat-dialog-3']/app-ubs-add-address-pop-up/form/div[2]/input")),
    STREET_INPUT(By.cssSelector(".shadow-none.form-control.ng-untouched.ng-pristine.ng-invalid.pac-target-input")),
    HOUSE_INPUT(By.xpath("//*[@id=\"mat-dialog-11\"]/app-ubs-add-address-pop-up/form/div[4]/div[1]/input")),
    CORP_INPUT(By.xpath("//*[@id=\"mat-dialog-11\"]/app-ubs-add-address-pop-up/form/div[4]/div[2]/input")),
    ENTRANCE_INPUT(By.xpath("//*[@id=\"mat-dialog-11\"]/app-ubs-add-address-pop-up/form/div[4]/div[3]/input")),
    CITY_LABEL(By.cssSelector(".w-100.city-notice"))
    ;
    private By path;

    AddAddressPopupLocators(By path) {
        this.path = path;
    }

    public By getPath() {
        return path;
    }
}
