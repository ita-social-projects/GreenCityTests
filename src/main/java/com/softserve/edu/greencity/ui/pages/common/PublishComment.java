package com.softserve.edu.greencity.ui.pages.common;

import com.softserve.edu.greencity.ui.pages.econews.SingleNewsPage;
import com.softserve.edu.greencity.ui.tools.engine.StableWebElementSearch;
import com.softserve.edu.greencity.ui.tools.engine.WaitsSwitcher;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static com.softserve.edu.greencity.ui.locators.CommentComponentLocators.*;

public class PublishComment implements StableWebElementSearch {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private WaitsSwitcher waitsSwitcher;
    protected WebDriver driver;
    private List<CommentComponent> commentComponents;
    private By item = COMMENTS_COMPONENTS.getPath();

    public PublishComment(WebDriver driver){
        this.driver = driver;
        this.waitsSwitcher= new WaitsSwitcher(driver);
    }

    public WebElement getCommentField(){
        return searchElementByCss(COMMENT_FIELD.getPath());
    }

    public PublishComment setCommentText(String commentText){
        getCommentField().sendKeys(commentText);
        return this;
    }

    public  WebElement getCommentButton(){
        return searchElementByCss(COMMENT_BUTTON.getPath());
    }

    public PublishComment clickCommentButton() {
        getCommentButton().click();
        waitsSwitcher.setExplicitWait(5,
                ExpectedConditions.not(ExpectedConditions.elementToBeClickable(getCommentButton())));
        return this;
    }

    public SingleNewsPage addComment(String commentText){
        setCommentText(commentText).clickCommentButton();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new SingleNewsPage(driver);
    }

    @Override
    public WebDriver setDriver() {
        return this.driver;
    }
}
