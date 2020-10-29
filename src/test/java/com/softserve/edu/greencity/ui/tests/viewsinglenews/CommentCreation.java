package com.softserve.edu.greencity.ui.tests.viewsinglenews;

import com.softserve.edu.greencity.ui.data.User;
import com.softserve.edu.greencity.ui.data.UserRepository;
import com.softserve.edu.greencity.ui.pages.common.CommentComponent;
import com.softserve.edu.greencity.ui.pages.common.CommentContainer;
import com.softserve.edu.greencity.ui.pages.common.ReplyContainer;
import com.softserve.edu.greencity.ui.tests.runner.GreenCityTestRunner;
import com.softserve.edu.greencity.ui.tools.jdbc.services.EcoNewsService;
import jdk.jfr.Description;
import org.testng.annotations.Test;

public class CommentCreation extends GreenCityTestRunner {

    private User getTemporaryUser() {
        return UserRepository.get().temporary();
    }

    private EcoNewsService getEcoNewsService() {
        return new EcoNewsService();
    }

    @Test
    @Description("GC-958")
    public void loggedUserCanPublishReply() {
        logger.info("Verify that logged user can publish reply starts");

        String replyText = "First reply";
        CommentComponent commentComponent = loadApplication()
                .loginIn(getTemporaryUser())
                .navigateMenuEcoNews()
                .switchToSingleNewsPageByNumber(0)
                .getCommentContainer()
                .chooseCommentByNumber(0)
                .addReply(replyText);
        String actualReplyText = commentComponent.chooseCommentByNumber(0).openReply().chooseReplyByNumber(0).getReplyComment().getText();
        softAssert.assertEquals(replyText, actualReplyText);
        ReplyContainer replyContainer = new ReplyContainer(driver);
        replyContainer.chooseReplyByNumber(0).clickReplyDeleteButton();
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
        ReplyContainer replyContainer = new ReplyContainer(driver);
        replyContainer.chooseReplyByNumber(0).clickReplyDeleteButton();
    }




}
