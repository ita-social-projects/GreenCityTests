package com.softserve.edu.greencity.ui.tests.comments;

import com.softserve.edu.greencity.data.econews.NewsData;
import com.softserve.edu.greencity.data.econews.Tag;
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
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collections;

public class EcoNewsCommentReplyTests extends GreenCityTestRunner {
    private NewsData newsData;
    @BeforeClass
    public void creatingCommentAndReplyToNews() {
        String comment = "different news";
        User user = UserRepository.get().temporary();

        newsData = new NewsData(Arrays.asList(Tag.ADS), "Comment, please!",
                "I need a lot of comments! Comment, go on!");

        loadApplication()
                .signIn()
                .getManualLoginComponent()
                .successfullyLogin(user)
                .navigateMenuEcoNews()
                .gotoCreateNewsPage()
                .fillFields(newsData)
                .publishNews();

        SingleNewsPage page = loadApplication()
                .navigateMenuEcoNews()
                .refreshPage() //fresh news might not be displayed unless you refresh
                .switchToSingleNewsPageByParameters(newsData);
        page.getCommentPart()
                .addComment(comment);
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
}
