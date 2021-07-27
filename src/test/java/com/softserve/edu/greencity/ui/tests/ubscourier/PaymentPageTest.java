package com.softserve.edu.greencity.ui.tests.ubscourier;

import com.softserve.edu.greencity.data.UBS.Certificates;
import com.softserve.edu.greencity.data.UBS.UBSDataStrings;
import com.softserve.edu.greencity.data.users.User;
import com.softserve.edu.greencity.data.users.UserRepository;
import com.softserve.edu.greencity.ui.locators.ubs.AddAddressPopupLocators;
import com.softserve.edu.greencity.ui.pages.ubs.*;
import com.softserve.edu.greencity.ui.tests.runner.GreenCityTestRunnerWithLoginLogout;
import com.softserve.edu.greencity.ui.tests.runner.GreenCityTestRunnerWithoutLogin;
import io.qameta.allure.Description;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

public class PaymentPageTest extends GreenCityTestRunnerWithLoginLogout {

    private final String NAME = "Jack";
    private final String SURNAME = "London";
    private final String PHONE = "0634567890";
    private final String GMAIL = "Jkl@gmail.com";
    private OrderDetailsPage orderDetailsPage;
    private PersonalDataPage personalDataPage;
    private PaymentPage paymentPage;

    @BeforeMethod
    public void signIn() {
        User user = UserRepository.get().temporary();
        orderDetailsPage = loadApplication()
                .signIn()
                .getManualLoginComponent()
                .successfullyLogin(user)
                .navigateMenuUBSCourier();
    }


    @AfterMethod
    public void signOut() {

        paymentPage.signOut();
    }


    @Test(testName = "GC-2059", description = "GC-2059")
    @Description("GC-2059")
    public void orderNumberBlockDisplayedCorrectly() {
        logger.info("Verify that order number in 'Доставка з Еко магазину' block is displayed correctly");
        orderDetailsPage.fillAllFieldsForServices((new Random().nextInt(2) + 1))
                .getCertificateInput().sendKeys(Certificates.ACTIVE_1000.getCertificate());
        orderDetailsPage.getActivateCertificateButton().click();
        orderDetailsPage.getYesWaitingOrderButton().click();
        orderDetailsPage.getOrderNumberInput().sendKeys(UBSDataStrings.ORDER_NUMBER_ONE.getMessage());

        softAssert.assertTrue(orderDetailsPage.getNextButton().isActive(), "Button from 'Order Details Page' is not active");

        PersonalDataPage personalDataPage = orderDetailsPage.clickOnNextButton();
        personalDataPage.fullPersonalData(NAME, SURNAME, PHONE, GMAIL);
        if(personalDataPage.getQuantityOfAddresses()==0){
        AddAddressPopupComponent addAddressPopupComponent = personalDataPage.clickOnAddAddressButton();
        addAddressPopupComponent.fillAllFields(
                new UserAddress(AddAddressPopupLocators.CITY_KIEV, "Sadova", "Kiev", 1, "1", 2))
                .clickOnAddAddressButton();}

        softAssert.assertTrue(personalDataPage.getNextButton().isActive(), "Button from 'Personal Data Page' is not active");
        paymentPage = personalDataPage.clickOnNextButton();
        softAssert.assertTrue(paymentPage.isOrderNumbersDisplayed(), "Order number isn't displayed");
        softAssert.assertEquals(paymentPage.getTextFromOrderNumbers(), "1111111111", "Order number isn't displayed correctly"); //TODO bug with comma
        softAssert.assertAll();


    }

    @Test(testName = "GC-2060", description = "GC-2060")
    @Description("GC-2060")
    public void orderNumbersAreDisplayedCorrectly() {
        logger.info("Verify that order numbers are displayed correctly.");
        orderDetailsPage.fillAllFieldsForServices(new Random().nextInt(2) + 1)
                .getCertificateInput().sendKeys(Certificates.ACTIVE_1000.getCertificate());
        orderDetailsPage.getActivateCertificateButton().click();
        orderDetailsPage.getYesWaitingOrderButton().click();
        orderDetailsPage.inputOrderNumber(UBSDataStrings.ORDER_NUMBER_ONE.getMessage())
                .inputSecondOrderNumber(UBSDataStrings.ORDER_NUMBER_TWO.getMessage());

        softAssert.assertTrue(orderDetailsPage.getNextButton().isActive(), "Button from 'Order Details Page' is not active");

        PersonalDataPage personalDataPage = orderDetailsPage.clickOnNextButton();
        personalDataPage.fullPersonalData(NAME, SURNAME, PHONE, GMAIL);
        AddAddressPopupComponent addAddressPopupComponent = personalDataPage.clickOnAddAddressButton();
        addAddressPopupComponent.fillAllFields(
                new UserAddress(AddAddressPopupLocators.CITY_KIEV, "Sadova", "Kiev", 1, "1", 2))
                .clickOnAddAddressButton();
        softAssert.assertTrue(personalDataPage.getNextButton().isActive(), "Button from 'Personal Data Page' is not active");

        PaymentPage paymentPage = personalDataPage.clickOnNextButton();
        softAssert.assertTrue(paymentPage.isAllOrderNumbersDisplayed(), "Order numbers aren't displayed");
        softAssert.assertEquals(
                paymentPage.returnAllOrderNumbers(), "1111111111,2222222222", "Order numbers aren't displayed correctly");//TODO bug with comma
        softAssert.assertAll();


    }

