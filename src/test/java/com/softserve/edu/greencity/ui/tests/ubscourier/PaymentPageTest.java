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

public class PaymentPageTest extends GreenCityTestRunner {
    private OrderDetailsPage orderDetailsPage;
    private  PersonalDataPage personalDataPage;


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
    public void cancelOrder(){
        orderDetailsPage.signOut();
    }

    @Test(testName="GC-2059", description="GC-2059")
    @Description("GC-2059")
    public void orderNumberBlockDisplayedCorrectly(){
        logger.info("Verify that order number in 'Доставка з Еко магазину' block is displayed correctly");
        orderDetailsPage.getServicesComponents().get(0).getInput().sendKeys("2");
        orderDetailsPage.getServicesComponents().get(1).getInput().sendKeys("1");
        orderDetailsPage.getServicesComponents().get(2).getInput().sendKeys("1");
        orderDetailsPage.getCertificateInput().sendKeys("55555555");
        orderDetailsPage.getActivateCertificateButton().click();
        //Ecostore

        orderDetailsPage.clickOnNextButton()
                .inputName("Jack")
                .inputSurname("London")
                .inputPhone("0631234567")
                .inputEmail("JkL@gmail.com").clickOnAddAddressButton();





    }






}
