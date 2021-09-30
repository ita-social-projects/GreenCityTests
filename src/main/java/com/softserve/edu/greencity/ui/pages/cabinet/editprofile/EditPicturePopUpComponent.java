package com.softserve.edu.greencity.ui.pages.cabinet.editprofile;

import com.softserve.edu.greencity.ui.elements.ButtonElement;
import com.softserve.edu.greencity.ui.tools.engine.WaitsSwitcher;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;

import static com.softserve.edu.greencity.ui.locators.EditProfileLocators.*;

/**
 * This class represents pop up which is displayed when user click
 * 'Edit Picture' button on 'Edit Profile' page.
 */
public class EditPicturePopUpComponent {

    private WebDriver driver;
    private WaitsSwitcher waitsSwitcher;

    private ButtonElement titleInPopUpEditPicture;
    private ButtonElement titleInPopUpDeletePhoto;
    private ButtonElement cancelButton;

    private ButtonElement deletePhotoButton;
    private ButtonElement continueEditingButtonInDeletePhotoPopUp;
    private ButtonElement yesDeleteButtonInDeletePhotoPopUp;

    private ButtonElement savePhotoButton;
    private ButtonElement uploadNewPhotoButton;
    private ButtonElement closePopUpEditPhotoButton;
    private ButtonElement editPictureButton;
    private ButtonElement errorTextWhenInvalidImage;

    public EditPicturePopUpComponent(WebDriver driver) {
        this.driver = driver;
        this.waitsSwitcher = new WaitsSwitcher(driver);
    }


//    @Step("Click 'Edit Photo' button")
//    public EditPicturePopUpComponent clickEditPhotoButton(){
//        getEditPictureButton().click();
//        return new EditPicturePopUpComponent(driver);
//    }
//
//    @Step("Get edit picture button")
//    public ButtonElement getEditPictureButton(){
//        if(editPictureButton == null){
//            editPictureButton = new ButtonElement(driver, EDIT_AVATAR_BUTTON);
//        }
//        return editPictureButton;
//    }

    public ButtonElement getTitleInPopUpEditPicture() {
        if (titleInPopUpEditPicture == null) {
            titleInPopUpEditPicture = new ButtonElement(driver, TITLE_POP_UP_IN_EDIT_PICTURE);
        }
        return titleInPopUpEditPicture;
    }

    public ButtonElement getCancelButton(){
        if(cancelButton == null){
            cancelButton = new ButtonElement(driver, CANCEL_ADDING_PHOTO_BUTTON);
        }
        return cancelButton;
    }

    public ButtonElement getDeletePhotoButton(){
        waitsSwitcher.setExplicitWait(10,
                ExpectedConditions.visibilityOfElementLocated(DELETE_PHOTO_BUTTON.getPath()));
        if(deletePhotoButton == null){
            deletePhotoButton = new ButtonElement(driver, DELETE_PHOTO_BUTTON);
        }
        return deletePhotoButton;
    }

    public ButtonElement getTitleInPopUpInDeletePhoto() {
        if (titleInPopUpDeletePhoto == null) {
            titleInPopUpDeletePhoto = new ButtonElement(driver, VERIFY_DELETING_TEXT);
        }
        return titleInPopUpDeletePhoto;
    }

    public ButtonElement getContinueEditingButtonInDeletePhotoPopUp() {
        if (continueEditingButtonInDeletePhotoPopUp == null) {
            continueEditingButtonInDeletePhotoPopUp = new ButtonElement(driver, VERIFY_DELETING_CONTINUE_EDITING_BUTTON);
        }
        return continueEditingButtonInDeletePhotoPopUp;
    }

    public ButtonElement getYesDeleteButtonInDeletePhotoPopUp() {
        if (yesDeleteButtonInDeletePhotoPopUp == null) {
            yesDeleteButtonInDeletePhotoPopUp = new ButtonElement(driver, VERIFY_DELETING_YES_BUTTON);
        }
        return yesDeleteButtonInDeletePhotoPopUp;
    }

    public ButtonElement getUploadNewPhotoButton() {
        if (uploadNewPhotoButton == null) {
            uploadNewPhotoButton = new ButtonElement(driver, UPLOAD_NEW_PHOTO_BUTTON);
        }
        return uploadNewPhotoButton;
    }

    public ButtonElement getClosePopUpEditPhotoButton(){
        WaitsSwitcher.sleep(2000);
        if(closePopUpEditPhotoButton == null){
            closePopUpEditPhotoButton = new ButtonElement(driver, CLOSE_POP_UP_EDIT_PHOTO_BUTTON);
        }
        return closePopUpEditPhotoButton;
    }

    public EditProfilePage clickCancelButton(){
        getCancelButton().click();
        return new EditProfilePage(driver);
    }

    public EditPicturePopUpComponent clickDeletePhotoButton() {
        getDeletePhotoButton().click();
        waitsSwitcher.setExplicitWait(10,
                ExpectedConditions.invisibilityOfElementLocated(DELETE_PHOTO_BUTTON.getPath()));
        return new EditPicturePopUpComponent(driver);
    }

    public EditPicturePopUpComponent clickUploadNewPhotoButton() {
        getUploadNewPhotoButton().click();
        return new EditPicturePopUpComponent(driver);
    }

