package com.softserve.edu.greencity.ui.tests;

import com.softserve.edu.greencity.ui.data.User;
import com.softserve.edu.greencity.ui.data.UserRepository;
import com.softserve.edu.greencity.ui.data.econews.NewsDataRepository;
import com.softserve.edu.greencity.ui.data.econews.Tag;
import com.softserve.edu.greencity.ui.pages.econews.*;
import com.softserve.edu.greencity.ui.tests.runner.GreenCityTestRunner;
import com.softserve.edu.greencity.ui.tools.jdbc.services.EcoNewsService;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.List;

public class CreateNewsPositiveTest extends GreenCityTestRunner {

    private final String CREATE_NEWS_TITLE = "Create news";
    private final String CONTENT_ERROR = "Must be minimum 20 symbols";
    private final String INVALID_SOURCE_ERROR = " Please add the link of original article/news/post. ";
    private final String IMAGE_ERROR = "Download PNG or JPG only. File size should be less than 10MB";
    private final String VALID_TITLE = "Green Day";
    private final String VALID_CONTENT = "Content = description";
    private final String TAGS_ERROR = "Only 3 tags can be added";

    @BeforeTest
    private SoftAssert assertSoftly() {
        return new SoftAssert();
    }

    @BeforeTest
    private User getTemporaryUser() {
        return UserRepository.get().temporary();
    }

    @BeforeTest
    private EcoNewsService getEcoNewsService() {
        return new EcoNewsService();
    }

    @Test(testName = "GC-595")
    @Description("Verify that user is on create news form")
    public void checkThatUserOnCreateNewsForm() {
        logger.info("checkThatUserOnCreateNewsForm starts");

        CreateNewsPage createNewsPage = loadApplication()
                .loginIn(getTemporaryUser())
                .navigateMenuEcoNews()
                .gotoCreateNewsPage();

        assertSoftly().assertEquals(createNewsPage.getCreateNewsMainTitleText(), CREATE_NEWS_TITLE);
        assertSoftly().assertTrue(createNewsPage.isPublishButtonDisplayed());
        assertSoftly().assertAll();

        createNewsPage.signOut();
    }

    @Test(testName = "GC-591")
    @Description("Verify that create news button is visible for registered user")
    public void checkVisibilityOfCreateNewsButtonForRegisteredUser() {
        logger.info("checkVisibilityOfCreateNewsButtonForRegisteredUser starts");

        EcoNewsPage econewsPage = loadApplication()
                .loginIn(getTemporaryUser())
                .navigateMenuEcoNews();

        Assert.assertTrue(econewsPage.isCreateNewsButtonDisplayed());

        econewsPage.signOut();
    }

    @Test(testName = "GC-593")
    @Description("Verify that create news button is invisible for unregistered user")
    public void checkInvisibilityOfCreateNewsButtonForGuest() {
        logger.info("checkInvisibilityOfCreateNewsButtonForGuest starts");

        EcoNewsPage ecoNewsPage = loadApplication()
                .navigateMenuEcoNews();

        Assert.assertFalse(ecoNewsPage.isCreateNewsButtonPresent());
    }

    @Test(testName = "GC-623")
    @Description("Verify possibility of choosing tags")
    public void verifySelectAndDeselectPossibilityOfTags() {
        logger.info("verifySelectAndDeselectPossibilityOfTags starts");

        CreateNewsPage createNewsPage = loadApplication()
                .loginIn(getTemporaryUser())
                .navigateMenuEcoNews()
                .gotoCreateNewsPage();

        TagsComponent tagsComponent = createNewsPage.getTagsComponent();
        tagsComponent.selectTag(Tag.NEWS);
        Assert.assertTrue(tagsComponent.isTagActive(Tag.NEWS));
        tagsComponent.deselectTag(Tag.NEWS);
        Assert.assertFalse(tagsComponent.isTagActive(Tag.NEWS));

        createNewsPage.signOut();
    }

