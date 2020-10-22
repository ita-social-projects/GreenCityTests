package com.softserve.edu.greencity.ui.tests;

import com.softserve.edu.greencity.ui.pages.econews.EcoNewsPage;
import com.softserve.edu.greencity.ui.tests.runner.GreenCityTestRunner;
import com.softserve.edu.greencity.ui.tests.runner.RetryAnalyzerImpl;
import com.softserve.edu.greencity.ui.tools.jdbc.services.EcoNewsService;
import io.qameta.allure.Description;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.softserve.edu.greencity.ui.locators.EcoNewsPageLocator.*;
import static com.softserve.edu.greencity.ui.locators.EcoNewsPageLocator.DISPLAYED_ARTICLES;

public class EcoNewsGridViewTest extends GreenCityTestRunner {
    List<Integer> screenWidth;

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

    /*<======================================Grid View==========================================>*/

    @BeforeTest
    private SoftAssert assertSoftly() {
        return new SoftAssert();
    }

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
        assertSoftly().assertTrue(page.isActiveGridView());
        assertSoftly().assertAll();
    }

    @Test(description = "GC-336")
    public void twelveNewsDisplayed() {
        logger.info("twelveNewsDisplayed starts");
        EcoNewsPage page = loadApplication()
                .navigateMenuEcoNews();
        assertSoftly()
                .assertTrue(
                        page
                                .getTopicsInPage()
                                .size() > 11);
        logger.info("elements found: " +
                page
                        .getTopicsInPage()
                        .size());
        assertSoftly().assertAll();
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
            assertSoftly().assertTrue(econewsPage.getCreationDateLength(element) < 96,
                    "assert that length <= 95px");
        }
        assertSoftly().assertAll();
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
            assertSoftly().assertTrue(econewsPage
                            .getCreationAuthorNameLength(element) < 161,
                    "assert that length <= 160px");
        }
        assertSoftly().assertAll();
    }

    @Test
    @Description("GC-668")
    public void countOfColumnsInGridViewTest() {
        logger.info("Number of columns depending on screen width");
        EcoNewsPage ecoNewsPage = loadApplication().navigateMenuEcoNewsMinimized();
        for(Integer integer : screenWidth){
        //for (Integer integer = 1024; integer > 992; integer--) {
            ecoNewsPage.changeWindowWidth(integer);
            logger.info("When width = " + integer);
            ecoNewsPage.countNewsColumns();
        }
        assertSoftly().assertAll();
    }

    @Ignore
    @Test(dataProvider = "windowWidth")
    @Description("GC-668")
    public void countOfColumnsInGridViewTest(int width) {
        logger.info("----------------------------------------------------------------------------------");
        logger.info("Number of columns depending on screen width");
        EcoNewsPage ecoNewsPage = loadApplication().navigateMenuEcoNewsMinimized();
        ecoNewsPage.changeWindowWidth(width);
        logger.info("When width = " + width);
        ecoNewsPage.countNewsColumns();
        assertSoftly().assertAll();
    }


    @Test(dataProvider = "windowWidth")
    @Description("GC-669")
    public void verifyingUIForDifferentScreenResolutionTest(int width) {
        logger.info("Verify UI of the News page in Gallery view for different screen resolutions");
        EcoNewsPage ecoNewsPage = loadApplication().navigateMenuEcoNewsMinimized();
        ecoNewsPage.changeWindowWidth(width);
        assertSoftly().assertTrue(ecoNewsPage.isGridViewDisplayed());
        ecoNewsPage.isUiElementsDisplayedWithDifferentScreenResolution();
    }

    @Test(retryAnalyzer = RetryAnalyzerImpl.class)
    @Description("GC-341")
    public void topicIsClickableTest() {
        logger.info("topicIsClickableTest test");
        EcoNewsPage econewsPage = loadApplication().navigateMenuEcoNews();
        econewsPage.updateArticlesExistCount().scrollDown();
        List<WebElement> elements = econewsPage.getDisplayedArticles();
        for (WebElement element : elements) {
            assertSoftly()
                    .assertTrue(element.isEnabled(),
                            "topic is clickable");
        }
    }

    @Test
    @Description("GC-340")
    public void verifyContentItemsUITest() {
        logger.info("Verify Content items UI");
        EcoNewsPage ecoNewsPage = loadApplication().navigateMenuEcoNews();
        ecoNewsPage.verifyContentItemsUI();
    }

    @Test
    @Description("GC-674")
    public void newsAligningTest() {
        logger.info("News aligning");
        EcoNewsPage ecoNewsPage = loadApplication().navigateMenuEcoNews();
        for (Integer integer : screenWidth) {
            ecoNewsPage.changeWindowWidth(integer);
            for (int i = 0; i < ecoNewsPage.getItemsContainer().getItemComponentsCount(); i++) {
                assertSoftly().assertEquals(ecoNewsPage.getItemsContainer().chooseNewsByNumber(i).getTitle().getLocation().x,
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
        assertSoftly().assertTrue(econewsPage.getopenTopicTags().isDisplayed(), "Tags displayed");
        assertSoftly().assertTrue(econewsPage.getnewsTitle().isDisplayed(), "Title displayed");
        assertSoftly().assertTrue(econewsPage.getnewsInfoDate().isDisplayed(), "Date displayed");
        assertSoftly().assertTrue(econewsPage.getnewsInfoDot().isDisplayed(), "Dot displayed");
        assertSoftly().assertTrue(econewsPage.getnewsInfoAuthor().isDisplayed(), "Author displayed");
        assertSoftly().assertTrue(econewsPage.getnewsInfoImage().isDisplayed(), "Image displayed");
        assertSoftly().assertTrue(econewsPage.getnewsInfoSocialLinksImg().isDisplayed(), "Social buttons displayed");
        assertSoftly().assertTrue(econewsPage.getnewsInfoText().isDisplayed(), "Text displayed");
        assertSoftly().assertTrue(econewsPage.getnewsInfoSource().isDisplayed(), "Source displayed");
        assertSoftly().assertAll();

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
        assertSoftly().assertEquals(dates, pureDateDB,
                "assert dates order in DB and front");
        assertSoftly().assertAll();
    }
    /*<======================================Front Bug==========================================>*/

    /*<======================================Grid View==========================================>*/
}
