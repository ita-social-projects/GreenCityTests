package com.softserve.edu.greencity.ui.pages.ubs;

import com.softserve.edu.greencity.ui.elements.ButtonElement;
import com.softserve.edu.greencity.ui.elements.LabelElement;
import com.softserve.edu.greencity.ui.locators.ubs.AddressComponentLocators;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AddressComponent {
    private WebDriver webDriver;
    private ButtonElement selectAddressButton;
    private LabelElement cityName;
    private LabelElement streetName;
    private LabelElement districtName;
    private ButtonElement editAddressButton;
    private ButtonElement deleteAddressButton;

    public AddressComponent(WebDriver webDriver, WebElement root) {
        this.webDriver = webDriver;
        initElements(root);
    }

    private void initElements(WebElement root) {
        selectAddressButton = new ButtonElement(root, AddressComponentLocators.SELECT_ADDRESS);
        cityName = new LabelElement(root, AddressComponentLocators.CITY);
        streetName = new LabelElement(root, AddressComponentLocators.STREET);
        districtName = new LabelElement(root, AddressComponentLocators.DISTRICT);
        editAddressButton = new ButtonElement(root, AddressComponentLocators.EDIT);
        deleteAddressButton = new ButtonElement(root, AddressComponentLocators.DELETE);
    }

    public void clickOnSelectAddressButton() {  //return type?
        selectAddressButton.click();
    }

    public String getCityText() {
        return cityName.getText();
    }

    public String getStreetText() {
        return streetName.getText();
    }

    public String getDistinctText() {
        return districtName.getText();
    }

    public AddAddressPopupComponent clickOnEditAddressButton() {
        editAddressButton.click();
        return new AddAddressPopupComponent(webDriver);
    }

    public PersonalDataPage clickOnDeleteAddressButton() {
        deleteAddressButton.click();
        return new PersonalDataPage(webDriver);
    }
}
