package com.softserve.edu.greencity.ui.pages.cabinet;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.softserve.edu.greencity.ui.data.Estimation;


public class HabitComponent {

    private final int MAX_ITEMS_COUNT_IN_ROW = 8;
    private final String IDENTIFY_ELEMENT_CLASS_ATRIBUTE = "class";
    private final String ITEMS_STATUS_ATRIBUTE = "active-element";

    private WebElement habitLayout;

    private String habitTitle;

    private WebElement habitChart;

    private WebElement moreElementsButton;

    private List<WebElement> habitItems;

    private List<WebElement> estimationButtons;

    public HabitComponent(WebElement habitLayout) {
        this.habitLayout = habitLayout;
        initElements();
    }

    private void initElements() {
        habitTitle = habitLayout.findElement(By.cssSelector("app-habit-title h4")).getText();

        habitChart = habitLayout.findElement(By.cssSelector("app-habit-chart"));

        moreElementsButton = habitLayout.findElement(By.cssSelector("app-habit-item-list span"));

        estimationButtons = new ArrayList<WebElement>();
        habitLayout.findElements(By.cssSelector("button")).forEach(item -> estimationButtons.add(item));

        initHabitItems();
    }

    private void initHabitItems() {
        habitItems = new ArrayList<WebElement>();
        habitLayout.findElements(By.cssSelector("img")).forEach(item ->  habitItems.add(item));
    }

    // Page Object

    //habitLayout

    public WebElement getHabitLayout() {
        return habitLayout;
    }

    // habitTitle

    public String getHabitTitle() {
        return habitTitle;
    }

    // habitChart

    public WebElement getHabitChart() {
        return habitChart;
    }

    // moreElementsButton

    public WebElement getMoreElementsButton() {
        return moreElementsButton;
    }

    public void clickMoreElementsButton() {
        getMoreElementsButton().click();
    }

    // habitItems

    public List<WebElement> getHabitItems() {
        return habitItems;
    }

    // estimationButtons

    public List<WebElement> getEstimationButtons() {
        return estimationButtons;
    }


    // Functional

    public long getHabitItemsCount() {
        return getHabitItems().size();
    }

    public WebElement getHabitItemByNumber(int number) {
        return getHabitItems().get(number-1);
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
        if(getSelectedHabitItemsCount() != number) {
            getHabitItemByNumber(number).click();
        }
    }

    // TODO
    // do not work select estimation
    // do not know how to identify active button
    public Estimation getSelectedEstimation() {
         return null;
    }

    public void resizeHabitItemsContainer() {
        clickMoreElementsButton();
        initHabitItems();
    }


    // Business Logic

    /**
     * Select items count that could not be used.
     * @param number - items count
     * @return new HabitComponent
     */
    public HabitComponent selectItemsNumber(int number) {
        if (number > MAX_ITEMS_COUNT_IN_ROW && number < 2 * MAX_ITEMS_COUNT_IN_ROW) {
            resizeHabitItemsContainer();
            selectItems(number);
        } else {
            selectItems(number);
        }
        return new HabitComponent(habitLayout);
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


