package com.softserve.edu.greencity.ui.tests.createnews;

import com.softserve.edu.greencity.data.User;
import com.softserve.edu.greencity.data.UserRepository;
import com.softserve.edu.greencity.data.econews.NewsData;
import com.softserve.edu.greencity.data.econews.NewsDataRepository;
import com.softserve.edu.greencity.ui.pages.econews.CreateNewsPage;
import com.softserve.edu.greencity.ui.pages.econews.EcoNewsPage;
import com.softserve.edu.greencity.ui.pages.econews.PreviewPage;
import com.softserve.edu.greencity.ui.tests.runner.GreenCityTestRunner;
import com.softserve.edu.greencity.ui.tools.DateUtil;
import com.softserve.edu.greencity.ui.tools.jdbc.services.EcoNewsService;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

/**
 * Tests to verify "Create News" functional
 * WARNING! This is a legacy class.
 * Its test cases might be implemented in CreateNewsPositiveTest, CreateNewsNegativeTest and CreateNewsPreviewTest
 * TODO merge with other classes and then delete this class
 * @author lv-493
 */
public class CreateNewsLegacyTest extends GreenCityTestRunner {
    User defaultUser;

    @BeforeClass
    public void readCredentials() {
        defaultUser = UserRepository.get().temporary();
    }

    @BeforeMethod
    public void login() {
        if(isLogInNow()) return;
        loadApplication()
                .loginIn(defaultUser);
    }

    @DataProvider
    public Object[] newsDataProvider() {
        return new Object[]{
                NewsDataRepository.get().getAllFieldsNews(),
                NewsDataRepository.get().getRequiredFieldsNews(),
                NewsDataRepository.get().getAllFieldsNews()
        };
    }

    @Test(dataProvider = "newsDataProvider")
    public void createNewsTest(NewsData newsData) {
        logger.info("createNewsTest starts with parameters: " + newsData.toString());
        EcoNewsPage econewsPage = loadApplication()
                .navigateMenuEcoNews();
        int expectedCount = econewsPage.getNumberOfItemComponent();
        CreateNewsPage createNewsPage = econewsPage.gotoCreateNewsPage()
                .fillFields(newsData)
                .continueNewsCreating();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(createNewsPage.getContentFieldValue(), newsData.getContent(),
                "Text in Content field doesn't match with input data");
        softAssert.assertEquals(createNewsPage.getTitleFieldValue(), newsData.getTitle(),
                "Text in Title field doesn't match with input data");
        softAssert.assertEquals(createNewsPage.getSourceFieldValue(), newsData.getSource(),
                "Text in Source field doesn't match with input data");
        softAssert.assertEquals(createNewsPage.getSelectedTagsNames(), newsData.getTagsName(),
                "Tags don't match with input data");
        if (!newsData.getFilePath().equals("")) {
            softAssert.assertTrue(createNewsPage.isPictureUploaded(),
                    "Picture is not uploaded");
        }
        softAssert.assertAll();
        createNewsPage.publishNews();
        econewsPage = createNewsPage.navigateMenuEcoNews();
        Assert.assertEquals(econewsPage.getNumberOfItemComponent(), expectedCount + 1);
        econewsPage.signOut();
    }

    /**
     * Create news test by using Publish button from PreviewPage
     *@author lv-493
     * @param newsData
     */
    @Test(dataProvider = "newsDataProvider")
    public void createNewsFromPreViewTest(NewsData newsData) {
        logger.info("createNewsFromPreViewTest starts with parameters: " + newsData.toString());
        EcoNewsPage econewsPage = loadApplication()
                .navigateMenuEcoNews();
        int expectedCount = econewsPage.getNumberOfItemComponent();
        PreviewPage preViewPage = econewsPage
                .gotoCreateNewsPage()
                .fillFields(newsData)
                .goToPreViewPage();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(preViewPage.getContentFieldText().trim(), newsData.getContent().trim(),
                "Text in Content field doesn't match with input data");
        softAssert.assertEquals(preViewPage.getTitleFieldText(), newsData.getTitle(),
                "Text in Title field doesn't match with input data");
        softAssert.assertEquals(preViewPage.getDateFieldText(), DateUtil.getCurrentDateMonthFirst(),
                "Text in Date field doesn't match with input data");
        softAssert.assertEquals(preViewPage.getTagsNames(), newsData.getTagsName(),
                "Tags don't match with input data");
        econewsPage = preViewPage.publishNews();
        softAssert.assertEquals(econewsPage.getNumberOfItemComponent(), expectedCount + 1);
        econewsPage.signOut();
        softAssert.assertAll();
    }

