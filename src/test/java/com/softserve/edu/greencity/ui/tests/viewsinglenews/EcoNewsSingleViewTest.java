package com.softserve.edu.greencity.ui.tests.viewsinglenews;

import com.softserve.edu.greencity.data.econews.NewsData;
import com.softserve.edu.greencity.data.econews.NewsDataRepository;
import com.softserve.edu.greencity.data.econews.Tag;
import com.softserve.edu.greencity.data.users.User;
import com.softserve.edu.greencity.data.users.UserRepository;
import com.softserve.edu.greencity.ui.assertions.EcoNewsSuggestionsAssertion;
import com.softserve.edu.greencity.ui.assertions.EcoNewsTagsAssertion;
import com.softserve.edu.greencity.ui.pages.econews.CreateNewsPage;
import com.softserve.edu.greencity.ui.pages.econews.EcoNewsPage;
import com.softserve.edu.greencity.ui.pages.econews.ItemsContainer;
import com.softserve.edu.greencity.ui.pages.econews.SingleNewsPage;
import com.softserve.edu.greencity.ui.tests.runner.GreenCityTestRunner;
import com.softserve.edu.greencity.ui.tools.EcoNewsUtils;
import com.softserve.edu.greencity.ui.tools.TagsUtill;
import com.softserve.edu.greencity.ui.tools.jdbc.services.EcoNewsService;
import io.qameta.allure.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class EcoNewsSingleViewTest extends GreenCityTestRunner {

    @Test(testName = "GC-670", description = "GC-670")
    @Description("Verify that User can return to News from single view by clicking ‘Back to news’ button")
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


    @Test(testName = "GC-671", description = "GC-671")
    @Description("Verify that User can return to News filtered by tags from single view by clicking ‘Back to news’ button")
    public void returnToFilteredNews() {
        logger.info("Starting returnToFilteredNews");

        List<Tag> singleTag = new ArrayList<Tag>();
        singleTag.add(Tag.NEWS);

        List<Tag> multipleTags = new ArrayList<Tag>();
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

        ecoNewsPage = ecoNewsPage
                .switchToSingleNewsPageByNumber(0)
                .switchToEcoNewsPageBack()
                .selectFilters(singleTag)
                .selectFilters(multipleTags);
    }

    @Test(testName = "GC-672", description = "GC-672")
    @Description("Verify that ‘Edit’ button is not available for unregistered User")
    public void verifyEditNotAvailable() {
        logger.info("verifyEditNotAvailable starts");

        boolean editButtonExist = loadApplication()
                .navigateMenuEcoNews()
                .switchToSingleNewsPageByNumber(0)
                .editNewsButtonExist();
        Assert.assertFalse(editButtonExist, "Edit button exists");
    }

    @Test(testName = "GC-673", description = "GC-673")
    @Description("Verify that tags are displayed according to User`s selection")
    public void verifyTagsDisplayed() {
        logger.info("verifyTagsDisplayed starts");

        List<Tag> multipleTags = new ArrayList<Tag>();
        multipleTags.add(Tag.NEWS);
        multipleTags.add(Tag.ADS);
        multipleTags.add(Tag.EVENTS);

        List<String> tagsDisplayed = loadApplication()
                .navigateMenuEcoNews()
                .selectFilters(multipleTags)
                .switchToSingleNewsPageByNumber(0)
                .getTagsListText();
        softAssert.assertTrue(multipleTags.contains(tagsDisplayed));
    }

    @Test(testName = "GC-691", description = "GC-691")
    @Description("Verify that ‘Edit’ button is available for registered User in case " +
            "he/she has submitted this particular piece of news ")
    public void verifyEditAvailable() {
        logger.info("verifyEditAvailable starts");
        User user = UserRepository.get().temporary();
        NewsData news = NewsDataRepository.get().getNewsWithValidData("VerifyEditAvailable");
        boolean editButtonExist = loadApplication()
                .signIn()
                .getManualLoginComponent()
                .successfullyLogin(user)
                .navigateMenuEcoNews()
                .gotoCreateNewsPage()
                .fillFields(news)
                .publishNews()
                .switchToSingleNewsPageByParameters(news)
                .editNewsButtonExist();

        Assert.assertTrue(editButtonExist, "Edit button doesn't exist");
        //Clean up
        EcoNewsService ecoNewsService = new EcoNewsService();
        ecoNewsService.deleteNewsByTitle(news.getTitle());
    }

    @Test(testName = "GC-692", description = "GC-692")
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

        WebElement name = driver.findElement(By.cssSelector("#header_user-wrp span"));
        String userName = name.getText();
        int suitableNews = new EcoNewsUtils(driver).getNumberOfNewsNotCreatedBy(userName, ecoNewsPage.getItemsContainer());
        boolean editButtonExists = ecoNewsPage
                .switchToSingleNewsPageByNumber(suitableNews)
                .editNewsButtonExist();
        Assert.assertFalse(editButtonExists,"Edit button exists");
    }

    @Test(testName = "GC-695", description = "GC-695")
    @Description("Source field appears if User entered Source in the Create news form.")
    public void presentSourceIfItWasSpecified() {
        logger.info("presentSourceIfItWasSpecified starts");

        NewsData newsWithSource = NewsDataRepository.get().getNewsWithValidSourceField();
        try {
            SingleNewsPage singleNewsPage = loadApplication()
                    .loginIn(UserRepository.get().temporary())
                    .navigateMenuEcoNews()
                    .gotoCreateNewsPage()
                    .fillFields(newsWithSource)
                    .publishNews()
                    .switchToSingleNewsPageByParameters(newsWithSource);

            softAssert.assertTrue(singleNewsPage.getSourceTitleText().length() > 1,
                    "Checking if source title is present");
            softAssert.assertEquals(singleNewsPage.getSourceLinkText(), newsWithSource.getSource(),
                    "Checking if news has given source"); //TODO Site BUG: source == content
            softAssert.assertAll();

            singleNewsPage.signOut();
        } finally {
            EcoNewsService ecoNewsService = new EcoNewsService();
            ecoNewsService.deleteNewsByTitle(newsWithSource.getTitle());
        }
    }

    @Test(testName = "GC-731", description = "GC-731")
    @Description("Source field doesn't appear if User hasn't specified Source in the Create news form.")
    public void noSourceIfItWasntSpecified() {
        logger.info("noSourceIfItWasntSpecified starts");

        NewsData newsWithEmptySource = NewsDataRepository.get().getNewsWithEmptySourceField2();
        try {
            SingleNewsPage singleNewsPage = loadApplication()
                    .loginIn(UserRepository.get().temporary())
                    .navigateMenuEcoNews()
                    .gotoCreateNewsPage()
                    .fillFields(newsWithEmptySource)
                    .publishNews()
                    .switchToSingleNewsPageByParameters(newsWithEmptySource);

            softAssert.assertEquals(singleNewsPage.getSourceLinkText(), "",
                    "Checking if news has no source");
            softAssert.assertAll();

            singleNewsPage.signOut();
        } finally {
            EcoNewsService ecoNewsService = new EcoNewsService();
            ecoNewsService.deleteNewsByTitle(newsWithEmptySource.getTitle());
        }
    }

    //TODO testcase check only date of creation without tag`s
    @Test(testName = "GC-713", description = "GC-713")
    @Description("Verify that User sees the last 3 news with the same tag in the News recommendations" +
            " widget, if there are more than 3 news with this tag")
    public void testRecommendations() {
        EcoNewsPage ecoNewsPage = loadApplication()
                .navigateMenuEcoNews();

        //.getSuitableTag click on tag button
        Tag suitableTag = Tag.ADS;
        if (suitableTag != null) {
            ItemsContainer suggestedNews = ecoNewsPage
                    .selectFilter(suitableTag)
                    .switchToSingleNewsPageByNumber(0)
                    .suggestedNews();
            Assert.assertEquals(suggestedNews.getItemComponentsCount(), 3);
            EcoNewsSuggestionsAssertion.assertSuggestionsByDate(suggestedNews,suitableTag, false);
            ecoNewsPage
                    .switchToSingleNewsPageByNumber(0)
                    .switchToEcoNewsPageBack()
                    .selectFilter(suitableTag);
        } else {
            Assert.fail("Couldn't find suitable tag");
        }
    }

    @Test(testName = "GC-590", description = "GC-590")
    @Description("Verify that system doesn’t allow to add file of inappropriate format in ‘Image’ field")
    public void addingPDFformatIntoImageField(){
        logger.info("log");

        //Entering
        User user = UserRepository.get().temporary();
        NewsData news = NewsDataRepository.get().getNewsWithValidData("Test for upload PNG11");

        try
        {
            CreateNewsPage createNewsPage = loadApplication()
                    .signIn()
                    .getManualLoginComponent()
                    .successfullyLogin(user)
                    .navigateMenuEcoNews()
                    .gotoCreateNewsPage();

            boolean warningMessageExist = createNewsPage
                    .uploadPDFFile()
                    .isPictureDescriptionWarning();

            softAssert.assertTrue(warningMessageExist);

            boolean isDefaultPicture = createNewsPage
                    .fillFields(news)
                    .publishNews()
                    .switchToSingleNewsPageByParameters(news)
                    .isDefaultPicture();
            softAssert.assertTrue(isDefaultPicture);
            softAssert.assertAll();

            createNewsPage.signOut();
        }

        finally
        {
            EcoNewsService ecoNewsService = new EcoNewsService();
            ecoNewsService.deleteNewsByTitle(news.getTitle());
        }
    }
}
