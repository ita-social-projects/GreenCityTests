package com.softserve.edu.greencity.ui.tests.comments;

import com.softserve.edu.greencity.data.users.User;
import com.softserve.edu.greencity.data.users.UserRepository;
import com.softserve.edu.greencity.data.econews.NewsData;
import com.softserve.edu.greencity.data.econews.NewsDataRepository;
import com.softserve.edu.greencity.ui.pages.econews.EcoNewsPage;
import com.softserve.edu.greencity.ui.pages.econews.SingleNewsPage;
import com.softserve.edu.greencity.ui.tests.runner.GreenCityTestRunner;
import com.softserve.edu.greencity.ui.tools.jdbc.services.EcoNewsService;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Commentstest extends GreenCityTestRunner {
    private NewsData news;

    @BeforeClass
    public void creatingCommentToNews() {
        news = NewsDataRepository.get().getNewsWithValidData("Comments test");

        String comment = "wow";
        User user = UserRepository.get().temporary();

        SingleNewsPage page = loadApplication()
                .signIn()
                .getManualLoginComponent()
                .successfullyLogin(user)
                .navigateMenuEcoNews()
                .gotoCreateNewsPage()
                .fillFields(news)
                .publishNews()
                .switchToSingleNewsPageByParameters(news);
        page.getCommentPart()
                .addComment(comment);
        page.signOut()
                .navigateMenuEcoNews()
                .switchToSingleNewsPageByParameters(news);
    }

    @AfterClass
    public void deleteNews() {
        EcoNewsPage ecoNewsPage = loadApplication().navigateMenuEcoNews();
        (new EcoNewsService()).deleteNewsByTitle(news.getTitle());
        softAssert.assertFalse(ecoNewsPage.refreshPage().isNewsDisplayedByTitle(news.getTitle()));
        softAssert.assertAll();
    }

    @Test(testName = "GC-672", description = "GC-672")
    @Description("Unregistered users can not use 'Edit’ button")
    public void editIsNotAvailableForUnregisteredUsers() {
        logger.info("Verify that ‘Edit’ button is not available for unregistered User");
        SingleNewsPage page = loadApplication()
                .navigateMenuEcoNews()
                .switchToSingleNewsPageByParameters(news);

        Assert.assertFalse(page.getCommentPart().chooseCommentByNumber(0).isEditButtonDisplayed());
    }
}
