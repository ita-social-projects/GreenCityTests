package com.softserve.edu.greencity.ui.tests.createnews;

import com.softserve.edu.greencity.data.Languages;
import com.softserve.edu.greencity.data.users.User;
import com.softserve.edu.greencity.data.users.UserRepository;
import com.softserve.edu.greencity.data.econews.NewsDataRepository;
import com.softserve.edu.greencity.data.econews.Tag;
import com.softserve.edu.greencity.ui.pages.econews.CreateNewsPage;
import com.softserve.edu.greencity.ui.pages.econews.EcoNewsPage;
import com.softserve.edu.greencity.ui.pages.econews.ItemComponent;
import com.softserve.edu.greencity.ui.pages.econews.PreviewPage;
import com.softserve.edu.greencity.ui.tests.runner.GreenCityTestRunner;
import com.softserve.edu.greencity.ui.tools.testng.RemoteSkipTestAnalyzer;
import io.qameta.allure.Description;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static com.softserve.edu.greencity.ui.locators.CreateNewsPageLocators.CREATE_NEWS_MAIN_TITLE;
import static com.softserve.edu.greencity.ui.tests.createnews.CreateNewsTexts.*;

@Listeners(value = RemoteSkipTestAnalyzer.class)
public class CreateNewsPreviewTest extends GreenCityTestRunner {
    private User getTemporaryUser() {
        return UserRepository.get().temporary();
    }

    @Test(testName = "GC-621", description = "GC-621")
    @Description("Verify that user can go to preview page")
    public void verifyPossibilityOfPreViewingNewsPage() {
        logger.info("verifyPossibilityOfPreViewingNewsPage starts");

        PreviewPage preViewPage = loadApplication()
                .loginIn(getTemporaryUser())
                .navigateMenuEcoNews()
                .gotoCreateNewsPage()
                .fillFields(NewsDataRepository.get().getRequiredFieldsNews())
                .goToPreViewPage();

        softAssert.assertTrue(preViewPage.isBackToEditingButtonDisplayed());
        softAssert.assertAll();

        preViewPage.signOut();
    }

    @Test(testName = "GC-633", description = "GC-633")
    @Description("Verify that preview page is displayed correctly")
    public void verifyThatPreViewIsDisplayedCorrectly() {
        logger.info("verifyThatPreViewIsDisplayedCorrectly starts");

        PreviewPage preViewPage = loadApplication()
                .loginIn(getTemporaryUser())
                .navigateMenuEcoNews()
                .gotoCreateNewsPage()
                .fillFields(NewsDataRepository.get().getRequiredFieldsNews())
                .goToPreViewPage();

        softAssert.assertTrue(preViewPage.isBackToEditingButtonDisplayed());
        softAssert.assertEquals(preViewPage.getTitleFieldText(), VALID_TITLE.getText());
        softAssert.assertEquals(preViewPage.getContentFieldText().trim(), VALID_CONTENT.getText());
        softAssert.assertAll();

        preViewPage.signOut();
    }

    @Test(testName = "GC-606", description = "GC-606")
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

        softAssert.assertEquals(createNewsPage.getCreateNewsMainTitleText(), CREATE_NEWS_TITLE.getText());
        softAssert.assertAll();

