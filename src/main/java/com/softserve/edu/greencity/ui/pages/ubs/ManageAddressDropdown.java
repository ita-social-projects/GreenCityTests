package com.softserve.edu.greencity.ui.pages.ubs;

import com.softserve.edu.greencity.ui.elements.LabelElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ManageAddressDropdown {
    private WebDriver driver;

    private LabelElement cityLabel;
    private WebElement cityInput;

    public ManageAddressDropdown(WebDriver driver) {
        this.driver = driver;
        initElements();
    }

    private void initElements() {
//        cityLabel = driver.findElement(By.cssSelector(""));
//        cityInput = driver.findElement(By.cssSelector(""));
    }
}
