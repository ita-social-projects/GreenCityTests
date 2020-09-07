package com.softserve.edu.greencity.ui.pages.econews;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.softserve.edu.greencity.ui.pages.common.TopPart;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SingleNewsPage extends TopPart  {

    protected WebDriverWait wait;

    private By goToNews = By.cssSelector("div.back-button");
    private By title = By.cssSelector("div.news-title");
    private By data = By.cssSelector("div.news-info > div.news-info-date");
    private By author = By.cssSelector("div.news-info > div.news-info-author");
    private By picture = By.cssSelector("div.news-image > img.news-image-img");
    private By content = By.cssSelector("div.news-text");
    private List<WebElement> tagsList = driver.findElements(By.cssSelector("div.tags > div"));
    private ItemsContainer itemsContainer;

    public SingleNewsPage(WebDriver driver) {
        super(driver);
        checkElements();
    }

    private void checkElements() {
        itemsContainer = new ItemsContainer(driver);
        wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(getTitle()));
    }

    private WebElement getGoToNews() {
        return searchElementByCss(goToNews);
    }

    private String getGoToNewsText() {
        return getGoToNews().getText();
    }

    private void clickGoToNewsButton() {
        getGoToNews().click();
    }

    private List<WebElement> getTagsList() {
        return tagsList;
    }

    private WebElement getTitle() {
        return searchElementByCss(title);
    }

    public String getTitleText() {
        return getTitle().getText().trim();
    }

    private WebElement getData() {
        return searchElementByCss(data);
    }

    private String getDataText() {
        return getData().getText();
    }

    private WebElement getAuthor() {
        return searchElementByCss(author);
    }

    private String getAuthorText() {
        return getAuthor().getText();
    }

    private WebElement getContent() {
        return searchElementByCss(content);
    }

    private String getContentText() {
        return getContent().getText();
    }

    /**
     * Go to next SingleNewsPage
     *
     * @param number
     * @return SingleNewsPage
     */
    public SingleNewsPage switchToNextSingleNewsPageByNumber(int number) {
        itemsContainer.chooseNewsByNumber(number).clickTitle();
        return new SingleNewsPage(driver);
    }

    /**
     * Go to next SingleNewsPage
     *
     * @return SingleNewsPage
     */
    public SingleNewsPage switchToNextSingleNewsPage() {
        switchToNextSingleNewsPageByNumber(1);
        return new SingleNewsPage(driver);
    }

    /**
     * Return to EcoNewsPage
     *
     * @return EcoNewsPage
     */
    public EcoNewsPage switchToEcoNewsPageBack() {
        clickGoToNewsButton();
        return new EcoNewsPage(driver);
    }

    @Override
    public WebDriver setDriver() {
        return this.driver;
    }

}
