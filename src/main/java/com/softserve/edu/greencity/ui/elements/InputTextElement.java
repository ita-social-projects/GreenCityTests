package com.softserve.edu.greencity.ui.elements;

import com.softserve.edu.greencity.ui.locators.Locator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class InputTextElement extends BaseElement{
    public InputTextElement(WebDriver driver, Locator locator) {
        super(driver, locator);
    }

    public InputTextElement(WebElement element, Locator locator) {
        super(element, locator);
    }

    public InputTextElement(WebElement element) {
        super(element);
    }
}
