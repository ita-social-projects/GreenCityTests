package com.softserve.edu.greencity.ui.tests.ubscourier;

import com.softserve.edu.greencity.data.users.User;
import com.softserve.edu.greencity.data.users.UserRepository;
import com.softserve.edu.greencity.ui.pages.ubs.OrderDetailsPage;
import com.softserve.edu.greencity.ui.tests.runner.GreenCityTestRunnerWithoutLogin;
import io.qameta.allure.Description;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class OrderDetailsTest extends GreenCityTestRunnerWithoutLogin {
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

    @AfterMethod
    public void signOut() {
        orderDetailsPage.signOut();
    }

    @Test(testName = "GC-1944", description = "GC-1944")
    @Description("GC-1944")
    public void checkTabsIsActiveOnUBSPage() {
        logger.info("Verify that 'Order details', the next 'Personal data' and 'Confirmation' tabs are active");
        orderDetailsPage.getOrderDetailsButton();
        softAssert.assertTrue(orderDetailsPage.getOrderDetailsButton().isActive());
        softAssert.assertTrue(orderDetailsPage.getPersonalDataButton().isActive());
        softAssert.assertTrue(orderDetailsPage.getPaymentButton().isActive());
        softAssert.assertAll();
    }
}