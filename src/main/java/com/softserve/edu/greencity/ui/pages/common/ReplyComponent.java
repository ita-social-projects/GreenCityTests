package com.softserve.edu.greencity.ui.pages.common;

import com.softserve.edu.greencity.ui.tools.engine.WaitsSwitcher;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.softserve.edu.greencity.ui.locators.comments.ReplyLocator.*;

public class ReplyComponent {

    private WebElement replyItem;
    protected WebDriverWait wait;
    private WebDriver driver;
    private WaitsSwitcher waitsSwitcher;


    public ReplyComponent(WebDriver driver, WebElement replyItem) {
        this.driver = driver;
        this.replyItem = replyItem;
        this.waitsSwitcher = new WaitsSwitcher(driver);
    }

    public WebElement getReplyComment() {
        return replyItem.findElement(REPLY_CURRENT_TEXT.getPath());
    }

    public String getReplyText() {
        return getReplyComment().getText();
    }

//    public ReplyComponent editReply(String reply) {
//        clickReplyEditButton();
//    }
//
//    private WebElement getReplyTextInput() {
//        return replyItem.findElement()
//    }

    public WebElement getReplyAuthor() {
        return replyItem.findElement(REPLY_AUTHOR.getPath());
    }

    public String getReplyAuthorText() {
        return getReplyAuthor().getText();
    }

    public WebElement getReplyLikeButton() {
        return replyItem.findElement(REPLY_LIKE_BUTTON.getPath());
    }

    public ReplyComponent clickReplyLikeButton() {
        getReplyLikeButton().click();
        return this;
    }

    public boolean isReplyLikesButtonDisplayed() {
        return replyItem.findElements(REPLY_LIKE_BUTTON.getPath()).size() > 0;
    }

    public WebElement getReplyEditButton() {
        return replyItem.findElement(REPLY_EDIT_BUTTON.getPath());
    }

    public ReplyComponent clickReplyEditButton() {
        getReplyEditButton().click();
        return this;
    }

    public WebElement getReplyDeleteButton() {
        return replyItem.findElement(REPLY_DELETE_BUTTON.getPath());
    }

    public ReplyComponent clickReplyDeleteButton() {
        getReplyDeleteButton().click();
        waitsSwitcher.setExplicitWait(5,
                ExpectedConditions.invisibilityOf(getReplyDeleteButton()));
        return this;
    }


    public ReplyComponent clickDeleteReplyButtonConfirm() {
        getReplyDeleteButton().click();
        CommentPopUpComponent commentPopUpComponent = new CommentPopUpComponent(driver);
        commentPopUpComponent.clickConfirmButton();
        return this;
    }

    public ReplyComponent clickDeleteReplyButtonCancel() {
        getReplyDeleteButton().click();
        CommentPopUpComponent commentPopUpComponent = new CommentPopUpComponent(driver);
        commentPopUpComponent.clickCancelButton();
        return this;
    }



    public boolean isDeleteReplyButtonDisplayed() {
        return replyItem.findElements(REPLY_DELETE_BUTTON.getPath()).size() > 0;
    }

    public boolean isEditReplyButtonDisplayed() {
        return replyItem.findElements(REPLY_EDIT_BUTTON.getPath()).size() > 0;
    }

    public String getReplyLikesNumber() {
        return replyItem.findElement(REPLY_LIKE_AMOUNT.getPath()).getText();
    }

    public String getReplyDate() {
        return replyItem.findElement(REPLY_DATE.getPath()).getText();
    }

    public boolean isReplyPresent() {
        try {
            waitsSwitcher.setExplicitWait(5,
                    ExpectedConditions.visibilityOf(getReplyComment()));
            return true;
        } catch (StaleElementReferenceException e) {
            return false;
        }
    }
}
