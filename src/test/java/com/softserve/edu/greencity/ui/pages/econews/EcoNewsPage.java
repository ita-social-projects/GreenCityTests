package com.softserve.edu.greencity.ui.pages.econews;

import com.softserve.edu.greencity.ui.data.Languages;
import com.softserve.edu.greencity.ui.data.econews.NewsData;
import com.softserve.edu.greencity.ui.data.econews.Tag;
import com.softserve.edu.greencity.ui.pages.common.TopPart;
import com.softserve.edu.greencity.ui.tools.QuantityItems;
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
 * @author lv-519 Taqc/Java
 */
@Getter
public class EcoNewsPage extends TopPart {

    protected WebDriverWait wait;
    SoftAssert softAssert = new SoftAssert();
    private ItemsContainer itemsContainer;
    private TagsComponent tagsComponent;
    private By createNewsButton = By.id("create-button");
    private By gridView = By.cssSelector("div.gallery-view");
    private By listView = By.cssSelector("div.list-view");
    private By foundItems = By.xpath("//*[@class='ng-star-inserted']");
    private By header = By.cssSelector("H1");
    private By tagsFilterBlock = By.cssSelector("app-filter-news");
    private By tagsFilterLabel = By.cssSelector("app-filter-news>div.wrapper>span");
    private By tags = By.cssSelector("app-filter-news>div.wrapper>ul>a");
    private By activeTags = By.cssSelector("app-filter-news>div.wrapper>ul>a>li.clicked-filter-button");
    private By uncheckTagButtons = By.cssSelector("app-filter-news>div.wrapper>ul>a>li>div.close");
    private By articleFoundCounter = By.cssSelector("app-remaining-count>p");
    private By displayedArticles = By.cssSelector(".gallery-view-li-active.ng-star-inserted");
    private By displayedArticlesTitles = By.xpath("//div[@class = 'title-list word-wrap']");
    private By articleImage = By.cssSelector(" div.list-image>img");
    private By articleEcoButton = By.cssSelector("div.filter-tag>div.ul-eco-buttons");
    private By articleTitle = By.cssSelector("div.added-data>div.title-list>p");
    private By articleText = By.cssSelector(" div.added-data>div.list-text>p");
    private By articleCreationDate = By.cssSelector("div.user-data-added-news>p:first-child");
    private By articleAuthorName = By.cssSelector("div.user-data-added-news>p:last-child");
    private By galleryViewButton = By.cssSelector("div.gallery-view");
    private By listViewButton = By.cssSelector("div.list-view");
    private By openTopicTags = By.cssSelector("div.tags>div.tags-item");
    private By newsTitle = By.cssSelector("div.news-title");
    private By newsInfoDate = By.cssSelector("div.news-info>div.news-info-date");
    private By newsInfoDot = By.cssSelector("div.news-info>div.news-info-dot");
    private By newsInfoAuthor = By.cssSelector("div.news-info>div.news-info-author");
    private By newsInfoImage = By.cssSelector("div>img.news-image-img");
    private By newsInfoSocicalLinksImg = By.cssSelector("div.news-links-images");
    private By newsInfoText = By.cssSelector("div.news-text-content");
    private By newsInfoSource = By.cssSelector("div.source-field");
    private int articleExistCount;
    private int articleDisplayedCount;

    public EcoNewsPage(WebDriver driver) {
        super(driver);
        //checkElements();
    }