    @Test(testName = "GC-2061", description = "GC-2061")
    @Description("GC-2061")
    public void allCorrectInformationAboutOrderDisplayedCorrectly() {
        logger.info("Verify that user sees all correct information about the order");
        orderDetailsPage.fillAllFieldsForServices(new Random().nextInt(2) + 1)
                .getCertificateInput().sendKeys(Certificates.ACTIVE_1000.getCertificate());
        orderDetailsPage.getActivateCertificateButton().click();
        orderDetailsPage.getYesWaitingOrderButton().click();
        orderDetailsPage.inputOrderNumber(UBSDataStrings.ORDER_NUMBER_ONE.getMessage())
                .inputComment(UBSDataStrings.ADDRES_COMMENT.getMessage());
        PersonalDataPage personalDataPage = orderDetailsPage.clickOnNextButton();
        personalDataPage.fullPersonalData(NAME, SURNAME, PHONE, GMAIL);
        AddAddressPopupComponent addAddressPopupComponent = personalDataPage.clickOnAddAddressButton();
        addAddressPopupComponent.fillAllFields(
                new UserAddress(AddAddressPopupLocators.CITY_KIEV, "Sadova", "Kiev", 2, "3", 4))
                .clickOnAddAddressButton();
        paymentPage = personalDataPage.clickOnNextButton();
        logger.info("Verify all services");//TODO with DB
        //softAssert.assertTrue(paymentPage.);


    }


    @Test(testName = "GC-2062", description = "GC-2062")
    @Description("GC-2062")
    public void personalDataDisplayedCorrectly() {
        logger.info("Verify that the personal data is displayed correctly");
        orderDetailsPage.fillAllFieldsForServices(new Random().nextInt(2) + 1)
                .getCertificateInput().sendKeys(Certificates.ACTIVE_100.getCertificate());
        orderDetailsPage.getActivateCertificateButton().click();
        orderDetailsPage.getYesWaitingOrderButton().click();
        orderDetailsPage.inputOrderNumber(UBSDataStrings.ORDER_NUMBER_ONE.getMessage())
                .inputComment(UBSDataStrings.ADDRES_COMMENT.getMessage());
        PersonalDataPage personalDataPage = orderDetailsPage.clickOnNextButton();
        personalDataPage.fullPersonalData(NAME, SURNAME, PHONE, GMAIL);
        AddAddressPopupComponent addAddressPopupComponent = personalDataPage.clickOnAddAddressButton();
        addAddressPopupComponent.fillAllFields(
                new UserAddress(AddAddressPopupLocators.CITY_KIEV, "Sadova", "Kiev", 2, "3", 4))
                .clickOnAddAddressButton();
        paymentPage = personalDataPage.clickOnNextButton();
        logger.info("Verify full name");
        softAssert.assertEquals(paymentPage.getFullName().getText(), "Jack London");
        logger.info("Verify phone number");
        softAssert.assertEquals(paymentPage.getPhone().getText(), "+380 634 56 78 90");//TODO BUG with phone
        logger.info("Verify gmail address");
        softAssert.assertEquals(paymentPage.getGmail().getText(), "Jkl@gmail.com");
        softAssert.assertAll();

    }

