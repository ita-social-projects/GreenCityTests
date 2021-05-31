package com.softserve.edu.greencity.ui.pages.cabinet.editprofile;

import com.softserve.edu.greencity.data.editprofile.EditProfileData;
import com.softserve.edu.greencity.ui.elements.*;
import com.softserve.edu.greencity.ui.pages.cabinet.LanguageComponents;
import com.softserve.edu.greencity.ui.pages.cabinet.MyHabitPage;
import com.softserve.edu.greencity.ui.pages.common.TopPart;
import com.softserve.edu.greencity.ui.tools.engine.WaitsSwitcher;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.awt.*;

import static com.softserve.edu.greencity.ui.locators.EditProfileLocators.*;
import static com.softserve.edu.greencity.ui.locators.MyHabitLocators.SOCIAL_MEDIA_ICON;

/**
 * A class that handles this page: https://ita-social-projects.github.io/GreenCityClient/#/profile/{userId}/edit
 * (available only for logged in users)
 */
public class EditProfilePage extends TopPart {

    private LabelElement titleLabel;

    private ButtonElement editPictureButton;
    private ButtonElement deletePhotoButton;
    private ButtonElement uploadNewPhotoButton;
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

    private final int MAX_SOCIAL_NETWORKS = 5;

    private LabelElement nameNotification;
    private LabelElement cityNotification;
    private LabelElement credoNotification;

    private LanguageComponents languageSwitcher;

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

    @Step("Get title label")
    public String getTextTitleOnEditPage() {
        return getTitleOnEditPage().getText();
    }

    @Step("Get edit picture button")
    public ButtonElement getEditPictureButton(){
        if(editPictureButton == null){
            editPictureButton = new ButtonElement(driver, EDIT_AVATAR_BUTTON);
        }
        return editPictureButton;
    }

    @Step("Click 'Edit Photo' button")
    public EditPicturePopUpComponent clickEditPictureButton(){
        getEditPictureButton().click();
        return new EditPicturePopUpComponent(driver);
    }

    public EditProfilePage clickOnTitleOnEditPage(){
        getTitleOnEditPage().click();
        return this;
    }

    @Step("Get name field")
    public TextAreaElement getNameField(){
        return new TextAreaElement(driver, NAME_FIELD);
    }

    @Step("Clear name field")
    public EditProfilePage clearNameField() {
        WaitsSwitcher.sleep(3000);
        TextAreaElement element = getNameField();
        while (!element.getText().equals("")){
            getNameField().clearText();
        }
        return this;
    }

