package com.softserve.edu.greencity.ui.pages.cabinet;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.softserve.edu.greencity.ui.data.Habit;
import com.softserve.edu.greencity.ui.data.HabitItem;

public class HabitsContainer {

    private WebDriver driver;

    private List<HabitComponent> habitComponent;

    public HabitsContainer(WebDriver driver) {
        this.driver = driver;
        initElements();
    }

    private void initElements() {
        habitComponent = new ArrayList<HabitComponent>();
        driver.findElements(By.cssSelector("app-habit-tracker")).forEach(habit -> habitComponent.add(new HabitComponent(habit)));
    }

    // Page Object

    //habitComponent

    public List<HabitComponent> getHabitComponents() {
        return habitComponent;
    }


    // Functional

    public long getHabitsCount() {
        return getHabitComponents().size();
    }

    public HabitComponent findHabitComponent(Habit habit) {
        return getHabitComponents().stream()
                .filter(item -> item.getHabitTitle().contains(habit.toString()))
                .findAny()
                .orElse(null);
    }

    // Business Logic

    /**
     * Select unused items count and estimation of day for habit.
     * @param habit
     * @return HabitsContainer
     */
    public HabitsContainer addHabitInfo(HabitItem habit) {
        findHabitComponent(habit.getHabit())
            .selectItemsNumber(habit.getItemsCount())
            .selectEstimation(habit.getEstimation());
        return new HabitsContainer(driver);
    }

}
