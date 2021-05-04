package com.softserve.edu.greencity.ui.pages.econews;

import java.util.ArrayList;
import java.util.List;

import com.softserve.edu.greencity.ui.tools.engine.StableWebElementSearch;
import static com.softserve.edu.greencity.ui.locators.EcoNewsPageLocator.*;

import com.softserve.edu.greencity.ui.tools.engine.WaitsSwitcher;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.softserve.edu.greencity.data.econews.NewsData;

/**
 * Contains all itemsComponents (news cards) that are present on page
 * Use for SingleNewsPage & EcoNewsPage.
 *
 * @author lv-493
 */
public class ItemsContainer implements StableWebElementSearch {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private WebDriver driver;
    private List<ItemComponent> itemComponents;
    private By items = DISPLAYED_ARTICLES.getPath();
    private boolean isVertical;

    public ItemsContainer(WebDriver driver) {
        this.driver = driver;
        isVertical = false;
        // TODO remove from constructor
        checkElements();
    }

    public ItemsContainer(WebDriver driver, boolean isVertical) {
        this.driver = driver;
        this.isVertical = isVertical;
        checkElements();
    }

    private void checkElements() {
        getItemComponents();
    }

    private List<ItemComponent> getItemComponents() {
        itemComponents = new ArrayList<>();
        for (WebElement current : getItems()) {
            itemComponents.add(new ItemComponent(driver, current, isVertical));
        }


        return itemComponents;
    }

    private List<WebElement> getItems() {
        WaitsSwitcher waitsSwitcher = new WaitsSwitcher(driver);

        return waitsSwitcher.setExplicitWait(5,
                ExpectedConditions.presenceOfAllElementsLocatedBy(items));
    }

    public int getItemsSize() {
        return getItems().size();
    }

    public boolean hasListViewClassActive() {
        boolean isActive = false;
        for (WebElement current : getItems()) {
            isActive = current.getAttribute("class").contains("active");
        }
        return isActive;
    }

    /**
     * In this method we get how many ItemComponent is displayed
     *
     * @return int
     */
    public int getItemComponentsCount() {
        return getItemComponents().size();
    }

    /**
     * We get list of all news headers
     *
     * @return list itemComponentsHeader
     */
    private List<String> getItemComponentsHeader() {
        List<String> itemComponentsHeader = new ArrayList<>();
        for (ItemComponent cur : getItemComponents()) {
            itemComponentsHeader.add(cur.getTitleText());
        }
        return itemComponentsHeader;
    }

    /**
     * Find news by header
     *
     * @param headerName headerName
     * @return ItemComponent
     */
    protected ItemComponent getItemComponentByHeader(String headerName) {
        ItemComponent result = null;
        for (ItemComponent cur : getItemComponents()) {
            if (cur.getTitleText().toLowerCase()
                    .contains(headerName.toLowerCase())) {
                result = cur;
                break;
            }
        }
        if (result == null) {
            logger.warn("News with header " + headerName + "not exist");
            throw new RuntimeException("HeaderName " + headerName + " not found");
        }
        return result;
    }

    /**
     * Open new page with news given by title, tags, content
     *
     * @param news one
     */
    protected void clickItemComponentOpenPage(NewsData news) {
//   	getItemComponentByHeader(news.getTitle()).clickContent();
        findItemComponentByParameters(news).getTitle().click();
    }


    /**
     * Find appropriate news by its parameters: title, list tags & content
     *
     * @param news
     * @return ItemComponent
     */
    public ItemComponent findItemComponentByParameters(NewsData news) {
        for (ItemComponent cur : getItemComponents()) {
            if (cur.getTitleText().toLowerCase().equals(news.getTitle().toLowerCase())
                    && cur.getTagsText().equals(news.getTagsName())
                    && news.getContent().toLowerCase().trim().contains(cur.getContentText().toLowerCase().trim())
            ) {
                return cur;
            }
        }
        logger.warn("News with parameters " + news.toString() + " does not exist");
        throw new RuntimeException("ItemComponent with parameters " + news.toString() + " not found");
    }

    /**
     * this method return ItemComponent by its number
     *
     * @param number
     * @return ItemComponent
     */
    public ItemComponent chooseNewsByNumber(int number) {
        return getItemComponents().get(number);
    }

    @Override
    public WebDriver setDriver() {
        return this.driver;
    }
}
