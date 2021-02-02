package com.softserve.edu.greencity.api.builders.tipsandtricks;

import com.softserve.edu.greencity.api.models.tipsandtricks.TipsAndTricksAuthorModel;
import com.softserve.edu.greencity.api.models.tipsandtricks.TipsAndTricksModel;

public class TipsAndTricksBuilder {
    private TipsAndTricksModel tipsAndTricks;

    private TipsAndTricksBuilder() {
        tipsAndTricks = new TipsAndTricksModel();
    }

    public  static TipsAndTricksBuilder newsWith() {
        return new TipsAndTricksBuilder();
    }

    public TipsAndTricksBuilder author(TipsAndTricksAuthorModel author) {
        tipsAndTricks.author = author;
        return this;
    }

    public TipsAndTricksBuilder creationDate(String creationDate) {
        tipsAndTricks.creationDate = creationDate;
        return this;
    }

    public TipsAndTricksBuilder id(long id) {
        tipsAndTricks.id = id;
        return this;
    }

    public TipsAndTricksBuilder imagePath(String imagePath) {
        tipsAndTricks.imagePath = imagePath;
        return this;
    }

    public  TipsAndTricksBuilder source(String source) {
        tipsAndTricks.source = source;
        return this;
    }

    public TipsAndTricksBuilder tags(String[] tags) {
        tipsAndTricks.tags = tags;
        return this;
    }

    public TipsAndTricksBuilder text(String text) {
        tipsAndTricks.text = text;
        return this;
    }

    public TipsAndTricksBuilder title(String title) {
        tipsAndTricks.title = title;
        return this;
    }

    public TipsAndTricksModel build() {
        return this.tipsAndTricks;
    }
}
