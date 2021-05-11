package com.softserve.edu.greencity.ui.pages.cabinet.editprofile;

import com.softserve.edu.greencity.ui.elements.ButtonElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import static com.softserve.edu.greencity.ui.locators.EditProfileLocators.*;

/**
 * This class represents pop up which is displayed when user click
 * 'Edit Picture' button on 'Edit Profile' page.
 */
public class EditPicturePopUpComponent {
    protected WebDriverWait wait;
    private WebDriver driver;
//    private WaitsSwitcher waitsSwitcher;

    private ButtonElement titleInPopUpEditPicture;
    private ButtonElement cancelButton;
    private ButtonElement deletePhotoButton;
    private ButtonElement uploadNewPhotoButton;
    private ButtonElement closePopUpEditPhotoButton;
    private ButtonElement editPictureButton;

    public EditPicturePopUpComponent(WebDriver driver) {
        this.driver = driver;
//        this.waitsSwitcher = new WaitsSwitcher(driver);
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

    public EditProfilePage clickDeletePhotoButton(){
        getDeletePhotoButton().click();
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


}
