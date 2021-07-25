package com.softserve.edu.greencity.ui.tests.ubscourier;

import com.softserve.edu.greencity.data.UBS.Certificates;
import com.softserve.edu.greencity.data.UBS.UBSDataStrings;
import com.softserve.edu.greencity.data.users.User;
import com.softserve.edu.greencity.data.users.UserRepository;
import com.softserve.edu.greencity.ui.locators.ubs.AddAddressPopupLocators;
import com.softserve.edu.greencity.ui.pages.common.WelcomePage;
import com.softserve.edu.greencity.ui.pages.ubs.*;
import com.softserve.edu.greencity.ui.tests.runner.GreenCityTestRunnerWithoutLogin;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.*;

public class UBSCertificateTest extends GreenCityTestRunnerWithoutLogin {
    private OrderDetailsPage orderDetailsPage;
    private WelcomePage welcomePage;
    @BeforeClass
    public void login(){
        User user = UserRepository.get().temporary();
        welcomePage = loadApplication()
                .signIn()
                .getManualLoginComponent()
                .successfullyLogin(user)
                .navigateToWelcomePage();

    }
    @BeforeMethod
    public void navigateToUBS(){
        orderDetailsPage = loadApplication()
                .navigateMenuUBSCourier();
        orderDetailsPage.fillOldClothes20L("21");
    }

    @AfterMethod
    public void cancelOrder(){
        orderDetailsPage.clickOnCancelButtonWhenChangesPresent()
                .clickCancelOrderButton();
    }
    @AfterClass
    public void logOut(){
        welcomePage.signOut();
    }

    @Test(testName = "GC-1979, GC-1978", description = "GC-1979, GC-1978")
    @Description("Verify that User can apply valid certificate and correct  message appears , discount is deducted.")
    public void inputOrder(){
        orderDetailsPage.inputCertificate(Certificates.ACTIVE_500.getCertificate())
                .clickActivateButton();
        String message = orderDetailsPage.getCertificateMessage().getText();
        softAssert.assertEquals(UBSDataStrings.CORRECT_500_CERTIFICATE_MESSAGE_ENG.getMessage(), message, "Messages mismatch.");
        int due = orderDetailsPage.getAmountDueNumber();
        int discountFromLabel = orderDetailsPage.getCertificateLabelNumber();
        int discountFromMessage = orderDetailsPage.getDiscountFromMessage(message);
        softAssert.assertEquals(discountFromMessage,discountFromLabel);
        int totalSum = orderDetailsPage.getTotalSum();
        softAssert.assertEquals(totalSum-discountFromLabel,due);
        softAssert.assertAll();

    }

    @Test(testName = "GC-1975", description = "GC-1975")
    @Description("Verify that the user can order services when he applies the certificate, and leaves a comment")
    public void certificateAndComment(){

        orderDetailsPage.inputCertificate(Certificates.ACTIVE_1000.getCertificate())
                .clickActivateButton()
                .getCommentTextarea()
                .enterText(UBSDataStrings.ORDER_COMMENT.getMessage());
        PersonalDataPage personalDataPage = orderDetailsPage.clickOnNextButton();
        softAssert.assertTrue(personalDataPage.getAddAddressButton().isActive(),"crossing to personaldata page failed, or add addres button is not on the page");
        personalDataPage.clickOnBackButton();
        softAssert.assertEquals(orderDetailsPage.getCommentTextarea().getText(), UBSDataStrings.ORDER_COMMENT.getMessage(), "comments mismatch");

        softAssert.assertEquals(orderDetailsPage.getCertificateInput().getValue(),Certificates.ACTIVE_1000.getCertificate(), "Certificates do not match");
        softAssert.assertEquals(orderDetailsPage.getServicesComponents().get(0).getInput().getValue(),"21", "input quantuty mismatch");
        softAssert.assertAll();
    }

    @Test(testName = "GC-1969", description = "GC-1969")
    @Description("Verify that when the certificate is accepted the button “Активувати” switches to button “Відмінити")
    public void checkActivateCancelCertificateBtn(){
         orderDetailsPage.inputCertificate(Certificates.ACTIVE_1000.getCertificate())
                 .clickActivateButton();
         Assert.assertTrue(orderDetailsPage.isCancelButtonActive());
    }

