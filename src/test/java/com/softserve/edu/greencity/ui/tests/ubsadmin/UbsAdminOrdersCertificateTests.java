package com.softserve.edu.greencity.ui.tests.ubsadmin;

import com.softserve.edu.greencity.data.users.User;
import com.softserve.edu.greencity.data.users.UserRepository;
import com.softserve.edu.greencity.ui.pages.cabinet.MyHabitPage;
import com.softserve.edu.greencity.ui.pages.common.MainMenuDropdown;
import com.softserve.edu.greencity.ui.pages.ubs.OrderDetailsPage;
import com.softserve.edu.greencity.ui.pages.ubs.UbsCourierPage;
import com.softserve.edu.greencity.ui.pages.ubsadmin.UbsAdminPage.UbsAdminOrders;
import com.softserve.edu.greencity.ui.tests.runner.GreenCityTestRunnerWithLoginLogout;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class UbsAdminOrdersCertificateTests extends GreenCityTestRunnerWithLoginLogout {
    private User adminUser;
    private MainMenuDropdown mainMenuDropdown;
    private OrderDetailsPage orderDetailsPage;
    private MyHabitPage myHabitPage;


    @BeforeClass
    public void readCredentials() {
        adminUser = UserRepository.get().getAdminUser();
    }
    @BeforeMethod
    public void login() {
        myHabitPage = loadApplication()
                .signIn()
                .getManualLoginComponent()
                .successfullyLogin(adminUser);
    }
    @Test
    public void verifyTwoCertificateViewCorrectness() {
        final String firstCertificate = "9000-0003";
        final String secondCertificate = "1234-5555";
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
        driver.get("https://ita-social-projects.github.io/GreenCityClient/#/ubs-admin/orders");
        String actualResult = new UbsAdminOrders(driver)
                .setSearchField("NameForCertificateTest")
                .getTextOfCertificateNumber();
        String expectedResult = firstCertificate + "; " + secondCertificate;
        softAssert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void verifyCertificateTransferCorrectness() {
        new MyHabitPage(driver)
                .getMainMenuDropdown().clickMenuUBSCourier();
        new UbsCourierPage(driver)
                .clickOnCallUpTheCourierButton()
                .clickOnContinueButton()
                .fillOldClothes120L("50")
                .activateCertificateByPosition(0, "0999-0111")
                .clickOnNextButton()
                .inputName("NameForCertificateTest")
                .selectAddressByIndex(0)
                .clickOnNextButton()
                .clickOnOrderButton()
                .acceptAlert()
                .setSuccessfulPaymentCredits();
        driver.get("https://ita-social-projects.github.io/GreenCityClient/#/ubs-admin/orders");
        String actualResult = new UbsAdminOrders(driver)
                .setSearchField("NameForCertificateTest")
                .getTextOfCertificateNumber();
        String expectedResult = "0999-0111";
        softAssert.assertEquals(actualResult, expectedResult);
    }

}
