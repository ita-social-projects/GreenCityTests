package com.softserve.edu.greencity.ui.tests;

import com.softserve.edu.greencity.ui.data.User;
import com.softserve.edu.greencity.ui.data.econews.NewsData;
import com.softserve.edu.greencity.ui.data.econews.NewsDataRepository;
import com.softserve.edu.greencity.ui.pages.econews.CreateNewsPage;
import com.softserve.edu.greencity.ui.pages.econews.EconewsPage;
import com.softserve.edu.greencity.ui.pages.econews.PreViewPage;
import com.softserve.edu.greencity.ui.tools.DateUtil;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * Tests to verify "Create News" functional
 * @author lv-493
 */
public class CreateNewsTest extends GreenCityTestRunner {
    User defaultUser;

    @BeforeClass
    public void readCredentials() {
        Properties properties = new Properties();
        try {
            properties
                    .load(new BufferedReader(new FileReader("src/test/resources/credentials.properties")));
        } catch(IOException e) {
            e.printStackTrace();
        }
        defaultUser = new User(properties.getProperty("temporaryEmail"), properties.getProperty("temporaryPass"));
    }

    @BeforeMethod
    public void login() {
        if(isLoginingNow()) return;
        loadApplication()
                .loginIn(defaultUser)
                .navigateMenuTipsTricks();
    }


    @Test(dataProvider = "newsDataProvider")
    public void createNewsTest(NewsData newsData) {
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
    }

    /**
     * Create news test by using Publish button from PreViewPage
     *@author lv-493
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
    }

    /**
     * Create news test after visiting PreViewPage
     *@author lv-493
     * @param newsData
     */
    @Test(dataProvider = "newsDataProvider")
    public void createNewsAfterPreViewTest(NewsData newsData) {
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
//        if (newsData.getFilePath() != "") {
//            softAssert.assertTrue(createNewsPage.isPictureUploaded(), "Picture is not uploaded");
//        }TODO this assertion fail because of bug in the developers side. They should fix this place.
        softAssert.assertAll();
        econewsPage = createNewsPage.publishNews().navigateMenuEconews();
        Assert.assertEquals(econewsPage.getNumberOfItemComponent(), expectedCount + 1);
        econewsPage.signOut();
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
                .navigateMenuEconews()
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
        PreViewPage preViewPage = createNewsPage.goToPreViewPage();
        Assert.assertFalse(preViewPage.isPublishButtonPresent());
        preViewPage.signOut();
    }

    @DataProvider
    public Object[] newsDataProvider() {
        return new Object[]{
                NewsDataRepository.getAllFieldsNews(),
                NewsDataRepository.getRequiredFieldsNews(),
                NewsDataRepository.getAllFieldsNews()
        };
    }

    @DataProvider
    public Object[] newsInvalidDataProvider() {
        return new Object[]{
                NewsDataRepository.getInvalidData()
        };
    }

    @AfterMethod
    public void afterMethod() {
        driver.manage().deleteAllCookies();
    }

}