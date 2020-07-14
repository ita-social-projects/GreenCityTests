package com.softserve.edu.greencity.ui.pages.econews;

import com.softserve.edu.greencity.ui.data.Languages;
import com.softserve.edu.greencity.ui.data.econews.NewsData;
import com.softserve.edu.greencity.ui.data.econews.Tag;
import com.softserve.edu.greencity.ui.pages.common.TopPart;
import com.softserve.edu.greencity.ui.tools.CheckPage;
import com.softserve.edu.greencity.ui.tools.QuantityItems;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * @author lv-493 Taqc/Java
 */
public class EconewsPage extends TopPart {

    private TagsComponent tagsComponent;
    private WebElement createNewsButton;
    private WebElement gridView;
    private WebElement listView;
    private ItemsContainer itemsContainer;
    private WebElement foundItems;

    public EconewsPage(WebDriver driver) {
        super(driver);
        if(driver.findElements(By.cssSelector(".sign-up-link .create-button")).size() == 0) {
            new WebDriverWait(driver, 10)
                    .until(ExpectedConditions.visibilityOf(getCreateNewsButton()));
        } else {
            new WebDriverWait(driver, 10)
                    .until(ExpectedConditions.visibilityOf(getGridView()));
        }

        visualiseElements();
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

    private void initElements() {
        gridView = driver.findElement(By.cssSelector("div[class*='gallery-view']"));
        listView = driver.findElement(By.cssSelector("div[class*='list-view']"));
        foundItems = driver.findElement(By.cssSelector("p[class*='ng-star-inserted']"));
    }

    private TagsComponent getTagsComponent() {
        return tagsComponent = new TagsComponent(driver);
    }

    private WebElement getFoundItems() {
        foundItems = driver.findElement(By.cssSelector("p[class*='ng-star-inserted']"));
        return foundItems;
    }

    private String getFoundItemsText() {
        return getFoundItems().getText();
    }

    public WebElement getGridView() {
        gridView = driver.findElement(By.cssSelector("div.gallery-view"));
        return gridView;
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
        listView = driver.findElement(By.cssSelector("div[class*='list-view']"));
        return listView;
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
        return createNewsButton = driver.findElement(By.id("create-button"));
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
     * Get number of ItemComponent, what are present on EconewsPage
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
     * @return EconewsPage
     */
    public EconewsPage selectFilters(List<Tag> tags) {

        scrollToElement(getTagsComponent().getTags().get(1));
        getTagsComponent().selectTags(tags);
        return new EconewsPage(driver);
    }

    /**
     * Method allows to choose type of news, which will be displayed on the EcoNewsPage
     *
     * @param tags
     * @return EconewsPage
     */
    public EconewsPage deselectFilters(List<Tag> tags) {

        scrollToElement(getTagsComponent().getTags().get(1));
        getTagsComponent().deselectTags(tags);
        return new EconewsPage(driver);
    }

    /**
     * Choose language
     *
     * @param language
     * @return EconewsPage
     */
    public EconewsPage switchLanguage(Languages language) {
        chooseLanguage(language);
        return new EconewsPage(driver);
    }

    /**
     * News are displayed as grid
     *
     * @return EconewsPage
     */
    public EconewsPage switchToGridViev() {
        clickGridView();
        return new EconewsPage(driver);
    }

    /**
     * News are displaeyd as list
     *
     * @return EconewsPage
     */
    public EconewsPage switchToListViev() {
        clickListView();
        return new EconewsPage(driver);
    }

    /**
     * Open OneNewsPage
     *
     * @param number
     * @return OneNewsPage
     */
    public OneNewsPage switchToOneNewsPagebyNumber(int number) {
        scrollToElement(itemsContainer.chooseNewsByNumber(number).getIitle());
        itemsContainer.chooseNewsByNumber(number).clickTitle();
        return new OneNewsPage(driver);
    }

    /**
     * Open OneNewsPage
     *
     * @param news
     * @return OneNewsPage
     */
    public OneNewsPage switchToOneNewsPagebyParameters(NewsData news) {

        scrollToElement(itemsContainer.findItemComponentByParameters(news).getIitle());
        itemsContainer.clickItemComponentOpenPage(news);
        return new OneNewsPage(driver);
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
}
