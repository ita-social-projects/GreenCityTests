package com.softserve.edu.greencity.ui.tests.createnews;

import com.softserve.edu.greencity.ui.data.User;
import com.softserve.edu.greencity.ui.data.UserRepository;
import com.softserve.edu.greencity.ui.data.econews.NewsDataRepository;
import com.softserve.edu.greencity.ui.data.econews.Tag;
import com.softserve.edu.greencity.ui.pages.econews.CreateNewsPage;
import com.softserve.edu.greencity.ui.pages.econews.EcoNewsPage;
import com.softserve.edu.greencity.ui.tests.runner.GreenCityTestRunner;
import com.softserve.edu.greencity.ui.tests.runner.LocalOnly;
import com.softserve.edu.greencity.ui.tests.runner.RemoteSkipTestAnalyzer;
import com.softserve.edu.greencity.ui.tools.jdbc.services.EcoNewsService;
import io.qameta.allure.Description;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static com.softserve.edu.greencity.ui.tests.createnews.CreateNewsTexts.*;

@Listeners(value = RemoteSkipTestAnalyzer.class)

public class CreateNewsNegativeTest extends GreenCityTestRunner {
    private User getTemporaryUser() {
        return UserRepository.get().temporary();
    }

    private EcoNewsService getEcoNewsService() {
        return new EcoNewsService();
    }

    @Test(testName = "GC-593", description = "GC-593")
    @Description("Verify that create news button is invisible for unregistered user")
    public void checkInvisibilityOfCreateNewsButtonForGuest() {
        logger.info("checkInvisibilityOfCreateNewsButtonForGuest starts");

        EcoNewsPage ecoNewsPage = loadApplication()
                .navigateMenuEcoNews();

        Assert.assertFalse(ecoNewsPage.isCreateNewsButtonPresent());
    }

    @Test(testName = "GC-592", description = "GC-592")
    @Description("Verify that news would not be created if content is too short")
    public void verifyImpossibilityOfCreatingNewsWithTooShortContent() {
        logger.info("verifyImpossibilityOfCreatingNewsWithTooShortContent starts");

        CreateNewsPage createNewsPage = loadApplication()
                .loginIn(getTemporaryUser())
                .navigateMenuEcoNews()
                .gotoCreateNewsPage()
                .fillFields(NewsDataRepository.get().getNewsWithInvalidContentField());

        Assert.assertFalse(createNewsPage.isPublishButtonClickable());
        Assert.assertEquals(createNewsPage.getContentErrorText(), CONTENT_ERROR.getText());

        createNewsPage.signOut();
    }

    @Test(testName = "GC-645", description = "GC-645")
    @Description("Verify that user can`t create news with incorrect URL")
    public void verifyImpossibilityOfCreatingTestWithIncorrectUrlInSourceField() {
        logger.info("verifyImpossibilityOfCreatingTestWithIncorrectUrlInSourceField starts");

        CreateNewsPage createNewsPage = loadApplication()
                .loginIn(getTemporaryUser())
                .navigateMenuEcoNews()
                .gotoCreateNewsPage()
                .fillFields(NewsDataRepository.get().getNewsWithInvalidSourceField());

        softAssert.assertFalse(createNewsPage.isPublishButtonClickable());
        softAssert.assertEquals(createNewsPage.getInvalidSourceErrorText(), INVALID_SOURCE_ERROR.getText());
        softAssert.assertAll();

        createNewsPage.signOut();
    }

    @Test(testName = "GC-637", description = "GC-637")
    @Description("Verify that user can`t create news with empty fields")
    public void verifyImpossibilityOfCreatingNewsWithEmptyFields() {
        logger.info("verifyImpossibilityOfCreatingNewsWithEmptyFields starts");

        CreateNewsPage createNewsPage = loadApplication()
                .loginIn(getTemporaryUser())
                .navigateMenuEcoNews()
                .gotoCreateNewsPage();

        Assert.assertFalse(createNewsPage.isPublishButtonClickable());

        createNewsPage.signOut();
    }

