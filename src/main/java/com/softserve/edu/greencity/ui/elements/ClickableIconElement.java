package com.softserve.edu.greencity.ui.elements;

import com.softserve.edu.greencity.ui.locators.Locator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ClickableIconElement extends IconElement{
    public ClickableIconElement(WebDriver driver, Locator locator) {
        super(driver, locator);
    }

    public ClickableIconElement(WebElement element, Locator locator) {
        super(element, locator);
    }

    public ClickableIconElement(WebElement element) {
        super(element);
    }

    public void click(){
        this.element.click();
    }

    public boolean isDisplayed(){
        return this.element.isDisplayed();
    }

    public int getIconWidth(){
        return this.element.getSize().width;
    }

    public int getIconHeigth(){
        return this.element.getSize().height;
    }
}
