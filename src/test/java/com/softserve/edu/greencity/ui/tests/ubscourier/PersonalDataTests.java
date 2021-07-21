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
        softAssert.assertFalse(personalDataPage.getPaymentButton().isActive());
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

    @Test(testName = "GC-2047", description = "GC-2047")
    @Description("Verify ordering when fields from first section on the 'Personal data' page filled with valid data")
    public void verifyOrderingWithValidData() {
        PersonalDataPage personalDataPage = orderDetailsPage.clickOnNextButton();
        personalDataPage.inputName("Lina")
                .inputSurname("Serhova")
                .inputPhone("0961111111")
                .inputEmail("dcghkv@gmail.com");
        AddAddressPopupComponent addAddressPopupComponent = personalDataPage.clickOnAddAddressButton();
                addAddressPopupComponent.fillAllFields(
                new UserAddress(AddAddressPopupLocators.CITY_KIEV, "Sadova", "Kiev", 2, "3", 4))
                .clickOnAddAddressButton();
        personalDataPage.clickOnNextButton();
        String paymentButtonText = paymentPage.getPaymentButton().getText();
        Assert.assertEquals("3\nConfirmation", paymentButtonText);
    }

    @Test(testName = "GC-2042", description = "GC-2042")
    @Description("Verify if the system save the order after interrupt the order")
    public void verifySaveOrderAfterInterrupt() {
        PersonalDataPage personalDataPage = orderDetailsPage.clickOnPersonalDataButton();
        personalDataPage.inputName("Lina")
                .inputSurname("Serhova")
                .inputPhone("0961111111")
                .inputEmail("dcghkv@gmail.com");
        AddAddressPopupComponent addAddressPopupComponent = personalDataPage.clickOnAddAddressButton();
        addAddressPopupComponent.fillAllFields(
                new UserAddress(AddAddressPopupLocators.CITY_KIEV, "Sadova", "Kiev", 2, "3", 4))
                .clickOnAddAddressButton();
        personalDataPage.clickOnCancelButton().clickCancelOrderButton();
    }

    @Test(testName = "GC-2041", description = "GC-2041")
    @Description("Verify if the system erase all entered user data after interrupt the order")
    public void verifyEraseDataAfterInterrupt() {
        PersonalDataPage personalDataPage = orderDetailsPage.clickOnPersonalDataButton();
        personalDataPage.inputName("Lina")
                .inputSurname("Serhova")
                .inputPhone("0961111111")
                .inputEmail("dcghkv@gmail.com");
        AddAddressPopupComponent addAddressPopupComponent = personalDataPage.clickOnAddAddressButton();
        addAddressPopupComponent.fillAllFields(
                new UserAddress(AddAddressPopupLocators.CITY_KIEV, "Sadova", "Kiev", 2, "3", 4))
                .clickOnAddAddressButton();
    }

    @Test
    public void deleteAddress() {
        personalDataPage = orderDetailsPage.clickOnNextButton();
        personalDataPage.deleteAddressOfIndex(personalDataPage.getQuantityOfAddresses()-1);
    }
}
