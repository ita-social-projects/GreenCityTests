package com.softserve.edu.greencity.ui.pages.ubs;

import com.softserve.edu.greencity.ui.elements.ButtonElement;
import com.softserve.edu.greencity.ui.elements.InputElement;
import com.softserve.edu.greencity.ui.elements.LabelElement;
import com.softserve.edu.greencity.ui.locators.ubs.AddAddressPopupLocators;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddAddressPopupComponent extends UBSCourierBasePage {
    private WebDriverWait wait;

    private ButtonElement cancelButton;
    private ButtonElement addAddressButton;

    private WebElement cityInput;

    private InputElement districtInput;
    private InputElement streetInput;
    private InputElement houseInput;
    private InputElement corpInput;
    private InputElement entranceInput;

    private LabelElement cityLabel;
    private LabelElement streetAlertMessage;
    private LabelElement districtAlertMessage;
    private LabelElement houseAlertMessage;

    public AddAddressPopupComponent(WebDriver webDriver) {
        super(webDriver);
        initElements();
    }

    public void initElements() {
        cancelButton = new ButtonElement(driver, AddAddressPopupLocators.CANCEL_BUTTON);
        addAddressButton = new ButtonElement(driver, AddAddressPopupLocators.ADD_ADDRESS_BUTTON);
        //cityInput = new DropDownElement(driver, AddAddressPopupLocators.CITY_INPUT);
        houseInput = new InputElement(driver, AddAddressPopupLocators.HOUSE_INPUT);
        corpInput = new InputElement(driver, AddAddressPopupLocators.CORP_INPUT);
        entranceInput = new InputElement(driver, AddAddressPopupLocators.ENTRANCE_INPUT);
        cityLabel = new LabelElement(driver, AddAddressPopupLocators.CITY_LABEL);
        cancelButton = new ButtonElement(driver, AddAddressPopupLocators.CANCEL_BUTTON);
    }

    //TODO write input data
    public AddAddressPopupComponent fillStreet(UserAddress userAddress) {
        getStreetInput().sendKeys(userAddress.getStreet());
        getDistrictInput().click();
        return this;
    }

    public AddAddressPopupComponent fillDistrict(UserAddress userAddress) {
        getDistrictInput().sendKeys(userAddress.getDistrict());

        return this;
    }

    public void fillFields(UserAddress user) {

    }

    //TODO check error message method
    //error message
    public LabelElement getStreetAlertMessage() {
        if (streetAlertMessage == null) {
            streetAlertMessage = new LabelElement(driver, AddAddressPopupLocators.STREET_ALERT_MESSAGE);
        }
        return streetAlertMessage;
    }

    public LabelElement getDistrictAlertMessage() {
        if (districtAlertMessage == null) {
            districtAlertMessage = new LabelElement(driver, AddAddressPopupLocators.DISTRICT_ALERT_MESSAGE);
        }
        return districtAlertMessage;
    }

    public LabelElement getHouseAlertMessage() {
        if (houseAlertMessage == null) {
            houseAlertMessage = new LabelElement(driver, AddAddressPopupLocators.HOUSE_ALERT_MESSAGE);
        }
        return houseAlertMessage;
    }


    public boolean isDisplayedStreetErrorMessage() {
        return getStreetAlertMessage().isDisplayedLabel();
    }

    public String getStreetValidationErrorText() {
        return getStreetAlertMessage().getText();
    }

    public boolean isDisplayedDistrictErrorMessage() {
        return getDistrictAlertMessage().isDisplayedLabel();
    }

    public boolean isDisplayedHouseErrorMessage() {
        return getHouseAlertMessage().isDisplayedLabel();
    }


    public WebElement getCityInput() {
        if (cityInput == null) {
            cityInput = driver.findElement(AddAddressPopupLocators.CITY_INPUT.getPath());
        }
        return cityInput;
    }

    public InputElement getStreetInput() {
        if (streetInput == null) {
            streetInput = new InputElement(driver, AddAddressPopupLocators.STREET_INPUT);
        }
        return streetInput;
    }

    public InputElement getDistrictInput() {
        if (districtInput == null) {
            districtInput = new InputElement(driver, AddAddressPopupLocators.DISTRICT_INPUT);
        }
        return districtInput;
    }

    public ButtonElement getAddButton() {
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
