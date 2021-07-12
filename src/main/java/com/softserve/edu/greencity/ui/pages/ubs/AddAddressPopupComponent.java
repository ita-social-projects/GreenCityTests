package com.softserve.edu.greencity.ui.pages.ubs;

import com.softserve.edu.greencity.ui.elements.ButtonElement;
import com.softserve.edu.greencity.ui.elements.InputElement;
import com.softserve.edu.greencity.ui.elements.LabelElement;
import com.softserve.edu.greencity.ui.locators.ubs.AddAddressPopupLocators;
import com.softserve.edu.greencity.ui.locators.ubs.OrderDetailsPageLocators;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddAddressPopupComponent extends UBSCourierBasePage {
    private WebDriverWait wait;

    private ButtonElement cancelButton;
    private ButtonElement addAddressButton;
    private InputElement cityInput;
    private InputElement districtInput;
    private InputElement streetInput;
    private InputElement houseInput;
    private InputElement corpInput;
    private InputElement entranceInput;
    private LabelElement cityLabel;

    public AddAddressPopupComponent(WebDriver webDriver) {
        super(webDriver);
        initElements();
    }

    public void initElements() {
        cancelButton = new ButtonElement(driver, AddAddressPopupLocators.CANCEL_BUTTON);
        addAddressButton = new ButtonElement(driver, AddAddressPopupLocators.ADD_ADDRESS_BUTTON);
        cityInput = new InputElement(driver, AddAddressPopupLocators.CITY_INPUT);
        districtInput = new InputElement(driver, AddAddressPopupLocators.DISTRICT_INPUT);
        streetInput = new InputElement(driver, AddAddressPopupLocators.STREET_INPUT);
        houseInput = new InputElement(driver, AddAddressPopupLocators.HOUSE_INPUT);
        corpInput = new InputElement(driver, AddAddressPopupLocators.CORP_INPUT);
        entranceInput = new InputElement(driver, AddAddressPopupLocators.ENTRANCE_INPUT);
        cityLabel = new LabelElement(driver, AddAddressPopupLocators.CITY_LABEL);
    }

    private ButtonElement getCancelButton() {
        if (cancelButton == null) {
            cancelButton = new ButtonElement(driver, AddAddressPopupLocators.CANCEL_BUTTON);
        }
        return cancelButton;
    }

    private ButtonElement getAddButton() {
        if (addAddressButton == null) {
            addAddressButton = new ButtonElement(driver, AddAddressPopupLocators.ADD_ADDRESS_BUTTON);
        }
        return addAddressButton;
    }

    public AddAddressPopupComponent clickOnAddAddressButton() {
        addAddressButton.click();
        return new AddAddressPopupComponent(driver);
    }

    public AddAddressPopupComponent clickOnCancelButton() {
        cancelButton.click();
        return new AddAddressPopupComponent(driver);
    }
}
