package com.softserve.edu.greencity.ui.pages.ubs;

import com.softserve.edu.greencity.ui.elements.ButtonElement;
import com.softserve.edu.greencity.ui.elements.InputElement;
import com.softserve.edu.greencity.ui.elements.LabelElement;
import com.softserve.edu.greencity.ui.elements.TextAreaElement;
import com.softserve.edu.greencity.ui.locators.ubs.OrderDetailsPageLocators;
import com.softserve.edu.greencity.ui.locators.ubs.PersonalDataPageLocators;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.LinkedList;
import java.util.List;

public class PersonalDataPage extends UBSCourierBasePage {

    private InputElement nameField;
    private InputElement surnameField;
    private InputElement phoneField;
    private InputElement emailField;
    private TextAreaElement commentToAddressField;
    private ButtonElement cancelButton;
    private ButtonElement nextButton;
    private ButtonElement backButton;
    private List<AddressComponent> listOfAddresses;
    private ButtonElement addAddressButton;

    public PersonalDataPage(WebDriver webDriver) {
        super(webDriver);
        initElements();
    }

    public void initElements() {
        nameField = new InputElement(driver, PersonalDataPageLocators.NAME_FIELD);
        surnameField = new InputElement(driver, PersonalDataPageLocators.SURNAME_FIELD);
        phoneField = new InputElement(driver, PersonalDataPageLocators.PHONE_FIELD);
        emailField = new InputElement(driver, PersonalDataPageLocators.EMAIL_FIELD);
        commentToAddressField = new TextAreaElement(driver, PersonalDataPageLocators.COMMENT_ADDRESS_FIELD);
        cancelButton = new ButtonElement(driver, PersonalDataPageLocators.CANCEL);
        nextButton = new ButtonElement(driver, PersonalDataPageLocators.NEXT);
        backButton = new ButtonElement(driver, PersonalDataPageLocators.BACK);
        addAddressButton = new ButtonElement(driver, PersonalDataPageLocators.ADD_ADDRESS);
    }

    private PersonalDataPage inputName(String name) {
        nameField.clearInput();
        nameField.sendKeys(name);
        return this;
    }

    private PersonalDataPage inputSurname(String surname) {
        surnameField.clearInput();
        surnameField.sendKeys(surname);
        return this;
    }

    private PersonalDataPage inputPhone(String phone) {
        phoneField.clearInput();
        phoneField.sendKeys(phone);
        return this;
    }

    private PersonalDataPage inputEmail(String email) {
        emailField.clearInput();
        emailField.sendKeys(email);
        return this;
    }

    private PersonalDataPage inputComment(String comment) {
        commentToAddressField.clearText();
        commentToAddressField.enterText(comment);
        return this;
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
