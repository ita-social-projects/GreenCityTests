package com.softserve.edu.greencity.ui.locators.ubs;

import com.softserve.edu.greencity.ui.locators.Locator;
import org.openqa.selenium.By;

public enum PaymentPageLocators implements Locator {
   CANCEL_BUTTON(By.xpath("//app-ubs-submit-order//button[contains(@class, 'cansel')]")),
    ORDER_BUTTON(By.xpath("//app-ubs-submit-order//div[contains(@class, 'content-end')]//button[@type = 'submit']")),
  BACK_BUTTON(By.xpath("//app-ubs-submit-order//button[contains(@class, 'back')]")),
    YOUR_ORDER_LABEL(By.xpath("//div[@class='container ng-star-inserted']//h3[@class='font-bolt order-title']")),
    ORDER_AMOUNT_LABEL(By),
    SERTIFICATE_LABEL(By),
    AMOUNT_DUE_LABEL(By),
    DELIVERY_FROM_ECO_STORE_LABEL(By.xpath("//div[@class='order-info ng-star-inserted']/p")),
    ORDER_NUMBERS_LABEL(By.xpath("//ul[@class='order-list d-flex']/li[@class='order-list-item']")),
    RECIPIENT_LABEL(By.xpath("//div[@class='order-info']/p")),
    ADDRES_OF_EXPORT_ORDERED_SERVICE_LABLE(By),
    PAYMENT_LABEL(By.xpath("//div[@class='form-group comment']/h3[@class='bottom_comment_payment']"));





    private By path;

    PaymentPageLocators(By path) {
        this.path = path;
    }

    public By getPath() {
        return path;
    }
}
