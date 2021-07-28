package com.softserve.edu.greencity.ui.pages.ubs;

import com.softserve.edu.greencity.ui.elements.ButtonElement;
import com.softserve.edu.greencity.ui.elements.DropDownElement;
import com.softserve.edu.greencity.ui.elements.LabelElement;
import com.softserve.edu.greencity.ui.locators.ubs.PaymentPageLocators;
import com.softserve.edu.greencity.ui.pages.common.WelcomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class PaymentPage extends UBSCourierBasePage {

    private ButtonElement cancelButton;
    private ButtonElement orderButton;
    private ButtonElement backButton;

    //private LabelElement yourOrder;
    private LabelElement service;
    private LabelElement volume;
    private LabelElement cost;
    private LabelElement numberOfPackages;
    private LabelElement total;

    private LabelElement oldClothesCheap;
    private LabelElement oldClothesExpensive;
    private LabelElement recycledMaterials;

    private LabelElement cheapPricePackage;
    private LabelElement expensivePricePackage;
    private LabelElement recycledMaterialsPackage;
    private LabelElement cheapPriceTotalSum;
    private LabelElement expensivePriceTotalSum;
    private LabelElement recycledMaterialsTotalSum;


    private LabelElement orderAmount;
    private LabelElement certificate;
    private LabelElement amountDue;


    private LabelElement deliveryFromEcoStore;
    private LabelElement orderNumber;


    private LabelElement recipient;
    private LabelElement fullName;
    private LabelElement phone;
    private LabelElement gmail;


    private LabelElement addressOfExportOrderedService;
    private LabelElement town;
    private LabelElement street;
    private LabelElement district;
    private LabelElement commentToAddress;
    private LabelElement commentToOrder;


    private DropDownElement payment;


    public PaymentPage(WebDriver webDriver) {
        super(webDriver);
        initPaymentElements();
    }

    public void initPaymentElements() {
        service = new LabelElement(driver, PaymentPageLocators.SERVICE_LABEL);
      //  volume = new LabelElement(driver, PaymentPageLocators.VOLUME_LABEL);
        cost = new LabelElement(driver, PaymentPageLocators.COST_LABEL);
        numberOfPackages = new LabelElement(driver, PaymentPageLocators.NUMBER_OF_PACKAGES_LABEL);
         total = new LabelElement(driver, PaymentPageLocators.TOTAL_LABEL);
//        orderAmount = new LabelElement(driver, PaymentPageLocators.ORDER_AMOUNT_LABEL);
//        certificate = new LabelElement(driver, PaymentPageLocators.CERTIFICATE_LABEL);
//        amountDue = new LabelElement(driver, PaymentPageLocators.AMOUNT_DUE_LABEL);
//        deliveryFromEcoStore=new LabelElement(driver,PaymentPageLocators.DELIVERY_FROM_ECO_STORE_LABEL);
        recipient = new LabelElement(driver, PaymentPageLocators.RECIPIENT_LABEL);
        fullName = new LabelElement(driver, PaymentPageLocators.FULL_NAME_LABEL);
        phone = new LabelElement(driver, PaymentPageLocators.PHONE_LABEL);
        gmail = new LabelElement(driver, PaymentPageLocators.GMAIL_LABEL);
        addressOfExportOrderedService = new LabelElement(driver, PaymentPageLocators.ADDRESS_OF_EXPORT_ORDERED_SERVICE_LABEL);
        town = new LabelElement(driver, PaymentPageLocators.TOWN_LABEL);
        street = new LabelElement(driver, PaymentPageLocators.STREET_LABEL);
        district = new LabelElement(driver, PaymentPageLocators.DISTRICT_LABEL);

        //  payment = new DropDownElement(driver, PaymentPageLocators.PAYMENT_METHOD);


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

    public PaymentPage clickOnOrderButton() {
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

    public LabelElement getService() {
        return service;
    }

    public LabelElement getVolume() {
        return volume;
    }

    public LabelElement getCost() {
        return cost;
    }

    public LabelElement getNumberOfPackages() {
        return numberOfPackages;
    }

    public LabelElement getTotal() {
        return total;
    }


    public LabelElement getOldClothesCheap() {
        oldClothesCheap = new LabelElement(driver, PaymentPageLocators.OLD_CLOTHES_CHEAP_PRICE_LABEL);
        return oldClothesCheap;
    }

    public LabelElement getOldClothesExpensive() {
        oldClothesExpensive = new LabelElement(driver, PaymentPageLocators.OLD_CLOTHES_EXPENSIVE_PRICE_LABEL);
        return oldClothesExpensive;
    }

    public LabelElement getRecycledMaterials() {
        recycledMaterials = new LabelElement(driver, PaymentPageLocators.RECYCLED_MATERIALS_LABEL);
        return recycledMaterials;
    }

    public LabelElement getCheapPricePackage() {
        cheapPricePackage = new LabelElement(driver, PaymentPageLocators.OLD_CLOTHES_CHEAP_PRICE_NUMBER_PACKAGES_LABEL);
        return cheapPricePackage;
    }

    public LabelElement getExpensivePricePackage() {
        expensivePricePackage = new LabelElement(driver, PaymentPageLocators.OLD_CLOTHES_EXPENSIVE_PRICE_NUMBER_PACKAGES_LABEL);
        return expensivePricePackage;
    }

    public LabelElement getRecycledMaterialsPackage() {
        recycledMaterialsPackage = new LabelElement(driver, PaymentPageLocators.RECYCLED_MATERIALS_NUMBER_PACKAGES_LABEL);
        return recycledMaterialsPackage;
    }

    public LabelElement getCheapPriceTotalSum() {
        cheapPriceTotalSum = new LabelElement(driver, PaymentPageLocators.OLD_CLOTHES_CHEAP_PRICE_TOTAL_SUM_LABEL);
        return cheapPriceTotalSum;
    }

    public LabelElement getExpensivePriceTotalSum() {
        expensivePriceTotalSum = new LabelElement(driver, PaymentPageLocators.OLD_CLOTHES_EXPENSIVE_TOTAL_SUM_LABEL);
        return expensivePriceTotalSum;
    }

    public LabelElement getRecycledMaterialsTotalSum() {
        recycledMaterialsTotalSum = new LabelElement(driver, PaymentPageLocators.RECYCLED_MATERIALS_NUMBER_TOTAL_SUM_LABEL);
        return recycledMaterialsTotalSum;
    }

    public LabelElement getOrderAmount() {
        orderAmount = new LabelElement(driver, PaymentPageLocators.ORDER_AMOUNT_LABEL);
        return orderAmount;
    }

    public LabelElement getCertificate() {
        certificate = new LabelElement(driver, PaymentPageLocators.CERTIFICATE_LABEL);
        return certificate;
    }

    public LabelElement getAmountDue() {
        amountDue = new LabelElement(driver, PaymentPageLocators.AMOUNT_DUE_LABEL);
        return amountDue;
    }


    public LabelElement getOrderNumbers() {
        orderNumber = new LabelElement(driver, PaymentPageLocators.ORDER_NUMBERS_LABEL);
        return orderNumber;
    }

    public LabelElement getFullName() {
        return fullName;
    }

    public LabelElement getPhone() {
        return phone;
    }

    public LabelElement getGmail() {
        return gmail;
    }

    public LabelElement getTown() {
        return town;
    }

    public LabelElement getStreet() {
        return street;
    }

    public LabelElement getDistrict() {
        return district;
    }

    public LabelElement getCommentToAddress() {
        commentToAddress = new LabelElement(driver, PaymentPageLocators.COMMENT_TO_ADDRESS_LABEL);
        return commentToAddress;
    }

    public LabelElement getCommentToOrder() {
        commentToOrder = new LabelElement(driver, PaymentPageLocators.COMMENT_TO_ORDER_LABEL);
        return commentToOrder;
    }

    public DropDownElement getPaymentField() {
        payment = new DropDownElement(driver, PaymentPageLocators.PAYMENT_METHOD);
        return payment;
    }


    public String returnAllOrderNumbers() {
        String s = "";
        List<WebElement> orderPath = driver.findElements(PaymentPageLocators.ORDER_NUMBERS_LABEL.getPath());
        for (WebElement orders : orderPath) {
            s += orders.getText().trim();
        }
        return s;
    }

    public boolean isAllOrderNumbersDisplayed() {

        List<WebElement> orderPath = driver.findElements(PaymentPageLocators.ORDER_NUMBERS_LABEL.getPath());
        for (WebElement orders : orderPath) {
            if (orders.isDisplayed() == false) {
                return false;
            }
        }
        return true;

    }

    public String getTextFromOrderNumbers() {
        return orderNumber.getText();
    }

    public boolean isOrderNumbersDisplayed() {
        return getOrderNumbers().isDisplayedLabel();

    }


    public String getCommentOrderText() {
        return getCommentToOrder().getText();

    }

    public String getCommentAddressText() {
        return getCommentToAddress().getText();

    }


    public String getFullAddress() {
        String result = "";
        List<WebElement> addressData = driver.findElements(PaymentPageLocators.TOTAL_ADDRESS_OF_EXPORT_LABEL.getPath());
        for (WebElement addresItem : addressData) {
            result = result + " " + addresItem.getText();
        }
        return result.trim();


    }

    public boolean isSelectedServicesDisplayed() {
        return getOldClothesCheap().isDisplayedLabel()
                && getOldClothesExpensive().isDisplayedLabel()
                && getRecycledMaterials().isDisplayedLabel()
                && getCheapPricePackage().isDisplayedLabel()
                && getExpensivePricePackage().isDisplayedLabel()
                && getRecycledMaterialsPackage().isDisplayedLabel()
                && getCheapPriceTotalSum().isDisplayedLabel()
                && getExpensivePriceTotalSum().isDisplayedLabel()
                && getRecycledMaterialsPackage().isDisplayedLabel();

    }

}
