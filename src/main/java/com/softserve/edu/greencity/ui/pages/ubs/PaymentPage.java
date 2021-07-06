package com.softserve.edu.greencity.ui.pages.ubs;

import com.softserve.edu.greencity.ui.elements.ButtonElement;
import com.softserve.edu.greencity.ui.locators.ubs.OrderDetailsPageLocators;
import org.openqa.selenium.WebDriver;

public class PaymentPage extends UBSCourierBasePage {

    private ButtonElement cancelButton;
    private ButtonElement orderButton;
    private ButtonElement backButton;

    public PaymentPage(WebDriver webDriver) {
        super(webDriver);
    }

    private ButtonElement getOrderButton() {
        if (orderButton == null) {
            orderButton = new ButtonElement(driver, OrderDetailsPageLocators.NEXT);
        }
        return orderButton;
    }

    private ButtonElement getCancelButton() {
        if (cancelButton == null) {
            cancelButton = new ButtonElement(driver, OrderDetailsPageLocators.CANCEL);
        }
        return cancelButton;
    }

    private ButtonElement getBackButton() {
        if (backButton == null) {
            backButton = new ButtonElement(driver, OrderDetailsPageLocators.CANCEL);
        }
        return backButton;
    }

    public PaymentPage clickOnOrderButton() {   //return type?
        getOrderButton().click();
        return this;
    }

    public CancelOrderPopupComponent clickOnCancelButton() {
        getCancelButton().click();
        return new CancelOrderPopupComponent(driver, this);
    }

    public PersonalDataPage clickOnBackButton() {
        getBackButton().click();
        return new PersonalDataPage(driver);
    }
}
