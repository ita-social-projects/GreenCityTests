package com.softserve.edu.greencity.ui.pages.cabinet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class HabitCardComponent {

    private WebElement habitCardLayout;

    private String habitCardTitle;
    private String habitCardDescription;
    private WebElement deleteButton;

    public HabitCardComponent(WebElement habitCardLayout) {
        this.habitCardLayout = habitCardLayout;
        initElements();
    }

    private void initElements() {
        habitCardTitle = habitCardLayout.findElement(By.cssSelector(".habit-card-caption")).getText();
        habitCardDescription = habitCardLayout.findElement(By.cssSelector(".habit-card-text")).getText();
    }

    // Page Object

    // habitCardLayout

    public WebElement getHabitCardLayout() {
        return habitCardLayout;
    }

    // habitCardTitle

    public String getHabitCardTitle() {
        //return habitCardLayout.findElement(By.cssSelector(".habit-card-caption")).getText();
        return habitCardTitle;
    }

    // habitCardText

    public String getHabitCardDescription() {
        //return habitCardLayout.findElement(By.cssSelector(".habit-card-text")).getText();
        return habitCardDescription;
    }

    // deleteButton

    public WebElement getDeleteButton() {
        return habitCardLayout.findElement(By.cssSelector(".corner-image"));
    }

    public void clickDeleteButton() {
        getDeleteButton().click();
    }

    public boolean isDisplayedDeleteButton() {
        return getDeleteButton().isDisplayed();
    }


    // Functional

    public DeleteCard delete() {
       clickDeleteButton();
       return new DeleteCard();
    }


    // Inner class for Confirmation Deleting

    class DeleteCard {

        private WebElement cancelButton;
        private WebElement confirmButton;

        public DeleteCard() {
            initElements();
        }

        private void initElements() {
            cancelButton = habitCardLayout.findElement(By.cssSelector("app-confirmation-modal .button-cancel"));
            confirmButton =  habitCardLayout.findElement(By.cssSelector("app-confirmation-modal .button-save"));
        }

        // Page Object

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

        // confirmButton

        public WebElement getConfirmButton() {
            return confirmButton;
        }

        public void clickConfirmButton() {
            getConfirmButton().click();
        }

        public boolean isDisplayedConfirmButton() {
            return getConfirmButton().isDisplayed();
        }


        // Functional

        public void confirm() {
            if (isDisplayedConfirmButton()) {
                clickConfirmButton();
            }
        }

        public void cancel() {
            if (isDisplayedCancelButton()) {
                clickCancelButton();
            }
        }

    }

    // Business Logic
}