    @Test(testName = "GC-642", description = "GC-642")
    @Description("Verify that user can`t create news without tags")
    public void verifyImpossibilityOfCreatingNewsWithoutAnyTags() {
        logger.info("verifyImpossibilityOfCreatingNewsWithoutAnyTags starts");

        CreateNewsPage createNewsPage = loadApplication()
                .loginIn(getTemporaryUser())
                .navigateMenuEcoNews()
                .gotoCreateNewsPage()
                .fillFields(NewsDataRepository.get().getRequiredFieldsNews());
        createNewsPage.getTagsComponent().deselectTag(Tag.NEWS);
        createNewsPage.getTagsComponent().deselectTag(Tag.EVENTS);

        Assert.assertFalse(createNewsPage.isPublishButtonClickable());

        createNewsPage.signOut();
    }

    @Test(testName = "GC-644", description = "GC-644")
    @Description("Verify that user can`t create news with empty title")
    public void verifyImpossibilityOfCreatingNewsWithEmptyTitle() {
        logger.info("verifyImpossibilityOfCreatingNewsWithEmptyTitle starts");

        CreateNewsPage createNewsPage = loadApplication()
                .loginIn(getTemporaryUser())
                .navigateMenuEcoNews()
                .gotoCreateNewsPage()
                .fillFields(NewsDataRepository.get().getNewsWithInvalidTitleField());

        softAssert.assertFalse(createNewsPage.isPublishButtonClickable());
        softAssert.assertAll();

        createNewsPage.signOut();
    }

    @Test(testName = "GC-638", description = "GC-638")
    @Description("Verify that user can`t create news with empty content")
    public void verifyImpossibilityOfCreatingNewsWithEmptyContent() {
        logger.info("verifyImpossibilityOfCreatingNewsWithEmptyContent starts");

        CreateNewsPage createNewsPage = loadApplication()
                .loginIn(getTemporaryUser())
                .navigateMenuEcoNews()
                .gotoCreateNewsPage()
                .fillFields(NewsDataRepository.get().getNewsWithEmptyContentField());

        softAssert.assertFalse(createNewsPage.isPublishButtonClickable());
        softAssert.assertEquals(createNewsPage.getContentErrorText(), CONTENT_ERROR.getText());
        softAssert.assertAll();

        createNewsPage.signOut();
    }

    @LocalOnly
    @Test(testName = "GC-588", description = "GC-588")
    @Description("Verify that user can`t upload .gif format image")
    public void verifyImpossibilityOfUploadingGifImage() {

        logger.info("verifyImpossibilityOfUploadingGifImage starts");

        CreateNewsPage createNewsPage = loadApplication()
                .loginIn(getTemporaryUser())
                .navigateMenuEcoNews()
                .gotoCreateNewsPage()
                .fillFields(NewsDataRepository.get().getRequiredFieldsNews())
                .uploadGIFImage();
        Assert.assertEquals(createNewsPage.getInvalidImageErrorText(), IMAGE_ERROR.getText());

        createNewsPage.signOut();


    }

    @LocalOnly
    @Test(testName = "GC-634", description = "GC-634")
    @Description("Verify that user can`t add JPEG image more than 10 MB")
    public void verifyImpossibilityOfUploadingTooLargeImage() {

        logger.info("verifyImpossibilityOfUploadingTooLargeImage starts");

        CreateNewsPage createNewsPage = loadApplication()
                .loginIn(getTemporaryUser())
                .navigateMenuEcoNews()
                .gotoCreateNewsPage()
                .fillFields(NewsDataRepository.get().getRequiredFieldsNews())
                .uploadTooLargeImage();
        try {
            String errorMsg = createNewsPage.getInvalidImageErrorText();
            Assert.assertEquals(errorMsg, IMAGE_ERROR.getText());
            //TODO ^ Site BUG: Large image can be uploaded ^
        } catch (TimeoutException | NoSuchElementException er) {
            Assert.fail("No error message appeared");
        }

        createNewsPage.signOut();


    }

    @Test(testName = "GC-397", description = "GC-397")
    @Description("Verify that user can publish news only after filling in all mandatory fields with valid data")
    public void checkImpossibleToCreateNewsWithoutFillingMandatoryFields() {
        logger.info("checkImpossibleToCreateNewsWithoutFillingMandatoryFields starts");

        EcoNewsPage ecoNewsPage = loadApplication()
                .loginIn(getTemporaryUser())
                .navigateMenuEcoNews()
                .gotoCreateNewsPage()
                .fillFields(NewsDataRepository.get().getNewsWithValidData())
                .publishNews();

        softAssert.assertTrue(ecoNewsPage.isNewsDisplayedByTitle(NewsDataRepository.get().getNewsWithValidData().getTitle()));
        getEcoNewsService().deleteNewsByTitle(NewsDataRepository.get().getNewsWithValidData().getTitle());
        softAssert.assertFalse(ecoNewsPage.refreshPage().isNewsDisplayedByTitle(NewsDataRepository.get().getNewsWithValidData().getTitle()));
        softAssert.assertAll();

        ecoNewsPage.signOut();
    }


