package com.softserve.edu.greencity.ui.tests;
import java.util.ArrayList;
import java.util.List;

import com.softserve.edu.greencity.ui.data.User;
import com.softserve.edu.greencity.ui.pages.econews.EcoNewsPage;
import com.softserve.edu.greencity.ui.tests.runner.GreenCityTestRunner;
import com.softserve.edu.greencity.ui.tests.runner.RetryAnalyzerImpl;
import com.softserve.edu.greencity.ui.tools.jdbc.services.EcoNewsService;
import io.qameta.allure.Description;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class EcoNewsGridViewTest extends GreenCityTestRunner {

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
        softAssert.assertTrue(page.isActiveGridView()) ;
        softAssert.assertAll();
    }

    @Test(description = "GC-336")
    public void twelveNewsDisplayed(){
        logger.info("twelveNewsDisplayed starts");
        EcoNewsPage page = loadApplication()
                .navigateMenuEcoNews();
        softAssert
                .assertTrue(
                        page
                                .getTopicsInPage()
                                .size()>11);
        logger.info("elements found: "+
                page
                        .getTopicsInPage()
                        .size());
        softAssert.assertAll();
    }

    @Test(retryAnalyzer= RetryAnalyzerImpl.class)
    @Description("GC-666")
    public void	datePxLengthTest(){
        logger.info("Date px length test");
        EcoNewsPage econewsPage = loadApplication().navigateMenuEcoNews();
        econewsPage.updateArticlesExistCount().scrollDown();
        List<WebElement> elements = econewsPage.getDisplayedArticles();

        for (WebElement element : elements) {
            logger.info("assert that date:" +  " length <= 95px");
            softAssert.assertTrue(econewsPage.getCreationDateLength(element) < 96,
                    "assert that length <= 95px");
        }
        softAssert.assertAll();
    }

    @Test(retryAnalyzer= RetryAnalyzerImpl.class)
    @Description("GC-666")
    public void	authorPxLengthTest(){
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
    @DataProvider
    public static Object[][] windowWidth(){
        return new Object[][]{
                {320},
                {576},
                {768},
                {1024},
                {1140},
        };
    }

    @Test(dataProvider = "windowWidth")
    @Description("GC-668")
    public void countOfColumnsInGridViewTest(int width) {
        logger.info("Number of columns depending on screen width");
        EcoNewsPage ecoNewsPage = loadApplication().navigateMenuEcoNewsMinimized();
        ecoNewsPage.changeWindowWidth(width);
        if(width > 1024)
            softAssert.assertTrue(ecoNewsPage.countNewsColumns() == 3);
        if ((width < 769) && (width > 575))
            softAssert.assertTrue(ecoNewsPage.countNewsColumns() == 2);
        if(width < 576)
            softAssert.assertTrue(ecoNewsPage.countNewsColumns() ==1 );
    }

    @Test(dataProvider = "windowWidth")
    @Description("GC-669")
    public void verifyingUIForDifferentScreenResolutionTest(int width) {
        logger.info("Verify UI of the News page in Gallery view for different screen resolutions");
        EcoNewsPage ecoNewsPage = loadApplication().navigateMenuEcoNewsMinimized();
        ecoNewsPage.changeWindowWidth(width);
        ecoNewsPage.isUiElementsDisplayedWithDifferentScreenResolution();
    }

    @Test(retryAnalyzer= RetryAnalyzerImpl.class)
    @Description("GC-341")
    public void topicIsClickableTest(){
        logger.info("topicIsClickableTest test");
        EcoNewsPage econewsPage = loadApplication().navigateMenuEcoNews();
        econewsPage.updateArticlesExistCount().scrollDown();
        List<WebElement> elements = econewsPage.getDisplayedArticles();
        for (WebElement element : elements) {
            softAssert
                    .assertTrue(element.isEnabled(),
                            "topc is clickble");
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
    @Description("GC-341")
    public void openTopicTest(){
        logger.info("openTopicTest test");
        EcoNewsPage econewsPage = loadApplication().navigateMenuEcoNews();
        econewsPage.getRandomTopic().click();
        logger.info("assert that Tags, Title, Date, Dot, Author, Image, Social buttons, Text, Source displayed");
        softAssert.assertTrue(econewsPage.getopenTopicTags().isDisplayed(),"Tags displayed");
        softAssert.assertTrue(econewsPage.getnewsTitle().isDisplayed(),"Title displayed");
        softAssert.assertTrue(econewsPage.getnewsInfoDate().isDisplayed(),"Date displayed");
        softAssert.assertTrue(econewsPage.getnewsInfoDot().isDisplayed(),"Dot displayed");
        softAssert.assertTrue(econewsPage.getnewsInfoAuthor().isDisplayed(),"Author displayed");
        softAssert.assertTrue(econewsPage.getnewsInfoImage().isDisplayed(),"Image displayed");
        softAssert.assertTrue(econewsPage.getnewsInfoSocialLinksImg().isDisplayed(),"Social buttons displayed");
        softAssert.assertTrue(econewsPage.getnewsInfoText().isDisplayed(),"Text displayed");
        softAssert.assertTrue(econewsPage.getnewsInfoSource().isDisplayed(),"Source displayed");
        softAssert.assertAll();

    }

    //TODO Jira task
    /*<======================================Front Bug==========================================>*/
    @Test
    @Description("Verify that all content in each article displayed GC-675")
    public void allContentDisplayedTest(){
        EcoNewsPage econewsPage = loadApplication().navigateMenuEcoNews();
        econewsPage.updateArticlesExistCount().scrollDown();
        List<WebElement> elements = econewsPage.getDisplayedArticles();
        econewsPage.isArticleContentDisplayed(elements);
    }


    @Test
    @Description("Verify that at least text content displayed in each article displayed GC-675")
    public void allTextContentDisplayedTest(){
        EcoNewsPage econewsPage = loadApplication().navigateMenuEcoNews();
        econewsPage.updateArticlesExistCount().scrollDown();
        List<WebElement> elements = econewsPage.getDisplayedArticles();
        econewsPage.isArticleTextContentDisplayed(elements);
    }

    @Test(retryAnalyzer= RetryAnalyzerImpl.class)
    @Description("Verify that at least text content displayed in each article displayed GC-337")
    public void chronologicalNewsTest(){
        logger.info("ChronologicalNewsTest");
        logger.info("Get dates from Front in chronological order");
        EcoNewsPage econewsPage = loadApplication().navigateMenuEcoNews();
        econewsPage.updateArticlesExistCount().scrollDown();
        List<WebElement> elements = econewsPage.getDisplayedArticles();
        List <String> dates = new ArrayList<>();
        for (WebElement element : elements)
            dates.add(econewsPage.getArticleCreationDate(element));
        logger.info("Get dates from DB in chronological order");
        List <String> datesDB = new ArrayList<>();
        EcoNewsService ecoNewsService = new EcoNewsService();
        ecoNewsService.getAllNewsOrderByDate().forEach(d -> datesDB.add(String.valueOf(d)));
        List <String> pureDateDB = new ArrayList<>();
        datesDB.forEach(d -> pureDateDB.add(econewsPage.formatChronologicalDateFromDB(d)));
        logger.info("compare  dates order in DB and front");
        softAssert.assertEquals(dates,pureDateDB,
                "assert dates order in DB and front");
        softAssert.assertAll();
    }
    /*<======================================Front Bug==========================================>*/

    /*<======================================Grid View==========================================>*/
}
