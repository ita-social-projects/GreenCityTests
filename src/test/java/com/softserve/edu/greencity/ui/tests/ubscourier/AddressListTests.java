package com.softserve.edu.greencity.ui.tests.ubscourier;

import com.softserve.edu.greencity.data.users.User;
import com.softserve.edu.greencity.data.users.UserRepository;
import com.softserve.edu.greencity.ui.locators.ubs.AddAddressPopupLocators;
import com.softserve.edu.greencity.ui.pages.ubs.OrderDetailsPage;
import com.softserve.edu.greencity.ui.pages.ubs.PaymentPage;
import com.softserve.edu.greencity.ui.pages.ubs.PersonalDataPage;
import com.softserve.edu.greencity.ui.pages.ubs.UserAddress;
import com.softserve.edu.greencity.ui.tests.runner.GreenCityTestRunner;
import io.qameta.allure.Description;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class AddressListTests extends GreenCityTestRunner {
    private OrderDetailsPage orderDetailsPage;
    private PersonalDataPage personalDataPage;

    @BeforeMethod
    public void signIn() {
        User user = UserRepository.get().temporary();
        orderDetailsPage = loadApplication()
                .signIn()
                .getManualLoginComponent()
                .successfullyLogin(user)
                .navigateMenuUBSCourier();
        orderDetailsPage.getServicesComponents().get(0).getInput().sendKeys("15");
        personalDataPage = orderDetailsPage.clickOnPersonalDataButton();
    }

    @AfterMethod
    public void goToWelcomePage() {
        personalDataPage.clickOnCancelButton().clickCancelOrderButton();
    }

    @Test(testName = "GC-5001", description = "Verify that user can't click on button 'Add Address when the list of addresses consist of 4 items'")
    @Description("GC-5001")
    public void verifyDeleteAddress() {
        while (personalDataPage.getQuantityOfAddresses() < 4) {
            UserAddress addressData = new UserAddress(AddAddressPopupLocators.CITY_KIEV).generateRandomAddressValues();
            personalDataPage = personalDataPage.clickOnAddAddressButton().fillAllFields(addressData).clickOnAddAddressButton();
        }
        softAssert.assertFalse(personalDataPage.isAddAddressButtonActive());
    }

    @Test(testName = "GC-5002", description = "Verify correct message when there is no addresses in list", dataProviderClass = DataProviders_UBS.class, dataProvider = "absenceOfAddressesMessages")
    @Description("GC-5002")
    public void verifyMessageOfAbsenceAddresses(String language, String expectedMassage) {

        while (personalDataPage.getQuantityOfAddresses() > 0) {
            personalDataPage = personalDataPage.deleteAddressOfIndex(personalDataPage.getQuantityOfAddresses() - 1);
        }

        personalDataPage.createLanguageSwitchComponent().changeLanguage(language);
        softAssert.assertTrue(personalDataPage.getAbsenceAddressesText().equals(expectedMassage), "The messages are not the same!");
    }
}
