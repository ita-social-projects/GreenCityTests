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
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class CNSmokeTests extends GreenCityTestRunner {

    private final String CREATE_NEWS_URL = "https://ita-social-projects.github.io/GreenCityClient/#/news/create-news";

    @BeforeMethod
    public void login() {
        if(isLoginingNow()) return;
        loadApplication()
                .loginIn(UserRepository.get().defaultUserCredentials())
                .navigateMenuTipsTricks();
    }

    /**
     * @ID=GC-591
     */
    @Test
    public void checkVisibilityOfCreateNewsButtonForRegisteredUser() {
        EconewsPage econewsPage = loadApplication()
                .navigateMenuEconews();
        Assert.assertTrue(driver.findElements(By.cssSelector("#create-button")).size() == 1);
    }

    /**
     * @ID=GC-595
     */
    @Test
    public void checkThatUserOnCreateNewsForm() {
        CreateNewsPage createNewsPage = loadApplication()
                .navigateMenuEconews()
                .gotoCreateNewsPage();
        WebElement createNewsMainTitle = driver.findElement(By.cssSelector(".title h2"));
        int numberOfButtons = driver.findElements(By.cssSelector(".submit-buttons button")).size();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(createNewsMainTitle.getText(), "Create news");
        softAssert.assertTrue(numberOfButtons == 3);
        softAssert.assertAll();
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
        new DBQueries().deleteNewsByTitle(newsData.getTitle());
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
        new DBQueries().deleteNewsByTitle(newsData.getTitle());
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
        new DBQueries().deleteNewsByTitle(newsData.getTitle());
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
     * @ID=621-1300
     */
    @Test
    public void checkPreviewPage() {
        driver.navigate().to(CREATE_NEWS_URL);
        CreateNewsPage createNewsPage = new CreateNewsPage(driver);
        PreViewPage preViewPage = createNewsPage
                .fillFields(NewsDataRepository.getRequiredFieldsNews())
                .goToPreViewPage();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(preViewPage.isPublishButtonPresent(), "Publish button is not present");
        System.out.println(preViewPage.getDateFieldText());
        softAssert.assertTrue(preViewPage.getDateFieldText()
                .equals(DateUtil.getCurrentDate("MMM dd, yyyy")),
                "Data is not match with today or may be has incorrect format");
        softAssert.assertAll();
    }

    /**
     * @ID=403-1303
     */
    @Test
    public void fillCreateNewsPreviewGoBackEcoNewsCreateCheckEmptyFields() {
        CreateNewsPage createNewsPage = loadCreateNewsPage()
                .fillFields(NewsDataRepository.getRequiredFieldsNews())
                .goToPreViewPage()
                .backToCreateNewsPage()
                .cancelNewsCreating()
                .gotoCreateNewsPage();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(createNewsPage.getTitleFieldText(), "");
        softAssert.assertEquals(createNewsPage.getSourceFieldText(), "");
        softAssert.assertEquals(createNewsPage.getContentFieldText(), "");
        softAssert.assertEquals(createNewsPage.getSelectedTagsNames().size(), 0);
        softAssert.assertAll();
    }

    public CreateNewsPage loadCreateNewsPage() {
        return loadApplication()
                .navigateMenuEconews()
                .gotoCreateNewsPage();
    }
}

