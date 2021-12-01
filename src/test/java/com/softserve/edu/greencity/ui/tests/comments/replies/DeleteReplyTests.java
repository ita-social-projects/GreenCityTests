package com.softserve.edu.greencity.ui.tests.comments.replies;

import com.softserve.edu.greencity.data.econews.NewsData;
import com.softserve.edu.greencity.data.econews.NewsDataRepository;
import com.softserve.edu.greencity.data.users.User;
import com.softserve.edu.greencity.data.users.UserRepository;
import com.softserve.edu.greencity.ui.pages.common.CommentComponent;
import com.softserve.edu.greencity.ui.pages.common.ReplyComponent;
import com.softserve.edu.greencity.ui.pages.econews.SingleNewsPage;
import com.softserve.edu.greencity.ui.tests.runner.GreenCityTestRunnerWithLoginLogout;
import com.softserve.edu.greencity.ui.tools.jdbc.services.EcoNewsService;
import io.qameta.allure.Description;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class DeleteReplyTests extends GreenCityTestRunnerWithLoginLogout {
    private final String replyText = "Test reply";
    private NewsData newsData;

    private User getTemporaryUser() {
        return UserRepository.get().temporary();
    }

    @BeforeClass
    public void creatingCommentAndReplyToNews() {
        String comment = "different news";
        newsData = NewsDataRepository.get().getNewsWithValidData();
        loadApplication()
                .signIn()
                .getManualLoginComponent()
                .successfullyLogin(getTemporaryUser())
                .navigateMenuEcoNews()
                .gotoCreateNewsPage()
                .fillFields(newsData)
                .publishNews();

        SingleNewsPage page = loadApplication()
                .navigateMenuEcoNews()
                .refreshPage() //fresh news might not be displayed unless you refresh
                .switchToSingleNewsPageByParameters(newsData);
        page.getCommentPart()
                .addComment(comment)
                .chooseCommentByNumber(0)
                .addReply(replyText);
        page.signOut();
    }

    @AfterClass
    public void clearUp() {
        EcoNewsService ecoNewsService = new EcoNewsService();
        ecoNewsService.deleteNewsByTitle(newsData.getTitle());
    }

    @Test(testName = "GC-823", description = "GC-823")
    @Description("Verify that unlogged user cannot delete not his reply on the 'Single News' page")
    public void verifyUnloggedUserCanDeleteReplyToComment() {

        SingleNewsPage page = loadApplication()
                .signIn()
                .getManualLoginComponent()
                .successfullyLogin(getTemporaryUser())
                .navigateMenuEcoNews()
                .refreshPage() //fresh news might not be displayed unless you refresh
                .switchToSingleNewsPageByParameters(newsData);
        page.getCommentPart()
                .addComment("GC-823")
                .chooseCommentByNumber(0)
                .addReply(replyText);
        page.signOut();

        ReplyComponent comment = loadApplication()
                .navigateMenuEcoNews()
                .switchToSingleNewsPageByParameters(newsData)
                .getCommentPart()
                .chooseCommentByNumber(0)
                .openReply()
                .chooseReplyByNumber(0);
        softAssert.assertFalse(comment.isDeleteReplyButtonDisplayed(), "the 'Delete' button should not be displayed");
        softAssert.assertAll();
    }

    @Test(testName = "GC-822", description = "GC-822")
    @Description("GC-822")
    public void loggedUserCanDeleteReplyToComment() {
        logger.info("Verify that logged user can delete his own reply on the 'Single News' page");
        User user = UserRepository.get().temporary();
        String commentText = "Test comment";
        String replyText = "Test reply";
        ReplyComponent comment = loadApplication()
                .signIn()
                .getManualLoginComponent()
                .successfullyLogin(user)
                .navigateMenuEcoNews()
                .switchToSingleNewsPageByParameters(newsData)
                .getCommentPart()
                .addComment(commentText)
                .chooseCommentByNumber(0)
                .clickReplyButton()
                .setReplyText(replyText)
                .clickAddReplyButton()
                .openReply()
                .chooseReplyByNumber(0);
        softAssert.assertTrue(comment.clickDeleteReplyButtonCancel().isReplyPresent());
        softAssert.assertTrue(comment.clickDeleteReplyButtonConfirm().isReplyPresent());
        softAssert.assertAll();
    }


    @Test(testName = "GC-828", description = "828")
    @Description("Verify if user delete his own comment then all replies to this comment at the ‘News’ page are deleted too.")
    public void deleteCommentWithAllReplies() {
        String comment = "different news";
        String reply2 = "added second reply";
        CommentComponent commentComponent = loadApplication()
                .signIn()
                .getManualLoginComponent()
                .successfullyLogin(getTemporaryUser())
                .navigateMenuEcoNews()
                .switchToSingleNewsPageByParameters(newsData)
                .getCommentPart()
                .chooseCommentByNumber(0)
                .addReply(reply2);
        SingleNewsPage page = loadApplication()
                .navigateMenuEcoNews()
                .refreshPage() //fresh news might not be displayed unless you refresh
                .switchToSingleNewsPageByParameters(newsData);
        page.getCommentPart()
                .chooseCommentByNumber(0)
                .clickDeleteCommentButton();
        ReplyComponent replyComponent = page
                .getCommentPart()
                .chooseCommentByNumber(0)
                .openReply().chooseReplyByNumber(0);
        softAssert.assertNotEquals(comment, page.getCommentPart().chooseCommentByNumber(0).getCommentText());
        softAssert.assertNotEquals(reply2, replyComponent.getReplyComment().getText());
    }
}