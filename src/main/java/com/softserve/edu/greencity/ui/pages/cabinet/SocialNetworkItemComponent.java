package com.softserve.edu.greencity.ui.pages.cabinet;

import com.softserve.edu.greencity.ui.elements.IconElement;
import com.softserve.edu.greencity.ui.tools.engine.WaitsSwitcher;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;

import static com.softserve.edu.greencity.ui.locators.MyHabitLocators.SOCIAL_MEDIA_ICON;

/**
 * Any one of social network icons displayed at 'My Habit' page.
 * Handled by SocialNetworkItemContainer.java
 */
public class SocialNetworkItemComponent {

    private final WebDriver driver;
    private final WebElement iconItem;
    private WaitsSwitcher waitsSwitcher;
    private Logger logger;
    private IconElement socialIcon;
    private final String CLASS_ATTRIBUTE = "class";

    public SocialNetworkItemComponent(WebDriver driver, WebElement iconItem) {
        this.driver = driver;
        this.iconItem = iconItem;
        waitsSwitcher = new WaitsSwitcher(driver);
    }

    private IconElement getSocialIconElement(){
        socialIcon = new IconElement(driver, SOCIAL_MEDIA_ICON);
        return socialIcon;
    }

    public boolean isSocialIconPresent(){
        try {
            return getSocialIconElement().isDisplayed();
        }
        catch (TimeoutException er) {
            return false;
        }
    }

    public boolean isSocialIconClickable(){
        try {
            waitsSwitcher.setExplicitWait(ExpectedConditions
                    .elementToBeClickable(SOCIAL_MEDIA_ICON.getPath()));
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public void clickSocialIconElement(){
        getSocialIconElement().click();
    }

    public boolean isIconContainsAttribute(String attribute){
        return getSocialIconElement().getAttribute(CLASS_ATTRIBUTE).contains(attribute);
    }
}
