package com.softserve.edu.greencity.ui.tests.viewallnews;

import com.softserve.edu.greencity.data.users.User;
import com.softserve.edu.greencity.data.users.UserRepository;
import com.softserve.edu.greencity.data.econews.NewsData;
import com.softserve.edu.greencity.data.econews.NewsDataRepository;
import com.softserve.edu.greencity.ui.pages.econews.EcoNewsPage;
import com.softserve.edu.greencity.ui.pages.econews.ItemComponent;
import com.softserve.edu.greencity.ui.tests.runner.GreenCityTestRunnerWithLoginLogout;
import com.softserve.edu.greencity.ui.tools.jdbc.services.EcoNewsService;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class EcoNewsListViewTests extends GreenCityTestRunnerWithLoginLogout {
    String cssColorProperty;
    String expectedColorRGBA;
    String expectedHoveredByMouseColorRGBA;
    List<Integer> screenWidth1, screenWidthWithContent, screenWidthWithoutImagesAndContent, screenWidthForTitleTests;
    Integer screenWidth2, screenWidth3;

    private final String DEFAULT_IMAGE = "https://ita-social-projects.github.io/GreenCityClient/assets/img/icon/econews/default-image-list-view.png";
    private static List<String> testNewsTitles = new ArrayList<>();

    @AfterTest(alwaysRun = true)
    private void deleteNewsIfCreated() {
        if (testNewsTitles.size() > 0) {
            EcoNewsService ecoNewsService = new EcoNewsService();
            testNewsTitles.forEach(ecoNewsService::deleteNewsByTitle);
        }
    }

    @BeforeClass
    public void beforeClass() {
        cssColorProperty = "color";
        expectedColorRGBA = "rgba(19, 170, 87, 1)"; //lighter
        expectedHoveredByMouseColorRGBA = "rgba(5, 107, 51, 1)"; //darker
        screenWidthWithContent = Arrays.asList(1440, 1200, 1024, 768);
        screenWidthWithoutImagesAndContent = Arrays.asList(667, 576, 590);
        screenWidthForTitleTests = Arrays.asList(1440, 1024, 768, 576);
        screenWidth1 = Arrays.asList(1400, 1024, 768);
        screenWidth2 = 576;
        screenWidth3 = 360;
    }

    @Test(testName = "GC-707", description = "GC-707")
    @Description("Verify that Content items are displayed as a list in case if 'List view' option is activated")
    public void isDisplayedListContent() {
        logger.info("Starting isDisplayedListContent");
        EcoNewsPage ecoNewsPage = loadApplication()
                .navigateMenuEcoNews()
                .switchToListView();

        Assert.assertTrue(ecoNewsPage.getItemsContainer().hasListViewClassActive());
    }


    @Test(testName = "GC-704", description = "GC-704")
    @Description("Verify that ‘List view’ icon is present on the 'Eco news' page")
    public void isPresentListView() {
        logger.info("Starting isPresentListView");
        EcoNewsPage ecoNewsPage = loadApplication()
                .navigateMenuEcoNews();

        softAssert.assertTrue(ecoNewsPage.isDisplayedListView(), "List view displayed");

        ecoNewsPage.hoverToListView();
        String hoverListViewIconColor = ecoNewsPage.getListViewButtonHoverColor();
        softAssert.assertEquals(hoverListViewIconColor, expectedHoveredByMouseColorRGBA, "List view hover");

        ecoNewsPage
                .switchToListView()
                .hoverToGridView();

        String listViewIconColor = ecoNewsPage.getListViewButtonComponent().getCssValue(cssColorProperty);
        softAssert.assertEquals(listViewIconColor, expectedColorRGBA, "List view no hover");
        softAssert.assertAll();
    }

    @Test(testName = "GC-710", description = "GC-710")
    @Description("Verify that 6 first Content items are displayed by default")
    public void isDisplayedFirstSixContent() {
        logger.info("Starting isDisplayedFirstSixContent");
        EcoNewsPage ecoNewsPage = loadApplication()
                .navigateMenuEcoNews()
                .switchToListView();

        Assert.assertTrue(ecoNewsPage.getItemsContainer().getItemsSize() >= 6);
    }

    @Test(testName = "GC-332", description = "GC-332")
    @Description("Verify that content items are displayed in a list view")
    public void isDisplayedContentItems() {
        logger.info("Starting isDisplayedContentItems");
        EcoNewsPage ecoNewsPage = loadApplication()
                .navigateMenuEcoNews()
                .switchToListView();

        String listViewIconColor = ecoNewsPage.getListViewButtonComponent().getCssValue(cssColorProperty);

        softAssert.assertEquals(listViewIconColor, expectedColorRGBA);
        softAssert.assertTrue(ecoNewsPage.getItemsContainer().getItemsSize() >= 6);
        softAssert.assertAll();
    }

    @Test(testName = "GC-333", description = "GC-333")
    @Description("Verify that content items have all specified elements.")
    public void isPresentAllItemElements() {
        logger.info("Starting isPresentAllItemElements");
        EcoNewsPage ecoNewsPage = loadApplication()
                .navigateMenuEcoNews()
                .switchToListView();

        ItemComponent firstItem = ecoNewsPage.getItemsContainer().chooseNewsByNumber(1);

        softAssert.assertTrue(firstItem.isDisplayedImage());
        softAssert.assertTrue(firstItem.isDisplayedTags());
        softAssert.assertTrue(firstItem.isDisplayedTitle());
        softAssert.assertTrue(firstItem.isDisplayedContent());
        softAssert.assertTrue(firstItem.isDisplayedDateOfCreation());
        softAssert.assertTrue(firstItem.isDisplayedAuthor());
        softAssert.assertTrue(firstItem.isCorrectDateFormat(firstItem.getDateOfCreationText()));

        softAssert.assertAll();
    }

    @Test(testName = "GC-352", description = "GC-352")
    @Description("Verify that Content items are displayed in chronological order")
    public void isItemsDisplayedChronological() {
        logger.info("isItemsDisplayedChronological");
        logger.info("isItemsDisplayedChronological");
        EcoNewsPage ecoNewsPage = loadApplication()
                .navigateMenuEcoNews()
                .switchToListView();

        Date date1 = ecoNewsPage.getItemsContainer().chooseNewsByNumber(0).getDateOfCreationDateFormat();
        Date date2 = ecoNewsPage.getItemsContainer().chooseNewsByNumber(1).getDateOfCreationDateFormat();

        softAssert.assertTrue((date1.compareTo(date2) == 0) || (date1.compareTo(date2) > 0));

        //JDBC
        EcoNewsService ecoNewsService = new EcoNewsService();
        Date firstNewsDate = ecoNewsService.getLastNewsCreationDateByTitle(ecoNewsPage.getItemsContainer().chooseNewsByNumber(0).getTitleText());
        Date secondNewsDate = ecoNewsService.getLastNewsCreationDateByTitle(ecoNewsPage.getItemsContainer().chooseNewsByNumber(1).getTitleText());

        softAssert.assertTrue(firstNewsDate.compareTo(secondNewsDate) > 0);

        softAssert.assertAll();
    }

    @Test(testName = "GC-720", description = "GC-720")
    @Description("Verify that content items contain all required UI elements according to mock-up.")
    public void isPresentAllContentElements() {
        logger.info("isPresentAllContentElements");
        User user = UserRepository.get().temporary();
        NewsData newsData = NewsDataRepository.get().getOneRowTitle();
        EcoNewsPage ecoNewsPage = loadApplication()
                .signIn()
                .getManualLoginComponent()
                .successfullyLogin(user)
                .navigateMenuEcoNews()
                .gotoCreateNewsPage()
                .fillFields(newsData)
                .publishNews();

        testNewsTitles.add(newsData.getTitle());

        for (Integer integer : screenWidthWithContent) {
            ecoNewsPage.changeWindowWidth(integer);
            logger.info("set width = " + integer);
            logger.info("script width = " + ecoNewsPage.getWindowWidth(integer));
            ecoNewsPage.switchToListView();
            softAssert.assertTrue(ecoNewsPage.getItemsContainer().findItemComponentByParameters(newsData).isDisplayedImage());
            logger.info("image " + ecoNewsPage.getItemsContainer().findItemComponentByParameters(newsData).isDisplayedImage());
            softAssert.assertTrue(ecoNewsPage.getItemsContainer().findItemComponentByParameters(newsData).isDisplayedTags());
            logger.info("tags " + ecoNewsPage.getItemsContainer().findItemComponentByParameters(newsData).isDisplayedTags());
            softAssert.assertTrue(ecoNewsPage.getItemsContainer().findItemComponentByParameters(newsData).isDisplayedTitle());
            logger.info("title " + ecoNewsPage.getItemsContainer().findItemComponentByParameters(newsData).isDisplayedTitle());
            softAssert.assertTrue(ecoNewsPage.getItemsContainer().findItemComponentByParameters(newsData).isDisplayedContent());
            logger.info("content " + ecoNewsPage.getItemsContainer().findItemComponentByParameters(newsData).isDisplayedContent());
            softAssert.assertTrue(ecoNewsPage.getItemsContainer().findItemComponentByParameters(newsData).isDisplayedDateOfCreation());
            logger.info("date " + ecoNewsPage.getItemsContainer().findItemComponentByParameters(newsData).isDisplayedDateOfCreation());
            softAssert.assertTrue(ecoNewsPage.getItemsContainer().findItemComponentByParameters(newsData).isCorrectDateFormat(ecoNewsPage.getItemsContainer().findItemComponentByParameters(newsData).getDateOfCreationText()));
            logger.info("dateformat " + ecoNewsPage.getItemsContainer().findItemComponentByParameters(newsData).isCorrectDateFormat(ecoNewsPage.getItemsContainer().findItemComponentByParameters(newsData).getDateOfCreationText()));
            softAssert.assertTrue(ecoNewsPage.getItemsContainer().findItemComponentByParameters(newsData).isDisplayedAuthor());
            logger.info("author " + ecoNewsPage.getItemsContainer().findItemComponentByParameters(newsData).isDisplayedAuthor());
        }

        for (Integer integer : screenWidthWithoutImagesAndContent) {
            ecoNewsPage.changeWindowWidth(integer);
            logger.info("set width = " + integer);
            logger.info("script width = " + ecoNewsPage.getWindowWidth(integer));
            ecoNewsPage.switchToListView();
            if (ecoNewsPage.isActiveListView()) {
                softAssert.assertFalse(ecoNewsPage.getItemsContainer().findItemComponentByParameters(newsData).isDisplayedImage(), "image");
                logger.info("image " + ecoNewsPage.getItemsContainer().findItemComponentByParameters(newsData).isDisplayedImage());
                softAssert.assertTrue(ecoNewsPage.getItemsContainer().findItemComponentByParameters(newsData).isDisplayedTags(), "tags");
                logger.info("tags " + ecoNewsPage.getItemsContainer().findItemComponentByParameters(newsData).isDisplayedTags());
                softAssert.assertTrue(ecoNewsPage.getItemsContainer().findItemComponentByParameters(newsData).isDisplayedTitle(), "title");
                logger.info("title " + ecoNewsPage.getItemsContainer().findItemComponentByParameters(newsData).isDisplayedTitle());
                softAssert.assertTrue(ecoNewsPage.getItemsContainer().findItemComponentByParameters(newsData).isDisplayedContent(), "content");
                logger.info("content " + ecoNewsPage.getItemsContainer().findItemComponentByParameters(newsData).isDisplayedContent());
                softAssert.assertTrue(ecoNewsPage.getItemsContainer().findItemComponentByParameters(newsData).isDisplayedDateOfCreation(), "date");
                logger.info("date " + ecoNewsPage.getItemsContainer().findItemComponentByParameters(newsData).isDisplayedDateOfCreation());
                softAssert.assertTrue(ecoNewsPage.getItemsContainer().findItemComponentByParameters(newsData)
                                .isCorrectDateFormat(ecoNewsPage.getItemsContainer().findItemComponentByParameters(newsData).getDateOfCreationText()),
                        "date format");
                logger.info("dateformat " + ecoNewsPage.getItemsContainer().findItemComponentByParameters(newsData).isCorrectDateFormat(ecoNewsPage.getItemsContainer().findItemComponentByParameters(newsData).getDateOfCreationText()));
                softAssert.assertTrue(ecoNewsPage.getItemsContainer().findItemComponentByParameters(newsData).isDisplayedAuthor(), "author");
                logger.info("author " + ecoNewsPage.getItemsContainer().findItemComponentByParameters(newsData).isDisplayedAuthor());
            }
        }
        ecoNewsPage.maximizeWindow();
        ecoNewsPage.signOut();
        softAssert.assertAll();
    }


    @Test(testName = "GC-725", description = "GC-725")
    @Description("Verify that displayed image in List view is default image if user didn’t choose own image during news creation.")
    public void isPresentDefaultImage() {
        logger.info("isPresentDefaultImage");
        User user = UserRepository.get().temporary();
        NewsData newsData = NewsDataRepository.get().getNewsWithValidData("Default image test");

        EcoNewsPage ecoNewsPage = loadApplication()
                .signIn()
                .getManualLoginComponent()
                .successfullyLogin(user)
                .navigateMenuEcoNews()
                .gotoCreateNewsPage()
                .fillFields(newsData)
                .publishNews();

        testNewsTitles.add(newsData.getTitle());

        for (Integer integer : screenWidth1) {
            logger.debug("Screen width: " + integer);
            ecoNewsPage.changeWindowWidth(integer);
            ecoNewsPage.switchToListView();
            String src = ecoNewsPage.getItemsContainer().findItemComponentByParameters(newsData).getImage().getAttribute("src");
            softAssert.assertEquals(src, DEFAULT_IMAGE);
        }

        logger.debug("Screen width: " + screenWidth2);
        ecoNewsPage.changeWindowWidth(screenWidth2);
        ecoNewsPage.switchToListView();
        softAssert.assertFalse(ecoNewsPage.getItemsContainer().findItemComponentByParameters(newsData).isDisplayedImage(), "image");

        logger.debug("Screen width: " + screenWidth3);
        ecoNewsPage.changeWindowWidth(screenWidth3);
        //On small screen resolution list view automatically switches off
        softAssert.assertFalse(ecoNewsPage.isListViewPresent(), "List view at " + screenWidth3 + " width");
        String src = ecoNewsPage.getItemsContainer().findItemComponentByParameters(newsData).getImage().getAttribute("src");
        softAssert.assertEquals(src, DEFAULT_IMAGE);

        ecoNewsPage.maximizeWindow();
        ecoNewsPage.signOut();
        softAssert.assertAll();
    }


    //TODO Modify after UI bug fixed
    @Test(testName = "GC-703", description = "GC-703")
    @Description("Verify that Title and Content text can be not higher than 104 px.")
    public void isTitleAndContentNotHigherThan() {
        logger.info("isTitleAndContentNotHigherThan");
        User user = UserRepository.get().temporary();
        NewsData newsData = NewsDataRepository.get().getHeightCheckContent();

        EcoNewsPage ecoNewsPage = loadApplication()
                .signIn()
                .getManualLoginComponent()
                .successfullyLogin(user)
                .navigateMenuEcoNews()
                .gotoCreateNewsPage()
                .fillFields(newsData)
                .publishNews();

        testNewsTitles.add(newsData.getTitle());

        for (Integer integer : screenWidthForTitleTests) {
            ecoNewsPage.changeWindowWidth(integer);
            ecoNewsPage.switchToListView();
            ItemComponent firstItemTitle = ecoNewsPage.getItemsContainer().findItemComponentByParameters(newsData);
            logger.info("titleHeight " + firstItemTitle.getTitleHeight() + "+ contentHeight " + firstItemTitle.getContentHeight() + " = " + (firstItemTitle.getTitleHeight() + firstItemTitle.getContentHeight()));
            int TitleAndContentHeight = (firstItemTitle.getTitleHeight() + firstItemTitle.getContentHeight());
            logger.info("Height = " + TitleAndContentHeight);
            softAssert.assertTrue(89 <= TitleAndContentHeight && TitleAndContentHeight <= 104); //TitleAndContentHeight block should be max 104 due to new requirements, but mock-up not updated yet, so cant open bug. Title should be 26px (1 row, max 4 rows -> 26*4=104. Content 21px (firstly displays all title rows, if there are free place(less then 4 title rows), only then content will be displayed). So, we have 104px (is max) if 4 rows of title and 89 px (is min size of block) if 1 row of title and other content (104 - 26px (title) = 78 -> 78:21 = 3.7 -> 3 rows of content -> 26 + 21*3 = 89)
        }
        ecoNewsPage.maximizeWindow();
        ecoNewsPage.signOut();
        softAssert.assertAll();
    }

    //TODO Modify after UI bug fixed
    @Test(testName = "GC-706", description = "GC-706")
    @Description("Verify that Title is displayed fully.")
    public void isTitleDisplayedFully() {
        logger.info("isTitleDisplayedFully");
        User user = UserRepository.get().temporary();
        NewsData newsData = NewsDataRepository.get().getHeightCheckContent();
        String expectedTitle = newsData.getTitle();

        EcoNewsPage ecoNewsPage = loadApplication()
                .signIn()
                .getManualLoginComponent()
                .successfullyLogin(user)
                .navigateMenuEcoNews()
                .gotoCreateNewsPage()
                .fillFields(newsData)
                .publishNews();

        testNewsTitles.add(newsData.getTitle());

        for (Integer integer : screenWidthForTitleTests) {
            ecoNewsPage.changeWindowWidth(integer);
            ecoNewsPage.switchToListView();
            ItemComponent firstItemTitle = ecoNewsPage.getItemsContainer().findItemComponentByParameters(newsData);
            softAssert.assertEquals(firstItemTitle.getTitleText(), expectedTitle);
        }
        ecoNewsPage.maximizeWindow();
        ecoNewsPage.signOut();
        softAssert.assertAll();
    }

    @Test(testName = "GC-708", description = "GC-708")
    @Description("Verify that when Title consist of 4 row, then Description consist of 0 row.")
    public void isZeroRowDescriptionWhenFourRowsTitle() {
        logger.info("isZeroRowDescriptionWhenFourRowsTitle");
        User user = UserRepository.get().temporary();
        NewsData newsData = NewsDataRepository.get().getFourRowsTitle();

        EcoNewsPage ecoNewsPage = loadApplication()
                .signIn()
                .getManualLoginComponent()
                .successfullyLogin(user)
                .navigateMenuEcoNews()
                .gotoCreateNewsPage()
                .fillFields(newsData)
                .publishNews();

        testNewsTitles.add(newsData.getTitle());

        ecoNewsPage.changeWindowWidth(1024);

        ecoNewsPage.switchToListView();

        softAssert.assertEquals(ecoNewsPage.getItemsContainer().findItemComponentByParameters(newsData).getTitleHeight(), 104);//mock-up not updated yet, but requirements changed (ask Vika and Alex, for sure), so cant open bug. Title should be 26px (1 row, max 4 rows -> 26*4=104. Content 21px (firstly displays all title rows, if there are free place(less then 4 title rows), only then content will be displayed).
        softAssert.assertEquals(ecoNewsPage.getItemsContainer().findItemComponentByParameters(newsData).getTitleNumberRow(), 4);
        softAssert.assertFalse(ecoNewsPage.getItemsContainer().findItemComponentByParameters(newsData).getContent().isDisplayed());
        softAssert.assertEquals(ecoNewsPage.getItemsContainer().findItemComponentByParameters(newsData).getContentNumberVisibleRow(), 0);

        ecoNewsPage.signOut();
        softAssert.assertAll();
    }


    @Test(testName = "GC-723", description = "GC-723")
    @Description("Verify that when Title consist of 3 row, then Description consist of 1 row.")
    public void isOneRowDescriptionWhenThreeRowsTitle() {
        logger.info("isOneRowDescriptionWhenThreeRowsTitle");
        User user = UserRepository.get().temporary();
        NewsData newsData = NewsDataRepository.get().getThreeRowsTitle();

        EcoNewsPage ecoNewsPage = loadApplication()
                .signIn()
                .getManualLoginComponent()
                .successfullyLogin(user)
                .navigateMenuEcoNews()
                .gotoCreateNewsPage()
                .fillFields(newsData)
                .publishNews();

        testNewsTitles.add(newsData.getTitle());

        ecoNewsPage.changeWindowWidth(1440);

        ecoNewsPage.switchToListView();

        ItemComponent firstItemTitle = ecoNewsPage.getItemsContainer().findItemComponentByParameters(newsData);
        softAssert.assertEquals(firstItemTitle.getTitleHeight(), 78);//mock-up not updated yet, but requirements changed (ask Vika and Alex, for sure), so cant open bug. Title should be 26px (1 row, max 4 rows -> 26*4=104. Content 21px (firstly displays all title rows, if there are free place(less then 4 title rows), only then content will be displayed). So 26*3+21=99 all, title 26*3=78
        softAssert.assertEquals(firstItemTitle.getTitleNumberRow(), 3);
        softAssert.assertEquals(firstItemTitle.getContentNumberVisibleRow(), 1);

        ecoNewsPage.signOut();
        softAssert.assertAll();
    }


    @Test(testName = "GC-722", description = "GC-722")
    @Description("Verify that when Title consist of 2 row, then Description consist of 2 row.")
    public void isTwoRowsDescriptionWhenTwoRowsTitle() {
        logger.info("isTwoRowsDescriptionWhenTwoRowsTitle");
        User user = UserRepository.get().temporary();
        NewsData newsData = NewsDataRepository.get().getTwoRowsTitle();

        EcoNewsPage ecoNewsPage = loadApplication()
                .signIn()
                .getManualLoginComponent()
                .successfullyLogin(user)
                .navigateMenuEcoNews()
                .gotoCreateNewsPage()
                .fillFields(newsData)
                .publishNews();

        testNewsTitles.add(newsData.getTitle());

        ecoNewsPage.changeWindowWidth(1440);

        ecoNewsPage.switchToListView();

        ItemComponent firstItemTitle = ecoNewsPage.getItemsContainer().findItemComponentByParameters(newsData);
        softAssert.assertEquals(firstItemTitle.getTitleHeight(), 52);//mock-up not updated yet, but requirements changed (ask Vika and Alex, for sure), so cant open bug. Title should be 26px (1 row, max 4 rows -> 26*4=104. Content 21px (firstly displays all title rows, if there are free place(less then 4 title rows), only then content will be displayed). 26*2+21*2=94 all, 52 title
        softAssert.assertEquals(firstItemTitle.getTitleNumberRow(), 2);
        softAssert.assertEquals(firstItemTitle.getContentNumberVisibleRow(), 2);

        ecoNewsPage.signOut();
        softAssert.assertAll();
    }


    @Test(testName = "GC-724", description = "GC-724")
    @Description("Verify that when Title consist of 1 row, then Description consist of 3 row.")
    public void isTwoRowsDescriptionWhenOneRowsTitle() {
        logger.info("isTwoRowsDescriptionWhenOneRowsTitle");
        User user = UserRepository.get().temporary();
        NewsData newsData = NewsDataRepository.get().getOneRowTitle();

        EcoNewsPage ecoNewsPage = loadApplication()
                .signIn()
                .getManualLoginComponent()
                .successfullyLogin(user)
                .navigateMenuEcoNews()
                .gotoCreateNewsPage()
                .fillFields(newsData)
                .publishNews();

        testNewsTitles.add(newsData.getTitle());

        ecoNewsPage.changeWindowWidth(1440);

        ecoNewsPage.switchToListView();

        ItemComponent firstItemTitle = ecoNewsPage.getItemsContainer().findItemComponentByParameters(newsData);
        softAssert.assertEquals(firstItemTitle.getTitleHeight(), 26);//mock-up not updated yet, but requirements changed (ask Vika and Alex, for sure), so cant open bug. Title should be 26px (1 row, max 4 rows -> 26*4=104. Content 21px (firstly displays all title rows, if there are free place(less then 4 title rows), only then content will be displayed). 26+21*3=89 all, 26 title
        softAssert.assertEquals(firstItemTitle.getTitleNumberRow(), 1);
        softAssert.assertEquals(firstItemTitle.getContentNumberVisibleRow(), 2);

        ecoNewsPage.signOut();
        softAssert.assertAll();
    }
}

