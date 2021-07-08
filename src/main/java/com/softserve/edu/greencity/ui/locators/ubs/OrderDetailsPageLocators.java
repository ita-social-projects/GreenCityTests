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
