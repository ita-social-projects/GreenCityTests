package com.softserve.edu.greencity.api.builders.userbuilder;

import com.softserve.edu.greencity.api.model.UserModel;

public class UserBuilderImpl implements UserBuilder {
    private String email;
    private String password;

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    public UserModel getResult() {
        return new UserModel(email, password);
    }
}
