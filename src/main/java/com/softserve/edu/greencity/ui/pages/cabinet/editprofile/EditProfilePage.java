package com.softserve.edu.greencity.ui.pages.cabinet.editprofile;

import com.softserve.edu.greencity.data.editprofile.EditProfileData;
import com.softserve.edu.greencity.ui.elements.*;
import com.softserve.edu.greencity.ui.pages.cabinet.MyHabitPage;
import com.softserve.edu.greencity.ui.pages.common.TopPart;
import io.qameta.allure.Step;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static com.softserve.edu.greencity.ui.locators.EditProfileLocators.*;

/**
 * A class that handles this page: https://ita-social-projects.github.io/GreenCityClient/#/profile/{userId}/edit
 * (available only for logged in users)
 */
public class EditProfilePage extends TopPart {

    private LabelElement titleLabel;

    private ButtonElement editPictureButton;
    private TextAreaElement nameField;
    private TextAreaElement cityField;
    private TextAreaElement credoField;

    private ButtonElement addSocialNetworkButton;
    private CheckBoxElement showLocation;
    private CheckBoxElement showEcoPlaces;
    private CheckBoxElement showShoppingList;

    private SocialNetworkComponent socialNetworkComponent;
    private ButtonElement cancelButton;
    private ButtonElement saveButton;
    private ButtonElement confirmCancelingButton;

    public EditProfilePage(WebDriver driver) {
        super(driver);
    }


    @Step("Get title label")
    public LabelElement getTitleOnEditPage() {
        if (titleLabel == null) {
            titleLabel = new LabelElement(driver, TITLE_LABEL);
        }
        return titleLabel;
    }

    @Step("Get edit picture button")
    public ButtonElement getEditPictureButton(){
        if(editPictureButton == null){
            editPictureButton = new ButtonElement(driver, EDIT_AVATAR_BUTTON);
        }
        return editPictureButton;
    }

    @Step("Get name field")
    public TextAreaElement getNameField(){
        if(nameField == null){
            nameField = new TextAreaElement(driver, NAME_FIELD);
        }
        return nameField;
    }

    @Step("Clear name field")
    public EditProfilePage clearNameField() {
        getNameField().clearText();
        return this;
    }
    @Step("Fill name field")
    public EditProfilePage fillNameField(String name){
        getNameField().enterText(name);
        return this;
    }

    @Step("Get city field")
    public TextAreaElement getCityField(){
        if(cityField == null){
            cityField = new TextAreaElement(driver, CITY_FIELD);
        }
        return cityField;
    }

    @Step("Clear city field")
    public EditProfilePage clearCityField() {
        getCityField().clearText();
        return this;
    }

    @Step("Fill city field")
    public EditProfilePage fillCityField(String city){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        getCityField().enterText(city);
        return this;
    }

    @Step("Get credo field")
    public TextAreaElement getCredoField(){
        if(credoField == null){
            credoField = new TextAreaElement(driver, CREDO_FIELDS);
        }
        return credoField;
    }

    @Step("Clear credo field")
    public EditProfilePage clearCredoField() {
        getCredoField().clearText();
        return this;
    }

    @Step("Fill credo field")
    public EditProfilePage fillCredoField(String credo){
        getCredoField().enterText(credo);
        return this;
    }

    @Step("Get add social network button")
    public ButtonElement getAddSocialNetworkButton(){
//        if(addSocialNetworkButton == null){
            addSocialNetworkButton = new ButtonWithIconElement(driver, ADD_SOCIAL_NETWORKS_BUTTON);
//        }
        return addSocialNetworkButton;
//        return (ButtonElement) searchElementByCss(ADD_SOCIAL_NETWORKS_BUTTON.getPath());
    }

    @Step("Get show location check box")
    public CheckBoxElement getShowLocationCheckBox(){
        if(showLocation == null){
            showLocation = new CheckBoxElement(driver, SHOW_LOCATION_CHECK_BOX);
        }
        return showLocation;
    }

    @Step("Get show eco places check box")
    public CheckBoxElement getShowEcoPlacesCheckBox(){
        if(showEcoPlaces == null){
            showEcoPlaces = new CheckBoxElement(driver, SHOW_ECO_PLACES_CHECK_BOX);
        }
        return showEcoPlaces;
    }

