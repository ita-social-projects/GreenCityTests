package com.softserve.edu.greencity.api.models.ownsecurity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "emailToken",
        "userId"
})

public class VerifyEmailDto {
    @JsonProperty("emailToken")
    public String emailToken;
    @JsonProperty("userId")
    public String userId;


    /**
     * No args constructor for use in serialization
     *
     */
    public VerifyEmailDto() {
        super();
        this.emailToken = "";
        this.userId = "";
    }

    /**
     *
     * @param emailToken
     * @param userId
     */
    public VerifyEmailDto(String emailToken, String userId) {
        super();
        this.emailToken = emailToken;
        this.userId = userId;
    }
}
