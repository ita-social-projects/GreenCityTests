package com.softserve.edu.greencity.ui.tests.comments;

import com.softserve.edu.greencity.data.users.User;
import com.softserve.edu.greencity.data.users.UserRepository;
import com.softserve.edu.greencity.data.econews.NewsData;
import com.softserve.edu.greencity.data.econews.NewsDataRepository;
import com.softserve.edu.greencity.ui.pages.common.CommentComponent;
import com.softserve.edu.greencity.ui.pages.common.CommentPart;
import com.softserve.edu.greencity.ui.pages.econews.EcoNewsPage;
import com.softserve.edu.greencity.ui.pages.econews.SingleNewsPage;
import com.softserve.edu.greencity.ui.tests.runner.GreenCityTestRunner;
import com.softserve.edu.greencity.ui.tools.jdbc.services.EcoNewsService;
import io.qameta.allure.Description;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CheckElementOfCommentTest extends GreenCityTestRunner {
    private NewsData news;

    private User getTemporaryUser() {
        return UserRepository.get().temporary();
    }

    private EcoNewsService getEcoNewsService() {
        return new EcoNewsService();
    }

    @BeforeClass
    public void createNews() {
        news = NewsDataRepository.get().getNewsWithValidData("Check Element Of Comment Test");

        CommentComponent commentComponent = loadApplication()
                .loginIn(getTemporaryUser())
                .navigateMenuEcoNews()
                .gotoCreateNewsPage()
                .fillFields(news)
                .publishNews()
                .switchToSingleNewsPageByParameters(news)
                .getCommentPart()
                .addComment("First Comment")
                .chooseCommentByNumber(0)
                .addReply("First reply");
        signOutByStorage();
    }

    @AfterClass
    public void deleteNews() {
        EcoNewsPage ecoNewsPage = loadApplication().navigateMenuEcoNews();
        getEcoNewsService().deleteNewsByTitle(news.getTitle());
        softAssert.assertFalse(ecoNewsPage.refreshPage().isNewsDisplayedByTitle(news.getTitle()));
        softAssert.assertAll();
    }

    @Test
    @Description("GC-908")
    public void notLoggedInUserCannotLikeTheCommentAndReply() {
        logger.info("Verify that not logged in user cannot like the comment/reply on News Single Page starts");

        CommentComponent commentComponent = loadApplication()
                .navigateMenuEcoNews()
                .switchToSingleNewsPageByParameters(news)
                .getCommentPart()
                .chooseCommentByNumber(0);

        softAssert.assertFalse(commentComponent.isLikesButtonDisplayed());
        softAssert.assertFalse(commentComponent.openReply().chooseReplyByNumber(0).isReplyLikesButtonDisplayed());
        softAssert.assertAll();
    }


    @Test
    @Description("GC-914")
    public void notLoggedInUserCanReviewReply() {
        logger.info("Verify that not logged in user can review the replies to the comment starts");

        boolean isReplyDisplayed = loadApplication()
                .navigateMenuEcoNews()
                .switchToSingleNewsPageByParameters(news)
                .getCommentPart()
                .chooseCommentByNumber(0)
                .openReply().isReplyComponentPresent();
        softAssert.assertTrue(isReplyDisplayed);
        softAssert.assertAll();
    }

    @Test
    @Description("GC-895")
    public void loggedUserCanReviewReply() {
        logger.info("Verify that logged user can review the replies to the comment starts");

        boolean isReplyDisplayed = loadApplication()
                .loginIn(getTemporaryUser())
                .navigateMenuEcoNews()
                .switchToSingleNewsPageByParameters(news)
                .getCommentPart()
                .chooseCommentByNumber(0)
                .openReply().isReplyComponentPresent();
        softAssert.assertTrue(isReplyDisplayed);
        softAssert.assertAll();
    }

    @Test
    @Description("GC-920")
    public void notLoggedInUserCanSeeLikes() {
        logger.info("Verify that not logged in user can see the total likes number related to the comments and/or replies");

        CommentComponent commentComponent = loadApplication()
                .navigateMenuEcoNews()
                .switchToSingleNewsPageByParameters(news)
                .getCommentPart()
                .chooseCommentByNumber(0);
        String commentNumberOfLikes = commentComponent.getLikesNumber();
        softAssert.assertEquals(commentNumberOfLikes, "0");
        String replyNumberOfLikes = commentComponent.openReply().chooseReplyByNumber(0).getReplyLikesNumber();
        softAssert.assertEquals(replyNumberOfLikes, "0");
        softAssert.assertAll();
    }

    @Test
    @Description("GC-917")
    public void loggedUserCanSeeLikes() {
        logger.info("Verify that logged user can see the total likes number related to the comments and/or replies");

        CommentComponent commentComponent = loadApplication()
                .loginIn(getTemporaryUser())
                .navigateMenuEcoNews()
                .switchToSingleNewsPageByParameters(news)
                .getCommentPart()
                .chooseCommentByNumber(0);
        String commentNumberOfLikes = commentComponent.getLikesNumber();
        softAssert.assertEquals(commentNumberOfLikes, "0");
        String replyNumberOfLikes = commentComponent.openReply().chooseReplyByNumber(0).getReplyLikesNumber();
        softAssert.assertEquals(replyNumberOfLikes, "0");
        softAssert.assertAll();
    }

    @Test
    @Description("GC-922")
    public void repliesToTheCommentAreHidden() {
        logger.info("Verify that replies to the comment are hidden by default for all users(logged and not logged)");

        boolean isReplyComponentPresentNotLoggedUser = loadApplication()
                .navigateMenuEcoNews()
                .switchToSingleNewsPageByParameters(news)
                .getCommentPart()
                .chooseCommentByNumber(0)
                .isReplyComponentPresent();
        softAssert.assertFalse(isReplyComponentPresentNotLoggedUser);
        softAssert.assertAll();

        boolean isReplyComponentPresentLoginUser = loadApplication()
                .loginIn(getTemporaryUser())
                .navigateMenuEcoNews()
                .switchToSingleNewsPageByParameters(news)
                .getCommentPart()
                .chooseCommentByNumber(0)
                .isReplyComponentPresent();
        softAssert.assertFalse(isReplyComponentPresentLoginUser);

    }

    @Test
    @Description("GC-826")
    public void unregisteredUserCannotDeleteTheCommentAndReply() {
        logger.info("Verify that unregistered user can’t delete comment/reply starts");

        CommentComponent commentComponent = loadApplication()
                .navigateMenuEcoNews()
                .switchToSingleNewsPageByParameters(news)
                .getCommentPart()
                .chooseCommentByNumber(0);

        softAssert.assertFalse(commentComponent.isDeleteCommentButtonDisplayed());
        softAssert.assertFalse(commentComponent.openReply().chooseReplyByNumber(0).isDeleteReplyButtonDisplayed());
        softAssert.assertAll();
    }

    @Test
    @Description("GC-864")
    public void changesAreNotSavedWhenLeavePage() {
        logger.info("Changes are not saved if logged user leaves the ‘News’ page without pressing ‘Comment’ button starts");
        String commentText = "Test comment";
        CommentPart commentPart = loadApplication()
                .loginIn(getTemporaryUser())
                .navigateMenuEcoNews()
                .switchToSingleNewsPageByParameters(news)
                .getCommentPart()
                .setCommentText(commentText);

        SingleNewsPage singleNewsPage = new SingleNewsPage(driver);
        String commentAfterLeave = singleNewsPage.navigateMenuEcoNews()
                .switchToSingleNewsPageByParameters(news)
                .getCommentPart()
                .chooseCommentByNumber(0).getCommentText();

        softAssert.assertNotEquals(commentPart, commentAfterLeave);
        softAssert.assertAll();
    }

    @Test
    @Description("GC-865")
    public void changesAreNotSavedWhenUpdatePage() {
        logger.info("Changes are not saved if logged user updates the ‘News’ page without pressing ‘Comment’ button starts");
        String commentText = "Test comment";
        CommentPart commentPart = loadApplication()
                .loginIn(getTemporaryUser())
                .navigateMenuEcoNews()
                .switchToSingleNewsPageByParameters(news)
                .getCommentPart()
                .setCommentText(commentText);

        SingleNewsPage singleNewsPage = new SingleNewsPage(driver);
        singleNewsPage.refreshPage();

        softAssert.assertNotEquals(commentText, singleNewsPage.getCommentPart().chooseCommentByNumber(0).getCommentText());
        softAssert.assertAll();
    }

}
