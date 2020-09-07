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
public class EcoNewsPage extends TopPart  {

    protected WebDriverWait wait;

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
    private By displayedArticles = By.cssSelector("ul.list.gallery-view-active > li.gallery-view-li-active");
    private By articleImage = By.cssSelector(" div.list-image>img");
    private By articleEcoButton = By.cssSelector("div.filter-tag>div.ul-eco-buttons");
    private By articleTitle = By.cssSelector("div.added-data>div.title-list>p");
    private By articleText = By.cssSelector(" div.added-data>div.list-text>p");
    private By articleCreationDate = By.cssSelector("div.user-data-added-news>p:first-child");
    private By articleAuthorName = By.cssSelector("div.user-data-added-news>p:last-child");
    private By galleryViewButton = By.cssSelector("div.gallery-view");
    private By listViewButton = By.cssSelector("div.list-view");
    private int articleExistCount;
    private int articleDisplayedCount;

    public EcoNewsPage(WebDriver driver) {
        super(driver);
        checkElements();
//        visualiseElements();
    }

    private void checkElements(){
        wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(getGridView()));
        wait.until(ExpectedConditions.visibilityOf(getListView()));
    }
    public List<WebElement>  getTopicsInPage(){
        logger.info("Find all displayed topics");
        return searchElementsByXpath(foundItems);
    }

    private void visualiseElements() {
        int i = 0;
        waiting(2);
        scrollToElement(getCopyright()); //  open all news
        waiting(2);
        List<WebElement> listElements = driver.findElements(By.cssSelector("div[id='list-gallery-content']"));
        while (i < listElements.size()) {
            waiting(2);
            scrollToElement(listElements.get(i));
            i++;
            listElements = driver.findElements(By.cssSelector("div[id='list-gallery-content']"));
        }
    }

    private TagsComponent getTagsComponent() {
        return tagsComponent = new TagsComponent(driver);
    }

    private WebElement getFoundItems() {
        return searchElementByXpath(foundItems);
    }

    private String getFoundItemsText() {
        return getFoundItems().getText();
    }

    public WebElement getGridView() {
        return searchElementByCss(gridView);
    }

    public boolean isActiveGridView() {
        return getGridView().getAttribute("class").contains("active");
    }

    private void clickGridView() {
        if (!isActiveGridView()) {
            scrollToElement(getGridView());
            getGridView().click();
        }
    }

    private WebElement getListView() {
        return searchElementByCss(listView);
    }

    public boolean isActiveListView() {
        return getListView().getAttribute("class").contains("active");
    }

    private void clickListView() {
        if (!isActiveListView()) {
            scrollToElement(getListView());
            getListView().click();
        }
    }

    private WebElement getCreateNewsButton() {
        return driver.findElement(createNewsButton);
    }

    private String getCreateNewsButtonText() {
        return getCreateNewsButton().getText();
    }

    private void clickCreateNewsButton() {
        getCreateNewsButton().click();
    }

    public ItemsContainer getItemsContainer() {
        return itemsContainer = new ItemsContainer(driver);
    }

    /**
     * Scroll to WebElement, in case when need to click on it or without scrolling are invisible
     *
     * @param el
     */
    private void scrollToElement(WebElement el) {
        Actions action = new Actions(driver);
        action.moveToElement(el).perform();
    }

    /**
     * Waiting for elements became visible
     *
     * @param i
     */
    private void waiting(int i) {
        try {
            Thread.sleep(i * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get number of ItemComponent, what are present on EcoNewsPage
     *
     * @return int
     */
    public int getNumberOfItemComponent() {
        return new QuantityItems().quantityItems(getFoundItemsText());
    }

    /**
     * Method allows to choose type of news, which will be displayed on the EcoNewsPage
     *
     * @param tags
     * @return EcoNewsPage
     */
    public EcoNewsPage selectFilters(List<Tag> tags) {
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
    public EcoNewsPage deselectFilters(List<Tag> tags) {
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
    public EcoNewsPage switchLanguage(Languages language) {
        chooseLanguage(language);
        return new EcoNewsPage(driver);
    }

    /**
     * News are displayed as grid
     *
     * @return EcoNewsPage
     */
    public EcoNewsPage switchToGridView() {
        clickGridView();
        return new EcoNewsPage(driver);
    }

    /**
     * News are displaeyd as list
     *
     * @return EcoNewsPage
     */
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
    public SingleNewsPage switchToSingleNewsPageByNumber(int number) {
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
    public SingleNewsPage switchToSingleNewsPageByParameters(NewsData news) {
        scrollToElement(itemsContainer.findItemComponentByParameters(news).getTitle());
        itemsContainer.clickItemComponentOpenPage(news);
        return new SingleNewsPage(driver);
    }

    /**
     * Open CreateNewsPage
     *
     * @return CreateNewsPage
     */
    public CreateNewsPage gotoCreateNewsPage() {
        scrollToElement(getCreateNewsButton());
        clickCreateNewsButton();
        return new CreateNewsPage(driver);
    }


    /*<======================================Grid View==========================================>*/
    // Functional
    @Step("Verification of page condition")
    public void pageExistQuickCheck() {
        searchElementByCss(header);
        searchElementByCss(tagsFilterBlock);
        searchElementByCss(tagsFilterLabel);
        searchElementByCss(tags);
        searchElementByCss(articleFoundCounter);
        searchElementByCss(displayedArticles);
        searchElementByCss(listViewButton);
        searchElementByCss(galleryViewButton);
    }

    @Step("Get list of elements by css")
    public List<WebElement> getElements(By cssSelector) {
        logger.info("Get list of elements by css");
        return driver.findElements(cssSelector);
    }


    @Step("Set actual information from page to articleExistCount")
    public EcoNewsPage updateArticlesExistCount() {
        logger.info("Set actual information from page to articleExistCount");
        waiting(getElements(displayedArticles));
        articleExistCount = Integer.parseInt(searchElementByCss(articleFoundCounter).getText().split(" ")[0]);
        return this;
    }

    @Step("Scroll under end of page")
    public void scrollDown() {
        logger.info("refresh page");
        driver.navigate().refresh();
        logger.info("scroll down");
        waiting(searchElementByCss(By.cssSelector("body")));
        while (articleExistCount != articleDisplayedCount) {
            searchElementByCss(By.cssSelector("body")).sendKeys(Keys.CONTROL, Keys.END);
            articleDisplayedCount = getElements(displayedArticles).size();
        }
        waiting(getElements(displayedArticles));
    }

    SoftAssert softAssert = new SoftAssert();
    @Step("Verification that all content in the chosen article displayed")
    private void isArticleContentDisplayed(WebElement element) {
        logger.info("<---------------------------------------------------------------->");
        logger.info("Verification that all content in the chosen article displayed");
        logger.info("assert all items displayed");
        logger.info("Titele: " + element.findElement(articleTitle).getText());
        softAssert.assertTrue (
                element.findElement(articleImage).isDisplayed() &&
                        element.findElement(articleEcoButton).isDisplayed() &&
                        element.findElement(articleTitle).isDisplayed() &&
                        element.findElement(articleText).isDisplayed() &&
                        element.findElement(articleCreationDate).isDisplayed() &&
                        element.findElement(articleAuthorName).isDisplayed(),
                "Assert that all content is displayed in article");
        logger.info("assert text length < 170");
        softAssert.assertTrue(
                element.findElement(articleText).getText().trim()
                        .replace("\\s","").length() < 171,
                "Assert that topic text lenght < 170. Title: " + element.findElement(articleTitle).getText() );
    }

    @Step("Verification that all text content in the chosen article displayed")
    private void isArticleTextContentDisplayed(WebElement element) {
        logger.info("<---------------------------------------------------------------->");
        logger.info("Verification that all text content in the chosen article displayed");
        logger.info("assert all text  items displayed");
        logger.info("Titele: " + element.findElement(articleTitle).getText());
        softAssert.assertTrue (
                element.findElement(articleTitle).isDisplayed() &&
                        element.findElement(articleText).isDisplayed() &&
                        element.findElement(articleCreationDate).isDisplayed() &&
                        element.findElement(articleAuthorName).isDisplayed()
                ,"Assert that all text content is displayed in article");
        logger.info("assert text length < 170");
        softAssert.assertTrue(
                element.findElement(articleText).getText().trim()
                        .replace("\\s","").length() < 171,
                "Assert that topic text lenght < 170. Title: " + element.findElement(articleTitle).getText() );
    }

        @Step("Verification that all content in the list of articles displayed")
    public void isArticleContentDisplayed(List<WebElement> elements) {
        logger.info("Verification that all content in the list of articles displayed");
        elements.forEach(this::isArticleContentDisplayed);
        softAssert.assertAll();
    }

    @Step("Verification that all text content in the list of articles displayed")
    public void isArticleTextContentDisplayed(List<WebElement> elements) {
        logger.info("Verification that all text content in the list of articles displayed");
        elements.forEach(this::isArticleTextContentDisplayed);
        softAssert.assertAll();
    }

    @Step("short explicit wait visibility Of element")
    private void waiting(WebElement element) {
        logger.info("short explicit wait visibility Of element");
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(element));
    }

    @Step("short explicit wait visibility Of elements list")
    public void waiting(List<WebElement> elements) {
        logger.info("short explicit wait visibility Of elements list");
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfAllElements(elements));
    }



    @Step("Get creation date")
    public String   getArticleCreationDate(WebElement element){
        logger.info("Get creation date");
        String date =  element.findElement(articleCreationDate)
                .getText().replace(",","");
        String[]dateFlip = date.split(" ");
        String dbFormatDate = dateFlip[2] + "-" + dateFlip[0] + "-" + dateFlip[1];
        return dbFormatDate
                .replace("Jan","01")
                .replace("Feb","02")
                .replace("Mar","03")
                .replace("Apr","04")
                .replace("May","05")
                .replace("Jun","06")
                .replace("Jul","07")
                .replace("Aug","08")
                .replace("Sep","09")
                .replace("Oct","10")
                .replace("Nov","11")
                .replace("Dec","12")
                .replace("0","");
    }

    public String getChronologicalDateFromDB(String topic){
        Pattern pattern = Pattern.compile("creation_date=[^\"]+");
        final Matcher m = pattern.matcher(topic);
        m.find();

        return m.group(0)
                .replace(",","")
                .substring(0, 24)
                .replace("creation_date=","")
                .replace("0","");
    }
    /*<======================================Grid View==========================================>*/
    @Override
    public WebDriver setDriver() {
        return this.driver;
    }
}