    @Test(testName = "GC-2063", description = "GC-2063")
    @Description("GC-2063")
    public void correctAddressAndCommentsAreDisplayed() {
        logger.info("Verify that the correct address and comments are displayed in 'Адреса вивезення замовлених послуг' block");
        orderDetailsPage.fillAllFieldsForServices(new Random().nextInt(2) + 1)
                .getCertificateInput().sendKeys(Certificates.ACTIVE_500.getCertificate());
        orderDetailsPage.getActivateCertificateButton().click();
        orderDetailsPage.getYesWaitingOrderButton().click();
        orderDetailsPage.inputOrderNumber(UBSDataStrings.ORDER_NUMBER_ONE.getMessage())
                .inputComment(UBSDataStrings.ORDER_COMMENT.getMessage());
        PersonalDataPage personalDataPage = orderDetailsPage.clickOnNextButton();
        personalDataPage.fullPersonalData(NAME, SURNAME, PHONE, GMAIL);
        AddAddressPopupComponent addAddressPopupComponent = personalDataPage.clickOnAddAddressButton();
        addAddressPopupComponent.fillAllFields(
                new UserAddress(AddAddressPopupLocators.CITY_KIEV, "Sadova", "Kiev", 2, "3", 4))
                .clickOnAddAddressButton();
        personalDataPage.inputComment(UBSDataStrings.ADDRES_COMMENT.getMessage());
        PaymentPage paymentPage = personalDataPage.clickOnNextButton();
        logger.info("Verify address");
        softAssert.assertEquals(paymentPage.getTown().getText(), "Kiev");
        //
        softAssert.assertEquals(paymentPage.getStreet().getText(), "Sadova");
        logger.info("Verify comments");
        softAssert.assertEquals(paymentPage.getCommentOrderText(),
                "Comment to the order: Над нами ментори кружили. Спостерігали кожен день, чи з головою ми дружили,чи не творили єрундєнь.");
        softAssert.assertEquals(paymentPage.getCommentAddressText(),
                "Comment to the address: Ремонтується дорога. Під'їзд до будинку доступний зі сторони будинку номер 15");
        softAssert.assertAll();


    }

    @Test(testName = "GC-2064", description = "GC-2064")
    @Description("GC-2064")
    public void previouslyEnteredDataIsSavedAndDisplayed() {
        logger.info("Verify that previously entered data is saved when navigating in steps in 'UBS кур'єр' tab");
        orderDetailsPage.inputCertificate(Certificates.ACTIVE_1000.getCertificate())
                .clickActivateButton()
                .clickYesWaitingForAnOrderButton()
                .fillOrderByPosition(0,"1111111111")
                .clickAddOrderButton()
                .fillOrderByPosition(1,"2222222222")
                .inputComment(UBSDataStrings.ORDER_COMMENT.getMessage());
        String dueAmount = orderDetailsPage.getTextAmountDue();
        String orderAmount = orderDetailsPage.getTextOrderAmount();
        String certificateAmount = orderDetailsPage.getCertificateAmount();

        PersonalDataPage personalDataPage = orderDetailsPage.clickOnNextButton()
                .fullPersonalData(UBSDataStrings.PERSONAL_DATA_NAME.getMessage(),UBSDataStrings.PERSONAL_DATA_SURNAME.getMessage(),
                        UBSDataStrings.PERSONAL_DATA_PHONE.getMessage(),UBSDataStrings.PERSONAL_DATA_EMAIL.getMessage())
                .deleteAllAddresses()
                .clickOnAddAddressButton()
                .fillAllFields(
                        new UserAddress(AddAddressPopupLocators.CITY_KIEV, "Sadova", "Kiev", 2, "3", 4))
                .clickOnAddAddressButton()
                .inputComment(UBSDataStrings.ADDRES_COMMENT.getMessage());

        PaymentPage paymentPage = personalDataPage.clickOnNextButton();
        checkPaymentPageAsserts(dueAmount, orderAmount, certificateAmount, paymentPage);
        personalDataPage = paymentPage.clickOnBackButton();
        checkPersonalDataAsserts(personalDataPage);
        orderDetailsPage = personalDataPage.clickOnBackButton();
        checkOrderDetailsAsserts(dueAmount, orderAmount, certificateAmount);
        personalDataPage = orderDetailsPage.clickOnNextButton();
        checkPersonalDataAsserts(personalDataPage);
        paymentPage = personalDataPage.clickOnNextButton();
        checkPaymentPageAsserts(dueAmount, orderAmount, certificateAmount, paymentPage);
        paymentPage.clickOnOrderDetailsButton();
        softAssert.assertAll();
    }

