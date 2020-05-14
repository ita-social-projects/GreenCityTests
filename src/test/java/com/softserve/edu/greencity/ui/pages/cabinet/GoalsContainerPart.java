package com.softserve.edu.greencity.ui.pages.cabinet;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.openqa.selenium.WebDriver;

import com.google.common.collect.Streams;
import com.softserve.edu.greencity.ui.data.Goal;

public abstract class GoalsContainerPart {

    protected WebDriver driver;

    protected List<GoalComponent> goalComponents;

    public GoalsContainerPart(WebDriver driver) {
        this.driver = driver;
    }

    // Page Object

    // goalComponents

    protected List<GoalComponent> getGoalComponents() {
        return goalComponents;
    }


    // Functional

    protected int getGoalsCount() {
        return getGoalComponents().size();
    }

    protected  GoalComponent findGoalByTitle(String title) {
        return getGoalComponents().stream()
                .filter(goal -> goal.getGoalTitle().contains(title))
                .findAny()
                .orElse(null);
    }

    protected  GoalComponent findGoal(Goal goal) {
        return getGoalComponents().stream()
                .filter(item -> item.getGoalTitle().contains(goal.getTitle()))
                .findAny()
                .orElse(null);
    }


    protected   List<String> getGoalsTitle() {
        return getGoalComponents().stream()
                .flatMap(goal -> Stream.of(goal.getGoalTitle()))
                .collect(Collectors.toList());
    }

    protected  GoalComponent getLastGoal() {
        return  Streams.findLast(getGoalComponents().stream()).get();

    }

    // Business Logic


}