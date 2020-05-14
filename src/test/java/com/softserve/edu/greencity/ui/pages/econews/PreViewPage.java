package com.softserve.edu.greencity.ui.pages.econews;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.softserve.edu.greencity.ui.pages.common.TopPart;
import org.openqa.selenium.WebElement;

import java.util.*;

/**
 * PreViewPage class
 * @author lv-493 Taqc/Java
 */
public class PreViewPage extends TopPart {

    // news fields
    private List<WebElement> tagsFields;
    private WebElement titleField;
    private WebElement dateField;
    private WebElement authorField;
    private WebElement contentField;

    //img to share news
    private WebElement imgTwitterLink;
    private WebElement imgLinkedinLink;
    private WebElement imgFacebookLink;

    private WebElement backToEditingLink;
    private WebElement publishButton;

    /**
     * Constructor PreViewPage
     * @param driver
     */
    public PreViewPage(WebDriver driver) {
        super(driver);
        initElements();
    }

    private void initElements() {
        titleField = driver.findElement(By.cssSelector("div.news-title"));
        tagsFields = driver.findElements(By.cssSelector("div.tags > div"));
        contentField = driver.findElement(By.cssSelector("div.news-text"));
        dateField = driver.findElement(By.cssSelector("div.news-info-date"));
        authorField = driver.findElement(By.cssSelector("div.news-info-author"));
        imgFacebookLink = driver.findElement(By.xpath("//img[contains(@src,'facebook.svg')]"));
        imgLinkedinLink = driver.findElement(By.xpath("//img[contains(@src,'linkedin.svg')]"));
        imgTwitterLink = driver.findElement(By.xpath("//img[contains(@src,'twitter.svg')]"));
        backToEditingLink = driver.findElement(By.cssSelector("div.button-text"));

        if (isPublishButtonPresent()){ //TODO shoud create component for Publish button
            publishButton = driver.findElement(By.cssSelector("button[type='submit']"));
        }
        else {
            publishButton = null;
        }
    }

    // Page Object

    // tagsFields;
    public List<WebElement> getTagsFields() {
        return tagsFields;
    }

    //titleField;
    public WebElement getTitleField() {
        return titleField;
    }

    public String getTitleFieldText() {
        return getTitleField().getText();
    }

    //dateField;
    public WebElement getDateField() {
        return dateField;
    }

    public String getDateFieldText() {
        return getDateField().getText();
    }

    //authorField;
    public WebElement getAuthorField() {
        return authorField;
    }

    public String getAuthorFieldText() {
        return getAuthorField().getText();
    }

    //contentField
    public WebElement getContentField() {
        return contentField;
    }

    public String getContentFieldText() {
        return getContentField().getText();
    }

    //imgTwitterLink;
    public WebElement getImgTwitterLink() {
        return imgTwitterLink;
    }

    public void clickImgTwitterLink() {
        getImgTwitterLink().click();
    }

    //imgLinkedinLink;
    public WebElement getImgLinkedinLink() {
        return imgLinkedinLink;
    }

    public void clickImgLinkedinLink() {
        getImgLinkedinLink().click();
    }

    //imgFacebookLink;
    public WebElement getImgFacebookLink() {
        return imgFacebookLink;
    }

    public void clickImgFacebookLink() {
        getImgFacebookLink().click();
    }

    //backToEditingLink;
    public WebElement getBackToEditingLink() {
        return backToEditingLink;
    }

    public void clickBackToEditingLink() {
        getBackToEditingLink().click();
    }

    //publishButton
    public WebElement getPublishButton() {
        return publishButton;
    }

    public boolean isPublishButtonPresent() {
        return (driver.findElements(By.cssSelector("button[type='submit']")).size() > 0);
    }

    private void clickPublishButton() {
        if (isPublishButtonPresent()){
            getPublishButton().click();
        }
        else {
            logger.info("Element Publish button does not exist.");
        }
    }

    // Functional

    /**
     * Get sorted list of Strings with tags names
     * @return List<String>
     */
    public List<String> getTagsNames() {
        List<String> tagsNames = new ArrayList<>();
        for (WebElement current : getTagsFields()) {
            tagsNames.add(current.getText().toLowerCase());
        }
        Collections.sort(tagsNames);
        return tagsNames;
    }

    // Business Logic

    /**
     * Method to back to CreateNewsPage from PreViewPage
     * @return CreateNewsPage
     */
    public CreateNewsPage backToCreateNewsPage() {
        clickBackToEditingLink();
        return new CreateNewsPage(driver);
    }

    /**
     * Method to publish news
     * @return EconewsPage
     */
    public EconewsPage publishNews() {
        clickPublishButton();  // Button doesn't work
        return new EconewsPage(driver);
    }
}