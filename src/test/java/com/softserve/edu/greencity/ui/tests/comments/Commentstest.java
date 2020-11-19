package com.softserve.edu.greencity.ui.tests.comments;

import com.softserve.edu.greencity.ui.data.User;
import com.softserve.edu.greencity.ui.data.UserRepository;
import com.softserve.edu.greencity.ui.pages.econews.SingleNewsPage;
import com.softserve.edu.greencity.ui.tests.runner.GreenCityTestRunner;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Commentstest extends GreenCityTestRunner {
    @BeforeClass
    public void creatingCommentToNews() {
        String comment = "wow";
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

    @Test(testName = "GC-672", description = "GC-672")
    @Description("Unregistered users can not use 'Edit’ button")
    public void editIsNotAvailableForUnregisteredUsers() {
        logger.info("Verify that ‘Edit’ button is not available for unregistered User");
        SingleNewsPage page = loadApplication()
                .navigateMenuEcoNews()
                .switchToSingleNewsPageByNumber(0);

        Assert.assertFalse(page.getCommentPart().chooseCommentByNumber(0).isEditButtonDisplayed());
    }
}
