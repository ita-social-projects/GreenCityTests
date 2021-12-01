package com.softserve.edu.greencity.ui.tests.comments.replies;

import com.softserve.edu.greencity.data.econews.NewsData;
import com.softserve.edu.greencity.data.econews.NewsDataRepository;
import com.softserve.edu.greencity.data.users.User;
import com.softserve.edu.greencity.data.users.UserRepository;
import com.softserve.edu.greencity.ui.pages.cabinet.LanguageComponents;
import com.softserve.edu.greencity.ui.pages.common.ReplyComponent;
import com.softserve.edu.greencity.ui.pages.econews.SingleNewsPage;
import com.softserve.edu.greencity.ui.tests.runner.GreenCityTestRunnerWithLoginLogout;
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

public class EditReplyTests extends GreenCityTestRunnerWithLoginLogout {
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

    @Test(testName = "GC-870", description = "GC-870")
    @Description("verify that logged user can't edit reply of the other user on the 'News' page.")
    public void loggedUserCanNotEditNoHisReply() {
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
        Assert.assertFalse(canEdit, "Edit button on the reply shouldn't be displayed");
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
        // TODO method for getting local depending on site language
        String timeStamp = new SimpleDateFormat("MMM d, yyyy",
                new LanguageComponents(driver).getLanguageLocale())
                .format(Calendar.getInstance().getTime());

        softAssert.assertEquals(replyComponent.getReplyText(), textForReplyEditing, "Inserted and posted text should be the same");
        softAssert.assertEquals(replyComponent.getReplyDate(), timeStamp, "Reply should contain date");
        softAssert.assertTrue(replyComponent.isaAvatarDisplayed(), "Avatar should be displayed");
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
