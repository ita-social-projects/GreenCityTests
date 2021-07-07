package com.softserve.edu.greencity.ui.pages.ubs;

import com.softserve.edu.greencity.ui.elements.ButtonElement;
import com.softserve.edu.greencity.ui.locators.ubs.OrderDetailsPageLocators;
import com.softserve.edu.greencity.ui.pages.common.WelcomePage;
import org.openqa.selenium.WebDriver;

public class OrderDetailsPage extends UBSCourierBasePage {

    private ButtonElement cancelButton;
    private ButtonElement nextButton;

    public OrderDetailsPage(WebDriver webDriver) {
        super(webDriver);
    }

    private ButtonElement getNextButton() {
        if (nextButton == null) {
            nextButton = new ButtonElement(driver, OrderDetailsPageLocators.NEXT);
        }
        return nextButton;
    }

    private ButtonElement getCancelButton() {
        if (cancelButton == null) {
            cancelButton = new ButtonElement(driver, OrderDetailsPageLocators.CANCEL);
        }
        return cancelButton;
    }

    public PersonalDataPage clickOnNextButton() {
        getNextButton().click();
        return new PersonalDataPage(driver);
    }

    public CancelOrderPopupComponent clickOnCancelButton() {
        getCancelButton().click();
        return new CancelOrderPopupComponent(driver, this);
    }

}
