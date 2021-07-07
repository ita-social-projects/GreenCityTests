package com.softserve.edu.greencity.ui.tests.viewallnews;

import com.softserve.edu.greencity.data.users.User;
import com.softserve.edu.greencity.data.users.UserRepository;
import com.softserve.edu.greencity.data.econews.NewsData;
import com.softserve.edu.greencity.data.econews.NewsDataRepository;
import com.softserve.edu.greencity.ui.pages.econews.EcoNewsPage;
import com.softserve.edu.greencity.ui.pages.econews.ItemComponent;
import com.softserve.edu.greencity.ui.tests.runner.GreenCityTestRunner;
import com.softserve.edu.greencity.ui.tools.engine.WaitsSwitcher;
import com.softserve.edu.greencity.ui.tools.testng.LocalOnly;
import com.softserve.edu.greencity.ui.tools.testng.RetryAnalyzerImpl;
import com.softserve.edu.greencity.ui.tools.jdbc.services.EcoNewsService;
import io.qameta.allure.Description;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EcoNewsGridViewTest extends GreenCityTestRunner {
    List<Integer> screenWidth;
    private final String defaultImagePath = "resources/images/defaultImage.png";


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


    @Test(testName = "GC-334", description = "GC-334")
    @Description("Open eco news")
    public void NavigateToEcoNews() {
        logger.info("NavigateToEcoNews starts");
        EcoNewsPage page = loadApplication()
                .navigateMenuEcoNews();
        softAssert.assertTrue(page.isActiveGridView());
        softAssert.assertAll();
    }

    @Test(testName = "GC-336", description = "GC-336")
    public void twelveNewsDisplayed() {
        logger.info("twelveNewsDisplayed starts");
        EcoNewsPage ecoNewsPage = loadApplication().navigateMenuEcoNews();
        logger.info("Number of displayed news without scrolling down = " + ecoNewsPage.getDisplayedArticles().size());
        softAssert.assertTrue(ecoNewsPage.getDisplayedArticles().size() > 11);
        softAssert.assertAll();
    }


    @Test(testName = "GC-666", description = "GC-666")
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

    @Test(retryAnalyzer = RetryAnalyzerImpl.class ,testName = "GC-666", description = "GC-666")
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

    @Test(testName = "GC-668", description = "GC-668")
    public void countOfColumnsInGridViewTest() {
        logger.info("Number of columns depending on screen width " + screenWidth);
        EcoNewsPage ecoNewsPage = loadApplication().navigateMenuEcoNews();
        for (Integer integer : screenWidth) {
            ecoNewsPage.changeWindowWidth(integer);
            ecoNewsPage.countNewsColumns(integer);
        }
        softAssert.assertAll(); //Asserts are hidden in countNewsColumns
    }


    @Test(testName = "GC-669", description = "GC-669")
    @Description("Verify UI of the News page in Gallery view for different screen resolutions")
    public void verifyingUIForDifferentScreenResolutionTest() {
        EcoNewsPage ecoNewsPage = loadApplication().navigateMenuEcoNewsMinimized();
        for (Integer integer : screenWidth) {
            ecoNewsPage.changeWindowWidth(integer);
            ecoNewsPage.isUiElementsDisplayedWithDifferentScreenResolution();
        }
    }

    @Test(testName = "GC-340", description = "GC-340")
    public void verifyContentItemsUITest() {
        final int imageHeight = 206;                            //Valid result when image height is 206. Requirements could be changed
        final int tagsHeight = 24;
        final int titleAndContentHeight = 201;                  // Depends on size of Title and Content. Probably max height is 200
        final int dateHeight = 21;
        final int heightBetweenImageAndTags = 8 ;               //Valid result when image height is 206. Requirements could be changed
        final int heightBetweenTitleAndTags = 8;
        final int heightBetweenContentAndTitle = 10;            // Requirements could be changed
        final int widthBetweenImageAndTitleLeftSide = 24;
        final int widthBetweenImageAndTagsLeftSide = 24;
        final int widthBetweenImageAndContentLeftSide = 24;
        final int widthBetweenImageCreationDate = 24;
        final int widthBetweenImageAndTitleRightSide = 24;
        final int widthBetweenImageAndTagsRightSide = 24;
        final int widthBetweenImageAndContentRightSide = 24;
        logger.info("Verify Content items UI");
        EcoNewsPage ecoNewsPage = loadApplication().navigateMenuEcoNews();
        ItemComponent itemComponent = ecoNewsPage.getItemsContainer().chooseNewsByNumber(0);
        final int heightBetweenDateAndContent = (165 - itemComponent.getContentHeight()); //165->y.date - y.component
        final int widthBetweenImageAndDateWithAuthorContainerRightSide = (335-itemComponent.getDateAndAuthorContainer().getSize().width); //335-> x.image - x.cont + w.image
        softAssert.assertEquals(itemComponent.getImage().getSize().height,
                imageHeight, "Image height");
        softAssert.assertEquals(itemComponent.getTagsContainer().getSize().height,
                tagsHeight, "Tags height");
        softAssert.assertTrue(itemComponent.getTitleHeight() + itemComponent.getContentHeight() + heightBetweenTitleAndTags <
                titleAndContentHeight, "Title and Content height");
        softAssert.assertEquals(itemComponent.getDateOfCreation().getSize().height,
                dateHeight, "Date height");
        softAssert.assertEquals(itemComponent.getTagsContainer().getLocation().y - (itemComponent.getImage().getLocation().y + itemComponent.getImage().getSize().height),
                heightBetweenImageAndTags, "Height between image and tags");
        softAssert.assertEquals(itemComponent.getTitle().getLocation().y - (itemComponent.getTagsContainer().getLocation().y + itemComponent.getTagsContainer().getSize().height),
                heightBetweenTitleAndTags, "Height between title and tags");
        softAssert.assertEquals(itemComponent.getContent().getLocation().y - (itemComponent.getTitle().getLocation().y + itemComponent.getTitleHeight()),
                heightBetweenContentAndTitle, "Height between content and title");
        softAssert.assertEquals( (itemComponent.getDateOfCreation().getLocation().y - itemComponent.getContent().getLocation().y - itemComponent.getContentHeight()),
                heightBetweenDateAndContent, "Height between date and content");
        softAssert.assertEquals(itemComponent.getTitle().getLocation().x - itemComponent.getImage().getLocation().x,
                widthBetweenImageAndTitleLeftSide, "Width between image and title (left side)");
        softAssert.assertEquals(itemComponent.getTagsContainer().getLocation().x - itemComponent.getImage().getLocation().x,
                widthBetweenImageAndTagsLeftSide, "Width between image and tags (left side)");
        softAssert.assertEquals(itemComponent.getContent().getLocation().x - itemComponent.getImage().getLocation().x,
                widthBetweenImageAndContentLeftSide, "Width between image and content (left side)");
        softAssert.assertEquals(itemComponent.getDateOfCreation().getLocation().x - itemComponent.getImage().getLocation().x,
                widthBetweenImageCreationDate, "Width between image creation date");
        softAssert.assertEquals((itemComponent.getImage().getLocation().x + itemComponent.getImage().getSize().width) - (itemComponent.getTitle().getLocation().x + itemComponent.getTitle().getSize().width),
                widthBetweenImageAndTitleRightSide, "Width between image and title (right side)");
        softAssert.assertEquals((itemComponent.getImage().getLocation().x + itemComponent.getImage().getSize().width) - (itemComponent.getTagsContainer().getLocation().x + itemComponent.getTagsContainer().getSize().width),
                widthBetweenImageAndTagsRightSide, "Width between image and tags (right side)");
        softAssert.assertEquals((itemComponent.getImage().getLocation().x + itemComponent.getImage().getSize().width) - (itemComponent.getContent().getLocation().x + itemComponent.getContent().getSize().width),
                widthBetweenImageAndContentRightSide, "Width between image and content (right side)");
        softAssert.assertEquals((itemComponent.getImage().getLocation().x + itemComponent.getImage().getSize().width) - (itemComponent.getDateAndAuthorContainer().getLocation().x + itemComponent.getDateAndAuthorContainer().getSize().width),
                widthBetweenImageAndDateWithAuthorContainerRightSide, "Width between image and date with author container (right side)");
        softAssert.assertAll();
    }

    @Test(testName = "GC-339", description = "GC-339")
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
            softAssert.assertEquals(ecoNewsPage.getImageAttribute(newsData), defaultImagePath);
        }
        //Clean up
        EcoNewsService ecoNewsService = new EcoNewsService();
        ecoNewsService.deleteNewsByTitle(newsData.getTitle());

    }

    @Test(retryAnalyzer = RetryAnalyzerImpl.class,testName = "GC-341", description = "GC-341")
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

    @Test(testName = "GC-674", description = "GC-674")
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

    @Test(testName = "GC-341", description = "GC-341")
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
        econewsPage.switchToGridView().isArticleContentDisplayed(elements);
    }


    @Test(testName = "GC-675", description = "GC-675")
    @Description("Verify that at least text content displayed in each article displayed GC-675")
    public void allTextContentDisplayedTest() {
        EcoNewsPage econewsPage = loadApplication().navigateMenuEcoNews();
        econewsPage.updateArticlesExistCount().scrollDown();
        List<WebElement> elements = econewsPage.getDisplayedArticles();
        econewsPage.switchToGridView().isArticleTextContentDisplayed(elements);
    }

    @LocalOnly //On remote machine the time differs
    @Test(testName = "GC-337", description = "GC-337")
    @Description("Verify that at least text content displayed in each article displayed GC-337")
    public void chronologicalNewsTest() {
        logger.info("ChronologicalNewsTest");
        EcoNewsPage econewsPage = loadApplication().navigateMenuEcoNews();
        logger.info("Get dates from DB in chronological order");
        List<String> datesDB = new ArrayList<>();
        EcoNewsService ecoNewsService = new EcoNewsService();
        ecoNewsService.getAllNewsOrderByDate().forEach(d -> datesDB.add(String.valueOf(d)));
        List<String> pureDateDB = new ArrayList<>();
        datesDB.forEach(d -> pureDateDB.add(econewsPage.formatChronologicalDateFromDB(d)));
        logger.info("Get dates from Front in chronological order");
        List<String> dates = getDates(econewsPage);
        while(dates.size()!= pureDateDB.size()){
            econewsPage.refreshPage();
            econewsPage.navigateMenuEcoNews();
            dates = getDates(econewsPage);
        }
        logger.info("compare  dates order in DB and front");
        softAssert.assertEquals(dates, pureDateDB,
                "assert dates order in DB and front");
        softAssert.assertAll();
    }

    private List<String> getDates(EcoNewsPage econewsPage) {
        econewsPage.updateArticlesExistCount().scrollDown();
        WaitsSwitcher.sleep(3000);
        List<WebElement> elements = econewsPage.getDisplayedArticles();
        List<String> dates = new ArrayList<>();
        for (WebElement element : elements)
            dates.add(econewsPage.getArticleCreationDate(element));
        return dates;
    }
    /*<======================================Front Bug==========================================>*/

    /*<======================================Grid View==========================================>*/
}
