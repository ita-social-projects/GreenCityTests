package com.softserve.edu.greencity.ui.tests.ubscourier;

import com.softserve.edu.greencity.data.users.User;
import com.softserve.edu.greencity.data.users.UserRepository;
import com.softserve.edu.greencity.ui.locators.ubs.AddAddressPopupLocators;
import com.softserve.edu.greencity.ui.pages.econews.CreateNewsPage;
import com.softserve.edu.greencity.ui.pages.ubs.AddAddressPopupComponent;
import com.softserve.edu.greencity.ui.pages.ubs.OrderDetailsPage;
import com.softserve.edu.greencity.ui.pages.ubs.PersonalDataPage;
import com.softserve.edu.greencity.ui.pages.ubs.UserAddress;
import com.softserve.edu.greencity.ui.tests.runner.GreenCityTestRunner;
import io.qameta.allure.Description;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddAddressPopupTest extends GreenCityTestRunner {
    private CreateNewsPage createNewsPage;
    private OrderDetailsPage orderDetailsPage;
    private PersonalDataPage personalDataPage;
    private AddAddressPopupComponent addAddressPopup;

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

    @Test(testName = "GC-", description = "GC-")
    @Description("GC-")
    public void addAddress() {
        UserAddress userAddress = new UserAddress(AddAddressPopupLocators.CITY_KIEV, "Sadova", "Kiev", 1, "1", 2);
        new AddAddressPopupComponent(driver).inputDistrict(userAddress);
    }

    @Test(testName = "GC-", description = "verify that 'Add Address' button is disabled while input fields of city, street and district are empty")
    @Description("GC-")
    public void verifyAddAddressButtonIsDisabledWhenNeededDataFieldsIsEmpty() {
        UserAddress userAddress = new UserAddress(AddAddressPopupLocators.CITY_KIEV).generateRandomAddressValues();
        if (personalDataPage.isAddAddressButtonActive()) {
            addAddressPopup = personalDataPage.clickOnAddAddressButton();
        }
        else {
            addAddressPopup = personalDataPage.deleteAddressOfIndex(personalDataPage.getQuantityOfAddresses() - 1).clickOnAddAddressButton();
        }
        //TODO Bug: button is always active
        softAssert.assertFalse(addAddressPopup.isAddAddressButtonActive());
        addAddressPopup.chooseCity(AddAddressPopupLocators.CITY_KIEV);
        softAssert.assertFalse(addAddressPopup.isAddAddressButtonActive());
        addAddressPopup.inputStreet(userAddress);
        softAssert.assertFalse(addAddressPopup.isAddAddressButtonActive());
        addAddressPopup.inputDistrict(userAddress);
        softAssert.assertTrue(addAddressPopup.isAddAddressButtonActive());
    }

    @AfterMethod
    public void signOut() {
        addAddressPopup.clickOnCancelButton().clickOnCancelButton().clickCancelOrderButton();
    }
}
