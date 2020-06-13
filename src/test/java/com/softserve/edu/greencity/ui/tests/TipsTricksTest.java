package com.softserve.edu.greencity.ui.tests;

import com.softserve.edu.greencity.ui.data.User;
import com.softserve.edu.greencity.ui.data.UserRepository;
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
    public void text() {
        TipsTricksPage tipstrickspage = loadApplication();
        System.out.println(tipstrickspage.getAmountPeopleText());
        System.out.println("Amount Bags were used: " + tipstrickspage);

    }

    @Test
    public void checkGetNumber() {
        TipsTricksPage tipsTricksPage = loadApplication();
        System.out.println("Amount People: " + tipsTricksPage.quantityPeople());
        System.out.println("Amount Bags were used: " + tipsTricksPage.quantityBags());
        System.out.println("Amount Cups were used: " + tipsTricksPage.quantityCups());
        Assert.assertEquals(tipsTricksPage.quantityPeople(), tipsTricksPage.quantityPeople());
        Assert.assertEquals(tipsTricksPage.quantityBags(), tipsTricksPage.quantityBags());
        Assert.assertEquals(tipsTricksPage.quantityCups(), tipsTricksPage.quantityCups());
    }

    @Parameters({"email"})
    @Test
    public void subscribe(String email) throws InterruptedException {
        TipsTricksPage subscr = loadApplication();
        subscr.clickEmailTipsTricks();
        subscr.setEmailTipsTricks(email);
//     subscr.setEmailTipsTricks("almyyhvxddxxnoczzt@ttirv.com");
        Thread.sleep(1000);
        subscr.clickSubscribeOnTipsTricks();
    }

    @Test
    public void mainEcoNews() {
        TipsTricksPage news = loadApplication();
        news.moveMainEcoNewsLink();

    }
}

