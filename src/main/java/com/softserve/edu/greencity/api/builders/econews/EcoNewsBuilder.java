package com.softserve.edu.greencity.api.builders.econews;

import com.softserve.edu.greencity.api.models.econews.EcoNewsAuthorModel;
import com.softserve.edu.greencity.api.models.econews.EcoNewsModel;

public class EcoNewsBuilder {

    private EcoNewsModel ecoNews;

    private EcoNewsBuilder() {
        ecoNews = new EcoNewsModel();
    }

    public  static EcoNewsBuilder newsWith() {
        return new EcoNewsBuilder();
    }

    public EcoNewsBuilder author(EcoNewsAuthorModel author) {
        ecoNews.author = author;
        return this;
    }

    public EcoNewsBuilder creationDate(String creationDate) {
        ecoNews.creationDate = creationDate;
        return this;
    }

    public EcoNewsBuilder id(long id) {
        ecoNews.id = id;
        return this;
    }

    public EcoNewsBuilder imagePath(String imagePath) {
        ecoNews.imagePath = imagePath;
        return this;
    }

    public  EcoNewsBuilder source(String source) {
        ecoNews.source = source;
        return this;
    }

    public EcoNewsBuilder tags(String[] tags) {
        ecoNews.tags = tags;
        return this;
    }

    public EcoNewsBuilder text(String text) {
        ecoNews.text = text;
        return this;
    }

    public EcoNewsBuilder title(String title) {
        ecoNews.title = title;
        return this;
    }

    public EcoNewsModel build() {
        return this.ecoNews;
    }
}
