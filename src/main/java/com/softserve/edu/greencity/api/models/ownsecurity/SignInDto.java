package com.softserve.edu.greencity.api.models.ownsecurity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "email",
        "password"
})
public class SignInDto {

    @JsonProperty("email")
    public String email;
    @JsonProperty("password")
    public String password;

    /**
     * No args constructor for use in serialization
     *
     */
    public SignInDto() {
        super();
        this.email = "";
        this.password = "";
    }

    /**
     *
     * @param password
     * @param email
     */
    public SignInDto(String email, String password) {
        super();
        this.email = email;
        this.password = password;
    }

}
