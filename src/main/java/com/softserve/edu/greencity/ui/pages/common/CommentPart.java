package com.softserve.edu.greencity.ui.pages.common;

import com.softserve.edu.greencity.ui.tools.engine.StableWebElementSearch;
import com.softserve.edu.greencity.ui.tools.engine.WaitsSwitcher;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static com.softserve.edu.greencity.ui.locators.comments.CommentComponentLocators.*;

/**
 * The part of single news page where comments are displayed.
 * A single comment is CommentComponent.java
 */
public class CommentPart implements StableWebElementSearch {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private  WaitsSwitcher waitsSwitcher;
    protected WebDriver driver;
    private List<CommentComponent> commentComponents;

    public CommentPart(WebDriver driver) {
        this.driver = driver;
        this.waitsSwitcher = new WaitsSwitcher(driver);
    }

    public WebElement getCommentCounter() {
        return searchElementByCss(NUMBERS_OF_COMMENTS.getPath());
    }

    public int getNumberOfComment() {
        return Integer.parseInt(getCommentCounter().getText().split(" ")[0]);
    }

    public WebElement getCommentField() {
        return searchElementByCss(ADD_COMMENT_TEXTAREA.getPath());

    }

    public CommentPart setCommentText(String commentText) {
        getCommentField().sendKeys(commentText);
        return this;
    }

    public WebElement getPublishCommentButton() {
        return searchElementByCss(ADD_COMMENT_BUTTON.getPath());
    }

    public CommentPart clickPublishCommentButton() {
        int currentCount = getCommentComponents().size();
        getPublishCommentButton().click();
        //Mind pagination! Only 10 comments are displayed
            waitsSwitcher.setExplicitWait(40,
                    ExpectedConditions.numberOfElementsToBe(COMMENTS_LIST.getPath(), currentCount +1));
        return new CommentPart(driver);
    }



    public boolean isPublishCommentButtonEnable(){
        return getPublishCommentButton().isEnabled();
    }

    public List<CommentComponent> getCommentComponents() {
        commentComponents = new ArrayList<>();
        for (WebElement current : getComments()) {
            commentComponents.add(new CommentComponent(driver, current));
        }
        return commentComponents;
    }

    public List<WebElement> getComments() {
        WaitsSwitcher waitsSwitcher = new WaitsSwitcher(driver);
        try{
            return waitsSwitcher.setExplicitWait(4,
                    ExpectedConditions.visibilityOfAllElementsLocatedBy(COMMENTS_LIST.getPath()));
        }catch (TimeoutException e){
            logger.info("Comments are not present");
            return new ArrayList<>();
        }
    }

    public CommentComponent chooseCommentByNumber(int commentNumber) {
        List<CommentComponent> comments = getCommentComponents();
        if(commentNumber + 1 > comments.size()){
            logger.warn("Comment number was out of range");
            throw new IllegalArgumentException("Comment number was out of range");
        }else {
            return comments.get(commentNumber);
        }
    }

    public CommentComponent chooseCommentByText(String title) {
        CommentComponent result = null;
        for (CommentComponent current : getCommentComponents()) {
            if (current.getCommentText().equals(title)){
                result = current;
            }
        }
        if (result == null) {
            logger.warn("Comment with text " + title + "not exist");
            throw new RuntimeException("Comment Component with text " + title + " not found");
        }
        return result;
    }

    public CommentPart addComment(String commentText){
            setCommentText(commentText).clickPublishCommentButton();
        return this;
    }

    @Override
    public WebDriver setDriver() {
        return this.driver;
    }
}
