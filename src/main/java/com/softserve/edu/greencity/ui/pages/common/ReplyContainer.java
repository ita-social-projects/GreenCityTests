package com.softserve.edu.greencity.ui.pages.common;

import com.softserve.edu.greencity.ui.tools.engine.WaitsSwitcher;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static com.softserve.edu.greencity.ui.locators.CommentComponentLocators.COMMENTS_COMPONENTS;
import static com.softserve.edu.greencity.ui.locators.CommentComponentLocators.REPLY_COMPONENTS;

public class ReplyContainer {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private WebDriver driver;
    private List<ReplyComponent> replyComponent;
    private By item = REPLY_COMPONENTS.getPath();

    public ReplyContainer(WebDriver driver){
        this.driver = driver;
        //checkElements();
    }

    public void checkElements() {
        replyComponent = new ArrayList<>();
        for (WebElement current : getReplies()) {
            replyComponent.add(new ReplyComponent(driver, current));
        }
    }
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
                ExpectedConditions.visibilityOfAllElementsLocatedBy(item));
    }

    public ReplyComponent chooseReplyByNumber(int replyNumber) {
        List<ReplyComponent> replies = getReplyComponents();
        if(replyNumber + 1 > replies.size()){
            throw new IllegalArgumentException("Reply number was out of range");
        }else {
            return getReplyComponents().get(replyNumber);
        }
    }

    public ReplyComponent chooseReplyByText(String title) {
        ReplyComponent result = null;
        for (ReplyComponent current : getReplyComponents()) {
            if (current.getReplyText().equals(title)){
                result = current;
            }
        }
        if (result == null) {
            logger.warn("Reply with text " + title + "not exist");
            throw new RuntimeException("Comment Component with text " + title + " not found");
        }
        return result;
    }

    public boolean isReplyComponentPresent(){
        return driver.findElements(REPLY_COMPONENTS.getPath()).size() > 0;
    }

}
