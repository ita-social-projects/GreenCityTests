package com.softserve.edu.greencity.ui.pages.common;

import com.softserve.edu.greencity.ui.tools.engine.WaitsSwitcher;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static com.softserve.edu.greencity.ui.locators.comments.CommentLocator.*;

/**
 * A single comment at single news page. All comments are handled by CommentPart.java
 */
public class CommentComponent {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private final WebDriver driver;
    private final WebElement commentItem;
    private final WaitsSwitcher waitsSwitcher;

    public CommentComponent(WebDriver driver, WebElement commentItem) {
        this.driver = driver;
        this.commentItem = commentItem;
        this.waitsSwitcher = new WaitsSwitcher(driver);
    }

    public WebElement getComment() {
        return commentItem.findElement(COMMENT_CURRENT_TEXT.getPath());
    }

    public String getCommentText() {
        return getComment().getText();
    }

    public WebElement getCommentAuthor() {
        return commentItem.findElement(COMMENT_AUTHOR.getPath());
    }

    public String getCommentAuthorText() {
        return getCommentAuthor().getText();
    }

    public WebElement getEditButton() {
        return commentItem.findElement(COMMENT_EDIT_BUTTON.getPath());
    }

    public WebElement getEditTextAria(){
        return commentItem.findElement(COMMENT_EDIT_TEXT_AREA.getPath());
    }

    public WebElement getSaveEditButton(){
        return commentItem.findElement(COMMENT_SAVE_CHANGES_BUTTON.getPath());
    }

    public boolean isEditButtonDisplayed() {
        return commentItem.findElements(COMMENT_EDIT_BUTTON.getPath()).size() > 0;
    }

    public CommentComponent clickEditButton() {
        getEditButton().click();
        return this;
    }

    public CommentComponent setTextInEditAria(String editedText){
        clickEditButton().getEditTextAria().clear();
        getEditTextAria().sendKeys(editedText);
        return this;
    }

    public WebElement getDeleteButton() {
        return commentItem.findElement(COMMENT_DELETE_BUTTON.getPath());
    }

    public CommentComponent clickDeleteCommentButton() {
        getDeleteButton().click();
        CommentPopUpComponent commentPopUpComponent = new CommentPopUpComponent(driver);
        commentPopUpComponent.clickConfirmButton();
        return this;
    }

    public boolean isDeleteCommentButtonDisplayed() {
        return commentItem.findElements(COMMENT_DELETE_BUTTON.getPath()).size() > 0;
    }

    public boolean isCommentPresent() {
        try {
            waitsSwitcher.setExplicitWait(5,
                    ExpectedConditions.visibilityOf(getComment()));
            return true;
        } catch (StaleElementReferenceException e) {
            return false;
        }
    }

    public WebElement getLikeButton() {
        return commentItem.findElement(COMMENT_LIKE_BUTTON.getPath());
    }

    public CommentComponent clickLikeButton() {
        getLikeButton().click();
        return this;
    }

    public boolean isLikesButtonDisplayed() {
        return commentItem.findElements(COMMENT_LIKE_BUTTON.getPath()).size() > 0;
    }

    public WebElement getLikes() {
        return commentItem.findElement(COMMENT_LIKE_AMOUNT.getPath());
    }

    public String getLikesNumber() {
        return getLikes().getText();
    }

    public WebElement getCommentDate() {
        return commentItem.findElement(COMMENT_DATE.getPath());
    }

    public String getCommentDateText() {
        return getCommentDate().getText();
    }

    public WebElement getReplyButton() {
        return commentItem.findElement(COMMENT_REPLY_BUTTON.getPath());
    }

    public CommentComponent clickReplyButton() {
        getReplyButton().click();
        return this;
    }

    public boolean isReplyButtonDisplayed() {
        return commentItem.findElements(COMMENT_REPLY_BUTTON.getPath()).size() > 0;
    }

    public WebElement getReplyField() {
        return commentItem.findElement(ADD_REPLY_TEXTAREA.getPath());
    }

    public CommentComponent setReplyText(String replyText) {
        getReplyField().sendKeys(replyText);
        return this;
    }

    public WebElement getAddReplyButton() {
        return commentItem.findElement(ADD_REPLY_BUTTON.getPath());
    }

    public CommentComponent clickAddReplyButton() {
        boolean isShowReplyButtonDisplayed = isShowReplyDisplayed();
        getAddReplyButton().click();
        if (isShowReplyButtonDisplayed) {
            waitsSwitcher.setExplicitWait(5,
                    ExpectedConditions.textToBePresentInElement(getShowReplyButton(),
                            Integer.toString((Integer.parseInt(getShowReplyButton().getText().split(" ")[1])) + 1)));
        } else {
            waitsSwitcher.setExplicitWait(5,
                    ExpectedConditions.visibilityOf(getShowReplyButton()));
        }

        return this;
    }

    public WebElement getShowReplyButton() {
        return commentItem.findElement(COMMENT_SHOW_REPLIES_BUTTON.getPath());
    }

    public boolean isShowReplyDisplayed() {
        return commentItem.findElements(COMMENT_SHOW_REPLIES_BUTTON.getPath()).size() > 0;
    }

    public CommentComponent openReply() {
        getShowReplyButton().click();
        return this;
    }

    public CommentComponent closeReply() {
        getShowReplyButton().click();
        return this;
    }

    public boolean isAddReplyButtonEnable() {
        return getAddReplyButton().isEnabled();
    }

    public boolean isAddReplyDisplayed() {
        return commentItem.findElements(ADD_REPLY_BUTTON.getPath()).size() > 0;
    }

    public CommentComponent addReply(String replyText) {
        clickReplyButton().setReplyText(replyText).clickAddReplyButton();
        return this;
    }

    public boolean isReplyComponentPresent() {
        return driver.findElements(COMMENT_REPLIES.getPath()).size() > 0;
    }

    private List<ReplyComponent> replyComponent;

    public List<ReplyComponent> getReplyComponents() {
        replyComponent = new ArrayList<>();
        for (WebElement current : getReplies()) {
            replyComponent.add(new ReplyComponent(driver, current));
        }
        return replyComponent;
    }

    public List<WebElement> getReplies() {
        WaitsSwitcher waitsSwitcher = new WaitsSwitcher(driver);
        return waitsSwitcher.setExplicitWait(7,
                ExpectedConditions.visibilityOfAllElementsLocatedBy(COMMENT_REPLIES.getPath()));
    }

    public ReplyComponent chooseReplyByNumber(int replyNumber) {
        List<ReplyComponent> replies = getReplyComponents();
        if (replyNumber + 1 > replies.size()) {
            throw new IllegalArgumentException("Reply number was out of range");
        } else {
            return getReplyComponents().get(replyNumber);
        }
    }

    public ReplyComponent chooseReplyByText(String title) {
        ReplyComponent result = null;
        for (ReplyComponent current : getReplyComponents()) {
            if (current.getReplyText().equals(title)) {
                result = current;
            }
        }
        if (result == null) {
            logger.warn("Reply with text " + title + "not exist");
            throw new RuntimeException("Comment Component with text " + title + " not found");
        } else {
            return result;
        }
    }
}
