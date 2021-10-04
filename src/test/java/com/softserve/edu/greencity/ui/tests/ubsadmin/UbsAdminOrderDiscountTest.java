package com.softserve.edu.greencity.ui.tests.ubsadmin;

import com.softserve.edu.greencity.data.users.User;
import com.softserve.edu.greencity.data.users.UserRepository;
import com.softserve.edu.greencity.ui.pages.cabinet.MyHabitPage;
import com.softserve.edu.greencity.ui.pages.ubs.UbsCourierPage;
import com.softserve.edu.greencity.ui.pages.ubsadmin.UbsAdminPage.UbsAdminOrders;
import com.softserve.edu.greencity.ui.pages.ubsadmin.UbsAdminPage.UbsAdminTableComponent;
import com.softserve.edu.greencity.ui.tests.runner.GreenCityTestRunnerWithLoginLogout;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class UbsAdminOrderDiscountTest extends GreenCityTestRunnerWithLoginLogout {
    private User adminUser;
    String userName = "IvannaTestName";
    String userLastName = "String";
    String userPhoneNumberForDb = "+380938607879";
    String userEmail = "testmail@mail.com";
    String firstCertificate = "22225556";
    String secondCertificate = "22225557";

    @BeforeClass
    public void readCredentials() {
        adminUser = UserRepository.get().getAdminUser();
        loadApplication()
                .signIn()
                .getManualLoginComponent()
                .successfullyLogin(adminUser);
        new MyHabitPage(driver)
                .getMainMenuDropdown().clickMenuUBSCourier();
        new UbsCourierPage(driver)
                .clickOnCallUpTheCourierButton()
                .clickOnContinueButton()
                .setOrderDetailsWithTwoCertificates("50", firstCertificate, secondCertificate)
                .clickOnNextButton()
                .inputName("NameForCertificateTest")
                .selectAddressByIndex(0)
                .clickOnNextButton()
                .clickOnOrderButton()
                .acceptAlert()
                .setSuccessfulPaymentCredits();
    }

    /*
    TQ-242
     */
    @Test
    public void testAmountDueValue(){
        driver.get("https://ita-social-projects.github.io/GreenCityClient/#/ubs-admin/orders");
        new UbsAdminOrders(driver)
                .clearSearchField()
                .setSearchField(userName);
        long[] results = new UbsAdminTableComponent(driver)
                .getActualAndExpectedAmountDue();

        Assert.assertEquals(new UbsAdminTableComponent(driver).isAmountDueFloatWithTwoDigits(),true);
        Assert.assertEquals(results[0], results[1]);
    }


    /*
    TQ-241
     */
    @Test
    public void testOrderCertificatePoints(){
        driver.get("https://ita-social-projects.github.io/GreenCityClient/#/ubs-admin/orders");
        new UbsAdminOrders(driver)
                .clearSearchField()
                .setSearchField(userName);

        Assert.assertEquals(new UbsAdminTableComponent(driver).isOrderCertificatePointsPositiveInteger(),true);
    }


//    @AfterClass
//    private void deleteTestDataFromDatabase() {
//        DataBaseConnection dataBase = new DataBaseConnection();
//        dataBase.connectionToDataBase();
//
//        dataBase.DeleteOrderFromAllTable(
//                dataBase.findOrderId(userName, userLastName, userPhoneNumberForDb, userEmail));
//
//        dataBase.closeConnection();
//    }

}
