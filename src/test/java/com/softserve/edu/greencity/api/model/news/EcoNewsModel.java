package com.softserve.edu.greencity.api.model.news;


import com.softserve.edu.greencity.api.model.UserModel;
import com.softserve.edu.greencity.ui.data.econews.Tag;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EcoNewsModel {
    private String creationDate;
    private AuthorModel ecoNewsAuthorDto;
    private int id;
    private String imagePath;
    private String source;
    private String[] tags;
    private String text;
    private String title;

    public EcoNewsModel buildAuthor(AuthorModel author) {
        ecoNewsAuthorDto = author;
        updateDate();
        return this;
    }

    public EcoNewsModel buildAuthor(UserModel user) {
        return buildAuthor(new AuthorModel(user));
    }

    public EcoNewsModel buildId(int id) {
        this.id = id;
        updateDate();
        return this;
    }

    public EcoNewsModel buildImagePath(String imagePath) {
        this.imagePath = imagePath;
        updateDate();
        return this;
    }

    public EcoNewsModel buildSource(String source) {
        this.source = source;
        updateDate();
        return this;
    }

    public EcoNewsModel buildTags(Tag...someTags) {
        tags = new String[someTags.length];
        for(int i = 0; i < someTags.length; i++) {
            tags[i] = someTags[i].toString();
        }
        updateDate();
        return this;
    }

    public EcoNewsModel buildText(String text) {
        this.text = text;
        updateDate();
        return this;
    }

    public EcoNewsModel buildTitle(String title) {
        this.title = title;
        updateDate();
        return this;
    }

    private void updateDate() {
        creationDate = LocalDateTime.now().format(DateTimeFormatter.ISO_INSTANT);
    }
}
