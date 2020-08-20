package com.softserve.edu.greencity.ui.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.softserve.edu.greencity.ui.data.Languages;
import com.softserve.edu.greencity.ui.data.User;
import com.softserve.edu.greencity.ui.data.UserRepository;
import com.softserve.edu.greencity.ui.pages.cabinet.MyCabinetPage;
import com.softserve.edu.greencity.ui.pages.tipstricks.TipsTricksPage;

public class SmokeTest extends GreenCityTestRunner {
    @DataProvider
    public Object[][] users() {
        return new Object[][]{
                {UserRepository.get().temporary()}
        };
    }

    @Test(dataProvider = "users")
    public void checkElements(final User user) {
        TipsTricksPage tipstrickspage = loadApplication()
                .navigateMenuTipsTricks()
                .switchLanguage(Languages.UKRAINIAN)
                .navigateMenuEconews()
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
        MyCabinetPage myCabinetPage = loadApplication()
                .signIn()
                .getManualLoginComponent()
                .successfullyLogin(user);

        String userName = myCabinetPage.getTopUserName();

        myCabinetPage.signOut();

        Assert.assertEquals(userName, "Taras Malynovskyi");
    }

    @Test(dataProvider = "users")
    public void checkCabinet(User user) {
        MyCabinetPage myCabinetPage = loadApplication()
                .loginIn(user);

        String newHabitButtonText = myCabinetPage
                .getAddNewHabitButton()
                .getText();

        myCabinetPage.signOut();

        Assert.assertEquals(newHabitButtonText, "Add new habit");
    }
}
