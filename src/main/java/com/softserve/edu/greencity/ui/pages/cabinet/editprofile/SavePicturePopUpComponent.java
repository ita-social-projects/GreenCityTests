package com.softserve.edu.greencity.ui.pages.cabinet.editprofile;

import com.softserve.edu.greencity.ui.elements.ButtonElement;
import com.softserve.edu.greencity.ui.tools.engine.WaitsSwitcher;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.File;

import static com.softserve.edu.greencity.ui.locators.EditProfileLocators.CHANGE_PHOTO_INPUT;
import static com.softserve.edu.greencity.ui.locators.EditProfileLocators.SAVE_PHOTO_BUTTON;

/**
 * This class represents pop up which is displayed when user click
 * 'Upload new photo' button and uploading the image on EditPicturePopUpComponent.
 */
public class SavePicturePopUpComponent {

    private WebDriver driver;
    private WaitsSwitcher waitsSwitcher;

    private ButtonElement changePhoto;
    private ButtonElement saveButton;

    public SavePicturePopUpComponent(WebDriver driver) {
        this.driver = driver;
        this.waitsSwitcher = new WaitsSwitcher(driver);
    }

    public ButtonElement getSaveButton(){
        saveButton = new ButtonElement(driver, SAVE_PHOTO_BUTTON);
        return saveButton;
    }

    public EditProfilePage clickSavePhotoButton(){
        getSaveButton().click();
        waitsSwitcher.setExplicitWait(10,
        ExpectedConditions.invisibilityOfElementLocated(SAVE_PHOTO_BUTTON.getPath()));
        return new EditProfilePage(driver);
    }

    public WebElement getUploadArea() {
        //TODO refactor searchElementByCss
        return driver.findElement(CHANGE_PHOTO_INPUT.getPath());
    }

    @Step("Upload PNG image")
    public SavePicturePopUpComponent uploadPNGImage() {
        uploadFile(getUploadArea(), "src/test/resources/images/pngValidImage.png");
        return this;
    }

    public SavePicturePopUpComponent uploadFile(WebElement dropArea, String path) {
        String absolutePath = new File(path).getAbsolutePath();
        dropArea.sendKeys(absolutePath);
        return this;
    }

}