    @Test(testName = "GC-1961", description = "GC-1961")
    @Description("Verify first four numeric characters of the certificate,system  enters a dash according to the certificate format")
    public void dashTest(){
        orderDetailsPage.inputCertificate(Certificates.FOUR_DIGITS.getCertificate());
        Assert.assertEquals(orderDetailsPage.getCertificateInput().getValue(),UBSDataStrings.FOUR_DIGITS.getMessage(),"NotEqual");
        //TODO WRITE DEFECT REPORT???
    }

    @Test(testName = "GC-1990", description = "GC-1990")
    @Description("System counts discount after user enters two or more certificates")
    public void twoCertificatesTest(){
        orderDetailsPage.fillOldClothes120L("5");
        orderDetailsPage.inputCertificate(Certificates.ACTIVE_1000.getCertificate())
                .clickActivateButton()
                .clickAddCertificateButton()
                .activateCertificateByPosition(0,Certificates.ACTIVE_500.getCertificate())
                .clickAddCertificateButton()
                .activateCertificateByPosition(1,Certificates.ACTIVE_300.getCertificate());
        int due = orderDetailsPage.getAmountDueNumber();
        int discountFromLabel = orderDetailsPage.getCertificateLabelNumber();
        String message = orderDetailsPage.getCertificateMessage().getText();
        int discountFromMessage = orderDetailsPage.getDiscountFromMessage(message);
        softAssert.assertEquals(discountFromMessage,discountFromLabel);
        int totalSum = orderDetailsPage.getTotalSum();
        softAssert.assertEquals(totalSum-discountFromLabel,due);
        softAssert.assertEquals(String.format(UBSDataStrings.CORRECT_CERTIFICATE_THREE_ACTIVE.getMessage()),message,"messages mismatch");
        softAssert.assertAll();
        //Test can fail due to front defect.
    }
    @Test(testName = "GC-1965", description = "GC-1965")
    @Description("Verifies Activate Certificate button")
    public void certificateButtonTest(){
        orderDetailsPage.inputCertificate(Certificates.SEVEN_DIGITS.getCertificate());
        softAssert.assertFalse(orderDetailsPage.isActicateButtonActive());
        orderDetailsPage.inputCertificate(Certificates.ACTIVE_300.getCertificate());
        softAssert.assertTrue(orderDetailsPage.isActicateButtonActive());
        softAssert.assertAll();
    }
    @Test(testName = "GC-1980",description = "GC-1980")
    @Description("Wrong certificates input")
    public void wrongCertificatesInput(){
        orderDetailsPage.inputCertificate(Certificates.USED_500.getCertificate());
        orderDetailsPage.clickActivateButton();
        softAssert.assertEquals(orderDetailsPage.getCertificateMessage().getText(),"Certificate has already been used 2021-11-28");
        //todo report future dates in certificate
        orderDetailsPage.inputCertificate(Certificates.EXPIRED_1000.getCertificate());
        orderDetailsPage.clickActivateButton();
        softAssert.assertEquals(orderDetailsPage.getCertificateMessage().getText(), "Certificate is invalid. Certificate validity is up to 2021-06-24");
        orderDetailsPage.inputCertificate(Certificates.FAKE.getCertificate());
        orderDetailsPage.clickActivateButton();
        softAssert.assertEquals(orderDetailsPage.getCertificateMessage().getText(),"Certificate not found; check that the data is correct.");
        softAssert.assertAll();
    }
    @Test(testName = "GC-1982", description = "GC-1982")
    @Description("Verify that system does not show the link addCertificate")
    public void checkAddCertificateButtonIsAbsent(){
        orderDetailsPage.fillOldClothes20L("10");
        String message = orderDetailsPage.inputCertificate(Certificates.ACTIVE_1000.getCertificate())
                .clickActivateButton()
                .getCertificateMessage().getText();
        softAssert.assertEquals(message,UBSDataStrings.BONUS_500_CERTIFICATE_MESSAGE_ENG.getMessage(),"Messages mismatch");
        softAssert.assertFalse(orderDetailsPage.isAddCertificateBtnPresent(),"Check Add certificate button");
        softAssert.assertEquals("0 UAH",orderDetailsPage.getTextAmountDue(), "Due amounts are different.");
        softAssert.assertAll();
    }
    @Test(testName = "GC-2064", description = "GC-2064")
    @Description("Verify that user can navigate through the pages without loosing previously entered data")
    public void checkAllEnteredData(){
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
