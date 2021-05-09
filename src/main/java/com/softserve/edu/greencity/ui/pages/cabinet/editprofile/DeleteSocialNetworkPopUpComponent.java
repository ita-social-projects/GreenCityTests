package com.softserve.edu.greencity.ui.pages.cabinet.editprofile;

import com.softserve.edu.greencity.ui.elements.ButtonElement;
import org.openqa.selenium.WebDriver;

import static com.softserve.edu.greencity.ui.locators.EditProfileLocators.*;

/**
 * Pop up which is reproduced when user click 'Delete' icon on 'Edit profile' page
 * in 'Linked social networks' section.
 */
public class DeleteSocialNetworkPopUpComponent {

    private WebDriver driver;
    private ButtonElement yesDeleteButton;
    private ButtonElement cancelDeletingButton;
    private ButtonElement closeButton;

    public DeleteSocialNetworkPopUpComponent(WebDriver driver) {
        this.driver = driver;
    }

    private ButtonElement getYesDeleteButton() {
        yesDeleteButton = new ButtonElement(driver, YES_DELETE_SOCIAL_NETWORK);
        return yesDeleteButton;
    }

    private ButtonElement getCancelDeletingButton() {
        cancelDeletingButton = new ButtonElement(driver, CANCEL_DELETING_SOCIAL_NETWORK);
        return cancelDeletingButton;
    }

    private ButtonElement getCloseButton() {
        closeButton = new ButtonElement(driver, CLOSE_DELETING_BUTTON);
        return closeButton;
    }

    public EditProfilePage clickYesDeleteButton(){
        getYesDeleteButton().click();
        return new EditProfilePage(driver);
    }

    public EditProfilePage clickCancelDeletingButton(){
        getCancelDeletingButton().click();
        return new EditProfilePage(driver);
    }

    public EditProfilePage clickCloseButton(){
        getCloseButton().click();
        return new EditProfilePage(driver);
    }
}
