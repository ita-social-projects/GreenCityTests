package com.softserve.edu.greencity.ui.elements;

import com.softserve.edu.greencity.ui.locators.Locator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DropDownElement extends BaseElement{
    public DropDownElement(WebDriver driver, Locator locator) {
        super(driver, locator);
    }

    public DropDownElement(WebElement element, Locator locator) {
        super(element, locator);
    }

    public DropDownElement(WebElement element) {
        super(element);
    }

    public void click(){ this.element.click(); }
    
    public void choseFromOptions(By choice){this.element.findElement(choice).click();}

}
