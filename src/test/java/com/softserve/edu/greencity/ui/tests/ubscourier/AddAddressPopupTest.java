package com.softserve.edu.greencity.ui.tests.ubscourier;

import com.softserve.edu.greencity.data.users.User;
import com.softserve.edu.greencity.data.users.UserRepository;
import com.softserve.edu.greencity.ui.pages.ubs.AddAddressPopupComponent;
import com.softserve.edu.greencity.ui.pages.ubs.OrderDetailsPage;
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
    @Description("Verify that add address pop-up window shall display with the message “At the moment we serve only the city of Kiev”")
    public void checkCityMessageInfo() {
        final String originalInfo = "At the moment we serve only the city of Kiev";
        logger.info("check that AddAddressPopupMenu is active");
        orderDetailsPage
                .getServicesComponents()
                .get(0)
                .getInput()
                .sendKeys("10");

        String cityMessageInfo = orderDetailsPage
                .clickOnNextButton()
                .clickOnAddAddressButton()
                .getCityMessageInfo()
                .getText();
        addAddressPopupComponent = new AddAddressPopupComponent(driver);
        addAddressPopupComponent.clickOnCancelButton();

        softAssert.assertEquals(cityMessageInfo, originalInfo);
    }
}