package com.softserve.edu.greencity.ui.pages.cabinet;

import com.softserve.edu.greencity.ui.pages.common.TopPart;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MyCabinetPage extends TopPart  {
    protected WebDriverWait wait;
    private By addNewHabitButton = By.xpath("//*[@id = 'create-button']");
    private HabitsContainer habitsContainer;
    private MyGoalsContainer goalsContainer;

    public MyCabinetPage(WebDriver driver) {
        super(driver);
        checkElements();
    }
    @Step
    private void checkElements() {
        wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(getAddNewHabitButton()));
    }
    @Step
    public HabitsContainer getHabitsContainer() {
        return habitsContainer = new HabitsContainer(driver);
    }
    @Step
    public MyGoalsContainer getGoalsContainer() {
        return goalsContainer = new MyGoalsContainer(driver);
    }
    @Step
    public WebElement getAddNewHabitButton() {
        return searchElementByXpath(addNewHabitButton);
    }
}
