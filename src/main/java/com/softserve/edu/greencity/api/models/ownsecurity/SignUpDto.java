package com.softserve.edu.greencity.api.models.ownsecurity;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "email",
        "name",
        "password"
})

public class SignUpDto {
    @JsonProperty("email")
    public String email;
    @JsonProperty("name")
    public String name;
    @JsonProperty("password")
    public String password;

    /**
     * No args constructor for use in serialization
     *
     */
    public SignUpDto() {
        super();
        this.email = "";
        this.name = "";
        this.password = "";
    }

    /**
     *
     * @param email
     * @param name
     * @param password
     */
    public SignUpDto(String email, String name, String password) {
        super();
        this.email = email;
        this.name = name;
        this.password = password;
    }
}
