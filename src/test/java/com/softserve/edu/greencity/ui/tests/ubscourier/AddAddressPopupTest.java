package com.softserve.edu.greencity.ui.tests.ubscourier;

import com.softserve.edu.greencity.data.users.User;
import com.softserve.edu.greencity.data.users.UserRepository;
import com.softserve.edu.greencity.ui.locators.ubs.AddAddressPopupLocators;
import com.softserve.edu.greencity.ui.pages.econews.CreateNewsPage;
import com.softserve.edu.greencity.ui.pages.ubs.AddAddressPopupComponent;
import com.softserve.edu.greencity.ui.pages.ubs.OrderDetailsPage;
import com.softserve.edu.greencity.ui.pages.ubs.PersonalDataPage;
import com.softserve.edu.greencity.ui.pages.ubs.UserAddress;
import com.softserve.edu.greencity.ui.tests.runner.GreenCityTestRunner;
import io.qameta.allure.Description;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.softserve.edu.greencity.ui.tests.createnews.CreateNewsTexts.CREATE_NEWS_TITLE;

public class AddAddressPopupTest extends GreenCityTestRunner {
    private CreateNewsPage createNewsPage;
    private PersonalDataPage personalDataPage;
    private OrderDetailsPage orderDetailsPage;
    private AddAddressPopupComponent addAddressPopupComponent;


    @BeforeMethod
    public void login() {
        User user = UserRepository.get().temporary();
        orderDetailsPage = loadApplication()
                .signIn()
                .getManualLoginComponent()
                .successfullyLogin(user)
                .navigateMenuUBSCourier();
    }

    @AfterMethod
    public void signOut() {
        personalDataPage.signOut();
    }

    @Test(testName = "GC-1925", description = "GC-1925")
    @Description("Verify that add address pop-up window shall display with the message “At the moment we serve only the city of Kiev”")
    public void addAddress() {
        logger.info("check that AddAddressPopupMenu is active");

        orderDetailsPage.getNumberOfPackeges().get(1).click();
        orderDetailsPage.getNumberOfPackeges().get(1).sendKeys("10");
        personalDataPage.clickOnAddAddressButton().clickOnCityInput().chooseCity(AddAddressPopupLocators.CITY_KIEV);
        softAssert.assertTrue(addAddressPopupComponent.isDisplayedCityMessageInfo());
        softAssert.assertEquals("At the moment we serve only the city of Kyiv.",addAddressPopupComponent.getCityMessageInfo().getText());
        softAssert.assertAll();
    }
}
