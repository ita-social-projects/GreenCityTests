package com.softserve.edu.greencity.ui.tests.viewallnews;

import com.softserve.edu.greencity.ui.data.User;
import com.softserve.edu.greencity.ui.data.UserRepository;
import com.softserve.edu.greencity.ui.data.econews.NewsData;
import com.softserve.edu.greencity.ui.data.econews.NewsDataRepository;
import com.softserve.edu.greencity.ui.pages.econews.EcoNewsPage;
import com.softserve.edu.greencity.ui.pages.econews.ItemComponent;
import com.softserve.edu.greencity.ui.tests.runner.GreenCityTestRunner;
import com.softserve.edu.greencity.ui.tests.runner.RetryAnalyzerImpl;
import com.softserve.edu.greencity.ui.tools.jdbc.dao.EcoNewsDao;
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


    @BeforeClass
    public void widthData() {
        screenWidth = Arrays.asList(1140, 1024, 1007, 1005, 992, 991, 576, 575, 320);

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
        softAssert.assertTrue(page.isActiveGridView()) ;
        softAssert.assertAll();
    }

    @Test(description = "GC-336")
    public void twelveNewsDisplayed() {
        logger.info("twelveNewsDisplayed starts");
        EcoNewsPage page = loadApplication()
                .navigateMenuEcoNews();
        softAssert
                .assertTrue(
                            page.getTopicsInPage()
                                .size() > 11);
        logger.info("elements found: " +
                         page.getTopicsInPage()
                        .size());
        softAssert.assertAll();
    }

    @Test
    @Description("GC-666")
    public void datePxLengthTest() {
        logger.info("Date px length test");
        EcoNewsPage econewsPage = loadApplication().navigateMenuEcoNews();
        econewsPage.updateArticlesExistCount().scrollDown();
        List<WebElement> elements = econewsPage.getDisplayedArticles();

        for (WebElement element : elements) {
            logger.info(String.valueOf(econewsPage.getCreationDateLength(element)));
            logger.info("assert that date:" + " length <= 104px");
            softAssert.assertTrue(econewsPage.getCreationDateLength(element) < 105,//requirements may be changed. Actual width is up to 104
                    "assert that length <= 104px");
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
        logger.info("Number of columns depending on screen width " + screenWidth);
        EcoNewsPage ecoNewsPage = loadApplication().navigateMenuEcoNews();
        for (Integer integer : screenWidth) {
            ecoNewsPage.changeWindowWidth(integer);
            ecoNewsPage.countNewsColumns(integer);
        }
        softAssert.assertAll(); //Asserts are hidden in countNewsColumns
    }



    @Test(testName = "GC-669")
    @Description("Verify UI of the News page in Gallery view for different screen resolutions")
    public void verifyingUIForDifferentScreenResolutionTest() {
        EcoNewsPage ecoNewsPage = loadApplication().navigateMenuEcoNewsMinimized();
        for (Integer integer : screenWidth) {
            ecoNewsPage.changeWindowWidth(integer);
            ecoNewsPage.isUiElementsDisplayedWithDifferentScreenResolution();
        }
    }

    @Test(testName = "GC-340")
    public void verifyContentItemsUITest() {
        logger.info("Verify Content items UI");
        EcoNewsPage ecoNewsPage = loadApplication().navigateMenuEcoNews();
        ItemComponent itemComponent = ecoNewsPage.getItemsContainer().chooseNewsByNumber(0);
        softAssert.assertEquals(itemComponent.getImage().getSize().height,
                206, "Image height"); //Valid result when image height is 206. Requirements could be changed
        softAssert.assertEquals(itemComponent.getTagsContainer().getSize().height,
                24, "Tags height");
        softAssert.assertTrue(itemComponent.getTitleHeight() + itemComponent.getContentHeight() + 8 <
                    201, "Title and Content height"); // Depends on size of Title and Content. Probably max height is 200
        softAssert.assertEquals(itemComponent.getDateOfCreation().getSize().height,
                21, "Date height");
        softAssert.assertEquals(itemComponent.getTagsContainer().getLocation().y - (itemComponent.getImage().getLocation().y + itemComponent.getImage().getSize().height), //Valid result when image height is 206. Requirements could be changed
                8, "Height between image and tags");
        softAssert.assertEquals(itemComponent.getTitle().getLocation().y - (itemComponent.getTagsContainer().getLocation().y + itemComponent.getTagsContainer().getSize().height),
                8, "Height between title and tags");
        softAssert.assertEquals(itemComponent.getContent().getLocation().y - (itemComponent.getTitle().getLocation().y + itemComponent.getTitleHeight()),
                10, "Height between content and title"); // Requirements could be changed
        softAssert.assertEquals(itemComponent.getDateOfCreation().getLocation().y - (itemComponent.getContent().getLocation().y + itemComponent.getContentHeight()),
                16, "Height between date and content");
        softAssert.assertEquals(itemComponent.getTitle().getLocation().x - itemComponent.getImage().getLocation().x,
                24, "Width between image and title (left side)");
        softAssert.assertEquals(itemComponent.getTagsContainer().getLocation().x - itemComponent.getImage().getLocation().x,
                24, "Width between image and tags (left side)");
        softAssert.assertEquals(itemComponent.getContent().getLocation().x - itemComponent.getImage().getLocation().x,
                24, "Width between image and content (left side)");
        softAssert.assertEquals(itemComponent.getDateOfCreation().getLocation().x - itemComponent.getImage().getLocation().x,
                24, "Width between image creation date");
        softAssert.assertEquals((itemComponent.getImage().getLocation().x + itemComponent.getImage().getSize().width) - (itemComponent.getTitle().getLocation().x + itemComponent.getTitle().getSize().width),
                24, "Width between image and title (right side)");
        softAssert.assertEquals((itemComponent.getImage().getLocation().x + itemComponent.getImage().getSize().width) - (itemComponent.getTagsContainer().getLocation().x + itemComponent.getTagsContainer().getSize().width),
                24, "Width between image and tags (right side)");
        softAssert.assertEquals((itemComponent.getImage().getLocation().x + itemComponent.getImage().getSize().width) - (itemComponent.getContent().getLocation().x + itemComponent.getContent().getSize().width),
                24, "Width between image and content (right side)");
        softAssert.assertEquals((itemComponent.getImage().getLocation().x + itemComponent.getImage().getSize().width) - (itemComponent.getDateAndAuthorContainer().getLocation().x + itemComponent.getDateAndAuthorContainer().getSize().width),
                24, "Width between image and date with author container (right side)");
        softAssert.assertAll();
    }

    @Test
    @Description("GC-339")
    public void verifyDefaultImageTest() {
        logger.info("Verify that news article has default image if it was not uploaded");
        User user = UserRepository.get().temporary();
        NewsData newsData = NewsDataRepository.get().getNewsWithValidData();
        EcoNewsPage ecoNewsPage = loadApplication()
                .signIn()
                .getManualLoginComponent()
                .successfullyLogin(user)
                .navigateMenuEcoNews()
                .gotoCreateNewsPage()
                .fillFields(newsData)
                .publishNews();
        for (Integer integer : screenWidth) {
            ecoNewsPage.changeWindowWidth(integer);
            softAssert.assertEquals(ecoNewsPage.getImageAttribute(), defaultImagePath);
        }
        //Clean up
        EcoNewsService ecoNewsService = new EcoNewsService();
        ecoNewsService.deleteNewsByTitle(newsData.getTitle());

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

    //@Ignore //runs too long
    @Test
    @Description("GC-674")
    public void newsAligningTest() {
        logger.info("News aligning starts");
        EcoNewsPage ecoNewsPage = loadApplication().navigateMenuEcoNews();
        for (Integer integer : screenWidth) {
            logger.info("News aligning on screenWidth = " + integer);
            ecoNewsPage.changeWindowWidth(integer);
            ecoNewsPage.scrollDown();
            int newsFound = ecoNewsPage.getDisplayedArticles().size();
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

    //TODO Jira task9
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

    @Test
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
