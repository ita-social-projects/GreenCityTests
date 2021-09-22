package com.softserve.edu.greencity.ui.pages.cabinet.editprofile;

import com.softserve.edu.greencity.data.editprofile.EditProfileData;
import com.softserve.edu.greencity.ui.elements.ButtonElement;
import com.softserve.edu.greencity.ui.elements.LabelElement;
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
    private LabelElement invalidLinkErrorText;
    private LabelElement socialNetworkTitle;

    public SocialNetworkComponent(WebDriver driver) {
        this.driver = driver;
        this.waitsSwitcher = new WaitsSwitcher(driver);
        logger = LoggerFactory.getLogger("SocialNetworkComponent");
    }

    private TextAreaElement getLinkField(){
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return new TextAreaElement(driver, SOCIAL_NETWORK_LINK_FIELD);
    }

    private ButtonElement getCancelButton(){
        return new ButtonElement(driver, CANCEL_BUTTON);
    }

    private ButtonElement getAddButton(){
        return new ButtonElement(driver, ADD_SOCIAL_NETWORK_BUTTON);
    }

    private LabelElement getInvalidLinkErrorText(){
        invalidLinkErrorText = new LabelElement(driver, INVALID_LINK_TEXT_ERROR);
        return invalidLinkErrorText;
    }

    private LabelElement getSocialNetworkTitle(){
        socialNetworkTitle = new LabelElement(driver, SOCIAL_NETWORKS_TEXT);
        return socialNetworkTitle;
    }

    public SocialNetworkComponent clickSocialNetworkTitle(){
        getSocialNetworkTitle().click();
        return new SocialNetworkComponent(driver);
    }

    public EditProfilePage clickCancelButton(){
        getCancelButton().click();
        return new EditProfilePage(driver);
    }

    public EditProfilePage clickAddButton(){
        if(isAddButtonActive()) {
            waitsSwitcher.sleep(5000);
            getAddButton().click();
            logger.info("add button was clicked");

        } else {
            logger.warn("Add button is not clickable");
        }
        return new EditProfilePage(driver);
    }

    public SocialNetworkComponent clickAddButtonWithTheInvalidLink(){
        if(isAddButtonActive()) {
            waitsSwitcher.setImplicitWait(5);
            getAddButton().click();
            waitsSwitcher.setImplicitWait(5);
        } else {
            logger.warn("Add button is not clickable");
        }
        return new SocialNetworkComponent(driver);
    }

    public boolean isAddButtonActive(){
        return getAddButton().isActive();
    }

    public void addLink(String link){
        getLinkField().enterText(link);
    }

    public SocialNetworkComponent fillSocialNetworkField(String link){
        clearSocialNetworkField();
        getLinkField().enterText(link);
        return this;
    }

    public void clearSocialNetworkField(){
        getLinkField().clearText();
    }

    public SocialNetworkComponent fillSocialNetworkField(EditProfileData editProfileData){
        clearSocialNetworkField();
        getLinkField().enterText(editProfileData.getSocialNetwork());
        return this;
    }

    public String getInvalidLinkTextError(){
        return getInvalidLinkErrorText().getText();
    }

    public String getColorOfInvalidLinkTextError(){
        return getInvalidLinkErrorText().getColorHex();
    }
}
