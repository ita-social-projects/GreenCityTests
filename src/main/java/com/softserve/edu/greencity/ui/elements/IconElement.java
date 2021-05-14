package com.softserve.edu.greencity.ui.elements;

import com.softserve.edu.greencity.ui.locators.Locator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class IconElement extends BaseElement{
    public IconElement(WebDriver driver, Locator locator) {
        super(driver, locator);
    }

    public IconElement(WebElement element, Locator locator) {
        super(element, locator);
    }

    public IconElement(WebElement element) {
        super(element);
    }

    public int getIconWidth(){
        return this.element.getSize().width;
    }

    public int getIconHeigth(){
        return this.element.getSize().height;
    }

    public boolean isDisplayed(){
        return this.element.isDisplayed();
    }

    public void click(){ this.element.click();}

    public String getAttribute(String attribute) {return this.element.getAttribute(attribute);}
}
