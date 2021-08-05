package com.softserve.edu.greencity.api.models.ubscourier;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PersonalData {
    public String addressComment;
    public String email;
    public String firstName;
    public Long id;
    public String lastName;
    public String phoneNumber;

    @JsonProperty("addressComment")
    public String getAddressComment() { return addressComment; }
    @JsonProperty("addressComment")
    public void setAddressComment(String value) { this.addressComment = value; }

    @JsonProperty("email")
    public String getEmail() { return email; }
    @JsonProperty("email")
    public void setEmail(String value) { this.email = value; }

    @JsonProperty("firstName")
    public String getFirstName() { return firstName; }
    @JsonProperty("firstName")
    public void setFirstName(String value) { this.firstName = value; }

    @JsonProperty("id")
    public long getID() { return id; }
    @JsonProperty("id")
    public void setID(long value) { this.id = value; }

    @JsonProperty("lastName")
    public String getLastName() { return lastName; }
    @JsonProperty("lastName")
    public void setLastName(String value) { this.lastName = value; }

    @JsonProperty("phoneNumber")
    public String getPhoneNumber() { return phoneNumber; }
    @JsonProperty("phoneNumber")
    public void setPhoneNumber(String value) { this.phoneNumber = value; }

}
