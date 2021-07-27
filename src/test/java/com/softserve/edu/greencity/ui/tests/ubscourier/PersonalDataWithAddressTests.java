package com.softserve.edu.greencity.ui.tests.ubscourier;

import com.softserve.edu.greencity.data.UBS.UBSDataStrings;
import com.softserve.edu.greencity.data.users.User;
import com.softserve.edu.greencity.data.users.UserRepository;
import com.softserve.edu.greencity.ui.locators.ubs.AddAddressPopupLocators;
import com.softserve.edu.greencity.ui.pages.ubs.*;
import com.softserve.edu.greencity.ui.tests.runner.GreenCityTestRunnerWithLoginLogout;
import com.softserve.edu.greencity.ui.tools.engine.WaitsSwitcher;
import io.qameta.allure.Description;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PersonalDataWithAddressTests extends GreenCityTestRunnerWithLoginLogout {

    private OrderDetailsPage orderDetailsPage;
    private PersonalDataPage personalDataPage;

    @BeforeMethod
    public void login() {
        User user = UserRepository.get().temporary();
        orderDetailsPage = loadApplication()
                .signIn()
                .getManualLoginComponent()
                .successfullyLogin(user)
                .navigateMenuUBSCourier();
        orderDetailsPage.fillOldClothes20L("20");
    }

    @AfterMethod
    public void deleteAddress() {
        personalDataPage.deleteAddressOfIndex(personalDataPage.getQuantityOfAddresses()-1);
    }

    @Test(testName = "GC-2047", description = "GC-2047")
    @Description("Verify ordering when fields from first section on the 'Personal data' page filled with valid data")
    public void verifyOrderingWithValidData() {
        personalDataPage = orderDetailsPage.clickOnNextButton();
        personalDataPage.fullPersonalData(UBSDataStrings.PERSONAL_DATA_NAME.getMessage(),UBSDataStrings.PERSONAL_DATA_SURNAME.getMessage(),
                UBSDataStrings.PERSONAL_DATA_PHONE.getMessage(),UBSDataStrings.PERSONAL_DATA_EMAIL.getMessage());
        UserAddress addressData = new UserAddress(AddAddressPopupLocators.CITY_KIEV).generateRandomAddressValues();
        personalDataPage = personalDataPage.clickOnAddAddressButton().fillAllFields(addressData).clickOnAddAddressButton();
        PaymentPage paymentPage = personalDataPage.clickOnNextButton();
        softAssert.assertEquals("3\nConfirmation", personalDataPage.getPaymentButton().getText());
        softAssert.assertEquals(paymentPage.getFullName().getText(), UBSDataStrings.PERSONAL_DATA_NAME_SURNAME.getMessage());
        softAssert.assertEquals(paymentPage.getPhone().getText(), UBSDataStrings.PERSONAL_DATA_PHONE.getMessage());
        softAssert.assertEquals(paymentPage.getGmail().getText(), UBSDataStrings.PERSONAL_DATA_EMAIL.getMessage());
    }

    @Test(testName = "GC-2042", description = "GC-2042") //Need to connect to the database for check
    @Description("Verify if the system save the order after interrupt the order")
    public void verifySaveOrderAfterInterrupt() {
        personalDataPage = orderDetailsPage.clickOnPersonalDataButton();
        personalDataPage.fullPersonalData(UBSDataStrings.PERSONAL_DATA_NAME.getMessage(),UBSDataStrings.PERSONAL_DATA_SURNAME.getMessage(),
                UBSDataStrings.PERSONAL_DATA_PHONE.getMessage(),UBSDataStrings.PERSONAL_DATA_EMAIL.getMessage());
        UserAddress addressData = new UserAddress(AddAddressPopupLocators.CITY_KIEV).generateRandomAddressValues();
        personalDataPage = personalDataPage.clickOnAddAddressButton().fillAllFields(addressData).clickOnAddAddressButton();
        personalDataPage.clickOnCancelButton().clickCancelOrderButton();
    }

    @Test(testName = "GC-2041", description = "GC-2041")
    @Description("Verify if the system continue making order after clicking by 'Continue' button")
    public void verifyContinueMakingOrder() {
        personalDataPage = orderDetailsPage.clickOnPersonalDataButton();
        personalDataPage.fullPersonalData(UBSDataStrings.PERSONAL_DATA_NAME.getMessage(),UBSDataStrings.PERSONAL_DATA_SURNAME.getMessage(),
                UBSDataStrings.PERSONAL_DATA_PHONE.getMessage(),UBSDataStrings.PERSONAL_DATA_EMAIL.getMessage());
        UserAddress addressData = new UserAddress(AddAddressPopupLocators.CITY_KIEV).generateRandomAddressValues();
        personalDataPage = personalDataPage.clickOnAddAddressButton().fillAllFields(addressData).clickOnAddAddressButton();
        personalDataPage.clickOnCancelButton().clickContinueMakingOrderButton();
        WaitsSwitcher waitsSwitcher = new WaitsSwitcher(driver);
        waitsSwitcher.sleep(3000);
        softAssert.assertEquals("2\nPersonal data", personalDataPage.getPersonalDataButton().getText());
    }
}