        createNewsPage.signOut();
    }

    @Test(testName = "GC-607", description = "GC-607")
    @Description("Verify that user can cancel news creation if he decided to drop posting")
    public void verifyThatUserCanCancelNewsCreation() {
        logger.info("verifyThatUserCanCancelNewsCreation starts");

        EcoNewsPage ecoNewsPage = loadApplication()
                .loginIn(getTemporaryUser())
                .navigateMenuEcoNews()
                .gotoCreateNewsPage()
                .fillFields(NewsDataRepository.get().getAllFieldsNews())
                .clickCancelButton()
                .clickCancelEditingButton();

        softAssert.assertTrue(ecoNewsPage.isGridViewDisplayed());
        softAssert.assertFalse(ecoNewsPage.isNewsDisplayedByTitle(NewsDataRepository.get().getAllFieldsNews().getTitle()));
        softAssert.assertAll();

        ecoNewsPage.signOut();
    }

    @Test(testName = "GC-403", description = "GC-403")
    @Description("Verify that User can go back to editing news by following ‘Back to editing’ link")
    public void verifyPossibilityOfGoingBackToEditNews() {
        logger.info("verifyPossibilityOfGoingBackToEditNews starts");

        PreviewPage preViewPage = loadApplication()
                .loginIn(getTemporaryUser())
                .navigateMenuEcoNews()
                .gotoCreateNewsPage()
                .fillFields(NewsDataRepository.get().getAllFieldsNews())
                .goToPreViewPage();

        softAssert.assertTrue(preViewPage.isBackToEditingButtonDisplayed());
        CreateNewsPage createNewsPage = preViewPage.backToCreateNewsPage();
        softAssert.assertTrue(createNewsPage.getTagsComponent().isTagActive(Tag.NEWS)); //TODO BUG WITH TAGS
        softAssert.assertTrue(createNewsPage.getTagsComponent().isTagActive(Tag.EVENTS));
        createNewsPage.navigateMenuEcoNews().gotoCreateNewsPage();
        softAssert.assertFalse(createNewsPage.getTagsComponent().isTagActive(Tag.NEWS));
        softAssert.assertFalse(createNewsPage.getTagsComponent().isTagActive(Tag.EVENTS));
        softAssert.assertAll();

        createNewsPage.signOut();
    }

    //TODO add some test on publishing news right from preview page

    @Test(testName = "GC-657", description = "GC-657")
    @Description("Verify that title and content are displayed with wrapping and fill in container space on the 'Preview' page without spaces")
    public void verifyTitleAndContentAreDisplayedWithoutSpaces() {
        logger.info("verifyTitleAndContentAreDisplayedWithoutSpaces", "starts");
        PreviewPage preViewPage = loadApplication()
                .loginIn(getTemporaryUser())
                .navigateMenuEcoNews()
                .gotoCreateNewsPage()
                .fillFields(NewsDataRepository.get().getWrappedNews())
                .goToPreViewPage();
        softAssert.assertFalse(preViewPage.isHorizontalScrollPresent());
        int numberTitleRows = preViewPage.getTitleNumberRow();
        softAssert.assertTrue(numberTitleRows > 1);
        int numberContentRows = preViewPage.getTitleNumberRow();
        softAssert.assertTrue(numberContentRows > 1);
        preViewPage.setWindowsDimensions(500,300);
        softAssert.assertFalse(preViewPage.isHorizontalScrollPresent());
        int numberTitleRowsMini = preViewPage.getTitleNumberRow();
        softAssert.assertTrue(numberTitleRowsMini > 1);
        int numberContentRowsMini = preViewPage.getTitleNumberRow();
        softAssert.assertTrue(numberContentRowsMini > 1);
        softAssert.assertAll();

    }

    @Test(testName = "GC-658", description = "GC-658")
    @Description("Verify that title and content are displayed with wrapping and fill in container space on the 'Preview' page with spaces")
    public void verifyTitleAndContentAreDisplayedWithSpaces(){
        logger.info("verifyTitleAndContentAreDisplayedWithSpaces", "starts");
        PreviewPage preViewPage = loadApplication()
                .loginIn(getTemporaryUser())
                .navigateMenuEcoNews()
                .gotoCreateNewsPage()
                .fillFields(NewsDataRepository.get().getWrappedNewsWithSpaces())
                .goToPreViewPage();
        softAssert.assertFalse(preViewPage.isHorizontalScrollPresent());
        int numberTitleRowsOfWords = preViewPage.getTitleNumberRow();
        softAssert.assertTrue(numberTitleRowsOfWords > 1);
        int numberContentRowsOfWords = preViewPage.getTitleNumberRow();
        softAssert.assertTrue(numberContentRowsOfWords > 1);
        preViewPage.setWindowsDimensions(500,300);
        int numberTitleRowsOfWordsMini = preViewPage.getTitleNumberRow();
        softAssert.assertTrue(numberTitleRowsOfWordsMini > 1);
        int numberContentRowsOfWordsMini = preViewPage.getTitleNumberRow();
        softAssert.assertTrue(numberContentRowsOfWordsMini > 1);
        softAssert.assertFalse(preViewPage.isHorizontalScrollPresent());
        softAssert.assertAll();
    }
}
