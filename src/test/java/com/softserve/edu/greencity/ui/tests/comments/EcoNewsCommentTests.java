package com.softserve.edu.greencity.ui.tests.comments;

import com.softserve.edu.greencity.ui.assertions.EcoNewsCommentAssertions;
import com.softserve.edu.greencity.ui.data.User;
import com.softserve.edu.greencity.ui.data.UserRepository;
import com.softserve.edu.greencity.ui.pages.econews.SingleNewsPage;
import com.softserve.edu.greencity.ui.tests.runner.GreenCityTestRunner;
import io.qameta.allure.Description;
import org.testng.annotations.*;

public class EcoNewsCommentTests extends GreenCityTestRunner {

    @BeforeClass
    public void creatingCommentToNews() {
        String comment = "i think its a great news, especially deded";
        User user = UserRepository.get().temporary();
        SingleNewsPage singleNewsPage = loadApplication()
                .signIn()
                .getManualLoginComponent()
                .successfullyLogin(user)
                .navigateMenuEcoNews()
                .switchToSingleNewsPageByNumber(0)
                .addComment(comment);
    }

    @Test(testName = "GC-872")
    @Description("Unregistered users can’t edit reply on the News page")
    public void tryToReplyOnComment() {
        String reply = "hello,buddy,welcome to the club";
        SingleNewsPage singleNewsPage = loadApplication()
                .navigateMenuEcoNews()
                .switchToSingleNewsPageByNumber(0)
                .replyToComment(0, reply);
        EcoNewsCommentAssertions.assertNewComment(singleNewsPage.getCommentsList(), reply);
    }

}