    @Test(testName = "GC-2065", description = "GC-2065")
    @Description("GC-2065")
    public void blockConfirmationIsNotDisplayed() {
        logger.info("Verify that 'Оплата' block is not displayed when the order amount equals 0 UAH.");
        orderDetailsPage.fillAllFieldsForServices(new Random().nextInt(1) + 1)
                .getCertificateInput().sendKeys(Certificates.ACTIVE_1000.getCertificate());
        orderDetailsPage.getActivateCertificateButton().click();
        orderDetailsPage.getYesWaitingOrderButton().click();
        orderDetailsPage.inputOrderNumber(UBSDataStrings.ORDER_NUMBER_ONE.getMessage())
                .inputComment(UBSDataStrings.ORDER_COMMENT.getMessage());
        PersonalDataPage personalDataPage = orderDetailsPage.clickOnNextButton();
        personalDataPage.fullPersonalData(NAME, SURNAME, PHONE, GMAIL);
        AddAddressPopupComponent addAddressPopupComponent = personalDataPage.clickOnAddAddressButton();
        addAddressPopupComponent.fillAllFields(
                new UserAddress(AddAddressPopupLocators.CITY_KIEV, "Sadova", "Kiev", 2, "3", 4))
                .clickOnAddAddressButton();
        personalDataPage.inputComment(UBSDataStrings.ADDRES_COMMENT.getMessage());
        PaymentPage paymentPage = personalDataPage.clickOnNextButton();
        softAssert.assertFalse(paymentPage.getPaymentField().isDisplayed(), "Field is Displayed");
        softAssert.assertAll();


    }

    @Test(testName = "GC-2066", description = "GC-2066")
    @Description("GC-2066")
    public void verifAllControls() {
        logger.info("This is a checklist for testing the last step UI in 'UBS кур'єр' tab");


    }

    private void checkPaymentPageAsserts(String dueAmount, String orderAmount, String certificateAmount, PaymentPage paymentPage) {
        softAssert.assertEquals(paymentPage.getFullName().getText(), UBSDataStrings.PERSONAL_DATA_NAME_SURNAME.getMessage());
        softAssert.assertEquals(paymentPage.getPhone().getText(), UBSDataStrings.PERSONAL_DATA_PHONE.getMessage());//TODO BUG with phone
        softAssert.assertEquals(paymentPage.getGmail().getText(), UBSDataStrings.PERSONAL_DATA_EMAIL.getMessage());
        softAssert.assertEquals(paymentPage.getTown().getText(), "Kiev");
        softAssert.assertEquals(paymentPage.getStreet().getText(), "Sadova");
        softAssert.assertEquals(paymentPage.getDistrict().getText(),"Kiev");
        softAssert.assertEquals(paymentPage.getCommentOrderText(),"Comment to the order: "+ UBSDataStrings.ORDER_COMMENT.getMessage());
        softAssert.assertEquals(paymentPage.getCommentAddressText(),"Comment to the address: "+UBSDataStrings.ADDRES_COMMENT.getMessage());
        softAssert.assertEquals(paymentPage.getAmountDue().getText(), dueAmount);
        softAssert.assertEquals(paymentPage.getOrderAmount().getText(), orderAmount);
        softAssert.assertEquals(paymentPage.getCertificate().getText(), certificateAmount);
    }

    private void checkOrderDetailsAsserts(String dueAmount, String orderAmount, String certificateAmount) {
        softAssert.assertEquals(orderDetailsPage.getAmountDue().getText(), dueAmount);
        softAssert.assertEquals(orderDetailsPage.getOrderAmount().getText(), orderAmount);
        softAssert.assertEquals(orderDetailsPage.getCertificateAmount(), certificateAmount);
        softAssert.assertEquals(orderDetailsPage.getCertificateInput().getValue(),Certificates.ACTIVE_1000.getCertificate());
        softAssert.assertEquals(orderDetailsPage.getCommentTextarea().getText(),UBSDataStrings.ORDER_COMMENT.getMessage());
        softAssert.assertEquals(orderDetailsPage.getOrderValueByPosition(0),"1111111111");
        softAssert.assertEquals(orderDetailsPage.getOrderValueByPosition(1),"2222222222");
    }

    private void checkPersonalDataAsserts(PersonalDataPage personalDataPage) {
        softAssert.assertEquals(personalDataPage.getNameInput().getValue(), UBSDataStrings.PERSONAL_DATA_NAME.getMessage());
        softAssert.assertEquals(personalDataPage.getLastNameInput().getValue(),UBSDataStrings.PERSONAL_DATA_SURNAME.getMessage());
        softAssert.assertEquals(personalDataPage.getPhoneInput().getValue(),UBSDataStrings.PERSONAL_DATA_PHONE.getMessage());
        softAssert.assertEquals(personalDataPage.getEmailInput().getValue(),UBSDataStrings.PERSONAL_DATA_EMAIL.getMessage());
        softAssert.assertEquals(personalDataPage.getCommentInput().getText(),UBSDataStrings.ADDRES_COMMENT.getMessage());
        softAssert.assertEquals(personalDataPage.getCityNameOfAddressByIndex(0),"Kiev");
        softAssert.assertEquals(personalDataPage.getStreetNameOfAddressByIndex(0),"Sadova");
        softAssert.assertEquals(personalDataPage.getDistinctOfAddressByIndex(0),"Kiev");
    }

}
