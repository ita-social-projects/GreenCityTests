package com.softserve.edu.greencity.ui.pages.ubs;

import com.softserve.edu.greencity.ui.elements.ButtonElement;
import com.softserve.edu.greencity.ui.elements.DropDownElement;
import com.softserve.edu.greencity.ui.elements.InputElement;
import com.softserve.edu.greencity.ui.elements.LabelElement;
import com.softserve.edu.greencity.ui.locators.ubs.AddAddressPopupLocators;
import com.softserve.edu.greencity.ui.tools.engine.WaitsSwitcher;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
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
    private LabelElement cityMessageInfo;
    private LabelElement streetAlertMessage;
    private LabelElement districtAlertMessage;
    private LabelElement houseAlertMessage;

    private WaitsSwitcher waitsSwitcher;

    public AddAddressPopupComponent(WebDriver driver) {
        this.driver = driver;
        initElements();
    }

    public void initElements() {
        waitsSwitcher = new WaitsSwitcher(driver);
        cancelButton = new ButtonElement(driver, AddAddressPopupLocators.CANCEL_BUTTON);
        addAddressButton = new ButtonElement(driver, AddAddressPopupLocators.ADD_ADDRESS_BUTTON);
        cityInput = new DropDownElement(driver, AddAddressPopupLocators.CITY_INPUT);
        houseInput = new InputElement(driver, AddAddressPopupLocators.HOUSE_INPUT);
        corpInput = new InputElement(driver, AddAddressPopupLocators.CORP_INPUT);
        entranceInput = new InputElement(driver, AddAddressPopupLocators.ENTRANCE_INPUT);
        cityLabel = new LabelElement(driver, AddAddressPopupLocators.CITY_LABEL);
        cancelButton = new ButtonElement(driver, AddAddressPopupLocators.CANCEL_BUTTON);
    }

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

    //TODO method
//    public String getStreetHouseCorp(String street,int house,String corp){
//
//      //return  streetInput.getText().concat(String.valueOf(houseInput.getText());
//
//
//    }
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

    public LabelElement getCityMessageInfo() {
        if (cityMessageInfo == null) {
            cityMessageInfo = new LabelElement(driver, AddAddressPopupLocators.CITY_MESSAGE_INFO);
        }
        return cityMessageInfo;
    }

    public LabelElement getHouseAlertMessage() {
        if (houseAlertMessage == null) {
            houseAlertMessage = new LabelElement(driver, AddAddressPopupLocators.HOUSE_ALERT_MESSAGE);
        }
        return houseAlertMessage;
    }

    public String getStreetValidationErrorText() {
        return getStreetAlertMessage().getText();
    }

    public String getDistrictValidationErrorText() {
        return getDistrictAlertMessage().getText();
    }

    public String getHouseValidationErrorText() {
        return getHouseAlertMessage().getText();
    }

    public String getCityValidationTextInfo() {
        return getCityMessageInfo().getText();
    }

    public boolean isDisplayedCityMessageInfo() {
        return getCityMessageInfo().isDisplayedLabel();
    }

    public boolean isDisplayedStreetErrorMessage() {
        return getStreetAlertMessage().isDisplayedLabel();
    }

    public boolean isDisplayedDistrictErrorMessage() {
        return getDistrictAlertMessage().isDisplayedLabel();
    }

    public boolean isDisplayedHouseErrorMessage() {
        return getHouseAlertMessage().isDisplayedLabel();
    }

    public AddAddressPopupComponent chooseCity(AddAddressPopupLocators city) {
        cityInput = new DropDownElement(driver, AddAddressPopupLocators.CITY_INPUT);
        cityInput.click();
        cityInput.choseFromOptions(city.getPath());
        getStreetInput();
        return this;
    }

    public AddAddressPopupComponent clickOnStreetInput() {
        streetInput.click();
        return this;
    }

    public AddAddressPopupComponent clickOnDistrictInput() {
        districtInput.click();
        return this;
    }

    public AddAddressPopupComponent clickOnHouseInput() {
        houseInput.click();
        return this;
    }

    public AddAddressPopupComponent clickOnCorpInput() {
        corpInput.click();
        return this;
    }

    public AddAddressPopupComponent clickOnEntranceInput() {
        entranceInput.click();
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

    public InputElement getHouseInput() {
        return houseInput;
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
        waitsSwitcher.setExplicitWait(5, ExpectedConditions.invisibilityOfElementLocated(AddAddressPopupLocators.ADD_ADDRESS_BUTTON.getPath()));
//        waitsSwitcher.setExplicitWait(3,ExpectedConditions.numberOfElementsToBe(PersonalDataPageLocators.LIST_OF_ADDRESSES.getPath(), new PersonalDataPage(driver).getQuantityOfAddresses() + 1));
        return new PersonalDataPage(driver);
    }

    public boolean isAddAddressButtonActive() {
        return getAddButton().isActive();
    }

    public PersonalDataPage clickOnCancelButton() {
        getCancelButton().click();
        return new PersonalDataPage(driver);
    }

}
