package com.softserve.edu.greencity.ui.tests.createnews;

import com.softserve.edu.greencity.data.econews.NewsDataRepository;
import com.softserve.edu.greencity.data.econews.Tag;
import com.softserve.edu.greencity.data.users.User;
import com.softserve.edu.greencity.data.users.UserRepository;
import com.softserve.edu.greencity.ui.pages.econews.CreateNewsPage;
import com.softserve.edu.greencity.ui.pages.econews.EcoNewsPage;
import com.softserve.edu.greencity.ui.tests.runner.GreenCityTestRunnerWithLoginLogout;
import com.softserve.edu.greencity.ui.tools.jdbc.services.EcoNewsService;
import com.softserve.edu.greencity.ui.tools.testng.LocalOnly;
import com.softserve.edu.greencity.ui.tools.testng.RemoteSkipTestAnalyzer;
import io.qameta.allure.Description;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.List;

import static com.softserve.edu.greencity.ui.tests.createnews.CreateNewsTexts.*;

@Listeners(value = RemoteSkipTestAnalyzer.class)

public class CreateNewsNegativeTest extends GreenCityTestRunnerWithLoginLogout {
    private User getTemporaryUser() {
        return UserRepository.get().temporary();
    }

    private EcoNewsService getEcoNewsService() {
        return new EcoNewsService();
    }

    private CreateNewsPage createNewsPage;

    @BeforeMethod
    public void OpenCreateNewsPage() {
        createNewsPage = loadApplication()
                .loginIn(getTemporaryUser())
                .navigateMenuEcoNews()
                .gotoCreateNewsPage()
                .changeLanguageTo("En");
    }

    @AfterMethod
    public void signOut() {
        createNewsPage.signOut();
    }

    @Test(testName = "GC-584", description = "GC-584")
    @Description("Verify that system doesn't allow to add PNG image more than 10 MB")
    public void verifyImpossibleToAddImageMore10Mb() {
        logger.info("verifyImpossibleToAddImageMore10Mb");

        createNewsPage.fillFields(NewsDataRepository.get().getRequiredFieldsNews())
                .uploadTooLargeImage();

        Assert.assertEquals(createNewsPage.getInvalidImageErrorText(), IMAGE_ERROR.getText());

    }

    @Test(testName = "GC-592", description = "GC-592")
    @Description("Verify that news would not be created if content is too short")
    public void verifyImpossibilityOfCreatingNewsWithTooShortContent() {
        logger.info("verifyImpossibilityOfCreatingNewsWithTooShortContent starts");

        createNewsPage.fillFields(NewsDataRepository.get().getNewsWithInvalidContentField());

        Assert.assertFalse(createNewsPage.isPublishButtonClickable());
        Assert.assertEquals(createNewsPage.getContentErrorText(), CONTENT_ERROR.getText());

    }

    @Test(testName = "GC-645", description = "GC-645")
    @Description("Verify that user can`t create news with incorrect URL")
    public void verifyImpossibilityOfCreatingTestWithIncorrectUrlInSourceField() {
        logger.info("verifyImpossibilityOfCreatingTestWithIncorrectUrlInSourceField starts");

        createNewsPage.fillFields(NewsDataRepository.get().getNewsWithInvalidSourceField());

        softAssert.assertFalse(createNewsPage.isPublishButtonClickable());
        softAssert.assertEquals(createNewsPage.getInvalidSourceErrorText(), INVALID_SOURCE_ERROR.getText());
        softAssert.assertAll();

    }

    @Test(testName = "GC-637", description = "GC-637")
    @Description("Verify that user can`t create news with empty fields")
    public void verifyImpossibilityOfCreatingNewsWithEmptyFields() {
        logger.info("verifyImpossibilityOfCreatingNewsWithEmptyFields starts");

        Assert.assertFalse(createNewsPage.isPublishButtonClickable());

    }

    @Test(testName = "GC-642", description = "GC-642")
    @Description("Verify that user can`t create news without tags")
    public void verifyImpossibilityOfCreatingNewsWithoutAnyTags() {
        logger.info("verifyImpossibilityOfCreatingNewsWithoutAnyTags starts");

        createNewsPage.fillFields(NewsDataRepository.get().getRequiredFieldsNews());
        createNewsPage.getTagsComponent().deselectTag(Tag.NEWS);
        createNewsPage.getTagsComponent().deselectTag(Tag.EVENTS);

        Assert.assertFalse(createNewsPage.isPublishButtonClickable());

    }

    @Test(testName = "GC-644", description = "GC-644")
    @Description("Verify that user can`t create news with empty title")
    public void verifyImpossibilityOfCreatingNewsWithEmptyTitle() {
        logger.info("verifyImpossibilityOfCreatingNewsWithEmptyTitle starts");

        createNewsPage.fillFields(NewsDataRepository.get().getNewsWithInvalidTitleField());

        softAssert.assertFalse(createNewsPage.isPublishButtonClickable());
        softAssert.assertAll();

    }

    @Test(testName = "GC-638", description = "GC-638")
    @Description("Verify that user can`t create news with empty content")
    public void verifyImpossibilityOfCreatingNewsWithEmptyContent() {
        logger.info("verifyImpossibilityOfCreatingNewsWithEmptyContent starts");

        createNewsPage.fillFields(NewsDataRepository.get().getNewsWithEmptyContentField());

        softAssert.assertFalse(createNewsPage.isPublishButtonClickable());
        softAssert.assertEquals(createNewsPage.getContentErrorText(), CONTENT_ERROR.getText());
        softAssert.assertAll();

    }

