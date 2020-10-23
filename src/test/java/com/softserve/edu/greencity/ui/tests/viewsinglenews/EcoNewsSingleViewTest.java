package com.softserve.edu.greencity.ui.tests.viewsinglenews;

import com.softserve.edu.greencity.ui.assertions.EcoNewsTagsAssertion;
import com.softserve.edu.greencity.ui.data.UserRepository;
import com.softserve.edu.greencity.ui.data.econews.NewsData;
import com.softserve.edu.greencity.ui.data.econews.NewsDataRepository;
import com.softserve.edu.greencity.ui.data.econews.Tag;
import com.softserve.edu.greencity.ui.pages.econews.EcoNewsPage;
import com.softserve.edu.greencity.ui.pages.econews.ItemsContainer;
import com.softserve.edu.greencity.ui.pages.econews.SingleNewsPage;
import com.softserve.edu.greencity.ui.tests.runner.GreenCityTestRunner;
import com.softserve.edu.greencity.ui.tools.jdbc.services.EcoNewsService;
import io.qameta.allure.Description;
import org.testng.Assert;
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

    @Test
    @Description("GC-671")
    public void returnToFilteredNews() {
        logger.info("Starting returnToFilteredNews");

        List<Tag> singleTag = new ArrayList<Tag>();
        singleTag.add(Tag.NEWS);

        List<Tag> multipleTags = new ArrayList<Tag>(){};
        multipleTags.add(Tag.NEWS); multipleTags.add(Tag.ADS); multipleTags.add(Tag.EVENTS);

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




    @Test(testName = "GC-731")
    @Description("Source field doesn't appear if User hasn't specified Source in the Create news form.")
    public void noSourceIfItWasntSpecified() {
        logger.info("verifyPossibilityOfCreatingNewsWithEmptySourceField starts");

        NewsData newsWithEmptySource = NewsDataRepository.get().getNewsWithoutSource();
        try {
            SingleNewsPage singleNewsPage = loadApplication()
                    .loginIn(UserRepository.get().temporary())
                    .navigateMenuEcoNews()
                    .gotoCreateNewsPage()
                    .fillFields(newsWithEmptySource)
                    .publishNews()
                    .switchToSingleNewsPageByNumber(0);

            softAssert.assertTrue(singleNewsPage.getSourceLinkText().equals(""),
                    "Checking if news has no source");
            softAssert.assertAll();

            singleNewsPage.signOut();
        } finally {
            EcoNewsService ecoNewsService = new EcoNewsService();
            ecoNewsService.deleteNewsByTitle(newsWithEmptySource.getTitle());
        }
    }
}
