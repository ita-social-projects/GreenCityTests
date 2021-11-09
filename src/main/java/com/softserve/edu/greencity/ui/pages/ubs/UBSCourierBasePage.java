package com.softserve.edu.greencity.ui.pages.ubs;

import com.softserve.edu.greencity.ui.elements.ButtonElement;
import com.softserve.edu.greencity.ui.locators.ubs.UBSCourierBasePageLocators;
import com.softserve.edu.greencity.ui.pages.common.TopPart;
import org.openqa.selenium.WebDriver;

public abstract class UBSCourierBasePage extends TopPart {

    private ButtonElement orderDetailsButton;
    private ButtonElement personalDataButton;
    private ButtonElement paymentButton;

    public UBSCourierBasePage(WebDriver webDriver) {
        super(webDriver);
        //initElements();
    }

    public void initElements() {
        orderDetailsButton = new ButtonElement(driver, UBSCourierBasePageLocators.ORDER_DETAILS);
        personalDataButton = new ButtonElement(driver, UBSCourierBasePageLocators.PERSONAL_DATA);
        paymentButton = new ButtonElement(driver, UBSCourierBasePageLocators.PAYMENT);
    }

    public OrderDetailsPage clickOnOrderDetailsButton() {
        orderDetailsButton.click();
        return new OrderDetailsPage(driver);
    }

    public PersonalDataPage clickOnPersonalDataButton() {
        personalDataButton.click();
        return new PersonalDataPage(driver);
    }

    public PaymentPage clickOnPaymentButton() {
        paymentButton.click();
        return new PaymentPage(driver);
    }

    public ButtonElement getPersonalDataButton() {
        if (personalDataButton == null) {
            personalDataButton = new ButtonElement(driver, UBSCourierBasePageLocators.PERSONAL_DATA);
        }
        return personalDataButton;
    }

    public ButtonElement getOrderDetailsButton() {
        if (orderDetailsButton == null) {
            orderDetailsButton = new ButtonElement(driver, UBSCourierBasePageLocators.ORDER_DETAILS);
        }
        return orderDetailsButton;
    }

    public ButtonElement getPaymentButton() {
        if (paymentButton == null) {
            paymentButton = new ButtonElement(driver, UBSCourierBasePageLocators.PAYMENT);
        }
        return paymentButton;
    }


}
