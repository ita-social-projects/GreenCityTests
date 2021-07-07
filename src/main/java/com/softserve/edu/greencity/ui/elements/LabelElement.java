package com.softserve.edu.greencity.ui.elements;

import com.softserve.edu.greencity.ui.locators.Locator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;

public class LabelElement extends BaseElement {
    public LabelElement(WebDriver driver, Locator locator) {
        super(driver, locator);
    }

    public LabelElement(WebElement element, Locator locator) {
        super(element, locator);
    }

    public LabelElement(WebElement element) {
        super(element);
    }

    public boolean isDisplayedLabel(){return this.element.isDisplayed();} ///

    public String getColor(){return this.element.getCssValue("color");}
    public String getColorHex(){return Color.fromString(getColor()).asHex();}

    public String getText(){return this.element.getText();}
    public String getFont() {
        return this.element.getCssValue("font-family");
    }

    public String getSize() {
        return this.element.getCssValue("font-size");
    }

    public void click(){
        this.element.click();
    }
}
