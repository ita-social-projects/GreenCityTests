package com.softserve.edu.greencity.ui.tests.comments;

import com.softserve.edu.greencity.ui.data.User;
import com.softserve.edu.greencity.ui.data.UserRepository;
import com.softserve.edu.greencity.ui.data.econews.NewsDataRepository;
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

    private User getTemporaryUser() {
        return UserRepository.get().temporary();
    }
    private EcoNewsService getEcoNewsService() {
        return new EcoNewsService();
    }

    @BeforeClass
    public void createNews(){

        CommentComponent commentComponent = loadApplication()
                .loginIn(getTemporaryUser())
                .navigateMenuEcoNews()
                .gotoCreateNewsPage()
                .fillFields(NewsDataRepository.get().getNewsWithValidData())
                .publishNews()
                .switchToSingleNewsPageByNumber(0)
                .getCommentPart()
                .addComment("First Comment")
                .chooseCommentByNumber(0)
                .addReply("First reply");
        signOutByStorage();
    }

    @AfterClass
    public void deleteNews() {
        EcoNewsPage ecoNewsPage = loadApplication().navigateMenuEcoNews();
        getEcoNewsService().deleteNewsByTitle(NewsDataRepository.get().getNewsWithValidData().getTitle());
        softAssert.assertFalse(ecoNewsPage.refreshPage().isNewsDisplayedByTitle(NewsDataRepository.get().getNewsWithValidData().getTitle()));
        softAssert.assertAll();
    }

    @Test
    @Description("GC-908")
    public void unloggedUserCannotLikeTheCommentAndReply() {
        logger.info("Verify that unlogged user cannot like the comment/reply on News Single Page starts");

        CommentComponent commentComponent = loadApplication()
                .navigateMenuEcoNews()
                .switchToSingleNewsPageByNumber(0)
                .getCommentPart()
                .chooseCommentByNumber(0);

        softAssert.assertFalse(commentComponent.isLikesButtonDisplayed());
        softAssert.assertFalse(commentComponent.isReplyButtonDisplayed());
        softAssert.assertAll();
    }


    @Test
    @Description("GC-914")
    public void unloggedUserCanReviewReply() {
        logger.info("Verify that not logged user can review the replies to the comment starts");

        boolean isReplyDisplayed = loadApplication()
                .navigateMenuEcoNews()
                .switchToSingleNewsPageByNumber(0)
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
                .switchToSingleNewsPageByNumber(0)
                .getCommentPart()
                .chooseCommentByNumber(0)
                .openReply().isReplyComponentPresent();
        softAssert.assertTrue(isReplyDisplayed);
        softAssert.assertAll();
    }

    @Test
    @Description("GC-920")
    public void unloggedUserCanSeeLikes() {
        logger.info("Verify that unlogged user can see the total likes number related to the comments and/or replies");

        CommentComponent commentComponent = loadApplication()
                .navigateMenuEcoNews()
                .switchToSingleNewsPageByNumber(0)
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
                .switchToSingleNewsPageByNumber(0)
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

        Boolean isReplyComponentPresentLoginUser = loadApplication()
                .loginIn(getTemporaryUser())
                .navigateMenuEcoNews()
                .switchToSingleNewsPageByNumber(0)
                .getCommentPart()
                .chooseCommentByNumber(0)
                .isReplyComponentPresent();
        softAssert.assertFalse(isReplyComponentPresentLoginUser);
        signOutByStorage();

        Boolean isReplyComponentPresentNotLoggedUser = loadApplication()
                .navigateMenuEcoNews()
                .switchToSingleNewsPageByNumber(0)
                .getCommentPart()
                .chooseCommentByNumber(0)
                .isReplyComponentPresent();
        softAssert.assertFalse(isReplyComponentPresentNotLoggedUser);
        softAssert.assertAll();
    }

    @Test
    @Description("GC-826")
    public void unloggedUserCannotDeleteTheCommentAndReply() {
        logger.info("Verify that unregistered user can’t delete comment/reply starts");

        CommentComponent commentComponent = loadApplication()
                .navigateMenuEcoNews()
                .switchToSingleNewsPageByNumber(0)
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
                .switchToSingleNewsPageByNumber(0)
                .getCommentPart()
                .setCommentText(commentText);
        EcoNewsPage ecoNewsPage = new EcoNewsPage(driver);
        String commentAfterLeave = ecoNewsPage.navigateMenuEcoNews()
                .switchToSingleNewsPageByNumber(0)
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
                .switchToSingleNewsPageByNumber(0)
                .getCommentPart()
                .setCommentText(commentText);

        SingleNewsPage singleNewsPage = new SingleNewsPage(driver);
        singleNewsPage.refreshPage();

        softAssert.assertNotEquals(commentText, singleNewsPage.getCommentPart().chooseCommentByNumber(0).getCommentText());
        softAssert.assertAll();
    }

}
