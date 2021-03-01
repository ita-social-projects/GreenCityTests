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

    public void click(){
        this.element.click();
    }

    public void enterText(String text){
        this.element.sendKeys(text);
    }

    public void clearText(){
        this.element.clear();
    }

    public void reEnterText(String newText){
        this.element.click();
        this.element.clear();
        this.element.sendKeys(newText);
    }

    public String getTextFromInput(){
        return element.getAttribute("value");
    }

    public boolean isEmpty(){
        return this.element.getAttribute("value").isEmpty();
    }
}
