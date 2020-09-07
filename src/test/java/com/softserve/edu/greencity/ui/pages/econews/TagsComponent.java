package com.softserve.edu.greencity.ui.pages.econews;

import com.softserve.edu.greencity.ui.data.econews.Tag;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Components of CreateNewsPage & EcoNewsPage
 * Contains WebElements that define type of news(names of news is in enum Tag)
 *
 * @author lv-492
 */
public class TagsComponent {

    private WebDriver driver;
    private List<WebElement> tags;

    public TagsComponent(WebDriver driver) {
        this.driver = driver;
        initElements();
    }

    private void initElements() {
        tags = driver.findElements(By.cssSelector("div.tags > button"));
        if (tags.size() == 0) {
            tags = driver.findElements(By.cssSelector("ul.ul-eco-buttons > a > li"));
        }
    }

    protected List<WebElement> getTags() {
        return tags;
    }

    /**
     * Get sorted list of Strings with tags names
     *
     * @param tagsButtons
     * @return List<String>
     */
    protected List<String> getTagsNames(List<WebElement> tagsButtons) {
        List<String> tagsNames = new ArrayList<>();
        for (WebElement current : tagsButtons) {
            tagsNames.add(current.getText().toLowerCase());
        }
        Collections.sort(tagsNames);
        return tagsNames;
    }

    /**
     * Get WebElement of news type by its name
     *
     * @param tagName
     * @return WebElement
     */
    protected WebElement getWebElementByTagName(Tag tagName) {
        WebElement tag = getTags().stream()
                .filter((element) -> element
                        .getText().toLowerCase().contains(tagName.toString().toLowerCase()))
                .findFirst().get();
        return tag;
    }

    /**
     * Get list of selected tags elements
     *
     * @return List<WebElement>
     */
    protected List<WebElement> getSelectedTagsButtons() {
        return getTags().stream()
                .filter((element) -> element
                        .getAttribute("class").contains("filters-color"))
                .collect(Collectors.toList());
    }

    /**
     * Method to select tags
     *
     * @param tags
     */
    public void selectTags(List<Tag> tags) {
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        js.executeScript("window.scrollTo(0, -document.body.scrollHeight);");
        for (Tag current : tags) {
            selectTag(current);
        }
    }


    /**
     * Check is some WebElement are choosen(clicked first time)
     *
     * @param newsfilter
     * @return boolean
     */
    protected boolean isTagActive(Tag newsfilter) {
        return getWebElementByTagName(newsfilter)
                .getAttribute("class")
                .matches(".*(clicked-filter-button|filters-color).*");
    }

    /**
     * Click on some WebElement to choose it(click first time)
     *
     * @param newsfilter
     * @return boolean
     */
    public void selectTag(Tag newsfilter) {
        if (!isTagActive(newsfilter)) {
            getWebElementByTagName(newsfilter).click();
        }
    }

    /**
     * Click on some WebElement to deselect it(click second time)
     *
     * @param tag
     * @return boolean
     */
    public void deselectTag(Tag tag) {
        if (isTagActive(tag)) {
            getWebElementByTagName(tag).click();
        }
    }

    public void deselectTags(List<Tag> tags) {
        for (Tag current : tags) {
            deselectTag(current);
        }
    }
}
