package com.softserve.edu.greencity.ui.pages.common;

import com.softserve.edu.greencity.ui.tools.engine.WaitsSwitcher;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.softserve.edu.greencity.ui.locators.CommentComponentLocators.*;

public class ReplyComponent extends CommentComponent  {

    /*private WebElement replyItem;
    protected WebDriverWait wait;
    private final WebDriver driver;
    private WaitsSwitcher waitsSwitcher;*/

    public ReplyComponent(WebDriver driver, WebElement replyItem){
        super(driver, replyItem);
        /*this.driver = driver;
        this.replyItem = replyItem;
        this.waitsSwitcher = new WaitsSwitcher(driver);*/
    }

   /* public WebElement getComment(){
        return replyItem.findElement(COMMENT_TEXT.getPath());
    }

    public String getCommentText(){
        return getComment().getText();
    }*/





}
