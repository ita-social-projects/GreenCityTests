package com.softserve.edu.greencity.api.models.ownsecurity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "confirmPassword",
        "password",
        "token"
})

public class ChangePasswordDto {
    @JsonProperty("confirmPassword")
    public String confirmPassword;
    @JsonProperty("password")
    public String password;
    @JsonProperty("token")
    public String token;

    /**
     * No args constructor for use in serialization
     *
     */
    public ChangePasswordDto() {
        super();
        this.confirmPassword = "";
        this.password = "";
        this.token = "";
    }

    /**
     *
     * @param confirmPassword
     * @param password
     * @param token
     */
    public ChangePasswordDto(String confirmPassword, String password, String token) {
        super();
        this.confirmPassword = confirmPassword;
        this.password = password;
        this.token = token;
    }
}
