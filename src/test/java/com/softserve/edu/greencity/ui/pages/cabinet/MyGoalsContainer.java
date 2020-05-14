package com.softserve.edu.greencity.ui.pages.cabinet;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.softserve.edu.greencity.ui.data.Goal;

public class MyGoalsContainer extends GoalsContainerPart{

    private final int MY_GOALS_MAX_COUNT_WITHOUT_SHOW_MORE = 3;

    private WebElement viewAllGoalsButton;

    public MyGoalsContainer(WebDriver driver) {
        super(driver);
        initElements();
    }

    private void initElements() {
        goalComponents = new ArrayList<GoalComponent>();
        driver.findElements(By.cssSelector("app-goal-item")).forEach(goal -> goalComponents.add(new GoalComponent(goal)));
    }

    // Page Object

    // viewAllGoalsButton

    public WebElement getViewAllGoalsButton() {
        return viewAllGoalsButton = driver.findElement(By.cssSelector(".full-goal-list-text.ng-star-inserted"));
    }

    public void clickViewAllGoalsButton() {
        getViewAllGoalsButton().click();
    }

    public boolean isDisplayedViewAllGoalsButton() {
        return getViewAllGoalsButton().isDisplayed();
    }


    // Functional

    public  GoalComponent findGoalInFullList(Goal goal) {
        return showMoreGoals().findGoalByTitle(goal.getTitle());

    }

    // Business Logic

    /**
     * Show more goals if it more than 3 one.
     * @return
     */
    public MyGoalsContainer showMoreGoals() {
        if(getGoalsCount() == MY_GOALS_MAX_COUNT_WITHOUT_SHOW_MORE & isDisplayedViewAllGoalsButton()) {
            clickViewAllGoalsButton();
        }
        return new MyGoalsContainer(driver);
    }

    /**
     * Show less goals if it more than 3 one.
     * @return
     */
    public MyGoalsContainer showLessGoals() {
        if(isDisplayedViewAllGoalsButton() & getGoalsCount() > MY_GOALS_MAX_COUNT_WITHOUT_SHOW_MORE) {
            clickViewAllGoalsButton();
        }
        return new MyGoalsContainer(driver);
    }

    /**
     * Select goal.
     * @param goal
     * @return MyGoalsContainer
     */
    public MyGoalsContainer selectGoal(Goal goal) {
        GoalComponent currentGoal = findGoal(goal);
        if (currentGoal != null) {
            currentGoal.select();
        } else {
            currentGoal = findGoalInFullList(goal);
            if (currentGoal != null) {
                currentGoal.select();
            }
        }
        return new MyGoalsContainer(driver);
    }

    /**
     * Deselect goal.
     * @param goal
     * @return MyGoalsContainer
     */
    public MyGoalsContainer deselectGoal(Goal goal) {
        GoalComponent currentGoal = findGoal(goal);
        if (currentGoal != null) {
            currentGoal.deselect();
        } else {
            currentGoal = findGoalInFullList(goal);
            if (currentGoal != null) {
                currentGoal.deselect();
            }
        }
        return new MyGoalsContainer(driver);
    }
}
