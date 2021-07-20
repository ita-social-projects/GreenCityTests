package com.softserve.edu.greencity.ui.tests.ubscourier;

import com.softserve.edu.greencity.data.users.User;
import com.softserve.edu.greencity.data.users.UserRepository;
import com.softserve.edu.greencity.ui.elements.TextAreaElement;
import com.softserve.edu.greencity.ui.pages.ubs.OrderDetailsPage;
import com.softserve.edu.greencity.ui.pages.ubs.PaymentPage;
import com.softserve.edu.greencity.ui.pages.ubs.PersonalDataPage;
import com.softserve.edu.greencity.ui.tests.runner.GreenCityTestRunner;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.softserve.edu.greencity.ui.tests.signin.SignInTexts.ADD_NEW_HABIT_BUTTON_TEXT;

public class PersonalDataTests extends GreenCityTestRunner {

    private OrderDetailsPage orderDetailsPage;
    private PersonalDataPage personalDataPage;
    private PaymentPage paymentPage;

    @BeforeMethod
    public void login() {
        User user = UserRepository.get().temporary();
        orderDetailsPage = loadApplication()
                .signIn()
                .getManualLoginComponent()
                .successfullyLogin(user)
                .navigateMenuUBSCourier();
        orderDetailsPage.getServicesComponents().get(0).getInput().sendKeys("20");
    }

    @AfterMethod
    public void cancelOrder() {
        orderDetailsPage.signOut();
    }

    @Test(testName = "GC-2040", description = "GC-2040")
    @Description("Verify that second tab 'Personal data' is active, the tab 'Order details' is marked as done and 'Confirmation' tab is disabled")
    public void verifyActiveOrDisabledTabs() {
        orderDetailsPage.clickOnNextButton();
//        personalDataPage.clickOnPersonalDataButton();
//        paymentPage.clickOnPaymentButton();
        softAssert.assertTrue(orderDetailsPage.getOrderDetailsButton().isActive());
//        softAssert.assertTrue(personalDataPage.getPersonalDataButton().isActive(),"Tab 'Personal Data' is not active");
//        softAssert.assertFalse(paymentPage.getPaymentButton().isActive());
    }

    @Test(testName = "GC-2044", description = "GC-2044")
    @Description("Verify if the user is redirected to the next page of the oder form 'Personal data' by clicking on the button 'Next'")
    public void verifyRedirectToNextPage() {
        String personalDataButtonText = orderDetailsPage
                .clickOnNextButton()
                .getPersonalDataButton()
                .getText();
        Assert.assertEquals(personalDataButtonText,"2\nPersonal data");
    }

    @Test(testName = "GC-2047", description = "GC-2047")
    @Description("Verify ordering when fields from first section on the 'Personal data' page filled with valid data")
    public void verifyOrderingWithValidData() {
        orderDetailsPage.clickOnNextButton();
        personalDataPage.inputName("Lina")
                .inputSurname("Serhova")
                .inputPhone("0961111111")
                .inputEmail("dcghkv@gmail.com")
                .clickOnAddAddressButton();

    }
}
