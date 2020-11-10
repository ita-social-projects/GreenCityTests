package com.softserve.edu.greencity.ui.pages.cabinet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CreateHabitDropdown {
    private WebDriver driver;

    private WebElement saveButton;
    private WebElement cancelButton;
    private WebElement closeButton;

    public CreateHabitDropdown(WebDriver driver) {
        this.driver = driver;
        initElements();
    }

    private void initElements() {
        saveButton = driver.findElement(By.cssSelector("div.splitter + div .button-save"));
        cancelButton = driver.findElement(By.cssSelector("div.splitter + div .button-cancel"));
        closeButton = driver.findElement(By.cssSelector("img[src*='close']"));
    }
}
