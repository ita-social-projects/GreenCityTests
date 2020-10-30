package com.softserve.edu.greencity.api.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Generated with http://www.jsonschema2pojo.org/
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "accessToken",
        "name",
        "ownRegistrations",
        "refreshToken",
        "userId"
})
public class OwnSecurityModel {

    @JsonProperty("accessToken")
    public String accessToken;
    @JsonProperty("name")
    public String name;
    @JsonProperty("ownRegistrations")
    public Boolean ownRegistrations;
    @JsonProperty("refreshToken")
    public String refreshToken;
    @JsonProperty("userId")
    public Integer userId;

    /**
     * No args constructor for use in serialization
     *
     */
    public OwnSecurityModel() {
        super();
        this.accessToken = "";
        this.name = "";
        this.ownRegistrations = false;
        this.refreshToken = "";
        this.userId = 0;
    }

    /**
     *
     * @param ownRegistrations
     * @param name
     * @param accessToken
     * @param userId
     * @param refreshToken
     */
    public OwnSecurityModel(String accessToken, String name, Boolean ownRegistrations, String refreshToken, Integer userId) {
        super();
        this.accessToken = accessToken;
        this.name = name;
        this.ownRegistrations = ownRegistrations;
        this.refreshToken = refreshToken;
        this.userId = userId;
    }


}
