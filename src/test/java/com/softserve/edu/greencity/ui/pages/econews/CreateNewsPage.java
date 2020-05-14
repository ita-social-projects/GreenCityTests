package com.softserve.edu.greencity.ui.pages.econews;

import com.softserve.edu.greencity.ui.data.econews.NewsData;
import com.softserve.edu.greencity.ui.pages.common.TopPart;
import com.softserve.edu.greencity.ui.tools.UploadFileUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.util.List;

/**
 * CreateNewsPage class
 *
 * @author lv-493 Taqc/Java
 */
public class CreateNewsPage extends TopPart {
    private final String VALUE_ATTRIBUTE = "value";
    private final String CLASS_ATTRIBUTE = "class";

    private TagsComponent tagsComponent;

    private WebElement titleField;
    private WebElement sourceField;
    private WebElement contentField;
    private WebElement dateField;
    private WebElement authorField;
    private WebElement cancelButton;
    private WebElement previewButton;
    private WebElement publishButton;
    private WebElement dropArea;

    //description fields
    private WebElement titleDescription;
    private WebElement tagsDescription;
    private WebElement sourceDescription;
    private WebElement contentDescription;
    private WebElement pictureDescription;

    /**
     * Constructor CreateNewsPage
     *
     * @param driver
     */
    public CreateNewsPage(WebDriver driver) {
        super(driver);
        initElements();
    }

    private void initElements() {
        // init main elements
        tagsComponent = new TagsComponent(driver);

        titleField = driver.findElement(By.cssSelector("input[formcontrolname='title']"));
        sourceField = driver.findElement(By.cssSelector("div[formarrayname='tags']+label > input"));
        contentField = driver.findElement(By.cssSelector("div.textarea-wrapper > textarea"));
        dateField = driver.findElement(By.cssSelector("div.date > p:first-child > span"));
        authorField = driver.findElement(By.cssSelector("div.date > :nth-child(2n) > span"));
        dropArea = driver.findElement(By.cssSelector("div.text-wrapper, div.ng-star-inserted > img"));
        cancelButton = driver.findElement(By.cssSelector("div.submit-buttons > :first-child"));
        previewButton = driver.findElement(By.cssSelector("div.submit-buttons > :first-child+button"));
        publishButton = driver.findElement(By.cssSelector("div.submit-buttons > button[type='submit']"));

        // init description fields
        titleDescription = driver.findElement(By.cssSelector("input[formcontrolname='title'] + span"));
        tagsDescription = driver.findElement(By.cssSelector("div.tags > button + p"));
        sourceDescription = driver.findElement(By.cssSelector("div[formarrayname='tags']+label > input + span"));
        contentDescription = driver.findElement(By.cssSelector("p.textarea-description"));
        pictureDescription = driver.findElement(By.xpath("//div[@class = 'text-wrapper']/../../div/../span | //div[@class = 'ng-star-inserted']/../span"));
    }

    // Page Object

    public TagsComponent getTagsComponent() {
        return tagsComponent;
    }

    //titleField
    private WebElement getTitleField() {
        return titleField;
    }

    public void setTitleField(String text) {
        getTitleField().sendKeys(text);
    }

    public String getTitleFieldText() {
        return getTitleField().getText();
    }

    public String getTitleFieldValue() {
        return getTitleField().getAttribute(VALUE_ATTRIBUTE);
    }

    public void clearTitleField() {
        getTitleField().clear();
    }

    public void clickTitleField() {
        getTitleField().click();
    }

    //dropArea
    public WebElement getDropArea() {
        return dropArea;
    }

    public Boolean isPictureUploaded() {
        return getDropArea().getAttribute(CLASS_ATTRIBUTE).contains("ng-star-inserted");
    }

    //sourceField
    public WebElement getSourceField() {
        return sourceField;
    }

    public void setSourceField(String text) {
        getSourceField().sendKeys(text);
    }

    public String getSourceFieldText() {
        return getSourceField().getText();
    }

    public String getSourceFieldValue() {
        return getSourceField().getAttribute(VALUE_ATTRIBUTE);
    }

    public void clearSourceField() {
        getSourceField().clear();
    }

    public void clickSourceField() {
        getSourceField().click();
    }

    //contentField
    public WebElement getContentField() {
        return contentField;
    }

    public void setContentField(String text) {
        getContentField().sendKeys(text);
    }

    public String getContentFieldText() {
        return getContentField().getText();
    }

    public String getContentFieldValue() {
        return getContentField().getAttribute(VALUE_ATTRIBUTE);
    }

    public void clearContentField() {
        getContentField().clear();
    }

    public void clickContentField() {
        getContentField().click();
    }

    //dateField
    public WebElement getDateField() {
        return dateField;
    }

    public String getDateFieldText() {
        return getDateField().getText();
    }

    //authorField
    public WebElement getAuthorField() {
        return authorField;
    }

    public String getAuthorFieldText() {
        return getAuthorField().getText();
    }

    //cancelButton;
    public WebElement getCancelButton() {
        return cancelButton;
    }

    public void clickCancelButton() {
        getCancelButton().click();
    }

    //previewButton

    public WebElement getPreviewButton() {
        return previewButton;
    }

