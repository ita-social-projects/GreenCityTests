package com.softserve.edu.greencity.ui.tests.ubscourier;

import com.softserve.edu.greencity.data.users.User;
import com.softserve.edu.greencity.data.users.UserRepository;
import com.softserve.edu.greencity.ui.locators.ubs.AddAddressPopupLocators;
import com.softserve.edu.greencity.ui.pages.ubs.*;
import com.softserve.edu.greencity.ui.tests.runner.GreenCityTestRunner;
import io.qameta.allure.Description;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PaymentPageTest extends GreenCityTestRunner {
    private OrderDetailsPage orderDetailsPage;
    private  PersonalDataPage personalDataPage;
    private  AddAddressPopupComponent addAddressPopupComponent;


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
//    public void cancelOrder(){
//        orderDetailsPage.signOut();
//    }

    @Test(testName="GC-2059", description="GC-2059")
    @Description("GC-2059")
    public void orderNumberBlockDisplayedCorrectly(){
        logger.info("Verify that order number in 'Доставка з Еко магазину' block is displayed correctly");
        orderDetailsPage.getServicesComponents().get(0).getInput().sendKeys("1");
        orderDetailsPage.getServicesComponents().get(1).getInput().sendKeys("1");
        orderDetailsPage.getServicesComponents().get(2).getInput().sendKeys("1");
        orderDetailsPage.getCertificateInput().sendKeys("55555555");
       orderDetailsPage.getActivateCertificateButton().click();
    //   orderDetailsPage.clickYesWaitingForAnOrderButton();
      //  orderDetailsPage.inputOrderNumber("2231111111");

        PersonalDataPage personalDataPage= orderDetailsPage.clickOnNextButton();

        personalDataPage.inputName("Jack")
                .inputSurname("London")
                .inputPhone("0631234567")
                .inputEmail("JkL@gmail.com");

         AddAddressPopupComponent addAddressPopupComponent=personalDataPage.clickOnAddAddressButton();

              addAddressPopupComponent.chooseCity(AddAddressPopupLocators.CITY_KIEV).getStreetInput().sendKeys("Хрещатик");
              addAddressPopupComponent.getDistrictInput().click();
        addAddressPopupComponent.getDistrictInput().sendKeys("LLLLLLL");
        addAddressPopupComponent.inputHouse("1");

        //AddAddressPopupComponent  addAddressPopupComponent=addAddressPopupComponent.getDistrictInput().click();
       // addAddressPopupComponent.getDistrictInput().sendKeys("1");
       //addAddressPopupComponent.inputStreet(new UserAddress("one","4",1,"3d",6));
        //.inputDistrict().inputHouse().inputCorp().inputEntrance();
       // addAddressPopupComponent.get.sendKeys("1");





    }






}
