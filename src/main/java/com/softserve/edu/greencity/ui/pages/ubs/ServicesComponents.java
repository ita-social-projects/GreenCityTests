package com.softserve.edu.greencity.ui.pages.ubs;

import com.softserve.edu.greencity.ui.locators.ubs.OrderDetailsPageLocators;
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
    public WebElement getInput() {
        return servicesItem.findElement(OrderDetailsPageLocators.SERVICES_INPUT.getPath());
    }
    public WebElement getServiceName(){
        return servicesItem.findElement(SERVICE_NAME.getPath());
    }
    public WebElement getTotal(){
        return servicesItem.findElement(SERVICE_TOTAL.getPath());
    }

    public WebElement getVolumeOrCost(){return servicesItem.findElement(SERVICE_VOLUME_COST.getPath());}

}
