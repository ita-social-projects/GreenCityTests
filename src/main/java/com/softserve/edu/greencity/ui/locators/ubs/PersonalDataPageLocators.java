package com.softserve.edu.greencity.ui.locators.ubs;

import com.softserve.edu.greencity.ui.locators.Locator;
import org.openqa.selenium.By;

public enum PersonalDataPageLocators implements Locator {
    NAME_FIELD(By.cssSelector("input.shadow-none.form-control[5]")),
    SURNAME_FIELD(By.cssSelector("input.shadow-none.form-control[7]")),
    PHONE_FIELD(By.cssSelector("input.shadow-none.form-control[6]")),
    EMAIL_FIELD(By.cssSelector("input.shadow-none.form-control[8]")),
    COMMENT_ADDRESS_FIELD(By.cssSelector("textarea.form-control.ng-pristine[1]")),
    CANCEL(By.xpath("//app-ubs-personal-information//button[contains(@class, 'cansel')]")),
    NEXT(By.xpath("//app-ubs-personal-information//div[contains(@class, 'content-end')]//button[@type = 'submit']")),
    BACK(By.xpath("//app-ubs-personal-information//button[contains(@class, 'back')]")),
    ADDRESS_TITLE(By.xpath("//div[contains(@class, 'adress-section')]//*[@class = 'address-title']")),
    ADDRESS_NOTIFICATION(By.xpath("//div[contains(@class, 'adress-section')]//*[@class = 'address-title']/following-sibling::p")),
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
