package com.softserve.edu.greencity.ui.tests.ubscourier;

import com.softserve.edu.greencity.data.users.User;
import com.softserve.edu.greencity.data.users.UserRepository;
import com.softserve.edu.greencity.ui.pages.ubs.OrderDetailsPage;
import com.softserve.edu.greencity.ui.tests.runner.GreenCityTestRunner;
import io.qameta.allure.Description;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class UBSCertificateTest extends GreenCityTestRunner {
   private OrderDetailsPage orderDetailsPage;
    @BeforeMethod
    public void login(){
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
    @Test
    public void inputOrder(){
        orderDetailsPage.getServicesComponents().get(0).getInput().sendKeys("20");
        orderDetailsPage.getCertificateInput().sendKeys("55555555");
        orderDetailsPage.getActivateCertificateButton().click();

        System.out.println("Test passed");
    }
    @Test(testName = "GC-7000", description = "GC-7000")
    @Description("Verifies that certificate button is not active after entering certificate without order.")
    public void activateCertificate() {
        logger.info("Starting activate certificate test.");
        String message = orderDetailsPage
                .getPointsBalanceLabel().getText();
        logger.info("checking points balance is zero");
        softAssert.assertEquals(message,"On your bonus account: 0 UAH");   // можна створити окремий клас щоб винести всі messages?
        logger.info("checking if activate certificate button is not active");
        softAssert.assertFalse(orderDetailsPage
                .inputCertificate("55555555")
                .isActicateButtonActive());
        softAssert.assertAll();
    }
    @Test(testName = "GC-7001", description = "GC-7001")
    @Description("Verifies user can't enter comment more than 255 characters")
    public void addComment(){
        logger.info("Start add comment test\nadding more than 255 characters in coomment textarea.");
        orderDetailsPage.getCommentTextarea().enterText("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Faucibus scelerisque eleifend donec pretium vulputate sapien nec. Senectus et netus et malesuada fames ac. Aliquet enim tortor at auctor urna nunc id cursus. Morbi quis commodo odio ");
        softAssert.assertEquals("You can't enter more than 255 characters.",orderDetailsPage.getCommentAlertLabel().getText());
    }


}
