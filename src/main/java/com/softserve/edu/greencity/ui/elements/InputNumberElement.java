package com.softserve.edu.greencity.ui.elements;

import com.softserve.edu.greencity.ui.locators.Locator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class InputNumberElement extends BaseElement {
    public InputNumberElement(WebDriver driver, Locator locator) {
        super(driver, locator);
    }

    public InputNumberElement(WebElement element, Locator locator) {
        super(element, locator);
    }

    public InputNumberElement(WebElement element) {
        super(element);
    }

    public void click() {
        this.element.click();
    }

    public void enterNumber(String numb) {
        this.element.sendKeys(numb);
    }

    public void clearInput() {
        this.element.clear();
    }

    public String getNumbFromInput() {
        return element.getAttribute("value");
    }

    public boolean isEmpty() {
        return this.element.getAttribute("value").isEmpty();
    }

    public void changeNumb(String newNumb) {
        this.element.click();
        this.element.clear();
        this.element.sendKeys(newNumb);

    }


}