    private void checkElements() {
        wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(getGridView()));
        wait.until(ExpectedConditions.visibilityOf(getListView()));
    }

    public List<WebElement> getTopicsInPage() {
        logger.info("Find all displayed topics");
        return searchElementsByXpath(foundItems);
    }

    private TagsComponent getTagsComponent() {
        return tagsComponent = new TagsComponent(driver);
    }

    //Header
    public WebElement getHeader() {
        return driver.findElement(header);
    }

    @Step("Get found items")
    private WebElement getFoundItems() {
        return searchElementByXpath(foundItems);
    }

    @Step("Get found items text")
    private String getFoundItemsText() {
        return getFoundItems().getText();
    }

    @Step("Get grid view")
    public WebElement getGridView() {
        return searchElementByCss(gridView);
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
        return searchElementByCss(listView);
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
            driver.findElements(listView);
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
        return driver.findElement(createNewsButton);
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
            driver.findElement(createNewsButton);
            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    @Step("Get items container")
    public ItemsContainer getItemsContainer() {
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
        scrollToElement(getTagsComponent().getTags().get(1));
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


    @Step
    public void isUiElementsDisplayedWithDifferentScreenResolution() {
        logger.info("Verify UI of the News page in Gallery view for different screen resolutions");
        softAssert.assertTrue(
                 searchElementByCss(header).isDisplayed() &&
                         //searchElementByCss(createNewsButton).isDisplayed() &&
                         searchElementByCss(tagsFilterBlock).isDisplayed() &&
                         searchElementByCss(articleFoundCounter).isDisplayed() &&
                         searchElementByCss(displayedArticles).isDisplayed(),
                "Assert that all UI elements in Eco News page is visible"
        );
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
        searchElementsByCss(displayedArticlesTitles);
        return driver.findElements(displayedArticlesTitles);
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
        searchElementByCss(header);
        logger.info("tagsFilterBlock");
        searchElementByCss(tagsFilterBlock);
        logger.info("tagsFilterLabel");
        searchElementByCss(tagsFilterLabel);
        logger.info("tags");
        searchElementByCss(tags);
        logger.info("articleFoundCounter");
        searchElementByCss(articleFoundCounter);
        logger.info("displayedArticles");
        searchElementByCss(displayedArticles);
        logger.info("listViewButton");
        searchElementByCss(listView);
        logger.info("galleryViewButton");
        searchElementByCss(gridView);
    }

    @Step("Get list of elements by css")
    public List<WebElement> getElements(By cssSelector) {
        logger.info("Get list of elements by css:\n " + cssSelector);
        return driver.findElements(cssSelector);
    }

    @Step
    public List<WebElement> getDisplayedArticles() {
        return searchElementsByCss(displayedArticles);
    }

    @Step("Set actual information from page to articleExistCount")
    public EcoNewsPage updateArticlesExistCount() {
        logger.info("refresh page");
        driver.navigate().refresh();
        logger.info("wait until at least one article is displayed");
        waiting(searchElementByCss(displayedArticles));
        logger.info("Set actual information from page to articleExistCount");
        articleExistCount = Integer.parseInt(searchElementByCss(articleFoundCounter).getText().split(" ")[0]);
        logger.info("Articles exist: " + articleExistCount);
        return this;
    }

    @Step("Scroll under end of page")
    public void scrollDown() {
        logger.info("scroll down");
        while (articleExistCount != articleDisplayedCount) {
            searchElementByCss(By.cssSelector("body")).sendKeys(Keys.CONTROL, Keys.END);
            articleExistCount = Integer.parseInt(searchElementByCss(articleFoundCounter).getText().split(" ")[0]);
            articleDisplayedCount = getElements(displayedArticles).size();
            logger.info("Articles displayed: " + articleDisplayedCount);
        }
        waiting(searchElementsByCss(displayedArticles));
    }

    @Step("Verification that all content in the chosen article displayed")
    private void isArticleContentDisplayed(WebElement element) {
        logger.info("<---------------------------------------------------------------->");
        logger.info("Verification that all content in the chosen article displayed");
        logger.info("assert all items displayed");
        logger.info("Title: " + element.findElement(articleTitle).getText());
        softAssert.assertTrue(
                searchElementByCss(articleImage).isDisplayed() &&
                        searchElementByCss(articleEcoButton).isDisplayed() &&
                        searchElementByCss(articleTitle).isDisplayed() &&
                        searchElementByCss(articleText).isDisplayed() &&
                        searchElementByCss(articleCreationDate).isDisplayed() &&
                        searchElementByCss(articleAuthorName).isDisplayed(),
                "Assert that all content is displayed in article");
        logger.info("assert text length < 170");


        softAssert.assertTrue(
                element.findElement(articleTitle).getText().trim()
                        .replace("\\s", "").length() < 171,
                "Assert that topic text length < 170. Title: " + element.findElement(articleTitle).getText());
    }

    @Step("Verification that all text content in the chosen article displayed")
    private void isArticleTextContentDisplayed(WebElement element) {
        logger.info("<---------------------------------------------------------------->");
        logger.info("Verification that all text content in the chosen article displayed");
        logger.info("assert all text  items displayed");
        logger.info("Text: " + element.findElement(articleText).getText());
        softAssert.assertTrue(
                searchElementByCss(articleTitle).isDisplayed() &&
                        searchElementByCss(articleText).isDisplayed() &&
                        searchElementByCss(articleCreationDate).isDisplayed() &&
                        searchElementByCss(articleAuthorName).isDisplayed()
                , "Assert that all text content is displayed in article");
        logger.info("assert text length < 200");
        softAssert.assertTrue(
                element.findElement(articleText).getText().trim()
                        .replace("\\s", "").length() < 201,
                "Assert that text length < 200. Title: " + element.findElement(articleText).getText());
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
        String date = element.findElement(articleCreationDate)
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
        logger.info("get random topic: " + topicNumber + " css: \n" + searchElementsByCss(displayedArticles).get(topicNumber).getCssValue("class"));
        return searchElementsByCss(displayedArticles).get(topicNumber);
    }

    public int getCreationDateLength(WebElement element) {
        return element.findElement(articleCreationDate).getSize().getWidth();
    }

    public int getCreationAuthorNameLength(WebElement element) {
        return element.findElement(articleAuthorName).getSize().getWidth();
    }

    public WebElement getopenTopicTags() {
        return searchElementByCss(openTopicTags);
    }

    public WebElement getnewsTitle() {
        return searchElementByCss(newsTitle);
    }

    public WebElement getnewsInfoDate() {
        return searchElementByCss(newsInfoDate);
    }

    public WebElement getnewsInfoDot() {
        return searchElementByCss(newsInfoDot);
    }

    public WebElement getnewsInfoAuthor() {
        return searchElementByCss(newsInfoAuthor);
    }

    public WebElement getnewsInfoImage() {
        return searchElementByCss(newsInfoImage);
    }

    public WebElement getnewsInfoSocicalLinksImg() {
        return searchElementByCss(newsInfoSocicalLinksImg);
    }

    public WebElement getnewsInfoText() {
        return searchElementByCss(newsInfoText);
    }

    public WebElement getnewsInfoSource() {
        return searchElementByCss(newsInfoSource);
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
