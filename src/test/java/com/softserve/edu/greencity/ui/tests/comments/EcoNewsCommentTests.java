package com.softserve.edu.greencity.ui.tests.comments;

import com.softserve.edu.greencity.ui.data.User;
import com.softserve.edu.greencity.ui.data.UserRepository;
import com.softserve.edu.greencity.ui.pages.econews.SingleNewsPage;
import com.softserve.edu.greencity.ui.tests.runner.GreenCityTestRunner;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class EcoNewsCommentTests extends GreenCityTestRunner {
    @BeforeClass
    public void creatingCommentToNews() {
        String comment = "different news";
        User user = UserRepository.get().temporary();

        SingleNewsPage page = loadApplication()
                .signIn()
                .getManualLoginComponent()
                .successfullyLogin(user)
                .navigateMenuEcoNews()
                .switchToSingleNewsPageByNumber(0);
        page.getCommentPart()
                .addComment(comment);
        page.signOut()
                .navigateMenuEcoNews()
                .switchToSingleNewsPageByNumber(0);
    }

    @Test(testName = "GC-872")
    @Description("Unregistered users can’t edit reply on the News page")
    public void unregisteredUsersCanNotEditReply() {
        logger.info("Verify that unregistered users can’t edit reply on the News page");
        String reply = "hello,sweetiee";
        User user = UserRepository.get().temporary();
        SingleNewsPage page = loadApplication()
                .signIn()
                .getManualLoginComponent()
                .successfullyLogin(user)
                .navigateMenuEcoNews()
                .switchToSingleNewsPageByNumber(0);
        page.getCommentPart()
                .chooseCommentByNumber(0)
                .addReply(reply);
        page.signOut();

        SingleNewsPage newpage = loadApplication()
                .navigateMenuEcoNews()
                .switchToSingleNewsPageByNumber(0);

        Assert.assertFalse(newpage.getCommentPart().chooseCommentByNumber(0).openReply()
                .chooseReplyByNumber(0).isEditReplyButtonDisplayed());
    }
}
