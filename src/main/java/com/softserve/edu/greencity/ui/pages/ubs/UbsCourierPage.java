package com.softserve.edu.greencity.ui.pages.ubs;

import com.softserve.edu.greencity.ui.locators.ubs.UBSCourierBasePageLocators;
import com.softserve.edu.greencity.ui.locators.ubs.UbsCourierPageLocators;
import com.softserve.edu.greencity.ui.pages.common.TopPart;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class UbsCourierPage extends UBSCourierBasePage {
    private WebElement callUpTheCourierButton;

    public UbsCourierPage(WebDriver driver) {
        super(driver);
    }

    public ChooseLocationPopup clickOnCallUpTheCourierButton(){
        getCallUpTheCourierButton().click();
        return new ChooseLocationPopup(driver);
    }

    public WebElement getCallUpTheCourierButton() {
        if (callUpTheCourierButton==null){
            callUpTheCourierButton = driver.findElement(UbsCourierPageLocators.CALL_UP_THE_COURIER_BUTTON.getPath());
        }
        return callUpTheCourierButton;
    }
}
