package com.softserve.edu.greencity.ui.elements;

import com.softserve.edu.greencity.ui.locators.Locator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ButtonElement extends BaseElement{
    public ButtonElement(WebDriver driver, Locator locator) {
        super(driver, locator);
    }

    public ButtonElement(WebElement element, Locator locator) {
        super(element, locator);
    }

    public ButtonElement(WebElement element) {
        super(element);
    }

    public void click(){
        this.element.click();
    }
    public void sendKeys(String keys) {
        this.element.sendKeys(keys);
    }
    public boolean isActive(){ return this.element.isEnabled();}

    public void sendKeys(String key){ this.element.sendKeys(key);}
}
