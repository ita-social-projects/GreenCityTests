package com.softserve.edu.greencity.ui.pages.cabinet.editprofile;

import com.softserve.edu.greencity.ui.elements.ButtonElement;
import com.softserve.edu.greencity.ui.elements.CheckBoxElement;
import com.softserve.edu.greencity.ui.elements.TextAreaElement;
import com.softserve.edu.greencity.ui.pages.common.TopPart;
import com.softserve.edu.greencity.ui.tools.engine.WaitsSwitcher;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import static com.softserve.edu.greencity.ui.locators.EditProfileLocators.*;

/**
 * A class that handles this page: https://ita-social-projects.github.io/GreenCityClient/#/profile/{userId}/edit
 * (available only for logged in users)
 */
public class EditProfilePage extends TopPart {


    protected WebDriverWait wait;
    private WebDriver driver;
    private WaitsSwitcher waitsSwitcher;

    private ButtonElement editPictureButton;
    private TextAreaElement nameField;
    private TextAreaElement cityField;
    private TextAreaElement credoField;

    private ButtonElement addSocialNetworkButton;
    private CheckBoxElement showLocation;
    private CheckBoxElement showEcoPlaces;
    private CheckBoxElement showShoppingList;

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
        if(addSocialNetworkButton == null){
            addSocialNetworkButton = new ButtonElement(driver, ADD_SOCIAL_NETWORKS_BUTTON);
        }
        return addSocialNetworkButton;
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
}