    @Test(testName = "GC-592")
    @Description("Verify that news would not be created if content is too short")
    public void verifyImpossibilityOfCreatingNewsWithTooShortContent() {
        logger.info("verifyImpossibilityOfCreatingNewsWithTooShortContent starts");

        CreateNewsPage createNewsPage = loadApplication()
                .loginIn(getTemporaryUser())
                .navigateMenuEcoNews()
                .gotoCreateNewsPage()
                .fillFields(NewsDataRepository.get().getNewsWithInvalidContentField());

        Assert.assertFalse(createNewsPage.isPublishButtonClickable());
        Assert.assertEquals(createNewsPage.getContentErrorText(), CONTENT_ERROR);

        createNewsPage.signOut();
    }

    @Test(testName = "GC-645")
    @Description("Verify that user can`t create news with incorrect URL")
    public void verifyImpossibilityOfCreatingTestWithIncorrectUrlInSourceField() {
        logger.info("verifyImpossibilityOfCreatingTestWithIncorrectUrlInSourceField starts");

        CreateNewsPage createNewsPage = loadApplication()
                .loginIn(getTemporaryUser())
                .navigateMenuEcoNews()
                .gotoCreateNewsPage()
                .fillFields(NewsDataRepository.get().getNewsWithInvalidSourceField());

        assertSoftly().assertFalse(createNewsPage.isPublishButtonClickable());
        assertSoftly().assertEquals(createNewsPage.getInvalidSourceErrorText(), INVALID_SOURCE_ERROR);
        assertSoftly().assertAll();

        createNewsPage.signOut();
    }

    @Test(testName = "GC-637")
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

    @Test(testName = "GC-642")
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

    @Test(testName = "GC-644")
    @Description("Verify that user can`t create news with empty title")
    public void verifyImpossibilityOfCreatingNewsWithEmptyTitle() {
        logger.info("verifyImpossibilityOfCreatingNewsWithEmptyTitle starts");

        CreateNewsPage createNewsPage = loadApplication()
                .loginIn(getTemporaryUser())
                .navigateMenuEcoNews()
                .gotoCreateNewsPage()
                .fillFields(NewsDataRepository.get().getNewsWithInvalidTitleField());

        assertSoftly().assertFalse(createNewsPage.isPublishButtonClickable());
        assertSoftly().assertAll();

        createNewsPage.signOut();
    }

    @Test(testName = "GC-638")
    @Description("Verify that user can`t create news with empty content")
    public void verifyImpossibilityOfCreatingNewsWithEmptyContent() {
        logger.info("verifyImpossibilityOfCreatingNewsWithEmptyContent starts");

        CreateNewsPage createNewsPage = loadApplication()
                .loginIn(getTemporaryUser())
                .navigateMenuEcoNews()
                .gotoCreateNewsPage()
                .fillFields(NewsDataRepository.get().getNewsWithEmptyContentField());

        assertSoftly().assertFalse(createNewsPage.isPublishButtonClickable());
        assertSoftly().assertEquals(createNewsPage.getContentErrorText(), CONTENT_ERROR);
        assertSoftly().assertAll();

        createNewsPage.signOut();
    }

    @Test(testName = "GC-621")
    @Description("Verify that user can go to preview page")
    public void verifyPossibilityOfPreViewingNewsPage() {
        logger.info("verifyPossibilityOfPreViewingNewsPage starts");

        PreViewPage preViewPage = loadApplication()
                .loginIn(getTemporaryUser())
                .navigateMenuEcoNews()
                .gotoCreateNewsPage()
                .fillFields(NewsDataRepository.get().getRequiredFieldsNews())
                .goToPreViewPage();

        assertSoftly().assertTrue(preViewPage.isBackToEditingButtonDisplayed());
        assertSoftly().assertAll();

        preViewPage.signOut();
    }

