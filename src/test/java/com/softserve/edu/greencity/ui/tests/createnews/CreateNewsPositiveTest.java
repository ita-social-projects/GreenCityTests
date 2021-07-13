package com.softserve.edu.greencity.ui.tests.createnews;

import com.softserve.edu.greencity.data.CreateNewsUaExpectedText;
import com.softserve.edu.greencity.data.Languages;
import com.softserve.edu.greencity.data.users.User;
import com.softserve.edu.greencity.data.users.UserRepository;

import com.softserve.edu.greencity.data.econews.NewsData;
import com.softserve.edu.greencity.data.econews.NewsDataRepository;
import com.softserve.edu.greencity.data.econews.Tag;
import com.softserve.edu.greencity.data.users.User;
import com.softserve.edu.greencity.data.users.UserRepository;
import com.softserve.edu.greencity.ui.locators.CreateNewsPageLocators;
import com.softserve.edu.greencity.ui.pages.econews.CreateNewsPage;
import com.softserve.edu.greencity.ui.pages.econews.EcoNewsPage;
import com.softserve.edu.greencity.ui.pages.econews.SingleNewsPage;
import com.softserve.edu.greencity.ui.pages.econews.TagsComponent;
import com.softserve.edu.greencity.ui.tests.runner.GreenCityTestRunner;
import com.softserve.edu.greencity.ui.tools.jdbc.services.EcoNewsService;
import com.softserve.edu.greencity.ui.tools.jdbc.dao.EcoNewsDao;
import com.softserve.edu.greencity.ui.tools.jdbc.dao.EcoNewsTagsDao;
import com.softserve.edu.greencity.ui.tools.jdbc.entity.EcoNewsEntity;
import com.softserve.edu.greencity.ui.tools.jdbc.entity.EcoNewsTagsEntity;
import com.softserve.edu.greencity.ui.tools.testng.LocalOnly;
import com.softserve.edu.greencity.ui.tools.testng.RemoteSkipTestAnalyzer;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.softserve.edu.greencity.data.econews.NewsDataStrings;

import java.util.ArrayList;
import java.util.List;

import static com.softserve.edu.greencity.ui.tests.createnews.CreateNewsTexts.*;

