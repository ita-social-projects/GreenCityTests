package com.softserve.edu.greencity.ui.tests;
import java.util.ArrayList;
import java.util.List;
import com.softserve.edu.greencity.ui.pages.econews.EcoNewsPage;
import com.softserve.edu.greencity.ui.tests.runner.GreenCityTestRunner;
import com.softserve.edu.greencity.ui.tests.runner.RetryAnalyzerImpl;
import com.softserve.edu.greencity.ui.tools.jdbc.services.EcoNewsService;
import io.qameta.allure.Description;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class EcoNewsGridViewTest extends GreenCityTestRunner {
    @BeforeTest
    private SoftAssert assertSoftly(){
        return new  SoftAssert();
    }

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
        assertSoftly().assertTrue(page.isActiveGridView()) ;
        assertSoftly().assertAll();
    }

    @Test(description = "GC-336")
    public void twelveNewsDisplayed(){
        logger.info("twelveNewsDisplayed starts");
        EcoNewsPage page = loadApplication()
                .navigateMenuEcoNews();
        assertSoftly()
                .assertTrue(
                        page
                                .getTopicsInPage()
                                .size()>11);
        logger.info("elements found: "+
                page
                        .getTopicsInPage()
                        .size());
        assertSoftly().assertAll();
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
            assertSoftly().assertTrue(econewsPage.getCreationDateLength(element) < 96,
                    "assert that length <= 95px");
        }
        assertSoftly().assertAll();
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
            assertSoftly().assertTrue(econewsPage
                            .getCreationAuthorNameLength(element) < 161,
                    "assert that length <= 160px");
        }
        assertSoftly().assertAll();
    }

    @Test(retryAnalyzer= RetryAnalyzerImpl.class)
    @Description("GC-341")
    public void topicIsClickableTest(){
        logger.info("topicIsClickableTest test");
        EcoNewsPage econewsPage = loadApplication().navigateMenuEcoNews();
        econewsPage.updateArticlesExistCount().scrollDown();
        List<WebElement> elements = econewsPage.getDisplayedArticles();
        for (WebElement element : elements) {
            assertSoftly()
                    .assertTrue(element.isEnabled(),
                            "topc is clickble");
        }
    }

    @Test
    @Description("GC-341")
    public void openTopicTest(){
        logger.info("openTopicTest test");
        EcoNewsPage econewsPage = loadApplication().navigateMenuEcoNews();
        econewsPage.getRandomTopic().click();
        logger.info("assert that Tags, Title, Date, Dot, Author, Image, Social buttons, Text, Source displayed");
        assertSoftly().assertTrue(econewsPage.getopenTopicTags().isDisplayed(),"Tags displayed");
        assertSoftly().assertTrue(econewsPage.getnewsTitle().isDisplayed(),"Title displayed");
        assertSoftly().assertTrue(econewsPage.getnewsInfoDate().isDisplayed(),"Date displayed");
        assertSoftly().assertTrue(econewsPage.getnewsInfoDot().isDisplayed(),"Dot displayed");
        assertSoftly().assertTrue(econewsPage.getnewsInfoAuthor().isDisplayed(),"Author displayed");
        assertSoftly().assertTrue(econewsPage.getnewsInfoImage().isDisplayed(),"Image displayed");
        assertSoftly().assertTrue(econewsPage.getnewsInfoSocicalLinksImg().isDisplayed(),"Social buttons displayed");
        assertSoftly().assertTrue(econewsPage.getnewsInfoText().isDisplayed(),"Text displayed");
        assertSoftly().assertTrue(econewsPage.getnewsInfoSource().isDisplayed(),"Source displayed");
        assertSoftly().assertAll();

    }

    //TODO Jira task
    /*<======================================Front Bug==========================================>*/
    //@Test
    @Description("Verify that all content in each article displayed GC-675")
    public void allContentDisplayedTest(){
        EcoNewsPage econewsPage = loadApplication().navigateMenuEcoNews();
        econewsPage.updateArticlesExistCount().scrollDown();
        List<WebElement> elements = econewsPage.getDisplayedArticles();
        econewsPage.isArticleContentDisplayed(elements);
    }


    //@Test
    @Description("Verify that at least text content displayed in each article displayed GC-675")
    public void allTextContentDisplayedTest(){
        EcoNewsPage econewsPage = loadApplication().navigateMenuEcoNews();
        econewsPage.updateArticlesExistCount().scrollDown();
        List<WebElement> elements = econewsPage.getDisplayedArticles();
        econewsPage.isArticleTextContentDisplayed(elements);
    }

    //@Test(retryAnalyzer= RetryAnalyzerImpl.class)
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
        assertSoftly().assertEquals(dates,pureDateDB,
                "assert dates order in DB and front");
        assertSoftly().assertAll();
    }
    /*<======================================Front Bug==========================================>*/

    /*<======================================Grid View==========================================>*/
}
