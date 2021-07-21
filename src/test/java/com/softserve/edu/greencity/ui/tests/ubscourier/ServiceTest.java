package com.softserve.edu.greencity.ui.tests.ubscourier;

import com.softserve.edu.greencity.data.users.User;
import com.softserve.edu.greencity.data.users.UserRepository;
import com.softserve.edu.greencity.ui.pages.econews.CreateNewsPage;
import com.softserve.edu.greencity.ui.pages.ubs.OrderDetailsPage;
import com.softserve.edu.greencity.ui.tests.runner.GreenCityTestRunner;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class ServiceTest extends GreenCityTestRunner {
    private CreateNewsPage createNewsPage;
    private OrderDetailsPage orderDetailsPage;

    @BeforeMethod
    public void SignIn() {
        SoftAssert softAssert = new SoftAssert();
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

    @Test(testName = "GC-1951 ", description = "GC-1951 ")
    @Description("Verify that a total price of 'Безнадійний одяг' 120 л. label is calculated according to the formula")
    public void OldClothesFormula120LTest(){
        logger.info("Enter 3 in input of 'Безнадійний одяг'");
       orderDetailsPage.enterOnInputNumberOfPackeges(1,"3");
        Assert.assertEquals(orderDetailsPage.getTotalPrice(1),"900 UAH", "Calculating of formula went wrong");
    }

}
