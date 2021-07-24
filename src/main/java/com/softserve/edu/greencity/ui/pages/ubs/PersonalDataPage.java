package com.softserve.edu.greencity.ui.pages.ubs;

import com.softserve.edu.greencity.ui.elements.*;
import com.softserve.edu.greencity.ui.locators.ubs.AddressComponentLocators;
import com.softserve.edu.greencity.ui.locators.ubs.PersonalDataPageLocators;
import com.softserve.edu.greencity.ui.locators.ubs.UBSCourierBasePageLocators;
import com.softserve.edu.greencity.ui.pages.common.WelcomePage;
import org.openqa.selenium.Keys;
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
    private LabelElement absenceAddresses;
    private List<AddressComponent> listOfAddresses;
    private ButtonElement addAddressButton;
    private IconElement orderDetailsIconDone;
    private LabelElement errorNameMessage;
    private LabelElement errorSurnameMessage;
    private LabelElement errorPhoneMessage;
    private LabelElement errorEmailMessage;

    public PersonalDataPage(WebDriver webDriver) {
        super(webDriver);
        initPersonalDataElements();
    }

    public void initPersonalDataElements() {
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

    public PersonalDataPage inputName(String name) {
        nameField.clearInput();
        nameField.sendKeys(name);
        return this;
    }
    public InputElement getNameInput(){
        return nameField;
    }

    public PersonalDataPage inputSurname(String surname) {
        surnameField.clearInput();
        surnameField.sendKeys(surname);
        return this;
    }
    public InputElement getLastNameInput(){
        return surnameField;
    }

    public PersonalDataPage inputPhone(String phone) {
        phoneField.clearInput();
        phoneField.sendKeys(phone);
        return this;
    }
    public InputElement getPhoneInput(){
        return phoneField;
    }
    public PersonalDataPage inputEmail(String email) {
        emailField.clearInput();
        emailField.sendKeys(email);
        return this;
    }
    public InputElement getEmailInput(){
        return emailField;
    }
    public PersonalDataPage clearPersonalDataFields() {
        nameField.clearInput();
        surnameField.clearInput();
        phoneField.clearInput();
        emailField.clearInput();
        return this;
    }

    public String getFullName(){
        String fullName=nameField.getValue()+" "+surnameField.getValue();
        return fullName;

    }
    public String getPhoneNumber(){
        return phoneField.getValue();
    }

    public String getEmailAddress(){
        return emailField.getValue();
    }

    public String getErrorNameMessage() {
        errorNameMessage = new LabelElement(driver, PersonalDataPageLocators.ERROR_MESSAGE_FOR_NAME);
        return errorNameMessage.getText();
    }

    public String getErrorSurnameMessage() {
        errorSurnameMessage = new LabelElement(driver, PersonalDataPageLocators.ERROR_MESSAGE_FOR_SURNAME);
        return errorSurnameMessage.getText();
    }

    public String getErrorPhoneMessage() {
        errorPhoneMessage = new LabelElement(driver, PersonalDataPageLocators.ERROR_MESSAGE_FOR_PHONE);
        return errorPhoneMessage.getText();
    }

    public String getErrorEmailMessage() {
        errorEmailMessage = new LabelElement(driver, PersonalDataPageLocators.ERROR_MESSAGE_FOR_EMAIL);
        return errorEmailMessage.getText();
    }

    public PersonalDataPage fullPersonalData(String name,String surname,String phone,String gmail){
        logger.info("" +
                " full fields for personal data");
        return inputName(name)
                .inputSurname(surname)
                .inputPhone(phone)
                .inputEmail(gmail);
    }


    public PersonalDataPage inputComment(String comment) {
        commentToAddressField.clearText();
        commentToAddressField.enterText(comment);
        return this;
    }
    public TextAreaElement getCommentInput(){
        return commentToAddressField;
    }

    public ButtonElement getNextButton() {
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

    public PaymentPage clickOnNextButton() {
        waitsSwitcher.sleep(5000);
        //waitsSwitcher.setExplicitWait(3, ExpectedConditions.invisibilityOfElementLocated(PersonalDataPageLocators.NEXT.getPath()));
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

    private LabelElement getAbsenceAddresses() {
        if (absenceAddresses == null) {
            absenceAddresses = new LabelElement(driver, PersonalDataPageLocators.ABSENCE_OF_ADDRESSES);
        }
        return absenceAddresses;
    }

    private List<AddressComponent> getListOfAddresses() {
        waitsSwitcher.sleep(2000);
        if (listOfAddresses == null) {
            listOfAddresses = new LinkedList<>();
            List<WebElement> addressesPath = driver.findElements(PersonalDataPageLocators.LIST_OF_ADDRESSES.getPath());
            if (!addressesPath.get(0).getAttribute("class").contains("no-addresses")) {
                waitsSwitcher.setExplicitWait(2, ExpectedConditions.elementToBeClickable(AddressComponentLocators.DELETE.getPath())); //??
                for (WebElement addressPath : addressesPath) {
                    listOfAddresses.add(new AddressComponent(driver, addressPath));
                }
            }
        }
        return listOfAddresses;
    }

    public String getAbsenceAddressesText() {
        return getAbsenceAddresses().getText();
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
    public PersonalDataPage deleteAllAddresses(){
        for ( AddressComponent addressComponent:getListOfAddresses()) {
            addressComponent.clickOnDeleteAddressButton();
        }
        return new PersonalDataPage(driver);
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

    public IconElement getOrderDetailsIconDone() {
        if (orderDetailsIconDone == null) {
            orderDetailsIconDone = new IconElement(driver, UBSCourierBasePageLocators.ORDER_DETAILS_ICON_DONE);
        }
        return orderDetailsIconDone;
    }
}
