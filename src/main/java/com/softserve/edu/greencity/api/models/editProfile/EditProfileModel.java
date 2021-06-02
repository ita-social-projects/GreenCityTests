package com.softserve.edu.greencity.api.models.editProfile;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "city",
        "firstName",
        "showEcoPlace",
        "showLocation",
        "showShoppingList",
        "socialNetworks",
        "userCredo"
})

public class EditProfileModel {
    @JsonProperty("city")
    public String city;
    @JsonProperty("firstName")
    public String firstName;
    @JsonProperty("showEcoPlace")
    public Boolean showEcoPlace;
    @JsonProperty("showLocation")
    public Boolean showLocation;
    @JsonProperty("showShoppingList")
    public Boolean showShoppingList;
    @JsonProperty("socialNetworks")
    public String[] socialNetworks;
    @JsonProperty("userCredo")
    public String userCredo;

    public EditProfileModel(){
        this.city = "";
        this.firstName = "";
        this.showEcoPlace = false;
        this.showLocation = false;
        this.showShoppingList = false;
        this.socialNetworks = new String[0];
        this.userCredo = "";
    }


    public EditProfileModel (String city, String firstName, boolean showEcoPlace, boolean showLocation, boolean showShoppingList, String[] socialNetworks, String userCredo){
        this.city = city;
        this.firstName = firstName;
        this.showEcoPlace = showEcoPlace;
        this.showLocation = showLocation;
        this.showShoppingList = showShoppingList;
        this.socialNetworks = socialNetworks;
        this.userCredo = userCredo;
    }
}
