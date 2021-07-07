package com.softserve.edu.greencity.ui.tests.createnews;

import com.softserve.edu.greencity.data.Languages;
import com.softserve.edu.greencity.data.users.User;
import com.softserve.edu.greencity.data.users.UserRepository;
import com.softserve.edu.greencity.data.econews.NewsDataRepository;
import com.softserve.edu.greencity.data.econews.Tag;
import com.softserve.edu.greencity.ui.pages.econews.CreateNewsPage;
import com.softserve.edu.greencity.ui.pages.econews.EcoNewsPage;
import com.softserve.edu.greencity.ui.pages.econews.PreviewPage;
import com.softserve.edu.greencity.ui.tests.runner.GreenCityTestRunner;
import com.softserve.edu.greencity.ui.tools.testng.RemoteSkipTestAnalyzer;
import io.qameta.allure.Description;
import org.testng.annotations.*;

import static com.softserve.edu.greencity.ui.tests.createnews.CreateNewsTexts.*;

@Listeners(value = RemoteSkipTestAnalyzer.class)
public class CreateNewsPreviewTest extends GreenCityTestRunner {
    private User getTemporaryUser() {
        return UserRepository.get().temporary();
    }

    private CreateNewsPage createNewsPage;

    @BeforeMethod
    public void OpenCreateNewsPage() {
        createNewsPage = loadApplication()
                .loginIn(getTemporaryUser())
                .navigateMenuEcoNews()
                .gotoCreateNewsPage();
    }

    @AfterMethod
    public void signOut() {
        createNewsPage.signOut();
    }

    @Test(testName = "GC-621", description = "GC-621")
    @Description("Verify that user can go to preview page")
    public void verifyPossibilityOfPreViewingNewsPage() {
        logger.info("verifyPossibilityOfPreViewingNewsPage starts");

        PreviewPage preViewPage = createNewsPage.fillFields(NewsDataRepository.get().getRequiredFieldsNews())
                .goToPreViewPage();

        softAssert.assertTrue(preViewPage.isBackToEditingButtonDisplayed());
        softAssert.assertAll();

    }

    @Test(testName = "GC-633", description = "GC-633")
    @Description("Verify that preview page is displayed correctly")
    public void verifyThatPreViewIsDisplayedCorrectly() {
        logger.info("verifyThatPreViewIsDisplayedCorrectly starts");

        PreviewPage preViewPage = createNewsPage.fillFields(NewsDataRepository.get().getRequiredFieldsNews())
                .goToPreViewPage();

        softAssert.assertTrue(preViewPage.isBackToEditingButtonDisplayed());
        softAssert.assertEquals(preViewPage.getTitleFieldText(), VALID_TITLE.getText());
        softAssert.assertEquals(preViewPage.getContentFieldText().trim(), VALID_CONTENT.getText());
        softAssert.assertAll();
    }

    @Test(testName = "GC-606", description = "GC-606")
    @Description("Verify that User can continue editing news in case ‘Cancel’ button was pressed")
    public void verifyThatUserCanContinueNewsCreations() {
        logger.info("verifyThatUserCanContinueNewsCreations starts");

        CreateNewsPage createNews = createNewsPage.fillFields(NewsDataRepository.get().getAllFieldsNews())
                .clickCancelButton()
                .clickContinueEditingButton();

        softAssert.assertEquals(createNews.getCreateNewsMainTitleText(), CREATE_NEWS_TITLE.getText());
        softAssert.assertAll();

    }

    @Test(testName = "GC-607", description = "GC-607")
    @Description("Verify that user can cancel news creation if he decided to drop posting")
    public void verifyThatUserCanCancelNewsCreation() {
        logger.info("verifyThatUserCanCancelNewsCreation starts");

        EcoNewsPage ecoNewsPage = createNewsPage.fillFields(NewsDataRepository.get().getAllFieldsNews())
                .clickCancelButton()
                .clickCancelEditingButton();

        softAssert.assertTrue(ecoNewsPage.isGridViewDisplayed());
        softAssert.assertFalse(ecoNewsPage.isNewsDisplayedByTitle(NewsDataRepository.get().getAllFieldsNews().getTitle()));
        softAssert.assertAll();

    }
@Ignore //TODO activate this test after fixing bug with cancel button
    @Test(testName = "GC-608", description = "GC-608")
    @Description("Verify that pop-up notification interface meets the mock-up specification")
    public void verifyThatPopUpNotificationInterfaceMeetsMockUp() {
        logger.info("verifyThatPopUpNotificationInterfaceMeetsMockUp");

        CreateNewsPage.CancelFrame cancelFrame = createNewsPage.fillFields(NewsDataRepository.get().getAllFieldsNews())
                .clickCancelButton();

        //TODO add asserts after fixing bug with cancel button
        softAssert.assertTrue(cancelFrame.isContinueEditingButtonDisplayed());
        softAssert.assertTrue(cancelFrame.isCancelEditingButtonDisplayed());
    }

   @Ignore
    @Test(testName = "GC-614", description = "GC-614")
    @Description("Verify that pop-up notification is displayed in Russian localization after clicking on ‘Выйти’ button")
    public void verifyThatRussianLocalizationIsDisplayedAfterCancel() {
        logger.info("verifyThatUserCanCancelNewsCreation starts");

        CreateNewsPage.CancelFrame cancelFrame = createNewsPage.fillFields(NewsDataRepository.get().getAllFieldsNewsRussian())
                .clickCancelButton();
        //TODO tu add an assert after fixing bug with cancel button
        softAssert.assertAll();

    }

    @Test(testName = "GC-403", description = "GC-403")
    @Description("Verify that User can go back to editing news by following ‘Back to editing’ link")
    public void verifyPossibilityOfGoingBackToEditNews() {
        logger.info("verifyPossibilityOfGoingBackToEditNews starts");

        PreviewPage preViewPage = createNewsPage.fillFields(NewsDataRepository.get().getAllFieldsNews())
                .goToPreViewPage();

        softAssert.assertTrue(preViewPage.isBackToEditingButtonDisplayed());
        CreateNewsPage createNewsPage = preViewPage.backToCreateNewsPage();
        softAssert.assertTrue(createNewsPage.getTagsComponent().isTagActive(Tag.NEWS)); //TODO BUG WITH TAGS
        softAssert.assertTrue(createNewsPage.getTagsComponent().isTagActive(Tag.EVENTS));
        createNewsPage.navigateMenuEcoNews().gotoCreateNewsPage();
        softAssert.assertFalse(createNewsPage.getTagsComponent().isTagActive(Tag.NEWS));
        softAssert.assertFalse(createNewsPage.getTagsComponent().isTagActive(Tag.EVENTS));
        softAssert.assertAll();

    }

    //TODO add some test on publishing news right from preview page

}
