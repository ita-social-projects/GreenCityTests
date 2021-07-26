package com.softserve.edu.greencity.ui.pages.ubs;

import com.softserve.edu.greencity.ui.tools.engine.WaitsSwitcher;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static com.softserve.edu.greencity.ui.locators.ubs.OrderDetailsPageLocators.*;

public class AnotherOrderNumberComponents {
    private final WebDriver driver;
    private final WebElement anotherOrder;
    private final WaitsSwitcher waitsSwitcher;

    public AnotherOrderNumberComponents(WebDriver driver, WebElement anotherOrder) {
        this.driver = driver;
        this.anotherOrder = anotherOrder;
        this.waitsSwitcher = new WaitsSwitcher(driver);
    }

    public WebElement getAddAnotherOrderButton() {
        return anotherOrder.findElement(ADD_ANOTHER_ORDER_BUTTON.getPath());
    }
    public WebElement getAnotherOrderInput() {
        return anotherOrder.findElement(ANOTHER_ORDER_NUMBER_INPUT.getPath());
    }
}
