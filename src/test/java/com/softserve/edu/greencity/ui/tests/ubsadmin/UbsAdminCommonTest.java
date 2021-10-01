package com.softserve.edu.greencity.ui.tests.ubsadmin;

import com.softserve.edu.greencity.data.users.User;
import com.softserve.edu.greencity.data.users.UserRepository;
import com.softserve.edu.greencity.ui.tests.runner.GreenCityTestRunnerWithLoginLogout;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class UbsAdminCommonTest extends GreenCityTestRunnerWithLoginLogout {
    User adminUser;

    @BeforeClass
    public void readCredentials() {
        adminUser = UserRepository.get().getAdminUser();
    }
    @BeforeMethod
    public void login() {
        if(isLogInNow()) return;
        loadApplication()
                .loginIn(adminUser);
    }
    @Test(testName = "TQ-226", description = "TQ-226")
    @Description("[UBS-Orders] Verify that 'Search' field has a placeholder 'Search'")
    public void verifySearchPlaceholder(){
        String expectedResult = "Search";
        String actualResult = loadApplication()
                .ubsAdminCommon()
                .getUBSAdminOrders()
                .getSearchField()
                .getAttribute("placeholder");
        Assert.assertEquals(expectedResult, actualResult);
    }
    @Test(testName = "TQ-225", description = "TQ-225")
    @Description("[Ubs-orders] Verify that 'Search' field gives the result you searched for.")
    public void verifySearchBySearchField(){
        String orderId = loadApplication()
                .ubsAdminCommon()
                .getUBSAdminOrders()
                .clearSearchField()
                .setSearchField("1667")
                .getTableRow()
                .getOrderIdText();
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(orderId, "1667");
    }
    @Test(testName = "TQ-342", description = "")
    public void verifyAscSortingOfColumnEmail(){
        List<String> listOfEmailsFromSite = loadApplication()
                .ubsAdminCommon()
                .getUBSAdminOrders()
                .clickSortOrderEmailButton()
                .getUbsAdminTableComponent()
                .getColumnEmail();

    }

}
