package com.softserve.edu.greencity.ui.pages.ubs;

import com.softserve.edu.greencity.ui.elements.ButtonElement;
import com.softserve.edu.greencity.ui.elements.DropDownElement;
import com.softserve.edu.greencity.ui.elements.InputElement;
import com.softserve.edu.greencity.ui.elements.LabelElement;
import com.softserve.edu.greencity.ui.locators.ubs.AddAddressPopupLocators;
import com.softserve.edu.greencity.ui.pages.common.WelcomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddAddressPopupComponent {
    private WebDriver driver;
    private WebDriverWait wait;

    private ButtonElement cancelButton;
    private ButtonElement addAddressButton;

    private DropDownElement cityInput;

    private InputElement districtInput;
    private InputElement streetInput;
    private InputElement houseInput;
    private InputElement corpInput;
    private InputElement entranceInput;

    private LabelElement cityLabel;
    private LabelElement streetAlertMessage;
    private LabelElement districtAlertMessage;
    private LabelElement houseAlertMessage;


    public AddAddressPopupComponent(WebDriver driver) {
        this.driver = driver;
        initElements();
    }

    public void initElements() {
        cancelButton = new ButtonElement(driver, AddAddressPopupLocators.CANCEL_BUTTON);
        addAddressButton = new ButtonElement(driver, AddAddressPopupLocators.ADD_ADDRESS_BUTTON);
        cityInput = new DropDownElement(driver, AddAddressPopupLocators.CITY_INPUT);
        houseInput = new InputElement(driver, AddAddressPopupLocators.HOUSE_INPUT);
        corpInput = new InputElement(driver, AddAddressPopupLocators.CORP_INPUT);
        entranceInput = new InputElement(driver, AddAddressPopupLocators.ENTRANCE_INPUT);
        cityLabel = new LabelElement(driver, AddAddressPopupLocators.CITY_LABEL);
        cancelButton = new ButtonElement(driver, AddAddressPopupLocators.CANCEL_BUTTON);
    }

    //input data
    public AddAddressPopupComponent inputStreet(UserAddress userAddress) {
        getStreetInput().sendKeys(userAddress.getStreet());
        return this;
    }

    public AddAddressPopupComponent inputDistrict(UserAddress userAddress) {
        getDistrictInput().click();
        getDistrictInput().sendKeys(userAddress.getDistrict());
        return this;
    }

    public AddAddressPopupComponent inputHouse(UserAddress userAddress) {
        houseInput.click();
        houseInput.sendKeys(String.valueOf(userAddress.getHouse()));
        return this;
    }

    /////////
    public AddAddressPopupComponent inputHouse(String name) {
        houseInput.click();
        houseInput.sendKeys(name);
        return this;
    }
    ////////

    public AddAddressPopupComponent inputCorp(UserAddress userAddress) {
        corpInput.click();
        corpInput.sendKeys(userAddress.getCorp());
        return this;
    }

    public AddAddressPopupComponent inputEntrance(UserAddress userAddress) {
        entranceInput.click();
        entranceInput.sendKeys(String.valueOf(userAddress.getEntrance()));
        return this;
    }

    public AddAddressPopupComponent fillAllFields(UserAddress data) {
        return chooseCity(data.getCity())
                .inputStreet(data)
                .inputDistrict(data)
                .inputHouse(data)
                .inputCorp(data)
                .inputEntrance(data);
    }

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

    public AddAddressPopupComponent chooseCity(AddAddressPopupLocators city){
        cityInput = new DropDownElement(driver, AddAddressPopupLocators.CITY_INPUT);
        cityInput.click();
        cityInput.choseFromOptions(city.getPath());
        getStreetInput();
        return this;
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

    public ButtonElement getCancelButton() {
        if (cancelButton == null) {
            cancelButton = new ButtonElement(driver, AddAddressPopupLocators.CANCEL_BUTTON);
        }
        return cancelButton;
    }

    public PersonalDataPage clickOnAddAddressButton() {
        getAddButton().click();
        return new PersonalDataPage(driver);
    }

    public WelcomePage clickOnCancelButton() {
        getCancelButton().click();
        return new WelcomePage(driver);
    }
}
