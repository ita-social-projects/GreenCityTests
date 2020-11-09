package com.softserve.edu.greencity.ui.tests.viewsinglenews;

import com.softserve.edu.greencity.ui.assertions.EcoNewsSuggestionsAssertion;
import com.softserve.edu.greencity.ui.assertions.EcoNewsTagsAssertion;
import com.softserve.edu.greencity.ui.data.User;
import com.softserve.edu.greencity.ui.data.UserRepository;
import com.softserve.edu.greencity.ui.data.econews.NewsData;
import com.softserve.edu.greencity.ui.data.econews.NewsDataRepository;
import com.softserve.edu.greencity.ui.data.econews.Tag;
import com.softserve.edu.greencity.ui.pages.econews.EcoNewsPage;
import com.softserve.edu.greencity.ui.pages.econews.ItemsContainer;
import com.softserve.edu.greencity.ui.pages.econews.SingleNewsPage;
import com.softserve.edu.greencity.ui.tests.runner.GreenCityTestRunner;
import com.softserve.edu.greencity.ui.tools.EcoNewsUtils;
import com.softserve.edu.greencity.ui.tools.TagsUtill;
import com.softserve.edu.greencity.ui.tools.jdbc.services.EcoNewsService;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class EcoNewsSingleViewTest extends GreenCityTestRunner {

    @Test
    @Description("GC-670")
    public void returningToNewsViaBackToNews() {
        logger.info("Starting returningToNewsViaBackToNews");

        // Steps
        EcoNewsPage ecoNewsPage = loadApplication()
                .navigateMenuEcoNews()
                .switchToSingleNewsPageByNumber(0)
                .switchToEcoNewsPageBack();
        Assert.assertTrue(ecoNewsPage.isActiveGridView());

        ecoNewsPage = ecoNewsPage
                .switchToListView()
                .switchToSingleNewsPageByNumber(0)
                .switchToEcoNewsPageBack();
        Assert.assertTrue(ecoNewsPage.isActiveListView());
    }

    //@Ignore //Runs too long
    @Test
    @Description("GC-671")
    public void returnToFilteredNews() {
        logger.info("Starting returnToFilteredNews");

        List<Tag> singleTag = new ArrayList<Tag>();
        singleTag.add(Tag.NEWS);

        List<Tag> multipleTags = new ArrayList<Tag>() {
        };
        multipleTags.add(Tag.NEWS);
        multipleTags.add(Tag.ADS);
        multipleTags.add(Tag.EVENTS);

        // Steps
        EcoNewsPage ecoNewsPage = loadApplication()
                .navigateMenuEcoNews()
                .selectFilters(singleTag);

        EcoNewsTagsAssertion.assertNewsFilteredByTags(ecoNewsPage.getItemsContainer(), singleTag);

        ecoNewsPage = ecoNewsPage
                .switchToSingleNewsPageByNumber(0)
                .switchToEcoNewsPageBack()
                .selectFilters(multipleTags);

        EcoNewsTagsAssertion.assertNewsFilteredByTags(ecoNewsPage.getItemsContainer(), multipleTags);

        ecoNewsPage = ecoNewsPage
                .switchToSingleNewsPageByNumber(0)
                .switchToEcoNewsPageBack();

        EcoNewsTagsAssertion.assertNewsFilteredByTags(ecoNewsPage.getItemsContainer(), multipleTags);
    }

    @Test(testName = "GC-672")
    @Description("Verify that ‘Edit’ button is not available for unregistered User")
    public void verifyEditNotAvailable() {
        logger.info("verifyEditNotAvailable starts");

        boolean editButtonExist = loadApplication()
                .navigateMenuEcoNews()
                .switchToSingleNewsPageByNumber(0)
                .editNewsButtonExist();
        Assert.assertFalse(editButtonExist, "Edit button exists");
    }

    @Test(testName = "GC-691")
    @Description("Verify that ‘Edit’ button is available for registered User in case " +
            "he/she has submitted this particular piece of news ")
    public void verifyEditAvailable() {
        logger.info("verifyEditAvailable starts");
        User user = UserRepository.get().temporary();
        NewsData news = NewsDataRepository.get().getNewsWithValidData();
        boolean editButtonExist = loadApplication()
                .signIn()
                .getManualLoginComponent()
                .successfullyLogin(user)
                .navigateMenuEcoNews()
                .gotoCreateNewsPage()
                .fillFields(news)
                .publishNews()
                .switchToSingleNewsPageByNumber(0)
                .editNewsButtonExist();

        Assert.assertTrue(editButtonExist, "Edit button doesn't exist");
        //Clean up
        EcoNewsService ecoNewsService = new EcoNewsService();
        ecoNewsService.deleteNewsByTitle(news.getTitle());
    }

    @Test(testName = "GC-692")
    @Description("Verify that ‘Edit’ button is not available for registered User in " +
            "case he/she has not submitted this particular piece of news")
    public void verifyUnavailabilityOfEditButton() {
        logger.info("verifyUnavailabilityOfEditButton starts");
        User user = UserRepository.get().temporary();
        EcoNewsPage ecoNewsPage = loadApplication()
                .signIn()
                .getManualLoginComponent()
                .successfullyLogin(user)
                .navigateMenuEcoNews();
        int suitableNews = EcoNewsUtils.getNumberOfNewsNotCreatedBy(user.getUserName(),
                ecoNewsPage.getItemsContainer());
        boolean editButtonExists = ecoNewsPage
                .switchToSingleNewsPageByNumber(suitableNews)
                .editNewsButtonExist();
        Assert.assertFalse(editButtonExists,"Edit button exists");
    }

    @Test(testName = "GC-695")
    @Description("Source field appears if User entered Source in the Create news form.")
    public void presentSourceIfItWasSpecified() {
        logger.info("presentSourceIfItWasSpecified starts");

        NewsData newsWithSource = NewsDataRepository.get().getNewsWithSource();
        try {
            SingleNewsPage singleNewsPage = loadApplication()
                    .loginIn(UserRepository.get().temporary())
                    .navigateMenuEcoNews()
                    .gotoCreateNewsPage()
                    .fillFields(newsWithSource)
                    .publishNews()
                    .switchToSingleNewsPageByNumber(0);

            softAssert.assertTrue(singleNewsPage.getSourceTitleText().length() > 1,
                    "Checking if source title is present");
            softAssert.assertEquals(singleNewsPage.getSourceLinkText(), newsWithSource.getSource(),
                    "Checking if news has given source"); //TODO BUG: source == content. Site bug?
            softAssert.assertAll();

            singleNewsPage.signOut();
        } finally {
            EcoNewsService ecoNewsService = new EcoNewsService();
            ecoNewsService.deleteNewsByTitle(newsWithSource.getTitle());
        }
    }

    @Test(testName = "GC-713")
    @Description("Verify that User sees the last 3 news with the same tag in the News recommendations" +
            " widget, if there are more than 3 news with this tag")
    public void testRecommendations() {
        EcoNewsPage ecoNewsPage = loadApplication()
                .navigateMenuEcoNews();

        Tag suitableTag = TagsUtill.getSuitableTag(ecoNewsPage, (e) -> (e.getNumberOfItemComponent() > 3));
        if (suitableTag != null) {
            ItemsContainer suggestedNews = ecoNewsPage
                    .selectFilter(suitableTag)
                    .switchToSingleNewsPageByNumber(0)
                    .suggestedNews();
            Assert.assertEquals(suggestedNews.getItemComponentsCount(), 3);
            EcoNewsSuggestionsAssertion.assertSuggestionsByDate(suggestedNews, false);
        } else {
            Assert.assertTrue(false, "Couldn't find suitable tag");
        }
    }

    @Test(testName = "GC-731")
    @Description("Source field doesn't appear if User hasn't specified Source in the Create news form.")
    public void noSourceIfItWasntSpecified() {
        logger.info("noSourceIfItWasntSpecified starts");

        NewsData newsWithEmptySource = NewsDataRepository.get().getNewsWithoutSource();
        try {
            SingleNewsPage singleNewsPage = loadApplication()
                    .loginIn(UserRepository.get().temporary())
                    .navigateMenuEcoNews()
                    .gotoCreateNewsPage()
                    .fillFields(newsWithEmptySource)
                    .publishNews()
                    .switchToSingleNewsPageByNumber(0);

            softAssert.assertEquals(singleNewsPage.getSourceLinkText(), "",
                    "Checking if news has no source");
            softAssert.assertAll();

            singleNewsPage.signOut();
        } finally {
            EcoNewsService ecoNewsService = new EcoNewsService();
            ecoNewsService.deleteNewsByTitle(newsWithEmptySource.getTitle());
        }
    }
}
