package com.softserve.edu.greencity.ui.data;

public class HabitCard {

    private Habit habit;

    public HabitCard(Habit habit) {
        this.habit = habit;
    }

    public Habit getHabit() {
        return habit;
    }

    @Override
    public String toString() {
        return "HabitCard [habit=" + habit + "]";
    }


}
