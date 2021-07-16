package com.softserve.edu.greencity.ui.pages.ubs;

import com.softserve.edu.greencity.ui.tools.engine.WaitsSwitcher;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static com.softserve.edu.greencity.ui.locators.ubs.OrderDetailsPageLocators.ADDITIONAL_CERTIFICATE_INPUT;
import static com.softserve.edu.greencity.ui.locators.ubs.OrderDetailsPageLocators.SERVICES_INPUT;

public class ServicesComponents {
    private final WebDriver driver;
    private final WebElement servicesItem;
    private final WaitsSwitcher waitsSwitcher;

    public ServicesComponents(WebDriver driver, WebElement certificateItem) {
        this.driver = driver;
        this.servicesItem = certificateItem;
        this.waitsSwitcher = new WaitsSwitcher(driver);
    }
    public WebElement getInput() {
        return servicesItem.findElement(SERVICES_INPUT.getPath());
    }
}
