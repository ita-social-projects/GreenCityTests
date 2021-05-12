package com.softserve.edu.greencity.ui.pages.cabinet.editprofile;

import com.softserve.edu.greencity.ui.elements.ButtonElement;
import com.softserve.edu.greencity.ui.tools.engine.WaitsSwitcher;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
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
    private ButtonElement cancelButton;
    private ButtonElement deletePhotoButton;
    private ButtonElement uploadNewPhotoButton;
    private ButtonElement closePopUpEditPhotoButton;

    public EditPicturePopUpComponent(WebDriver driver) {
        this.driver = driver;
        this.waitsSwitcher = new WaitsSwitcher(driver);
    }

    public ButtonElement getTitleInPopUpEditPicture(){
        if(titleInPopUpEditPicture == null){
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
        if(deletePhotoButton == null){
            deletePhotoButton = new ButtonElement(driver, DELETE_PHOTO_BUTTON);
        }
        return deletePhotoButton;
    }

    public ButtonElement getUploadNewPhotoButton(){
        if(uploadNewPhotoButton == null){
            uploadNewPhotoButton = new ButtonElement(driver, UPLOAD_NEW_PHOTO_BUTTON);
        }
        return uploadNewPhotoButton;
    }

    public ButtonElement getClosePopUpEditPhotoButton(){
        if(closePopUpEditPhotoButton == null){
            closePopUpEditPhotoButton = new ButtonElement(driver, CLOSE_POP_UP_EDIT_PHOTO_BUTTON);
        }
        return closePopUpEditPhotoButton;
    }

    public EditProfilePage clickCancelButton(){
        getCancelButton().click();
        return new EditProfilePage(driver);
    }

    public EditProfilePage clickUploadNewPhotoButton(){
        getUploadNewPhotoButton().click();
        return new EditProfilePage(driver);
    }

    public EditProfilePage clickClosePopUpButton(){
        getClosePopUpEditPhotoButton().click();
        return new EditProfilePage(driver);
    }

    public VerifyDeletingPhotoPopUpComponent clickDeletePhotoButton(){
        if(isDeleteButtonClickable()) {
            getDeletePhotoButton().click();
        }
        return new VerifyDeletingPhotoPopUpComponent(driver);
    }

    public boolean isDeleteButtonClickable(){
        try {
            waitsSwitcher.setExplicitWait(5, ExpectedConditions.elementToBeClickable(DELETE_PHOTO_BUTTON.getPath()));
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public WebElement getUploadArea() {
        //TODO refactor searchElementByCss
        return driver.findElement(CHANGE_PHOTO_INPUT.getPath());
    }

    @Step("Upload PNG image")
    public SavePicturePopUpComponent uploadPNGImage() {
        uploadFile(getUploadArea(), "src/test/resources/images/pngValidImage.png");
        return new SavePicturePopUpComponent(driver);
    }

    @Step("Upload PNG image")
    public EditPicturePopUpComponent uploadJPGImage() {
        uploadFile(getUploadArea(), "src/test/resources/images/jpgValidImage.jpg");
        return this;
    }

    public EditPicturePopUpComponent uploadFile(WebElement dropArea, String path) {
        String absolutePath = new File(path).getAbsolutePath();
        dropArea.sendKeys(absolutePath);
        return this;
    }
}
