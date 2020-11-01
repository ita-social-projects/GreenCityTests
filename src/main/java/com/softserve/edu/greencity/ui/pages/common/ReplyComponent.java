package com.softserve.edu.greencity.ui.pages.common;

import com.softserve.edu.greencity.ui.tools.engine.WaitsSwitcher;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.softserve.edu.greencity.ui.locators.CommentComponentLocators.*;

public class ReplyComponent  {

    private WebElement replyItem;
    protected WebDriverWait wait;
    private WebDriver driver;
    private WaitsSwitcher waitsSwitcher;

    public ReplyComponent(WebDriver driver, WebElement replyItem){
        this.driver = driver;
        this.replyItem = replyItem;
        this.waitsSwitcher = new WaitsSwitcher(driver);
    }

    public WebElement getReplyComment(){ return replyItem.findElement(COMMENT_TEXT.getPath()); }

    public String getReplyText(){ return getReplyComment().getText(); }

    public WebElement getReplyAuthor(){
        return replyItem.findElement(AUTHOR_NAME.getPath());
    }

    public String getReplyAuthorText(){
        return getReplyAuthor().getText();
    }

    public WebElement getReplyLikeButton(){
        return replyItem.findElement(LIKE_BUTTON.getPath());
    }

    public ReplyComponent clickReplyLikeButton(){
        getReplyLikeButton().click();
        return this;
    }

    public boolean isReplyLikesButtonDisplayed(){
        return replyItem.findElements(LIKE_BUTTON.getPath()).size() > 0;
    }

    public WebElement getReplyEditButton(){
        return replyItem.findElement(EDIT_COMMENT_BUTTON.getPath());
    }

    public ReplyComponent clickReplyEditButton(){
        getReplyEditButton().click();
        return this;
    }

    public WebElement getReplyDeleteButton(){
        return replyItem.findElement(DELETE_COMMENT_BUTTON.getPath());
    }

    public ReplyComponent clickReplyDeleteButton(){
        getReplyDeleteButton().click();
        waitsSwitcher.setExplicitWait(5,
                ExpectedConditions.invisibilityOf(getReplyDeleteButton()));
        return this;
    }

    public boolean isDeleteReplyButtonDisplayed(){
        return replyItem.findElements(DELETE_COMMENT_BUTTON.getPath()).size() > 0;
    }

    public WebElement getReplyLikes(){
        return replyItem.findElement(COMMENT_LIKES.getPath());
    }

    public String getReplyLikesNumber(){
        return getReplyLikes().getText();
    }

    public WebElement getReplyDate(){
        return replyItem.findElement(COMMENT_DATE.getPath());
    }

    public String getReplyDateText(){
        return getReplyDate().getText();
    }


}