    @Test(testName = "GC-633")
    @Description("Verify that preview page is displayed correctly")
    public void verifyThatPreViewIsDisplayedCorrectly() {
        logger.info("verifyThatPreViewIsDisplayedCorrectly starts");

        PreViewPage preViewPage = loadApplication()
                .loginIn(getTemporaryUser())
                .navigateMenuEcoNews()
                .gotoCreateNewsPage()
                .fillFields(NewsDataRepository.get().getAllFieldsNews())
                .goToPreViewPage();

        assertSoftly().assertTrue(preViewPage.isBackToEditingButtonDisplayed());
        assertSoftly().assertEquals(preViewPage.getTitleFieldText(), VALID_TITLE);
        assertSoftly().assertEquals(preViewPage.getContentFieldText(), VALID_CONTENT);
        assertSoftly().assertAll();

        preViewPage.signOut();
    }

    @Test(testName = "GC-606")
    @Description("Verify that User can continue editing news in case ‘Cancel’ button was pressed")
    public void verifyThatUserCanContinueNewsCreations() {
        logger.info("verifyThatUserCanContinueNewsCreations starts");

        CreateNewsPage createNewsPage = loadApplication()
                .loginIn(getTemporaryUser())
                .navigateMenuEcoNews()
                .gotoCreateNewsPage()
                .fillFields(NewsDataRepository.get().getAllFieldsNews())
                .clickCancelButton()
                .clickContinueEditingButton();

        assertSoftly().assertEquals(createNewsPage.getCreateNewsMainTitleText(), CREATE_NEWS_TITLE);
        assertSoftly().assertAll();

        createNewsPage.signOut();
    }

    @Test(testName = "GC-607")
    @Description("Verify that user can cancel news creation if he decided to drop posting")
    public void verifyThatUserCanCancelNewsCreations() {
        logger.info("verifyThatUserCanCancelNewsCreations starts");

        EcoNewsPage ecoNewsPage = loadApplication()
                .loginIn(getTemporaryUser())
                .navigateMenuEcoNews()
                .gotoCreateNewsPage()
                .fillFields(NewsDataRepository.get().getAllFieldsNews())
                .clickCancelButton()
                .clickCancelEditingButton();

        assertSoftly().assertTrue(ecoNewsPage.isGridViewDisplayed());
        assertSoftly().assertAll();

        ecoNewsPage.signOut();
    }

        @Test(testName = "GC-588")
    @Description("Verify that user can`t upload .gif format image")
    public void verifyImpossibilityOfUploadingGifImage() {
        logger.info("verifyImpossibilityOfUploadingGifImage starts");

        CreateNewsPage createNewsPage = loadApplication()
                .loginIn(getTemporaryUser())
                .navigateMenuEcoNews()
                .gotoCreateNewsPage()
                .fillFields(NewsDataRepository.get().getRequiredFieldsNews())
                .uploadGIFImage();
        Assert.assertEquals(createNewsPage.getInvalidImageErrorText(), IMAGE_ERROR);

        createNewsPage.signOut();
    }

        @Test(testName = "GC-634")
    @Description("Verify that user can`t add JPEG image more than 10 MB")
    public void verifyImpossibilityOfUploadingTooLargeImage() {
        logger.info("verifyImpossibilityOfUploadingTooLargeImage starts");

        CreateNewsPage createNewsPage = loadApplication()
                .loginIn(getTemporaryUser())
                .navigateMenuEcoNews()
                .gotoCreateNewsPage()
                .fillFields(NewsDataRepository.get().getRequiredFieldsNews())
                .uploadTooLargeImage();
        assertSoftly().assertEquals(createNewsPage.getInvalidImageErrorText(), IMAGE_ERROR);//TODO BUG

        createNewsPage.signOut();
    }

    @Test(testName = "GC-397")
    @Description("Verify that user can publish news only after filling in all mandatory fields with valid data")
    public void checkImpossibleToCreateNewsWithoutFillingMandatoryFields() {
        logger.info("checkImpossibleToCreateNewsWithoutFillingMandatoryFields starts");

        EcoNewsPage ecoNewsPage = loadApplication()
                .loginIn(getTemporaryUser())
                .navigateMenuEcoNews()
                .gotoCreateNewsPage()
                .fillFields(NewsDataRepository.get().getNewsWithValidData())
                .publishNews();

        assertSoftly().assertTrue(ecoNewsPage.isNewsDisplayedByTitle(NewsDataRepository.get().getNewsWithValidData().getTitle()));
        getEcoNewsService().deleteNewsByTitle(NewsDataRepository.get().getNewsWithValidData().getTitle());
//        assertSoftly().assertFalse(ecoNewsPage.refreshPage().isNewsDisplayedByTitle(NewsDataRepository.get().getNewsWithValidData().getTitle()));
        assertSoftly().assertAll();

        ecoNewsPage.signOut();
    }

