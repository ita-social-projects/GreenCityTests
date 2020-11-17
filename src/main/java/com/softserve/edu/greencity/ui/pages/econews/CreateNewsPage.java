package com.softserve.edu.greencity.ui.pages.econews;

import com.softserve.edu.greencity.ui.data.econews.NewsData;
import com.softserve.edu.greencity.ui.pages.common.TopPart;
import com.softserve.edu.greencity.ui.tools.UploadFileUtil;
import static com.softserve.edu.greencity.ui.locators.CreateNewsPageLocators.*;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.List;

/**
 * A class that handles this page: https://ita-social-projects.github.io/GreenCityClient/#/news/create-news
 * (available only for logged in users)
 */
public class CreateNewsPage extends TopPart {

    protected WebDriverWait wait;
    protected RemoteWebDriver remoteWebDriver;

    private final String VALUE_ATTRIBUTE = "value";
    private final String CLASS_ATTRIBUTE = "class";
    private TagsComponent tagsComponent;

    public CreateNewsPage(WebDriver driver) {
        super(driver);
        checkElements();
    }

    private void checkElements() {
        tagsComponent = new TagsComponent(driver);
    }

    @Step("Get tags components")
    public TagsComponent getTagsComponent() {
        tagsComponent = new TagsComponent(driver);
        return tagsComponent;
    }

    @Step("Get create news main title text")
    public String getCreateNewsMainTitleText() {
        return searchElementByCss(CREATE_NEWS_MAIN_TITLE.getPath()).getText();
    }

    @Step("Get title field")
    private WebElement getTitleField() {
        return searchElementByCss(TITLE_FIELD.getPath());
    }

    @Step("Set title field")
    public CreateNewsPage setTitleField(String text) {
        getTitleField().sendKeys(text);
        return  this;
    }

    @Step("Get title field height")
    public int getTitleFieldHeight(){
        return getTitleField().getSize().getHeight();
    }

    @Step("Get title field width")
    public int getTitleFieldWidth() {
        return getTitleField().getSize().getWidth();
    }

    @Step("Get title field text")
    public String getTitleFieldText() {
        return getTitleField().getText();
    }

    @Step("Get title field value")
    public String getTitleFieldValue() {
        return getTitleField().getAttribute(VALUE_ATTRIBUTE);
    }

    @Step("Clear title field")
    public void clearTitleField() {
        getTitleField().clear();
    }

    @Step("Click on title field")
    public void clickTitleField() {
        getTitleField().click();
    }

    @Step("Get source field")
    public WebElement getSourceField() {
        return searchElementByCss(SOURCE_FIELD.getPath());
    }

    @Step("Set source field")
    public void setSourceField(String text) {
        getSourceField().sendKeys(text);
    }

    @Step("Get source field text")
    public String getSourceFieldText() {
        return getSourceField().getText();
    }

    @Step("Get source field value")
    public String getSourceFieldValue() {
        return getSourceField().getAttribute(VALUE_ATTRIBUTE);
    }

    @Step("Clear source field")
    public void clearSourceField() {
        getSourceField().clear();
    }

    @Step("Click on source field")
    public void clickSourceField() {
        getSourceField().click();
    }

    @Step("Get content field")
    public WebElement getContentField() {
        return searchElementByCss(CONTENT_FIELD.getPath());
    }

    @Step("Set content field")
    public CreateNewsPage setContentField(String text) {
        getContentField().sendKeys(text);
        return this;
    }

    @Step("Get content field text")
    public String getContentFieldText() {
        return getContentField().getText();
    }

    @Step("Get content field height")
    public int getContentHeight(){
        return getContentField().getSize().getHeight();
    }

    @Step("Get content field width")
    public int getContentWidth() {
        return getContentField().getSize().getWidth();
    }

    @Step("Get content field value")
    public String getContentFieldValue() {
        return getContentField().getAttribute(VALUE_ATTRIBUTE);
    }

    @Step("Clear content field")
    public void clearContentField() {
        getContentField().clear();
    }

    @Step("Click on content field")
    public void clickContentField() {
        getContentField().click();
    }

    @Step("Get date field")
    public WebElement getDateField() {
        return searchElementByCss(DATE_FIELD.getPath());
    }

