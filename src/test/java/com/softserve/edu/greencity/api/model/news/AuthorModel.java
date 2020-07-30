package com.softserve.edu.greencity.api.model.news;

import com.softserve.edu.greencity.api.model.UserModel;

public class AuthorModel {
    private int id;
    private String name;

    public AuthorModel(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public AuthorModel(UserModel userModel) {
        id = userModel.getUserId();
        name = userModel.getName();
    }
}
