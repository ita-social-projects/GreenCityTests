package com.softserve.edu.greencity.ui.tests.comments;

import com.softserve.edu.greencity.data.econews.NewsData;
import com.softserve.edu.greencity.data.econews.NewsDataRepository;
import com.softserve.edu.greencity.data.users.User;
import com.softserve.edu.greencity.data.users.UserRepository;
import com.softserve.edu.greencity.ui.pages.common.CommentComponent;
import com.softserve.edu.greencity.ui.pages.common.ReplyComponent;
import com.softserve.edu.greencity.ui.pages.econews.SingleNewsPage;
import com.softserve.edu.greencity.ui.tests.runner.GreenCityTestRunner;
import com.softserve.edu.greencity.ui.tools.jdbc.services.EcoNewsService;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;

public class EcoNewsCommentReplyTests extends GreenCityTestRunner {
    private NewsData newsData;
    private final String replyText = "Test reply";

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

    @Test(testName = "GC-866", description = "866")
    @Description("Verify that ‘Comment’ button is disable, when ‘Add a comment’ field is empty on the ‘News’ page.")
    public void verifyCommentButtonIsDisableWhenFieldIsEmpty() {
        String emptyCommentField = "";
        CommentComponent comment = loadApplication()
                .signIn()
                .getManualLoginComponent()
                .successfullyLogin(getTemporaryUser())
                .navigateMenuEcoNews()
                .switchToSingleNewsPageByParameters(newsData)
                .getCommentPart()
                .chooseCommentByNumber(0).setTextInEditAria(emptyCommentField);
        Assert.assertFalse(comment.getSaveEditButton().isEnabled());
    }

    @Test(testName = "GC-961", description = "GC-961")
    @Description("This test case verifies that logged user cannot add a reply with 8001+ characters on News Single Page")
    public void verifyThatLoggedUserAddReplyWithInvalidNumberOfCharacters() {
        CommentComponent commentComponent = loadApplication()
                .signIn()
                .getManualLoginComponent()
                .successfullyLogin(getTemporaryUser())
                .navigateMenuEcoNews()
                .switchToSingleNewsPageByParameters(newsData)
                .getCommentPart()
                .chooseCommentByNumber(0)
                .clickReplyButton()
                .setReplyText(String.join("", Collections.nCopies(8010, "z")));

        Assert.assertEquals(commentComponent.getReplyField().getAttribute("value").length(), 8000, "system should cuts everything after 8000 characters");
        commentComponent.clickAddReplyButton().getShowReplyButton().click();
        ReplyComponent replyComponent = commentComponent.getReplyComponents().get(0);
        Assert.assertEquals(replyComponent.getReplyComment().getText().length(), 8000, "the text cannot contain more than 8000 characters");
    }

    @Test(testName = "GC-966", description = "GC-966")
    @Description("Verify that unlogged user cannot add reply to the comment on News Single Page.")
    public void verifyUnloggedUserCanAddReplyToComment() {
        CommentComponent comment = loadApplication()
                .navigateMenuEcoNews()
                .switchToSingleNewsPageByParameters(newsData)
                .getCommentPart()
                .chooseCommentByNumber(0);
        Assert.assertFalse(comment.isAddReplyDisplayed(), "the 'Reply' button should not be displayed, if user is unlogged");
    }

    @Test(testName = "GC-958", description = "GC-958")
    public void loggedUserCanPublishReply() {
        logger.info("Verify that logged user can publish reply starts");
        ReplyComponent replyComponent = loadApplication()
                .loginIn(getTemporaryUser())
                .navigateMenuEcoNews()
                .switchToSingleNewsPageByParameters(newsData)
                .getCommentPart()
                .chooseCommentByNumber(0)
                .openReply().chooseReplyByNumber(0);

        softAssert.assertEquals(replyText, replyComponent.getReplyComment().getText());
        softAssert.assertAll();
    }

