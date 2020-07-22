package com.softserve.edu.greencity.ui.tests.createnews;

import com.softserve.edu.greencity.ui.data.UserRepository;
import com.softserve.edu.greencity.ui.data.econews.NewsData;
import com.softserve.edu.greencity.ui.data.econews.NewsDataRepository;
import com.softserve.edu.greencity.ui.data.econews.Tag;
import com.softserve.edu.greencity.ui.pages.econews.CreateNewsPage;
import com.softserve.edu.greencity.ui.pages.econews.EconewsPage;
import com.softserve.edu.greencity.ui.pages.econews.TagsComponent;
import com.softserve.edu.greencity.ui.pages.tipstricks.TipsTricksPage;
import com.softserve.edu.greencity.ui.tests.GreenCityTestRunner;
import com.softserve.edu.greencity.ui.tools.DBQueries;
import com.softserve.edu.greencity.ui.tools.DateUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.sql.SQLException;
import java.util.*;

public class CNFunctionalityTests extends GreenCityTestRunner {
    DBQueries dataBase = new DBQueries();

    @BeforeMethod
    public void login() {
        if(isLoginingNow()) return;
        loadApplication()
                .loginIn(UserRepository.get().defaultUserCredentials())
                .navigateMenuTipsTricks();
    }

    /**
     * Cancel news creation test.
     * @author lv-493
     */
    @Test
    public void cancelNewsCreatingTest() {
        logger.info("cancelNewsCreatingTest starts");
        EconewsPage econewsPage = loadApplication()
                .navigateMenuEconews();
        int expectedCount = econewsPage.getNumberOfItemComponent();
        econewsPage = econewsPage.gotoCreateNewsPage()
                .cancelNewsCreating();
        Assert.assertEquals(econewsPage.getNumberOfItemComponent(), expectedCount);
        Assert.assertEquals(driver.getTitle(), "Eco news");
        econewsPage.signOut();
    }

    /**
     * @ID=GC-397
     */
    @Test
    public void checkPossibilityToCreateNewsAfterFillingMandatoryFields() {
        CreateNewsPage createNewsPage = loadApplication()
                .navigateMenuEconews()
                .gotoCreateNewsPage();
        String title = "Be eco! Be cool!";
        createNewsPage.setTitleField(title);
        createNewsPage.getTagsComponent().selectTag(Tag.NEWS);
        createNewsPage.setContentField("It's so healthy, fun and cool to bring eco habits in everyday life");
        createNewsPage.clickPublishButton();
        dataBase.deleteNewsByTitle(title);
    }

    /**
     * @ID=GC-623 1. At once max selected tags amount is 4, so this test description need little correct
     */
    @Test
    public void verifySelectAndDeselectPossibilityOfTags() {
        CreateNewsPage createNewsPage = loadApplication()
                .navigateMenuEconews()
                .gotoCreateNewsPage();
        Map<String, WebElement> ourTags = new HashMap<>();
        TagsComponent tagsComponent = createNewsPage.getTagsComponent();
        SoftAssert softAssert = new SoftAssert();
        for (WebElement e : driver.findElements(By.cssSelector("div.tags button"))) {
            ourTags.put(e.getText().toLowerCase(), e);
        }
        for (Tag tag : Tag.values()) {
            tagsComponent.selectTag(tag);
            softAssert.assertTrue(ourTags.get(tag.name().toLowerCase()).getAttribute("class").contains("filters-color"));
            tagsComponent.deselectTag(tag);
            softAssert.assertFalse(ourTags.get(tag.name().toLowerCase()).getAttribute("class").contains("filters-color"));
        }
        softAssert.assertAll();
    }

    /**
     * @ID=GC-402
     * TODO in this test we should check that title textArea will grow after adding too long string
     * TODO but this element has another functionality:
     * TODO it don't grow however has a limit of max length of string
     */

    /**
     * @ID=GC-629
     * TODO this test need to compare images
     */

    /**
     * @ID=GC-651
     * TODO looks like I will do this with help of Robot
     */

    /**
     * @ID=GC-584
     * TODO can't realize this test even manually
     * TODO looks like we have really problems on backend with this functionality
     * TODO in any case red warning title under the upload area don't display (only standart grey)
     */

    /**
     * @ID=GC-590
     * TODO add checking that news has default image
     * TODO in practice the behavior of the site is different with test description...
     */

