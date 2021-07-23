package com.softserve.edu.greencity.ui.tests.ubscourier;

import com.softserve.edu.greencity.data.users.User;
import com.softserve.edu.greencity.data.users.UserRepository;
import com.softserve.edu.greencity.ui.locators.ubs.AddAddressPopupLocators;
import com.softserve.edu.greencity.ui.pages.ubs.*;
import com.softserve.edu.greencity.ui.tests.runner.GreenCityTestRunner;
import com.softserve.edu.greencity.ui.tools.engine.WaitsSwitcher;
import io.qameta.allure.Description;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.*;

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
        personalDataPage = orderDetailsPage.clickOnNextButton();
        WaitsSwitcher waitsSwitcher = new WaitsSwitcher(driver);
        waitsSwitcher.setExplicitWait(5, ExpectedConditions.visibilityOf(personalDataPage.getOrderDetailsIconDone().getInnerElement()));

        softAssert.assertTrue(personalDataPage.getOrderDetailsIconDone().isDisplayed());
        softAssert.assertTrue(personalDataPage.getPersonalDataButton().isActive(),"Tab 'Personal Data' is not active");
        softAssert.assertFalse(personalDataPage.getPaymentButton().isActive(), "Tab 'Confirmation' is active");
    }

    @Test(testName = "GC-2044", description = "GC-2044")
    @Description("Verify if the user is redirected to the next page of the oder form 'Personal data' by clicking on the button 'Next'")
    public void verifyRedirectToNextPage() {
        String personalDataButtonText = orderDetailsPage
                .clickOnNextButton()
                .getPersonalDataButton()
                .getText();
        Assert.assertEquals("2\nPersonal data", personalDataButtonText);
    }

    //TODO modify after bug fixed/requirements changed
    @Test(testName = "GC-2046", description = "GC-2046") // bug, error message does not appear automatically
    @Description("Verify the error message is shown when 'Personal data' page with empty mandatory fields")
    public void verifyErrorMessageShown() {
        personalDataPage = orderDetailsPage.clickOnPersonalDataButton().clearPersonalDataFields();
        //        personalDataPage.clickOnPaymentButton();
        String expectedNameMessage = "This field is required.";
        String nameMessage = personalDataPage.getErrorNameMessage();
        String expectedSurnameMessage = "This field is required.";
        String surnameMessage = personalDataPage.getErrorSurnameMessage();
        String expectedPhoneMessage = "This field is required.";
        String phoneMessage = personalDataPage.getErrorPhoneMessage();
        String expectedEmailMessage = "This field is required.";
        String emailMessage = personalDataPage.getErrorEmailMessage();

        softAssert.assertEquals(nameMessage, expectedNameMessage);
        softAssert.assertEquals(surnameMessage, expectedSurnameMessage);
        softAssert.assertEquals(phoneMessage, expectedPhoneMessage);
        softAssert.assertEquals(emailMessage, expectedEmailMessage);
    }

    @Test
    public void deleteAddress() {
        personalDataPage = orderDetailsPage.clickOnNextButton();
        personalDataPage.deleteAddressOfIndex(personalDataPage.getQuantityOfAddresses()-1);
    }
}
