package com.softserve.edu.greencity.ui.tests;


import com.softserve.edu.greencity.ui.data.User;
import com.softserve.edu.greencity.ui.data.UserRepository;
import com.softserve.edu.greencity.ui.pages.cabinet.LoginComponent;
import com.softserve.edu.greencity.ui.pages.cabinet.RegisterComponent;
import com.softserve.edu.greencity.ui.pages.common.TopGuestComponent;
import com.softserve.edu.greencity.ui.tools.CookiesAndStorageHelper;
import com.softserve.edu.greencity.ui.tools.GMailBox;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.Set;

public class Troubled extends GreenCityTestRunner {

    @Test(dataProvider = "userCreds")
    public void testCleanUp(User user){

////GC part
        LoginComponent logComp =  new TopGuestComponent(driver).clickSignInLink();

        logComp.getManualLoginComponent().successfullyLogin(user);

////GMail part

        Set<String> allTabs = driver.getWindowHandles();
        String newlyOpenedTab = openNewTabAndGetId(allTabs);
        driver.switchTo().window(newlyOpenedTab);

        GMailBox logInGMailPage = new GMailBox(driver);
        logInGMailPage.logInGMail();
    }

    public String openNewTabAndGetId(Set<String> allTabs) {

        ((JavascriptExecutor) driver).executeScript("window.open()");

        Set<String> allTabsUpdated = driver.getWindowHandles();

        allTabsUpdated.removeAll(allTabs);

        Iterator iter = allTabsUpdated.iterator();

        String newTab = null;

        if (iter.hasNext()){
            newTab = iter.next().toString();
        }

        System.out.println(newTab);

        return newTab;

    }

    @DataProvider
    public Object[][] userCreds() {
        return new Object[][]{
                {UserRepository.get().defaultUserCredentials()},};
    }


    @AfterMethod
    public void cleanup(){

        CookiesAndStorageHelper help = new CookiesAndStorageHelper(driver);
        help.cleanGreenCityCookiesAndStorages();
        help.cleanGMailCookiesAndStorages();

    }
}





