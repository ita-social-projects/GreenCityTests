package com.softserve.edu.greencity.ui.tests.ubscourier;

import com.softserve.edu.greencity.data.users.User;
import com.softserve.edu.greencity.data.users.UserRepository;
import com.softserve.edu.greencity.ui.pages.ubs.OrderDetailsPage;
import com.softserve.edu.greencity.ui.pages.ubs.PersonalDataPage;
import com.softserve.edu.greencity.ui.tests.runner.GreenCityTestRunner;
import io.qameta.allure.Description;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

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

    @Test(testName = "GC-5001", description = "Verify that user can't click on button 'Add Address when the list of addresses consist of 4 items'")
    @Description("GC-5001")
    public void verifyDeleteAddress() {
        while(personalDataPage.getQuantityOfAddresses() < 4) {
            personalDataPage = personalDataPage.clickOnAddAddressButton().
        }
    }
}
