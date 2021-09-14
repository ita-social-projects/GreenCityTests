package com.softserve.edu.greencity.ui.elements;

import com.softserve.edu.greencity.ui.locators.Locator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckBoxElement extends BaseElement{
    public CheckBoxElement(WebDriver driver, Locator locator) {
        super(driver, locator);
    }

    public CheckBoxElement(WebElement element, Locator locator) {
        super(element, locator);
    }

    public CheckBoxElement(WebElement element) {
        super(element);
    }

    public void click(){
        this.element.click();
    }

    public boolean isChecked(){
        return this.element.isSelected();
    }

    public String getAtribute(String atribute){
        return this.element.getAttribute(atribute);
    }
}
