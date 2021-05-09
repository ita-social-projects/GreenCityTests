package com.softserve.edu.greencity.ui.pages.cabinet.editprofile;

import com.softserve.edu.greencity.ui.elements.ButtonElement;
import com.softserve.edu.greencity.ui.pages.cabinet.MyHabitPage;
import org.openqa.selenium.WebDriver;

import static com.softserve.edu.greencity.ui.locators.EditProfileLocators.*;

/**
 * Pop up which is reproduced when user click 'Cancel' button on 'Edit profile' page.
 * Appeared when data on 'Edit profile' page is changed.
 */
public class CancelEditingPopUpComponent {

    private WebDriver driver;
    private ButtonElement continueEditingButton;
    private ButtonElement cancelEditingButton;
    private ButtonElement closeButton;

    public CancelEditingPopUpComponent(WebDriver driver) {
        this.driver = driver;
    }

    private ButtonElement getContinueEditingButton() {
        continueEditingButton = new ButtonElement(driver, CONTINUE_EDITING_BUTTON);
        return continueEditingButton;
    }

    private ButtonElement getCancelEditingButton() {
        cancelEditingButton = new ButtonElement(driver, CANCEL_EDITING);
        return cancelEditingButton;
    }

    private ButtonElement getCloseButton() {
        closeButton = new ButtonElement(driver, CLOSE_EDITING_BUTTON);
        return closeButton;
    }

    public EditProfilePage clickContinueEditingButton(){
        getContinueEditingButton().click();
        return new EditProfilePage(driver);
    }

    public MyHabitPage clickCancelButton(){
        getCancelEditingButton().click();
        return new MyHabitPage(driver);
    }

    public EditProfilePage clickCloseButton(){
        getCloseButton().click();
        return new EditProfilePage(driver);
    }
}
