package com.softserve.edu.greencity.api.builders.userbuilder;

import com.softserve.edu.greencity.api.model.UserModel;

public class UserBuilder {
    private String email;
    private String name;
    private String password;

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserModel getResult() {
        return new UserModel(email, name, password);
    }
}
