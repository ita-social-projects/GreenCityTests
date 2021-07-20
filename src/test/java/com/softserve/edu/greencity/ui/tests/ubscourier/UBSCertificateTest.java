package com.softserve.edu.greencity.ui.tests.ubscourier;

import com.softserve.edu.greencity.data.UBS.Certificates;
import com.softserve.edu.greencity.data.UBS.UBSDataStrings;
import com.softserve.edu.greencity.data.users.User;
import com.softserve.edu.greencity.data.users.UserRepository;
import com.softserve.edu.greencity.ui.pages.ubs.OrderDetailsPage;
import com.softserve.edu.greencity.ui.pages.ubs.PersonalDataPage;
import com.softserve.edu.greencity.ui.tests.runner.GreenCityTestRunner;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UBSCertificateTest extends GreenCityTestRunner {
   private OrderDetailsPage orderDetailsPage;
    @BeforeMethod
    public void login(){
        User user = UserRepository.get().temporary();
        SoftAssert softAssert = new SoftAssert();
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

    @Test(testName = "GC-1979", description = "GC-1979")
    @Description("Verify that User can apply valid certificate and correct error message appears ")
    public void inputOrder(){
        orderDetailsPage.getCertificateInput().sendKeys(Certificates.ACTIVE_1000.getCertificate());
        orderDetailsPage.getActivateCertificateButton().click();
        String message = orderDetailsPage.getCertificateMessage().getText();
        Assert.assertEquals(UBSDataStrings.CORRECT_CERTIFICATE_MESSAGE_ENG.getMessage(), message, "Messages mismatch.");
    }
    @Test(testName = "GC-1975", description = "GC-1975")
    @Description("Verify that the user can order services when he applies the certificate, and leaves a comment")
    public void certificateAndComment(){
        orderDetailsPage.getCertificateInput().sendKeys(Certificates.ACTIVE_1000.getCertificate());
        orderDetailsPage.getActivateCertificateButton().click();
        orderDetailsPage.getCommentTextarea().enterText(UBSDataStrings.ORDER_COMMENT.getMessage());
        PersonalDataPage personalDataPage = orderDetailsPage.clickOnNextButton();
        softAssert.assertTrue(personalDataPage.getAddAddressButton().isActive(),"crossing to personaldata page failed, or add addres button is not on the page");
        personalDataPage.clickOnBackButton();
        softAssert.assertEquals(orderDetailsPage.getCommentTextarea().getText(), UBSDataStrings.ORDER_COMMENT.getMessage(), "comments mismatch");
        softAssert.assertEquals(orderDetailsPage.getCertificateInput().getValue().replace("-",""),Certificates.ACTIVE_1000.getCertificate(), "Certificates do not match");
        softAssert.assertEquals(orderDetailsPage.getServicesComponents().get(0).getInput().getAttribute("value"),"20", "input quantuty mismatch");
        softAssert.assertAll();
    }
    @Test(testName = "GC-1990", description = "GC-1990")
    @Description("System counts discount after user enters two or more certificates")
    public void twoCertificatesTest(){
        orderDetailsPage.getServicesComponents().get(1).getInput().sendKeys("5");
        orderDetailsPage.getCertificateInput().sendKeys(Certificates.ACTIVE_1000.getCertificate());
        orderDetailsPage.clickActivateButton();
        orderDetailsPage.clickAddCertificateButton();
        orderDetailsPage.getAdditionalCertificates().get(0).getCertificateInput().sendKeys(Certificates.ACTIVE_500.getCertificate());
        orderDetailsPage.getAdditionalCertificates().get(0).getActivateCertificateButton().click();
        orderDetailsPage.clickAddCertificateButton();
        orderDetailsPage.getAdditionalCertificates().get(1).getCertificateInput().sendKeys(Certificates.ACTIVE_300.getCertificate());
        orderDetailsPage.getAdditionalCertificates().get(1).getActivateCertificateButton().click();
        orderDetailsPage.getTextOrderAmount();
        int due = orderDetailsPage.getAmountDueNumber();
        int discountFromLabel = orderDetailsPage.getCertificateLabelNumber();
        String message = orderDetailsPage.getCertificateMessage().getText();
        int discountFromMessage = orderDetailsPage.getDiscountFromMessage(message);
        softAssert.assertEquals(discountFromMessage,discountFromLabel);
        int totalSum = orderDetailsPage.getTotalSum();
        softAssert.assertEquals(totalSum-discountFromLabel,due);
        softAssert.assertEquals(String.format(UBSDataStrings.CORRECT_CERTIFICATE_THREE_ACTIVE.getMessage()),message,"messages mismatch");
        softAssert.assertAll();
        System.out.println(message);
    }

}
