package com.softserve.edu.greencity.api.builders.econews;

import com.softserve.edu.greencity.api.models.econews.EcoNewsPOSTdto;

public class EcoNewsDtoBuilder {

    private EcoNewsPOSTdto ecoNews;

    private EcoNewsDtoBuilder() {
        ecoNews = new EcoNewsPOSTdto();
    }

    /**
     * Starts building
     * @return A builder with the possibility to set properties
     */
    public static EcoNewsDtoBuilder ecoNewsDtoWith() {
        return new EcoNewsDtoBuilder();
    }

    public EcoNewsDtoBuilder title(String title) {
        this.ecoNews.title = title;
        return this;
    }

    public EcoNewsDtoBuilder text(String text) {
        this.ecoNews.text = text;
        return this;
    }

    public EcoNewsDtoBuilder image(String image) {
        this.ecoNews.image = image;
        return this;
    }

    public EcoNewsDtoBuilder source(String source) {
        this.ecoNews.source = source;
        return this;
    }

    public EcoNewsDtoBuilder tags(String[] tags) {
        this.ecoNews.tags = tags;
        return this;
    }

    public EcoNewsPOSTdto build() {
        return ecoNews;
    }
}