    public EditProfilePage clickClosePopUpButton() {
        getClosePopUpEditPhotoButton().click();
        return new EditProfilePage(driver);
    }

    public EditProfilePage clickContinueEditingButtonInDeletePhotoPopUp() {
        getContinueEditingButtonInDeletePhotoPopUp().click();
        return new EditProfilePage(driver);
    }

    public EditProfilePage clickYesDeleteButtonInDeletePhotoPopUp() {
        getYesDeleteButtonInDeletePhotoPopUp().click();
        WaitsSwitcher.sleep(2000);
        return new EditProfilePage(driver);
    }

    //Upload different types of files

    public ButtonElement getSavePhotoButton() {
        if (savePhotoButton == null) {
            savePhotoButton = new ButtonElement(driver, SAVE_PHOTO_BUTTON);
        }
        return savePhotoButton;
    }

    public EditProfilePage clickSavePhotoButton() {
        getSavePhotoButton().click();
        waitsSwitcher.setExplicitWait(5,ExpectedConditions.invisibilityOfElementLocated(SAVE_PHOTO_BUTTON.getPath()));
        return new EditProfilePage(driver);
    }


//    public EditPicturePopUpComponent browseImage(String img){
//        if (browseButton == null) {
//            browseButton = new ButtonElement(this.driver, UPLOAD_NEW_PHOTO_BUTTON);
//        }
//        browseButton.sendKeys(img);
//        return this;
//    }

//    @Step("Get upload area")
//    public WebElement getUploadArea() {
//        return driver.findElement(By.cssSelector("label.secondary-global-button"));
//    }
//
//
    @Step("Get 'ErrorTextWhenInvalidImage' element")
    public ButtonElement getErrorTextWhenInvalidImage() {
        if (errorTextWhenInvalidImage == null) {
            errorTextWhenInvalidImage = new ButtonElement(driver, ERROR_TEXT_WHEN_INVALID_IMAGE);
        }
        return errorTextWhenInvalidImage;
    }

    @Step("Get 'ErrorTextWhenInvalidImage' text")
    public String  getErrorTextWhenInvalidImageText() {
        return getErrorTextWhenInvalidImage().getText();
    }

    @Step("Check if Save button in 'Upload new photo' is clickable")
    public boolean isSaveButtonClickable() {
        try {
            return getSavePhotoButton().isActive();
        } catch (TimeoutException er) {
            return false;
        }
    }
//
    @Step("Check if Delete button is clickable")
    public boolean isDeleteButtonClickable() {
        try {
            return getDeletePhotoButton().isActive();
        } catch (TimeoutException er) {
            return false;
        }

    }
//
//    @Step("Get edit picture button")
//    public ButtonElement getEditPictureButton(){
//        if(editPictureButton == null){
//            editPictureButton = new ButtonElement(driver, EDIT_AVATAR_BUTTON);
//        }
//        return editPictureButton;
//    }
//
//    @Step("Click 'Edit Photo' button")
//    public EditPicturePopUpComponent clicksEditPhotoButton(){
//        getEditPictureButton().click();
//        return new EditPicturePopUpComponent(driver);
//    }
//
//
//    @Step("Upload PNG image")
//    public EditPicturePopUpComponent uploadJPGImage() {
//        uploadFile(getUploadArea(), "src/test/resources/images/jpgValidImage.jpg");
//        return this;
//    }

//    public EditPicturePopUpComponent uploadFile(WebElement dropArea, String path) {
//        String absolutePath = new File(path).getAbsolutePath();
//        dropArea.sendKeys(absolutePath);
//        return this;
//    }

    public WebElement getUploadArea() {
        //TODO refactor searchElementByCss
        return driver.findElement(CHANGE_PHOTO_INPUT.getPath());
    }

    @Step("Upload PNG image")
    public SavePicturePopUpComponent uploadPNGImage() {
        uploadFile(getUploadArea(), "src/test/resources/images/pngValidImage.png");
        return new SavePicturePopUpComponent(driver);
    }

    @Step("Upload JPG image")
    public SavePicturePopUpComponent uploadJPGImage() {
        uploadFile(getUploadArea(), "src/test/resources/images/jpgValidImage.jpg");
        return new SavePicturePopUpComponent(driver);
    }

    public EditPicturePopUpComponent uploadFile(WebElement dropArea, String path) {
        String absolutePath = new File(path).getAbsolutePath();
        dropArea.sendKeys(absolutePath);
        return this;
    }

    public EditPicturePopUpComponent uploadTooLargeJPEGImage() {
        uploadFile(getUploadArea(), "src/test/resources/images/tooLargeImage.jpg");
        return new EditPicturePopUpComponent(driver);
    }

    public EditPicturePopUpComponent uploadGIFImage() {
        uploadFile(getUploadArea(), "src/test/resources/images/gifImage.gif");
        return new EditPicturePopUpComponent(driver);
    }

    public EditPicturePopUpComponent uploadTooLargePNGImage() {
        uploadFile(getUploadArea(), "src/test/resources/images/tooLargePng.png");
        return new EditPicturePopUpComponent(driver);
    }
}
