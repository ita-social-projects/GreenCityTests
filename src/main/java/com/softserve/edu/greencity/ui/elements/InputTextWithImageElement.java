package com.softserve.edu.greencity.ui.elements;

import com.softserve.edu.greencity.ui.locators.Locator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class InputTextWithImageElement extends InputTextElement {
    public InputTextWithImageElement(WebDriver driver, Locator locator) {
        super(driver, locator);
    }

    public InputTextWithImageElement(WebElement element, Locator locator) {
        super(element, locator);
    }

    public InputTextWithImageElement(WebElement element) {
        super(element);
    }

    public void click() {
        this.element.click();
    }

    public void enterText(String text) {
        this.element.sendKeys(text);
    }

    public String getTextFromInput() {
        return element.getAttribute("value");
    }

    public void clearInput() {
        this.element.clear();
    }

    public boolean isEmpty() {
        return this.element.getAttribute("value").isEmpty();
    }

    public void changeText(String newText) {
        this.element.click();
        this.element.clear();
        this.element.sendKeys(newText);
    }

    public int getImageWight() {
        return this.element.getSize().width;
    }

    public int getImageHeight() {
        return this.element.getSize().height;
    }

    public boolean isImageVisible() {
        return this.element.isDisplayed();
    }
}
