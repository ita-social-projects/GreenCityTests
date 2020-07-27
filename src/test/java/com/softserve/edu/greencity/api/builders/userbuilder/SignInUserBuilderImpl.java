package com.softserve.edu.greencity.api.builders.userbuilder;

import com.softserve.edu.greencity.api.model.SignInUser;

public class SignInUserBuilderImpl implements SignInUserBuilder {
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

    public SignInUser getResult() {
        return new SignInUser(email, password);
    }
}
