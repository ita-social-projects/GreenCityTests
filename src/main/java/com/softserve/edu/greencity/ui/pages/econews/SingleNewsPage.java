package com.softserve.edu.greencity.ui.pages.econews;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.softserve.edu.greencity.ui.pages.common.TopPart;

import static com.softserve.edu.greencity.ui.locators.SingleNewsPageLocators.*;
import static com.softserve.edu.greencity.ui.locators.EcoNewsCommentLocators.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class SingleNewsPage extends TopPart {

    protected WebDriverWait wait;

    private List<WebElement> tagsList = driver.findElements(TAGS_LIST.getPath());
    private ItemsContainer itemsContainer;

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

    private WebElement getReplyButton() {
        return searchElementByCss(REPLY_BUTTON.getPath());
    }

    private WebElement getDeleteButton() {
        return searchElementByCss(DELETE_BUTTON.getPath());
    }

    private WebElement getEditButton() {
        return searchElementByCss(EDIT_BUTTON.getPath());
    }

    private WebElement getCommentButton() {
        return searchElementByCss(COMMENT_BUTTON.getPath());
    }

    private WebElement getCommentArea() {
        return searchElementByCss(COMMENT_AREA.getPath());
//        return driver.findElement(COMMENT_AREA.getPath());
    }

    public SingleNewsPage addComment(String value) {
        WebElement area = getCommentArea();
        area.click();
        area.sendKeys(value);
        getCommentButton().click();
        return new SingleNewsPage(driver);
    }

    public List<WebElement> getCommentsList() {
        return driver.findElements(COMMENTS_LIST.getPath());
    }

    public SingleNewsPage replyToComment(int commentNumber, String replyText) {
        if (replyButtonExist()) {
            //TODO
            List<WebElement> comments = getCommentsList();
            if (commentNumber + 1 > comments.size()) {
                throw new IllegalArgumentException("comment number was out of range");
            } else {
                WebElement comment = comments.get(commentNumber);
                comment.findElement(REPLY_BUTTON.getPath()).click();
                WebElement reply = comment.findElement(COMMENT_REPLY.getPath());

            }
            return new SingleNewsPage(driver);
        } else {
            return this;
        }
    }

    public boolean replyButtonExist() {
        if (driver.findElements(REPLY_BUTTON.getPath()).size() == 0) {
            return false;
        } else {
            return true;
        }
    }

    public String getTitleText() {
        return getTitle().getText().trim();
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

    @Override
    public WebDriver setDriver() {
        return this.driver;
    }

}
