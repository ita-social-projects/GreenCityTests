package com.softserve.edu.greencity.ui.tests.ubscourier;

import com.softserve.edu.greencity.data.users.User;
import com.softserve.edu.greencity.data.users.UserRepository;
import com.softserve.edu.greencity.ui.locators.ubs.AddAddressPopupLocators;
import com.softserve.edu.greencity.ui.pages.ubs.*;
import com.softserve.edu.greencity.ui.tests.runner.GreenCityTestRunner;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PersonalDataWithAddressTests extends GreenCityTestRunner {

    private OrderDetailsPage orderDetailsPage;
    private PersonalDataPage personalDataPage;
    private PaymentPage paymentPage;

    @BeforeMethod
    public void login() {
        User user = UserRepository.get().temporary();
        orderDetailsPage = loadApplication()
                .signIn()
                .getManualLoginComponent()
                .successfullyLogin(user)
                .navigateMenuUBSCourier();
        orderDetailsPage.getServicesComponents().get(0).getInput().sendKeys("20");
    }

    @AfterMethod
    public void deleteAddress() {
        personalDataPage.deleteAddressOfIndex(personalDataPage.getQuantityOfAddresses()-1);
    }

    @Test(testName = "GC-2047", description = "GC-2047")
    @Description("Verify ordering when fields from first section on the 'Personal data' page filled with valid data")
    public void verifyOrderingWithValidData() {
        personalDataPage = orderDetailsPage.clickOnNextButton();
        personalDataPage.inputName("Lina")
                .inputSurname("Serhova")
                .inputPhone("0961111111")
                .inputEmail("dcghkv@gmail.com");
        AddAddressPopupComponent addAddressPopupComponent = personalDataPage.clickOnAddAddressButton();
        addAddressPopupComponent.fillAllFields(
                new UserAddress(AddAddressPopupLocators.CITY_KIEV, "Sadova", "Kiev", 2, "3", 4))
                .clickOnAddAddressButton();
        personalDataPage.clickOnNextButton();
        Assert.assertEquals("3\nConfirmation", paymentPage.getPaymentButton().getText());
    }

    @Test(testName = "GC-2042", description = "GC-2042")
    @Description("Verify if the system save the order after interrupt the order")
    public void verifySaveOrderAfterInterrupt() {
        personalDataPage = orderDetailsPage.clickOnPersonalDataButton();
        personalDataPage.inputName("Lina")
                .inputSurname("Serhova")
                .inputPhone("0961111111")
                .inputEmail("dcghkv@gmail.com");
        AddAddressPopupComponent addAddressPopupComponent = personalDataPage.clickOnAddAddressButton();
        addAddressPopupComponent.fillAllFields(
                new UserAddress(AddAddressPopupLocators.CITY_KIEV, "Sadova", "Kiev", 2, "3", 4))
                .clickOnAddAddressButton();
        personalDataPage.clickOnCancelButton().clickCancelOrderButton();
    }

    @Test(testName = "GC-2041", description = "GC-2041")
    @Description("Verify if the system erase all entered user data after interrupt the order")
    public void verifyEraseDataAfterInterrupt() {
        personalDataPage = orderDetailsPage.clickOnPersonalDataButton();
        personalDataPage.inputName("Lina")
                .inputSurname("Serhova")
                .inputPhone("0961111111")
                .inputEmail("dcghkv@gmail.com");
        AddAddressPopupComponent addAddressPopupComponent = personalDataPage.clickOnAddAddressButton();
        addAddressPopupComponent.fillAllFields(
                new UserAddress(AddAddressPopupLocators.CITY_KIEV, "Sadova", "Kiev", 2, "3", 4))
                .clickOnAddAddressButton();
        personalDataPage.clickOnCancelButton().clickContinueMakingOrderButton();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }//TODO method for wait
        Assert.assertEquals("2\nPersonal data", personalDataPage.getPersonalDataButton().getText());
    }

}