    public void clickPreviewButton() {
        getPreviewButton().click();
    }

    //publishButton

    public WebElement getPublishButton() {
        return publishButton;
    }

    public void clickPublishButton() {
        getPublishButton().click();
    }

    public boolean isPublishButtonClickable() {
        return getPublishButton().isEnabled();
    }

    // titleDescription and warning
    public WebElement getTitleDescription() {
        return titleDescription;
    }

    public boolean isTitleDescriptionWarning() {
        return getTitleField().getAttribute(CLASS_ATTRIBUTE).contains("invalid");
    }

    // sourceDescription and warning
    public WebElement getSourceDescription() {
        return sourceDescription;
    }

    public boolean isSourceDescriptionWarning() {
        return getSourceDescription().getAttribute(CLASS_ATTRIBUTE).contains("warning");
    }

    // contentDescription and warning
    public WebElement getContentDescription() {
        return contentDescription;
    }

    public boolean isContentDescriptionWarning() {
        return getContentField().getAttribute(CLASS_ATTRIBUTE).contains("invalid");
    }

    // pictureDescription and warning
    public WebElement getPictureDescription() {
        return pictureDescription;
    }

    public boolean isPictureDescriptionWarning() {
        return getPictureDescription().getAttribute(CLASS_ATTRIBUTE).contains("warning-color");
    }

    // tagsDescription and warning
    public WebElement getTagsDescription() {
        return tagsDescription;
    }

    public boolean isTagsDescriptionWarning() {
        return getTagsDescription().getAttribute(CLASS_ATTRIBUTE).contains("warning");
    }

    // Functional

    // Drop and upload file
    private CreateNewsPage uploadFile(WebElement dropArea, String path) {
        String absolutePath = new File(path).getAbsolutePath();
        UploadFileUtil.DropFile(new File(absolutePath), dropArea, 0, 0);
        return this;
    }

    /**
     * Method to fill all fields in CreateNewsPage
     *
     * @param newsData
     * @return CreateNewsPage
     */
    public CreateNewsPage fillAllNewsFields(NewsData newsData) {
        clearTitleField();
        setTitleField(newsData.getTitle());
        clearContentField();
        setContentField(newsData.getContent());
        clearSourceField();
        setSourceField(newsData.getSource());
        uploadFile(dropArea, newsData.getFilePath());
        tagsComponent.selectTags(newsData.getTags());
        return this;
    }

    /**
     * Method to fill Required fields in CreateNewsPage
     *
     * @param newsData
     * @return CreateNewsPage
     */
    public CreateNewsPage fillRequiredFields(NewsData newsData) {
        clearTitleField();
        setTitleField(newsData.getTitle());
        clearContentField();
        setContentField(newsData.getContent());
        tagsComponent.selectTags(newsData.getTags());
        return this;
    }

    // Business Logic

    /**
     * Method to get list of selected tags names from TagsComponent
     *
     * @return List<String>
     */
    public List<String> getSelectedTagsNames() {
        return tagsComponent.getTagsNames(tagsComponent.getSelectedTagsButtons());
    }

    /**
     * Method to open PreViewPage
     *
     * @return PreViewPage
     */
    public PreViewPage goToPreViewPage() {
        clickPreviewButton();
        return new PreViewPage(driver);
    }

    /**
     * Method to Publish news
     *
     * @return EconewsPage
     */
    public CreateNewsPage publishNews() {  //FIXME return type should be changed to EconewsPage
        clickPublishButton(); // Button doesn't work
        return this;
        //return new EconewsPage(driver);
    }

    /**
     * Method to cancel news creation
     * by clicking Cancel button in IFrame
     *
     * @return EconewsPage
     */
    public EconewsPage cancelNewsCreating() {
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
    public CreateNewsPage continueNewsCreating() {
        clickCancelButton();
        CancelFrame cancelFrame = new CancelFrame(driver);
        return cancelFrame.clickContinueEditingButton();
    }

    /**
     * CancelFrame class
     * Nested class that appears after clicking on Cancel button
     */
    private class CancelFrame {

        private WebElement continueEditingButton;
        private WebElement cancelEditingButton;

        /**
         * Constructor CancelFrame
         *
         * @param driver
         */
        public CancelFrame(WebDriver driver) {
            initElements(driver);
        }

        private void initElements(WebDriver driver) {
            continueEditingButton = driver.findElement(By.cssSelector("div.continue-btn > button"));
            cancelEditingButton = driver.findElement(By.cssSelector("button.primary-global-button"));
        }

        private WebElement getContinueEditingButton() {
            return continueEditingButton;
        }

        private WebElement getCancelEditingButton() {
            return cancelEditingButton;
        }

        /**
         * Method to continue news creation after clicking Continue editing button
         *
         * @return CreateNewsPage
         */
        public CreateNewsPage clickContinueEditingButton() {
            getContinueEditingButton().click();
            return new CreateNewsPage(driver);
        }

        /**
         * Method to cancel news creation after clicking "Yes, cancel" button
         *
         * @return EconewsPage
         */
        public EconewsPage clickCancelEditingButton() {
            getCancelEditingButton().click();
            return new EconewsPage(driver);
        }
    }

}