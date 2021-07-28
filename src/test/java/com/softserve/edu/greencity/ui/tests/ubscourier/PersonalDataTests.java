package com.softserve.edu.greencity.ui.tests.ubscourier;

import com.softserve.edu.greencity.data.UBS.UBSDataStrings;
import com.softserve.edu.greencity.data.users.User;
import com.softserve.edu.greencity.data.users.UserRepository;
import com.softserve.edu.greencity.ui.locators.ubs.AddAddressPopupLocators;
import com.softserve.edu.greencity.ui.pages.ubs.*;
import com.softserve.edu.greencity.ui.tests.runner.GreenCityTestRunnerWithLoginLogout;
import com.softserve.edu.greencity.ui.tools.engine.WaitsSwitcher;
import io.qameta.allure.Description;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.*;

public class PersonalDataTests extends GreenCityTestRunnerWithLoginLogout {

    private OrderDetailsPage orderDetailsPage;
    private PersonalDataPage personalDataPage;

    @BeforeMethod
    public void login() {
        User user = UserRepository.get().temporary();
        orderDetailsPage = loadApplication()
                .signIn()
                .getManualLoginComponent()
                .successfullyLogin(user)
                .navigateMenuUBSCourier();
        orderDetailsPage.fillOldClothes20L("20");
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

    @Test(testName = "GC-2046", description = "GC-2046")
    @Description("Verify the error message is shown when 'Personal data' page with empty mandatory fields")
    public void verifyErrorMessageShown() {
        personalDataPage = orderDetailsPage.clickOnPersonalDataButton().clearPersonalDataFields();
        personalDataPage.clickOnPaymentButton();
        String nameMessage = personalDataPage.getErrorNameMessage();
        String surnameMessage = personalDataPage.getErrorSurnameMessage();
        String phoneMessage = personalDataPage.getErrorPhoneMessage();
        String emailMessage = personalDataPage.getErrorEmailMessage();

        softAssert.assertEquals(nameMessage, UBSDataStrings.PERSONAL_DATA_WARNING_MESSAGE, "Message mismatch");
        softAssert.assertEquals(surnameMessage, UBSDataStrings.PERSONAL_DATA_WARNING_MESSAGE, "Message mismatch");
        softAssert.assertEquals(phoneMessage, UBSDataStrings.PERSONAL_DATA_WARNING_MESSAGE, "Message mismatch");
        softAssert.assertEquals(emailMessage, UBSDataStrings.PERSONAL_DATA_WARNING_MESSAGE, "Message mismatch");
    }
}
