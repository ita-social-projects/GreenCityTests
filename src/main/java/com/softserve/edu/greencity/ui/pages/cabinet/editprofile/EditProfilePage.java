package com.softserve.edu.greencity.ui.pages.cabinet.editprofile;

import com.softserve.edu.greencity.data.editprofile.EditProfileData;
import com.softserve.edu.greencity.ui.elements.ButtonElement;
import com.softserve.edu.greencity.ui.elements.ButtonWithIconElement;
import com.softserve.edu.greencity.ui.elements.CheckBoxElement;
import com.softserve.edu.greencity.ui.elements.TextAreaElement;
import com.softserve.edu.greencity.ui.pages.cabinet.MyHabitPage;
import com.softserve.edu.greencity.ui.pages.common.TopPart;
import io.qameta.allure.Step;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.softserve.edu.greencity.ui.locators.EditProfileLocators.*;

/**
 * A class that handles this page: https://ita-social-projects.github.io/GreenCityClient/#/profile/{userId}/edit
 * (available only for logged in users)
 */
public class EditProfilePage extends TopPart {

    private ButtonElement editPictureButton;
    private TextAreaElement nameField;
    private TextAreaElement cityField;
    private TextAreaElement credoField;

    private ButtonElement addSocialNetworkButton;
    private CheckBoxElement showLocation;
    private CheckBoxElement showEcoPlaces;
    private CheckBoxElement showShoppingList;

    private ButtonElement cancelButton;
    private ButtonElement saveButton;

    private SocialNetworkComponent socialNetworkComponent;

    public EditProfilePage(WebDriver driver) {
        super(driver);
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

    @Step("Get city field")
    public TextAreaElement getCityField(){
        if(cityField == null){
            cityField = new TextAreaElement(driver, CITY_FIELD);
        }
        return cityField;
    }

    @Step("Get credo field")
    public TextAreaElement getCredoField(){
        if(credoField == null){
            credoField = new TextAreaElement(driver, CREDO_FIELDS);
        }
        return credoField;
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

    public ButtonElement getCancelButton(){
        if(cancelButton == null){
            cancelButton = new ButtonElement(driver, CANCEL_BUTTON);
        }
        return cancelButton;
    }

    public ButtonElement getSaveButton() {
        if(saveButton == null){
            saveButton = new ButtonElement(driver, SAVE_BUTTON);
        }
        return  saveButton;
    }
    public void fillNameField(String name){
        getNameField().enterText(name);
    }

    public void fillCityField(String city){
        getCityField().enterText(city);
    }

    public void fillCredoField(String credo){
        getCredoField().enterText(credo);
    }

    public SocialNetworkComponent clickAddSocialNetworksButton(){
        getAddSocialNetworkButton().click();
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

    //TODO add popup element to return CancelEditingPopUp
    public MyHabitPage clickCancelButton(){
        getCancelButton().click();
        return new MyHabitPage(driver);
    }

    public MyHabitPage clickSaveButton(){
        if(isSaveButtonActive()){
            getSaveButton().click();
            waitsSwitcher.setImplicitWait(30);
        } else {
            clickCancelButton();
        }
        return new MyHabitPage(driver);
    }

    public boolean isSaveButtonActive(){
        return getSaveButton().isActive();
    }

    public void clearNameField(){
        getNameField().clearText();
    }

    public void clearCityField(){
        getCityField().clearText();
    }

    public void cleatCredoField(){
        getCredoField().clearText();
    }

    public EditProfilePage fillAllRequiredFields(EditProfileData editProfileData){
        clearNameField();
        fillNameField(editProfileData.getName());
        clearCityField();
        fillCityField(editProfileData.getCity());
        cleatCredoField();
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
