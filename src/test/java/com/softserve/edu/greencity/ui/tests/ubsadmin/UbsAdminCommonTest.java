package com.softserve.edu.greencity.ui.tests.ubsadmin;

import com.softserve.edu.greencity.data.users.User;
import com.softserve.edu.greencity.data.users.UserRepository;
import com.softserve.edu.greencity.ui.pages.ubsadmin.UbsAdminPage.UBSAdminMenuCommon;
import com.softserve.edu.greencity.ui.pages.ubsadmin.UbsAdminPage.UbsAdminOrders;
import com.softserve.edu.greencity.ui.tests.runner.GreenCityTestRunnerWithLoginLogout;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class UbsAdminCommonTest extends GreenCityTestRunnerWithLoginLogout {
    User defaultUser;

    @BeforeClass
    public void readCredentials() {
        defaultUser = UserRepository.get().getAdminUser();
    }
    @BeforeMethod
    public void login() {
        if(isLogInNow()) return;
        loadApplication()
                .loginIn(defaultUser);
    }
    @Test
    public void verifySearchPlaceholder(){
        String expectedResult = "Search";
        String actualResult = loadApplication()
                .ubsAdminCommon()
                .getUBSAdminOrders()
                .getSearchField()
                .getAttribute("placeholder");
        Assert.assertEquals(expectedResult, actualResult);
    }
    @Test
    public void verifySearchBySearchField(){
        String orderId = loadApplication()
                .ubsAdminCommon()
                .getUBSAdminOrders()
                .clearSearchField()
                .setSearchField("1667").
    }

}
