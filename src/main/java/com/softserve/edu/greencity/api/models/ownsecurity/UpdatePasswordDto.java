package com.softserve.edu.greencity.api.models.ownsecurity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "confirmPassword",
        "currentPassword",
        "password"
})

public class UpdatePasswordDto {
    @JsonProperty("confirmPassword")
    public String confirmPassword;
    @JsonProperty("currentPassword")
    public String currentPassword;
    @JsonProperty("password")
    public String password;


    /**
     * No args constructor for use in serialization
     *
     */
    public UpdatePasswordDto() {
        super();
        this.confirmPassword = "";
        this.currentPassword = "";
        this.password = "";

    }

    /**
     *
     * @param confirmPassword
     * @param currentPassword
     * @param password
     */
    public UpdatePasswordDto(String confirmPassword, String currentPassword, String password) {
        super();
        this.confirmPassword = confirmPassword;
        this.currentPassword = currentPassword;
        this.password = password;
    }
}
