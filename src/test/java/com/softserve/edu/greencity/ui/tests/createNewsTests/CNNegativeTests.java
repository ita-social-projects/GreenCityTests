package com.softserve.edu.greencity.ui.tests.createNewsTests;

import com.softserve.edu.greencity.ui.data.UserRepository;
import com.softserve.edu.greencity.ui.data.econews.NewsData;
import com.softserve.edu.greencity.ui.data.econews.NewsDataRepository;
import com.softserve.edu.greencity.ui.data.econews.Tag;
import com.softserve.edu.greencity.ui.pages.econews.CreateNewsPage;
import com.softserve.edu.greencity.ui.pages.econews.EconewsPage;
import com.softserve.edu.greencity.ui.pages.econews.PreViewPage;
import com.softserve.edu.greencity.ui.tests.GreenCityTestRunner;
import com.softserve.edu.greencity.ui.tools.DBQueries;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import java.util.ArrayList;
import java.util.List;

public class CNNegativeTests extends GreenCityTestRunner {

    @BeforeMethod
    public void login() {
        if(isLoginingNow()) return;
        loadApplication()
                .loginIn(UserRepository.get().defaultUserCredentials())
                .navigateMenuTipsTricks();
    }

    /**
     * @ID=GC-592
     */
    @Test
    public void verifyImpossibilityOFCreatingNewsWithTooShortContent() {
        CreateNewsPage createNewsPage = loadApplication()
                .navigateMenuEconews()
                .gotoCreateNewsPage()
                .fillFields(NewsDataRepository.getRequiredFieldsNews());
        createNewsPage.clearTitleField();
        createNewsPage.clearContentField();
        createNewsPage.setContentField("oops");
        boolean isDisabled = driver.findElement(By.cssSelector(".submit-buttons button+button+button")).isEnabled();
        Assert.assertFalse(isDisabled);
    }

    /**
     * @ID=GC-645
     */
    @Test
    public void verifyImpossibilityOfCreatingTestWithUncorrectUrlInSourceField() {
        CreateNewsPage createNewsPage = loadApplication()
                .navigateMenuEconews()
                .gotoCreateNewsPage()
                .fillFields(NewsDataRepository.getRequiredFieldsNews());
        createNewsPage.clearSourceField();
        createNewsPage.setSourceField("www.greenmatch.co.uk/blog/how-to-be-more-eco-friendly");
        SoftAssert softAssert = new SoftAssert();
        boolean isDisabled = driver.findElement(By.cssSelector(".submit-buttons button+button+button")).isEnabled();
        softAssert.assertFalse(isDisabled);
        createNewsPage.clearSourceField();
        createNewsPage.setSourceField("www.greenmatch.co.uk/blog/how-to-be-more-eco-friendlyhttps://www.greenmatch.co.uk/blog/how-to-be-more-eco-friendly");
        isDisabled = driver.findElement(By.cssSelector(".submit-buttons button+button+button")).isEnabled();
        softAssert.assertFalse(isDisabled);
        softAssert.assertAll();
    }

    /**
     * @ID=GC-637
     */
    @Test
    public void verifyImpossibilityOfCreatingNewsWithEmptyFields() {
        CreateNewsPage createNewsPage = loadApplication()
                .navigateMenuEconews()
                .gotoCreateNewsPage();
        boolean isDisabled = driver.findElement(By.cssSelector(".submit-buttons button+button+button")).isEnabled();
        Assert.assertFalse(isDisabled);
    }

    /**
     * @gc1290
     * @ID=GC-638git
     */
    @Test
    public void verifyImpossibilityOfCreatingNewsWithoutContent() throws InterruptedException {
        NewsData newsData = NewsDataRepository.getRequiredFieldsNews();
        newsData.setContent("");
        CreateNewsPage createNewsPage = loadApplication()
                .navigateMenuEconews()
                .gotoCreateNewsPage()
                .fillFields(newsData);
        Thread.sleep(4000);
        SoftAssert softAssert = new SoftAssert();
        boolean isDisabled = driver.findElement(By.cssSelector(".submit-buttons button+button+button")).isEnabled();
        softAssert.assertFalse(isDisabled);
        softAssert.assertTrue(driver.findElement(By.cssSelector(".textarea-wrapper p")).getText().equals("Must be minimum 20 symbols"));
        softAssert.assertAll();
    }

    /**
     * @ID=GC-642
     */
    @Test
    public void verriyImpossibilityOfCreatingNewsWithoutAnyTags() {
        CreateNewsPage createNewsPage = loadApplication()
                .navigateMenuEconews()
                .gotoCreateNewsPage()
                .fillFields(NewsDataRepository.getRequiredFieldsNews());
        List<Tag> tags = new ArrayList<>();
        for (Tag tag : Tag.values()) {
            tags.add(tag);
        }
        createNewsPage.getTagsComponent().deselectTags(tags);
        boolean isDisabled = driver.findElement(By.cssSelector(".submit-buttons button+button+button")).isEnabled();
        Assert.assertFalse(isDisabled);
        Assert.assertFalse(createNewsPage.goToPreViewPage().isPublishButtonPresent());
    }

