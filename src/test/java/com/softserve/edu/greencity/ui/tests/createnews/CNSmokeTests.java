package com.softserve.edu.greencity.ui.tests.createnews;

import com.softserve.edu.greencity.ui.data.UserRepository;
import com.softserve.edu.greencity.ui.data.econews.NewsData;
import com.softserve.edu.greencity.ui.data.econews.NewsDataRepository;
import com.softserve.edu.greencity.ui.pages.econews.CreateNewsPage;
import com.softserve.edu.greencity.ui.pages.econews.EconewsPage;
import com.softserve.edu.greencity.ui.pages.econews.PreViewPage;
import com.softserve.edu.greencity.ui.tests.GreenCityTestRunner;
import com.softserve.edu.greencity.ui.tools.DBQueries;
import com.softserve.edu.greencity.ui.tools.DateUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class CNSmokeTests extends GreenCityTestRunner {
    DBQueries dataBase = new DBQueries();
    String createNewsUrl = BASE_URL.substring(0, BASE_URL.indexOf('#')) + "#/news/create-news";

    @BeforeMethod
    public void login() {
        if(isLoginingNow()) return;
        loadApplication()
                .loginIn(UserRepository.get().defaultUserCredentials())
                .navigateMenuTipsTricks();
    }

    /**
     * @ID=GC-591
     */
    @Test
    public void checkVisibilityOfCreateNewsButtonForRegisteredUser() {
        EconewsPage econewsPage = loadApplication()
                .navigateMenuEconews();
        Assert.assertTrue(driver.findElements(By.cssSelector("#create-button")).size() == 1);
    }

    /**
     * @ID=GC-595
     */
    @Test
    public void checkThatUserOnCreateNewsForm() {
        CreateNewsPage createNewsPage = loadCreateNewsPage();
        WebElement createNewsMainTitle = driver.findElement(By.cssSelector(".title h2"));
        int numberOfButtons = driver.findElements(By.cssSelector(".submit-buttons button")).size();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(createNewsMainTitle.getText(), "Create news");
        softAssert.assertTrue(numberOfButtons == 3);
        softAssert.assertAll();
    }

    /**
     * @ID=621-1300
     */
    @Test
    public void checkPreviewPage() {
        CreateNewsPage createNewsPage = loadCreateNewsPage();
        PreViewPage preViewPage = createNewsPage
                .fillFields(NewsDataRepository.getRequiredFieldsNews())
                .goToPreViewPage();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(preViewPage.isPublishButtonPresent(), "Publish button is not present");
        System.out.println(preViewPage.getDateFieldText());
        softAssert.assertTrue(preViewPage.getDateFieldText()
                .equals(DateUtil.getCurrentDate("MMM dd, yyyy")),
                "Data is not match with today or may be has incorrect format");
        softAssert.assertAll();
    }

    /**
     * @ID=403-1303
     */
    @Test
    public void fillCreateNewsPreviewGoBackEcoNewsCreateCheckEmptyFields() {
        CreateNewsPage createNewsPage = loadCreateNewsPage()
                .fillFields(NewsDataRepository.getRequiredFieldsNews())
                .goToPreViewPage()
                .backToCreateNewsPage()
                .cancelNewsCreating()
                .gotoCreateNewsPage();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(createNewsPage.getTitleFieldText(), "");
        softAssert.assertEquals(createNewsPage.getSourceFieldText(), "");
        softAssert.assertEquals(createNewsPage.getContentFieldText(), "");
        softAssert.assertEquals(createNewsPage.getSelectedTagsNames().size(), 0);
        softAssert.assertAll();
    }

    /**
     * @ID=633-1387
     * @return
     */
    @Test
    public void checkPreviewOption() {
        NewsData newsData = NewsDataRepository.getRequiredFieldsNews();
        PreViewPage preViewPage = loadCreateNewsPage().fillFields(newsData).goToPreViewPage();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(newsData.getContent(), preViewPage.getContentFieldText());
        softAssert.assertEquals(newsData.getTitle(), preViewPage.getTitleFieldText());
        softAssert.assertAll();
    }

    public CreateNewsPage loadCreateNewsPage() {
        driver.navigate().to(createNewsUrl);
        return new CreateNewsPage(driver);
    }
}

