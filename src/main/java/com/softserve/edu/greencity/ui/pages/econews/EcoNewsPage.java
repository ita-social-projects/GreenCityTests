package com.softserve.edu.greencity.ui.pages.econews;

import com.softserve.edu.greencity.ui.data.Languages;
import com.softserve.edu.greencity.ui.data.econews.NewsData;
import com.softserve.edu.greencity.ui.data.econews.Tag;
import com.softserve.edu.greencity.ui.locators.EcoNewsPageLocator;
import com.softserve.edu.greencity.ui.pages.common.TopPart;
import com.softserve.edu.greencity.ui.tools.QuantityItems;
import static com.softserve.edu.greencity.ui.locators.EcoNewsPageLocator.*;

import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The list of all eco news: https://ita-social-projects.github.io/GreenCityClient/#/news
 * @author lv-519 Taqc/Java
 */
@Getter
public class EcoNewsPage extends TopPart {

    protected WebDriverWait wait;
    SoftAssert softAssert = new SoftAssert();
    private ItemsContainer itemsContainer;
    private TagsComponent tagsComponent;


    private int articleExistCount;
    private int articleDisplayedCount;

    public EcoNewsPage(WebDriver driver) {
        super(driver);
        //checkElements();
    }

    private void checkElements() {
        waitsSwitcher.setExplicitWait(10, ExpectedConditions.presenceOfAllElementsLocatedBy(DISPLAYED_ARTICLES.getPath()));
        waitsSwitcher.setExplicitWait(10, ExpectedConditions.visibilityOf(getGridView()));
        waitsSwitcher.setExplicitWait(10, ExpectedConditions.visibilityOf(getListView()));
    }

    public List<WebElement> getTopicsInPage() {
        logger.info("Find all displayed topics");
        return searchElementsByXpath(FOUND_ITEMS.getPath());
    }

    private TagsComponent getTagsComponent() {
        return tagsComponent = new TagsComponent(driver);
    }

    //Header
    public WebElement getHeader() {
        return driver.findElement(HEADER.getPath());
    }

    @Step("Get found items")
    private WebElement getFoundItems() {
        return searchElementByXpath(FOUND_ITEMS.getPath());
    }

    @Step("Get found items text")
    private String getFoundItemsText() {
        return getFoundItems().getText();
    }

    @Step("Get grid view")
    public WebElement getGridView() {
        return searchElementByCss(GALLERY_VIEW_BUTTON.getPath());
    }

    @Step("Check if grid view is active")
    public boolean isActiveGridView() {
        return getGridView().getAttribute("class").contains("active");
    }

    @Step("Check if grid view is displayed")
    public boolean isGridViewDisplayed() {
        return getGridView().isDisplayed();
    }

    @Step("Click on grid view")
    private void clickGridView() {
        if (!isActiveGridView()) {
            scrollToElement(getGridView());
            getGridView().click();
        }
    }

    @Step("Hover to grid view")
    public EcoNewsPage hoverToGridView() {
        Actions action = new Actions(driver);
        action.moveToElement(getGridView()).perform();
        return this;
    }

    @Step("Get list view")
    public WebElement getListView() {
        return searchElementByCss(LIST_VIEW_BUTTON.getPath());
    }

    @Step("Check if list view is displayed")
    public boolean isDisplayedListView() {
        return getListView().isDisplayed();
    }

    @Step("Hover to list view")
    public EcoNewsPage hoverToListView() {
        Actions action = new Actions(driver);
        action.moveToElement(getListView()).perform();
        return this;
    }

