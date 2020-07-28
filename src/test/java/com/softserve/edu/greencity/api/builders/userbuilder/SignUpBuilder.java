package com.softserve.edu.greencity.api.builders.userbuilder;

import com.softserve.edu.greencity.api.model.SignUpRequest;

public class SignUpBuilder {
    private String email;
    private String name;
    private String password;


    public SignUpBuilder setEmail(String email){
        this.email = email;
        return this;
    }

    public SignUpBuilder setName(String name){
        this.name = name;
        return this;
    }

    public SignUpBuilder setPassword(String password){
        this.password = password;
        return this;
    }

    public SignUpRequest build() {
        SignUpRequest signUpRequest = new SignUpRequest();
        signUpRequest.setEmail(email);
        signUpRequest.setName(name);
        signUpRequest.setPassword(password);

        return signUpRequest;
    }
}

