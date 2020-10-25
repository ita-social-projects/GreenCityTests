package com.softserve.edu.greencity.ui.tests.viewallnews;

import com.softserve.edu.greencity.ui.data.User;
import com.softserve.edu.greencity.ui.data.UserRepository;
import com.softserve.edu.greencity.ui.data.econews.NewsDataRepository;
import com.softserve.edu.greencity.ui.pages.econews.EcoNewsPage;
import com.softserve.edu.greencity.ui.tests.runner.GreenCityTestRunner;
import com.softserve.edu.greencity.ui.tests.runner.RetryAnalyzerImpl;
import com.softserve.edu.greencity.ui.tools.jdbc.services.EcoNewsService;
import io.qameta.allure.Description;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EcoNewsGridViewTest extends GreenCityTestRunner {
    List<Integer> screenWidth;
    private String defaultImagePath = "resources/images/defaultImage.png";

    @DataProvider
    public static Object[][] windowWidth() {
        return new Object[][]{
                {320},
                {576},
                {768},
                {1024},
                {1140},
        };
    }

    @BeforeClass
    public void widthData() {
        screenWidth = Arrays.asList(1140, 1024, 992, 991, 576, 575, 320);

    }

    @BeforeMethod
    public void maximizeWindow() {
        driver.manage().window().maximize(); //Returning as it was, because some tests change width
    }

    /*<======================================Grid View==========================================>*/

    /*<======================================Grid View==========================================>*/

    @Test
    @Description("Select grid view")
    public void selectGridView() {
        logger.info("selectGridView starts");

        EcoNewsPage page = loadApplication()
                .navigateMenuEcoNews()
                .switchToListView()
                .switchToGridView();

        Assert.assertTrue(page.isActiveGridView(), "Grid view is  active:");
    }


    @Test(description = "GC-334")
    @Description("Open eco news")
    public void NavigateToEcoNews() {
        logger.info("NavigateToEcoNews starts");
        EcoNewsPage page = loadApplication()
                .navigateMenuEcoNews();
        softAssert.assertTrue(page.isActiveGridView());
        softAssert.assertAll();
    }

    @Test(description = "GC-336")
    public void twelveNewsDisplayed() {
        logger.info("twelveNewsDisplayed starts");
        EcoNewsPage page = loadApplication()
                .navigateMenuEcoNews();
        softAssert
                .assertTrue(
                        page
                                .getTopicsInPage()
                                .size() > 11);
        logger.info("elements found: " +
                page
                        .getTopicsInPage()
                        .size());
        softAssert.assertAll();
    }

    @Test(retryAnalyzer = RetryAnalyzerImpl.class)
    @Description("GC-666")
    public void datePxLengthTest() {
        logger.info("Date px length test");
        EcoNewsPage econewsPage = loadApplication().navigateMenuEcoNews();
        econewsPage.updateArticlesExistCount().scrollDown();
        List<WebElement> elements = econewsPage.getDisplayedArticles();

        for (WebElement element : elements) {
            logger.info("assert that date:" + " length <= 95px");
            softAssert.assertTrue(econewsPage.getCreationDateLength(element) < 96,
                    "assert that length <= 95px");
        }
        softAssert.assertAll();
    }

    @Test(retryAnalyzer = RetryAnalyzerImpl.class)
    @Description("GC-666")
    public void authorPxLengthTest() {
        logger.info("Author px length test");
        EcoNewsPage econewsPage = loadApplication().navigateMenuEcoNews();
        econewsPage.updateArticlesExistCount().scrollDown();
        List<WebElement> elements = econewsPage.getDisplayedArticles();

        for (WebElement element : elements) {
            logger.info("assert that length <= 160px");
            softAssert.assertTrue(econewsPage
                            .getCreationAuthorNameLength(element) < 161,
                    "assert that length <= 160px");
        }
        softAssert.assertAll();
    }

    @Test(testName = "GC-668")
    public void countOfColumnsInGridViewTest() {
        logger.info("Number of columns depending on screen width");
        EcoNewsPage ecoNewsPage = loadApplication().navigateMenuEcoNews();
        for (Integer integer : screenWidth) {
            ecoNewsPage.changeWindowWidth(integer);
            ecoNewsPage.countNewsColumns(integer);
        }
    }

    @Test(dataProvider = "windowWidth")
    @Description("GC-669")
    public void verifyingUIForDifferentScreenResolutionTest(int width) {
        logger.info("Verify UI of the News page in Gallery view for different screen resolutions");
        EcoNewsPage ecoNewsPage = loadApplication().navigateMenuEcoNewsMinimized();
        ecoNewsPage.changeWindowWidth(width);
        softAssert.assertTrue(ecoNewsPage.isGridViewDisplayed());
        ecoNewsPage.isUiElementsDisplayedWithDifferentScreenResolution();

    }

    @Test
    @Description("GC-340")
    public void verifyContentItemsUITest() {
        logger.info("Verify Content items UI");
        EcoNewsPage ecoNewsPage = loadApplication().navigateMenuEcoNews();
        ecoNewsPage.verifyContentItemsUI();

    }

    @Test
    @Description("GC-339")
    public void verifyDefaultImageTest() {
        logger.info("Verify that news article has default image if it was not uploaded");
        User user = UserRepository.get().temporary();
        EcoNewsPage ecoNewsPage = loadApplication()
                .signIn()
                .getManualLoginComponent()
                .successfullyLogin(user)
                .navigateMenuEcoNews()
                .gotoCreateNewsPage()
                .fillFields(NewsDataRepository.get().getNewsWithValidData())
                .publishNews();
        for (Integer integer : screenWidth) {
            ecoNewsPage.changeWindowWidth(integer);
            softAssert.assertEquals(ecoNewsPage.getImageAttribute(), defaultImagePath);
        }

    }

    @Test(retryAnalyzer = RetryAnalyzerImpl.class)
    @Description("GC-341")
    public void topicIsClickableTest() {
        logger.info("topicIsClickableTest test");
        EcoNewsPage econewsPage = loadApplication().navigateMenuEcoNews();
        econewsPage.updateArticlesExistCount().scrollDown();
        List<WebElement> elements = econewsPage.getDisplayedArticles();
        for (WebElement element : elements) {
            softAssert
                    .assertTrue(element.isEnabled(),
                            "topic is clickable");
        }
    }

    @Test
    @Description("GC-674")
    public void newsAligningTest() {
        logger.info("News aligning starts");
        EcoNewsPage ecoNewsPage = loadApplication().navigateMenuEcoNews();
        for (Integer integer : screenWidth) {
            logger.info("News aligning on screenWidth = " + integer);
            ecoNewsPage.changeWindowWidth(integer);
            int newsFound = ecoNewsPage.getItemsContainer().getItemComponentsCount();
            for (int i = 0; i < newsFound; i++) {
                logger.info("i = " + i);
                softAssert.assertEquals(ecoNewsPage.getItemsContainer().chooseNewsByNumber(i).getTitle().getLocation().x,
                        ecoNewsPage.getItemsContainer().chooseNewsByNumber(i).getContent().getLocation().x);
            }
        }

    }

    @Test
    @Description("GC-341")
    public void openTopicTest() {
        logger.info("openTopicTest test");
        EcoNewsPage econewsPage = loadApplication().navigateMenuEcoNews();
        econewsPage.getRandomTopic().click();
        logger.info("assert that Tags, Title, Date, Dot, Author, Image, Social buttons, Text, Source displayed");
        softAssert.assertTrue(econewsPage.getopenTopicTags().isDisplayed(), "Tags displayed");
        softAssert.assertTrue(econewsPage.getnewsTitle().isDisplayed(), "Title displayed");
        softAssert.assertTrue(econewsPage.getnewsInfoDate().isDisplayed(), "Date displayed");
        softAssert.assertTrue(econewsPage.getnewsInfoDot().isDisplayed(), "Dot displayed");
        softAssert.assertTrue(econewsPage.getnewsInfoAuthor().isDisplayed(), "Author displayed");
        softAssert.assertTrue(econewsPage.getnewsInfoImage().isDisplayed(), "Image displayed");
        softAssert.assertTrue(econewsPage.getnewsInfoSocialLinksImg().isDisplayed(), "Social buttons displayed");
        softAssert.assertTrue(econewsPage.getnewsInfoText().isDisplayed(), "Text displayed");
        softAssert.assertTrue(econewsPage.getnewsInfoSource().isDisplayed(), "Source displayed");
        softAssert.assertAll();

    }

    //TODO Jira task
    /*<======================================Front Bug==========================================>*/
    @Test
    @Description("Verify that all content in each article displayed GC-675")
    public void allContentDisplayedTest() {
        EcoNewsPage econewsPage = loadApplication().navigateMenuEcoNews();
        econewsPage.updateArticlesExistCount().scrollDown();
        List<WebElement> elements = econewsPage.getDisplayedArticles();
        econewsPage.isArticleContentDisplayed(elements);
    }


    @Test
    @Description("Verify that at least text content displayed in each article displayed GC-675")
    public void allTextContentDisplayedTest() {
        EcoNewsPage econewsPage = loadApplication().navigateMenuEcoNews();
        econewsPage.updateArticlesExistCount().scrollDown();
        List<WebElement> elements = econewsPage.getDisplayedArticles();
        econewsPage.isArticleTextContentDisplayed(elements);
    }

    @Test(retryAnalyzer = RetryAnalyzerImpl.class)
    @Description("Verify that at least text content displayed in each article displayed GC-337")
    public void chronologicalNewsTest() {
        logger.info("ChronologicalNewsTest");
        logger.info("Get dates from Front in chronological order");
        EcoNewsPage econewsPage = loadApplication().navigateMenuEcoNews();
        econewsPage.updateArticlesExistCount().scrollDown();
        List<WebElement> elements = econewsPage.getDisplayedArticles();
        List<String> dates = new ArrayList<>();
        for (WebElement element : elements)
            dates.add(econewsPage.getArticleCreationDate(element));
        logger.info("Get dates from DB in chronological order");
        List<String> datesDB = new ArrayList<>();
        EcoNewsService ecoNewsService = new EcoNewsService();
        ecoNewsService.getAllNewsOrderByDate().forEach(d -> datesDB.add(String.valueOf(d)));
        List<String> pureDateDB = new ArrayList<>();
        datesDB.forEach(d -> pureDateDB.add(econewsPage.formatChronologicalDateFromDB(d)));
        logger.info("compare  dates order in DB and front");
        softAssert.assertEquals(dates, pureDateDB,
                "assert dates order in DB and front");
        softAssert.assertAll();
    }
    /*<======================================Front Bug==========================================>*/

    /*<======================================Grid View==========================================>*/
}
