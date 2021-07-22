package com.softserve.edu.greencity.ui.tests.ubscourier;

import com.softserve.edu.greencity.data.users.User;
import com.softserve.edu.greencity.data.users.UserRepository;
import com.softserve.edu.greencity.ui.pages.econews.CreateNewsPage;
import com.softserve.edu.greencity.ui.pages.ubs.OrderDetailsPage;
import com.softserve.edu.greencity.ui.tests.runner.GreenCityTestRunnerWithLoginLogout;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class EcoStoreTest extends GreenCityTestRunnerWithLoginLogout {
    private CreateNewsPage createNewsPage;
    private OrderDetailsPage orderDetailsPage;

    @BeforeMethod
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
        orderDetailsPage.signOut();
    }

    @Test(testName = "GC-2049", description = "GC-2049")
    @Description("Verify that the system shows error message if user enters less than 10 numeric characters")
    public void ErrorMassageIfLessThan10NumericTest(){
        logger.info("Input 9 numeric characters");
        orderDetailsPage
                .inputOrderNumber("123456789")
                .clickYesWaitingForAnOrderButton();
        Assert.assertEquals(orderDetailsPage.getTextIncorrectOrderMassage(), "The number was entered incorrectly. Try again.", " Massage doesn't appear" );
    }
}
