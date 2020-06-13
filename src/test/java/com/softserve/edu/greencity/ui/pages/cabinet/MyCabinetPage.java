package com.softserve.edu.greencity.ui.pages.cabinet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.softserve.edu.greencity.ui.data.Goal;
import com.softserve.edu.greencity.ui.data.Habit;
import com.softserve.edu.greencity.ui.data.HabitItem;
import com.softserve.edu.greencity.ui.pages.common.TopPart;


public class MyCabinetPage extends TopPart {
    private WebElement addNewHabitButton;

    private HabitsContainer habitsContainer;

    private MyGoalsContainer goalsContainer;

    public MyCabinetPage(WebDriver driver) {
        super(driver);
        initElements();
    }

    private void initElements() {
        //TODO make separate lazy methods for this initiation
//        habitsContainer = new HabitsContainer(driver);
//        goalsContainer = new MyGoalsContainer(driver);

        addNewHabitButton = driver.findElement(By.id("create-button"));
    }

    // Page Object

    // habitsContainer
    public HabitsContainer getHabitsContainer() {
        return new HabitsContainer(driver);
    }

    // goalsContainer

    public MyGoalsContainer getGoalsContainer() {
        return goalsContainer;
    }

    // addNewHabitButton

    public WebElement getAddNewHabitButton() {
        return addNewHabitButton;
    }

    private void clickAddNewHabitButton() {
        getAddNewHabitButton().click();
    }


    // Functional

    public boolean isAvailableHabit(Habit habit) {
        return getHabitsContainer().findHabitComponent(habit) != null ? true : false;
    }

    public HabitComponent getHabitComponent(Habit habit) {
        return getHabitsContainer().findHabitComponent(habit);
    }

    public boolean isAvailableGoal(Goal goal) {
        return getGoalsContainer().findGoal(goal) != null ? true : false;
    }

    public GoalComponent getGoalComponent(Goal goal) {
        return getGoalsContainer().findGoal(goal);
    }

    public MyCabinetPage refresh() {
        driver.navigate().refresh();
        return new MyCabinetPage(driver);
    }

    // Business Logic

    /**
     * Go to NewHabitDropdown.
     *
     * @return NewHabitDropdown
     */
    public CreateHabitDropdown gotoCreateHabitDropdown() {
        clickAddNewHabitButton();
        return new CreateHabitDropdown(driver);
    }

    /**
     * Select goal. Mark as done.
     *
     * @param goal
     * @return MyCabinetPage
     */
    public MyCabinetPage doGoal(Goal goal) {
        getGoalsContainer().selectGoal(goal);
        return new MyCabinetPage(driver);
    }

    /**
     * Deselect goal.
     *
     * @param goal
     * @return MyCabinetPage
     */
    public MyCabinetPage undoGoal(Goal goal) {
        getGoalsContainer().deselectGoal(goal);
        return new MyCabinetPage(driver);
    }

    /**
     * Show more goals if it more than 3 one.
     *
     * @return
     */
    public MyCabinetPage showMoreGoals() {
        getGoalsContainer().showMoreGoals();
        return new MyCabinetPage(driver);
    }

    /**
     * Show less goals if it less than 3 one.
     *
     * @return
     */
    public MyCabinetPage showLessGoals() {
        getGoalsContainer().showLessGoals();
        return new MyCabinetPage(driver);
    }

    /**
     * Add all info about habit for current day.
     *
     * @param habit
     * @return MyCabinetPage
     */
    public MyCabinetPage addTodaysHabitInfo(HabitItem habit) {
        getHabitsContainer().addHabitInfo(habit);
        return new MyCabinetPage(driver);
    }
}