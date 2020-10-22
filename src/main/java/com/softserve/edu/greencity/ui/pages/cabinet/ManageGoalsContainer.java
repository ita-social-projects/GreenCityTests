package com.softserve.edu.greencity.ui.pages.cabinet;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.softserve.edu.greencity.ui.data.Goal;

public class ManageGoalsContainer extends GoalsContainerPart {

    private final String GOAL_COMPONENTS = "app-add-goal-list app-add-goal-item div.row";

    public ManageGoalsContainer(WebDriver driver) {
        super(driver);
        initElements();
    }

    private void initElements() {
        goalComponents = new ArrayList<GoalComponent>();
        for (WebElement current : driver.findElements(By.cssSelector(GOAL_COMPONENTS))) {
            goalComponents.add(new GoalComponent(current));
        }
    }

    // Page Object


    // Functional


    // Business Logic

    /**
     * Delete goal.
     * @param title
     * @return ManageGoalsContainer
     */
    public ManageGoalsContainer deleteGoalComponent(Goal goal) {
        GoalComponent currentGoal = findGoal(goal);
        if (currentGoal != null && currentGoal.isDisplayedDeleteNewGoalButton()) {
            currentGoal.clickDeleteNewGoalButton();
        }
        return new ManageGoalsContainer(driver);
    }

    /**
     * Delete newly created goal.
     * @return ManageGoalsContainer
     */
    public  ManageGoalsContainer deleteNewGoalComponent() {
        getLastGoal().clickDeleteNewGoalButton();
        return new ManageGoalsContainer(driver);
    }

    /**
     * Set title to goal.
     * @param goal
     * @return ManageGoalsContainer
     */
    public  ManageGoalsContainer setNewGoalComponentTitle(Goal goal) {
        getLastGoal().setNewTitle(goal.getTitle());
        return new ManageGoalsContainer(driver);
    }

    /**
     * Select goal.
     * @param goal
     * @return ManageGoalsContainer
     */
    public ManageGoalsContainer selectGoal(Goal goal) {
        GoalComponent goalComponent = findGoal(goal);
        if (goalComponent != null) {
            goalComponent.select();
        }
        return new ManageGoalsContainer(driver);
    }

    /**
     * Deselect goal.
     * @param goal
     * @return ManageGoalsContainer
     */
    public ManageGoalsContainer deselectGoal(Goal goal) {
        GoalComponent goalComponent = findGoal(goal);
        if (goalComponent != null) {
            goalComponent.deselect();
        }
        return new ManageGoalsContainer(driver);
    }

}