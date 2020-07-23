package com.softserve.edu.greencity.ui.tests.createnews;

import com.softserve.edu.greencity.ui.data.UserRepository;
import com.softserve.edu.greencity.ui.data.econews.NewsData;
import com.softserve.edu.greencity.ui.data.econews.NewsDataRepository;
import com.softserve.edu.greencity.ui.data.econews.Tag;
import com.softserve.edu.greencity.ui.pages.econews.CreateNewsPage;
import com.softserve.edu.greencity.ui.pages.econews.EconewsPage;
import com.softserve.edu.greencity.ui.tests.GreenCityTestRunner;
import com.softserve.edu.greencity.ui.tools.DBQueries;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.List;

public class CNTests extends GreenCityTestRunner {
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
     * @ID=GC-401 1. Second example string has 171 characters but should 170
     * 2. "'" character in "it's" dangerous because of query to Data Base
     * 3. In description query example to Data Base has little mistake with "*" character
     */

    @Test(dataProvider = "getStringForTitle")
    public void fillTitleFieldFromMinToMax(String title) {
        CreateNewsPage createNewsPage = loadCreateNewsPage()
                .fillFields(NewsDataRepository.getRequiredFieldsNews());
        createNewsPage.clearTitleField();
        createNewsPage.setTitleField(title);
        createNewsPage.publishNews();
        dataBase.deleteNewsByTitle(title);
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

    /**
     * @ID=GC-613
     */
    @Test
    public void createNewsWithSourceField() {
        CreateNewsPage createNewsPage = loadCreateNewsPage();
        NewsData newsData = NewsDataRepository.getRequiredFieldsNews();
        createNewsPage.fillFields(newsData);
        createNewsPage.clearTitleField();
        String title = "simple test very similary for previous";
        createNewsPage.setTitleField(title);
        createNewsPage.setSourceField("https://google.com");
        EconewsPage econewsPage = createNewsPage.publishNews();
        List<WebElement> elements = driver.findElements(By.cssSelector("div.list-gallery-content"));
        boolean isPresent = false;
        for (WebElement e : elements) {
            if (e.findElement(By.cssSelector(".title-list p")).getText().equals(title)) {
                isPresent = true;
                int tagsCount = e.findElements(By.cssSelector(".filter-tag div")).size();
                Assert.assertTrue(tagsCount == newsData.getTags().size());
                break;
            }
            Assert.assertTrue(isPresent);
        }
        dataBase.deleteNewsByTitle(title);
    }

    /**
     * @ID=GC-616
     */
    @Test
    public void createNewsWithContentLengthMoreThen20() {
        CreateNewsPage createNewsPage = loadCreateNewsPage();
        NewsData newsData = NewsDataRepository.getRequiredFieldsNews();
        createNewsPage.fillFields(newsData);
        createNewsPage.clearContentField();
        createNewsPage.setContentField("12345678901234567890+1");
        createNewsPage.clearTitleField();
        String title = "very simple test like previous. verify contentType filed with more than 20 characters";
        createNewsPage.setTitleField(title);
        createNewsPage.publishNews();
        dataBase.deleteNewsByTitle(title);
    }

    /**
     * @ID=GC-628
     */
    @Test(dataProvider = "getTagsList")
    public void checkCreateNewsWithOneToThreeTags(List<Tag> tags) {
        CreateNewsPage createNewsPage = loadCreateNewsPage();
        createNewsPage.clearTitleField();
        String title = "XVI International specialized exhibition of ecologic products for the daily life";
        createNewsPage.setTitleField(title);
        createNewsPage.clearContentField();
        createNewsPage.setContentField("March 4 – 7, 2020, International Exhibition Center, Kyiv, 15 Brovarsky Ave.," +
                " takes place the most important event for professionals and funs of natural food and healthy life");
        createNewsPage.getTagsComponent().selectTags(tags);
        EconewsPage econewsPage = createNewsPage.publishNews();
        dataBase.deleteNewsByTitle(title);
        int news = econewsPage.getNumberOfItemComponent();
        System.out.println(news);
        List<WebElement> elements = driver.findElements(By.cssSelector("div.list-gallery-content"));
        for (WebElement e : elements) {
            if (e.findElement(By.cssSelector(".title-list p")).getText().equals(title)) {
                List<WebElement> findingTags = e.findElements(By.cssSelector(".filter-tag div"));
                for (WebElement e2 : findingTags) {
                    boolean assertTags = false;
                    for (Tag tag : tags) {
                        if (tag.name().equalsIgnoreCase(e2.getText())) {
                            assertTags = true;
                            break;
                        }
                    }
                    Assert.assertTrue(assertTags);
                }
                break;
            }
        }
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

    public CreateNewsPage loadCreateNewsPage() {
        driver.navigate().to(createNewsUrl);
        return new CreateNewsPage(driver);
    }

}
