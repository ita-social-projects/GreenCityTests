package com.softserve.edu.greencity.ui.pages.econews;

import java.util.List;

import com.softserve.edu.greencity.ui.pages.common.CommentContainer;
import com.softserve.edu.greencity.ui.pages.common.PublishComment;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.softserve.edu.greencity.ui.pages.common.TopPart;

import static com.softserve.edu.greencity.ui.locators.CommentComponentLocators.COMMENTS_COMPONENTS;
import static com.softserve.edu.greencity.ui.locators.CommentComponentLocators.PUBLISH_COMPONENT;
import static com.softserve.edu.greencity.ui.locators.EcoNewsPageLocator.DISPLAYED_ARTICLES;
import static com.softserve.edu.greencity.ui.locators.SingleNewsPageLocators.*;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class SingleNewsPage extends TopPart {

    protected WebDriverWait wait;

    private List<WebElement> tagsList = driver.findElements(TAGS_LIST.getPath());
    private ItemsContainer itemsContainer;
    private CommentContainer commentContainer;

    public SingleNewsPage(WebDriver driver) {
        super(driver);
        checkElements();
    }

    private void checkElements() {
        itemsContainer = new ItemsContainer(driver);
        waitsSwitcher.setExplicitWait(10, ExpectedConditions.visibilityOf(getTitle()));
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
        return getAuthor().getText().split(" ")[1];
    }

    public String getAuthorText() {
        return getAuthor().getText();
    }

    private WebElement getContent() {
        return searchElementByCss(CONTENT.getPath());
    }

    private String getContentText() {
        return getContent().getText();
    }

    private WebElement getSourceTitle() {
        return searchElementByCss(SOURCE_TITLE.getPath());
    }

    public String getSourceTitleText() {
        return getSourceTitle().getText();
    }

    private WebElement getSourceLink() {
        return waitsSwitcher.setExplicitWait(
                ExpectedConditions.presenceOfElementLocated(SOURCE_LINK.getPath()));
    }

    public String getSourceLinkText() {
        String link = getSourceLink().getAttribute("href");
        try {
            if (link.equals("null") || link.equals("")) {
                return "";
            }
        } catch (NullPointerException e) {
            logger.warn("NullPointerException in getSourceLinkText() interpreted as not existing href attribute");
            return "";
        }
        return link;
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
     * @return EcoNewsPage
     */
    public EcoNewsPage switchToEcoNewsPageBack() {
        clickGoToNewsButton();
        return new EcoNewsPage(driver);
    }

    /**
     * Gives a list of suggested news in the bottom of page
     * @return ItemsContainer with suggested news
     */
    public ItemsContainer suggestedNews() {
        return new ItemsContainer(driver);
    }

    public CommentContainer getCommentContainer(){
        waitsSwitcher.setExplicitWait(
                ExpectedConditions.presenceOfAllElementsLocatedBy(COMMENTS_COMPONENTS.getPath()));
        //commentContainer = new CommentContainer(driver);
        return new CommentContainer(driver);
    }

    public PublishComment getPublishComment(){
        waitsSwitcher.setExplicitWait(
                ExpectedConditions.presenceOfAllElementsLocatedBy(PUBLISH_COMPONENT.getPath()));
        return new PublishComment(driver);
    }

    @Override
    public WebDriver setDriver() {
        return this.driver;
    }

}
