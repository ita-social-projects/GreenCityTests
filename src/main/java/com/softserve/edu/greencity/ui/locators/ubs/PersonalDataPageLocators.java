package com.softserve.edu.greencity.ui.locators.ubs;

import com.softserve.edu.greencity.ui.locators.Locator;
import org.openqa.selenium.By;

public enum PersonalDataPageLocators implements Locator {
    NAME_FIELD(By.xpath("//input[contains(@formcontrolname,'firstName')]")),
    SURNAME_FIELD(By.xpath("//input[contains(@formcontrolname,'lastName')]")),
    PHONE_FIELD(By.xpath("//input[contains(@formcontrolname,'phoneNumber')]")),
    EMAIL_FIELD(By.xpath("//input[contains(@formcontrolname,'email')]")),
    ERROR_MESSAGE_FOR_NAME(By.xpath("//app-ubs-input-error//div[contains(text(),'This field is required')]")),
    ERROR_MESSAGE_FOR_SURNAME(By.xpath("//app-ubs-input-error//div[contains(text(),'This field is required')]")),
    ERROR_MESSAGE_FOR_PHONE(By.xpath("//app-ubs-input-error//div[contains(text(),'This field is required')]")),
    ERROR_MESSAGE_FOR_EMAIL(By.xpath("//app-ubs-input-error//div[contains(text(),'This field is required')]")),
    COMMENT_ADDRESS_FIELD(By.xpath("//textarea[contains(@formcontrolname,'addressComment')]")),
    CANCEL(By.xpath("//app-ubs-personal-information//button[contains(@class, 'cansel')]")),
    NEXT(By.xpath("//app-ubs-personal-information//div[contains(@class, 'content-end')]//button[@type = 'submit']")),
    BACK(By.xpath("//app-ubs-personal-information//button[contains(@class, 'back')]")),
    ADDRESS_TITLE(By.xpath("//div[contains(@class, 'adress-section')]//*[@class = 'address-title']")),
    ADDRESS_NOTIFICATION(By.xpath("//div[contains(@class, 'adress-section')]//*[@class = 'address-title']/following-sibling::p")),
    ABSENCE_OF_ADDRESSES(By.xpath("//app-ubs-personal-information//div[contains(@class, 'no-address')]/p")),
    LIST_OF_ADDRESSES(By.xpath("//div[contains(@class, 'adress-section')]//div[contains(@class, 'inserted')]")),
    ADD_ADDRESS(By.xpath("//button[contains(@class, 'add-address')]"));
    private By path;

    PersonalDataPageLocators(By path) {
        this.path = path;
    }

    public By getPath() {
        return path;
    }
}