    @Test(testName = "GC-870", description = "GC-870")
    @Description("verify that logged user can't edit reply of the other user on the 'News' page.")
    public void loggedUserCanNotEditNoHisReply(){
        logger.info("verify that logged user can't edit reply of the other user on the 'News' page.");
        User user = UserRepository.get().exist();
        boolean canEdit = loadApplication()
                .loginIn(user)
                .navigateMenuEcoNews()
                .switchToSingleNewsPageByParameters(newsData)
                .getCommentPart()
                .chooseCommentByNumber(0)
                .openReply()
                .chooseReplyByNumber(0)
                .isEditReplyButtonDisplayed();
        Assert.assertFalse(canEdit,"Edit button on the reply shouldn't be displayed");
    }

    @Test(testName = "GC-874", description = "GC-874")
    @Description("verify that system saves the changes after click ‘Save’ button on the ‘News’ page")
    public void systemSavesChangesAfterClickReply() {
        logger.info("verify that system saves the changes after click ‘Save’ button on the ‘News’ page");
        String textToEditTheReply = "reply has been changed";
        SingleNewsPage newsPage = loadApplication()
                .loginIn(getTemporaryUser())
                .navigateMenuEcoNews()
                .switchToSingleNewsPageByParameters(newsData);
        logger.info("Set text into reply edit field");
        newsPage.getCommentPart()
                .chooseCommentByNumber(0)
                .openReply()
                .chooseReplyByNumber(0)
                .clickReplyEditButton()
                .setTextIntoReplyEditField(textToEditTheReply);
        logger.info("leave page and check that changes have not been saved");
        String replyTextAfterRefresh = newsPage
                .navigateMenuEcoNews()
                .switchToSingleNewsPageByParameters(newsData)
                .getCommentPart()
                .chooseCommentByNumber(0)
                .openReply()
                .chooseReplyByNumber(0)
                .getReplyText();
        softAssert.assertEquals(replyText, replyTextAfterRefresh, "Fail, system shouldn't saved reply without 'edit' click ");
        logger.info("Edit reply");
        newsPage.getCommentPart()
                .chooseCommentByNumber(0)
                .chooseReplyByNumber(0)
                .editReply(textToEditTheReply);
        logger.info("refresh page");
        driver.navigate().refresh();
        ReplyComponent replyAfterEdit = newsPage
                .getCommentPart()
                .chooseCommentByNumber(0)
                .openReply()
                .chooseReplyByNumber(0);
        logger.info("check changes after editing");
        softAssert.assertEquals(replyAfterEdit.getReplyText(),textToEditTheReply,"Fail, system should save changes after editing reply");
        softAssert.assertAll();
    }

    @Test(testName = "GC-998", description = "GC-998")
    @Description("Verify that logged user cannot reply to other replies on News Single Page")
    public void loggedUserCannotReplyToOtherReply() {
        logger.info("Verify that logged user cannot reply to other replies on News Single Page starts");
        boolean isReplyButtonDisplayed = loadApplication()
                .loginIn(getTemporaryUser())
                .navigateMenuEcoNews()
                .switchToSingleNewsPageByParameters(newsData)
                .getCommentPart()
                .chooseCommentByNumber(0)
                .clickReplyButton()
                .openReply()
                .chooseReplyByNumber(0)
                .isReplyButtonDisplayed();
        softAssert.assertFalse(isReplyButtonDisplayed);
        softAssert.assertAll();
    }

    @Test(testName = "GC-999", description = "GC-999")
    @Description("Verify that unlogged user cannot reply to other replies on News Single Page ")
    public void notLoggedUserCannotReplyToOtherReply() {
        logger.info("Verify that unlogged user cannot reply to other replies on News Single Page starts");
        boolean isReplyButtonDisplayed = loadApplication()
                .navigateMenuEcoNews()
                .switchToSingleNewsPageByParameters(newsData)
                .getCommentPart()
                .chooseCommentByNumber(0)
                .openReply()
                .chooseReplyByNumber(0)
                .isReplyButtonDisplayed();
        softAssert.assertFalse(isReplyButtonDisplayed);
        softAssert.assertAll();
    }

