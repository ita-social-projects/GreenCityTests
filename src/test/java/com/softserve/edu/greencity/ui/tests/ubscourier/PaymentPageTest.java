package com.softserve.edu.greencity.ui.tests.ubscourier;

import com.softserve.edu.greencity.data.UBS.Certificates;
import com.softserve.edu.greencity.data.UBS.UBSDataStrings;
import com.softserve.edu.greencity.data.users.User;
import com.softserve.edu.greencity.data.users.UserRepository;
import com.softserve.edu.greencity.ui.locators.ubs.AddAddressPopupLocators;
import com.softserve.edu.greencity.ui.pages.ubs.*;
import com.softserve.edu.greencity.ui.tests.runner.GreenCityTestRunner;
import io.qameta.allure.Description;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

public class PaymentPageTest extends GreenCityTestRunner {

    private OrderDetailsPage orderDetailsPage;
    private PaymentPage paymentPage;
    private PersonalDataPage personalDataPage;

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
    public void cancelOrder() {
        orderDetailsPage.signOut();
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

        softAssert.assertTrue(orderDetailsPage.getNextButton().isActive(),"Button from 'Order details page' is not active");

       PersonalDataPage personalDataPage = orderDetailsPage.clickOnNextButton();
        personalDataPage.fullPersonalData("Jack","London","0634567890","Jkl@gmail.com");
        AddAddressPopupComponent addAddressPopupComponent = personalDataPage.clickOnAddAddressButton();
        addAddressPopupComponent.fillAllFields(
                new UserAddress(AddAddressPopupLocators.CITY_KIEV, "Sadova", "Kiev", 1, "1", 2))
                .clickOnAddAddressButton();
        //TODO method for wait
        softAssert.assertTrue(personalDataPage.getNextButton().isActive(),"Button from 'Personal data page' is not active");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        PaymentPage paymentPage = personalDataPage.clickOnNextButton();
      //  softAssert.assertTrue(paymentPage.getOrderNumbers().isDisplayedLabel(),"Order number isn't displayed");
        softAssert.assertEquals(paymentPage.getOrderNumbers().getText(),"1111111111", "Order number isn't displayed correctly");
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
        orderDetailsPage.getOrderNumberInput().sendKeys(UBSDataStrings.ORDER_NUMBER_ONE.getMessage());
        orderDetailsPage.getOrderNumberInput().sendKeys(UBSDataStrings.ORDER_NUMBER_TWO.getMessage());
        PersonalDataPage personalDataPage = orderDetailsPage.clickOnNextButton();
        personalDataPage.fullPersonalData("Jack","London","0634567890","Jkl@gmail.com");
        AddAddressPopupComponent addAddressPopupComponent = personalDataPage.clickOnAddAddressButton();
        addAddressPopupComponent.fillAllFields(
                new UserAddress(AddAddressPopupLocators.CITY_KIEV, "Sadova", "Kiev", 1, "1", 2))
                .clickOnAddAddressButton();
        //TODO method for wait
        PaymentPage paymentPage = personalDataPage.clickOnNextButton();

        softAssert.assertEquals(
                paymentPage.returnAllOrderNumbers(), "1111111111,2222222222", "Order numbers aren't displayed correctly");
        softAssert.assertAll();


    }

    @Test(testName = "GC-2061", description = "GC-2061")
    @Description("GC-2061")
    public void allCorrectInformationAboutOrderDisplayedCorrectly() {
        logger.info("Verify that user sees all correct information about the order");


    }


    @Test(testName = "GC-2062", description = "GC-2062")
    @Description("GC-2062")
    public void personalDataDisplayedCorrectly() {
        logger.info("Verify that the personal data is displayed correctly");
        orderDetailsPage.fillAllFieldsForServices(new Random().nextInt(2) + 1)
                .getCertificateInput().sendKeys(Certificates.ACTIVE_1000.getCertificate());
        orderDetailsPage.getActivateCertificateButton().click();
        orderDetailsPage.getYesWaitingOrderButton().click();
        orderDetailsPage.inputOrderNumber("1111111111").inputComment("First comment");
        PersonalDataPage personalDataPage = orderDetailsPage.clickOnNextButton();
        personalDataPage.fullPersonalData("Jack","London","0634567890","Jkl@gmail.com");
        AddAddressPopupComponent addAddressPopupComponent = personalDataPage.clickOnAddAddressButton();
        addAddressPopupComponent.fillAllFields(
                new UserAddress(AddAddressPopupLocators.CITY_KIEV, "Sadova", "Kiev", 2, "3", 4))
                .clickOnAddAddressButton();
                //inputComment("Comment to order");
        personalDataPage.inputComment("Comment to order");
        //TODO method for wait
        PaymentPage paymentPage = personalDataPage.clickOnNextButton();
        logger.info("Verify full name");
        softAssert.assertEquals(paymentPage.getFullName().getText(),personalDataPage.getFullName());
        logger.info("Verify phone number");
        softAssert.assertEquals(paymentPage.getPhone().getText(),personalDataPage.getPhoneNumber());//TODO BUG with phone
        logger.info("Verify gmail address");
        softAssert.assertEquals(paymentPage.getGmail().getText(),personalDataPage.getEmailAddress());

        softAssert.assertAll();

    }

    @Test(testName = "GC-2063", description = "GC-2063")
    @Description("GC-2063")
    public void correctAddressAndCommentsAreDisplayed() {
        logger.info("Verify that the correct address and comments are displayed in 'Адреса вивезення замовлених послуг' block");
    }

    @Test(testName = "GC-2064", description = "GC-2064")
    @Description("GC-2064")
    public void previouslyEnteredDataIsSavedAndDisplayed() {
        logger.info("Verify that previously entered data is saved when navigating in steps in 'UBS кур'єр' tab");
    }

    @Test(testName = "GC-2065", description = "GC-2065")
    @Description("GC-2065")
    public void blockConfirmationIsNotDisplayed() {
        logger.info("Verify that 'Оплата' block is not displayed when the order amount equals 0 UAH.");
    }

    @Test(testName = "GC-2066", description = "GC-2066")
    @Description("GC-2066")
    public void verifAllControls() {
        logger.info("This is a checklist for testing the last step UI in 'UBS кур'єр' tab");
    }


}
