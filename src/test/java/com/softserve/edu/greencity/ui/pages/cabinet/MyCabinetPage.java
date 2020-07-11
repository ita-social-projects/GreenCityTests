package com.softserve.edu.greencity.ui.pages.cabinet;

import com.softserve.edu.greencity.ui.pages.common.TopPart;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;


public class MyCabinetPage extends TopPart {
    private WebElement addNewHabitButton;
    private HabitsContainer habitsContainer;
    private MyGoalsContainer goalsContainer;

    public MyCabinetPage(WebDriver driver) {
        super(driver);
        Wait<WebDriver> wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfAllElements(getAddNewHabitButton()));
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
