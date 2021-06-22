package com.softserve.edu.greencity.api.builders.editprofile;

import com.softserve.edu.greencity.api.models.editProfile.EditProfileModel;

import java.util.List;


public class EditProfileBuilder {
    private EditProfileModel editProfileModel;


    public EditProfileBuilder () {editProfileModel = new EditProfileModel();}

    public EditProfileBuilder city (String city){
        editProfileModel.city = city;
        return this;
    }

    public EditProfileBuilder firstName (String firstName){
        editProfileModel.firstName = firstName;
        return this;
    }
    public EditProfileBuilder showEcoPlace (boolean showEcoPlace){
        editProfileModel.showEcoPlace = showEcoPlace;
        return this;
    }
    public EditProfileBuilder showLocation (boolean showLocation){
        editProfileModel.showLocation = showLocation;
        return this;
    }
    public EditProfileBuilder showShoppingList (boolean showShoppingList){
        editProfileModel.showShoppingList = showShoppingList;
        return this;
    }
    public EditProfileBuilder socialNetworks (String[] socialNetworks){
        editProfileModel.socialNetworks = socialNetworks;
        return this;
    }
    public EditProfileBuilder userCredo (String userCredo){
        editProfileModel.userCredo = userCredo;
        return this;
    }

    /**
     * Final method
     * @return The constructed EditProfileModel.
     * If it wasn't constructed, then returns default EditProfileModel.
     */
    public EditProfileModel build() {
        return editProfileModel;
    }

}
