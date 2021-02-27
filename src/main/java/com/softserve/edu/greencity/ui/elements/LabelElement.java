package com.softserve.edu.greencity.ui.elements;

import com.softserve.edu.greencity.ui.locators.Locator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LabelElement extends BaseElement{
    public LabelElement(WebDriver driver, Locator locator) {
        super(driver, locator);
    }

    public LabelElement(WebElement element, Locator locator) {
        super(element, locator);
    }

    public LabelElement(WebElement element) {
        super(element);
    }
}
