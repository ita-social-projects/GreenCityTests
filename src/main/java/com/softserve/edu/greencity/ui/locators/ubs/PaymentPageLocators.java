package com.softserve.edu.greencity.ui.locators.ubs;

import com.softserve.edu.greencity.ui.locators.Locator;
import org.openqa.selenium.By;

public enum PaymentPageLocators implements Locator {
    CANCEL(By.xpath("//app-ubs-submit-order//button[contains(@class, 'cansel')]")),
    ORDER(By.xpath("//app-ubs-submit-order//div[contains(@class, 'content-end')]//button[@type = 'submit']")),
    BACK(By.xpath("//app-ubs-submit-order//button[contains(@class, 'back')]"));

    private By path;

    PaymentPageLocators(By path) {
        this.path = path;
    }

    public By getPath() {
        return path;
    }
}
