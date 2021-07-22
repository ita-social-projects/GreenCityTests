package com.softserve.edu.greencity.ui.tests.ubscourier;

import com.softserve.edu.greencity.data.users.User;
import com.softserve.edu.greencity.data.users.UserRepository;
import com.softserve.edu.greencity.ui.locators.ubs.AddAddressPopupLocators;
import com.softserve.edu.greencity.ui.pages.econews.CreateNewsPage;
import com.softserve.edu.greencity.ui.pages.ubs.OrderDetailsPage;
import com.softserve.edu.greencity.ui.pages.ubs.UserAddress;
import com.softserve.edu.greencity.ui.tests.runner.GreenCityTestRunnerWithLoginLogout;
import io.qameta.allure.Description;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddAddressPopupTest extends GreenCityTestRunnerWithLoginLogout {
    private CreateNewsPage createNewsPage;
    private OrderDetailsPage orderDetailsPage;

    @BeforeMethod
    public void signIn() {
        User user = UserRepository.get().temporary();
        orderDetailsPage = loadApplication()
                .signIn()
                .getManualLoginComponent()
                .successfullyLogin(user)
                .navigateMenuUBSCourier();
    }

    @Test(testName = "GC-", description = "GC-")
    @Description("GC-")
    public void addAddress() {
        UserAddress userAddress = new UserAddress(AddAddressPopupLocators.CITY_KIEV,"Sadova", "Kiev", 1, "1", 2);
      //  new AddAddressPopupComponent(driver).inputDistrict(userAddress);
    }

    @AfterMethod
    public void signOut() {
        createNewsPage.signOut();
    }
}
