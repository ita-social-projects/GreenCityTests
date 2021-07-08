package com.softserve.edu.greencity.ui.pages.ubs;

import com.softserve.edu.greencity.ui.tools.engine.WaitsSwitcher;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static com.softserve.edu.greencity.ui.locators.comments.CommentLocator.*;
import static com.softserve.edu.greencity.ui.locators.comments.CommentLocator.COMMENT_EDIT_BUTTON;
import static com.softserve.edu.greencity.ui.locators.ubs.OrderDetailsPageLocators.*;

public class AdditionalCertificatesComponents {
    private final WebDriver driver;
    private final WebElement certificateItem;
    private final WaitsSwitcher waitsSwitcher;
    private String activeColor = "#13aa57";

    public AdditionalCertificatesComponents(WebDriver driver, WebElement certificateItem) {
        this.driver = driver;
        this.certificateItem = certificateItem;
        this.waitsSwitcher = new WaitsSwitcher(driver);
    }

    public WebElement getCertificateInput() {
        return certificateItem.findElement(ADDITIONAL_CERTIFICATE_INPUT.getPath());
    }
    public WebElement getActivateAdditionalCertificateButton(){
        return certificateItem.findElement(ADDITIONAL_CERTIFICATE_ACTIVATE_BUTTON.getPath());
    }

    public boolean isActivateButtonActive() {
        return (getButtonColor().equalsIgnoreCase(activeColor)) ? true: false;
    }
    public String getButtonColor(){
      return  certificateItem.findElement(ADDITIONAL_CERTIFICATE_ACTIVATE_BUTTON.getPath()).getCssValue("background");
    }

}
