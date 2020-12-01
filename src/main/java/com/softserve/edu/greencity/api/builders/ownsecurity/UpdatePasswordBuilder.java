package com.softserve.edu.greencity.api.builders.ownsecurity;

import com.softserve.edu.greencity.api.models.ownsecurity.SignUpDto;
import com.softserve.edu.greencity.api.models.ownsecurity.UpdatePasswordDto;

public class UpdatePasswordBuilder {
    private UpdatePasswordDto updatePassword;

    public UpdatePasswordBuilder() {
        updatePassword = new UpdatePasswordDto();
    }

    /**
     * Starts building
     * @return A builder with the possibility to set properties
     */
    public static UpdatePasswordBuilder updatePasswordWith() {
        return new UpdatePasswordBuilder();
    }

    public UpdatePasswordBuilder confirmPassword(String confirmPassword) {
        updatePassword.confirmPassword = confirmPassword;
        return this;
    }

    public UpdatePasswordBuilder currentPassword(String currentPassword) {
        updatePassword.currentPassword = currentPassword;
        return this;
    }

    public UpdatePasswordBuilder password(String password) {
        updatePassword.password = password;
        return this;
    }

    /**
     * Final method
     * @return The constructed UpdatePasswordDto.
     * If it wasn't constructed, then returns default UpdatePasswordDto.
     */
    public UpdatePasswordDto build() {
        return updatePassword;
    }
}