@Listeners(value = RemoteSkipTestAnalyzer.class)
public class CreateNewsPositiveTest extends GreenCityTestRunner {
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
                .gotoCreateNewsPage();
    }

    @AfterMethod
    public void signOut() {
        createNewsPage.signOut();
    }

    @Test(testName = "GC-579", description = "GC-579")
    @Description("Verify UI of the ‘Create news’ page")
    public void verifyUiOfCreateNewsPage() {
        logger.info("verifyUiOfCreateNewsPage");

        softAssert.assertTrue(createNewsPage.getTitleField().isDisplayed());
        softAssert.assertTrue(createNewsPage.getSourceField().isDisplayed());
        softAssert.assertTrue(createNewsPage.getContentField().isDisplayed());

        TagsComponent tagsComponent = createNewsPage.getTagsComponent();
        softAssert.assertFalse(tagsComponent.isTagActive(Tag.NEWS));
        softAssert.assertFalse(tagsComponent.isTagActive(Tag.ADS));
        softAssert.assertFalse(tagsComponent.isTagActive(Tag.EVENTS));
        softAssert.assertFalse(tagsComponent.isTagActive(Tag.INITIATIVES));
        softAssert.assertFalse(tagsComponent.isTagActive(Tag.EDUCATION));

        softAssert.assertTrue(createNewsPage.getDropArea().isDisplayed());
        softAssert.assertTrue(createNewsPage.getUploadArea().isDisplayed());

        softAssert.assertTrue(createNewsPage.getDateField().isDisplayed());
        softAssert.assertTrue(createNewsPage.getAuthorField().isDisplayed());

        softAssert.assertTrue(createNewsPage.getCancelButton().isDisplayed());
        softAssert.assertTrue(createNewsPage.getPreviewButton().isDisplayed());
        softAssert.assertTrue(createNewsPage.getPublishButton().isDisplayed());

        createNewsPage.clickTitleField();
        softAssert.assertTrue(createNewsPage.getTitleField().equals(driver.switchTo().activeElement()));
        createNewsPage.clickSourceField();
        softAssert.assertTrue(createNewsPage.getSourceField().equals(driver.switchTo().activeElement()));
        createNewsPage.clickContentField();
        softAssert.assertTrue(createNewsPage.getContentField().equals(driver.switchTo().activeElement()));

        String CreateNewsLabelsFont = "Lato, sans-serif";
        String CreateNewsLabelsFontSize = "18px";
        String ContentLabelSize = "12px";
        String CreateNewsLabelsFontColor = "#494a49";
        softAssert.assertEquals(CreateNewsLabelsFont, createNewsPage.getTitleLabel().getFont());
        softAssert.assertEquals(CreateNewsLabelsFont, createNewsPage.getTagsLabel().getFont());
        softAssert.assertEquals(CreateNewsLabelsFont, createNewsPage.getSourceLabel().getFont());
        softAssert.assertEquals(CreateNewsLabelsFont, createNewsPage.getPictureLabel().getFont());
        softAssert.assertEquals(CreateNewsLabelsFont, createNewsPage.getContentLabel().getFont());

        softAssert.assertEquals(CreateNewsLabelsFontSize, createNewsPage.getTitleLabel().getSize());
        softAssert.assertEquals(CreateNewsLabelsFontSize, createNewsPage.getTagsLabel().getSize());
        softAssert.assertEquals(CreateNewsLabelsFontSize, createNewsPage.getSourceLabel().getSize());
        softAssert.assertEquals(CreateNewsLabelsFontSize, createNewsPage.getPictureLabel().getSize());
        softAssert.assertEquals(ContentLabelSize, createNewsPage.getContentLabel().getSize());

        softAssert.assertEquals(CreateNewsLabelsFontColor, createNewsPage.getTitleLabel().getColorHex());
        softAssert.assertEquals(CreateNewsLabelsFontColor, createNewsPage.getTagsLabel().getColorHex());
        softAssert.assertEquals(CreateNewsLabelsFontColor, createNewsPage.getSourceLabel().getColorHex());
        softAssert.assertEquals(CreateNewsLabelsFontColor, createNewsPage.getPictureLabel().getColorHex());
        softAssert.assertEquals(CreateNewsLabelsFontColor, createNewsPage.getContentLabel().getColorHex());

        createNewsPage.changeWindowWidth(800);

        softAssert.assertTrue(createNewsPage.getTitleField().isDisplayed());
        softAssert.assertTrue(createNewsPage.getSourceField().isDisplayed());
        softAssert.assertTrue(createNewsPage.getContentField().isDisplayed());

        softAssert.assertFalse(tagsComponent.isTagActive(Tag.NEWS));
        softAssert.assertFalse(tagsComponent.isTagActive(Tag.ADS));
        softAssert.assertFalse(tagsComponent.isTagActive(Tag.EVENTS));
        softAssert.assertFalse(tagsComponent.isTagActive(Tag.INITIATIVES));
        softAssert.assertFalse(tagsComponent.isTagActive(Tag.EDUCATION));

        softAssert.assertTrue(createNewsPage.getDropArea().isDisplayed());
        softAssert.assertTrue(createNewsPage.getUploadArea().isDisplayed());
        softAssert.assertTrue(createNewsPage.getDateField().isDisplayed());
        softAssert.assertTrue(createNewsPage.getAuthorField().isDisplayed());
        softAssert.assertTrue(createNewsPage.getCancelButton().isDisplayed());
        softAssert.assertTrue(createNewsPage.getPreviewButton().isDisplayed());
        softAssert.assertTrue(createNewsPage.getPublishButton().isDisplayed());

        softAssert.assertAll();

    }

    @Test(testName = "GC-595", description = "GC-595")
    @Description("Verify that user is on create news form")
    public void checkThatUserOnCreateNewsForm() {
        logger.info("checkThatUserOnCreateNewsForm starts");

        softAssert.assertEquals(createNewsPage.getCreateNewsMainTitleText(), CREATE_NEWS_TITLE.getText());
        softAssert.assertTrue(createNewsPage.isPublishButtonDisplayed());
        softAssert.assertAll();

    }
