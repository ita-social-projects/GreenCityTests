package com.softserve.edu.greencity.ui.pages.cabinet;

import com.softserve.edu.greencity.ui.data.Estimation;
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

    public long getHabitItemsCount() {
        return getHabitItems().size();
    }

    public WebElement getHabitItemByNumber(int number) {
        return getHabitItems().get(number - 1);
    }

    public boolean isActiveItemElement(WebElement item) {
        return item.getAttribute(IDENTIFY_ELEMENT_CLASS_ATRIBUTE).contentEquals(ITEMS_STATUS_ATRIBUTE);
    }

    public long getSelectedHabitItemsCount() {
        return getHabitItems().stream()
                .filter(item -> isActiveItemElement(item))
                .count();
    }

    public void selectItems(int number) {
        if (getSelectedHabitItemsCount() != number) {
            getHabitItemByNumber(number).click();
        }
    }

    public Estimation getSelectedEstimation() {
        return null;
    }

    /**
     * Select estimation of day.
     * @param estimation
     * @return new HabitComponent
     */
    public HabitComponent selectEstimation(Estimation estimation) {
        WebElement estimateButton = getEstimationButtons().stream()
                .filter(button -> button
                        .getAttribute(IDENTIFY_ELEMENT_CLASS_ATRIBUTE)
                        .contains(estimation.toString()))
                .findAny().orElse(null);
        if (estimateButton != null) {
            estimateButton.click();
        }
        return new HabitComponent(habitLayout);
    }
}
