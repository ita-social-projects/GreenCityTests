package com.softserve.edu.greencity.ui.locators.ubs;

import com.softserve.edu.greencity.ui.locators.Locator;
import org.openqa.selenium.By;

public enum PaymentPageLocators implements Locator {
    CANCEL_BUTTON(By.xpath("//app-ubs-submit-order//button[contains(@class, 'cansel')]")),
    ORDER_BUTTON(By.xpath("//app-ubs-submit-order//div[contains(@class, 'content-end')]//button[@type = 'submit']")),
    BACK_BUTTON(By.xpath("//app-ubs-submit-order//button[contains(@class, 'back')]")),

    YOUR_ORDER_LABEL(By.xpath("//div[@class='container ng-star-inserted']//h3[@class='font-bolt order-title']")),
    SERVICE_LABEL(By.xpath("//app-ubs-submit-order//label[@class='col-3 label m-0']")),

    VOLUME_LABEL(By.xpath("(//app-ubs-submit-order//div[@class='w-100 d-flex justify-content-between mb-2']//label[position()=2]")),// not so good
    COST_LABEL(By.xpath("//app-ubs-submit-order//div[@class='w-100 d-flex justify-content-between mb-2']//label[position()=3]")),//not so good
    NUMBER_OF_PACKAGES_LABEL(By.xpath("//app-ubs-submit-order//div[@class='w-100 d-flex justify-content-between mb-2']//label[position()=4]")),//not so good
    TOTAL_LABEL(By.xpath("//app-ubs-submit-order//label[@class='col-3 label m-0 text-center']")),
    OLD_CLOTHES_FIRST_LABEL(By.xpath("//app-ubs-submit-order//div[@class='services']//li[position()=1]/span[position()=1]")),//not so good
    OLD_CLOTHES_SECOND_LABEL(By.xpath("//app-ubs-submit-order//div[@class='services']//li[position()=2]/span[position()=1]")),//not so good
    RECYCLED_MATERIALS_LABEL(By.xpath("//app-ubs-submit-order//div[@class='services']//li[position()=3]/span[position()=1]")),//not so good

    ORDER_AMOUNT_LABEL(By.xpath("//app-ubs-submit-order//p[@class='total-content ng-star-inserted']/preceding-sibling::p")),
    CERTIFICATE_LABEL(By.xpath("//app-ubs-submit-order//div[@class='showTotal']//p[@class='total-content ng-star-inserted']")),
    AMOUNT_DUE_LABEL(By.xpath("//app-ubs-submit-order//p[@class='total-content ng-star-inserted']/following-sibling::p")),

    DELIVERY_FROM_ECO_STORE_LABEL(By.xpath("//div[@class='order-info ng-star-inserted']/p")),
    ORDER_NUMBERS_LABEL(By.xpath("//ul[@class='order-list d-flex']/li[@class='order-list-item']")),

    RECIPIENT_LABEL(By.xpath("//div[@class='order-info']/p")),
    FULL_NAME_LABEL(By.xpath("//div[@class='order-info']//ul[@class='order-list']/li[position()=1]")),
    PHONE_LABEL(By.xpath("//div[@class='order-info']//ul[@class='order-list']/li[position()=2]")),
    GMAIL_LABEL(By.xpath("//div[@class='order-info']//ul[@class='order-list']/li[position()=2]")),


    ADDRESS_OF_EXPORT_ORDERED_SERVICE_LABEL(By.xpath("//app-ubs-submit-order//div[@class='order-info-container d-flex flex-column']//div[@class='order-info m-0']/p")),
    TOWN_LABEL(By.xpath("//div[@class='order-info m-0']//ul/li[position()=1]")),
    ADDRESS_LABEL(By.xpath("//div[@class='order-info m-0']//ul/li[position()=2]")),
    DISTRICT_LABEL(By.xpath("//div[@class='order-info m-0']//ul/li[position()=3]")),
    COMMENT_TO_ADDRESS_LABEL(By.xpath("//div[@class='order-info m-0']//ul[@class='order-list']//li[position()=4]")),
    COMMENT_TO_ORDER_LABEL(By.xpath("//div[@class='order-info m-0']//ul[@class='order-list']//li[position()=5]")),

    PAYMENT_LABEL(By.xpath("//app-ubs-submit-order//div[@class='form-group comment']"));





    private By path;

    PaymentPageLocators(By path) {
        this.path = path;
    }

    public By getPath() {
        return path;
    }
}