    @Step("Get show shopping list check box")
    public CheckBoxElement getShowShoppingListCheckBox(){
        if(showShoppingList == null){
            showShoppingList = new CheckBoxElement(driver, SHOW_SHOPPING_LIST_CHECK_BOX);
        }
        return showShoppingList;
    }

    @Step("Get 'Save' button")
    public ButtonElement getSaveButton(){
        if(saveButton == null){
            saveButton = new ButtonElement(driver, SAVE_BUTTON);
        }
        return saveButton;
    }

    @Step("Get 'Cancel' button")
    public ButtonElement getCancelButton(){
        if(cancelButton == null){
            cancelButton = new ButtonElement(driver, CANCEL_BUTTON);
        }
        return cancelButton;
    }

    @Step("Click 'Save' button")
    public MyHabitPage clickSaveButton(){
        if(isSaveButtonActive()){
            getSaveButton().click();
            waitsSwitcher.setImplicitWait(30);
        } else {
            clickCancelButton();
        }
        return new MyHabitPage(driver);
    }

    @Step("Click 'Cancel' button")
    public CancelEditingPopUpComponent clickCancelButtonWithPopUp(){
        getCancelButton().click();
        return new CancelEditingPopUpComponent(driver);
    }


    @Step("Click 'Cancel' button")
    public MyHabitPage clickCancelButton(){
        getCancelButton().click();
        return new MyHabitPage(driver);
    }

    @Step("Click 'Yes, Cancel' on popup after clicking 'Cancel' button")
    public MyHabitPage ClickConfirmationButtonAfterCancelButtonPopup(){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (confirmCancelingButton == null){
            confirmCancelingButton = new ButtonElement(driver, CONFIRM_CANCEL_PROFILE_EDITING);
        }
        confirmCancelingButton.click();
        return new MyHabitPage(driver);
    }

    public SocialNetworkComponent clickAddSocialNetworksButton(){
        logger.info("wait to click add social networks icon");
        waitsSwitcher.setExplicitWait(10, ExpectedConditions
                .elementToBeClickable(ADD_SOCIAL_NETWORKS_BUTTON.getPath()));
        getAddSocialNetworkButton().click();
        logger.info("add social networks icon was clicked");
        waitsSwitcher.setImplicitWait(5);
        return new SocialNetworkComponent(driver);
    }

    public void clickShowLocationCheckBox(){
        getShowLocationCheckBox().click();
    }

    public void clickShowEcoPlacesCheckBox(){
        getShowEcoPlacesCheckBox().click();
    }

    public void clickShowShoppingListCheckBox(){
        getShowShoppingListCheckBox().click();
    }

    public boolean isSaveButtonActive(){
        return getSaveButton().isActive();
    }


    public EditProfilePage fillAllRequiredFields(EditProfileData editProfileData){
        clearNameField();
        fillNameField(editProfileData.getName());
        clearCityField();
        fillCityField(editProfileData.getCity());
        clearCredoField();
        fillCredoField(editProfileData.getCredo());
        return this;
    }

    public boolean isAddSocialNetworkButtonActive(){
        try {
            return getAddSocialNetworkButton().isActive();
        }
        catch (TimeoutException er) {
            return false;
        }
    }

    public AddedSocialNetworkContainer getSocialNetworksContainer() {
        AddedSocialNetworkContainer addedSocialNetworkContainer
                = new AddedSocialNetworkContainer(driver);
        try {
            waitsSwitcher.setExplicitWait(10,
                    ExpectedConditions.presenceOfAllElementsLocatedBy(DISPLAYED_LINKS.getPath()));
        }catch (TimeoutException e){
            addedSocialNetworkContainer.setSocialNetworksSize(0);
        }
        return addedSocialNetworkContainer;
    }

    public EditProfilePage resetFields(EditProfileData editProfileData){
        fillAllRequiredFields(editProfileData);
        int socialNetworkContainerSize = getSocialNetworksContainer().getSocialNetworksSize();
        if(socialNetworkContainerSize > 0){
            for (int i = 0; i < socialNetworkContainerSize; i++){
                getSocialNetworksContainer()
                        .chooseSocialNetworkByNumber(i)
                        .clickDeleteButton()
                        .clickYesDeleteButton();
            }
        }
        return this;
    }

    //TODO add check if data in fields have been changed, if yes after clicking cancel button pop up is shown else no
//    public boolean isDataHaveBeenChanged(){
//        getNameField().getText()
//    }

}
