package com.softserve.edu.greencity.ui.pages.econews;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.softserve.edu.greencity.ui.pages.common.TopPart;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.*;

/**
 * PreViewPage class
 *
 * @author lv-493 Taqc/Java
 */
public class PreViewPage extends TopPart  {

    protected WebDriverWait wait;

    private By titleField = By.cssSelector("div.news-title");
    private By dateField = By.cssSelector("div.news-info-date");
    private By authorField = By.cssSelector("div.news-info-author");
    private By contentField = By.cssSelector("div.news-text-content");
    private By imgTwitterLink = By.xpath("//img[contains(@src,'twitter.svg')]");
    private By imgLinkedInLink = By.xpath("//img[contains(@src,'linkedin.svg')]");
    private By imgFacebookLink = By.xpath("//img[contains(@src,'facebook.svg')]");
    private By backToEditingLink = By.cssSelector("div.button-text");
    private WebElement publishButton;
    private List<WebElement> tagsFields;

    /**
     * Constructor PreViewPage
     *
     * @param driver
     */
    public PreViewPage(WebDriver driver) {
        super(driver);
        checkElements();
    }

    private void checkElements() {
        wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(getBackToEditingLink()));
    }

    public List<WebElement> getTagsFields() {
        tagsFields = driver.findElements(By.cssSelector("div.tags > div"));
        return tagsFields;
    }

    public WebElement getTitleField() {
        return searchElementByCss(titleField);
    }

    public String getTitleFieldText() {
        return getTitleField().getText();
    }

    public WebElement getDateField() {
        return searchElementByCss(dateField);
    }

    public String getDateFieldText() {
        return getDateField().getText();
    }

    public WebElement getAuthorField() {
        return searchElementByCss(authorField);
    }

    public String getAuthorFieldText() {
        return getAuthorField().getText();
    }

    public WebElement getContentField() {
        return searchElementByCss(contentField);
    }

    public String getContentFieldText() {
        return getContentField().getText();
    }

    public WebElement getImgTwitterLink() {
        return searchElementByXpath(imgTwitterLink);
    }

    public void clickImgTwitterLink() {
        getImgTwitterLink().click();
    }

    public WebElement getImgLinkedInLink() {
        return searchElementByXpath(imgLinkedInLink);
    }

    public void clickImgLinkedInLink() {
        getImgLinkedInLink().click();
    }

    public WebElement getImgFacebookLink() {
        return searchElementByXpath(imgFacebookLink);
    }

    public void clickImgFacebookLink() {
        getImgFacebookLink().click();
    }

    public WebElement getBackToEditingLink() {
        return searchElementByCss(backToEditingLink);
    }

    public void clickBackToEditingLink() {
        getBackToEditingLink().click();
    }

    public WebElement getPublishButton() {
        List<WebElement> list = driver.findElements(By.cssSelector("button[type='submit']"));
        if (list.size() > 0) {
            publishButton = list.get(0);
        } else {
            return null;
        }
        return publishButton;
    }

    public boolean isPublishButtonPresent() {
        return (driver.findElements(By.cssSelector("button[type='submit']")).size() > 0);
    }

    private void clickPublishButton() {
        if (isPublishButtonPresent()) {
            getPublishButton().click();
        } else {
            logger.info("Element Publish button does not exist.");
        }
    }

    /**
     * Get sorted list of Strings with tags names
     *
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

    /**
     * Method to back to CreateNewsPage from PreViewPage
     *
     * @return CreateNewsPage
     */
    public CreateNewsPage backToCreateNewsPage() {
        clickBackToEditingLink();
        return new CreateNewsPage(driver);
    }

    /**
     * Method to publish news
     *
     * @return EcoNewsPage
     */
    public EcoNewsPage publishNews() {
        clickPublishButton();
        return new EcoNewsPage(driver);
    }

    @Override
    public WebDriver setDriver() {
        return this.driver;
    }
}
