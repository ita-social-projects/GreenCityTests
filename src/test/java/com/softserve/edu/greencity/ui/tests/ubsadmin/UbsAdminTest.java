package com.softserve.edu.greencity.ui.tests.ubsadmin;

import com.softserve.edu.greencity.data.users.User;
import com.softserve.edu.greencity.data.users.UserRepository;
import com.softserve.edu.greencity.ui.pages.econews.EcoNewsPage;
import com.softserve.edu.greencity.ui.pages.ubsadmin.UbsAdminPage.UbsAdminCommon;
import com.softserve.edu.greencity.ui.tests.runner.GreenCityTestRunnerWithLoginLogout;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class UbsAdminTest extends GreenCityTestRunnerWithLoginLogout {
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
    public void test(){
        UbsAdminCommon ubsAdminCommon = loadApplication().ubsAdminCommon();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
