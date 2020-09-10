package com.softserve.edu.greencity.ui.tests;

import com.softserve.edu.greencity.ui.data.User;
import com.softserve.edu.greencity.ui.data.UserRepository;
import com.softserve.edu.greencity.ui.data.econews.NewsData;
import com.softserve.edu.greencity.ui.data.econews.NewsDataRepository;
import com.softserve.edu.greencity.ui.data.econews.Tag;
import com.softserve.edu.greencity.ui.pages.econews.CreateNewsPage;
import com.softserve.edu.greencity.ui.pages.econews.EcoNewsPage;
import com.softserve.edu.greencity.ui.pages.econews.TagsComponent;
import com.softserve.edu.greencity.ui.pages.tipstricks.TipsTricksPage;
import com.softserve.edu.greencity.ui.tests.runner.GreenCityTestRunner;
import com.softserve.edu.greencity.ui.tools.DateUtil;
import io.qameta.allure.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class CreateNewsPositiveTest extends GreenCityTestRunner {

    @BeforeMethod
    public void logoutBeforeTest() {
        if (isLogInNow()) {
            driver.findElement(By.cssSelector("a[href='#/welcome'")).click();
            new TipsTricksPage(driver).signOut();
        }
    }

    /**
     * @ID=GC-595
     */
    @Test
    @Description("Verify that user is on create news form")
    public void checkThatUserOnCreateNewsForm() {
        User user = UserRepository.get().temporary();
        loadApplication()
                .loginIn(user)
                .navigateMenuEcoNews()
                .gotoCreateNewsPage();

        WebElement createNewsMainTitle = driver.findElement(By.cssSelector(".title h2"));
        int numberOfButtons = driver.findElements(By.cssSelector(".submit-buttons button")).size();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(createNewsMainTitle.getText(), "Create news");
        softAssert.assertTrue(numberOfButtons == 3);
        loadApplication().signOut();
        softAssert.assertAll();
    }

    /**
     * @ID=GC-591
     */
    @Test
    @Description("Verify that create news button is visible for registered user")
    public void checkVisibilityOfCreateNewsButtonForRegisteredUser() {
        User user = UserRepository.get().temporary();

        EcoNewsPage econewsPage = loadApplication()
                .loginIn(user)
                .navigateMenuEcoNews();

        Assert.assertTrue(driver.findElements(By.cssSelector("#create-button")).size() == 1);
        econewsPage.signOut();
    }

    /**
     * ID GC-593
     */
    @Test
    @Description("Verify that create news button is invisible for unregistered user")
    public void checkInvisibilityOfCreateNewsButtonForGuest() {
        loadApplication()
                .navigateMenuEcoNews();
        Assert.assertTrue(driver.findElements(By.cssSelector("#create-button")).size() == 0);
    }

    /**
     * @ID=GC-623
     */
    @Test
    @Description("Verify possibility of choosing tags")
    public void verifySelectAndDeselectPossibilityOfTags() {
        User user = UserRepository.get().temporary();

        CreateNewsPage createNewsPage = loadApplication()
                .loginIn(user)
                .navigateMenuEcoNews()
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
        createNewsPage.signOut();
        softAssert.assertAll();
    }

    /**
     * @ID=GC-592
     */
    @Test
    @Description("Verify that news would not be created if content is too short")
    public void verifyImpossibilityOfCreatingNewsWithTooShortContent() {
        User user = UserRepository.get().temporary();

        CreateNewsPage createNewsPage = loadApplication()
                .loginIn(user)
                .navigateMenuEcoNews()
                .gotoCreateNewsPage()
                .fillFields(NewsDataRepository.getRequiredFieldsNews());
        createNewsPage.clearTitleField();
        createNewsPage.clearContentField();
        createNewsPage.setContentField("oops");
        boolean isDisabled = driver.findElement(By.cssSelector(".submit-buttons button+button+button")).isEnabled();
        Assert.assertFalse(isDisabled);
        createNewsPage.signOut();
    }

    /**
     * @ID=GC-645
     */
    @Test
    @Description("Verify that user can`t create news with incorrect URL")
    public void verifyImpossibilityOfCreatingTestWithIncorrectUrlInSourceField() {
        User user = UserRepository.get().temporary();

        CreateNewsPage createNewsPage = loadApplication()
                .loginIn(user)
                .navigateMenuEcoNews()
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
        createNewsPage.signOut();
    }

    /**
     * @ID=GC-637
     */
    @Test
    @Description("Verify that user can`t create news with empty fields")
    public void verifyImpossibilityOfCreatingNewsWithEmptyFields() {
        User user = UserRepository.get().temporary();

        CreateNewsPage createNewsPage = loadApplication()
                .loginIn(user)
                .navigateMenuEcoNews()
                .gotoCreateNewsPage();
        boolean isDisabled = driver.findElement(By.cssSelector(".submit-buttons button+button+button")).isEnabled();
        Assert.assertFalse(isDisabled);
        createNewsPage.signOut();
    }

    /**
     * @ID=GC-642
     */
    @Test
    @Description("Verify that user can`t create news without tags")
    public void verifyImpossibilityOfCreatingNewsWithoutAnyTags() {
        User user = UserRepository.get().temporary();

        CreateNewsPage createNewsPage = loadApplication()
                .loginIn(user)
                .navigateMenuEcoNews()
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
        createNewsPage.signOut();
    }

    /**
     * @ID=GC-644
     */
    //TODO fix commented part
    @Test
    @Description("Verify that user can`t create news with empty title")
    public void verifyImpossibilityCreateNewsWithEmptyTitle() {
        User user = UserRepository.get().temporary();

        CreateNewsPage createNewsPage = loadApplication()
                .loginIn(user)
                .navigateMenuEcoNews()
                .gotoCreateNewsPage();
        createNewsPage.setContentField("March 4 – 7, 2020, International Exhibition Center," +
                " Kyiv, 15 Brovarsky Ave.," +
                " takes place the most important event for professionals and funs of natural food and healthy life");
        createNewsPage.getTagsComponent().selectTag(Tag.NEWS);
        createNewsPage.clearTitleField();
        SoftAssert softAssert = new SoftAssert();
        boolean isDisabled = driver.findElement(By.cssSelector(".submit-buttons button+button+button")).isEnabled();
        softAssert.assertFalse(isDisabled);
        //softAssert.assertTrue(driver.findElement(By.cssSelector(".left-form-column label span")).getText().equals("Should contain maximum 170 symbols"));
        softAssert.assertAll();
        createNewsPage.signOut();
    }







    //Tests with SQL queries not yet implemented








    /**
     * @ID=GC-397 1. In description query example to Data Base has little mistake with "*" character
     */
    // @Test
    public void checkImpossibleToCreateNewsWithoutFillingMandatoryFields() throws SQLException {
        CreateNewsPage createNewsPage = loadApplication()
                .loginIn(getSpecialUser("EmailFor_green.city.test2@gmail.com", "PassFor_green.city.test2@gmail.com"))
                .navigateMenuEcoNews()
                .gotoCreateNewsPage();
        createNewsPage.setTitleField("Be eco! Be cool!");
        createNewsPage.getTagsComponent().selectTag(Tag.NEWS);
        createNewsPage.setContentField("It's so healthy, fun and cool to bring eco habits in everyday life");
        createNewsPage.clickPublishButton();

        Connection connection = connectToJDBC();
        ResultSet findNews = connection
                .createStatement()
                .executeQuery("SELECT * FROM eco_news WHERE title = 'Be eco! Be cool!'");
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(findNews.next());
        int id = findNews.getInt("id");
        connection.prepareStatement("DELETE FROM public.eco_news_tags * WHERE eco_news_id = " + id).execute();
        connection.prepareStatement("DELETE FROM public.eco_news * WHERE id = " + id).execute();
        softAssert.assertFalse(connection.createStatement().executeQuery("SELECT * FROM eco_news WHERE title = 'Be eco! Be cool!'").next());
        createNewsPage.signOut();
        softAssert.assertAll();
    }

    /**
     * @ID=GC-401 1. Second example string has 171 characters but should 170
     * 2. "'" character in "it's" dangerous because of query to Data Base
     * 3. In description query example to Data Base has little mistake with "*" character
     */
    //  @Test(dataProvider = "getStringForTitle")
    public void fillTitleFieldFromMinToMax(String title) throws SQLException, InterruptedException {
        CreateNewsPage createNewsPage = loadApplication()
                .loginIn(getSpecialUser("EmailFor_green.city.test2@gmail.com", "PassFor_green.city.test2@gmail.com"))
                .navigateMenuEcoNews()
                .gotoCreateNewsPage()
                .fillFields(NewsDataRepository.getRequiredFieldsNews());
        createNewsPage.clearTitleField();
        createNewsPage.setTitleField(title);
        createNewsPage.publishNews().signOut();
        cleanDataBase(title);
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
     * @ID=GC-628
     */
    // @Test(dataProvider = "getTagsList")
    public void checkCreateNewsWithOneToThreeTags(List<Tag> tags) throws SQLException, InterruptedException {
        CreateNewsPage createNewsPage = loadApplication()
                .loginIn(getSpecialUser("EmailFor_green.city.test2@gmail.com", "PassFor_green.city.test2@gmail.com"))
                .navigateMenuEcoNews()
                .gotoCreateNewsPage();
        createNewsPage.clearTitleField();
        String title = "XVI International specialized exhibition of ecologic products for the daily life";
        createNewsPage.setTitleField(title);
        createNewsPage.clearContentField();
        createNewsPage.setContentField("March 4 – 7, 2020, International Exhibition Center, Kyiv, 15 Brovarsky Ave.," +
                " takes place the most important event for professionals and funs of natural food and healthy life");
        createNewsPage.getTagsComponent().selectTags(tags);
        EcoNewsPage econewsPage = createNewsPage.publishNews();
        cleanDataBase(title);
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
        econewsPage.signOut();
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

    /**
     * @ID=GC-588 1. Change expected title to "Download PNG or JPG only. File size should be less than 10MB"
     */
    // @Test
    public void verifyImpossibilityOfUploadingTooLargeImage() {
        CreateNewsPage createNewsPage = loadApplication()
                .loginIn(getSpecialUser("EmailFor_green.city.test2@gmail.com", "PassFor_green.city.test2@gmail.com"))
                .navigateMenuEcoNews()
                .gotoCreateNewsPage()
                .fillFields(NewsDataRepository.getRequiredFieldsNews());
        createNewsPage.uploadFile(createNewsPage.getDropArea(), "src/test/resources/invalid.gif");
        String warning = driver.findElement(By.cssSelector(".dropzone+.warning")).getText();
        Assert.assertEquals(warning, "Download PNG or JPG only. File size should be less than 10MB");
        createNewsPage.signOut();
    }

    /**
     * @ID=GC-643
     */
    // @Test
    public void verifyPossibilityOfMaxThreeTagsWhenCreateNews() throws SQLException, InterruptedException {
        CreateNewsPage createNewsPage = loadApplication()
                .loginIn(getSpecialUser("EmailFor_green.city.test2@gmail.com", "PassFor_green.city.test2@gmail.com"))
                .navigateMenuEcoNews()
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
        EcoNewsPage econewsPage = createNewsPage.publishNews();
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
        econewsPage.signOut();
        cleanDataBase(title);
    }

    /**
     * @ID=GC-654
     */
    // @Test
    public void verifyImpossibilityToSelectOneTagTwice() throws SQLException, InterruptedException {
        CreateNewsPage createNewsPage = loadApplication()
                .loginIn(getSpecialUser("EmailFor_green.city.test2@gmail.com", "PassFor_green.city.test2@gmail.com"))
                .navigateMenuEcoNews()
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
        EcoNewsPage econewsPage = createNewsPage.publishNews();
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
        econewsPage.signOut();
        cleanDataBase(title);
    }

    /**
     * @ID=GC-613
     */
    // @Test
    public void creatNewsWithSourceField() throws SQLException, InterruptedException {
        CreateNewsPage createNewsPage = loadApplication()
                .loginIn(getSpecialUser("EmailFor_green.city.test2@gmail.com", "PassFor_green.city.test2@gmail.com"))
                .navigateMenuEcoNews()
                .gotoCreateNewsPage();
        NewsData newsData = NewsDataRepository.getRequiredFieldsNews();
        createNewsPage.fillFields(newsData);
        createNewsPage.clearTitleField();
        String title = "simple test very similary for previous";
        createNewsPage.setTitleField(title);
        createNewsPage.setSourceField("https://google.com");
        EcoNewsPage econewsPage = createNewsPage.publishNews();
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
        econewsPage.signOut();
        cleanDataBase(title);
    }

    /**
     * @ID=GC-616
     */
    // @Test
    public void createNewsWithContentLengthMoreThen20() throws SQLException, InterruptedException {
        CreateNewsPage createNewsPage = loadApplication()
                .loginIn(getSpecialUser("EmailFor_green.city.test2@gmail.com", "PassFor_green.city.test2@gmail.com"))
                .navigateMenuEcoNews()
                .gotoCreateNewsPage();
        NewsData newsData = NewsDataRepository.getRequiredFieldsNews();
        createNewsPage.fillFields(newsData);
        createNewsPage.clearContentField();
        createNewsPage.setContentField("12345678901234567890+1");
        createNewsPage.clearTitleField();
        String title = "very simple test like previous. verify contentType filed with more than 20 characters";
        createNewsPage.setTitleField(title);
        createNewsPage.publishNews().signOut();
        cleanDataBase(title);
    }

    /**
     * @ID=GC-617
     */
    // @Test
    public void verifingAutoFillingDataWhenCreateNews() throws InterruptedException, SQLException {
        CreateNewsPage createNewsPage = loadApplication()
                .loginIn(getSpecialUser("EmailFor_green.city.test2@gmail.com", "PassFor_green.city.test2@gmail.com"))
                .navigateMenuEcoNews()
                .gotoCreateNewsPage();
        NewsData newsData = NewsDataRepository.getRequiredFieldsNews();
        createNewsPage.fillFields(newsData);
        EcoNewsPage econewsPage = createNewsPage.publishNews();
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
        new TipsTricksPage(driver).signOut();
        cleanDataBase(newsData.getTitle());
    }


    /**
     * Additional methods, tools, helpers for these tests
     * ===========================================================================================================================================================
     */
    public String getCredential(String key) {
        Properties properties = new Properties();
        try {
            properties
                    .load(new BufferedReader(new FileReader("src/test/resources/credentials.properties")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties.getProperty(key);
    }

    public User getSpecialUser(String emailKey, String passKey) {
        String email = getCredential(emailKey);
        String pass = getCredential(passKey);
        return new User(email, pass);
    }

    public Connection connectToJDBC() {
        try {

            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Where is your PostgreSQL JDBC Driver? "
                    + "Include in your library path!");
            e.printStackTrace();
            return null;
        }
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(
                    getCredential("JDBC_url"),
                    getCredential("JDBC_user"),
                    getCredential("JDBC_password"));
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
            return null;
        }
        if (connection != null) {
            System.out.println("You made it, take control your database now!");
        } else {
            System.out.println("Failed to make connection!");
        }
        return connection;
    }

    public void cleanDataBase(String title) throws SQLException, InterruptedException {
        cleanDataBase(title, false);
    }

    public void cleanDataBase(String title, boolean waiting) throws SQLException, InterruptedException {
        if (waiting) {
            Thread.sleep(15000);
        }
        Connection connection = connectToJDBC();

        String titleInDataBase = title;
        ResultSet queryResponse = connection
                .createStatement()
                .executeQuery("SELECT * FROM public.eco_news WHERE title = '" + titleInDataBase + "'");
        Assert.assertTrue(queryResponse.next());
        int id = queryResponse.getInt("id");
        connection
                .prepareStatement("DELETE FROM public.eco_news_tags * WHERE eco_news_id = " + id)
                .execute();
        connection
                .prepareStatement("DELETE FROM public.eco_news * WHERE id = " + id)
                .execute();
    }

}
