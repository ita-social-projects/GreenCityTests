package com.softserve.edu.greencity.ui.tests;

import com.softserve.edu.greencity.ui.data.User;
import com.softserve.edu.greencity.ui.data.UserRepository;
import com.softserve.edu.greencity.ui.data.econews.NewsDataRepository;
import com.softserve.edu.greencity.ui.pages.econews.EcoNewsPage;
import com.softserve.edu.greencity.ui.pages.econews.ItemComponent;
import com.softserve.edu.greencity.ui.tests.runner.GreenCityTestRunner;
import com.softserve.edu.greencity.ui.tools.jdbc.services.EcoNewsService;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class EcoNewsListViewTests extends GreenCityTestRunner {
    String cssBackgroundColorProperty;
    String expectedBackgroundColorRGBA;
    List<Integer> screenWidth, screenWidth1, screenWidth2;

    private final String DEFAULT_IMAGE = "assets/img/icon/econews/default-image-list-view.png";
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
        cssBackgroundColorProperty = "background-color";
        expectedBackgroundColorRGBA = "rgba(5, 107, 51, 1)";
        screenWidth = Arrays.asList(1200, 1024, 768, 667);
        screenWidth1 = Arrays.asList(1400, 1024, 768);
        screenWidth2 = Arrays.asList(576, 360);
    }

    @BeforeTest
    private SoftAssert assertSoftly() {
        return new SoftAssert();
    }


    @Test(testName = "GC-707")
    @Description("Verify that Content items are displayed as a list in case if 'List view' option is activated")
    public void isDisplayedListContent() {
        logger.info("Starting isDisplayedListContent");
        EcoNewsPage ecoNewsPage = loadApplication()
                .navigateMenuEcoNews()
                .switchToListView();

        Assert.assertTrue(ecoNewsPage.getItemsContainer().hasListViewClassActive());
    }


    @Test(testName = "GC-704")
    @Description("Verify that ‘List view’ icon is present on the 'Eco news' page")
    public void isPresentListView() {
        logger.info("Starting isPresentListView");
        EcoNewsPage ecoNewsPage = loadApplication()
                .navigateMenuEcoNews();

        assertSoftly().assertTrue(ecoNewsPage.isDisplayedListView());

        ecoNewsPage.hoverToListView();
        String hoverListViewIconColor = ecoNewsPage.getListView().getCssValue(cssBackgroundColorProperty);
        assertSoftly().assertEquals(hoverListViewIconColor, expectedBackgroundColorRGBA);

        ecoNewsPage
                .switchToListView()
                .hoverToGridView();

        String ListViewIconColor = ecoNewsPage.getListView().getCssValue(cssBackgroundColorProperty);
        assertSoftly().assertEquals(ListViewIconColor, expectedBackgroundColorRGBA);
        assertSoftly().assertAll();
    }

    @Test(testName = "GC-710")
    @Description("Verify that 6 first Content items are displayed by deafault")
    public void isDisplayedFirstSixContent() {
        logger.info("Starting isDisplayedFirstSixContent");
        EcoNewsPage ecoNewsPage = loadApplication()
                .navigateMenuEcoNews()
                .switchToListView();

        Assert.assertTrue(ecoNewsPage.getItemsContainer().getItemsSize() >= 6);
    }

    @Test(testName = "GC-332")
    @Description("Verify that content items are displayed in a list view")
    public void isDisplayedContentItems() {
        logger.info("Starting isDisplayedContentItems");
        EcoNewsPage ecoNewsPage = loadApplication()
                .navigateMenuEcoNews()
                .switchToListView();

        String listViewIconColor = ecoNewsPage.getListView().getCssValue(cssBackgroundColorProperty);

        assertSoftly().assertEquals(listViewIconColor, expectedBackgroundColorRGBA);
        assertSoftly().assertTrue(ecoNewsPage.getItemsContainer().getItemsSize() >= 6);
        assertSoftly().assertAll();
    }

    @Test(testName = "GC-333")
    @Description("Verify that content items have all specified elements.")
    public void isPresentAllItemElements() {
        logger.info("Starting isPresentAllItemElements");
        EcoNewsPage ecoNewsPage = loadApplication()
                .navigateMenuEcoNews()
                .switchToListView();

        ItemComponent firstItem = ecoNewsPage.getItemsContainer().chooseNewsByNumber(0);

        assertSoftly().assertTrue(firstItem.isDisplayedImage());
        assertSoftly().assertTrue(firstItem.isDisplayedTags());
        assertSoftly().assertTrue(firstItem.isDisplayedTitle());
        assertSoftly().assertTrue(firstItem.isDisplayedContent());
        assertSoftly().assertTrue(firstItem.isDisplayedDateOfCreation());
        assertSoftly().assertTrue(firstItem.isDisplayedAuthor());
        assertSoftly().assertTrue(firstItem.isCorrectDateFormat(firstItem.getDateOfCreationText()));

        assertSoftly().assertAll();
    }

    @Test(testName = "GC-352")
    @Description("Verify that Content items are displayed in chronological order")
    public void isItemsDisplayedChronological() {
        logger.info("isItemsDisplayedChronological");
        logger.info("isItemsDisplayedChronological");
        EcoNewsPage ecoNewsPage = loadApplication()
                .navigateMenuEcoNews()
                .switchToListView();

        Date date1 = ecoNewsPage.getItemsContainer().chooseNewsByNumber(0).getDateOfCreationDateFormat();
        Date date2 = ecoNewsPage.getItemsContainer().chooseNewsByNumber(1).getDateOfCreationDateFormat();

        assertSoftly().assertTrue((date1.compareTo(date2) == 0) || (date1.compareTo(date2) > 0));

        //JDBC
        EcoNewsService ecoNewsService = new EcoNewsService();
        Date firstNewsDate = ecoNewsService.getLastNewsCreationDateByTitle(ecoNewsPage.getItemsContainer().chooseNewsByNumber(0).getTitleText());
        Date secondNewsDate = ecoNewsService.getLastNewsCreationDateByTitle(ecoNewsPage.getItemsContainer().chooseNewsByNumber(1).getTitleText());

        assertSoftly().assertTrue(firstNewsDate.compareTo(secondNewsDate) > 0);

        assertSoftly().assertAll();
    }

    @Test(testName = "GC-720")
    @Description("Verify that content items contain all required UI elements according to mock-up.")
    public void isPresentAllContentElements() {
        logger.info("isPresentAllContentElements");
        User user = UserRepository.get().temporary();
        EcoNewsPage ecoNewsPage = loadApplication()
                .signIn()
                .getManualLoginComponent()
                .successfullyLogin(user)
                .navigateMenuEcoNews()
                .gotoCreateNewsPage()
                .fillFields(NewsDataRepository.get().getOneRowTitle())
                .publishNews();

        testNewsTitles.add(NewsDataRepository.get().getOneRowTitle().getTitle());

        for (Integer integer : screenWidth) {

            ecoNewsPage.changeWindowWidth(integer);
            ecoNewsPage.switchToListView();
            assertSoftly().assertTrue(ecoNewsPage.getItemsContainer().chooseNewsByNumber(0).isDisplayedImage());
            assertSoftly().assertTrue(ecoNewsPage.getItemsContainer().chooseNewsByNumber(0).isDisplayedTags());
            assertSoftly().assertTrue(ecoNewsPage.getItemsContainer().chooseNewsByNumber(0).isDisplayedTitle());
            assertSoftly().assertTrue(ecoNewsPage.getItemsContainer().chooseNewsByNumber(0).isDisplayedContent());
            assertSoftly().assertTrue(ecoNewsPage.getItemsContainer().chooseNewsByNumber(0).isDisplayedDateOfCreation());
            assertSoftly().assertTrue(ecoNewsPage.getItemsContainer().chooseNewsByNumber(0).isCorrectDateFormat(ecoNewsPage.getItemsContainer().chooseNewsByNumber(0).getDateOfCreationText()));
            assertSoftly().assertTrue(ecoNewsPage.getItemsContainer().chooseNewsByNumber(0).isDisplayedAuthor());

            ecoNewsPage.maximizeWindow();
        }
        for (Integer integer : screenWidth2) {

            ecoNewsPage.changeWindowWidth(integer);
            assertSoftly().assertFalse(ecoNewsPage.isListViewPresent());
            assertSoftly().assertTrue(ecoNewsPage.getItemsContainer().chooseNewsByNumber(0).isDisplayedImage());
            assertSoftly().assertTrue(ecoNewsPage.getItemsContainer().chooseNewsByNumber(0).isDisplayedTags());
            assertSoftly().assertTrue(ecoNewsPage.getItemsContainer().chooseNewsByNumber(0).isDisplayedTitle());
            assertSoftly().assertTrue(ecoNewsPage.getItemsContainer().chooseNewsByNumber(0).isDisplayedContent());
            assertSoftly().assertTrue(ecoNewsPage.getItemsContainer().chooseNewsByNumber(0).isDisplayedDateOfCreation());
            assertSoftly().assertTrue(ecoNewsPage.getItemsContainer().chooseNewsByNumber(0).isCorrectDateFormat(ecoNewsPage.getItemsContainer().chooseNewsByNumber(0).getDateOfCreationText()));
            assertSoftly().assertTrue(ecoNewsPage.getItemsContainer().chooseNewsByNumber(0).isDisplayedAuthor());

            ecoNewsPage.maximizeWindow();
        }
        ecoNewsPage.signOut();
        assertSoftly().assertAll();
    }

    /*
        @Test(testName = "GC-720")
        @Description("Verify that content items contain all required UI elements according to mock-up.(Add news and verify Author)")
        public void isNewsAuthorEqualsTopUserName() {
            User user = UserRepository.get().temporary();
            EcoNewsPage ecoNewsPage = loadApplication()
                    .signIn()
                    .getManualLoginComponent()
                    .successfullyLogin(user)
                    .navigateMenuEcoNews()
                    .gotoCreateNewsPage()
                    .fillFields(NewsDataRepository.getOneRowTitle())
                    .publishNews();

            String userName = ecoNewsPage.getTopUserName();
            Assert.assertEquals(userName, ecoNewsPage.getItemsContainer().chooseNewsByNumber(0).getAuthorText());
        }

        @DataProvider
        private Object[] getScreenWidthValidForListView() {
            return new Object[]{1200, 1000, 850, 650};
        }

        @Test(testName = "GC-720", dataProvider = "getScreenWidthValidForListView", dependsOnMethods = {"isNewsAuthorEqualsTopUserName"})
        @Description("Verify that content items contain all required UI elements according to mock-up.")
        public void isPresentAllContentElements(int width) {
            EcoNewsPage ecoNewsPage = loadApplication()
                    .navigateMenuEcoNews();

            ecoNewsPage.changeWindowWidth(width);
            ecoNewsPage.switchToListView();
            assertSoftly().assertTrue(ecoNewsPage.getItemsContainer().chooseNewsByNumber(0).isDisplayedImage());
            assertSoftly().assertTrue(ecoNewsPage.getItemsContainer().chooseNewsByNumber(0).isDisplayedTags());
            assertSoftly().assertTrue(ecoNewsPage.getItemsContainer().chooseNewsByNumber(0).isDisplayedTitle());
            assertSoftly().assertTrue(ecoNewsPage.getItemsContainer().chooseNewsByNumber(0).isDisplayedContent());
            assertSoftly().assertTrue(ecoNewsPage.getItemsContainer().chooseNewsByNumber(0).isDisplayedDateOfCreation());
            assertSoftly().assertTrue(ecoNewsPage.getItemsContainer().chooseNewsByNumber(0).isCorrectDateFormat(ecoNewsPage.getItemsContainer().chooseNewsByNumber(0).getDateOfCreationText()));
            assertSoftly().assertTrue(ecoNewsPage.getItemsContainer().chooseNewsByNumber(0).isDisplayedAuthor());

            ecoNewsPage.maximizeWindow();

            assertSoftly().assertAll();
        }

        @DataProvider
        private Object[] getScreenWidthInvalidForListView() {
            return new Object[]{576, 360};
        }
        @Test(testName = "GC-720", dataProvider = "getScreenWidthInvalidForListView", dependsOnMethods = {"isNewsAuthorEqualsTopUserName"})
        @Description("Verify that content items contain all required UI elements according to mock-up.")
        public void isPresentAllContentElementsSmallResolution(int width) {
            EcoNewsPage ecoNewsPage = loadApplication()
                    .navigateMenuEcoNews();

            ecoNewsPage.changeWindowWidth(width);
            assertSoftly().assertFalse(ecoNewsPage.isListViewPresent());
            assertSoftly().assertTrue(ecoNewsPage.getItemsContainer().chooseNewsByNumber(0).isDisplayedImage());
            assertSoftly().assertTrue(ecoNewsPage.getItemsContainer().chooseNewsByNumber(0).isDisplayedTags());
            assertSoftly().assertTrue(ecoNewsPage.getItemsContainer().chooseNewsByNumber(0).isDisplayedTitle());
            assertSoftly().assertTrue(ecoNewsPage.getItemsContainer().chooseNewsByNumber(0).isDisplayedContent());
            assertSoftly().assertTrue(ecoNewsPage.getItemsContainer().chooseNewsByNumber(0).isDisplayedDateOfCreation());
            assertSoftly().assertTrue(ecoNewsPage.getItemsContainer().chooseNewsByNumber(0).isCorrectDateFormat(ecoNewsPage.getItemsContainer().chooseNewsByNumber(0).getDateOfCreationText()));
            assertSoftly().assertTrue(ecoNewsPage.getItemsContainer().chooseNewsByNumber(0).isDisplayedAuthor());

            ecoNewsPage.maximizeWindow();

            assertSoftly().assertAll();
        }

        @Test(testName = "GC-720", dependsOnMethods = {"isNewsAuthorEqualsTopUserName", "isPresentAllContentElements", "isPresentAllContentElementsSmallResolution"})
        @Description("Verify that content items contain all required UI elements according to mock-up.")
        public void deleteNewsGC720() {
            EcoNewsService ecoNewsService = new EcoNewsService();
        ecoNewsService.deleteNewsByTitle(NewsDataRepository.getOneRowTitle().getTitle());
        //ecoNewsService.deleteLastNewsByTitle(NewsDataRepository.getOneRowTitle().getTitle());
        }
    */
    @Test(testName = "GC-725")
    @Description("Verify that displayed image in List view is default image if user didn’t choose own image during news creation.")
    public void isPresentDefaultImage() {
        logger.info("isPresentDefaultImage");
        User user = UserRepository.get().temporary();

        EcoNewsPage ecoNewsPage = loadApplication()
                .signIn()
                .getManualLoginComponent()
                .successfullyLogin(user)
                .navigateMenuEcoNews()
                .gotoCreateNewsPage()
                .fillFields(NewsDataRepository.get().getOneRowTitle())
                .publishNews();

        testNewsTitles.add(NewsDataRepository.get().getOneRowTitle().getTitle());

        for (Integer integer : screenWidth1) {
            ecoNewsPage.changeWindowWidth(integer);
            ecoNewsPage.switchToListView();
            String src = ecoNewsPage.getItemsContainer().chooseNewsByNumber(0).getImage().getAttribute("src");
            assertSoftly().assertEquals(src, DEFAULT_IMAGE);
        }
        for (Integer integer : screenWidth2) {
            ecoNewsPage.changeWindowWidth(integer);
            assertSoftly().assertFalse(ecoNewsPage.isListViewPresent());
            String src = ecoNewsPage.getItemsContainer().chooseNewsByNumber(0).getImage().getAttribute("src");
            assertSoftly().assertEquals(src, DEFAULT_IMAGE);
        }

        ecoNewsPage.maximizeWindow();
        ecoNewsPage.signOut();
        assertSoftly().assertAll();
    }


    @Test(testName = "GC-708")
    @Description("Verify that when Title consist of 4 row, then Description consist of 0 row.")
    public void isZeroRowDescriptionWhenFourRowsTitle() {
        logger.info("isZeroRowDescriptionWhenFourRowsTitle");
        User user = UserRepository.get().temporary();

        EcoNewsPage ecoNewsPage = loadApplication()
                .signIn()
                .getManualLoginComponent()
                .successfullyLogin(user)
                .navigateMenuEcoNews()
                .gotoCreateNewsPage()
                .fillFields(NewsDataRepository.get().getFourRowsTitle())
                .publishNews();

        testNewsTitles.add(NewsDataRepository.get().getFourRowsTitle().getTitle());

        ecoNewsPage.changeWindowWidth(1400);

        ecoNewsPage.switchToListView();

        Assert.assertEquals(ecoNewsPage.getItemsContainer().chooseNewsByNumber(0).getTitleHeight(), 104);
        Assert.assertEquals(ecoNewsPage.getItemsContainer().chooseNewsByNumber(0).getTitleNumberRow(), 4);
        Assert.assertFalse(ecoNewsPage.getItemsContainer().chooseNewsByNumber(0).getContent().isDisplayed());
        Assert.assertEquals(ecoNewsPage.getItemsContainer().chooseNewsByNumber(0).getContentNumberVisibleRow(), 0);

        ecoNewsPage.signOut();
        assertSoftly().assertAll();
    }

    @Test(testName = "GC-723")
    @Description("Verify that when Title consist of 3 row, then Description consist of 1 row.")
    public void isOneRowDescriptionWhenThreeRowsTitle() {
        logger.info("isOneRowDescriptionWhenThreeRowsTitle");
        User user = UserRepository.get().temporary();

        EcoNewsPage ecoNewsPage = loadApplication()
                .signIn()
                .getManualLoginComponent()
                .successfullyLogin(user)
                .navigateMenuEcoNews()
                .gotoCreateNewsPage()
                .fillFields(NewsDataRepository.get().getThreeRowsTitle())
                .publishNews();

        testNewsTitles.add(NewsDataRepository.get().getThreeRowsTitle().getTitle());

        ecoNewsPage.changeWindowWidth(1400);

        ecoNewsPage.switchToListView();

        ItemComponent firstItemTitle = ecoNewsPage.getItemsContainer().chooseNewsByNumber(0);
        assertSoftly().assertEquals(firstItemTitle.getTitleHeight(), 78);
        assertSoftly().assertEquals(firstItemTitle.getTitleNumberRow(), 3);
        assertSoftly().assertEquals(firstItemTitle.getContentNumberVisibleRow(), 1);

        ecoNewsPage.signOut();
        assertSoftly().assertAll();
    }

    @Test(testName = "GC-722")
    @Description("Verify that when Title consist of 2 row, then Description consist of 2 row.")
    public void isTwoRowsDescriptionWhenTwoRowsTitle() {
        logger.info("isTwoRowsDescriptionWhenTwoRowsTitle");
        User user = UserRepository.get().temporary();

        EcoNewsPage ecoNewsPage = loadApplication()
                .signIn()
                .getManualLoginComponent()
                .successfullyLogin(user)
                .navigateMenuEcoNews()
                .gotoCreateNewsPage()
                .fillFields(NewsDataRepository.get().getTwoRowsTitle())
                .publishNews();

        testNewsTitles.add(NewsDataRepository.get().getTwoRowsTitle().getTitle());

        ecoNewsPage.changeWindowWidth(1400);

        ecoNewsPage.switchToListView();

        ItemComponent firstItemTitle = ecoNewsPage.getItemsContainer().chooseNewsByNumber(0);
        assertSoftly().assertEquals(firstItemTitle.getTitleHeight(), 52);
        assertSoftly().assertEquals(firstItemTitle.getTitleNumberRow(), 2);
        assertSoftly().assertEquals(firstItemTitle.getContentNumberVisibleRow(), 2);

        ecoNewsPage.signOut();
        assertSoftly().assertAll();
    }

    @Test(testName = "GC-724")
    @Description("Verify that when Title consist of 1 row, then Description consist of 3 row.")
    public void isTwoRowsDescriptionWhenOneRowsTitle() {
        logger.info("isTwoRowsDescriptionWhenOneRowsTitle");
        User user = UserRepository.get().temporary();

        EcoNewsPage ecoNewsPage = loadApplication()
                .signIn()
                .getManualLoginComponent()
                .successfullyLogin(user)
                .navigateMenuEcoNews()
                .gotoCreateNewsPage()
                .fillFields(NewsDataRepository.get().getOneRowTitle())
                .publishNews();

        testNewsTitles.add(NewsDataRepository.get().getOneRowTitle().getTitle());

        ecoNewsPage.changeWindowWidth(1400);

        ecoNewsPage.switchToListView();

        ItemComponent firstItemTitle = ecoNewsPage.getItemsContainer().chooseNewsByNumber(0);
        assertSoftly().assertEquals(firstItemTitle.getTitleHeight(), 26);
        assertSoftly().assertEquals(firstItemTitle.getTitleNumberRow(), 1);
        assertSoftly().assertEquals(firstItemTitle.getContentNumberVisibleRow(), 2);

        ecoNewsPage.signOut();
        assertSoftly().assertAll();
    }
}

