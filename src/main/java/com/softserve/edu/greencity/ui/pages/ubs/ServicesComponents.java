package com.softserve.edu.greencity.ui.pages.ubs;

import com.softserve.edu.greencity.ui.elements.InputElement;
import com.softserve.edu.greencity.ui.elements.LabelElement;
import com.softserve.edu.greencity.ui.tools.engine.WaitsSwitcher;
import static com.softserve.edu.greencity.ui.locators.ubs.OrderDetailsPageLocators.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ServicesComponents {
    private final WebDriver driver;
    private final WebElement servicesItem;
    private final WaitsSwitcher waitsSwitcher;

    public ServicesComponents(WebDriver driver, WebElement certificateItem) {
        this.driver = driver;
        this.servicesItem = certificateItem;
        this.waitsSwitcher = new WaitsSwitcher(driver);
    }
    public InputElement getInput() {
        return new InputElement(driver, SERVICES_INPUT);
    }
    public LabelElement getServiceName(){
        return new LabelElement(driver, SERVICE_NAME);
    }
    public LabelElement getTotal(){
        return new LabelElement(driver, SERVICE_TOTAL);
    }

    public LabelElement getVolumeOrCost(){
        return new LabelElement(driver, SERVICE_VOLUME_COST);
    }

}