    @Step("Get date field text")
    public String getDateFieldText() {
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

    @Step("Get cancel button")
    public WebElement getCancelButton() {
        return searchElementByCss(CANCEL_BUTTON.getPath());
    }

    @Step("Click cancel button")
    public CancelFrame clickCancelButton() {
        getCancelButton().click();
        return new CancelFrame(driver);
    }

    @Step("Get preview button")
    public WebElement getPreviewButton() {
        return searchElementByCss(PREVIEW_BUTTON.getPath());
    }

    @Step("Click on preview button")
    public void clickPreviewButton() {
        getPreviewButton().click();
    }

    @Step("Get publish button")
    public WebElement getPublishButton() {
        return searchElementByCss(PUBLISH_BUTTON.getPath());
    }

    @Step("Click on publish button")
    public void clickPublishButton() {
        getPublishButton().click();
    }

    @Step("Check if publish button is clickable")
    public boolean isPublishButtonClickable() {
        return getPublishButton().isEnabled();
    }

    @Step("Check if publish button is displayed")
    public boolean isPublishButtonDisplayed() {
        return getPublishButton().isDisplayed();
    }

    @Step("Get drop area")
    public WebElement getDropArea() {
        return searchElementByCss(DROP_AREA.getPath());
    }

    @Step("Check if picture is uploaded")
    public Boolean isPictureUploaded() {
        return getDropArea().getAttribute(CLASS_ATTRIBUTE).contains("ng-star-inserted");
    }

    @Step("Get title description")
    public WebElement getTitleDescription() {
        return searchElementByCss(TITLE_DESCRIPTION.getPath());
    }

    @Step("Is title description warning")
    public boolean isTitleDescriptionWarning() {
        return getTitleField().getAttribute(CLASS_ATTRIBUTE).contains("invalid");
    }

    @Step("Get source description")
    public WebElement getSourceDescription() {
        return searchElementByCss(SOURCE_DESCRIPTION.getPath());
    }

    @Step("Is source description warning")
    public boolean isSourceDescriptionWarning() {
        return getSourceDescription().getAttribute(CLASS_ATTRIBUTE).contains("warning");
    }

    @Step("Get content description")
    public WebElement getContentDescription() {
        return searchElementByCss(CONTENT_DESCRIPTION.getPath());
    }

    @Step("Get content description text")
    public String getContentErrorText() {
        return searchElementByXpath(CONTENT_ERROR.getPath()).getText();
    }

    @Step("Is content description warning")
    public boolean isContentDescriptionWarning() {
        return getContentField().getAttribute(CLASS_ATTRIBUTE).contains("invalid");
    }

    @Step("Get picture description")
    public WebElement getPictureDescription() {
        return searchElementByXpath(PICTURE_DESCRIPTION.getPath());
    }

    @Step("Is picture description warning")
    public boolean isPictureDescriptionWarning() {
        return getPictureDescription().getAttribute(CLASS_ATTRIBUTE).contains("warning-color");
    }

    @Step("Get tags description")
    public WebElement getTagsDescription() {
        return searchElementByCss(TAGS_DESCRIPTION.getPath());
    }

    @Step("Is tags description warning")
    public boolean isTagsDescriptionWarning() {
        return getTagsDescription().getAttribute(CLASS_ATTRIBUTE).contains("warning");
    }

    @Step("Get invalid source error text")
    public String getInvalidSourceErrorText() {
        return searchElementByXpath(INVALID_SOURCE_ERROR.getPath()).getText();
    }

    @Step("Get tags error")
    public WebElement getTagsError() {
        return searchElementByXpath(TAGS_ERROR.getPath());
    }

    @Step("Check if tags error is displayed")
    public boolean isTagsErrorDisplayed() {
        return getTagsError().isDisplayed();
    }

    @Step("Get tags error text")
    public String getTagsErrorText() {
        return getTagsError().getText();
    }

    @Step("Get invalid image error text")
    public String getInvalidImageErrorText() {
        return searchElementByCss(INVALID_IMAGE_ERROR.getPath()).getText();
    }

    @Step("Upload file")
    public CreateNewsPage uploadFile(WebElement dropArea, String path) {
        String absolutePath = new File(path).getAbsolutePath();
        UploadFileUtil.DropFile(new File(absolutePath), dropArea, 0, 0);
        try {
            driver.findElements(By.cssSelector(".cropper-buttons button")).get(0).click();
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
        return this;
    }

    @Step("Upload GIF image")
    public CreateNewsPage uploadGIFImage() {
        uploadFile(getDropArea(),"src/test/resources/images/gifImage.gif");
        return this;
    }

    @Step("Upload too large image")
    public CreateNewsPage uploadTooLargeImage() {
        uploadFile(getDropArea(), "src/test/resources/images/tooLargeImage.jpg");
        return this;
    }

    @Step("Upload PNG image")
    public CreateNewsPage uploadPNGImage() {
        uploadFile(getDropArea(), "src/test/resources/images/pngValidImage.png");
        return this;
    }

    @Step("Upload JPG image")
    public CreateNewsPage uploadJPGImage() {
        uploadFile(getDropArea(), "src/test/resources/images/jpgValidImage.jpg");
        return this;
    }

    /**
     * Method to fill all fields in CreateNewsPage or only required
     *
     * @param newsData
     * @return CreateNewsPage
     */
    @Step("Fill all fields")
    public CreateNewsPage fillFields(NewsData newsData) {
        clearTitleField();
        setTitleField(newsData.getTitle());
        clearContentField();
        setContentField(newsData.getContent());
        if (!newsData.getSource().equals("")) {
            clearSourceField();
            setSourceField(newsData.getSource());
        }
        if (!newsData.getFilePath().equals("")) {
            uploadFile(getDropArea(), newsData.getFilePath());
        }
        tagsComponent.selectTags(newsData.getTags());
        return this;
    }

    /**
     * Method to get list of selected tags names from TagsComponent
     *
     * @return List<String>
     */
    @Step("Get selected tags names")
    public List<String> getSelectedTagsNames() {
        return tagsComponent.getTagsNames(tagsComponent.getSelectedTagsButtons());
    }

    /**
     * Method to open PreviewPage
     *
     * @return PreviewPage
     */
    @Step("Go to pre view page")
    public PreviewPage goToPreViewPage() {
        clickPreviewButton();
        return new PreviewPage(driver);
    }

    /**
     * Method to Publish news
     *
     * @return EcoNewsPage
     */
    @Step("Publish news")
    public EcoNewsPage publishNews() {
        scrollToElement(getPublishButton());
        clickPublishButton();
        try {
            new WebDriverWait(driver, 20)
                    .until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector("div.container div.people-img"))));
        } catch (Exception e) {
            navigateMenuEcoNews();
        }
        return navigateMenuEcoNews();
    }

    /**
     * Method to cancel news creation
     * by clicking Cancel button in IFrame
     *
     * @return EcoNewsPage
     */
    @Step("Cancel news creating")
    public EcoNewsPage cancelNewsCreating() {
        clickCancelButton();
        CancelFrame cancelFrame = new CancelFrame(driver);
        return cancelFrame.clickCancelEditingButton();
    }

    /**
     * Method to continue news creation after clicking Cancel button
     * by clicking ContinueEditing button in IFrame
     *
     * @return CreateNewsPage
     */
    @Step("Continue news creating")
    public CreateNewsPage continueNewsCreating() {
        clickCancelButton();
        CancelFrame cancelFrame = new CancelFrame(driver);
        return cancelFrame.clickContinueEditingButton();
    }

    @Step("Scroll to element")
    private void scrollToElement(WebElement element) {
        Actions action = new Actions(driver);
        action.moveToElement(element).perform();
    }

    /**
     * Method that resized 'Content' field
     * Get width (x) and height (y) of content field, divided it by 2 because getContentField method return
     * the central coordinates of the element. Minus 2 for navigation exactly on resize element by X and Y coordinate
     * @param resizeDown - the number of pixels on which content field resize
     */
    @Step("Action")
    public CreateNewsPage changeContentFieldSize(int resizeDown){
        Actions action = new Actions(driver);
        action.moveToElement(getCopyright()).perform();
        int x = getContentWidth() / 2 - 2;
        int y = getContentHeight() / 2 - 2;
        action.moveToElement(getContentField(), x, y)
                .clickAndHold().moveByOffset(0, resizeDown).release().perform();
        return this;
    }

    /**
     * CancelFrame class
     * Nested class that appears after clicking on Cancel button
     */
    public class CancelFrame {

        protected WebDriverWait wait;
        private By continueEditingButton = By.cssSelector("div.continue-btn > button");
        private By cancelEditingButton = By.cssSelector("button.primary-global-button");

        public CancelFrame(WebDriver driver) {
            checkElements(driver);
        }

        private void checkElements(WebDriver driver) {
            waitsSwitcher.setExplicitWait(10, ExpectedConditions.visibilityOf(getContinueEditingButton()));
            waitsSwitcher.setExplicitWait(10, ExpectedConditions.visibilityOf(getCancelEditingButton()));
        }

        @Step("Get continue editing button")
        private WebElement getContinueEditingButton() {
            return searchElementByCss(continueEditingButton);
        }

        @Step("Get cancel editing button")
        private WebElement getCancelEditingButton() {
            return searchElementByCss(cancelEditingButton);
        }

        /**
         * Method to continue news creation after clicking Continue editing button
         *
         * @return CreateNewsPage
         */
        @Step("Click on continue editing button")
        public CreateNewsPage clickContinueEditingButton() {
            getContinueEditingButton().click();
            return new CreateNewsPage(driver);
        }

        /**
         * Method to cancel news creation after clicking "Yes, cancel" button
         *
         * @return EcoNewsPage
         */
        @Step("Click on cancel editing button")
        public EcoNewsPage clickCancelEditingButton() {
            getCancelEditingButton().click();
            return new EcoNewsPage(driver);
        }
    }
}
