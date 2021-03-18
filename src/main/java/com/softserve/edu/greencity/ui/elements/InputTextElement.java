package com.softserve.edu.greencity.ui.elements;

import com.softserve.edu.greencity.ui.locators.Locator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class InputTextElement extends InputElement{
    public InputTextElement(WebDriver driver, Locator locator) {
        super(driver, locator);
    }

    public InputTextElement(WebElement element, Locator locator) {
        super(element, locator);
    }

    public InputTextElement(WebElement element) {
        super(element);
    }

    public void enterText(String text){
        this.element.sendKeys(text);
    }

    public void reEnterText(String newText){
        this.element.click();
        this.element.clear();
        this.element.sendKeys(newText);
    }

    public String getTextFromInput(){
        return element.getAttribute("value");
    }

}
