package com.softserve.edu.greencity.ui.pages.cabinet.editprofile;

import com.softserve.edu.greencity.ui.elements.ButtonElement;
import com.softserve.edu.greencity.ui.tools.engine.WaitsSwitcher;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import static com.softserve.edu.greencity.ui.locators.EditProfileLocators.*;

/**
 * This class represents pop up which is displayed when user click
 * 'Edit Picture' button on 'Edit Profile' page.
 */
public class EditPicturePopUpComponent {
    protected WebDriverWait wait;
    private WebDriver driver;
    private WaitsSwitcher waitsSwitcher;

    private ButtonElement cancelButton;
    private ButtonElement deletePhotoButton;
    private ButtonElement uploadNewPhotoButton;

    public EditPicturePopUpComponent(WebDriver driver, WaitsSwitcher waitsSwitcher) {
        this.driver = driver;
        this.waitsSwitcher = new WaitsSwitcher(driver);
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

    public EditProfilePage clickCancelButton(){
        getCancelButton().click();
        return new EditProfilePage(driver);
    }
}
