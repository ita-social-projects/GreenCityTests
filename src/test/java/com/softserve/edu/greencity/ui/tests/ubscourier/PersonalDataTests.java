package com.softserve.edu.greencity.ui.tests.ubscourier;

import com.softserve.edu.greencity.data.users.User;
import com.softserve.edu.greencity.data.users.UserRepository;
import com.softserve.edu.greencity.ui.pages.ubs.OrderDetailsPage;
import com.softserve.edu.greencity.ui.pages.ubs.PaymentPage;
import com.softserve.edu.greencity.ui.pages.ubs.PersonalDataPage;
import com.softserve.edu.greencity.ui.tests.runner.GreenCityTestRunner;
import io.qameta.allure.Description;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PersonalDataTests extends GreenCityTestRunner {

    private OrderDetailsPage orderDetailsPage;
    private PersonalDataPage personalDataPage;
    private PaymentPage paymentPage;

    @BeforeMethod
    public void login(){
        User user = UserRepository.get().temporary();
        orderDetailsPage = loadApplication()
                .signIn()
                .getManualLoginComponent()
                .successfullyLogin(user)
                .navigateMenuUBSCourier();
        orderDetailsPage.getServicesComponents().get(0).getInput().sendKeys("20");
    }
    @AfterMethod
    public void cancelOrder(){
        orderDetailsPage.signOut();
    }

    @Test(testName = "GC-2040", description = "GC-2040")
    @Description("Verify that second tab 'Personal data' is active, the tab 'Order details' is marked as done and 'Confirmation' tab is disabled")
    public void verifyActiveOrDisabledTabs(){
        orderDetailsPage.clickOnNextButton();
        personalDataPage.clickOnPersonalDataButton();
        paymentPage.clickOnPaymentButton();


    }
}
