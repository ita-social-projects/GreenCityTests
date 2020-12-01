package com.softserve.edu.greencity.api.builders.ownsecurity;

import com.softserve.edu.greencity.api.models.ownsecurity.SignInDto;
import com.softserve.edu.greencity.api.models.ownsecurity.SignUpDto;

public class SignUpBuilder {
    private SignUpDto signUp;

    public SignUpBuilder() {
        signUp = new SignUpDto();
    }

    /**
     * Starts building
     * @return A builder with the possibility to set properties
     */
    public static SignUpBuilder signUpWith() {
        return new SignUpBuilder();
    }

    public SignUpBuilder email(String email) {
        signUp.email = email;
        return this;
    }

    public SignUpBuilder name(String name) {
        signUp.name = name;
        return this;
    }

    public SignUpBuilder password(String password) {
        signUp.password = password;
        return this;
    }

    /**
     * Final method
     * @return The constructed SignUpDto.
     * If it wasn't constructed, then returns default SignUpDto.
     */
    public SignUpDto build() {
        return signUp;
    }
}
