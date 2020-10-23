package com.softserve.edu.greencity.ui.data;

public class HabitItem {

    private Habit habit;
    private int itemsCount;
    private Estimation estimation;

    public HabitItem(Habit habit, int itemsCount, Estimation estimation) {
        this.habit = habit;
        this.itemsCount = itemsCount;
        this.estimation = estimation;
    }

    public Habit getHabit() {
        return habit;
    }

    public int getItemsCount() {
        return itemsCount;
    }

    public Estimation getEstimation() {
        return estimation;
    }

    @Override
    public String toString() {
        return "HabitItem [habit=" + habit + ", itemsCount=" + itemsCount
                + ", estimation=" + estimation + "]";
    }

}
