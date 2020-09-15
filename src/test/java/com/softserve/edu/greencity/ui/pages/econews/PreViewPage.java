package com.softserve.edu.greencity.ui.pages.econews;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.softserve.edu.greencity.ui.pages.common.TopPart;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.*;

/**
 * @author lv-519 Taqc/Java
 */
public class PreViewPage extends TopPart {

    protected WebDriverWait wait;

    private By titleField = By.cssSelector("div.news-title");
    private By dateField = By.cssSelector("div.news-info-date");
    private By authorField = By.cssSelector("div.news-info-author");
    private By contentField = By.cssSelector("div.news-text-content");
    private By imgTwitterLink = By.xpath("//img[contains(@src,'twitter.svg')]");
    private By imgLinkedInLink = By.xpath("//img[contains(@src,'linkedin.svg')]");
    private By imgFacebookLink = By.xpath("//img[contains(@src,'facebook.svg')]");
    private By backToEditingButton = By.cssSelector("div.button-text");
    private By publishButton = By.cssSelector("button[type='submit']");
    private List<WebElement> tagsFields;

    public PreViewPage(WebDriver driver) {
        super(driver);
        checkElements();
    }

    private void checkElements() {
        wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(getBackToEditingButton()));
    }

    @Step("Get tags fields")
    public List<WebElement> getTagsFields() {
        tagsFields = driver.findElements(By.cssSelector("div.tags > div"));
        return tagsFields;
    }

    @Step("Get title field")
    public WebElement getTitleField() {
        return searchElementByCss(titleField);
    }

    @Step("Get title field text")
    public String getTitleFieldText() {
        return getTitleField().getText();
    }

    @Step("Get date field")
    public WebElement getDateField() {
        return searchElementByCss(dateField);
    }

    @Step("Get date field text")
    public String getDateFieldText() {
        return getDateField().getText();
    }

    @Step("Get author field")
    public WebElement getAuthorField() {
        return searchElementByCss(authorField);
    }

    @Step("Get author field text")
    public String getAuthorFieldText() {
        return getAuthorField().getText();
    }

    @Step("Get content field")
    public WebElement getContentField() {
        return searchElementByCss(contentField);
    }

    @Step("Get content filed text")
    public String getContentFieldText() {
        return getContentField().getText();
    }

    @Step("Get twitter image link")
    public WebElement getImgTwitterLink() {
        return searchElementByXpath(imgTwitterLink);
    }

    @Step("Click on twitter image link")
    public void clickImgTwitterLink() {
        getImgTwitterLink().click();
    }

    @Step("Get LinkedIn image link")
    public WebElement getImgLinkedInLink() {
        return searchElementByXpath(imgLinkedInLink);
    }

    @Step("Click on LinkedIn image link")
    public void clickImgLinkedInLink() {
        getImgLinkedInLink().click();
    }

    @Step("Get FaceBook image link")
    public WebElement getImgFacebookLink() {
        return searchElementByXpath(imgFacebookLink);
    }

    @Step("Click on FaceBook image ling")
    public void clickImgFacebookLink() {
        getImgFacebookLink().click();
    }

    @Step("Get back to editing button")
    public WebElement getBackToEditingButton() {
        return searchElementByCss(backToEditingButton);
    }

    @Step("Check if back editing button is displayed")
    public boolean isBackToEditingButtonDisplayed() {
        return getBackToEditingButton().isDisplayed();
    }

    @Step("Click on back editing button")
    public void clickBackToEditingButton() {
        getBackToEditingButton().click();
    }

    @Step("Get publish button")
    public WebElement getPublishButton() {
        return searchElementByCss(publishButton);
    }

    @Step("Check if publish button is present")
    public boolean isPublishButtonPresent() {
        return getPublishButton().isEnabled();
    }

    @Step("Click on publish button")
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
    @Step("Get tags names")
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
    @Step("Go back to create news page")
    public CreateNewsPage backToCreateNewsPage() {
        clickBackToEditingButton();
        return new CreateNewsPage(driver);
    }

    /**
     * Method to publish news
     *
     * @return EcoNewsPage
     */
    @Step("Publish news")
    public EcoNewsPage publishNews() {
        clickPublishButton();
        return new EcoNewsPage(driver);
    }
}
