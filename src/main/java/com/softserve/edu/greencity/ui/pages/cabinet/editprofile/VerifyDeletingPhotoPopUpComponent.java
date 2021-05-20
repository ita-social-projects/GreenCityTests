package com.softserve.edu.greencity.ui.pages.cabinet.editprofile;

import com.softserve.edu.greencity.ui.elements.ButtonElement;
import com.softserve.edu.greencity.ui.tools.engine.WaitsSwitcher;
import org.openqa.selenium.WebDriver;

import static com.softserve.edu.greencity.ui.locators.EditProfileLocators.*;

/**
 * This class represents pop up which is displayed when user click
 * 'Delete photo' button on EditPicturePopUpComponent.
 */
public class VerifyDeletingPhotoPopUpComponent {

    private WebDriver driver;
    private WaitsSwitcher waitsSwitcher;

    private ButtonElement continueEditingButton;
    private ButtonElement yesDeleteButton;

    public VerifyDeletingPhotoPopUpComponent(WebDriver driver) {
        this.driver = driver;
        this.waitsSwitcher = new WaitsSwitcher(driver);
    }

    public ButtonElement getContinueEditingButton(){
        continueEditingButton = new ButtonElement(driver, CONTINUE_EDITING_BUTTON);
        return continueEditingButton;
    }

    public ButtonElement getYesDeleteButton(){
        yesDeleteButton = new ButtonElement(driver, YES_DELETE_SOCIAL_NETWORK);
        return yesDeleteButton;
    }

    public EditPicturePopUpComponent clickContinueEditingButton(){
        getContinueEditingButton().click();
        return new EditPicturePopUpComponent(driver);
    }

    public EditProfilePage clickYesDeleteButton(){
        getYesDeleteButton().click();
        waitsSwitcher.setImplicitWait(30);
        return new EditProfilePage(driver);
    }
}