    @Test(testName = "GC-401", dataProvider = "getStringForTitle")
    @Description("Verify that user can publish news with valid characters in a 'Title' field")
    public void fillTitleFieldFromMinToMax(String title) {
        logger.info("fillTitleFieldFromMinToMax starts with parameters : " + title);

        EcoNewsPage ecoNewsPage = loadApplication()
                .loginIn(getTemporaryUser())
                .navigateMenuEcoNews()
                .gotoCreateNewsPage()
                .fillFields(NewsDataRepository.get().getNewsWithValidData(title))
                .publishNews();

        assertSoftly().assertTrue(ecoNewsPage.isNewsDisplayedByTitle(title));
        getEcoNewsService().deleteNewsByTitle(title);
//        assertSoftly().assertFalse(ecoNewsPage.refreshPage().isNewsDisplayedByTitle(title));
        assertSoftly().assertAll();

        ecoNewsPage.signOut();
    }

    @DataProvider
    public Object[] getStringForTitle() {
        return new Object[]{
                "B",
                "Be eco! Be cool! Be healthy! Do not be indifferent to the future of our planet! " +
                        "It`s so healthy, fun and cool to bring eco habits in everyday life!Test maximum characters",
                "Be eco! Be cool! Be healthy! Do not be indifferent to the future of our planet!",
                "1234567890",
                "~!@#$%^&*()_+|?/,.\\"
        };
    }

    @Test(testName = "GC-628", dataProvider = "getTagsList")
    @Description("Verify news creation with allowed amount of selected tags")
    public void checkCreateNewsWithOneToThreeTags(List<Tag> tags) {
        logger.info("checkCreateNewsWithOneToThreeTags starts with parameters : " + tags.toString());

        EcoNewsPage ecoNewsPage = loadApplication()
                .loginIn(getTemporaryUser())
                .navigateMenuEcoNews()
                .gotoCreateNewsPage()
                .fillFields(NewsDataRepository.get().getNewsWithValidData(tags))
                .publishNews();

        assertSoftly().assertTrue(ecoNewsPage.isNewsDisplayedByTitle(NewsDataRepository.get().getNewsWithValidData().getTitle()));
        getEcoNewsService().deleteNewsByTitle(NewsDataRepository.get().getNewsWithValidData().getTitle());
//        assertSoftly().assertFalse(ecoNewsPage.refreshPage().isNewsDisplayedByTitle(NewsDataRepository.get().getNewsWithValidData().getTitle()));
        assertSoftly().assertAll();

        ecoNewsPage.signOut();
    }

    @DataProvider
    public Object[] getTagsList() {
        return new Object[]{
                new ArrayList<Tag>() {
                    {
                        add(Tag.NEWS);
                    }
                },
                new ArrayList<Tag>() {
                    {
                        add(Tag.NEWS);
                        add(Tag.EVENTS);
                    }
                },
                new ArrayList<Tag>() {
                    {
                        add(Tag.EVENTS);
                        add(Tag.NEWS);
                        add(Tag.EDUCATION);
                    }
                }
        };
    }

