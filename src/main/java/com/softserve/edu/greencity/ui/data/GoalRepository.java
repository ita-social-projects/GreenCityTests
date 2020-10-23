package com.softserve.edu.greencity.ui.data;

public class GoalRepository {
    private static volatile GoalRepository instance = null;

    private GoalRepository() {
    }

    public static GoalRepository get() {
        if (instance == null) {
            synchronized (GoalRepository.class) {
                if (instance == null) {
                    instance = new GoalRepository();
                }
            }
        }
        return instance;
    }

    public Goal defaultGoalForSelecting() {
        return new Goal("Buy composter");
    }

    public Goal defaultGoalForAdding() {
        return new Goal("Buy eco products for washing");
    }
}
