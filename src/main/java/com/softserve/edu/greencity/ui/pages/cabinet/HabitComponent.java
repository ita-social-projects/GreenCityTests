package com.softserve.edu.greencity.ui.pages.cabinet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;


public class HabitComponent {
    private final int MAX_ITEMS_COUNT_IN_ROW = 8;
    private final String IDENTIFY_ELEMENT_CLASS_ATRIBUTE = "class";
    private final String ITEMS_STATUS_ATRIBUTE = "active-element";

    private WebElement habitLayout;

    private String habitTitle;

    private List<WebElement> habitItems;

    private List<WebElement> estimationButtons = new ArrayList<>();

    public HabitComponent(WebElement habitLayout) {
        this.habitLayout = habitLayout;
        initElements();
    }

    private void initElements() {
        habitTitle = habitLayout.findElement(By.className("title")).getText();
        habitItems = new ArrayList<>();
        habitLayout.findElements(By.cssSelector(".habits-in-progress > .ng-star-inserted")).forEach(item -> habitItems.add(item));
    }

    public WebElement getHabitLayout() {
        return habitLayout;
    }

    public String getHabitTitle() {
        return habitTitle;
    }

    public List<WebElement> getHabitItems() {
        return habitItems;
    }

    public List<WebElement> getEstimationButtons() {
        return estimationButtons;
    }
}
