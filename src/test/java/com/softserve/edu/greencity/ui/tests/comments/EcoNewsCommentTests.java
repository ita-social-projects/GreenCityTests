package com.softserve.edu.greencity.ui.tests.comments;

import com.softserve.edu.greencity.data.users.User;
import com.softserve.edu.greencity.data.users.UserRepository;
import com.softserve.edu.greencity.data.econews.NewsData;
import com.softserve.edu.greencity.data.econews.Tag;
import com.softserve.edu.greencity.ui.pages.common.CommentComponent;
import com.softserve.edu.greencity.ui.pages.econews.SingleNewsPage;
import com.softserve.edu.greencity.ui.tests.runner.GreenCityTestRunnerWithLoginLogout;
import com.softserve.edu.greencity.ui.tools.jdbc.services.EcoNewsService;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class EcoNewsCommentTests extends GreenCityTestRunnerWithLoginLogout {
    private NewsData newsData;

    private User getExistUser() {
        return UserRepository.get().exist();
    }

    @BeforeClass
    public void creatingCommentToNews() {
        String comment = "different news";
        User user = UserRepository.get().temporary();

        newsData = new NewsData(Arrays.asList(Tag.ADS), "Comment, please!",
                "I need a lot of comments! Comment, go on!");

        loadApplication()
                .signIn()
                .getManualLoginComponent()
                .successfullyLogin(user)
                .navigateHeaderEcoNews()
                .gotoCreateNewsPage()
                .fillFields(newsData)
                .publishNews();

        SingleNewsPage page = loadApplication()
                .navigateHeaderEcoNews()
                .refreshPage() //fresh news might not be displayed unless you refresh
                .switchToSingleNewsPageByParameters(newsData);
        page.getCommentPart()
                .addComment(comment);
        page.signOut()
                .navigateHeaderEcoNews()
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
                .navigateHeaderEcoNews()
                .switchToSingleNewsPageByParameters(newsData);
        page.getCommentPart()
                .chooseCommentByNumber(0)
                .addReply(reply);
        page.signOut();

        SingleNewsPage newpage = loadApplication()
                .navigateHeaderEcoNews()
                .switchToSingleNewsPageByParameters(newsData);

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
                .navigateHeaderEcoNews()
                .switchToSingleNewsPageByParameters(newsData);
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
                .navigateHeaderEcoNews()
                .switchToSingleNewsPageByParameters(newsData);
        page.getCommentPart()
                .addComment(comment);

    }

    @Test(testName = "GC-862", description = "GC-862")
    @Description("Unlogged users can not edit their own comments on the 'News' page.")
    public void unloggedUsersCanNotEditComments() {
        User user = UserRepository.get().temporary();
        String comment = "You can't delete this comment because you are logged out";
        SingleNewsPage page = loadApplication()
                .signIn()
                .getManualLoginComponent()
                .successfullyLogin(user)
                .navigateHeaderEcoNews()
                .switchToSingleNewsPageByParameters(newsData);
        page.getCommentPart()
                .addComment(comment);
        page.signOut();
        SingleNewsPage newsPage = loadApplication()
                .navigateHeaderEcoNews()
                .switchToSingleNewsPageByParameters(newsData);
        List<CommentComponent> commentComponents = newsPage.getCommentPart().getCommentComponents();
        for (CommentComponent commentComponent : commentComponents) {
            softAssert.assertFalse(commentComponent.isEditButtonDisplayed());
        }
    }

    @Test(testName = "GC-863", description = "GC-863")
    @Description("Verify that logged user can not edit not him/her comment on the ‘News’ page")
    public void loggedUsersCanNotEditNotHimComment() {
        logger.info("logged user can not edit not him comment on the ‘News’ page");
        CommentComponent commentComponent = loadApplication()
                .loginIn(getExistUser())
                .navigateHeaderEcoNews()
                .switchToSingleNewsPageByParameters(newsData)
                .getCommentPart()
                .chooseCommentByNumber(0);

        softAssert.assertFalse(commentComponent.isEditButtonDisplayed(),"Button shouldn't displayed");
        softAssert.assertAll();
    }

    @Test(testName = "GC-873", description = "GC-873")
    @Description("User who is the author of comment can’t edit reply to her/his comment on the ‘News’ page")
    public void cantEditReplyToHisComment() {
        logger.info("User who is the author of comment can’t edit reply to her/his comment on the ‘News’ page");
        CommentComponent commentComponent = loadApplication()
                .loginIn(getExistUser())
                .navigateHeaderEcoNews()
                .switchToSingleNewsPageByParameters(newsData)
                .getCommentPart()
                .chooseCommentByNumber(0)
                .addReply("reply");
        signOutByStorage();
        User user = UserRepository.get().temporary();
        SingleNewsPage page = loadApplication()
                .signIn()
                .getManualLoginComponent()
                .successfullyLogin(user)
                .navigateHeaderEcoNews()
                .switchToSingleNewsPageByParameters(newsData);
        Assert.assertFalse(page.getCommentPart().chooseCommentByNumber(0).openReply()
                .chooseReplyByNumber(0).isEditReplyButtonDisplayed());
    }
}
