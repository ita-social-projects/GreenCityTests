package com.softserve.edu.greencity.ui.pages.ubs;

import com.softserve.edu.greencity.ui.elements.ButtonElement;
import com.softserve.edu.greencity.ui.elements.LabelElement;
import com.softserve.edu.greencity.ui.locators.ubs.OrderDetailsPageLocators;
import com.softserve.edu.greencity.ui.locators.ubs.PaymentPageLocators;
import com.softserve.edu.greencity.ui.pages.common.WelcomePage;

import org.openqa.selenium.WebDriver;

public class PaymentPage extends UBSCourierBasePage {

    private ButtonElement cancelButton;
    private ButtonElement orderButton;
    private ButtonElement backButton;

    private LabelElement yourOrder;
    private LabelElement service;
    private LabelElement volume;
    private LabelElement cost;
    private LabelElement numberOfPackages;
    private LabelElement total;

    private LabelElement oldClothesFirst;
    private LabelElement oldClothesSecond;
    private LabelElement recycledMaterials;

    private LabelElement orderAmount;
    private LabelElement certificate;
    private LabelElement amountDue;


    private LabelElement deliveryFromEcoStore;
    private LabelElement orderNumbers;


    private LabelElement recipient;
    private LabelElement fullName;
    private LabelElement phone;
    private LabelElement gmail;



    private LabelElement addressOfExportOrderedService;
    private LabelElement town;
    private LabelElement address;
    private LabelElement district;
    private LabelElement commentToAddress;
    private LabelElement commentToOrder;


    private LabelElement payment;



    public PaymentPage(WebDriver webDriver) {
        super(webDriver);
        initElements();
    }

    public void initElements() {
        yourOrder=new LabelElement(driver,PaymentPageLocators.YOUR_ORDER_LABEL);
        service=new LabelElement(driver,PaymentPageLocators.SERVICE_LABEL);
        volume= new LabelElement(driver,PaymentPageLocators.VOLUME_LABEL);
        cost=new LabelElement(driver,PaymentPageLocators.COST_LABEL);
        numberOfPackages=new LabelElement(driver,PaymentPageLocators.NUMBER_OF_PACKAGES_LABEL);
        total=new LabelElement(driver,PaymentPageLocators.TOTAL_LABEL);
        oldClothesFirst=new LabelElement(driver,PaymentPageLocators.OLD_CLOTHES_FIRST_LABEL);
        oldClothesSecond=new LabelElement(driver,PaymentPageLocators.OLD_CLOTHES_SECOND_LABEL);
        recycledMaterials=new LabelElement(driver,PaymentPageLocators.OLD_CLOTHES_SECOND_LABEL);
        orderAmount=new LabelElement(driver,PaymentPageLocators.ORDER_AMOUNT_LABEL);
        certificate=new LabelElement(driver,PaymentPageLocators.CERTIFICATE_LABEL);
        amountDue=new LabelElement(driver,PaymentPageLocators.AMOUNT_DUE_LABEL);
        deliveryFromEcoStore=new LabelElement(driver,PaymentPageLocators.DELIVERY_FROM_ECO_STORE_LABEL);
        orderNumbers=new LabelElement(driver,PaymentPageLocators.ORDER_NUMBERS_LABEL);
        recipient=new LabelElement(driver,PaymentPageLocators.RECIPIENT_LABEL);
        fullName=new LabelElement(driver,PaymentPageLocators.FULL_NAME_LABEL);
        phone=new LabelElement(driver,PaymentPageLocators.PHONE_LABEL);
        gmail=new LabelElement(driver,PaymentPageLocators.GMAIL_LABEL);
        addressOfExportOrderedService=new LabelElement(driver,PaymentPageLocators.ADDRESS_OF_EXPORT_ORDERED_SERVICE_LABEL);
        town=new LabelElement(driver,PaymentPageLocators.TOWN_LABEL);
        address=new LabelElement(driver,PaymentPageLocators.ADDRESS_LABEL);
        district=new LabelElement(driver,PaymentPageLocators.DISTRICT_LABEL);
        commentToAddress=new LabelElement(driver,PaymentPageLocators.COMMENT_TO_ADDRESS_LABEL);
        commentToOrder=new LabelElement(driver,PaymentPageLocators.COMMENT_TO_ORDER_LABEL);
        

    }

    private ButtonElement getOrderButton() { //doesn't work
        if (orderButton == null) {
            orderButton = new ButtonElement(driver, PaymentPageLocators.ORDER_BUTTON);
        }
        return orderButton;
    }

    private ButtonElement getCancelButton() {
        if (cancelButton == null) {
            cancelButton = new ButtonElement(driver, PaymentPageLocators.CANCEL_BUTTON);
        }
        return cancelButton;
    }

    private ButtonElement getBackButton() {
        if (backButton == null) {
            backButton = new ButtonElement(driver, PaymentPageLocators.BACK_BUTTON);
        }
        return backButton;
    }

    public PaymentPage clickOnOrderButton() {   //return type?
        getOrderButton().click();
        return this;
    }

    public CancelOrderPopupComponent clickOnCancelButton() {
        getCancelButton().click();
        return new CancelOrderPopupComponent(driver, this, new WelcomePage(driver));
    }

    public PersonalDataPage clickOnBackButton() {
        getBackButton().click();
        return new PersonalDataPage(driver);
    }
    public String getFullName(){
        return fullName.getText();
    }
    public String getPhone(){
        return phone.getText();
    }
    public String getGmail(){
        return gmail.getText();
    }
//    public void getData(){
//        if(!fullName.getText().isEmpty()&&!phone.getText().isEmpty()&&!gmail.getText().isEmpty());
//
//    }
}
