package com.softserve.edu.greencity.ui.pages.cabinet;
import com.softserve.edu.greencity.ui.elements.ButtonElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Locale;

import static com.softserve.edu.greencity.ui.locators.EcoNewsPageLocator.LANGUAGE_BUTTONS;
import static com.softserve.edu.greencity.ui.locators.EcoNewsPageLocator.LANGUAGE_SWITCHER;

public class LanguageComponents {

    private final WebDriver driver;
    private ButtonElement enButton;
    private ButtonElement ruButton;
    private ButtonElement uaButton;

    public LanguageComponents (WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getLanguageSwitchWebElement() {
        return driver.findElement(LANGUAGE_SWITCHER.getPath());
    }

    public LanguageComponents clickEnLanguage() {
        initLanguageButtons();
        this.enButton.click();
        return this;
    }

    public LanguageComponents clickUaLanguage() {
        initLanguageButtons();
        this.uaButton.click();
        return this;
    }

    public LanguageComponents clickRuLanguage() {
        initLanguageButtons();
        this.ruButton.click();
        return this;
    }

    public void initLanguageButtons() {
        getLanguageSwitchWebElement().click();
        List<WebElement> elements = driver.findElements(LANGUAGE_BUTTONS.getPath());
        for (WebElement element : elements) {
            switch (element.getText()) {
                case "En":
                    enButton = new ButtonElement(element);
                    break;
                case "Ua":
                    uaButton = new ButtonElement(element);
                    break;
                case "Ru":
                    ruButton = new ButtonElement(element);
                    break;
            }
        }
    }

    public Locale getLanguageLocale(){
        String languageButton = driver.findElement(LANGUAGE_BUTTONS.getPath()).getText();
        Locale locale;
        switch (languageButton){
            case "Ua":
                locale = new Locale("uk", "UA");
                break;
            case "Ru":
                locale = new Locale("ru", "RU");
                break;
            default:
                locale = Locale.ENGLISH;
        }
        return locale;
    }

    public void changeLanguage(String language){
        switch(language){
            case "ua":
                clickUaLanguage();
                break;
            case "en":
                clickEnLanguage();
                break;
            case "ru":
                clickRuLanguage();
                break;
        }
    }
}
