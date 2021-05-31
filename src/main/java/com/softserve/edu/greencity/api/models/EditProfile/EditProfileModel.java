package com.softserve.edu.greencity.api.models.EditProfile;

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
    public List<String> socialNetworks = null;
    @JsonProperty("userCredo")
    public String userCredo;

    public EditProfileModel(){
        super();
        this.city = "";
        this.firstName = "";
        this.showEcoPlace = false;
        this.showLocation = false;
        this.showShoppingList = false;
        this.socialNetworks = null;
        this.userCredo = "";
    }
    /**
     *
     * @param city
     * @param firstName
     * @param showEcoPlace
     * @param showLocation
     * @param showShoppingList
     * @param socialNetworks
     * @param userCredo
     */

    public EditProfileModel (String city, String firstName, boolean showEcoPlace, boolean showLocation, boolean showShoppingList, List socialNetworks, String userCredo){
        super();
        this.city = city;
        this.firstName = firstName;
        this.showEcoPlace = showEcoPlace;
        this.showLocation = showLocation;
        this.showShoppingList = showShoppingList;
        this.socialNetworks = socialNetworks;
        this.userCredo = userCredo;
    }
}
