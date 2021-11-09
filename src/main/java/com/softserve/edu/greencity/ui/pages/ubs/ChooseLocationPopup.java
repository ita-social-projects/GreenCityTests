package com.softserve.edu.greencity.ui.pages.ubs;

import com.softserve.edu.greencity.ui.locators.ubs.ChooseLocationPopupLocators;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ChooseLocationPopup {
    private WebDriver driver;
    private WebElement continueButton;
    private WebElement backButton;
    private WebDriverWait wait;

    public ChooseLocationPopup(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
    }

    public OrderDetailsPage clickOnContinueButton(){
        wait.until(ExpectedConditions.elementToBeClickable(getContinueButton()));
        getContinueButton().click();
        return new OrderDetailsPage(driver);
    }

    public WebElement getContinueButton() {
        if (continueButton == null) {
            continueButton = driver.findElement(ChooseLocationPopupLocators.CHOOSE_LOCATION_CONTINUE_POPUP_BUTTON.getPath());
        }
        return continueButton;
    }

    public WebElement getBackButton() {
        return backButton;
    }
}
