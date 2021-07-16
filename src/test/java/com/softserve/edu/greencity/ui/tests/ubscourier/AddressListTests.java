package com.softserve.edu.greencity.ui.tests.ubscourier;

import com.softserve.edu.greencity.data.users.User;
import com.softserve.edu.greencity.data.users.UserRepository;
import com.softserve.edu.greencity.ui.pages.ubs.OrderDetailsPage;
import com.softserve.edu.greencity.ui.tests.runner.GreenCityTestRunner;
import io.qameta.allure.Description;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddressListTests extends GreenCityTestRunner {

    private OrderDetailsPage orderDetailsPage;

    @BeforeMethod
    public void signIn() {
        User user = UserRepository.get().temporary();
        orderDetailsPage = loadApplication()
                .signIn()
                .getManualLoginComponent()
                .successfullyLogin(user)
                .navigateMenuUBSCourier();
    }

    @Test(testName = "GC-", description = "verify Address")
    @Description("GC-")
    public void verifyDeleteAddress() {
        orderDetailsPage.getNumberOfPackeges().get(1).click();
        orderDetailsPage.getNumberOfPackeges().get(1).sendKeys("5");
        orderDetailsPage.clickOnPersonalDataButton().deleteAddressOfIndex(1);
    }
}
