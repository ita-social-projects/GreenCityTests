package com.softserve.edu.greencity.ui.tests.viewsinglenews;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.softserve.edu.greencity.ui.data.User;
import com.softserve.edu.greencity.ui.data.UserRepository;
import com.softserve.edu.greencity.ui.data.econews.NewsDataRepository;
import com.softserve.edu.greencity.ui.pages.common.CommentComponent;
import com.softserve.edu.greencity.ui.pages.common.CommentContainer;
import com.softserve.edu.greencity.ui.pages.common.ReplyComponent;
import com.softserve.edu.greencity.ui.pages.econews.EcoNewsPage;
import com.softserve.edu.greencity.ui.pages.econews.SingleNewsPage;
import com.softserve.edu.greencity.ui.tests.runner.GreenCityTestRunner;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CommentTest extends GreenCityTestRunner {

    private User getTemporaryUser() {
        return UserRepository.get().temporary();
    }

    @Test
    @Description("Get comment text")
    public void returningToNewsViaBackToNews() {
        logger.info("Starting returningToNewsViaBackToNews");

        SingleNewsPage singleNewsPage = loadApplication()
                .navigateMenuEcoNews()
                .switchToSingleNewsPageByNumber(8);
        String commentText = singleNewsPage.getCommentContainer().chooseCommentByNumber(0).getCommentText();
        Assert.assertEquals("comments", commentText);
    }

    @Test
    @Description("Get reply text")
    public void commentReplyTestNumber() {
        logger.info("commentReplyTest");

        CommentComponent commentComponent = loadApplication()
                .loginIn(getTemporaryUser())
                .navigateMenuEcoNews()
                .switchToSingleNewsPageByNumber(0)
                .getCommentContainer().chooseCommentByNumber(0).clickReplyButton()
                .setReplyText("test reply 2").clickAddReplyButton();
    }

    @Test
    @Description("publishComment")
    public void publishComment() {
        logger.info("publishComment");
        String setText = "Set comment test";
        CommentContainer commentContainer = loadApplication()
                .loginIn(getTemporaryUser())
                .navigateMenuEcoNews()
                .switchToSingleNewsPageByNumber(0)
                .getCommentContainer()
                .setCommentText(setText).clickCommentButton();
        String commentText = commentContainer.chooseCommentByNumber(0).getCommentText();
        softAssert.assertEquals(setText, commentText);
    }


    @Test
    @Description("Test")
    public void commentReplyTest() {
        logger.info("commentReplyTest");

        String replyText = loadApplication()
                .loginIn(getTemporaryUser())
                .navigateMenuEcoNews()
                .switchToSingleNewsPageByNumber(0)
                .getCommentContainer().chooseCommentByNumber(0)
                .clickShowReplyButton().chooseReplyByNumber(0).getCommentText();
        Assert.assertEquals(replyText, "test");
    }

    @Test
    @Description("GC-908")
    public void unloggedUserCannotLikeTheCommentAndReply() {
        logger.info("Verify that unlogged user cannot like the comment/reply on News Single Page starts");

        CommentComponent commentComponent = loadApplication()
                .navigateMenuEcoNews()
                .switchToSingleNewsPageByNumber(1).getCommentContainer()
                .chooseCommentByNumber(1);

        softAssert.assertFalse(commentComponent.isLikesButtonDisplayed());
        softAssert.assertFalse(commentComponent.isReplyButtonDisplayed());
        softAssert.assertAll();
    }

    @Test
    @Description("GC-test")
    public void testComment() {
        logger.info("Starts");

        CommentComponent commentComponent = loadApplication()
                .navigateMenuEcoNews()
                .switchToSingleNewsPageByNumber(1).getCommentContainer()
                .chooseCommentByText("Test Comment");

        softAssert.assertTrue(commentComponent.getCommentText().equals("Test Comment"));
        softAssert.assertAll();
    }

    @Test
    @Description("GC-817")
    public void loggedUserCanAddComment() {
        logger.info("Verify that logged user can add comment starts");
        String commentText = "First comment";
        CommentContainer commentContainer = loadApplication()
                .loginIn(getTemporaryUser())
                .navigateMenuEcoNews()
                .switchToSingleNewsPageByNumber(0)
                .getCommentContainer()
                .addComment(commentText);

        softAssert.assertEquals(commentText, commentContainer.chooseCommentByNumber(0).getCommentText());
    }

    @Test
    @Description("GC-958")
    public void loggedUserCanPublishReply() {
        logger.info("Verify that logged user can publish reply starts");

        String replyText = "First reply";
        CommentComponent commentComponent = loadApplication()
                .navigateMenuEcoNews()
                .switchToSingleNewsPageByNumber(1)
                .getCommentContainer()
                .chooseCommentByNumber(0)
                .clickAddReplyButton()
                .setReplyText(replyText).clickAddReplyButton();

        String actualReplyText = commentComponent.chooseCommentByNumber(0).clickShowReplyButton().chooseReplyByNumber(0).getReplyField().getText();
        softAssert.assertEquals(replyText, actualReplyText);

    }

}