    @Test
    public void verifyThatWithInvalidImgFormatNewsWillPublishWithDefaultImg() {
        CreateNewsPage createNewsPage = loadApplication()
                .navigateMenuEconews()
                .gotoCreateNewsPage()
                .fillFields(NewsDataRepository.getRequiredFieldsNews());
        createNewsPage.clearTitleField();
        String title = "Hello, World! How are you doing?";
        createNewsPage.setTitleField(title);
        createNewsPage.uploadFile(createNewsPage.getDropArea(), "src/test/resources/credentials.properties");
        String warning = driver.findElement(By.cssSelector(".dropzone+.warning")).getText();
        Assert.assertEquals(warning, "Download PNG or JPG only. File size should be less than 10MB");
        EconewsPage econewsPage = createNewsPage.publishNews();
        List<WebElement> elements = driver.findElements(By.cssSelector("div.list-gallery-content"));
        boolean isPresent = false;
        for(WebElement e : elements) {
            if(e.findElement(By.cssSelector(".title-list p")).getText().equals(title)) {
                isPresent = true;
                break;
            }
        }
        Assert.assertTrue(isPresent);
        dataBase.deleteNewsByTitle(title);
    }

    /**
     * @ID=GC-634
     * TODO we already have the same test (CG-584)
     */

    /**
     * @ID=GC-643
     */

    @Test
    public void verifyPossibilityOfMaxThreeTagsWhenCreateNews() {
        CreateNewsPage createNewsPage = loadApplication()
                .navigateMenuEconews()
                .gotoCreateNewsPage()
                .fillFields(NewsDataRepository.getRequiredFieldsNews());
        createNewsPage.clearTitleField();
        String title = "checking tags";
        createNewsPage.setTitleField(title);
        List<Tag> tags = new ArrayList<>();
        for (Tag tag : Tag.values()) {
            tags.add(tag);
        }
        createNewsPage.getTagsComponent().deselectTags(tags);
        createNewsPage.getTagsComponent().selectTags(tags);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(driver.findElement(By.cssSelector(".tags p")).getAttribute("class").contains("warning"));
        softAssert.assertTrue(driver.findElement(By.cssSelector(".tags p")).getText().equals("Only 3 tags can be added"));
        createNewsPage.goToPreViewPage().backToCreateNewsPage();
        createNewsPage.getTagsComponent().deselectTags(tags);
        createNewsPage.getTagsComponent().selectTags(tags);
        softAssert.assertTrue(driver.findElement(By.cssSelector(".tags p")).getAttribute("class").contains("warning"));
        softAssert.assertTrue(driver.findElement(By.cssSelector(".tags p")).getText().equals("Only 3 tags can be added"));
        EconewsPage econewsPage = createNewsPage.publishNews();
        List<WebElement> elements = driver.findElements(By.cssSelector("div.list-gallery-content"));
        boolean isPresent = false;
        for (WebElement e : elements) {
            if (e.findElement(By.cssSelector(".title-list p")).getText().equals(title)) {
                isPresent = true;
                int tagsCount = e.findElements(By.cssSelector(".filter-tag div")).size();
                softAssert.assertTrue(tagsCount == 3);
                break;
            }
            softAssert.assertTrue(isPresent);
        }

        dataBase.deleteNewsByTitle(title);
    }

    /**
     * @ID=GC-610
     * TODO compare images
     */

    /**
     * @ID=GC-611
     * TODO compare images
     */

    /**
     * @ID=GC-617
     */

    @Test
    public void verifingAutoFillingDataWhenCreateNews() throws InterruptedException, SQLException {
        CreateNewsPage createNewsPage = loadApplication()
                .navigateMenuEconews()
                .gotoCreateNewsPage();
        NewsData newsData = NewsDataRepository.getRequiredFieldsNews();
        createNewsPage.fillFields(newsData);
        EconewsPage econewsPage = createNewsPage.publishNews();
        List<WebElement> elements = driver.findElements(By.cssSelector("div.list-gallery-content"));
        boolean isPresent = false;
        WebElement myNews;
        for (WebElement e : elements) {
            if (e.findElement(By.cssSelector(".title-list p")).getText().equals(newsData.getTitle())) {
                isPresent = true;
                e.findElement(By.cssSelector(".list-image img")).click();
                break;
            }
            Assert.assertTrue(isPresent);
        }
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions
                        .visibilityOf(driver
                                .findElement(By.cssSelector("app-eco-news-widget>div>p"))));
        Assert.assertEquals(
                driver.findElement(By.cssSelector(".news-info-date")).getText(),
                DateUtil.getCurrentDate("MMM dd, yyyy"));
        driver.findElement(By.cssSelector("a[href='#/welcome'")).click();
        new TipsTricksPage(driver);
        dataBase.deleteNewsByTitle(newsData.getTitle());
    }

    /**
     * @ID=405-1304
     * @return
     */
    @Test
    public void checkThatNewsWillCreate() {
        NewsData newsData = NewsDataRepository.getRequiredFieldsNews();
        String title = "Test 'checkThatNewsWillCreate'";
        newsData.setTitle(title);
        loadCreateNewsPage()
                .fillFields(newsData)
                .publishNews();
        dataBase.deleteNewsByTitle(title);
    }

    public CreateNewsPage loadCreateNewsPage() {
        return loadApplication()
                .navigateMenuEconews()
                .gotoCreateNewsPage();
    }

}
