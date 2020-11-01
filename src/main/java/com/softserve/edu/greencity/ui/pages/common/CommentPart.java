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

import static com.softserve.edu.greencity.ui.locators.CommentComponentLocators.*;

public class CommentPart implements StableWebElementSearch {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private WaitsSwitcher waitsSwitcher;
    protected WebDriver driver;
    private List<CommentComponent> commentComponents;
    private By item = COMMENTS_COMPONENTS.getPath();

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
        return searchElementByCss(COMMENT_FIELD.getPath());
    }

    public CommentPart setCommentText(String commentText) {
        getCommentField().sendKeys(commentText);
        return this;
    }

    public WebElement getPublishCommentButton() {
        return searchElementByCss(COMMENT_BUTTON.getPath());
    }

    public CommentPart clickPublishCommentButton() {
        if (getCommentComponents().size() == 0) {
            getPublishCommentButton().click();
        } else {
            getPublishCommentButton().click();
            waitsSwitcher.setExplicitWait(5,
                    ExpectedConditions.numberOfElementsToBe(item, getCommentComponents().size() + 1));
        }
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
            return waitsSwitcher.setExplicitWait(2,
                    ExpectedConditions.visibilityOfAllElementsLocatedBy(item));
        }catch (TimeoutException e){
            return new ArrayList<>();
        }
    }

    public CommentComponent chooseCommentByNumber(int commentNumber) {
        List<CommentComponent> comments = getCommentComponents();
        if(commentNumber + 1 > comments.size()){
            throw new IllegalArgumentException("Comment number was out of range");
        }else {
            return getCommentComponents().get(commentNumber);
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
        return new CommentPart(driver);
    }

    @Override
    public WebDriver setDriver() {
        return this.driver;
    }

}
