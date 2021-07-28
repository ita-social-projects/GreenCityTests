package com.softserve.edu.greencity.ui.locators.ubs;

import com.softserve.edu.greencity.ui.locators.Locator;
import org.openqa.selenium.By;

public enum PaymentPageLocators implements Locator {
    CANCEL_BUTTON(By.xpath("//app-ubs-submit-order//button[contains(@class, 'cansel')]")),
    ORDER_BUTTON(By.xpath("//app-ubs-submit-order//div[contains(@class, 'content-end')]//button[@type = 'submit']")),
    BACK_BUTTON(By.xpath("//app-ubs-submit-order//button[contains(@class, 'back')]")),

    YOUR_ORDER_LABEL(By.xpath("//div[@class='container ng-star-inserted']//h3[@class='font-bolt order-title']")),
    SERVICE_LABEL(By.xpath("//app-ubs-submit-order//label[@class='col-3 label m-0']")),
    VOLUME_LABEL(By.xpath("//div[@id='cdk-step-content-0-2']//div[@class='w-100 d-flex justify-content-between mb-2']/label[position()=2]")),

    COST_LABEL(By.xpath("//app-ubs-submit-order//div[@class='w-100 d-flex justify-content-between mb-2']//label[position()=3]")),//not so good
    NUMBER_OF_PACKAGES_LABEL(By.xpath("//app-ubs-submit-order//div[@class='w-100 d-flex justify-content-between mb-2']//label[position()=4]")),//not so good
    TOTAL_LABEL(By.xpath("//app-ubs-submit-order//label[@class='col-3 label m-0 text-center']")),
    OLD_CLOTHES_CHEAP_PRICE_LABEL(By.xpath("//app-ubs-submit-order//div[@class='services']//li[position()=1]/span[position()=1]")),//not so good
    OLD_CLOTHES_EXPENSIVE_PRICE_LABEL(By.xpath("//app-ubs-submit-order//div[@class='services']//li[position()=2]/span[position()=1]")),//not so good
    RECYCLED_MATERIALS_LABEL(By.xpath("//app-ubs-submit-order//div[@class='services']//li[position()=3]/span[position()=1]")),//not so good

    OLD_CLOTHES_CHEAP_PRICE_NUMBER_PACKAGES_LABEL(By.xpath("//app-ubs-submit-order//div[@class='services']//li[position()=1]//span[position()=4]")),
    OLD_CLOTHES_EXPENSIVE_PRICE_NUMBER_PACKAGES_LABEL(By.xpath("//app-ubs-submit-order//div[@class='services']//li[position()=2]/span[position()=4]")),
    RECYCLED_MATERIALS_NUMBER_PACKAGES_LABEL(By.xpath("//app-ubs-submit-order//div[@class='services']//li[position()=3]/span[position()=4]")),
    OLD_CLOTHES_CHEAP_PRICE_TOTAL_SUM_LABEL(By.xpath("//app-ubs-submit-order//div[@class='services']//li[position()=1]//span[position()=5]")),
    OLD_CLOTHES_EXPENSIVE_TOTAL_SUM_LABEL(By.xpath("//app-ubs-submit-order//div[@class='services']//li[position()=2]/span[position()=5]")),
    RECYCLED_MATERIALS_NUMBER_TOTAL_SUM_LABEL(By.xpath("//app-ubs-submit-order//div[@class='services']//li[position()=3]/span[position()=5]")),


    ORDER_AMOUNT_LABEL(By.cssSelector("app-ubs-submit-order p.total-content:first-child strong")),
    CERTIFICATE_LABEL(By.xpath("//app-ubs-submit-order//div[@class='showTotal']//p[@class='total-content ng-star-inserted']//strong")),
    AMOUNT_DUE_LABEL(By.cssSelector("app-ubs-submit-order p.total-content:last-child strong")),


    DELIVERY_FROM_ECO_STORE_LABEL(By.xpath("//div[@class='order-info ng-star-inserted']/p")),
    ORDER_NUMBERS_LABEL(By.xpath("//ul[@class='order-list d-flex']/li[@class='order-list-item ng-star-inserted']")),

    RECIPIENT_LABEL(By.xpath("//div[@class='order-info']/p")),
    FULL_NAME_LABEL(By.xpath("//div[@class='order-info']//ul[@class='order-list']/li[position()=1]")),
    PHONE_LABEL(By.xpath("//div[@class='order-info']//ul[@class='order-list']/li[position()=2]")),
    GMAIL_LABEL(By.xpath("//div[@class='order-info']//ul[@class='order-list']/li[position()=3]")),


    ADDRESS_OF_EXPORT_ORDERED_SERVICE_LABEL(By.xpath("//app-ubs-submit-order//div[@class='order-info-container d-flex flex-column']//div[@class='order-info m-0']/p")),
    TOWN_LABEL(By.xpath("//div[@class='order-info m-0']//ul/li[position()=1]")),
    STREET_LABEL(By.xpath("//div[@class='order-info m-0']//ul/li[position()=2]")),
    DISTRICT_LABEL(By.xpath("//div[@class='order-info m-0']//ul/li[position()=3]")),
    COMMENT_TO_ADDRESS_LABEL(By.xpath("//div[@class='order-info m-0']//ul[@class='order-list']//li[position()=4]")),
    COMMENT_TO_ORDER_LABEL(By.xpath("//div[@class='order-info m-0']//ul[@class='order-list']//li[position()=5]")),
    TOTAL_ADDRESS_OF_EXPORT_LABEL(By.xpath("//div[@class='order-info m-0']//ul[@class='order-list']/li[position()=4]/preceding-sibling::li")),
    PAYMENT_METHOD(By.xpath("//app-ubs-submit-order//div[@class='form-group comment']//select"));


    private final By path;

    PaymentPageLocators(By path) {
        this.path = path;
    }

    public By getPath() {
        return path;
    }
}
