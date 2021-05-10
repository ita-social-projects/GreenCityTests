package com.softserve.edu.greencity.ui.elements;

import com.softserve.edu.greencity.ui.locators.Locator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TextAreaElement extends BaseElement {
    public TextAreaElement(WebDriver driver, Locator locator) {
        super(driver, locator);
    }
    

    public TextAreaElement(WebElement element, Locator locator) {
        super(element, locator);
    }

    public TextAreaElement(WebElement element) {
        super(element);
    }

    public boolean isEmpty(){return this.element.getText().isEmpty();}

    public void clearText(){this.element.clear();}

    public void enterText(String text){ this.element.sendKeys(text);}

    public String getText(){return this.element.getAttribute("value");}

}
