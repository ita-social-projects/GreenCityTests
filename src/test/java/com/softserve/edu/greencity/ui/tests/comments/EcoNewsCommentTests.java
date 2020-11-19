package com.softserve.edu.greencity.ui.tests.comments;

import com.softserve.edu.greencity.ui.data.User;
import com.softserve.edu.greencity.ui.data.UserRepository;
import com.softserve.edu.greencity.ui.data.econews.NewsData;
import com.softserve.edu.greencity.ui.data.econews.Tag;
import com.softserve.edu.greencity.ui.pages.econews.SingleNewsPage;
import com.softserve.edu.greencity.ui.tests.runner.GreenCityTestRunner;
import com.softserve.edu.greencity.ui.tools.jdbc.services.EcoNewsService;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Arrays;

public class EcoNewsCommentTests extends GreenCityTestRunner {
    private NewsData newsData;

    @BeforeClass
    public void creatingCommentToNews() {
        String comment = "different news";
        User user = UserRepository.get().temporary();

        newsData = new NewsData(Arrays.asList(new Tag[]{Tag.ADS}), "Comment, please!",
                "I need a lot of comments! Comment, go on!");

        loadApplication()
                .signIn()
                .getManualLoginComponent()
                .successfullyLogin(user)
                .navigateMenuEcoNews()
                .gotoCreateNewsPage()
                .fillFields(newsData)
                .clickPublishButton();

        SingleNewsPage page = loadApplication()
                .navigateMenuEcoNews()
                .refreshPage() //fresh news might not be displayed unless you refresh
                .switchToSingleNewsPageByParameters(newsData);
        page.getCommentPart()
                .addComment(comment);
        page.signOut()
                .navigateMenuEcoNews()
                .switchToSingleNewsPageByParameters(newsData);
    }

    @AfterClass
    public void clearUp() {
        EcoNewsService ecoNewsService = new EcoNewsService();
        ecoNewsService.deleteNewsByTitle(newsData.getTitle());
    }

    @Test(testName = "GC-872", description = "GC-872")
    @Description("Unregistered users can’t edit reply on the News page")
    public void unregisteredUsersCanNotEditReply() {
        logger.info("Verify that unregistered users can’t edit reply on the News page");
        String reply = "hello,sweetiee";
        User user = UserRepository.get().temporary();
        SingleNewsPage page = loadApplication()
                .signIn()
                .getManualLoginComponent()
                .successfullyLogin(user)
                .navigateMenuEcoNews()
                .switchToSingleNewsPageByNumber(0);
        page.getCommentPart()
                .chooseCommentByNumber(0)
                .addReply(reply);
        page.signOut();

        SingleNewsPage newpage = loadApplication()
                .navigateMenuEcoNews()
                .switchToSingleNewsPageByNumber(0);

        Assert.assertFalse(newpage.getCommentPart().chooseCommentByNumber(0).openReply()
                .chooseReplyByNumber(0).isEditReplyButtonDisplayed());
    }

    @Test(testName = "GC-871", description = "GC-871")
    @Description("Logged users can edit their own replies on the 'News' page.")
    public void loggedUsersCanEditTheirReply() {
        logger.info("Verify that logged users can edit their own reply on the News page");
        String reply = "hello,sweetiee";
        User user = UserRepository.get().temporary();
        SingleNewsPage page = loadApplication()
                .signIn()
                .getManualLoginComponent()
                .successfullyLogin(user)
                .navigateMenuEcoNews()
                .switchToSingleNewsPageByNumber(0);
        page.getCommentPart()
                .chooseCommentByNumber(0)
                .addReply(reply)
                .openReply()
                .chooseReplyByNumber(0)
                .clickReplyEditButton();
    }

    @Test(testName = "GC-861", description = "GC-861")
    @Description("Logged users can edit their own comments on the 'News' page.")
    public void loggedUsersCanEditOwnComments() {
        User user = UserRepository.get().temporary();
        String comment = "different news";
        SingleNewsPage page = loadApplication()
                .signIn()
                .getManualLoginComponent()
                .successfullyLogin(user)
                .navigateMenuEcoNews()
                .switchToSingleNewsPageByNumber(0);
        page.getCommentPart()
                .addComment(comment);

    }

    @Test(testName = "GC-862", description = "GC-862")
    @Description("Unlogged users can not edit their own comments on the 'News' page.")
    public void unloggedUsersCanNotEditComments() {
        throw new SkipException("This test is not implemented!");
    }
}
