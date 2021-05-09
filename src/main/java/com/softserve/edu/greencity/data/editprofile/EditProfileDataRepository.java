package com.softserve.edu.greencity.data.editprofile;


import com.softserve.edu.greencity.data.econews.NewsData;
import com.softserve.edu.greencity.data.econews.Tag;
import com.softserve.edu.greencity.data.users.UserRepository;

import java.util.ArrayList;
import java.util.List;

import static com.softserve.edu.greencity.data.editprofile.EditProfileDataStrings.*;

public class EditProfileDataRepository {
    private static volatile EditProfileDataRepository instance = null;

    private EditProfileDataRepository() {
    }

    public static EditProfileData getDefault() {
        return get().getAllFieldsEditProfile();
    }

    public static EditProfileDataRepository get() {
        if (instance == null) {
            synchronized (UserRepository.class) {
                if (instance == null) {
                    instance = new EditProfileDataRepository();
                }
            }
        }
        return instance;
    }

    public EditProfileData getAllFieldsEditProfile() {
        return new EditProfileData(NAME.getString(), CITY.getString(), CREDO.getString());
    }

    public EditProfileData getRequiredFieldsEditProfile() {
        return new EditProfileData(NAME.getString(), CITY.getString(), CREDO.getString());
    }

    public EditProfileData getRequiredDefaultFieldsEditProfile() {
        return new EditProfileData(NAME_BY_DEFAULT.getString(), CITY_BY_DEFAULT.getString(), CREDO_BY_DEFAULT.getString());
    }

    public EditProfileData getUpToFiveSocialNetworks(){
        List<String> links = new ArrayList<>();
        links.add(SOCIAL_NETWORK_FACEBOOK_VALID.getString());
        links.add(SOCIAL_NETWORK_FACEBOOK_VALID_GREENCITYTEST.getString());
        links.add(SOCIAL_NETWORK_INSTAGRAM_VALID.getString());
        links.add(SOCIAL_NETWORK_TWITTER_VALID.getString());
        links.add(SOCIAL_NETWORK_TIKTOK_VALID.getString());
        return new EditProfileData(links);
    }

    public EditProfileData getSocialNetworkFacebook(){
        return new EditProfileData(SOCIAL_NETWORK_FACEBOOK_VALID.getString());
    }

    public EditProfileData getSocialNetworkInstagram(){
        return new EditProfileData(SOCIAL_NETWORK_INSTAGRAM_VALID.getString());
    }
}
