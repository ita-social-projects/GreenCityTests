package com.softserve.edu.greencity.ui.pages.ubs;

import com.softserve.edu.greencity.ui.locators.ubs.FondyPaymentPageLocator;
import com.softserve.edu.greencity.ui.locators.ubs.ResultOfFondyPage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FondyPaymentPage {
    private WebDriver driver;
    private WebElement numberOfCard;
    private WebElement dateOfTheEndCard;
    private WebElement cvvOfCard;
    private WebElement email;
    private WebElement pay;
    private WebElement continueButton;

    public FondyPaymentPage(WebDriver driver) {
        this.driver = driver;
    }

    private WebElement getNumberOfCard() {
        return driver.findElement(FondyPaymentPageLocator.NUMBER_OF_THE_CARD_INPUT.getPath());
    }

    public FondyPaymentPage setNumberOfCard(String number) {
        getNumberOfCard().sendKeys(number);
        return this;
    }

    private WebElement getPayButton() {
        return driver.findElement(FondyPaymentPageLocator.PAY_BUTTON.getPath());
    }

    public ResultOfFondyPage clickContinueButton() {
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("setTimeout(function(){ $('button').trigger('click'); }, 1000);");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new ResultOfFondyPage(driver);
    }

    public FondyPaymentPage clickPayButton() {
        getPayButton().click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this;
    }

    private WebElement getContinueButton() {
        return driver.findElement(FondyPaymentPageLocator.CONTINUE_BUTTON.getPath());
    }

    private WebElement getDateOfTheEndCard() {
        return driver.findElement(FondyPaymentPageLocator.DATE_OF_THE_END_OF_THE_CARD.getPath());
    }

    public FondyPaymentPage setDateOfTheEndCard(String date) {
        getDateOfTheEndCard().sendKeys(date);
        return this;
    }

    private WebElement getCvvOfCard() {
        return driver.findElement(FondyPaymentPageLocator.CVV_OF_CARD_INPUT.getPath());
    }

    public FondyPaymentPage setCvvOfCard(String cvv) {
        getCvvOfCard().sendKeys(cvv);
        return this;
    }

    public FondyPaymentPage clearNumberOfCardField() {
        getNumberOfCard().clear();
        return this;
    }

    public FondyPaymentPage clearDateOfTheEndCardField() {
        getDateOfTheEndCard().clear();
        return this;
    }

    public FondyPaymentPage clearCvvOfCardField() {
        getCvvOfCard().clear();
        return this;
    }

    private WebElement getEmail() {
        return driver.findElement(FondyPaymentPageLocator.EMAIL_FIELD.getPath());
    }

    public FondyPaymentPage setEmail(String email) {
        getEmail().sendKeys(email);
        return this;
    }

    public FondyPaymentPage clearEmail() {
        getEmail().clear();
        return this;
    }

    public ResultOfFondyPage setSuccessfulPaymentCredits(){
        setCvvOfCard("123")
                .setDateOfTheEndCard("1224")
                .setNumberOfCard("6666444455551111")
                .setEmail("testmail@mail.com")
                .clickPayButton()
                .clickContinueButton();
        return new ResultOfFondyPage(driver);
    }
}
