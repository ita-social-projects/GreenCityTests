package com.softserve.edu.greencity.ui.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.softserve.edu.greencity.ui.data.Languages;
import com.softserve.edu.greencity.ui.data.User;
import com.softserve.edu.greencity.ui.data.UserRepository;
import com.softserve.edu.greencity.ui.pages.cabinet.LoginPage;
import com.softserve.edu.greencity.ui.pages.cabinet.MyCabinetPage;
import com.softserve.edu.greencity.ui.pages.common.TopPart;
import com.softserve.edu.greencity.ui.pages.tipstricks.TipsTricksPage;

public class SmokeTest extends GreencityTestRunner {

    @DataProvider
    public Object[][] languages() {
        return new Object[][] {
            //{ SearchItemRepository.getMacItem(), SearchRefineRepository.getPriceDescUsd() },
            { Languages.UKRAINIAN }
            };
    }

    //@Test(dataProvider = "languages")
    public void checkElements(Languages languages) {
        // Steps
        TipsTricksPage tipstrickspage = loadApplication()
                .switchLanguage(languages);
        presentationSleep();
        //
        /*-
        System.out.println("is menu Home text: " 
                + homepage.getMainMenuDropdown().getMenuHomeText());
        //
        System.out.println("is menu Home: " 
                + homepage.getMainMenuDropdown().isDisplayedMenuHome());
        System.out.println("is menu EcoNews: " 
                + homepage.getMainMenuDropdown().isDisplayedMenuEcoNews());
        System.out.println("is menu NaviconButton: " 
                + homepage.getMainMenuDropdown().isDisplayedNaviconButton());
        */
        //
        tipstrickspage = tipstrickspage
                .navigateMenuEconews()
                .navigateMenuTipsTricks()
                .navigateMenuMap()
                .navigateMenuMyCabinet()
                .navigateMenuAbout()
                .navigateMenuTipsTricks();
        //
//      TopPart tp = tipstrickspage;
//      tp = tp.navigateMenuEconews();
//      presentationSleep(2);
//      tp = tp.navigateMenuTipsTricks();
//      presentationSleep(2);
//      tp = tp.navigateMenuMyCabinet();
//      presentationSleep(2);
//      tp = tp.navigateMenuMap();
        //
        // Check
//      Assert.assertEquals(tipstrickspage.getLanguageSwitcherText(),
//              Languages.UKRAINIAN.toString());
        //
        // Return to Previous State
        presentationSleep();
    }
    
    @DataProvider
    public Object[][] users() {
        return new Object[][] {
            { UserRepository.get().temporary() }
            };
    }
    
    //@Test(dataProvider = "users")
    public void checkLogin(User user) {
        MyCabinetPage myCabinetPage = loadApplication()
                .signin()
                .successfullyLogin(user);
        System.out.println("name = " + myCabinetPage.getTopUserName());
        Assert.assertEquals(myCabinetPage.getTopUserName(),
                TopPart.PROFILE_NAME);
                //user.getFirstname());
        myCabinetPage.signout();
    }

//    @Test(dataProvider = "users")
    public void checkCabinet(User user) {
        MyCabinetPage myCabinetPage = loadApplication()
                .navigateMenuMyCabinet(user);
        presentationSleep();
        //
        Assert.assertEquals(myCabinetPage.getTopUserName(), TopPart.PROFILE_NAME);
        // user.getFirstname());
        presentationSleep();
        //
        myCabinetPage = myCabinetPage
                .navigateMenuTipsTricks()
                .navigateMenuEconews()
                .navigateMenuMyCabinet();
        presentationSleep();
        //
        TipsTricksPage tipstrickspage = myCabinetPage
                .signout();
        presentationSleep();
        LoginPage loginPage = tipstrickspage
                .navigateMenuMyCabinetGuest();
        presentationSleep(4);
    }
        
}
