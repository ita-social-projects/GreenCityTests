package com.softserve.edu.greencity.ui.tests.ubscourier;

import com.softserve.edu.greencity.data.UBS.UBSDataStrings;
import com.softserve.edu.greencity.data.users.User;
import com.softserve.edu.greencity.data.users.UserRepository;
import com.softserve.edu.greencity.ui.pages.ubs.AddAddressPopupComponent;
import com.softserve.edu.greencity.ui.pages.ubs.OrderDetailsPage;
import com.softserve.edu.greencity.ui.pages.ubs.UserAddress;
import com.softserve.edu.greencity.ui.tests.runner.GreenCityTestRunner;
import io.qameta.allure.Description;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddAddressPopupTest extends GreenCityTestRunner {
    private OrderDetailsPage orderDetailsPage;
    private AddAddressPopupComponent addAddressPopupComponent;

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
    public void signOut() {
        orderDetailsPage.signOut();
    }

    @Test(testName = "GC-1925", description = "GC-1925")
    @Description("GC-1925")
    public void checkCityMessageInfo() {
        logger.info("Verify that add address pop-up window shall display with the message “At the moment we serve only the city of Kiev”");
        orderDetailsPage
                .getServicesComponents()
                .get(0)
                .getInput()
                .sendKeys("10");

        orderDetailsPage
                .clickOnNextButton()
                .clickOnAddAddressButton()
                .getCityMessageInfo()
                .getText();
        addAddressPopupComponent = new AddAddressPopupComponent(driver);
        addAddressPopupComponent.clickOnCancelButton();

        String info = addAddressPopupComponent.getCityValidationTextInfo();
        softAssert.assertEquals(UBSDataStrings.ADDRESS_CITY_INFO_MESSAGE.getMessage(), info, "At the moment we serve only the city of Kiev");
    }

    @Test(testName = "GC-1925", description = "GC-1925")
    @Description("GC-1925")
    public void checkStreetMessageInfo() {
        logger.info("Verify that the window will be closed and all the data on the pop-up shall be erased, when the user clicks on the button “Відмінити” on address pop-up menu");
        orderDetailsPage
                .getServicesComponents()
                .get(0)
                .getInput()
                .sendKeys("10");


    }
}