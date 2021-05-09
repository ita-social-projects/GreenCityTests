package com.softserve.edu.greencity.ui.pages.cabinet.editprofile;

import com.softserve.edu.greencity.data.editprofile.EditProfileData;
import com.softserve.edu.greencity.ui.elements.ButtonElement;
import com.softserve.edu.greencity.ui.elements.TextAreaElement;
import com.softserve.edu.greencity.ui.tools.engine.WaitsSwitcher;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

import static com.softserve.edu.greencity.ui.locators.EditProfileLocators.*;

/**
 * This class is responsible for adding social network link on 'Edit Profile' Page
 */
public class SocialNetworkComponent{

    private WebDriver driver;
    private WaitsSwitcher waitsSwitcher;
    private Logger logger;
    private TextAreaElement linkField;
    private ButtonElement cancelButton;
    private ButtonElement addButton;

    public SocialNetworkComponent(WebDriver driver) {
        this.driver = driver;
        this.waitsSwitcher = new WaitsSwitcher(driver);
        logger = LoggerFactory.getLogger("SocialNetworkComponent");
    }

    private TextAreaElement getLinkField(){
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        if(linkField == null){
            linkField = new TextAreaElement(driver, SOCIAL_NETWORK_LINK_FIELD);
        }
        return linkField;
        //return socialNetworkLayout.findElement(SOCIAL_NETWORK_LINK_FIELD.getPath());
//        return (TextAreaElement) searchElementByCss(SOCIAL_NETWORK_LINK_FIELD.getPath());
    }

    private ButtonElement getCancelButton(){
        if(cancelButton == null){
            cancelButton = new ButtonElement(driver, CANCEL_SOCIAL_NETWORK_BUTTON);
        }
        return cancelButton;
//        return socialNetworkLayout.findElement(CANCEL_SOCIAL_NETWORK_BUTTON.getPath());
//        return (ButtonElement) searchElementByCss(CANCEL_SOCIAL_NETWORK_BUTTON.getPath());
    }

    private ButtonElement getAddButton(){
//        waitsSwitcher.setExplicitWait(10, ExpectedConditions.
//                visibilityOf((WebElement) ADD_SOCIAL_NETWORK_BUTTON.getPath()));
//        if(addButton == null){
            addButton = new ButtonElement(driver, ADD_SOCIAL_NETWORK_BUTTON);
//        }
        return addButton;
//        return socialNetworkLayout.findElement(ADD_SOCIAL_NETWORK_BUTTON.getPath());
//        return (ButtonElement) searchElementByCss(ADD_SOCIAL_NETWORK_BUTTON.getPath());
    }

    public void clickCancelButton(){
        getCancelButton().click();
    }

    public EditProfilePage clickAddButton(){
        if(isAddButtonActive()) {
            getAddButton().click();
        } else {
            logger.warn("Add button is not clickable");
        }
        return new EditProfilePage(driver);
    }

    private boolean isAddButtonActive(){
        return getAddButton().isActive();
    }

    public void addLink(String link){
        getLinkField().enterText(link);
    }

    public void fillSocialNetworkField(String link){
        getLinkField().enterText(link);
    }

    public EditProfilePage fillUpToFiveSocialNetworksFields(EditProfileData editProfileData){
        for (String link: editProfileData.getSocialNetworks()) {
            fillSocialNetworkField(link);
            clickAddButton().clickAddSocialNetworksButton();
        }

        return new EditProfilePage(driver);
    }

    public void clearSocialNetworkField(){
        getLinkField().clearText();
    }

    public SocialNetworkComponent fillSocialNetworkField(EditProfileData editProfileData){
        clearSocialNetworkField();
        getLinkField().enterText(editProfileData.getSocialNetwork());
        return this;
    }
}
