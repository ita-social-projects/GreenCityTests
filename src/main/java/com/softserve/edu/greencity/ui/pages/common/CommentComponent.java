package com.softserve.edu.greencity.ui.pages.common;

import com.softserve.edu.greencity.ui.tools.engine.StableWebElementSearch;
import com.softserve.edu.greencity.ui.tools.engine.WaitsSwitcher;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.softserve.edu.greencity.ui.locators.CommentComponentLocators.*;

public class CommentComponent extends CommentContainer implements StableWebElementSearch {

    protected WebDriverWait wait;
    //private final WebDriver driver;
    protected WebElement commentItem;
    private WaitsSwitcher waitsSwitcher;
    private By replyItem = REPLY_COMPONENTS.getPath();

    public CommentComponent(WebDriver driver, WebElement commentItem) {
        super(driver);
        //this.driver = driver;
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

    public boolean isLikesButtonDisplayed(){
        return commentItem.findElements(LIKE_BUTTON.getPath()).size() > 0;
    }

    public WebElement getLikeButton(){
        return commentItem.findElement(LIKE_BUTTON.getPath());
    }

    public CommentComponent clickLikeButton(){
        getLikeButton().click();
        return this;
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
        getAddReplyButton().click();
        return this;
    }

    public WebElement getShowReplyButton(){
        return commentItem.findElement(SHOW_REPLIES.getPath());
    }

    public ReplyContainer clickShowReplyButton(){
        getShowReplyButton().click();
        return new ReplyContainer(driver);
    }



}
