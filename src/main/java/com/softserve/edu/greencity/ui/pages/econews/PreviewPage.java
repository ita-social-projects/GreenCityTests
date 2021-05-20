package com.softserve.edu.greencity.ui.pages.econews;

import com.softserve.edu.greencity.ui.tools.engine.WaitsSwitcher;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;

import com.softserve.edu.greencity.ui.pages.common.TopPart;
import static com.softserve.edu.greencity.ui.locators.PreviewPageLocators.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.*;

/**
 * The page you get to after clicking "Preview" on creating news
 * @author lv-519 Taqc/Java
 */
public class PreviewPage extends TopPart {

    protected WebDriverWait wait;
    private List<WebElement> tagsFields;

    public PreviewPage(WebDriver driver) {
        super(driver);
        // TODO remove from constructor
        checkElements();
    }

    private void checkElements() {
        waitsSwitcher.setExplicitWait(10, ExpectedConditions.visibilityOf(getBackToEditingButton()));
    }

    @Step("Get tags fields")
    public List<WebElement> getTagsFields() {
        tagsFields = driver.findElements(TAGS_FIELDS.getPath());
        return tagsFields;
    }

    @Step("Get title field")
    public WebElement getTitleField() {
        return searchElementByCss(TITLE_FIELD.getPath());
    }

    @Step("Get title field text")
    public String getTitleFieldText() {
        return getTitleField().getText();
    }

    @Step("Get date field")
    public WebElement getDateField() {
        return searchElementByCss(DATE_FIELD.getPath());
    }

    @Step("Get date field text")
    public String getDateFieldText() {
        int retriesLeft = 5;
        do {
            if (!getDateField().getText().contains("Date:")) { //The page displays a placeholder for milliseconds
                return getDateField().getText();
            }
            else {
                WaitsSwitcher.sleep(100);
                retriesLeft--;
            }
        } while (retriesLeft > 0);
        return getDateField().getText();
    }

    @Step("Get author field")
    public WebElement getAuthorField() {
        return searchElementByCss(AUTHOR_FIELD.getPath());
    }

    @Step("Get author field text")
    public String getAuthorFieldText() {
        return getAuthorField().getText();
    }

    @Step("Get content field")
    public WebElement getContentField() {
        return searchElementByCss(CONTENT_FIELD.getPath());
    }

    @Step("Get content filed text")
    public String getContentFieldText() {
        return getContentField().getText();
    }

    @Step("Get twitter image link")
    public WebElement getImgTwitterLink() {
        return searchElementByXpath(IMG_TWITTER_LINK.getPath());
    }

    @Step("Click on twitter image link")
    public void clickImgTwitterLink() {
        getImgTwitterLink().click();
    }

    @Step("Get LinkedIn image link")
    public WebElement getImgLinkedInLink() {
        return searchElementByXpath(IMG_LINKEDIN_LINK.getPath());
    }

    @Step("Click on LinkedIn image link")
    public void clickImgLinkedInLink() {
        getImgLinkedInLink().click();
    }

    @Step("Get FaceBook image link")
    public WebElement getImgFacebookLink() {
        return searchElementByXpath(IMG_FACEBOOK_LINK.getPath());
    }

    @Step("Click on FaceBook image ling")
    public void clickImgFacebookLink() {
        getImgFacebookLink().click();
    }

    @Step("Get back to editing button")
    public WebElement getBackToEditingButton() {
        return searchElementByCss(BACK_TO_EDITING_BUTTON.getPath());
    }

    @Step("Check if back editing button is displayed")
    public boolean isBackToEditingButtonDisplayed() {
        return getBackToEditingButton().isDisplayed();
    }

    @Step("Click on back editing button")
    public void clickBackToEditingButton() {
        getBackToEditingButton().click();
    }

    @Step("Get publish button")
    public WebElement getPublishButton() {
        return searchElementByCss(PUBLISH_BUTTON.getPath());
    }

    @Step("Check if publish button is present")
    public boolean isPublishButtonPresent() {
        try {
            return getPublishButton().isEnabled();
        }
        catch (TimeoutException er) {
            return false;
        }
    }

    @Step("Click on publish button")
    private void clickPublishButton() {
        if (isPublishButtonPresent()) {
            getPublishButton().click();
        } else {
            logger.info("Element Publish button does not exist.");
        }
    }

    /**
     * Get sorted list of Strings with tags names
     *
     * @return List<String>
     */
    @Step("Get tags names")
    public List<String> getTagsNames() {
        List<String> tagsNames = new ArrayList<>();
        for (WebElement current : getTagsFields()) {
            tagsNames.add(current.getText().toLowerCase());
        }
        Collections.sort(tagsNames);
        return tagsNames;
    }

    /**
     * Method to back to CreateNewsPage from PreviewPage
     *
     * @return CreateNewsPage
     */
    @Step("Go back to create news page")
    public CreateNewsPage backToCreateNewsPage() {
        clickBackToEditingButton();
        return new CreateNewsPage(driver);
    }

    /**
     * Method to publish news
     *
     * @return EcoNewsPage
     */
    @Step("Publish news")
    public EcoNewsPage publishNews() {
        clickPublishButton();
        navigateMenuEcoNews();
        return new EcoNewsPage(driver);
    }
}
