package com.softserve.edu.greencity.ui.pages.econews;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.softserve.edu.greencity.ui.pages.common.TopPart;
import static com.softserve.edu.greencity.ui.locators.SingleNewsPageLocators.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SingleNewsPage extends TopPart  {

    protected WebDriverWait wait;

    private List<WebElement> tagsList = driver.findElements(TAGS_LIST.getPath());
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
        return searchElementByCss(GO_TO_NEWS.getPath());
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
        return searchElementByCss(TITLE.getPath());
    }

    public String getTitleText() {
        return getTitle().getText().trim();
    }

    private WebElement getData() {
        return searchElementByCss(DATA.getPath());
    }

    private String getDataText() {
        return getData().getText();
    }

    private WebElement getAuthor() {
        return searchElementByCss(AUTHOR.getPath());
    }

    public String getAuthorNameOnly() {
        String author = getAuthor().getText();
        String [] arr = author.split(" ", 2);
        return arr[1];
    }

    public String getAuthorText(){
        return getAuthor().getText();
    }

    private WebElement getContent() {
        return searchElementByCss(CONTENT.getPath());
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
