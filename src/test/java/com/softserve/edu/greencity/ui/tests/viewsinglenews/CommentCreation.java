package com.softserve.edu.greencity.ui.tests.viewsinglenews;

import com.softserve.edu.greencity.ui.data.User;
import com.softserve.edu.greencity.ui.data.UserRepository;
import com.softserve.edu.greencity.ui.data.econews.NewsDataRepository;
import com.softserve.edu.greencity.ui.pages.common.*;
import com.softserve.edu.greencity.ui.pages.econews.EcoNewsPage;
import com.softserve.edu.greencity.ui.pages.econews.SingleNewsPage;
import com.softserve.edu.greencity.ui.tests.runner.GreenCityTestRunner;
import com.softserve.edu.greencity.ui.tools.jdbc.services.EcoNewsService;
import jdk.jfr.Description;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

public class CommentCreation extends GreenCityTestRunner {

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
                .getPublishComment()
                .addComment("First Comment")
                .getCommentContainer()
                .chooseCommentByNumber(0)
                .addReply("First reply");
        signOutByStorage();
    }
    @AfterClass
    public void deleteNews() {
        EcoNewsPage ecoNewsPage = loadApplication().navigateMenuEcoNews();
        getEcoNewsService().deleteNewsByTitle(NewsDataRepository.get().getNewsWithValidData().getTitle());
        softAssert.assertFalse(ecoNewsPage.refreshPage().isNewsDisplayedByTitle(NewsDataRepository.get().getNewsWithValidData().getTitle()));
    }

    @Test
    @Description("GC-958")
    public void loggedUserCanPublishReply() {
        logger.info("Verify that logged user can publish reply starts");

        String replyText = "Second reply";
        ReplyComponent replyComponent = loadApplication()
                .loginIn(getTemporaryUser())
                .navigateMenuEcoNews()
                .switchToSingleNewsPageByNumber(0)
                .getCommentContainer()
                .chooseCommentByNumber(0)
                .addReply(replyText).openReply().chooseReplyByNumber(0);

        softAssert.assertEquals(replyText, replyComponent.getReplyComment().getText());
        softAssert.assertAll();
    }

    @Test
    @Description("GC-817")
    public void loggedUserCanAddComment() {
        logger.info("Verify that logged user can add comment starts");
        String commentText = "Add comment";
        SingleNewsPage singleNewsPage = loadApplication()
                .loginIn(getTemporaryUser())
                .navigateMenuEcoNews()
                .switchToSingleNewsPageByNumber(0)
                .getPublishComment()
                .addComment(commentText);

        softAssert.assertEquals(commentText, singleNewsPage.getCommentContainer().chooseCommentByNumber(0).getCommentText());
        softAssert.assertAll();
    }


    @Test
    @Description("GC-819")
    public void loggedUserCanDeleteHisComment() {
        logger.info("Verify that logged user can add comment starts");
        String commentText = "Delete comment";
        CommentComponent commentComponent = loadApplication()
                .loginIn(getTemporaryUser())
                .navigateMenuEcoNews()
                .switchToSingleNewsPageByNumber(0)
                .getPublishComment()
                .addComment(commentText)
                .getCommentContainer()
                .chooseCommentByNumber(0)
                .clickDeleteButton(); //TODO Right
       softAssert.assertFalse(commentComponent.isCommentPresent());
       softAssert.assertAll();

    }




}
