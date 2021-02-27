package com.softserve.edu.greencity.ui.elements;

import com.softserve.edu.greencity.ui.locators.Locator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class IconElement extends BaseElement{
    public IconElement(WebDriver driver, Locator locator) {
        super(driver, locator);
    }

    public IconElement(WebElement element, Locator locator) {
        super(element, locator);
    }

    public IconElement(WebElement element) {
        super(element);
    }
}
