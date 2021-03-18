package com.softserve.edu.greencity.ui.elements;

import com.softserve.edu.greencity.ui.locators.Locator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class InputElement extends BaseElement {
    public InputElement(WebDriver driver, Locator locator) {
        super(driver, locator);
    }

    public InputElement(WebElement element, Locator locator) {
        super(element, locator);
    }

    public InputElement(WebElement element) {
        super(element);
    }

    public void click() {
        this.element.click();
    }

    public void clearInput() {
        this.element.clear();
    }

    public boolean isEmpty() {
        return this.element.getAttribute("value").isEmpty();
    }
}
