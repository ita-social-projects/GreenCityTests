package com.softserve.edu.greencity.ui.elements;

import com.softserve.edu.greencity.ui.locators.Locator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class InputTextWithImageElement extends InputTextElement{
    public InputTextWithImageElement(WebDriver driver, Locator locator) {
        super(driver, locator);
    }

    public InputTextWithImageElement(WebElement element, Locator locator) {
        super(element, locator);
    }

    public InputTextWithImageElement(WebElement element) {
        super(element);
    }
}
