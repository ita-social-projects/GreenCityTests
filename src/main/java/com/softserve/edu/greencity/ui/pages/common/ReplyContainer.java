package com.softserve.edu.greencity.ui.pages.common;

import com.softserve.edu.greencity.ui.data.econews.NewsData;
import com.softserve.edu.greencity.ui.pages.econews.ItemComponent;
import com.softserve.edu.greencity.ui.pages.econews.SingleNewsPage;
import com.softserve.edu.greencity.ui.tools.engine.WaitsSwitcher;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

import static com.softserve.edu.greencity.ui.locators.CommentComponentLocators.REPLY_COMPONENTS;

public class ReplyContainer {

    private WebDriver driver;
    private List<ReplyComponent> replyComponent;
    private By item = REPLY_COMPONENTS.getPath();

    public ReplyContainer(WebDriver driver){
        this.driver = driver;
        checkElements();
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

    public ReplyComponent chooseReplyByNumber(int number) {
        return getReplyComponents().get(number);
    }




}
