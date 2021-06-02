package com.softserve.edu.greencity.api.builders.editprofile;

import com.softserve.edu.greencity.api.models.econews.EcoNewsPOSTdto;
import com.softserve.edu.greencity.api.models.editProfile.EditProfileDto;

import java.util.List;

public class EditProfileDtoBuilder {
    private EditProfileDto editProfileDto;

    private EditProfileDtoBuilder(){editProfileDto = new EditProfileDto();}

    /**
     * Starts building
     * @return A builder with the possibility to set properties
     */

    public static EditProfileDtoBuilder editProfileDtoWith(){
        return new EditProfileDtoBuilder();
    }

    public EditProfileDtoBuilder city (String city){
        this.editProfileDto.city = city;
        return this;
    }

    public EditProfileDtoBuilder firstName (String firstName){
        editProfileDto.firstName = firstName;
        return this;
    }
    public EditProfileDtoBuilder showEcoPlace (boolean showEcoPlace){
        editProfileDto.showEcoPlace = showEcoPlace;
        return this;
    }
    public EditProfileDtoBuilder showLocation (boolean showLocation){
        editProfileDto.showLocation = showLocation;
        return this;
    }
    public EditProfileDtoBuilder showShoppingList (boolean showShoppingList){
        editProfileDto.showShoppingList = showShoppingList;
        return this;
    }
    public EditProfileDtoBuilder socialNetworks (List<String> socialNetworks){
        editProfileDto.socialNetworks = socialNetworks;
        return this;
    }
    public EditProfileDtoBuilder userCredo (String userCredo){
        editProfileDto.userCredo = userCredo;
        return this;
    }

    public EditProfileDto build() {
        return editProfileDto;
    }
}
