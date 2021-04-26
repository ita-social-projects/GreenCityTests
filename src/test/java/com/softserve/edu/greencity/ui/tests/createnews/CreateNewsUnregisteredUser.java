package com.softserve.edu.greencity.ui.tests.createnews;

import com.softserve.edu.greencity.data.users.User;
import com.softserve.edu.greencity.data.users.UserRepository;
import com.softserve.edu.greencity.ui.pages.econews.EcoNewsPage;
import com.softserve.edu.greencity.ui.tests.runner.GreenCityTestRunner;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CreateNewsUnregisteredUser extends GreenCityTestRunner {
    private User getTemporaryUser() {
        return UserRepository.get().temporary();
    }

    @Test(testName = "GC-591", description = "GC-591")
    @Description("Verify that create news button is visible for registered user")
    public void checkVisibilityOfCreateNewsButtonForRegisteredUser() {
        logger.info("checkVisibilityOfCreateNewsButtonForRegisteredUser starts");

        EcoNewsPage econewsPage = loadApplication()
                .loginIn(getTemporaryUser())
                .navigateMenuEcoNews();

        Assert.assertTrue(econewsPage.isCreateNewsButtonDisplayed());

        econewsPage.signOut();
    }
}
