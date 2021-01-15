package com.softserve.edu.greencity.ui.tests.comments;

import com.softserve.edu.greencity.data.econews.NewsData;
import com.softserve.edu.greencity.data.econews.NewsDataRepository;
import com.softserve.edu.greencity.data.users.User;
import com.softserve.edu.greencity.data.users.UserRepository;
import com.softserve.edu.greencity.ui.pages.common.CommentComponent;
import com.softserve.edu.greencity.ui.pages.common.ReplyComponent;
import com.softserve.edu.greencity.ui.pages.econews.SingleNewsPage;
import com.softserve.edu.greencity.ui.tests.runner.GreenCityTestRunner;
import com.softserve.edu.greencity.ui.tools.engine.WaitsSwitcher;
import com.softserve.edu.greencity.ui.tools.jdbc.services.EcoNewsService;
import io.qameta.allure.Description;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Collections;

public class EcoNewsCommentReplyTests extends GreenCityTestRunner {
    private NewsData newsData;
    private String replyText = "Test reply";

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
        User user = UserRepository.get().temporary();
        String emptyCommentField = "";
        CommentComponent comment = loadApplication()
                .signIn()
                .getManualLoginComponent()
                .successfullyLogin(user)
                .navigateMenuEcoNews()
                .switchToSingleNewsPageByParameters(newsData)
                .getCommentPart()
                .chooseCommentByNumber(0).setTextInEditAria(emptyCommentField);
        Assert.assertFalse(comment.getSaveEditButton().isEnabled());
    }

    @Test(testName = "GC-961", description = "GC-961")
    @Description("This test case verifies that logged user cannot add a reply with 8001+ characters on News Single Page")
    public void verifyThatLoggedUserAddReplyWithInvalidNumberOfCharacters() {
        User user = UserRepository.get().temporary();
        CommentComponent commentComponent = loadApplication()
                .signIn()
                .getManualLoginComponent()
                .successfullyLogin(user)
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

    @Test(testName = "GC-874", description = "Gc-874")
    @Description("verify that system saves the changes after click ‘Reply’ button on the ‘News’ page")
    public void systemSavesChangesAfterClickReply() {
        logger.info("verify that system saves the changes after click ‘Reply’ button on the ‘News’ page");
        WaitsSwitcher waitsSwitcher = new WaitsSwitcher(driver, 5, 5);
        String replyEditText = "text for editing reply";
        SingleNewsPage newsPage = loadApplication()
                .loginIn(getTemporaryUser())
                .navigateMenuEcoNews()
                .switchToSingleNewsPageByParameters(newsData);

        newsPage.getCommentPart()
                .chooseCommentByNumber(0)
                .openReply()
                .chooseReplyByNumber(0)
                .clickReplyEditButton()
                .setTextIntoReplyEditField(replyEditText);

        driver.navigate().refresh();

         waitsSwitcher.setExplicitWait(5, ExpectedConditions.visibilityOf(newsPage.getCommentPart()
                 .chooseCommentByNumber(0)
                 .getShowReplyButton()));

        String contentInReplyAfterRefresh = newsPage
                .getCommentPart()
                .chooseCommentByNumber(0)
                .openReply()
                .chooseReplyByNumber(0)
                .getReplyText();
        softAssert.assertEquals(replyText, contentInReplyAfterRefresh, "system shouldn't saved reply without 'edit' click ");

    }
}
