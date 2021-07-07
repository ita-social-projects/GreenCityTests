package com.softserve.edu.greencity.ui.pages.econews;

import com.softserve.edu.greencity.data.CreateNewsUaExpectedText;
import com.softserve.edu.greencity.data.Languages;
import com.softserve.edu.greencity.data.econews.NewsData;
import com.softserve.edu.greencity.ui.elements.ButtonElement;
import com.softserve.edu.greencity.ui.elements.InputElement;
import com.softserve.edu.greencity.ui.elements.LabelElement;
import com.softserve.edu.greencity.ui.elements.TextAreaElement;
import com.softserve.edu.greencity.ui.locators.CreateNewsPageLocators;
import com.softserve.edu.greencity.ui.elements.LabelElement;
import com.softserve.edu.greencity.ui.pages.common.TopPart;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static com.softserve.edu.greencity.ui.locators.CreateNewsPageLocators.*;

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

    private ButtonElement cancelButton;
    private ButtonElement previewButton;
    private ButtonElement publishButton;
    private ButtonElement currentLanguageButton;
    private List<WebElement> languageOptions;
    private LabelElement createNewsMainTitleLabel;
    private LabelElement tagsDescriptionLabel;
    private LabelElement nameTitleLabel;
    private LabelElement tagsTitleLabel;
    private LabelElement pictureTitleLabel;
    private LabelElement sourceTitleLabel;
    private LabelElement contentTitleLabel;
    private LabelElement dateTitleLabel;
    private LabelElement authorTitleLabel;

    private TextAreaElement titleField;
    private List<ButtonElement> tags;
    private InputElement sourceField;
    private TextAreaElement contentField;
    private InputElement uploadImageInput;
    private ButtonElement submitPhotoButton;

    public CreateNewsPage(WebDriver driver) {
        super(driver);
        checkElements();
        init();
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

    @Step("Get title label")
    public LabelElement getTitleLabel() {
        LabelElement lb = new LabelElement(searchElementByXpath(TITLE_LABELS.getPath()));
        return lb;
    }

    @Step("Get tags label")
    public LabelElement getTagsLabel() {
        LabelElement lb = new LabelElement(searchElementByXpath(TAGS_LABELS.getPath()));
        return lb;
    }

    @Step("Get source label")
    public LabelElement getSourceLabel() {
        LabelElement lb = new LabelElement(searchElementByXpath(SOURCE_LABELS.getPath()));
        return lb;
    }

    @Step("Get picture label")
    public LabelElement getPictureLabel() {
        LabelElement lb = new LabelElement(searchElementByXpath(PICTURE_LABELS.getPath()));
        return lb;
    }

    @Step("Get content label")
    public LabelElement getContentLabel() {
        LabelElement lb = new LabelElement(searchElementByXpath(CONTENT_LABELS.getPath()));
        return lb;
    }

    @Step("Get title field")
    public WebElement getTitleField() {
        return searchElementByCss(TITLE_FIELD.getPath());
    }

    @Step("Set title field")
    public CreateNewsPage setTitleField(String text) {
        getTitleField().sendKeys(text);
        return this;
    }

    @Step("Get title field height")
    public int getTitleFieldHeight() {
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
    public int getContentHeight() {
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

    @Step("Get upload area")
    public WebElement getUploadArea() {
        //TODO refactor searchElementByCss
        return driver.findElement(By.xpath("//label[@for='upload']"));
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

//    @Step("Upload file")
//    public CreateNewsPage uploadFile(WebElement dropArea, String path) {
//        String absolutePath = new File(path).getAbsolutePath();
//        UploadFileUtil.DropFile(new File(absolutePath), dropArea, 0, 0);
//        try {
//            driver.findElements(By.cssSelector(".cropper-buttons button")).get(0).click();
//        } catch (IndexOutOfBoundsException e) {
//            e.printStackTrace();
//        }
//        return this;
//    }

    @Step("Get validation header text")
    public String getConfirmationHeaderText() {
        return searchElementByCss(CONFIRMATION_POPUP_HEADER.getPath()).getText();
    }

    @Step("Get validation description text")
    public String getConfirmationDescriptionText() {
        return searchElementByCss(CONFIRMATION_POPUP_DESCRIPTION.getPath()).getText();
    }

    @Step("Upload file")
    public CreateNewsPage uploadFile(WebElement dropArea, String path) {
        String absolutePath = new File(path).getAbsolutePath();
        dropArea.sendKeys(absolutePath);
        try {
            driver.findElements(By.cssSelector(".cropper-buttons button")).get(0).click();
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
        return this;
    }

    @Step("Upload GIF image")
    public CreateNewsPage uploadGIFImage() {
        uploadFile(getUploadArea(), "src/test/resources/images/gifImage.gif");
        return this;
    }

    @Step("Upload PDF file")
    public CreateNewsPage uploadPDFFile() {
        uploadFile(getUploadArea(), "src/test/resources/images/pdfFileFormat.pdf");
        return this;
    }

//    @Step("Upload PDF file")
//    public CreateNewsPage uploadPDFFile(){
//        String path = "src/test/resources/images/PDFFile.pdf";
//        String absolutePath = new File(path).getAbsolutePath();
//        WebElement element = driver.findElement(By.id("upload"));
//        element.sendKeys(absolutePath);
//        return this;
//    }

    @Step("Upload too large image")
    public CreateNewsPage uploadTooLargeImage() {
        uploadFile(getUploadArea(), "src/test/resources/images/tooLargeImage.jpg");
        return this;
    }

    @Step("Upload PNG image")
    public CreateNewsPage uploadPNGImage() {
        uploadFile(getUploadArea(), "src/test/resources/images/pngValidImage.png");
        return this;
    }

    @Step("Upload JPG image")
    public CreateNewsPage uploadJPGImage() {
        uploadFile(getUploadArea(), "src/test/resources/images/jpgValidImage.jpg");
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
            uploadFile(getUploadArea(), newsData.getFilePath());
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
            new WebDriverWait(driver, 30)
                    .until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector("div.container div.people-img"))));
        } catch (Exception e) {
            //TODO handle show error message with new create news
            driver.navigate().refresh();
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

    private void clickTagByName(String tagName) {
        /**
         * Method searches tag element (ButtonElement) with defined name and clicks it
         * @param tagName text in the tag element
         */
        for(ButtonElement buttonElement: tags) {
            if(buttonElement.getInnerText().trim().equals(tagName)) {
                buttonElement.click();
                initTagsButtons();
                break;
            }
        }
    }

    /**
     * Method that resized 'Content' field
     * Get width (x) and height (y) of content field, divided it by 2 because getContentField method return
     * the central coordinates of the element. Minus 2 for navigation exactly on resize element by X and Y coordinate
     *
     * @param resizeDown - the number of pixels on which content field resize
     */
    @Step("Action")
    public CreateNewsPage changeContentFieldSize(int resizeDown) {
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
        private By continueEditingButton = By.cssSelector("button.secondary-global-button");
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

        @Step("Check if continue editting button is displayed")
        public boolean isContinueEditingButtonDisplayed() {
            return getContinueEditingButton().isDisplayed();
        }

        @Step("Get cancel editing button")
        private WebElement getCancelEditingButton() {
            return searchElementByCss(cancelEditingButton);
        }

        @Step("Check if cancel editting button is displayed")
        public boolean isCancelEditingButtonDisplayed() {
            return getCancelEditingButton().isDisplayed();
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

    public CreateNewsPage changeLanguageToUkrainian(){
        return changeLanguageTo(Languages.UKRAINIAN.toString());
    }

    public CreateNewsPage changeLanguageTo(String lang) {
        currentLanguageButton.click();
        languageOptions = driver.findElements(LANGUAGE_OPTIONS_BUTTON.getPath());
        for(WebElement element: languageOptions) {
            ButtonElement currentButton = new ButtonElement(element);
            if(currentButton.getText().equals(lang)){
                currentButton.click();
                break;
            }
        }
        return new CreateNewsPage(driver);
    }

    public void postingNews(String titleText, String[] tags, String contentText, String sourceText, String imagePath){
        titleField.enterText(titleText);
        sourceField.sendKeys(sourceText);
        contentField.enterText(contentText);
        for(String tag: tags) {
            clickTagByName(tag);
        }
        uploadImageInput.sendKeys(imagePath);
        waitsSwitcher.setExplicitWait(10, ExpectedConditions.elementToBeClickable(SUBMIT_PHOTO_BUTTON.getPath()));
        submitPhotoButton = new ButtonElement(driver, SUBMIT_PHOTO_BUTTON);
        submitPhotoButton.click();
        publishButton.click();
    }

    public void init() {
        cancelButton = new ButtonElement(driver, CANCEL_BUTTON);
        previewButton = new ButtonElement(driver, PREVIEW_BUTTON);
        publishButton = new ButtonElement(driver, PUBLISH_BUTTON);
        currentLanguageButton = new ButtonElement(driver, CURRENT_LANGUAGE_BUTTON);
        createNewsMainTitleLabel = new LabelElement(driver, CREATE_NEWS_MAIN_TITLE);
        tagsDescriptionLabel = new LabelElement(driver, TAGS_DESCRIPTION);
        nameTitleLabel = new LabelElement(driver, NAME_TITLE_LABEL);
        tagsTitleLabel = new LabelElement(driver, TAGS_TITLE_LABEL);
        pictureTitleLabel = new LabelElement(driver, PICTURE_TITLE_LABEL);
        sourceTitleLabel = new LabelElement(driver, SOURCE_TITLE_LABEL);
        contentTitleLabel = new LabelElement(driver, CONTENT_TITLE_LABEL);
        dateTitleLabel = new LabelElement(driver, DATE_TITLE_LABEL);
        authorTitleLabel = new LabelElement(driver, AUTHOR_TITLE_LABEL);

        titleField = new TextAreaElement(driver, TITLE_FIELD);
        initTagsButtons();
        sourceField = new InputElement(driver, SOURCE_FIELD);
        contentField = new TextAreaElement(driver, CONTENT_FIELD);
        uploadImageInput = new InputElement(driver, UPLOAD_IMAGE_INPUT);
    }

    private void initTagsButtons() {
        tags = new ArrayList<>();
        for(WebElement buttonElement: driver.findElements(TAGS_BUTTON.getPath())) {
            tags.add(new ButtonElement(buttonElement));
        }
    }

}
