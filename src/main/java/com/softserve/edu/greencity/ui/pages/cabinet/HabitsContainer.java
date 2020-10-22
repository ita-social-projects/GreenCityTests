package com.softserve.edu.greencity.ui.pages.cabinet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;

public class HabitsContainer {
    private WebDriver driver;

    public HabitsContainer(WebDriver driver) {
        this.driver = driver;
    }

    public List<HabitComponent> getHabitComponents() {
        final List<HabitComponent> habitComponents = new ArrayList<>();
        driver.findElements(By.cssSelector(".habits-in-progress > .ng-star-inserted"))
                .forEach(habit -> habitComponents.add(new HabitComponent(habit)));

        return habitComponents;
    }
}
