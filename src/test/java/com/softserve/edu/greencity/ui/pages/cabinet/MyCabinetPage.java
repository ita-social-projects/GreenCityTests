package com.softserve.edu.greencity.ui.pages.cabinet;

import com.softserve.edu.greencity.ui.pages.common.TopPart;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class MyCabinetPage extends TopPart {
    private WebElement addNewHabitButton;
    private HabitsContainer habitsContainer;
    private MyGoalsContainer goalsContainer;

    public MyCabinetPage(WebDriver driver) {
        super(driver);
    }

    public HabitsContainer getHabitsContainer() {
        return habitsContainer = new HabitsContainer(driver);
    }

    public MyGoalsContainer getGoalsContainer() {
        return goalsContainer = new MyGoalsContainer(driver);
    }

    public WebElement getAddNewHabitButton() {
        return addNewHabitButton = driver.findElement(By.id("create-button"));
    }
}
