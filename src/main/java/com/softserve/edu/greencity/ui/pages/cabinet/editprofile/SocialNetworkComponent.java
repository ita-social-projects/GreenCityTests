package com.softserve.edu.greencity.ui.pages.cabinet.editprofile;

import com.softserve.edu.greencity.ui.elements.ButtonElement;
import com.softserve.edu.greencity.ui.elements.TextAreaElement;
import com.softserve.edu.greencity.ui.tools.engine.WaitsSwitcher;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.softserve.edu.greencity.ui.locators.EditProfileLocators.*;

/**
 * This class is responsible for adding social network link on 'Edit Profile' Page
 */
public class SocialNetworkComponent {

    protected WebDriverWait wait;
    private WebDriver driver;
    private WaitsSwitcher waitsSwitcher;

    private TextAreaElement linkField;
    private ButtonElement cancelButton;
    private ButtonElement addButton;

    public SocialNetworkComponent(WebDriver driver) {
        this.driver = driver;
        this.waitsSwitcher = new WaitsSwitcher(driver);
    }

    public TextAreaElement getLinkField(){
        if(linkField == null){
            linkField = new TextAreaElement(driver, SOCIAL_NETWORK_LINK_FIELD);
        }
        //return socialNetworkLayout.findElement(SOCIAL_NETWORK_LINK_FIELD.getPath());
        return linkField;
    }

    public ButtonElement getCancelButton(){
        if(cancelButton == null){
            cancelButton = new ButtonElement(driver, CANCEL_SOCIAL_NETWORK_BUTTON);
        }
//        return socialNetworkLayout.findElement(CANCEL_SOCIAL_NETWORK_BUTTON.getPath());
        return cancelButton;
    }

    public ButtonElement getAddButton(){
        if(addButton == null){
            addButton = new ButtonElement(driver, ADD_SOCIAL_NETWORK_BUTTON);
        }
//        return socialNetworkLayout.findElement(ADD_SOCIAL_NETWORK_BUTTON.getPath());
        return addButton;
    }

    public void clickCancelButton(){
        getCancelButton().click();
    }

    public void clickAddButton(){
        getAddButton().click();
    }

    public void addLink(String link){
        getLinkField().enterText(link);
    }
}