    @Step("Clear name field using backspase")
    public  EditProfilePage clearNameFieldWithBackspase(){
        WaitsSwitcher.sleep(3000);
        TextAreaElement element = getNameField();
        System.out.println(element.getText());
        element.click();
        int x = element.getText().length();
        for (int i = 0; i < x; i++){
            getNameField().enterText("" + Keys.BACK_SPACE);
        }
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
        TextAreaElement element = getCityField();
        WaitsSwitcher.sleep(1000);
        while (!element.getText().equals("")){
            getCityField().clearText();
        }
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
    @Step("Check 'Show Location' checkbox")
    public EditProfilePage clickShowLocationCheckBoxCheck(){
        if (showLocation == null){
            showLocation = new CheckBoxElement(driver, SHOW_LOCATION_CHECK_BOX);
        }
        if(!showLocation.isChecked())
            showLocation.click();
        return new EditProfilePage(driver);
    }
    @Step("Uncheck 'Show Location' checkbox")
    public EditProfilePage clickShowLocationCheckBoxUncheck(){
        if (showLocation == null){
            showLocation = new CheckBoxElement(driver, SHOW_LOCATION_CHECK_BOX);
        }
        if(showLocation.isChecked())
            showLocation.click();
        return new EditProfilePage(driver);
    }
    @Step("Check 'Show Shopping List' checkbox")
    public EditProfilePage clickShowShoppingListCheckBoxCheck(){
        if (showShoppingList == null){
            showShoppingList = new CheckBoxElement(driver, SHOW_SHOPPING_LIST_CHECK_BOX);
        }
        if (!showShoppingList.isChecked()){
            showShoppingList.click();
        }
        return new EditProfilePage(driver);
    }

    @Step("Uncheck 'Show Shopping List' checkbox")
    public EditProfilePage clickShowShoppingListCheckBoxCheckUncheck(){
        if (showShoppingList == null){
            showShoppingList = new CheckBoxElement(driver, SHOW_SHOPPING_LIST_CHECK_BOX);
        }
        if (showShoppingList.isChecked()){
            showShoppingList.click();
        }
        return new EditProfilePage(driver);
    }

    @Step("Get credo field")
    public TextAreaElement getCredoField(){
        return new TextAreaElement(driver, CREDO_FIELDS);
    }

    @Step("Clear credo field")
    public EditProfilePage clearCredoField() {
        getCredoField().clearText();
        return this;
    }

    public EditProfilePage clickCityTextArea(){
        getCityField().click();
        return this;
    }

    @Step("Fill credo field")
    public EditProfilePage fillCredoField(String credo){
        getCredoField().enterText(credo);
        return this;
    }

    @Step("Get add social network button")
    public ButtonElement getAddSocialNetworkButton(){
        return new ButtonWithIconElement(driver, ADD_SOCIAL_NETWORKS_BUTTON);
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
            waitsSwitcher.sleep(10);
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

    @Step("Switch RU language")
    public EditProfilePage switchRuLanguage() {
        languageSwitcher = new LanguageComponents(driver);
        languageSwitcher.clickRuLanguage();
        return new EditProfilePage(driver);
    }

    @Step("Switch EN language")
    public EditProfilePage switchENLanguage() {
        languageSwitcher = new LanguageComponents(driver);
        languageSwitcher.clickEnLanguage();
        return new EditProfilePage(driver);
    }
    @Step("Switch UA language")
    public EditProfilePage switchUaLanguage() {
        languageSwitcher = new LanguageComponents(driver);
        languageSwitcher.clickUaLanguage();
        return new EditProfilePage(driver);
    }
    @Step("Navigate To Eco News")
    public CancelEditingPopUpComponent clickEcoNewsButton(){
        getMainMenuDropdown().clickMenuEcoNews();
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

    public EditProfilePage clickShowLocationCheckBox(){
        getShowLocationCheckBox().click();
        return this;
    }

    public EditProfilePage clickShowEcoPlacesCheckBox(){
        getShowEcoPlacesCheckBox().click();
        return this;
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
            logger.warn("No social links exists");
        }
        return new AddedSocialNetworkContainer(driver);
    }

    public EditProfilePage resetFields(EditProfileData editProfileData){
        fillAllRequiredFields(editProfileData);

        while (getSocialNetworksContainer().getSocialNetworksSize() != 0) {
            getSocialNetworksContainer()
                    .chooseSocialNetworkByNumber(0)
                    .clickDeleteButton()
                    .clickYesDeleteButton();
        }
        return this;
    }

    public EditProfilePage fillUpToFiveSocialNetworksFields(EditProfileData editProfileData){
        int count = 0;
        for (String link: editProfileData.getSocialNetworks()) {
            clickAddSocialNetworksButton()
                    .fillSocialNetworkField(link)
                    .clickAddButton();
            count++;
            if(count >= MAX_SOCIAL_NETWORKS){
                break;
            }
        }
        return this;
        }

    @Step("Get name notification label")
    public LabelElement getNameNotification() {
        if (nameNotification == null) {
            nameNotification = new LabelElement(driver, NOTIFICATION_FOR_NAME_FIELD_TEXT);
        }
        WaitsSwitcher.sleep(1000);
        return nameNotification;
    }

    @Step("Get text from name notification label")
    public String getTextFromNameNotification() {
        return getNameNotification().getText();
    }

    @Step("Get city notification label")
    public LabelElement getCityNotification() {
        if (cityNotification == null) {
            cityNotification = new LabelElement(driver, NOTIFICATION_FOR_CITY_FIELD_TEXT);
        }
        return cityNotification;
    }

    @Step("Get text from city notification label")
    public String getTextFromCityNotification() {
        return getCityNotification().getText();
    }

    @Step("Get credo notification label")
    public LabelElement getCredoNotification() {
        if (credoNotification == null) {
            credoNotification = new LabelElement(driver, NOTIFICATION_FOR_CREDO_FIELD_TEXT);
        }
        return credoNotification;
    }

    @Step("Get text from credo notification label")
    public String getTextFromCredoNotification() {
        return getCredoNotification().getText();
    }

    @Step("Get color from name notification label")
    public String getColorFromNameNotificationLabel(){
        return getNameNotification().getColorHex();
    }

    @Step("Get color from city notification label")
    public String getColorFromCityNotificationLabel(){
        return getCityNotification().getColorHex();
    }

    @Step("Get color from credo notification label")
    public String getColorFromCredoNotificationLabel(){
        return getCredoNotification().getColorHex();
    }

    public boolean saveButtonClickable(){
        try {
            waitsSwitcher.setExplicitWait(ExpectedConditions.elementToBeClickable(SAVE_BUTTON.getPath()));
            return true;
        } catch (Exception e){
            return false;
        }
    }

    //TODO add check if data in fields have been changed, if yes after clicking cancel button pop up is shown else no
//    public boolean isDataHaveBeenChanged(){
//        getNameField().getText()
//    }


    @Step("Get delete photo button")
    public ButtonElement getDeletePhotoButton(){
        if(deletePhotoButton == null){
            deletePhotoButton = new ButtonElement(driver, DELETE_PHOTO_BUTTON);
        }
        return deletePhotoButton;
    }

    @Step("Click 'Edit Photo' button")
    public EditPicturePopUpComponent clickEditPhotoButton(){
        getEditPictureButton().click();
        return new EditPicturePopUpComponent(driver);
    }

    @Step("Click 'Delete photo' button")
    public EditPicturePopUpComponent clickDeletePhotoButton(){
        getDeletePhotoButton().click();
        return new EditPicturePopUpComponent(driver);
    }

    @Step("Get upload new photo button")
    public ButtonElement getUploadNewPhotoButton(){
        if(uploadNewPhotoButton == null){
            uploadNewPhotoButton = new ButtonElement(driver, UPLOAD_NEW_PHOTO_BUTTON);
        }
        return uploadNewPhotoButton;
    }

    @Step("Click 'Upload new photo' button")
    public EditPicturePopUpComponent clickUploadNewPhotoButton(){
        getUploadNewPhotoButton().click();
        return new EditPicturePopUpComponent(driver);
    }
}
