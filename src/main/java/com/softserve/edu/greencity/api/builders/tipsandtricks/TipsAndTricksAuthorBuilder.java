package com.softserve.edu.greencity.api.builders.tipsandtricks;


import com.softserve.edu.greencity.api.models.tipsandtricks.TipsAndTricksAuthorModel;

public class TipsAndTricksAuthorBuilder {
    private TipsAndTricksAuthorModel author;

    private TipsAndTricksAuthorBuilder() {
        author = new TipsAndTricksAuthorModel();
    }

    public static TipsAndTricksAuthorBuilder authorWith() {
        return new TipsAndTricksAuthorBuilder();
    }

    public TipsAndTricksAuthorBuilder id(long id) {
        author.id = id;
        return this;
    }

    public TipsAndTricksAuthorBuilder name(String name) {
        author.name = name;
        return this;
    }

    public TipsAndTricksAuthorModel build() {
        return author;
    }
}

