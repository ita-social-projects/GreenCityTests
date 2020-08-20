package com.softserve.edu.greencity.ui.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.softserve.edu.greencity.ui.data.User;
import com.softserve.edu.greencity.ui.data.UserRepository;
import com.softserve.edu.greencity.ui.pages.cabinet.MyCabinetPage;
import com.softserve.edu.greencity.ui.pages.econews.EconewsPage;
import com.softserve.edu.greencity.ui.pages.tipstricks.TipsTricksPage;

public class TipsTricksTest extends GreenCityTestRunner {
    @DataProvider
    private Object[] getUser() {
        return new Object[]{
                UserRepository.get().temporary()
        };
    }

    @Test(dataProvider = "getUser", invocationCount = 10)
    public void checkHabitButtonTop(User user) {
        MyCabinetPage myCabinetPage = loadApplication()
                .loginIn(user)
                .navigateMenuTipsTricks()
                .clickStartHabitTop();

        String newHabitButtonText = myCabinetPage
                .getAddNewHabitButton()
                .getText();

        myCabinetPage.signOut();

        Assert.assertEquals(newHabitButtonText, "Add new habit");
    }

    @Test
    public void checkQuantityPeople() {
        TipsTricksPage tipstrickspage = loadApplication()
                .navigateMenuTipsTricks();
        Assert.assertEquals(tipstrickspage.quantityPeople(), 264);
    }

    @Test
    public void checkQuantityBags() {
        TipsTricksPage tipsTricksPage = loadApplication()
                .navigateMenuTipsTricks();
        Assert.assertEquals(tipsTricksPage.quantityBags(), 0);
    }

    @Test
    public void checkQuantityCups() {
        TipsTricksPage tipsTricksPage = loadApplication()
                .navigateMenuTipsTricks();
        Assert.assertEquals(tipsTricksPage.quantityCups(), 0);
    }

    @Test
    public void subscribeError() {
        TipsTricksPage tipsTricksPage = loadApplication()
                .navigateMenuTipsTricks();
        tipsTricksPage.clickEmailTipsTricks();
        tipsTricksPage.setEmailTipsTricks("almyyhvxddxxnoczzt@ttirv.com");
        tipsTricksPage.clickSubscribeOnTipsTricks();

        Assert.assertTrue(tipsTricksPage.isSubscriptionErrorDisplayed());
    }

    @Test
    public void mainEcoNews() {
        TipsTricksPage tipsTricksPage = loadApplication()
                .navigateMenuTipsTricks();
        EconewsPage econewsPage = tipsTricksPage.moveMainEcoNewsLink();

        Assert.assertTrue(econewsPage.getGridView().isDisplayed());
    }
}