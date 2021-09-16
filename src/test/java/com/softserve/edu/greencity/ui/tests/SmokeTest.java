package com.softserve.edu.greencity.ui.tests;

import com.softserve.edu.greencity.ui.tests.runner.GreenCityTestRunnerWithLoginLogout;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import com.softserve.edu.greencity.data.Languages;
import com.softserve.edu.greencity.data.users.User;
import com.softserve.edu.greencity.data.users.UserRepository;
import com.softserve.edu.greencity.ui.pages.cabinet.MyHabitPage;
import com.softserve.edu.greencity.ui.pages.tipstricks.TipsTricksPage;

public class SmokeTest extends GreenCityTestRunnerWithLoginLogout {
    @DataProvider
    public Object[][] users() {
        return new Object[][]{
                {UserRepository.get().temporary()}
        };
    }

    //TODO Clarify requirements!
    // There is no "start habit" and other buttons on Tips and tricks page. They all are at the main page.
    // That is why this test fails.
    @Ignore
    @Test(dataProvider = "users")
    public void checkElements(final User user) {
        TipsTricksPage tipstrickspage = loadApplication()
                .navigateMenuTipsTricks()
                .switchUaLanguage()
                .navigateMenuEcoNews()
                .navigateMenuTipsTricks()
                .navigateMenuMap()
                .navigateMenuAbout()
                .loginIn(user)
                .navigateMenuTipsTricks();

        tipstrickspage.signOut();

        Assert.assertEquals(tipstrickspage.getLanguageSwitcherText(), Languages.UKRAINIAN.toString());
    }

    @Test(dataProvider = "users")
    public void checkLogin(User user) {
        MyHabitPage myHabitPage = loadApplication()
                .signIn()
                .getManualLoginComponent()
                .successfullyLogin(user);

        String userName = myHabitPage.getTopUserName();

        myHabitPage.signOut();

        Assert.assertEquals(userName, "taqcTestName");
    }

    @Test(dataProvider = "users")
    public void checkCabinet(User user) {
        MyHabitPage myHabitPage = loadApplication()
                .loginIn(user);

        String newHabitButtonText = myHabitPage
                .getAddNewHabitButton()
                .getText();

        myHabitPage.signOut();

        Assert.assertEquals(newHabitButtonText, "Add New Habit");
    }
}
