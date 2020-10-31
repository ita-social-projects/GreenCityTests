package com.softserve.edu.greencity.ui.pages.common;

import com.softserve.edu.greencity.ui.tools.engine.StableWebElementSearch;
import com.softserve.edu.greencity.ui.tools.engine.WaitsSwitcher;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static com.softserve.edu.greencity.ui.locators.CommentComponentLocators.*;


public class CommentContainer implements StableWebElementSearch {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private WaitsSwitcher waitsSwitcher;
    protected WebDriver driver;
    private List<CommentComponent> commentComponents;
    private By item = COMMENTS_COMPONENTS.getPath();

    public CommentContainer(WebDriver driver){
        this.driver = driver;
        //checkElements();
    }

    public void checkElements(){
        if(isCommentPresent()){
            commentComponents = new ArrayList<>();
            for (WebElement current : getComments()) {
                commentComponents.add(new CommentComponent(driver, current));
            }
        } else {
            throw new IllegalArgumentException("Comment isn't present on this page");
        }
    }

    public boolean isCommentPresent(){
        return driver.findElements(COMMENTS_COMPONENTS.getPath()).size() > 0;
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
        return waitsSwitcher.setExplicitWait(7,
                ExpectedConditions.visibilityOfAllElementsLocatedBy(item));
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
/*
    public WebElement getCommentField(){
        return searchElementByCss(COMMENT_FIELD.getPath());
    }

    public CommentContainer setCommentText(String commentText){
        getCommentField().sendKeys(commentText);
        return this;
    }

    public  WebElement getCommentButton(){
        return searchElementByCss(COMMENT_BUTTON.getPath());
    }

    public CommentContainer clickCommentButton() {
        getCommentButton().click();
        return this;
    }

    public CommentContainer addComment(String commentText){
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        setCommentText(commentText).clickCommentButton();
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this;
    }*/


    @Override
    public WebDriver setDriver() {
        return this.driver;
    }
}
