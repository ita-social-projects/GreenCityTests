package com.softserve.edu.greencity.ui.tests.createnews;

import com.softserve.edu.greencity.ui.data.UserRepository;
import com.softserve.edu.greencity.ui.data.econews.NewsData;
import com.softserve.edu.greencity.ui.data.econews.NewsDataRepository;
import com.softserve.edu.greencity.ui.pages.econews.CreateNewsPage;
import com.softserve.edu.greencity.ui.pages.econews.EconewsPage;
import com.softserve.edu.greencity.ui.pages.econews.PreViewPage;
import com.softserve.edu.greencity.ui.tests.GreenCityTestRunner;
import com.softserve.edu.greencity.ui.tools.DBQueries;
import com.softserve.edu.greencity.ui.tools.DateUtil;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class CNUnparallelTests extends GreenCityTestRunner {
    DBQueries dataBase = new DBQueries();
    String createNewsUrl = BASE_URL.substring(0, BASE_URL.indexOf('#')) + "#/news/create-news";

    @BeforeMethod
    public void login() {
        if(isLoginingNow()) return;
        loadApplication()
                .loginIn(UserRepository.get().defaultUserCredentials())
                .navigateMenuTipsTricks();
    }


    /**
     * Create news test after visiting PreViewPage
     *@author lv-493
     * @param newsData
     */
    @Test(dataProvider = "newsDataProvider")
    public void createNewsAfterPreView(NewsData newsData) {
        logger.info("createNewsAfterPreViewTest starts with parameters: " + newsData.toString());
        EconewsPage econewsPage = loadApplication()
                .navigateMenuEconews();
        int expectedCount = econewsPage.getNumberOfItemComponent();
        PreViewPage preViewPage = econewsPage.gotoCreateNewsPage()
                .fillFields(newsData)
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
        softAssert.assertEquals(createNewsPage.getDateFieldText(), DateUtil.getCurrentDate("d MMM, yyyy"),
                "Text in Date field doesn't match with input data");
        softAssert.assertEquals(createNewsPage.getSourceFieldValue(), newsData.getSource(),
                "Text in Source field doesn't match with input data");
        softAssert.assertAll();
        econewsPage = createNewsPage.publishNews().navigateMenuEconews();
        Assert.assertEquals(econewsPage.getNumberOfItemComponent(), expectedCount + 1);
        econewsPage.signOut();
        dataBase.deleteNewsByTitle(newsData.getTitle());
    }

    /**
     * Create news test by using Publish button from PreViewPage
     *@author lv-493
     * @param newsData
     */
    @Test(dataProvider = "newsDataProvider")
    public void createNewsFromPreView(NewsData newsData) {
        logger.info("createNewsFromPreViewTest starts with parameters: " + newsData.toString());
        EconewsPage econewsPage = loadApplication()
                .navigateMenuEconews();
        int expectedCount = econewsPage.getNumberOfItemComponent();
        PreViewPage preViewPage = econewsPage
                .gotoCreateNewsPage()
                .fillFields(newsData)
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
        econewsPage = preViewPage.publishNews();
        softAssert.assertEquals(econewsPage.getNumberOfItemComponent(), expectedCount + 1);
        econewsPage.signOut();
        softAssert.assertAll();
        dataBase.deleteNewsByTitle(newsData.getTitle());
    }

    @Test(dataProvider = "newsDataProvider")
    public void createNewsWithContinueCreating(NewsData newsData) {
        logger.info("createNewsTest starts with parameters: " + newsData.toString());
        EconewsPage econewsPage = loadApplication()
                .navigateMenuEconews();
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
        if (newsData.getFilePath() != "") {
            softAssert.assertTrue(createNewsPage.isPictureUploaded(),
                    "Picture is not uploaded");
        }
        softAssert.assertAll();
        createNewsPage.publishNews();
        econewsPage = createNewsPage.navigateMenuEconews();
        Assert.assertEquals(econewsPage.getNumberOfItemComponent(), expectedCount + 1);
        econewsPage.signOut();
        dataBase.deleteNewsByTitle(newsData.getTitle());
    }


    @DataProvider
    public Object[] newsDataProvider() {
        return new Object[]{
                NewsDataRepository.getAllFieldsNews(),
                NewsDataRepository.getRequiredFieldsNews(),
                NewsDataRepository.getAllFieldsNews()
        };
    }

    /**
     * Cancel news creation test.
     * @author lv-493
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
        econewsPage.signOut();
    }

}
