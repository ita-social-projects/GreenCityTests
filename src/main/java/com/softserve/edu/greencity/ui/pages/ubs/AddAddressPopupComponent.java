package com.softserve.edu.greencity.ui.pages.ubs;

import com.softserve.edu.greencity.ui.elements.ButtonElement;
import com.softserve.edu.greencity.ui.elements.InputElement;
import com.softserve.edu.greencity.ui.elements.LabelElement;
import com.softserve.edu.greencity.ui.locators.ubs.AddAddressPopupLocators;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
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
    private LabelElement cityAlertMessage;
    private LabelElement districtAlertMessage;

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
    //TODO write crear
    public WebElement getStreetInputt() {
        wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(getStreetInput));
        return driver.findElement(getStreetInput);
    }

    public AddAddressPopupComponent fillStreet(Street street) {
        getStreetInput().sendKeys();
        return this;
    }


    //TODO check this method
    public LabelElement getCityAlertMessage() {
        if (cityAlertMessage == null) {
            cityAlertMessage = new LabelElement(driver, AddAddressPopupLocators.CITY_ALERT_MESSAGE);
        }
        return cityAlertMessage;
    }

    public LabelElement getDistrictAlertMessage() {
        if (cityAlertMessage == null) {
            cityAlertMessage = new LabelElement(driver, AddAddressPopupLocators.DISTRICT_ALERT_MESSAGE);
        }
        return cityAlertMessage;
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
