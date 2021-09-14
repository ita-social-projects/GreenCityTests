package com.softserve.edu.greencity.ui.pages.ubs;

import com.softserve.edu.greencity.ui.elements.DropDownElement;
import com.softserve.edu.greencity.ui.elements.LabelElement;
import com.softserve.edu.greencity.ui.locators.ubs.AddAddressPopupLocators;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ManageAddressDropdown {
    private WebDriver driver;

    private LabelElement cityLabel;
    private DropDownElement cityInput;

    public ManageAddressDropdown(WebDriver driver) {
        this.driver = driver;
    }

    public ManageAddressDropdown chooseCity(AddAddressPopupComponent city){
        cityInput.click();
        cityInput = new DropDownElement(driver, AddAddressPopupLocators.CITY_INPUT);
        city.getStreetInput();
        return this;
    }
}
