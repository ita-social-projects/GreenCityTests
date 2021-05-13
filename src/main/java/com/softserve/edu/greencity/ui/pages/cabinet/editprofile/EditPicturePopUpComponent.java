package com.softserve.edu.greencity.ui.pages.cabinet.editprofile;

import com.softserve.edu.greencity.ui.elements.ButtonElement;
import com.softserve.edu.greencity.ui.tools.engine.WaitsSwitcher;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;

import static com.softserve.edu.greencity.ui.locators.EditProfileLocators.*;

/**
 * This class represents pop up which is displayed when user click
 * 'Edit Picture' button on 'Edit Profile' page.
 */
public class EditPicturePopUpComponent {
    protected WebDriverWait wait;
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
    private ButtonElement browseButton;
    private ButtonElement errorTextWhenInvalidImage;

    public EditPicturePopUpComponent(WebDriver driver) {
        this.driver = driver;
//        this.waitsSwitcher = new WaitsSwitcher(driver);
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


    public ButtonElement getCancelButton() {
        if (cancelButton == null) {
            cancelButton = new ButtonElement(driver, CANCEL_ADDING_PHOTO_BUTTON);
        }
        return cancelButton;
    }

    public ButtonElement getDeletePhotoButton() {
        if (deletePhotoButton == null) {
            deletePhotoButton = new ButtonElement(driver, DELETE_PHOTO_BUTTON);
        }
        return deletePhotoButton;
    }

    public ButtonElement getTitleInPopUpInDeletePhoto() {
        if (titleInPopUpDeletePhoto == null) {
            titleInPopUpDeletePhoto = new ButtonElement(driver, TITLE_POP_UP_QUESTION_IN_DELETE_PHOTO);
        }
        return titleInPopUpDeletePhoto;
    }

    public ButtonElement getContinueEditingButtonInDeletePhotoPopUp() {
        if (continueEditingButtonInDeletePhotoPopUp == null) {
            continueEditingButtonInDeletePhotoPopUp = new ButtonElement(driver, CONTINUE_EDITING_IN_DELETE_PHOTO_BUTTON);
        }
        return continueEditingButtonInDeletePhotoPopUp;
    }

    public ButtonElement getYesDeleteButtonInDeletePhotoPopUp() {
        if (yesDeleteButtonInDeletePhotoPopUp == null) {
            yesDeleteButtonInDeletePhotoPopUp = new ButtonElement(driver, YES_DELETE_IN_DELETE_PHOTO_BUTTON);
        }
        return yesDeleteButtonInDeletePhotoPopUp;
    }

    public ButtonElement getUploadNewPhotoButton() {
        if (uploadNewPhotoButton == null) {
            uploadNewPhotoButton = new ButtonElement(driver, UPLOAD_NEW_PHOTO_BUTTON);
        }
        return uploadNewPhotoButton;
    }

    public ButtonElement getClosePopUpEditPhotoButton() {
        if (closePopUpEditPhotoButton == null) {
            closePopUpEditPhotoButton = new ButtonElement(driver, CLOSE_POP_UP_EDIT_PHOTO_BUTTON);
        }
        return closePopUpEditPhotoButton;
    }

    public EditProfilePage clickCancelButton() {
        getCancelButton().click();
        return new EditProfilePage(driver);
    }

    public EditPicturePopUpComponent clickDeletePhotoButton() {
        getDeletePhotoButton().click();
        return new EditPicturePopUpComponent(driver);
    }

    public EditPicturePopUpComponent clickUploadNewPhotoButton() {
        getUploadNewPhotoButton().click();
        return new EditPicturePopUpComponent(driver);
    }

    public EditPicturePopUpComponent clickClosePopUpButton() {
        getClosePopUpEditPhotoButton().click();
        return new EditPicturePopUpComponent(driver);
    }

    public EditProfilePage clickContinueEditingButtonInDeletePhotoPopUp() {
        getContinueEditingButtonInDeletePhotoPopUp().click();
        return new EditProfilePage(driver);
    }

    public EditPicturePopUpComponent clickYesDeleteButtonInDeletePhotoPopUp() {
        getYesDeleteButtonInDeletePhotoPopUp().click();
        return new EditPicturePopUpComponent(driver);
    }

    //Upload different types of files

    public ButtonElement getSavePhotoButton() {
        if (savePhotoButton == null) {
            savePhotoButton = new ButtonElement(driver, SAVE_BUTTON);
        }
        return savePhotoButton;
    }

    public EditPicturePopUpComponent clickSavePhotoButton() {
        getSavePhotoButton().click();
        return new EditPicturePopUpComponent(driver);
    }

//    public EditPicturePopUpComponent browseImage(String img){
//        if (browseButton == null) {
//            browseButton = new ButtonElement(this.driver, UPLOAD_NEW_PHOTO_BUTTON);
//        }
//        browseButton.sendKeys(img);
//        return this;
//    }

    @Step("Get upload area")
    public WebElement getUploadArea() {
        return driver.findElement(By.cssSelector("label.secondary-global-button"));
    }

    @Step("Upload file")
    public EditPicturePopUpComponent uploadFile(WebElement dropArea, String path) {
        String absolutePath = new File(path).getAbsolutePath();
        dropArea.sendKeys(absolutePath);
        try {
            driver.findElements(By.cssSelector("label.secondary-global-button")).get(0).click();
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
        return this;
    }

    @Step("Upload GIF image")
    public EditPicturePopUpComponent uploadGIFImage() {
        uploadFile(getUploadArea(), "src/test/resources/images/gifImage.gif");
        return this;
    }

    @Step("Upload too large JPEG image")
    public EditPicturePopUpComponent uploadTooLargeJPEGImage() {
        uploadFile(getUploadArea(), "src/test/resources/images/tooLargeImage.jpg");
        return this;
    }

    @Step("Upload too large PNG image")
    public EditPicturePopUpComponent uploadTooLargePNGImage() {
        uploadFile(getUploadArea(), "src/test/resources/images/tooLargePng.jpg");
        return this;
    }

    @Step("Upload PNG image")
    public EditPicturePopUpComponent uploadPNGImage() {
        uploadFile(getUploadArea(), "src/test/resources/images/pngValidImage.png");
        return this;
    }

    @Step("Upload JPG image")
    public EditPicturePopUpComponent uploadJPGImage() {
        uploadFile(getUploadArea(), "src/test/resources/images/jpgValidImage.jpg");
        return this;
    }

    public ButtonElement getErrorTextWhenInvalidImage() {
        if (errorTextWhenInvalidImage == null) {
            errorTextWhenInvalidImage = new ButtonElement(driver, ERROR_TEXT_WHEN_INVALID_IMAGE);
        }
        return errorTextWhenInvalidImage;
    }

    @Step("Check if Save button in 'Upload new photo' is clickable")
    public boolean isSaveButtonClickable() {
        try {
            return getSavePhotoButton().isActive();
        } catch (TimeoutException er) {
            return false;
        }
    }

    @Step("Check if Delete button is clickable")
    public boolean isDeleteButtonClickable() {
        try {
            return getDeletePhotoButton().isActive();
        } catch (TimeoutException er) {
            return false;
        }

    }

    @Step("Get edit picture button")
    public ButtonElement getEditPictureButton(){
        if(editPictureButton == null){
            editPictureButton = new ButtonElement(driver, EDIT_AVATAR_BUTTON);
        }
        return editPictureButton;
    }

    @Step("Click 'Edit Photo' button")
    public EditPicturePopUpComponent clicksEditPhotoButton(){
        getEditPictureButton().click();
        return new EditPicturePopUpComponent(driver);
    }


}
