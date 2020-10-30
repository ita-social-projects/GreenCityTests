package com.softserve.edu.greencity.api.builders.ownsecurity;

import com.softserve.edu.greencity.api.models.ownsecurity.SignInDto;

public class SignInBuilder {
    private SignInDto signIn;

    public SignInBuilder() {
        signIn = new SignInDto();
    }

    /**
     * Starts building
     * @return A builder with the possibility to set properties
     */
    public static SignInBuilder signInWith() {
        return new SignInBuilder();
    }

    public SignInBuilder email(String email) {
        signIn.email = email;
        return this;
    }

    public SignInBuilder password(String password) {
        signIn.password = password;
        return this;
    }

    /**
     * Final method
     * @return The constructed SignInDto.
     * If it wasn't constructed, then returns default SignInDto.
     */
    public SignInDto build() {
        return signIn;
    }
}
