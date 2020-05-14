package com.softserve.edu.greencity.ui.pages.cabinet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.softserve.edu.greencity.ui.data.HabitCard;

public class CreateHabitDropdown {

    private WebDriver driver;

    private HabitCardsContainer habitsContainer;

    private WebElement saveButton;
    private WebElement  cancelButton;
    private WebElement closeButton;

    public CreateHabitDropdown(WebDriver driver) {
        this.driver = driver;
        initElements();
    }

    private void initElements() {
        //habitsContainer = new HabitCardsContainer(driver);

        saveButton = driver.findElement(By.cssSelector("div.splitter + div .button-save"));
        cancelButton = driver.findElement(By.cssSelector("div.splitter + div .button-cancel"));
        closeButton = driver.findElement(By.cssSelector("img[src*='close']"));
    }

    // Page Object

    //chosenHabitsContainer

    public HabitCardsContainer getHabitsContainer() {
       // return habitsContainer;
        return new HabitCardsContainer(driver);
    }

    // saveButton

    public WebElement getSaveButton() {
        return saveButton;
    }

    public void clickSaveButton() {
        getSaveButton().click();
    }

    public boolean isDisplayedSaveButton() {
        return getSaveButton().isDisplayed();
    }

    // cancelButton

    public WebElement getCancelButton() {
        return cancelButton;
    }

    public void clickCancelButton() {
        getCancelButton().click();
    }

    public boolean isDisplayedCancelButton() {
        return getCancelButton().isDisplayed();
    }

    // closeButton

    public WebElement getCloseButton() {
        return closeButton;
    }

    public void clickCloseButton() {
        getCloseButton().click();
    }

    public boolean isDisplayedCloseButton() {
        return getCloseButton().isDisplayed();
    }


    // Functional

    public boolean isChosenHabitCard(HabitCard card) {
        return getHabitsContainer().findChosenHabitCard(card) != null ? true : false;
    }

    public boolean isVisibleWarning() {
        return getHabitsContainer().isVisibleDeleteMessageWarning();
    }


    // Business Logic

    /**
     * Close CreateHabitDropdown without saving changes.
     * @return MyCabinetPage
     */
    public MyCabinetPage cancelCreateHabitDropdown() {
        if(isDisplayedCancelButton()) {
            clickCancelButton();
        }
        return new MyCabinetPage(driver);
    }

    /**
     * Close CreateHabitDropdown with saving changes.
     * @return MyCabinetPage
     */
    public MyCabinetPage saveCreateHabitDropdown() {
        if(isDisplayedSaveButton()) {
            clickSaveButton();
        }

        // TODO
        // do not close dropdown when click save button
        clickCloseButton();
        return new MyCabinetPage(driver);
    }

    /**
     * Close CreateHabitDropdown.
     * @return MyCabinetPage
     */
    public MyCabinetPage closeCreateHabitDropdown() {
        // do not work
        if(isDisplayedCloseButton()) {
            clickCloseButton();
        }
        return new MyCabinetPage(driver);
    }

    /**
     * Delete habit card from chosen cards.
     * @param habit
     * @return CreateHabitDropdown
     */
    public CreateHabitDropdown deleteAndConfirmHabitCard(HabitCard card) {
        getHabitsContainer().deleteAndConfirmHabitCard(card);
        return new CreateHabitDropdown(driver);
    }

    /**
     * Delete and cancel deleting habit card from chosen cards.
     * @param habit
     * @return CreateHabitDropdown
     */
    public CreateHabitDropdown deleteAndCancelHabitCard(HabitCard card) {
        getHabitsContainer().deleteAndCancelHabitCard(card);
        return new CreateHabitDropdown(driver);
    }

    /**
     * Delete alone habit card from chosen cards.
     * @param habit
     * @return CreateHabitDropdown
     */
    public CreateHabitDropdown deleteAloneHabitCard() {
        getHabitsContainer().getAloneHabitCard().delete();
        return new CreateHabitDropdown(driver);
    }

    /**
     * Add habit card to chosen cards.
     * @param habit
     * @return CreateHabitDropdown
     */
    public CreateHabitDropdown addHabitCard(HabitCard card) {
        getHabitsContainer().addHabitCard(card);
        return new CreateHabitDropdown(driver);
    }

}
