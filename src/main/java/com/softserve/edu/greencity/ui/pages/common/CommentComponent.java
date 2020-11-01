package com.softserve.edu.greencity.ui.pages.common;

import com.softserve.edu.greencity.ui.tools.engine.WaitsSwitcher;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static com.softserve.edu.greencity.ui.locators.CommentComponentLocators.*;

public class CommentComponent {

    private WebDriver driver;
    private WebElement commentItem;
    private WaitsSwitcher waitsSwitcher;

    public CommentComponent(WebDriver driver, WebElement commentItem) {
        this.driver = driver;
        this.commentItem = commentItem;
        this.waitsSwitcher = new WaitsSwitcher(driver);
    }

    public WebElement getComment(){
        return commentItem.findElement(COMMENT_TEXT.getPath());
    }

    public String getCommentText(){
        return getComment().getText();
    }

    public WebElement getCommentAuthor(){
        return commentItem.findElement(AUTHOR_NAME.getPath());
    }

    public String getCommentAuthorText(){
        return getCommentAuthor().getText();
    }

    public WebElement getEditButton(){
        return commentItem.findElement(EDIT_COMMENT_BUTTON.getPath());
    }
    public CommentComponent clickEditButton(){
        getEditButton().click();
        return this;
    }

    public WebElement getDeleteButton(){
        return commentItem.findElement(DELETE_COMMENT_BUTTON.getPath());
    }

    public CommentComponent clickDeleteCommentButton(){
        getDeleteButton().click();
        waitsSwitcher.setExplicitWait(5,
                ExpectedConditions.invisibilityOf(getDeleteButton()));
        return this;
    }

    public boolean isDeleteCommentButtonDisplayed(){
        return commentItem.findElements(DELETE_COMMENT_BUTTON.getPath()).size() > 0;
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

    public WebElement getLikeButton(){
        return commentItem.findElement(LIKE_BUTTON.getPath());
    }

    public CommentComponent clickLikeButton(){
        getLikeButton().click();
        return this;
    }

    public boolean isLikesButtonDisplayed(){
        return commentItem.findElements(LIKE_BUTTON.getPath()).size() > 0;
    }

    public WebElement getLikes(){
        return commentItem.findElement(COMMENT_LIKES.getPath());
    }

    public String getLikesNumber(){
        return getLikes().getText();
    }

    public WebElement getCommentDate(){
        return commentItem.findElement(COMMENT_DATE.getPath());
    }

    public String getCommentDateText(){
        return getCommentDate().getText();
    }

    public WebElement getReplyButton(){
        return commentItem.findElement(REPLY_BUTTON.getPath());
    }

    public CommentComponent clickReplyButton(){
        getReplyButton().click();
        return this;
    }

    public boolean isReplyButtonDisplayed(){
        return commentItem.findElements(REPLY_BUTTON.getPath()).size() > 0;
    }

    public WebElement getReplyField(){
        return commentItem.findElement(REPLY_FIELD.getPath());
    }

    public CommentComponent setReplyText(String replyText){
        getReplyField().sendKeys(replyText);
        return this;
    }

    public WebElement getAddReplyButton(){
        return commentItem.findElement(ADD_REPLY_BUTTON.getPath());
    }

    public CommentComponent clickAddReplyButton(){
        if(isShowReplyDisplayed()){
            getAddReplyButton().click();
            waitsSwitcher.setExplicitWait(5,
                    ExpectedConditions.textToBePresentInElement(getShowReplyButton(),
                            Integer.toString((Integer.parseInt(getShowReplyButton().getText().split(" ")[1])) + 1)));
        }else {
            getAddReplyButton().click();
            waitsSwitcher.setExplicitWait(5,
                    ExpectedConditions.visibilityOf(getShowReplyButton()));
        }
        return this;
    }

    public WebElement getShowReplyButton(){
        return commentItem.findElement(SHOW_REPLIES.getPath());
    }

    public boolean isShowReplyDisplayed(){
        return commentItem.findElements(SHOW_REPLIES.getPath()).size() > 0;
    }

    public ReplyContainer openReply(){
        getShowReplyButton().click();
        return new ReplyContainer(driver);
    }

    public CommentComponent closeReply(){
        getShowReplyButton().click();
        return this;
    }
    public boolean isAddReplyButtonEnable(){
        return getAddReplyButton().isEnabled();
    }

    public boolean isAddReplyDisplayed(){
        return commentItem.findElements(ADD_REPLY_BUTTON.getPath()).size() > 0;
    }

    public CommentComponent addReply(String replyText){
            clickReplyButton().setReplyText(replyText).clickAddReplyButton();
        return this;
    }

    public boolean isReplyComponentPresent(){
        return driver.findElements(REPLY_COMPONENTS.getPath()).size() > 0;
    }

}
