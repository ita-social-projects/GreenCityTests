package com.softserve.edu.greencity.ui.elements;

import com.softserve.edu.greencity.ui.locators.Locator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ImageElement extends BaseElement{
    public ImageElement(WebDriver driver, Locator locator) {
        super(driver, locator);
    }

    public ImageElement(WebElement element, Locator locator) {
        super(element, locator);
    }

    public ImageElement(WebElement element) {
        super(element);
    }

    public String getAttribute(String attribute) {return this.element.getAttribute(attribute);}
}
