package com.softserve.edu.greencity.ui.tests.ubsadmin;

import com.softserve.edu.greencity.data.users.User;
import com.softserve.edu.greencity.data.users.UserRepository;
import com.softserve.edu.greencity.ui.pages.ubsadmin.UbsAdminPage.UbsAdminOrders;
import com.softserve.edu.greencity.ui.pages.ubsadmin.UbsAdminPage.UbsAdminRowTableComponent;
import com.softserve.edu.greencity.ui.pages.ubsadmin.UbsAdminPage.UbsAdminTableComponent;
import com.softserve.edu.greencity.ui.tests.runner.GreenCityTestRunnerWithLoginLogout;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class UbsAdminTableSearchTest extends GreenCityTestRunnerWithLoginLogout {
    private User adminUser;

    @BeforeClass
    public void readCredentials() {
        adminUser = UserRepository.get().getAdminUser();
    }

    @BeforeMethod
    public void login() {
         loadApplication()
                .signIn()
                .getManualLoginComponent()
                .successfullyLogin(adminUser);
    }

    /*
    TQ-244
     */
    @Test
    public void testSearchThroughtTable() {
        driver.get("https://ita-social-projects.github.io/GreenCityClient/#/ubs-admin/orders");
                new UbsAdminOrders(driver)
                        .clearSearchField()
                        .setSearchField("1582");

        UbsAdminRowTableComponent row = new UbsAdminTableComponent(driver).getRowById("1582");
        if (row == null)
            Assert.fail();
        else {
            Assert.assertEquals(row.getOrderIdText(), "1582");
        }
    }
}