    @DataProvider
    public Object[] getInvalidTagsList() {
        return new Object[]{
                new ArrayList<Tag>() {
                    {
                        add(Tag.NEWS);
                        add(Tag.EVENTS);
                        add(Tag.EDUCATION);
                        add(Tag.ADS);
                    }
                }
        };
    }

    @Test(testName = "GC-643", description = "GC-643", dataProvider = "getInvalidTagsList")
    @Description("Verify that user can`t create news with more than 3 tags selected")
    public void verifyPossibilityOfMaxThreeTagsWhenCreateNews(List<Tag> tags) {
        logger.info("verifyPossibilityOfMaxThreeTagsWhenCreateNews starts with parameter : " + tags.toString());

        CreateNewsPage createNewsPage = loadApplication()
                .loginIn(getTemporaryUser())
                .navigateMenuEcoNews()
                .gotoCreateNewsPage()
                .fillFields(NewsDataRepository.get().getNewsWithInvalidTags(tags));

        softAssert.assertTrue(createNewsPage.isTagsErrorDisplayed());
        softAssert.assertEquals(createNewsPage.getTagsErrorText(), TAGS_ERROR.getText());

        createNewsPage.goToPreViewPage().backToCreateNewsPage();

        createNewsPage.getTagsComponent().deselectTags(tags);
        createNewsPage.getTagsComponent().selectTags(tags);

        softAssert.assertTrue(createNewsPage.isTagsErrorDisplayed());
        softAssert.assertEquals(createNewsPage.getTagsErrorText(), TAGS_ERROR.getText());

        EcoNewsPage ecoNewsPage = createNewsPage.publishNews();

        softAssert.assertTrue(ecoNewsPage.isNewsDisplayedByTitle(NewsDataRepository.get().getNewsWithInvalidTags(tags).getTitle()));
        getEcoNewsService().deleteNewsByTitle(NewsDataRepository.get().getNewsWithInvalidTags(tags).getTitle());
        softAssert.assertFalse(ecoNewsPage.refreshPage().isNewsDisplayedByTitle(NewsDataRepository.get().getNewsWithInvalidTags(tags).getTitle()));
        softAssert.assertAll();

        ecoNewsPage.signOut();
    }


    @DataProvider
    public Object[] getTagsListWithSingleTag() {
        return new Object[]{
                new ArrayList<Tag>() {
                    {
                        add(Tag.NEWS);
                    }
                }
        };
    }

    @Test(testName = "GC-654", description = "GC-654", dataProvider = "getTagsListWithSingleTag")
    @Description("Verify that user can`t create news with 2 or more same tags")
    public void verifyImpossibilityToSelectOneTagTwice(ArrayList<Tag> tags) {
        logger.info("verifyImpossibilityToSelectOneTagTwice starts with parameters : " + tags.toString());

        CreateNewsPage createNewsPage = loadApplication()
                .loginIn(getTemporaryUser())
                .navigateMenuEcoNews()
                .gotoCreateNewsPage()
                .fillFields(NewsDataRepository.get().getNewsWithValidData());

        createNewsPage.goToPreViewPage().backToCreateNewsPage();
        createNewsPage.getTagsComponent().deselectTags(tags);
        createNewsPage.getTagsComponent().selectTags(tags);

        EcoNewsPage ecoNewsPage = createNewsPage.publishNews();

        softAssert.assertTrue(ecoNewsPage.isNewsDisplayedByTitle(NewsDataRepository.get().getNewsWithValidData().getTitle()));
        getEcoNewsService().deleteNewsByTitle(NewsDataRepository.get().getNewsWithValidData().getTitle());
        softAssert.assertFalse(ecoNewsPage.refreshPage().isNewsDisplayedByTitle(NewsDataRepository.get().getNewsWithValidData().getTitle()));
        softAssert.assertAll();

        ecoNewsPage.signOut();
    }

}
