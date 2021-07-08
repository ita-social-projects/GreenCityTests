package com.softserve.edu.greencity.ui.pages.ubs;

import com.softserve.edu.greencity.ui.elements.ButtonElement;
import com.softserve.edu.greencity.ui.elements.LabelElement;
import com.softserve.edu.greencity.ui.locators.ubs.OrderDetailsPageLocators;
import com.softserve.edu.greencity.ui.locators.ubs.PersonalDataPageLocators;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.LinkedList;
import java.util.List;

public class PersonalDataPage extends UBSCourierBasePage {

    private ButtonElement cancelButton;
    private ButtonElement nextButton;
    private ButtonElement backButton;
    private List<AddressComponent> listOfAddresses;
    private ButtonElement addAddressButton;

    public PersonalDataPage(WebDriver webDriver) {
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

    private ButtonElement getBackButton() {
        if (backButton == null) {
            backButton = new ButtonElement(driver, OrderDetailsPageLocators.CANCEL);
        }
        return backButton;
    }

    public PaymentPage clickOnNextButton() {   //return type?
        getNextButton().click();
        return new PaymentPage(driver);
    }

    public CancelOrderPopupComponent clickOnCancelButton() {
        getCancelButton().click();
        return new CancelOrderPopupComponent(driver, this);
    }

    public OrderDetailsPage clickOnBackButton() {
        getBackButton().click();
        return new OrderDetailsPage(driver);
    }

    private List<AddressComponent> getListOfAddresses() {
        if (listOfAddresses == null) {
            listOfAddresses = new LinkedList<>();
            List<WebElement> addressesPath = driver.findElements(PersonalDataPageLocators.LIST_OF_ADDRESSES.getPath());
            if (addressesPath.size() != 0) {
                for (WebElement addressPath : addressesPath) {
                    listOfAddresses.add(new AddressComponent(driver, addressPath));
                }
            }
        }
        return listOfAddresses;
    }

    public PersonalDataPage selectAddressByIndex(int i) {
        getListOfAddresses().get(i).clickOnSelectAddressButton();
        return this;
    }

    public int getQuantityOfAddresses() {
        return getListOfAddresses().size();
    }

    public String getCityNameOfAddressByIndex(int i) {
        return getListOfAddresses().get(i).getCityText();
    }

    public String getStreetNameOfAddressByIndex(int i) {
        return getListOfAddresses().get(i).getStreetText();
    }

    public String getDistinctOfAddressByIndex(int i) {
        return getListOfAddresses().get(i).getDistinctText();
    }

    public AddAddressPopupComponent editAddressOfIndex(int i) {
        return getListOfAddresses().get(i).clickOnEditAddressButton();
    }

    public PersonalDataPage deleteAddressOfIndex(int i) {
        return getListOfAddresses().get(i).clickOnDeleteAddressButton();
    }

}
