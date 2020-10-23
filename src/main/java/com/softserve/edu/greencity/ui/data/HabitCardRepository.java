package com.softserve.edu.greencity.ui.data;

public class HabitCardRepository {
    private static volatile HabitCardRepository instance = null;

    private HabitCardRepository() {
    }

    public static HabitCardRepository get() {
        if (instance == null) {
            synchronized (HabitCardRepository.class) {
                if (instance == null) {
                    instance = new HabitCardRepository();
                }
            }
        }
        return instance;
    }

    public HabitCard saveBagsCard() {
        return new HabitCard(Habit.SAVE_BAGS);
    }

    public HabitCard discardDisposableCups() {
        return new HabitCard(Habit.DISCARD_DISPOSABLE_CUPS);
    }
}
