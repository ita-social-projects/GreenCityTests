package com.softserve.edu.greencity.ui.tests;

import com.softserve.edu.greencity.ui.data.econews.NewsData;
import com.softserve.edu.greencity.ui.data.econews.NewsDataRepository;
import com.softserve.edu.greencity.ui.pages.econews.CreateNewsPage;
import com.softserve.edu.greencity.ui.pages.econews.EconewsPage;
import com.softserve.edu.greencity.ui.pages.econews.PreViewPage;
import com.softserve.edu.greencity.ui.tools.DateUtil;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

/**
 * Tests to verify "Create News" functional
 *
 * @author lv-493
 */
public class CreateNewsTests extends GreenCityTestRunner {

    @AfterMethod
    public void afterMethod() {
        driver.manage().deleteAllCookies();
    }

    @DataProvider
    public Object[] newsDataProvider() {
        return new Object[]{
                NewsDataRepository.getAllFieldsNews(),
                NewsDataRepository.getRequiredFieldsNews()
        };
    }

    @DataProvider
    public Object[] newsInvalidDataProvider() {
        return new Object[]{
                NewsDataRepository.getInvalidData()
        };
    }

    /**
     * Create news test after clicking Cancel button and then Continue creating.
     *
     * @param newsData
     */
    @Test(dataProvider = "newsDataProvider")
    public void createNewsTest(NewsData newsData) {
        logger.info("createNewsTest starts with parameters: " + newsData.toString());
        EconewsPage econewsPage = loadApplication()
                .navigateMenuEconews();

        int expectedCount = econewsPage.getNumberOfItemComponent();

        CreateNewsPage createNewsPage = econewsPage.gotoCreateNewsPage()
                .fillAllNewsFields(newsData)
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

        if (newsData.getFilePath() != "") {
            softAssert.assertTrue(createNewsPage.isPictureUploaded(),
                    "Picture is not uploaded");
        }

        softAssert.assertAll();

        createNewsPage.publishNews();
        presentationSleep(5);// for presentation only
        econewsPage = createNewsPage.navigateMenuEconews();

        Assert.assertEquals(econewsPage.getNumberOfItemComponent(), expectedCount + 1);
    }

    /**
     * Create news test by using Publish button from PreViewPage
     *
     * @param newsData
     */
    @Test(dataProvider = "newsDataProvider")
    public void createNewsFromPreViewTest(NewsData newsData) {
        logger.info("createNewsFromPreViewTest starts with parameters: " + newsData.toString());
        EconewsPage econewsPage = loadApplication()
                .navigateMenuEconews();

        int expectedCount = econewsPage.getNumberOfItemComponent();

        PreViewPage preViewPage = econewsPage
                .gotoCreateNewsPage()
                .fillAllNewsFields(newsData)
                .goToPreViewPage();

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(preViewPage.getContentFieldText(), newsData.getContent(),
                "Text in Content field doesn't match with input data");

        softAssert.assertEquals(preViewPage.getTitleFieldText(), newsData.getTitle(),
                "Text in Title field doesn't match with input data");

        softAssert.assertEquals(preViewPage.getDateFieldText(), DateUtil.getCurrentDate(),
                "Text in Date field doesn't match with input data");

        softAssert.assertEquals(preViewPage.getTagsNames(), newsData.getTagsName(),
                "Tags don't match with input data");

        softAssert.assertAll();

        econewsPage = preViewPage.publishNews(); // TODO button doesn't work
        Assert.assertEquals(econewsPage.getNumberOfItemComponent(), expectedCount);

    }

    /**
     * Create news test after visiting PreViewPage
     *
     * @param newsData
     */
    @Test(dataProvider = "newsDataProvider")
    public void createNewsAfterPreViewTest(NewsData newsData) {
        logger.info("createNewsAfterPreViewTest starts with parameters: " + newsData.toString());
        EconewsPage econewsPage = loadApplication()
                .navigateMenuEconews();

        int expectedCount = econewsPage.getNumberOfItemComponent();

        PreViewPage preViewPage = econewsPage.gotoCreateNewsPage()
                .fillAllNewsFields(newsData)
                .goToPreViewPage();

        SoftAssert softAssertPreView = new SoftAssert();

        softAssertPreView.assertEquals(preViewPage.getTitleFieldText(), newsData.getTitle(),
                "Text in Title field doesn't match with input data");

        softAssertPreView.assertEquals(preViewPage.getContentFieldText(), newsData.getContent(),
                "Text in Content field doesn't match with input data");

        softAssertPreView.assertEquals(preViewPage.getDateFieldText(), DateUtil.getCurrentDate(),
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
        softAssert.assertEquals(createNewsPage.getContentFieldValue(), newsData.getContent(),
                "Text in Content field doesn't match with input data");
        softAssert.assertEquals(createNewsPage.getDateFieldText(), DateUtil.getCurrentDate(),
                "Text in Date field doesn't match with input data");
        softAssert.assertEquals(createNewsPage.getSourceFieldValue(), newsData.getSource(),
                "Text in Source field doesn't match with input data");

        if (newsData.getFilePath() != "") {
            softAssert.assertTrue(createNewsPage.isPictureUploaded(), "Picture is not uploaded");
        }

        softAssert.assertAll();

        econewsPage = createNewsPage.publishNews().navigateMenuEconews();

        Assert.assertEquals(econewsPage.getNumberOfItemComponent(), expectedCount + 1);
    }


    /**
     * Cancel news creation test.
     */
    @Test
    public void cancelNewsCreatingTest() {
        logger.info("cancelNewsCreatingTest starts");
        EconewsPage econewsPage = loadApplication()
                .navigateMenuEconews();

        int expectedCount = econewsPage.getNumberOfItemComponent();

        econewsPage = econewsPage.gotoCreateNewsPage()
                .cancelNewsCreating();

        Assert.assertEquals(econewsPage.getNumberOfItemComponent(), expectedCount);

        Assert.assertEquals(driver.getTitle(), "Eco news");
    }

    /**
     * Create news negative test
     *
     * @param newsData
     */
    @Test(dataProvider = "newsInvalidDataProvider", invocationCount = 2)
    public void createNewsNegativeTest(NewsData newsData) {
        logger.info("createNewsNegativeTest starts with parameters: " + newsData.toString());
        CreateNewsPage createNewsPage = loadApplication()
                .navigateMenuEconews()
                .gotoCreateNewsPage()
                .fillAllNewsFields(newsData);

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertTrue(createNewsPage.isTagsDescriptionWarning(), "Tags warning is not present");
        softAssert.assertTrue(createNewsPage.isTitleDescriptionWarning(), "Title warning is not present");
        softAssert.assertTrue(createNewsPage.isContentDescriptionWarning(), "Content warning is not present");
        softAssert.assertTrue(createNewsPage.isPictureDescriptionWarning(), "Picture warning is not present");
        softAssert.assertTrue(createNewsPage.isSourceDescriptionWarning(), "Source warning is not present");
        softAssert.assertFalse(createNewsPage.isPublishButtonClickable(), "Publish button should not be clickable");
        softAssert.assertAll();

        PreViewPage preViewPage = createNewsPage.goToPreViewPage();

        Assert.assertFalse(preViewPage.isPublishButtonPresent());
    }
}