    @LocalOnly
    @Test(testName = "GC-588", description = "GC-588")
    @Description("Verify that user can`t upload .gif format image")
    public void verifyImpossibilityOfUploadingGifImage() {

        logger.info("verifyImpossibilityOfUploadingGifImage starts");

        createNewsPage.fillFields(NewsDataRepository.get().getRequiredFieldsNews())
                .uploadGIFImage();

        Assert.assertEquals(createNewsPage.getInvalidImageErrorText(), IMAGE_ERROR.getText());

    }

    @LocalOnly
    @Test(testName = "GC-634", description = "GC-634")
    @Description("Verify that user can`t add JPEG image more than 10 MB")
    public void verifyImpossibilityOfUploadingTooLargeImage() {

        logger.info("verifyImpossibilityOfUploadingTooLargeImage starts");

        createNewsPage.fillFields(NewsDataRepository.get().getRequiredFieldsNews())
                .uploadTooLargeImage();
        try {
            String errorMsg = createNewsPage.getInvalidImageErrorText();
            Assert.assertEquals(errorMsg, IMAGE_ERROR.getText());
            //TODO ^ Site BUG: Large image can be uploaded ^
        } catch (TimeoutException | NoSuchElementException er) {
            Assert.fail("No error message appeared");
        }

    }

    @Test(testName = "GC-397", description = "GC-397")
    @Description("Verify that user can publish news only after filling in all mandatory fields with valid data")
    public void checkImpossibleToCreateNewsWithoutFillingMandatoryFields() {
        logger.info("checkImpossibleToCreateNewsWithoutFillingMandatoryFields starts");

        EcoNewsPage ecoNewsPage = createNewsPage.fillFields(NewsDataRepository.get().getNewsWithValidData())
                .publishNews();

        softAssert.assertTrue(ecoNewsPage.isNewsDisplayedByTitle(NewsDataRepository.get().getNewsWithValidData().getTitle()));
        getEcoNewsService().deleteNewsByTitle(NewsDataRepository.get().getNewsWithValidData().getTitle());
        softAssert.assertFalse(ecoNewsPage.refreshPage().isNewsDisplayedByTitle(NewsDataRepository.get().getNewsWithValidData().getTitle()));
        softAssert.assertAll();

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

    @Ignore
    @Test(testName = "GC-643", description = "GC-643", dataProvider = "getInvalidTagsList")
    @Description("Verify that user can`t create news with more than 3 tags selected")
    public void verifyPossibilityOfMaxThreeTagsWhenCreateNews(List<Tag> tags) {
        logger.info("verifyPossibilityOfMaxThreeTagsWhenCreateNews starts with parameter : " + tags.toString());

        createNewsPage.fillFields(NewsDataRepository.get().getNewsWithInvalidTags(tags));

        softAssert.assertTrue(createNewsPage.isTagsErrorDisplayed());
        softAssert.assertEquals(createNewsPage.getTagsErrorText(), TAGS_ERROR.getText());

        createNewsPage.goToPreViewPage().backToCreateNewsPage();//TODO bug with tag

        createNewsPage.getTagsComponent().deselectTags(tags);
        createNewsPage.getTagsComponent().selectTags(tags);

        softAssert.assertTrue(createNewsPage.isTagsErrorDisplayed());
        softAssert.assertEquals(createNewsPage.getTagsErrorText(), TAGS_ERROR.getText());

        EcoNewsPage ecoNewsPage = createNewsPage.publishNews();

        softAssert.assertTrue(ecoNewsPage.isNewsDisplayedByTitle(NewsDataRepository.get().getNewsWithInvalidTags(tags).getTitle()));
        getEcoNewsService().deleteNewsByTitle(NewsDataRepository.get().getNewsWithInvalidTags(tags).getTitle());
        softAssert.assertFalse(ecoNewsPage.refreshPage().isNewsDisplayedByTitle(NewsDataRepository.get().getNewsWithInvalidTags(tags).getTitle()));
        softAssert.assertAll();

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

    @Ignore
    @Test(testName = "GC-654", description = "GC-654", dataProvider = "getTagsListWithSingleTag")
    @Description("Verify that user can`t create news with 2 or more same tags")
    public void verifyImpossibilityToSelectOneTagTwice(ArrayList<Tag> tags) {
        logger.info("verifyImpossibilityToSelectOneTagTwice starts with parameters : " + tags.toString());

        createNewsPage.fillFields(NewsDataRepository.get().getNewsWithValidData());

        createNewsPage.goToPreViewPage().backToCreateNewsPage();//TODO bug with tags
        createNewsPage.getTagsComponent().deselectTags(tags);
        createNewsPage.getTagsComponent().selectTags(tags);

        EcoNewsPage ecoNewsPage = createNewsPage.publishNews();

        softAssert.assertTrue(ecoNewsPage.isNewsDisplayedByTitle(NewsDataRepository.get().getNewsWithValidData().getTitle()));
        getEcoNewsService().deleteNewsByTitle(NewsDataRepository.get().getNewsWithValidData().getTitle());
        softAssert.assertFalse(ecoNewsPage.refreshPage().isNewsDisplayedByTitle(NewsDataRepository.get().getNewsWithValidData().getTitle()));
        softAssert.assertAll();

    }

}