    /**
     * @ID=GC-644
     */
    @Test
    public void verifyImpossibilityCreateNewsWithEmptyTitle() {
        CreateNewsPage createNewsPage = loadApplication()
                .navigateMenuEconews()
                .gotoCreateNewsPage();
        createNewsPage.setContentField("March 4 – 7, 2020, International Exhibition Center," +
                " Kyiv, 15 Brovarsky Ave.," +
                " takes place the most important event for professionals and funs of natural food and healthy life");
        createNewsPage.getTagsComponent().selectTag(Tag.NEWS);
        createNewsPage.clearTitleField();
        SoftAssert softAssert = new SoftAssert();
        boolean isDisabled = driver.findElement(By.cssSelector(".submit-buttons button+button+button")).isEnabled();
        softAssert.assertFalse(isDisabled);
        softAssert.assertTrue(driver.findElement(By.cssSelector(".left-form-column label span")).getText().equals("Should contain maximum 170 symbols"));
        softAssert.assertAll();
    }

    /**
     * @ID=GC-654
     */
    @Test
    public void verifyImpossibilityToSelectOneTagTwice() {
        CreateNewsPage createNewsPage = loadApplication()
                .navigateMenuEconews()
                .gotoCreateNewsPage()
                .fillFields(NewsDataRepository.getRequiredFieldsNews());
        createNewsPage.clearTitleField();
        String title = "public void verifyImpossibilityToSelectOneTagTwice() {";
        createNewsPage.setTitleField(title);
        List<Tag> tags = new ArrayList<>();
        for (Tag tag : Tag.values()) {
            tags.add(tag);
        }
        createNewsPage.getTagsComponent().deselectTags(tags);
        createNewsPage.getTagsComponent().selectTag(Tag.NEWS);
        createNewsPage.goToPreViewPage().backToCreateNewsPage();
        createNewsPage.getTagsComponent().deselectTag(Tag.NEWS);
        createNewsPage.getTagsComponent().selectTag(Tag.NEWS);
        EconewsPage econewsPage = createNewsPage.publishNews();
        List<WebElement> elements = driver.findElements(By.cssSelector("div.list-gallery-content"));
        boolean isPresent = false;
        for (WebElement e : elements) {
            if (e.findElement(By.cssSelector(".title-list p")).getText().equals(title)) {
                isPresent = true;
                int tagsCount = e.findElements(By.cssSelector(".filter-tag div")).size();
                Assert.assertTrue(tagsCount == 1);
                break;
            }
            Assert.assertTrue(isPresent);
        }
        new DBQueries().deleteNewsByTitle(title);
    }

    /**
     * @ID=GC-588 1. Change expected title to "Download PNG or JPG only. File size should be less than 10MB"
     */
    @Test
    public void verifyImpossibilityOfUploadingTooLargeImage() {
        CreateNewsPage createNewsPage = loadApplication()
                .navigateMenuEconews()
                .gotoCreateNewsPage()
                .fillFields(NewsDataRepository.getRequiredFieldsNews());
        createNewsPage.uploadFile(createNewsPage.getDropArea(), "src/test/resources/invalid.gif");
        String warning = driver.findElement(By.cssSelector(".dropzone+.warning")).getText();
        Assert.assertEquals(warning, "Download PNG or JPG only. File size should be less than 10MB");
    }

    /**
     * ID GC-593
     */
    @Test
    public void checkUnvisibilityOfCreateNewsButtonForGuest() {
        EconewsPage econewsPage = loadApplication().signOut()
                .navigateMenuEconews();
        Assert.assertTrue(driver.findElements(By.cssSelector("#create-button")).size() == 0);
    }

    /**
     * Create news negative test
     *@author lv-493
     * @param newsData
     */
    @Test(dataProvider = "newsInvalidDataProvider", invocationCount = 3)
    public void checkWarningsWhenCreateNewsWithInvalidData(NewsData newsData) {
        logger.info("createNewsNegativeTest starts with parameters: " + newsData.toString());
        SoftAssert softAssert = new SoftAssert();
        CreateNewsPage createNewsPage = loadApplication()
                .navigateMenuEconews()
                .gotoCreateNewsPage();
        createNewsPage.clearSourceField();
        createNewsPage.setSourceField(newsData.getSource());
        createNewsPage.clearTitleField();
        createNewsPage.setTitleField(newsData.getTitle());
        createNewsPage.clearContentField();
        createNewsPage.setContentField(newsData.getContent());
        createNewsPage.uploadFile(createNewsPage.getDropArea(), newsData.getFilePath());
        softAssert.assertTrue(createNewsPage.isTitleDescriptionWarning(), "Title warning is not present");
        softAssert.assertTrue(createNewsPage.isContentDescriptionWarning(), "Content warning is not present");
        softAssert.assertTrue(createNewsPage.isPictureDescriptionWarning(), "Picture warning is not present");
        softAssert.assertTrue(createNewsPage.isSourceDescriptionWarning(), "Source warning is not present");
        softAssert.assertFalse(createNewsPage.isPublishButtonClickable(), "Publish button should not be clickable");
        createNewsPage.getTagsComponent().deselectTags(newsData.getTags());
        createNewsPage.getTagsComponent().selectTags(newsData.getTags());
        softAssert.assertTrue(createNewsPage.isTagsDescriptionWarning(), "Tags warning is not present");
        softAssert.assertAll();
        createNewsPage.getTagsComponent().deselectTags(newsData.getTags());
        PreViewPage preViewPage = createNewsPage.goToPreViewPage();
        Assert.assertFalse(preViewPage.isPublishButtonPresent());
        preViewPage.signOut();
    }

    @DataProvider
    public Object[] newsInvalidDataProvider() {
        return new Object[]{
                NewsDataRepository.getInvalidData()
        };
    }

}