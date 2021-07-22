package com.softserve.edu.greencity.ui.tests.ubscourier;

import com.softserve.edu.greencity.data.users.User;
import com.softserve.edu.greencity.data.users.UserRepository;
import com.softserve.edu.greencity.ui.pages.econews.CreateNewsPage;
import com.softserve.edu.greencity.ui.pages.ubs.OrderDetailsPage;
import com.softserve.edu.greencity.ui.tests.runner.GreenCityTestRunnerWithLoginLogout;
import io.qameta.allure.Description;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class EcoStoreTest extends GreenCityTestRunnerWithLoginLogout {
    private CreateNewsPage createNewsPage;
    private OrderDetailsPage orderDetailsPage;

    @BeforeClass
    public void SignIn() {
        User user = UserRepository.get().temporary();
        orderDetailsPage = loadApplication()
                .signIn()
                .getManualLoginComponent()
                .successfullyLogin(user)
                .navigateMenuUBSCourier();
    }

    @AfterMethod
    public void signOut() {
        createNewsPage.signOut();
    }

    @Test(testName = "GC- ", description = "GC- ")
    @Description("Verify adding one order number")
    public void addingOrderNumber(){
        logger.info("Starting adding one order number test.");
    }
}
