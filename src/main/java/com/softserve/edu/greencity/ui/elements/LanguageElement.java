package com.softserve.edu.greencity.ui.elements;
import org.openqa.selenium.WebElement;

public class LanguageElement extends BaseElement {

    private ButtonElement enButton;
    private ButtonElement ruButton;
    private ButtonElement uaButton;

    public LanguageElement(WebElement element) {
        super(element);
    }

    public void initLanguageButtons(WebElement element){
            switch (element.getText()){
                case "En":
                    enButton = new ButtonElement(this.element);
                    break;
                case "Ua":
                    uaButton = new ButtonElement(this.element);
                    break;
                case "Ru":
                    ruButton = new ButtonElement(this.element);
                    break;
            }
        }


    public void clickLanguage(String text) {
        switch (text){
            case "En":
                enButton.click();
                break;
            case "Ua":
                uaButton.click();
                break;
            case "Ru":
                ruButton.click();
                break;
        }
    }
}
