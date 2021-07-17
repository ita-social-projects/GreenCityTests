package com.softserve.edu.greencity.ui.pages.ubs;

import com.softserve.edu.greencity.ui.elements.ButtonElement;
import com.softserve.edu.greencity.ui.elements.InputElement;
import com.softserve.edu.greencity.ui.elements.LabelElement;
import com.softserve.edu.greencity.ui.elements.TextAreaElement;
import com.softserve.edu.greencity.ui.locators.ubs.AddressComponentLocators;
import com.softserve.edu.greencity.ui.locators.ubs.OrderDetailsPageLocators;
import com.softserve.edu.greencity.ui.locators.ubs.PersonalDataPageLocators;
import com.softserve.edu.greencity.ui.pages.common.WelcomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

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
        initPersonalDataElements();
    }

    public void initPersonalDataElements() {
//        nameField = new InputElement(driver, PersonalDataPageLocators.NAME_FIELD);
//        surnameField = new InputElement(driver, PersonalDataPageLocators.SURNAME_FIELD);
//        phoneField = new InputElement(driver, PersonalDataPageLocators.PHONE_FIELD);
//        emailField = new InputElement(driver, PersonalDataPageLocators.EMAIL_FIELD);
//        commentToAddressField = new TextAreaElement(driver, PersonalDataPageLocators.COMMENT_ADDRESS_FIELD);
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
            nextButton = new ButtonElement(driver, PersonalDataPageLocators.NEXT);
        }
        return nextButton;
    }

    private ButtonElement getCancelButton() {
        if (cancelButton == null) {
            cancelButton = new ButtonElement(driver, PersonalDataPageLocators.CANCEL);
        }
        return cancelButton;
    }

    private ButtonElement getBackButton() {
        if (backButton == null) {
            backButton = new ButtonElement(driver, PersonalDataPageLocators.BACK);
        }
        return backButton;
    }

    public PaymentPage clickOnNextButton() {   //return type?
        getNextButton().click();
        return new PaymentPage(driver);
    }

    public CancelOrderPopupComponent clickOnCancelButton() {
        getCancelButton().click();
        return new CancelOrderPopupComponent(driver, this, new WelcomePage(driver));
    }

    public OrderDetailsPage clickOnBackButton() {
        getBackButton().click();
        return new OrderDetailsPage(driver);
    }

    private List<AddressComponent> getListOfAddresses() {
        waitsSwitcher.setExplicitWait(2, ExpectedConditions.elementToBeClickable(AddressComponentLocators.DELETE.getPath())); //??
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
        String currentCity = null;
        try {
            currentCity = getListOfAddresses().get(i).getCityText();
        } catch (IndexOutOfBoundsException e) {
            logger.error("The is no element with index #" + i);
        }
        return currentCity;
    }

    public String getStreetNameOfAddressByIndex(int i) {
        String currentStreet = null;
        try {
            currentStreet = getListOfAddresses().get(i).getStreetText();
        } catch (IndexOutOfBoundsException e) {
            logger.error("The is no element with index #" + i);
        }
        return currentStreet;
    }

    public String getDistinctOfAddressByIndex(int i) {
        String currentDistinct = null;
        try {
            currentDistinct = getListOfAddresses().get(i).getDistinctText();
        } catch (IndexOutOfBoundsException e) {
            logger.error("The is no element with index #" + i);
        }
        return currentDistinct;
    }

    public AddAddressPopupComponent editAddressOfIndex(int i) {
        AddAddressPopupComponent editAddress = null;
        try {
            editAddress = getListOfAddresses().get(i).clickOnEditAddressButton();
        } catch (IndexOutOfBoundsException e) {
            logger.error("The is no element with index #" + i);
        }
        return editAddress;
    }

    public PersonalDataPage deleteAddressOfIndex(int i) {
        try {
            return getListOfAddresses().get(i).clickOnDeleteAddressButton();
        } catch (IndexOutOfBoundsException e) {
            logger.error("The is no element with index #" + i);
        }
        return this;
    }

    public ButtonElement getAddAddressButton() {
        if (addAddressButton == null) {
            addAddressButton = new ButtonElement(driver, PersonalDataPageLocators.ADD_ADDRESS);
        }
        return addAddressButton;
    }

    public AddAddressPopupComponent clickOnAddAddressButton() {
        addAddressButton.click();
        return new AddAddressPopupComponent(driver);
    }

    public boolean isAddAddressButtonActive() {
        return getAddAddressButton().isActive();
    }

    public PersonalDataPage scrollToAddress() {
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(PersonalDataPageLocators.ADD_ADDRESS.getPath())).perform();
        return this;
    }

}
