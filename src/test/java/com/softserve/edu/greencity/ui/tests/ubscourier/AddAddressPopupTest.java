package com.softserve.edu.greencity.ui.tests.ubscourier;

import com.softserve.edu.greencity.data.users.User;
import com.softserve.edu.greencity.data.users.UserRepository;
import com.softserve.edu.greencity.ui.pages.common.CommentComponent;
import com.softserve.edu.greencity.ui.pages.econews.CreateNewsPage;
import com.softserve.edu.greencity.ui.pages.ubs.OrderDetailsPage;
import com.softserve.edu.greencity.ui.tests.runner.GreenCityTestRunner;
import io.qameta.allure.Description;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AddAddressPopupTest extends GreenCityTestRunner {
    private CreateNewsPage createNewsPage;
    private OrderDetailsPage orderDetailsPage;

    @BeforeClass
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
        logger.info("Verify that logged user can add address");
    }

    @AfterMethod
    public void signOut() {
        createNewsPage.signOut();
    }
}
