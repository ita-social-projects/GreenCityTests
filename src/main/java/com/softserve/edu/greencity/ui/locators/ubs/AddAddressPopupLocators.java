package com.softserve.edu.greencity.ui.locators.ubs;

import com.softserve.edu.greencity.ui.locators.Locator;
import org.openqa.selenium.By;

public enum AddAddressPopupLocators implements Locator {
    CANCEL_BUTTON(By.cssSelector(".secondary-global-button.btn.m-0.mr-2")),
    ADD_ADDRESS_BUTTON(By.xpath("//app-ubs-add-address-pop-up/div/button[contains(@class, 'primary')]")),

    CITY_INPUT(By.xpath("//app-ubs-add-address-pop-up//select")),
    CITY_KIEV(By.xpath("//app-ubs-add-address-pop-up//select[@formcontrolname='city']/option[@value='Kiev']")),

    DISTRICT_INPUT(By.xpath("//app-ubs-add-address-pop-up/form/div[2]/input")),
    STREET_INPUT(By.xpath("//app-ubs-add-address-pop-up//input[@formcontrolname = 'street']")),
    HOUSE_INPUT(By.xpath("//app-ubs-add-address-pop-up/form/div[4]/div[1]/input")),
    CORP_INPUT(By.xpath("//app-ubs-add-address-pop-up/form/div[4]/div[2]/input")),
    ENTRANCE_INPUT(By.xpath("//app-ubs-add-address-pop-up/form/div[4]/div[3]/input")),

    CITY_LABEL(By.cssSelector(".w-100.city-notice")),
    CITY_MESSAGE_INFO(By.cssSelector(".w-100.city-notice")),

    STREET_ALERT_MESSAGE(By.xpath("//*[@id='mat-dialog-4']/app-ubs-add-address-pop-up/form/div[3]/div/app-ubs-input-error/div")),
    DISTRICT_ALERT_MESSAGE(By.xpath("//*[@id='mat-dialog-1']/app-ubs-add-address-pop-up/form/div[2]/div/app-ubs-input-error/div")),
    HOUSE_ALERT_MESSAGE(By.xpath("//*[@id='mat-dialog-2']/app-ubs-add-address-pop-up/form/div[4]/div[1]/div/app-ubs-input-error/div")),

    ;

    private By path;

    AddAddressPopupLocators(By path) {
        this.path = path;
    }

    public By getPath() {
        return path;
    }
}