@Ignore
    @Test(testName = "GC-583", description = "GC-583")
    @Description("Checking of ukrainian translation of labels On CreateNews page")
    public void checkUkrainianTranslationOfLabelsOnCreateNewsPage() {

        createNewsPage.changeLanguageToUkrainian();

        for (CreateNewsUaExpectedText fieldName : CreateNewsUaExpectedText.values()) {
            String locatorEnum = fieldName.toString().replace("_UA_LANG", "");
            String actualResult = driver.findElement(CreateNewsPageLocators.valueOf(locatorEnum).getPath()).getText();
            Assert.assertEquals(actualResult.trim(), fieldName.getString().trim());
        }
    }

    @Ignore
    //This test required Article 1.jpg here: \src\main\java\com\softserve\edu\greencity\data\Article_1.jpg"
    @Test(testName = "GC-405", description = "GC-405", groups = "createNews")
    @Description("Posting news test")
    public void postingNewsTest() {

        final String titleText = "Plastic’ bags";
        final String contentText = "Ukrainian scientist invents eco-friendly ‘plastic’ bags";
        final String sourceText = "https://www.kyivpost.com/lifestyle/ukrainian-scientist-invents-eco-friendly-plastic-bags.html?cn-reloaded=1";
        NewsDataStrings imagePath = NewsDataStrings.IMAGE_MAN_WITH_BAGS;
        final String imagePathFinal = System.getProperty("user.dir") + NewsDataStrings.IMAGE_MAN_WITH_BAGS;
        final String[] tags = {"News", "Education"};
        createNewsPage.postingNews(titleText, tags, contentText, sourceText, imagePathFinal);
        EcoNewsService ecoNewsDao = new EcoNewsService();
        EcoNewsEntity lastNews = ecoNewsDao.getAllNewsOrderByDate().get(0);
        long lastNewsId = lastNews.getId();
        String actualResult = lastNews.getTitle();
        ecoNewsDao.deleteNewsById(lastNewsId);
        Assert.assertEquals(titleText, actualResult);
    }


    @Test(testName = "GC-623", description = "GC-623")
    @Description("Verify possibility of choosing tags")
    public void verifySelectAndDeselectPossibilityOfTags() {
        logger.info("verifySelectAndDeselectPossibilityOfTags starts");

        TagsComponent tagsComponent = createNewsPage.getTagsComponent();
        tagsComponent.selectTag(Tag.NEWS);
        Assert.assertTrue(tagsComponent.isTagActive(Tag.NEWS));
        tagsComponent.deselectTag(Tag.NEWS);
        Assert.assertFalse(tagsComponent.isTagActive(Tag.NEWS));

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

    @Test(testName = "GC-401", description = "GC-401", dataProvider = "getStringForTitle")
    @Description("Verify that user can publish news with valid characters in a 'Title' field")
    public void fillTitleFieldFromMinToMax(String title) {
        logger.info("fillTitleFieldFromMinToMax starts with parameters : " + title);

        EcoNewsPage ecoNewsPage = createNewsPage.fillFields(NewsDataRepository.get().getNewsWithValidData(title))
                .publishNews();

        softAssert.assertTrue(ecoNewsPage.isNewsDisplayedByTitle(title));
        getEcoNewsService().deleteNewsByTitle(title);
        softAssert.assertFalse(ecoNewsPage.refreshPage().isNewsDisplayedByTitle(title));
        softAssert.assertAll();

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

    @Test(testName = "GC-628", description = "GC-628", dataProvider = "getTagsList")
    @Description("Verify news creation with allowed amount of selected tags")
    public void checkCreateNewsWithOneToThreeTags(List<Tag> tags) {
        logger.info("checkCreateNewsWithOneToThreeTags starts with parameters : " + tags.toString());

        EcoNewsPage ecoNewsPage = createNewsPage.fillFields(NewsDataRepository.get().getNewsWithValidData(tags))
                .publishNews();

        softAssert.assertTrue(ecoNewsPage.isNewsDisplayedByTitle(NewsDataRepository.get().getNewsWithValidData().getTitle()));
        getEcoNewsService().deleteNewsByTitle(NewsDataRepository.get().getNewsWithValidData().getTitle());
        softAssert.assertFalse(ecoNewsPage.refreshPage().isNewsDisplayedByTitle(NewsDataRepository.get().getNewsWithValidData().getTitle()));
        softAssert.assertAll();

    }


    @Test(testName = "GC-616", description = "GC-616")
    @Description("Verify that news will be created, when user put more than 20 symbols in ‘Content’ field.")
    public void createNewsWithContentLengthMoreThen20() {
        logger.info("createNewsWithContentLengthMoreThen20 starts");

        EcoNewsPage ecoNewsPage = createNewsPage.fillFields(NewsDataRepository.get().getNewsWithValidData())
                .publishNews();

        softAssert.assertTrue(ecoNewsPage.isNewsDisplayedByTitle(NewsDataRepository.get().getNewsWithValidData().getTitle()));
        getEcoNewsService().deleteNewsByTitle(NewsDataRepository.get().getNewsWithValidData().getTitle());
        softAssert.assertFalse(ecoNewsPage.refreshPage().isNewsDisplayedByTitle(NewsDataRepository.get().getNewsWithValidData().getTitle()));
        softAssert.assertAll();

    }

    @Test(testName = "GC-613", description = "GC-613")
    @Description("Verify that news will be created when user add a link, which contains https:// in ‘Source’ field")
    public void createNewsWithSourceField() {
        logger.info("createNewsWithSourceField starts");

        EcoNewsPage ecoNewsPage = createNewsPage.fillFields(NewsDataRepository.get().getNewsWithValidSourceField()) //TODO BUG WITH TAGS
                .publishNews();

        softAssert.assertTrue(ecoNewsPage.isNewsDisplayedByTitle(NewsDataRepository.get().getNewsWithValidSourceField().getTitle()));
        getEcoNewsService().deleteNewsByTitle(NewsDataRepository.get().getNewsWithValidSourceField().getTitle());
        softAssert.assertFalse(ecoNewsPage.refreshPage().isNewsDisplayedByTitle(NewsDataRepository.get().getNewsWithValidSourceField().getTitle()));
        softAssert.assertAll();

    }

    @Test(testName = "GC-617", description = "GC-617")
    @Description("Verify that date is Auto-filled")
    public void verifyAutoFillingDataWhenCreateNews() {
        logger.info("verifyAutoFillingDataWhenCreateNews starts");

        EcoNewsPage ecoNewsPage = createNewsPage.fillFields(NewsDataRepository.get().getNewsWithValidData())
                .publishNews();

        softAssert.assertTrue(ecoNewsPage.isNewsDisplayedByTitle(NewsDataRepository.get().getNewsWithValidData().getTitle()));
        //TODO CHECK THAT DATE IS CREATED
        getEcoNewsService().deleteNewsByTitle(NewsDataRepository.get().getNewsWithValidData().getTitle());
        softAssert.assertFalse(ecoNewsPage.refreshPage().isNewsDisplayedByTitle(NewsDataRepository.get().getNewsWithValidData().getTitle()));
        softAssert.assertAll();

    }

    @Test(testName = "GC-629", description = "GC-629")
    @Description("Verify that news will be created when user add no image")
    public void verifyNewsCreationWithoutImage() {
        logger.info("verifyNewsCreationWithoutImage starts");

        EcoNewsPage ecoNewsPage = createNewsPage.fillFields(NewsDataRepository.get().getRequiredFieldsNews())
                .publishNews();

        softAssert.assertTrue(ecoNewsPage.isNewsDisplayedByTitle
                (NewsDataRepository.get().getRequiredFieldsNews().getTitle()));
        getEcoNewsService().deleteNewsByTitle(NewsDataRepository.get().getRequiredFieldsNews().getTitle());
        softAssert.assertFalse(ecoNewsPage.refreshPage().isNewsDisplayedByTitle
                (NewsDataRepository.get().getRequiredFieldsNews().getTitle()));
        softAssert.assertAll();

    }

    @LocalOnly
    @Test(testName = "GC-610", description = "GC-610") //TODO JIRA TEST IS NOT CORRECT!!!
    @Description("Verify that news will be created, when user add PNG image less than 10 MB")
    public void verifyNewsCreationWithPNGImage() {

        logger.info("verifyNewsCreationWithPNGImage starts");

        EcoNewsPage ecoNewsPage = createNewsPage.fillFields(NewsDataRepository.get().getRequiredFieldsNews())
                .uploadPNGImage()
                .publishNews();

        softAssert.assertTrue(ecoNewsPage.isNewsDisplayedByTitle(NewsDataRepository.get().getRequiredFieldsNews().getTitle()));
        getEcoNewsService().deleteNewsByTitle(NewsDataRepository.get().getRequiredFieldsNews().getTitle());
        softAssert.assertFalse(ecoNewsPage.refreshPage().isNewsDisplayedByTitle(NewsDataRepository.get().getRequiredFieldsNews().getTitle()));
        softAssert.assertAll();

    }

    @LocalOnly
    @Test(testName = "GC-611", description = "GC-611")
    @Description("Verify that news will be created, when user add JPG image less than 10 MB")
    public void verifyNewsCreationWithValidImage() {
        logger.info("verifyNewsCreationWithValidImage starts");

        EcoNewsPage ecoNewsPage = createNewsPage.fillFields(NewsDataRepository.get().getRequiredFieldsNews())
                .uploadJPGImage()
                .publishNews();

        softAssert.assertTrue(ecoNewsPage.isNewsDisplayedByTitle(NewsDataRepository.get().getRequiredFieldsNews().getTitle()));
        getEcoNewsService().deleteNewsByTitle(NewsDataRepository.get().getRequiredFieldsNews().getTitle());
        softAssert.assertFalse(ecoNewsPage.refreshPage().isNewsDisplayedByTitle(NewsDataRepository.get().getRequiredFieldsNews().getTitle()));
        softAssert.assertAll();

    }

    @Test(testName = "GC-620", description = "GC-620")
    @Description("Verify that user can publish news after clicking ‘Publish’ button")
    public void verifyThatNewsIsCreatedAfterClickOnPublishButton() {
        logger.info("verifyThatNewsIsCreatedAfterClickOnPublishButton starts");

        EcoNewsPage ecoNewsPage = createNewsPage.fillFields(NewsDataRepository.get().getAllFieldsNews()) //TODO BUG WITH TAGS
                .publishNews();

        softAssert.assertTrue(ecoNewsPage.isNewsDisplayedByTitle(NewsDataRepository.get().getAllFieldsNews().getTitle()));
        getEcoNewsService().deleteNewsByTitle(NewsDataRepository.get().getAllFieldsNews().getTitle());
        softAssert.assertFalse(ecoNewsPage.refreshPage().isNewsDisplayedByTitle(NewsDataRepository.get().getAllFieldsNews().getTitle()));
        softAssert.assertAll();

    }

    @Test(testName = "GC-622", description = "GC-622")
    @Description("Verify that news will be created with empty source field")
    public void verifyPossibilityOfCreatingNewsWithEmptySourceField() {
        logger.info("verifyPossibilityOfCreatingNewsWithEmptySourceField starts");

        NewsData newsWithEmptySource = NewsDataRepository.get().getNewsWithEmptySourceField();
        EcoNewsPage ecoNewsPage = createNewsPage.fillFields(newsWithEmptySource)
                .publishNews();

        softAssert.assertTrue(ecoNewsPage
                        .isNewsDisplayedByTitle(newsWithEmptySource.getTitle()),
                "Checking if news with title \"" + newsWithEmptySource.getTitle() + "\" is displayed");


        getEcoNewsService().deleteNewsByTitle(NewsDataRepository.get().getNewsWithEmptySourceField().getTitle());
        softAssert.assertFalse(ecoNewsPage.refreshPage().isNewsDisplayedByTitle(NewsDataRepository.get().getNewsWithEmptySourceField().getTitle()));
        softAssert.assertAll();

    }

    @Test(testName = "GC-402", description = "GC-402")
    @Description("Verify that 'Title' field is auto-resizing")
    public void verifyThatTitleFieldIsAutoResizing() {
        logger.info("verifyThatTitleFieldIsAutoResizing starts");

        int heightBeforeSetTitle = createNewsPage.getTitleFieldHeight();
        int widthBeforeSetTitle = createNewsPage.getTitleFieldWidth();

        createNewsPage.fillFields(NewsDataRepository.get().getTitleForAutoResizeCheck());
        softAssert.assertEquals(createNewsPage.getTitleFieldWidth(), widthBeforeSetTitle);
        softAssert.assertTrue(createNewsPage.getTitleFieldHeight() > (heightBeforeSetTitle * 2));
        softAssert.assertAll();

    }

    @Test(testName = "GC-651", description = "GC-651")
    @Description("Verify that 'Content' field is auto-resizing and can be manually resized by user")
    public void VerifyThatContentFieldIsAutoResizingAndCanBeResizedByUser() {
        logger.info("verifyThatContentFieldIsAutoResizing starts");

        int widthBeforeSetContent = createNewsPage.getContentWidth();
        int heightBeforeSetContent = createNewsPage.getContentHeight();

        createNewsPage.fillFields(NewsDataRepository.get().getNewsWithContentFieldForCheckAutoResize());
        softAssert.assertEquals(createNewsPage.getContentWidth(), widthBeforeSetContent);
        softAssert.assertNotEquals(createNewsPage.getContentHeight(), heightBeforeSetContent);
        softAssert.assertEquals(createNewsPage.getContentHeight() + 100, createNewsPage.changeContentFieldSize(100).getContentHeight());

    }

    @Test(testName = "GC-618", description = "GC-618")
    @Description("Verify that Author is Auto-filled based on Name of registered User")
    public void VerifyThatAuthorIsAutoFilledBasedOnNameOfRegisteredUser() {
        logger.info("VerifyThatAuthorIsAutoFilledBasedOnNameOfRegisteredUser starts");

        SingleNewsPage singleNewsPage = createNewsPage.fillFields(NewsDataRepository.get().getNewsWithValidData())
                .publishNews()
                .switchToSingleNewsPageByNumber(0);

        softAssert.assertEquals(singleNewsPage.getAuthorNameOnly(), singleNewsPage.getTopUserName());
        EcoNewsPage ecoNewsPage = loadApplication().navigateMenuEcoNews();
        getEcoNewsService().deleteNewsByTitle(NewsDataRepository.get().getNewsWithValidData().getTitle());
        softAssert.assertFalse(ecoNewsPage.refreshPage().isNewsDisplayedByTitle(NewsDataRepository.get().getNewsWithValidData().getTitle()));
        softAssert.assertAll();
    }

    @Test(testName = "GC-597", description = "Verify that after news was created pop-up appears with valid language")
    @Description("Verify that after news was created pop-up appears with valid language")
    public void verifyPopupLanguageAfterSuccessfulNewsCreation() {
        logger.info("verifyPopupLanguageAfterSuccessfulNewsCreation starts");

        createNewsPage.fillFields(NewsDataRepository.get().getAllFieldsNews())
                .clickPublishButton();

        softAssert.assertEquals(createNewsPage.getConfirmationHeaderText(), CONFIRMATION_HEADER_MESSAGE.getText());
        softAssert.assertEquals(createNewsPage.getConfirmationDescriptionText(), CONFIRMATION_DESCRIPTON_MESSAGE.getText());
        softAssert.assertAll();

    }

    @Test(testName = "GC-656", description = "Verify that window with the image is displayed without changes in it's size")
    @Description("Verify that window with the image is displayed without changes in it's size")
    public void verifyThatImageIsDisplayedWithoutChangesInSize() {
        logger.info("verifyThatImageIsDisplayedWithoutChangesInSize starts");
        createNewsPage.uploadJPGImage();

        int imageWindowHeight = 231; //Expected window size according to a mockup
        int imageWindowWidth = 426;

        softAssert.assertEquals(imageWindowHeight, createNewsPage.getDropArea().getSize().getHeight());
        softAssert.assertEquals(imageWindowWidth, createNewsPage.getDropArea().getSize().getWidth());
        softAssert.assertAll();

    }

}
