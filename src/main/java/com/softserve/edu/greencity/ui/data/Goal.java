package com.softserve.edu.greencity.ui.data;

public class Goal {

    private String title;

    public Goal(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "Goal [title=" + title + "]";
    }
}
