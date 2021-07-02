package com.softserve.edu.greencity.ui.pages.common;

import com.softserve.edu.greencity.ui.pages.cabinet.LoginComponent;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Getter
public class WelcomePage extends TopPart {
private By myHabitsUnsignedLink;
    public WelcomePage(WebDriver driver) {
        super(driver);
        initElements();
    }

    private void initElements() {

    }
    public LoginComponent clickMyHabitsUnsignedLink(){
        myHabitsUnsignedLink = By.cssSelector("header ul > li > a[href*='/profile']");
        logger.info("Unsigned user click My habits ");
        searchElementByCss(myHabitsUnsignedLink).click();
        return new  LoginComponent(driver);
    }
    // Page Object

    // Functional

    // Business Logic

}