    @Test(testName = "GC-995", description = "GC-955")
    @Description("Verify that unlogged user can review and hide all related to the comment replies on News Single Page")
    public void notLoggedUserCanReviewAndHideReplies() {
        logger.info("Verify that unlogged user cannot reply to other replies on News Single Page starts");
        CommentComponent commentComponent = loadApplication()
                .navigateMenuEcoNews()
                .switchToSingleNewsPageByParameters(newsData)
                .getCommentPart()
                .chooseCommentByNumber(0)
                .openReply();
        softAssert.assertTrue(commentComponent.isReplyComponentPresent());
        boolean isRepliesHide = commentComponent
                .closeReply()
                .isReplyComponentPresent();
        softAssert.assertFalse(isRepliesHide);
        softAssert.assertAll();
    }

    @Test(testName = "GC-971", description = "GC-971")
    @Description("Verify that logged users can review and hide all related to the comment replies on News Single Page")
    public void loggedUserCanReviewAndHideReplies() {
        logger.info("Verify that logged users can review and hide all related to the comment replies on News Single Page starts");
        CommentComponent commentComponent = loadApplication()
                .loginIn(getTemporaryUser())
                .navigateMenuEcoNews()
                .switchToSingleNewsPageByParameters(newsData)
                .getCommentPart()
                .chooseCommentByNumber(0)
                .openReply();
        softAssert.assertTrue(commentComponent.isReplyComponentPresent());
        boolean isRepliesHide = commentComponent
                .closeReply()
                .isReplyComponentPresent();
        softAssert.assertFalse(isRepliesHide);
        softAssert.assertAll();
    }

    @Test(testName = "GC-963", description = "GC-963")
    @Description("Verify that logged user cannot add reply with empty field on News Single Page")
    public void loggedUserCannotReplyWithEmptyFields() {
        logger.info("Verify that logged user cannot add reply with empty field on News Single Page starts");
        boolean isReplyButtonActive = loadApplication()
                .loginIn(getTemporaryUser())
                .navigateMenuEcoNews()
                .switchToSingleNewsPageByParameters(newsData)
                .getCommentPart()
                .chooseCommentByNumber(0)
                .clickReplyButton()
                .isAddReplyButtonEnable();
        softAssert.assertFalse(isReplyButtonActive);
        softAssert.assertAll();
    }

    @Test(dataProvider = "setStringLength", testName = "GC-1132", description = "GC-1132")
    @Description("Logged user can 'Edit' his reply on 'News' page")
    public void loggedUserCanEditHisReply(int stringLength) {
        ReplyComponent replyComponent = loadApplication()
                .signIn()
                .getManualLoginComponent()
                .successfullyLogin(getTemporaryUser())
                .navigateMenuEcoNews()
                .switchToSingleNewsPageByParameters(newsData)
                .getCommentPart()
                .chooseCommentByNumber(0)
                .openReply()
                .chooseReplyByNumber(0);

        String textForReplyEditing = String.join("", Collections.nCopies(stringLength, "a"));
        replyComponent.editReply(textForReplyEditing);
        String timeStamp = new SimpleDateFormat("MMM dd, yyyy").format(Calendar.getInstance().getTime());

        softAssert.assertEquals(replyComponent.getReplyText(), textForReplyEditing);
        softAssert.assertTrue(replyComponent.getReplyDate().contains(timeStamp));
        softAssert.assertTrue(replyComponent.isaAvatarDisplayed());
        softAssert.assertAll();
    }

    @DataProvider
    public Object[][] setStringLength() {
        Object[][] length = new Object[][]{
                {1}, {4444}, {8000}
        };
        return length;
    }
}
