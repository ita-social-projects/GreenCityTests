package com.softserve.edu.greencity.api.models.editProfile;
import java.util.List;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class EditProfileDto {

        public String city;
        public String firstName;
        public Boolean showEcoPlace;
        public Boolean showLocation;
        public Boolean showShoppingList;
        public List<String> socialNetworks = null;
        public String userCredo;

        public EditProfileDto(){
            this.city = "";
            this.firstName = "";
            this.showEcoPlace = false;
            this.showLocation = false;
            this.showShoppingList = false;
            this.socialNetworks = null;
            this.userCredo = "";
        }
    @Override
    public String toString() {
        ObjectMapper mapper = new ObjectMapper();
        String objAsJson = null;
        try {
            objAsJson = mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return objAsJson;
    }
}
