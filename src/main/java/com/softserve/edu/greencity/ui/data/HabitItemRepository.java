package com.softserve.edu.greencity.ui.data;

public class HabitItemRepository {
    private static volatile HabitItemRepository instance = null;

    private HabitItemRepository() {
    }

    public static HabitItemRepository get() {
        if (instance == null) {
            synchronized (HabitItemRepository.class) {
                if (instance == null) {
                    instance = new HabitItemRepository();
                }
            }
        }
        return instance;
    }


    public HabitItem saveBagsHabit() {
        return new HabitItem(Habit.SAVE_BAGS, 2, Estimation.SUPER);
    }

    public HabitItem discardCupsHabit() {
        return new HabitItem(Habit.DISCARD_DISPOSABLE_CUPS, 9, Estimation.NORMAL);
    }

}
