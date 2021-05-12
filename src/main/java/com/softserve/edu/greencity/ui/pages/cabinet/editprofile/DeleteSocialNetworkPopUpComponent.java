package com.softserve.edu.greencity.ui.pages.cabinet.editprofile;

import com.softserve.edu.greencity.ui.elements.ButtonElement;
import com.softserve.edu.greencity.ui.elements.LabelElement;
import com.softserve.edu.greencity.ui.tools.engine.WaitsSwitcher;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static com.softserve.edu.greencity.ui.locators.EditProfileLocators.*;

/**
 * Pop up which is reproduced when user click 'Delete' icon on 'Edit profile' page
 * in 'Linked social networks' section.
 */
public class DeleteSocialNetworkPopUpComponent {

    private WebDriver driver;
    private WaitsSwitcher waitsSwitcher;
    private LabelElement warningText;
    private ButtonElement yesDeleteButton;
    private ButtonElement cancelDeletingButton;
    private ButtonElement closeButton;

    public DeleteSocialNetworkPopUpComponent(WebDriver driver) {
        this.driver = driver;
        waitsSwitcher = new WaitsSwitcher(driver);
    }

    private LabelElement getWarningLabel(){
        warningText = new LabelElement(driver, POP_UP_WARNING_TEXT);
        return warningText;
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
        waitsSwitcher.setExplicitWait(ExpectedConditions.elementToBeClickable(YES_DELETE_SOCIAL_NETWORK.getPath()));
        getYesDeleteButton().click();
        return new EditProfilePage(driver);
    }

    public EditProfilePage clickCancelDeletingButton(){
        waitsSwitcher.setExplicitWait(ExpectedConditions.elementToBeClickable(CANCEL_BUTTON.getPath()));
        getCancelDeletingButton().click();
        return new EditProfilePage(driver);
    }

    public EditProfilePage clickCloseButton(){
        getCloseButton().click();
        return new EditProfilePage(driver);
    }

    public String getWarningText(){
        return getWarningLabel().getText();
    }

    public String getCancelButtonText(){
        return getCancelDeletingButton().getText();
    }

    public String getYesDeletingButtonText(){
        return getYesDeleteButton().getText();
    }
}
