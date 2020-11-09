package com.softserve.edu.greencity.api.builder;

import com.softserve.edu.greencity.api.model.EcoNewsAuthorModel;

public class EcoNewsAuthorBuilder {

    private EcoNewsAuthorModel author;

    private EcoNewsAuthorBuilder() {
        author = new EcoNewsAuthorModel();
    }

    public static EcoNewsAuthorBuilder authorWith() {
        return new EcoNewsAuthorBuilder();
    }

    public EcoNewsAuthorBuilder id(long id) {
        author.id = id;
        return this;
    }

    public EcoNewsAuthorBuilder name(String name) {
        author.name = name;
        return this;
    }

    public EcoNewsAuthorModel build() {
        return author;
    }
}
