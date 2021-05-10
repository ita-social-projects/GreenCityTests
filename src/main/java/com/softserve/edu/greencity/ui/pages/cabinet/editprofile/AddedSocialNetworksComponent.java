package com.softserve.edu.greencity.ui.pages.cabinet.editprofile;

import com.softserve.edu.greencity.ui.elements.ButtonWithIconElement;
import com.softserve.edu.greencity.ui.elements.LinkElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static  com.softserve.edu.greencity.ui.locators.EditProfileLocators.*;

/**
 * Any one of social network cards displayed at 'Edit profile' page in 'Linked social networks' section.
 * Handled by AddedSocialNetworkContainer.java
 */
public class AddedSocialNetworksComponent {

    private WebDriver driver;

    private final WebElement socialNetworkItem;
    private ButtonWithIconElement deleteLinkButton;
    private ButtonWithIconElement editLinkButton;
    private LinkElement socialNetworkLinkElement;

    public AddedSocialNetworksComponent(WebDriver driver, WebElement socialNetworkItem) {
        this.driver = driver;
        this.socialNetworkItem = socialNetworkItem;
    }

    private ButtonWithIconElement getDeleteLinkButton(){
        deleteLinkButton = new ButtonWithIconElement(driver, DELETE_SOCIAL_NETWORK);
        return deleteLinkButton;
    }

    private ButtonWithIconElement getEditLinkButton(){
        editLinkButton = new ButtonWithIconElement(driver, EDIT_SOCIAL_NETWORK);
        return editLinkButton;
    }

    private LinkElement getSocialNetworkLinkElement(){
        socialNetworkLinkElement = new LinkElement(driver, SOCIAL_NETWORK_LINK);
        return socialNetworkLinkElement;
    }

    public DeleteSocialNetworkPopUpComponent clickDeleteButton(){
        getDeleteLinkButton().click();
        return new DeleteSocialNetworkPopUpComponent(driver);
    }

    public SocialNetworkComponent clickEditLinkButton(){
        getEditLinkButton().click();
        return new SocialNetworkComponent(driver);
    }

    public String getSocialNetworkLinkElementText(){
        return getSocialNetworkLinkElement().getInnerText();
    }

}
