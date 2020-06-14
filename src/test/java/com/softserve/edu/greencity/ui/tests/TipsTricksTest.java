package com.softserve.edu.greencity.ui.tests;

import com.softserve.edu.greencity.ui.data.User;
import com.softserve.edu.greencity.ui.data.UserRepository;
 import com.softserve.edu.greencity.ui.pages.econews.EconewsPage;
import com.softserve.edu.greencity.ui.pages.tipstricks.TipsTricksPage;
import org.testng.Assert;
import org.testng.annotations.*;

public class TipsTricksTest extends GreencityTestRunner {
    @DataProvider
    private Object[] getUser() {
        return new Object[]{
                UserRepository.get().temporary()
        };
    }

    @Test(dataProvider = "getUser")
    public void checkHabitButtonTop(User user) {
        String newHabitButtonText =
                loadApplication()
                        .signin()
                        .successfullyLogin(user)
                        .navigateMenuTipsTricks()
                        .clickStartHabitTop()
                        .getAddNewHabitButton()
                        .getText();

        signOut();

        Assert.assertEquals(newHabitButtonText, "Add new habit");
    }

    @Test
    public void checkQuantityPeople() {
        TipsTricksPage tipstrickspage = loadApplication();
        Assert.assertEquals(tipstrickspage.quantityPeople(), 3);
    }

    @Test
    public void checkQuantityBags() {
        TipsTricksPage tipsTricksPage = loadApplication();
        Assert.assertEquals(tipsTricksPage.quantityBags(), 0);
    }

    @Test
    public void checkQuantityCups() {
        TipsTricksPage tipsTricksPage = loadApplication();
        Assert.assertEquals(tipsTricksPage.quantityCups(), 0);
    }

    @Test
    public void subscribe() throws InterruptedException {
        TipsTricksPage tipsTricksPage = loadApplication();
        tipsTricksPage.clickEmailTipsTricks();
        tipsTricksPage.setEmailTipsTricks("almyyhvxddxxnoczzt@ttirv.com");
        tipsTricksPage.clickSubscribeOnTipsTricks();

        Assert.assertTrue(tipsTricksPage.isDisplayedSubscriptionError());
    }

    @Test
    public void mainEcoNews() {
        TipsTricksPage tipsTricksPage = loadApplication();
        EconewsPage econewsPage = tipsTricksPage.moveMainEcoNewsLink();

        Assert.assertTrue(econewsPage.getGridView().isDisplayed());
    }
}