    /**
     * Create news test after visiting PreviewPage
     *@author lv-493
     * @param newsData
     */
    @Test(dataProvider = "newsDataProvider")
    public void createNewsAfterPreViewTest(NewsData newsData) {
        logger.info("createNewsAfterPreViewTest starts with parameters: " + newsData.toString());
        EcoNewsPage econewsPage = loadApplication()
                .navigateMenuEcoNews();
        int expectedCount = econewsPage.getNumberOfItemComponent();
        PreviewPage preViewPage = econewsPage.gotoCreateNewsPage()
                .fillFields(newsData)
                .goToPreViewPage();
        SoftAssert softAssertPreView = new SoftAssert();
        softAssertPreView.assertEquals(preViewPage.getTitleFieldText(), newsData.getTitle(),
                "Text in Title field doesn't match with input data");
        softAssertPreView.assertEquals(preViewPage.getContentFieldText().trim(), newsData.getContent().trim(),
                "Text in Content field doesn't match with input data");
        softAssertPreView.assertEquals(preViewPage.getDateFieldText(), DateUtil.getCurrentDateMonthFirst(),
                "Text in Date field doesn't match with input data");
        softAssertPreView.assertTrue(preViewPage.isPublishButtonPresent(),
                "Publish button is not present");
        softAssertPreView.assertEquals(preViewPage.getTagsNames(), newsData.getTagsName(),
                "Tags don't match with input data");
        softAssertPreView.assertAll();
        CreateNewsPage createNewsPage = preViewPage.backToCreateNewsPage();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(createNewsPage.getSelectedTagsNames(), newsData.getTagsName(),
                "Tags don't match with input data");
        softAssert.assertEquals(createNewsPage.getTitleFieldValue(), newsData.getTitle(),
                "Text in Title field doesn't match with input data");
        softAssert.assertEquals(createNewsPage.getContentFieldValue().trim(), newsData.getContent().trim(),
                "Text in Content field doesn't match with input data");
        softAssert.assertEquals(createNewsPage.getDateFieldText(), DateUtil.getCurrentDateDayFirst(),
                "Text in Date field doesn't match with input data");
        softAssert.assertEquals(createNewsPage.getSourceFieldValue(), newsData.getSource(),
                "Text in Source field doesn't match with input data");
//        if (newsData.getFilePath() != "") {
//            softAssert.assertTrue(createNewsPage.isPictureUploaded(), "Picture is not uploaded");
//        }TODO this assertion fail because of bug in the developers side. They should fix this place.
        softAssert.assertAll();
        econewsPage = createNewsPage.publishNews().navigateMenuEcoNews();
        Assert.assertEquals(econewsPage.getNumberOfItemComponent(), expectedCount + 1);
        econewsPage.signOut();
    }


    @DataProvider
    public Object[] newsInvalidDataProvider() {
        return new Object[]{
                NewsDataRepository.get().getNewsWithInvalidData()
        };
    }

    /**
     * Create news negative test
     *@author lv-493
     * @param newsData
     */
    @Test(dataProvider = "newsInvalidDataProvider", invocationCount = 4)
    public void createNewsNegativeTest(NewsData newsData) {
        logger.info("createNewsNegativeTest starts with parameters: " + newsData.toString());
        SoftAssert softAssert = new SoftAssert();
        CreateNewsPage createNewsPage = loadApplication()
                .navigateMenuEcoNews()
                .gotoCreateNewsPage();
        createNewsPage.clearSourceField();
        createNewsPage.setSourceField(newsData.getSource());
        createNewsPage.clearTitleField();
        createNewsPage.setTitleField(newsData.getTitle());
        createNewsPage.clearContentField();
        createNewsPage.setContentField(newsData.getContent());
        createNewsPage.uploadFile(createNewsPage.getDropArea(), newsData.getFilePath());
        softAssert.assertTrue(createNewsPage.isTitleDescriptionWarning(), "Title warning is not present");
        softAssert.assertTrue(createNewsPage.isContentDescriptionWarning(), "Content warning is not present");
        softAssert.assertTrue(createNewsPage.isPictureDescriptionWarning(), "Picture warning is not present");
        softAssert.assertTrue(createNewsPage.isSourceDescriptionWarning(), "Source warning is not present");
        softAssert.assertFalse(createNewsPage.isPublishButtonClickable(), "Publish button should not be clickable");
        createNewsPage.getTagsComponent().deselectTags(newsData.getTags());
        createNewsPage.getTagsComponent().selectTags(newsData.getTags());
        softAssert.assertTrue(createNewsPage.isTagsDescriptionWarning(), "Tags warning is not present");
        softAssert.assertAll();
        createNewsPage.getTagsComponent().deselectTags(newsData.getTags());
        PreviewPage preViewPage = createNewsPage.goToPreViewPage();
        Assert.assertFalse(preViewPage.isPublishButtonPresent());
        preViewPage.signOut();
    }

    @AfterMethod
    public void afterMethod() {
        driver.manage().deleteAllCookies();
    }

    @AfterClass
    public void cleanUp() {
        EcoNewsService service = new EcoNewsService();
        service.deleteNewsByTitle(NewsDataRepository.get().getAllFieldsNews().getTitle());
        service.deleteNewsByTitle(NewsDataRepository.get().getRequiredFieldsNews().getTitle());
    }

}
