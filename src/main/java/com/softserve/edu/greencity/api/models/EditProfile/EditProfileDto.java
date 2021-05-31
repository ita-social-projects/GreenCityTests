package com.softserve.edu.greencity.api.models.EditProfile;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

public class EditProfileDto {

    /**
     * Generated with http://www.jsonschema2pojo.org/
     */

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
    @Generated("jsonschema2pojo")
    public class Example {

        @JsonProperty("city")
        private String city;
        @JsonProperty("firstName")
        private String firstName;
        @JsonProperty("showEcoPlace")
        private Boolean showEcoPlace;
        @JsonProperty("showLocation")
        private Boolean showLocation;
        @JsonProperty("showShoppingList")
        private Boolean showShoppingList;
        @JsonProperty("socialNetworks")
        private List<String> socialNetworks = null;
        @JsonProperty("userCredo")
        private String userCredo;
        @JsonIgnore
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

        @JsonProperty("city")
        public String getCity() {
            return city;
        }

        @JsonProperty("city")
        public void setCity(String city) {
            this.city = city;
        }

        @JsonProperty("firstName")
        public String getFirstName() {
            return firstName;
        }

        @JsonProperty("firstName")
        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        @JsonProperty("showEcoPlace")
        public Boolean getShowEcoPlace() {
            return showEcoPlace;
        }

        @JsonProperty("showEcoPlace")
        public void setShowEcoPlace(Boolean showEcoPlace) {
            this.showEcoPlace = showEcoPlace;
        }

        @JsonProperty("showLocation")
        public Boolean getShowLocation() {
            return showLocation;
        }

        @JsonProperty("showLocation")
        public void setShowLocation(Boolean showLocation) {
            this.showLocation = showLocation;
        }

        @JsonProperty("showShoppingList")
        public Boolean getShowShoppingList() {
            return showShoppingList;
        }

        @JsonProperty("showShoppingList")
        public void setShowShoppingList(Boolean showShoppingList) {
            this.showShoppingList = showShoppingList;
        }

        @JsonProperty("socialNetworks")
        public List<String> getSocialNetworks() {
            return socialNetworks;
        }

        @JsonProperty("socialNetworks")
        public void setSocialNetworks(List<String> socialNetworks) {
            this.socialNetworks = socialNetworks;
        }

        @JsonProperty("userCredo")
        public String getUserCredo() {
            return userCredo;
        }

        @JsonProperty("userCredo")
        public void setUserCredo(String userCredo) {
            this.userCredo = userCredo;
        }

        @JsonAnyGetter
        public Map<String, Object> getAdditionalProperties() {
            return this.additionalProperties;
        }

        @JsonAnySetter
        public void setAdditionalProperty(String name, Object value) {
            this.additionalProperties.put(name, value);
        }

    }
}
