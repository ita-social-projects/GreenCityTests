package com.softserve.edu.greencity.api.builders.ownsecurity;

import com.softserve.edu.greencity.api.models.ownsecurity.ChangePasswordDto;


public class ChangePasswordBuilder {
    private ChangePasswordDto changePassword;

    public ChangePasswordBuilder() {
        changePassword = new ChangePasswordDto();
    }

    /**
     * Starts building
     * @return A builder with the possibility to set properties
     */
    public static ChangePasswordBuilder changePasswordWith() {
        return new ChangePasswordBuilder();
    }

    public ChangePasswordBuilder confirmPassword(String confirmPassword) {
        changePassword.confirmPassword = confirmPassword;
        return this;
    }

    public ChangePasswordBuilder password(String password) {
        changePassword.password = password;
        return this;
    }

    public ChangePasswordBuilder token(String token) {
        changePassword.token = token;
        return this;
    }

    /**
     * Final method
     * @return The constructed ChangePasswordDto.
     * If it wasn't constructed, then returns default ChangePasswordDto.
     */
    public ChangePasswordDto build() {
        return changePassword;
    }
}
