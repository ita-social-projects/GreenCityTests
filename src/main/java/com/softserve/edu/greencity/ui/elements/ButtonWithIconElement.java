package com.softserve.edu.greencity.ui.elements;

import com.softserve.edu.greencity.ui.locators.Locator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ButtonWithIconElement extends ButtonElement{
    public ButtonWithIconElement(WebDriver driver, Locator locator) {
        super(driver, locator);
    }

    public ButtonWithIconElement(WebElement element, Locator locator) {
        super(element, locator);
    }

    public ButtonWithIconElement(WebElement element) {
        super(element);
    }

    public void click(){
        this.element.click();
    }

    public boolean isDisplayed(){
        return this.element.isDisplayed();
    }

    public boolean isActive(){ return this.element.isEnabled();}
}
