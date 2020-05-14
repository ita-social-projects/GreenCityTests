package com.softserve.edu.greencity.ui.data;

public enum Habit {
    DISCARD_DISPOSABLE_CUPS("Discard disposable cups"),
    SAVE_BAGS("Save bags");

    private String name;

    private Habit(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
