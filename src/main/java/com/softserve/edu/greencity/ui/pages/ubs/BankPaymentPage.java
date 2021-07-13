package com.softserve.edu.greencity.ui.pages.ubs;

import org.openqa.selenium.WebDriver;

public class BankPaymentPage {
    WebDriver webDriver;

    public BankPaymentPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public String getURL() {
        return webDriver.getCurrentUrl();
    }
}