        @Test(testName = "GC-643", dataProvider = "getInvalidTagsList")//TODO REFACTOR
    @Description("Verify that user can`t create news with more than 3 tags selected")
    public void verifyPossibilityOfMaxThreeTagsWhenCreateNews(List<Tag> tags) {
        logger.info("verifyPossibilityOfMaxThreeTagsWhenCreateNews starts with parameter : " + tags.toString());

        CreateNewsPage createNewsPage = loadApplication()
                .loginIn(getTemporaryUser())
                .navigateMenuEcoNews()
                .gotoCreateNewsPage()
                .fillFields(NewsDataRepository.get().getNewsWithInvalidTags(tags));

        assertSoftly().assertTrue(createNewsPage.isTagsErrorDisplayed());
        assertSoftly().assertEquals(createNewsPage.getTagsErrorText(), TAGS_ERROR);

        createNewsPage.goToPreViewPage().backToCreateNewsPage();

        createNewsPage.getTagsComponent().deselectTags(tags);
        createNewsPage.getTagsComponent().selectTags(tags);

        assertSoftly().assertTrue(createNewsPage.isTagsErrorDisplayed());
        assertSoftly().assertEquals(createNewsPage.getTagsErrorText(), TAGS_ERROR);

        EcoNewsPage ecoNewsPage = createNewsPage.publishNews();

        assertSoftly().assertTrue(ecoNewsPage.isNewsDisplayedByTitle(NewsDataRepository.get().getNewsWithInvalidTags(tags).getTitle()));
        getEcoNewsService().deleteNewsByTitle(NewsDataRepository.get().getNewsWithInvalidTags(tags).getTitle());
//        assertSoftly().assertFalse(ecoNewsPage.refreshPage().isNewsDisplayedByTitle(NewsDataRepository.get().getNewsWithInvalidTags(tags).getTitle()));
        assertSoftly().assertAll();

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

        @Test(testName = "GC-654", dataProvider = "getTagsListWithSingleTag")
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

        assertSoftly().assertTrue(ecoNewsPage.isNewsDisplayedByTitle(NewsDataRepository.get().getNewsWithValidData().getTitle()));
        getEcoNewsService().deleteNewsByTitle(NewsDataRepository.get().getNewsWithValidData().getTitle());
//        assertSoftly().assertFalse(ecoNewsPage.refreshPage().isNewsDisplayedByTitle(NewsDataRepository.get().getNewsWithValidData().getTitle()));
        assertSoftly().assertAll();

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

    @Test(testName = "GC-616")
    @Description("Verify that news will be created, when user put more than 20 symbols in ‘Content’ field.")
    public void createNewsWithContentLengthMoreThen20() {
        logger.info("createNewsWithContentLengthMoreThen20 starts");

        EcoNewsPage ecoNewsPage = loadApplication()
                .loginIn(getTemporaryUser())
                .navigateMenuEcoNews()
                .gotoCreateNewsPage()
                .fillFields(NewsDataRepository.get().getNewsWithValidData())
                .publishNews();

        assertSoftly().assertTrue(ecoNewsPage.isNewsDisplayedByTitle(NewsDataRepository.get().getNewsWithValidData().getTitle()));
        getEcoNewsService().deleteNewsByTitle(NewsDataRepository.get().getNewsWithValidData().getTitle());
//        assertSoftly().assertFalse(ecoNewsPage.refreshPage().isNewsDisplayedByTitle(NewsDataRepository.get().getNewsWithValidData().getTitle()));
        assertSoftly().assertAll();

        ecoNewsPage.signOut();
    }

    @Test(testName = "GC-613")
    @Description("Verify that news will be created when user add a link, which contains https:// in ‘Source’ field")
    public void createNewsWithSourceField() {
        logger.info("createNewsWithSourceField starts");

        EcoNewsPage ecoNewsPage = loadApplication()
                .loginIn(getTemporaryUser())
                .navigateMenuEcoNews()
                .gotoCreateNewsPage()
                .fillFields(NewsDataRepository.get().getNewsWithValidSourceField())
                .publishNews();

        assertSoftly().assertTrue(ecoNewsPage.isNewsDisplayedByTitle(NewsDataRepository.get().getNewsWithValidSourceField().getTitle()));
        getEcoNewsService().deleteNewsByTitle(NewsDataRepository.get().getNewsWithValidSourceField().getTitle());
//        assertSoftly().assertFalse(ecoNewsPage.refreshPage().isNewsDisplayedByTitle(NewsDataRepository.get().getNewsWithValidSourceField().getTitle()));
        assertSoftly().assertAll();

        ecoNewsPage.signOut();
    }

    @Test(testName = "GC-617")
    @Description("Verify that date is Auto-filled")
    public void verifyAutoFillingDataWhenCreateNews() {
        logger.info("verifyAutoFillingDataWhenCreateNews starts");

        EcoNewsPage ecoNewsPage = loadApplication()
                .loginIn(getTemporaryUser())
                .navigateMenuEcoNews()
                .gotoCreateNewsPage()
                .fillFields(NewsDataRepository.get().getNewsWithValidData())
                .publishNews();

        assertSoftly().assertTrue(ecoNewsPage.isNewsDisplayedByTitle(NewsDataRepository.get().getNewsWithValidData().getTitle()));
        //TODO CHECK THAT DATE IS CREATED
        getEcoNewsService().deleteNewsByTitle(NewsDataRepository.get().getNewsWithValidData().getTitle());
//        assertSoftly().assertFalse(ecoNewsPage.refreshPage().isNewsDisplayedByTitle(NewsDataRepository.get().getNewsWithValidData().getTitle()));
        assertSoftly().assertAll();

        ecoNewsPage.signOut();
    }

    @Test(testName = "GC-629")
    @Description("Verify that news will be created when user add no image")
    public void verifyNewsCreationWithoutImage() {
        logger.info("verifyNewsCreationWithoutImage starts");

        EcoNewsPage ecoNewsPage = loadApplication()
                .loginIn(getTemporaryUser())
                .navigateMenuEcoNews()
                .gotoCreateNewsPage()
                .fillFields(NewsDataRepository.get().getRequiredFieldsNews())
                .publishNews();

        assertSoftly().assertTrue(ecoNewsPage.isNewsDisplayedByTitle(NewsDataRepository.get().getRequiredFieldsNews().getTitle()));
        getEcoNewsService().deleteNewsByTitle(NewsDataRepository.get().getRequiredFieldsNews().getTitle());
//        assertSoftly().assertFalse(ecoNewsPage.refreshPage().isNewsDisplayedByTitle(NewsDataRepository.get().getRequiredFieldsNews().getTitle()));
        assertSoftly().assertAll();

        ecoNewsPage.signOut();
    }

        @Test(testName = "GC-610") //TODO JIRA TEST IS NOT CORRECT!!!
    @Description("Verify that news will be created, when user add PNG image less than 10 MB")
    public void verifyNewsCreationWithPNGImage() {
        logger.info("verifyNewsCreationWithPNGImage starts");

        EcoNewsPage ecoNewsPage = loadApplication()
                .loginIn(getTemporaryUser())
                .navigateMenuEcoNews()
                .gotoCreateNewsPage()
                .fillFields(NewsDataRepository.get().getRequiredFieldsNews())
                .uploadPNGImage()
                .publishNews();

        assertSoftly().assertTrue(ecoNewsPage.isNewsDisplayedByTitle(NewsDataRepository.get().getRequiredFieldsNews().getTitle()));
        getEcoNewsService().deleteNewsByTitle(NewsDataRepository.get().getRequiredFieldsNews().getTitle());
//        assertSoftly().assertFalse(ecoNewsPage.refreshPage().isNewsDisplayedByTitle(NewsDataRepository.get().getRequiredFieldsNews().getTitle()));
        assertSoftly().assertAll();

        ecoNewsPage.signOut();
    }

        @Test(testName = "GC-611")
    @Description("Verify that news will be created, when user add JPG image less than 10 MB")
    public void verifyNewsCreationWithValidImage() {
        logger.info("verifyNewsCreationWithValidImage starts");

        EcoNewsPage ecoNewsPage = loadApplication()
                .loginIn(getTemporaryUser())
                .navigateMenuEcoNews()
                .gotoCreateNewsPage()
                .fillFields(NewsDataRepository.get().getRequiredFieldsNews())
                .uploadJPGImage()
                .publishNews();

        assertSoftly().assertTrue(ecoNewsPage.isNewsDisplayedByTitle(NewsDataRepository.get().getRequiredFieldsNews().getTitle()));
        getEcoNewsService().deleteNewsByTitle(NewsDataRepository.get().getRequiredFieldsNews().getTitle());
//        assertSoftly().assertFalse(ecoNewsPage.refreshPage().isNewsDisplayedByTitle(NewsDataRepository.get().getRequiredFieldsNews().getTitle()));
        assertSoftly().assertAll();

        ecoNewsPage.signOut();
    }

    @Test(testName = "GC-620")
    @Description("Verify that user can publish news after clicking ‘Publish’ button")
    public void verifyThatNewsIsCreatedAfterClickOnPublishButton() {
        logger.info("verifyThatNewsIsCreatedAfterClickOnPublishButton starts");

        EcoNewsPage ecoNewsPage = loadApplication()
                .loginIn(getTemporaryUser())
                .navigateMenuEcoNews()
                .gotoCreateNewsPage()
                .fillFields(NewsDataRepository.get().getAllFieldsNews())
                .publishNews();

        assertSoftly().assertTrue(ecoNewsPage.isNewsDisplayedByTitle(NewsDataRepository.get().getAllFieldsNews().getTitle()));
        getEcoNewsService().deleteNewsByTitle(NewsDataRepository.get().getAllFieldsNews().getTitle());
//        assertSoftly().assertFalse(ecoNewsPage.refreshPage().isNewsDisplayedByTitle(NewsDataRepository.get().getAllFieldsNews().getTitle()));
        assertSoftly().assertAll();

        ecoNewsPage.signOut();
    }

    @Test(testName = "GC-622")
    @Description("Verify that news will be created with empty source field")
    public void verifyPossibilityOfCreatingNewsWithEmptySourceField() {
        logger.info("verifyPossibilityOfCreatingNewsWithEmptySourceField starts");

        EcoNewsPage ecoNewsPage = loadApplication()
                .loginIn(getTemporaryUser())
                .navigateMenuEcoNews()
                .gotoCreateNewsPage()
                .fillFields(NewsDataRepository.get().getNewsWithEmptySourceField())
                .publishNews();

        assertSoftly().assertTrue(ecoNewsPage.isNewsDisplayedByTitle(NewsDataRepository.get().getNewsWithEmptySourceField().getTitle()));
        getEcoNewsService().deleteNewsByTitle(NewsDataRepository.get().getNewsWithEmptySourceField().getTitle());
//        assertSoftly().assertFalse(ecoNewsPage.refreshPage().isNewsDisplayedByTitle(NewsDataRepository.get().getNewsWithEmptySourceField().getTitle()));
        assertSoftly().assertAll();

        ecoNewsPage.signOut();
    }

    @Test(testName = "GC-403")
    @Description("Verify that User can go back to editing news by following ‘Back to editing’ link")
    public void verifyPossibilityOfGoingBackToEditNews() {
        logger.info("verifyPossibilityOfGoingBackToEditNews starts");

        PreViewPage preViewPage = loadApplication()
                .loginIn(getTemporaryUser())
                .navigateMenuEcoNews()
                .gotoCreateNewsPage()
                .fillFields(NewsDataRepository.get().getAllFieldsNews())
                .goToPreViewPage();

        assertSoftly().assertTrue(preViewPage.isBackToEditingButtonDisplayed());
        CreateNewsPage createNewsPage = preViewPage.backToCreateNewsPage();
        assertSoftly().assertTrue(createNewsPage.getTagsComponent().isTagActive(Tag.NEWS));
        assertSoftly().assertTrue(createNewsPage.getTagsComponent().isTagActive(Tag.EVENTS));
        createNewsPage.navigateMenuEcoNews().gotoCreateNewsPage();
        assertSoftly().assertFalse(createNewsPage.getTagsComponent().isTagActive(Tag.NEWS));
        assertSoftly().assertFalse(createNewsPage.getTagsComponent().isTagActive(Tag.EVENTS));
        assertSoftly().assertAll();

        createNewsPage.signOut();
    }
}