    @Step("Check if list view is present")
    public boolean isListViewPresent() {
        try {
            driver.findElements(LIST_VIEW_BUTTON.getPath());
            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    @Step("Check if list view is active")
    public boolean isActiveListView() {
        return getListView().getAttribute("class").contains("active");
    }

    @Step("Click on list view")
    private void clickListView() {
        if (!isActiveListView()) {
            scrollToElement(getListView());
            getListView().click();
        }
    }

    @Step("Get create news button")
    private WebElement getCreateNewsButton() {
        return driver.findElement(CREATE_NEWS_BUTTON.getPath());
    }

    @Step("Get create news button text")
    private String getCreateNewsButtonText() {
        return getCreateNewsButton().getText();
    }

    @Step("Click on create news button")
    private void clickCreateNewsButton() {
        getCreateNewsButton().click();
    }

    @Step("Check if create news button is displayed")
    public boolean isCreateNewsButtonDisplayed() {
        return getCreateNewsButton().isDisplayed();
    }

    @Step("Check if create news button is present")
    public boolean isCreateNewsButtonPresent() {
        try {
            driver.findElement(CREATE_NEWS_BUTTON.getPath());
            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    @Step("Get items container")
    public ItemsContainer getItemsContainer() {
        // TODO add here some waiter for uploading news
        try {
            Thread.sleep(800);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return itemsContainer = new ItemsContainer(driver);
    }

    /**
     * Scroll to WebElement, in case when need to click on it or without scrolling are invisible
     *
     * @param element
     */
    @Step("Scroll to element")
    private void scrollToElement(WebElement element) {
        Actions action = new Actions(driver);
        action.moveToElement(element).perform();
    }

    /**
     * Get number of ItemComponent, what are present on EcoNewsPage
     *
     * @return int
     */
    @Step("Get number of item component")
    public int getNumberOfItemComponent() {
        logger.info("Get number of items component");
        return new QuantityItems().quantityItems(getFoundItemsText());
    }

    /**
     * Method allows to choose type of news, which will be displayed on the EcoNewsPage
     *
     * @param tags
     * @return EcoNewsPage
     */
    @Step("Select filters")
    public EcoNewsPage selectFilters(List<Tag> tags) {
        logger.info("Select filters");
        //scrollToElement(getTagsComponent().getTags().get(1));
        getTagsComponent().selectTags(tags);

        return new EcoNewsPage(driver);
    }

    /**
     * Method allows to choose type of news, which will be displayed on the EcoNewsPage
     *
     * @param tags
     * @return EcoNewsPage
     */
    @Step("Deselect filters")
    public EcoNewsPage deselectFilters(List<Tag> tags) {
        logger.info("Deselect filters");
        scrollToElement(getTagsComponent().getTags().get(1));
        getTagsComponent().deselectTags(tags);
        return new EcoNewsPage(driver);
    }

    /**
     * Choose language
     *
     * @param language
     * @return EcoNewsPage
     */
    @Step("Switch language")
    public EcoNewsPage switchLanguage(Languages language) {
        chooseLanguage(language);
        return new EcoNewsPage(driver);
    }

    /**
     * News are displayed as grid
     *
     * @return EcoNewsPage
     */
    @Step("Switch to grid view")
    public EcoNewsPage switchToGridView() {
        clickGridView();
        return new EcoNewsPage(driver);
    }

    /**
     * News are displayed as list
     *
     * @return EcoNewsPage
     */
    @Step("Switch to list view")
    public EcoNewsPage switchToListView() {
        clickListView();
        return new EcoNewsPage(driver);
    }

    /**
     * Open SingleNewsPage
     *
     * @param number
     * @return SingleNewsPage
     */
    @Step("Switch to single news page by number")
    public SingleNewsPage switchToSingleNewsPageByNumber(int number) {
        logger.info("Switch to single news by number");
        itemsContainer = getItemsContainer();
        scrollToElement(itemsContainer.chooseNewsByNumber(number).getTitle());
        itemsContainer.chooseNewsByNumber(number).clickTitle();
        return new SingleNewsPage(driver);
    }

    /**
     * Open SingleNewsPage
     *
     * @param news
     * @return SingleNewsPage
     */
    @Step("Switch to single news page by parameters")
    public SingleNewsPage switchToSingleNewsPageByParameters(NewsData news) {
        logger.info("Switch to single news page by parameters");
        scrollToElement(itemsContainer.findItemComponentByParameters(news).getTitle());
        itemsContainer.clickItemComponentOpenPage(news);
        return new SingleNewsPage(driver);
    }

    /**
     * @return number of columns in gallery view depending on screen width (max = 3, min = 1)
     */
    @Step("Count number of Grid Columns")
    public int countNewsColumns() {
        logger.info("Count number of news columns in grid view");
        List<WebElement> elements = getDisplayedArticles();
        int count = 0;
        if (elements.get(0).getLocation().y == elements.get(1).getLocation().y) {
            if (elements.get(1).getLocation().y == elements.get(2).getLocation().y) {
                logger.info("3 columns");
                count = 3;
            }
            if (elements.get(1).getLocation().y < elements.get(2).getLocation().y) {
                logger.info("2 columns");
                count = 2;
            }
        }
        if (elements.get(0).getLocation().y < elements.get(1).getLocation().y) {
            logger.info("1 column");
            count = 1;
        }
        return count;
    }

    /**
     * Verify UI of the News page in Gallery view for different screen resolutions
     */
    @Step
    public void isUiElementsDisplayedWithDifferentScreenResolution() {
        logger.info("Verify UI of the News page in Gallery view for different screen resolutions");
        softAssert.assertTrue(
                 searchElementByCss(HEADER.getPath()).isDisplayed() &&
                         //searchElementByCss(createNewsButton).isDisplayed() &&
                         searchElementByCss(TAGS_FILTER_BLOCK.getPath()).isDisplayed() &&
                         searchElementByCss(ARTICLES_FOUND_COUNTER.getPath()).isDisplayed() &&
                         searchElementByCss(DISPLAYED_ARTICLES.getPath()).isDisplayed(),
                "Assert that all UI elements in Eco News page is visible"
        );
    }

    @Step
    public void verifyContentItemsUI() {
        List<WebElement> article = getDisplayedArticles();
        int articleLeftCorner = article.get(0).getSize().height;
        System.out.println("height = " + articleLeftCorner);
    }

    /**
     * Open CreateNewsPage
     *
     * @return CreateNewsPage
     */
    @Step("Go to create news page")
    public CreateNewsPage gotoCreateNewsPage() {
        logger.info("Go to create news page");
        scrollToElement(getCreateNewsButton());
        clickCreateNewsButton();
        return new CreateNewsPage(driver);
    }

    @Step("Check if news is displayed by title")
    public boolean isNewsDisplayedByTitle(String title) {
        logger.info("Check if news is displayed by title");
        refreshPage();
        boolean result = false;
        for (WebElement current : getDisplayedArticlesTitles()) {
            if (current.getText().toLowerCase().trim().equals(title.trim().toLowerCase())) {
                result = true;
            }
        }
        return result;
    }

    @Step("Get displayed articles titles")
    public List<WebElement> getDisplayedArticlesTitles() {
        searchElementsByCss(DISPLAYED_ARTICLES_TITLES.getPath());
        return driver.findElements(DISPLAYED_ARTICLES_TITLES.getPath());
    }

    @Step("Refresh page")
    public EcoNewsPage refreshPage() {
        driver.navigate().refresh();
        checkElements();
        return this;
    }

    /*<======================================Grid View==========================================>*/
    // Functional
    @Step("Verification of page condition")
    public void pageExistQuickCheck() {
        logger.info("Is element visible: \n");
        logger.info("header");
        searchElementByCss(HEADER.getPath());
        logger.info("tagsFilterBlock");
        searchElementByCss(TAGS_FILTER_BLOCK.getPath());
        logger.info("tagsFilterLabel");
        searchElementByCss(TAGS_FILTER_LABEL.getPath());
        logger.info("tags");
        searchElementByCss(TAGS.getPath());
        logger.info("articleFoundCounter");
        searchElementByCss(ARTICLES_FOUND_COUNTER.getPath());
        logger.info("displayedArticles");
        searchElementByCss(DISPLAYED_ARTICLES.getPath());
        logger.info("listViewButton");
        searchElementByCss(LIST_VIEW_BUTTON.getPath());
        logger.info("galleryViewButton");
        searchElementByCss(GALLERY_VIEW_BUTTON.getPath());
    }

    @Step("Get list of elements by css")
    public List<WebElement> getElements(By cssSelector) {
        logger.info("Get list of elements by css:\n " + cssSelector);
        return driver.findElements(cssSelector);
    }

    @Step
    public List<WebElement> getDisplayedArticles() {
        return searchElementsByCss(DISPLAYED_ARTICLES.getPath());
    }

    @Step("Set actual information from page to articleExistCount")
    public EcoNewsPage updateArticlesExistCount() {
        logger.info("refresh page");
        driver.navigate().refresh();
        logger.info("wait until at least one article is displayed");
        waiting(searchElementByCss(DISPLAYED_ARTICLES.getPath()));
        logger.info("Set actual information from page to articleExistCount");
        articleExistCount = Integer.parseInt(searchElementByCss(ARTICLES_FOUND_COUNTER.getPath()).getText().split(" ")[0]);
        logger.info("Articles exist: " + articleExistCount);
        return this;
    }

    @Step("Scroll under end of page")
    public EcoNewsPage scrollDown() {
        logger.info("scroll down");
        while (articleExistCount != articleDisplayedCount) {
            searchElementByCss(By.cssSelector("body")).sendKeys(Keys.CONTROL, Keys.END);
            articleExistCount = Integer.parseInt(searchElementByCss(ARTICLES_FOUND_COUNTER.getPath())
                    .getText().split(" ")[0]);
            articleDisplayedCount = getElements(DISPLAYED_ARTICLES.getPath()).size();
            logger.info("Articles displayed: " + articleDisplayedCount);
        }
        waiting(searchElementsByCss(DISPLAYED_ARTICLES.getPath()));
        return this;
    }

    @Step("Verification that all content in the chosen article displayed")
    private void isArticleContentDisplayed(WebElement element) {
        logger.info("<---------------------------------------------------------------->");
        logger.info("Verification that all content in the chosen article displayed");
        logger.info("assert all items displayed");
        logger.info("Title: " + element.findElement(ARTICLE_TITLE.getPath()).getText());
        softAssert.assertTrue(
                searchElementByCss(ARTICLE_IMAGE.getPath()).isDisplayed() &&
                        searchElementByCss(ARTICLE_ECO_BUTTON.getPath()).isDisplayed() &&
                        searchElementByCss(ARTICLE_TITLE.getPath()).isDisplayed() &&
                        searchElementByCss(ARTICLE_TEXT.getPath()).isDisplayed() &&
                        searchElementByCss(ARTICLE_CREATION_DATE.getPath()).isDisplayed() &&
                        searchElementByCss(ARTICLE_AUTHOR_NAME.getPath()).isDisplayed(),
                "Assert that all content is displayed in article");
        logger.info("assert text length < 170");


        softAssert.assertTrue(
                element.findElement(ARTICLE_TITLE.getPath()).getText().trim()
                        .replace("\\s", "").length() < 171,
                "Assert that topic text length < 170. Title: " +
                        element.findElement(ARTICLE_TITLE.getPath()).getText());
    }

    @Step("Verification that all text content in the chosen article displayed")
    private void isArticleTextContentDisplayed(WebElement element) {
        logger.info("<---------------------------------------------------------------->");
        logger.info("Verification that all text content in the chosen article displayed");
        logger.info("assert all text  items displayed");
        logger.info("Text: " + element.findElement(ARTICLE_TEXT.getPath()).getText());
        softAssert.assertTrue(
                searchElementByCss(ARTICLE_TITLE.getPath()).isDisplayed() &&
                        searchElementByCss(ARTICLE_TEXT.getPath()).isDisplayed() &&
                        searchElementByCss(ARTICLE_CREATION_DATE.getPath()).isDisplayed() &&
                        searchElementByCss(ARTICLE_AUTHOR_NAME.getPath()).isDisplayed()
                , "Assert that all text content is displayed in article");
        logger.info("assert text length < 200");
        softAssert.assertTrue(
                element.findElement(ARTICLE_TEXT.getPath()).getText().trim()
                        .replace("\\s", "").length() < 201,
                "Assert that text length < 200. Title: " +
                        element.findElement(ARTICLE_TEXT.getPath()).getText());
    }

    @Step("Verification that all content in the list of articles displayed")
    public void isArticleContentDisplayed(List<WebElement> elements) {
        logger.info("Verification that all content in the list of articles displayed");
        for (WebElement element : elements) {
            logger.info("element: " + element);
            isArticleContentDisplayed(element);
        }
        softAssert.assertAll();
    }

    @Step("Verification that all text content in the list of articles displayed")
    public void isArticleTextContentDisplayed(List<WebElement> elements) {
        logger.info("Verification that all text content in the list of articles displayed");

        for (WebElement element : elements) {
            logger.info("element: " + element);
            isArticleTextContentDisplayed(element);
        }
        softAssert.assertAll();
    }

    @Step("short explicit wait visibility Of element")
    private void waiting(WebElement element) {
        logger.info("short explicit wait visibility Of element \n" + String.valueOf(element));
        new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOf(element));
    }

    @Step("short explicit wait visibility Of elements list")
    private void waiting(List<WebElement> elements) {
        logger.info("short explicit wait visibility Of elements list \n" + String.valueOf(elements));
        new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfAllElements(elements));
    }

    @Step("Get creation date")
    public String getArticleCreationDate(WebElement element) {
        logger.info("Get creation date");
        String date = element.findElement(ARTICLE_CREATION_DATE.getPath())
                .getText().replace(",", "");
        String[] dateFlip = date.split(" ");
        String dbFormatDate = dateFlip[2] + "-" + dateFlip[0] + "-" + dateFlip[1];
        return dbFormatDate
                .replace("Jan", "01")
                .replace("Feb", "02")
                .replace("Mar", "03")
                .replace("Apr", "04")
                .replace("May", "05")
                .replace("Jun", "06")
                .replace("Jul", "07")
                .replace("Aug", "08")
                .replace("Sep", "09")
                .replace("Oct", "10")
                .replace("Nov", "11")
                .replace("Dec", "12")
                .replace("0", "");
    }

    public String formatChronologicalDateFromDB(String topic) {
        Pattern pattern = Pattern.compile("creation_date=[^\"]+");
        final Matcher m = pattern.matcher(topic);
        m.find();

        return m.group(0)
                .replace(",", "")
                .substring(0, 24)
                .replace("creation_date=", "")
                .replace("0", "");
    }

    @Step("get random topic")
    public WebElement getRandomTopic() {
        final int topicNumber = getRandom();
        logger.info("get random topic: " + topicNumber + " css: \n"
                + searchElementsByCss(DISPLAYED_ARTICLES.getPath()).get(topicNumber).getCssValue("class"));
        return searchElementsByCss(DISPLAYED_ARTICLES.getPath()).get(topicNumber);
    }

    public int getCreationDateLength(WebElement element) {
        return element.findElement(ARTICLE_CREATION_DATE.getPath()).getSize().getWidth();
    }

    public int getCreationAuthorNameLength(WebElement element) {
        return element.findElement(ARTICLE_AUTHOR_NAME.getPath()).getSize().getWidth();
    }

    public WebElement getopenTopicTags() {
        return searchElementByCss(OPEN_TOPICS_TAGS.getPath());
    }

    public WebElement getnewsTitle() {
        return searchElementByCss(NEWS_TITLE.getPath());
    }

    public WebElement getnewsInfoDate() {
        return searchElementByCss(NEWS_INFO_DATE.getPath());
    }

    public WebElement getnewsInfoDot() {
        return searchElementByCss(NEWS_INFO_DOT.getPath());
    }

    public WebElement getnewsInfoAuthor() {
        return searchElementByCss(NEWS_INFO_AUTHOR.getPath());
    }

    public WebElement getnewsInfoImage() {
        return searchElementByCss(NEWS_INFO_IMAGE.getPath());
    }

    public WebElement getnewsInfoSocialLinksImg() {
        return searchElementByCss(NEWS_INFO_SOCIAL_LINKS_IMG.getPath());
    }

    public WebElement getnewsInfoText() {
        return searchElementByCss(NEWS_INFO_TEXT.getPath());
    }

    public WebElement getnewsInfoSource() {
        return searchElementByCss(NEWS_INFO_SOURCE.getPath());
    }

    public int getRandom() {
        return (int) (Math.random() * ((20 - 1) - 10 + 1) + 1);
    }

    /*<======================================Grid View=========================================>*/
    @Override
    public WebDriver setDriver() {
        return this.driver;
    }
}
