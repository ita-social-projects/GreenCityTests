package com.softserve.edu.greencity.ui.locators.ubs;

import com.softserve.edu.greencity.ui.locators.Locator;
import org.openqa.selenium.By;

public enum OrderDetailsPageLocators implements Locator {
    CANCEL(By.xpath("//app-ubs-order-details//button[contains(@class, 'cansel')]")),
    NEXT(By.xpath("//app-ubs-order-details//div[contains(@class, 'content-end')]//button[@type = 'submit']")),

    POINTS_BALANCE_LABEL(By.cssSelector(".points p")),
    ADDITIONAL_CERTIFICATES(By.cssSelector(".addCertificate")),
    ADDITIONAL_CERTIFICATE_MESSAGE(By.cssSelector(".validMes small")),
    ADDITIONAL_CERTIFICATE_INPUT(By.cssSelector(".addCertificate input")),
    ADDITIONAL_CERTIFICATE_ACTIVATE_BUTTON(By.cssSelector(".addCertificate button")),
    ADD_CERTIFICATE_BUTTON(By.cssSelector(".addCertificateBtn")),
    CERTIFICATE_INPUT(By.cssSelector(".certificate-container input")),
    ACTIVATE_BUTTON(By.cssSelector(".certificate-container button")),
    CERTIFICATE_MESSAGE(By.cssSelector(".messages-container small")),

    ECO_STORE_LABEL(By.cssSelector("div.bottom > h3")),
    ECO_STORE_QUESTION(By.cssSelector("div.bottom > h5")),
    YES_WAITING_ORDER_RADIO_BUTTON(By.cssSelector("div.bottom > div:nth-child(3) > label")),
    NO_WAITING_ORDER_RADIO_BUTTON(By.cssSelector("div.bottom > div:nth-child(4) > label")),
    ORDER_NUMBER_INPUT(By.cssSelector("div.form-group.ng-star-inserted.ng-dirty.ng-touched.ng-valid > input")),
    INCORRECT_ORDER_NUMBER_MESSAGE(By.cssSelector("small.text-danger ng-star-inserted")),
    ADD_ANOTHER_ORDER_BUTTON(By.cssSelector("div.form-group.shop_submit > button")),
    ANOTHER_ORDER_NUMBER(By.cssSelector("div.form-group.shop_submit")),
    ANOTHER_ORDER_NUMBER_INPUT(By.cssSelector("div.form-group.shop_submit > div:nth-child(3) > input")),

    COMMENT_LABEL(By.cssSelector("app-ubs-order-details .comment h3")),
    COMMENT_TEXTAREA(By.cssSelector("app-ubs-order-details .comment textarea")),
    COMMENT_ALERT_LABEL(By.cssSelector("app-ubs-order-details .bottom_comment small"));

    private By path;

    OrderDetailsPageLocators(By path) {
        this.path = path;
    }

    public By getPath() {
        return path;
    }
}
