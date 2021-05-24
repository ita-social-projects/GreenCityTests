package com.softserve.edu.greencity.ui.tools.jdbc.entity;

import java.util.ArrayList;
import java.util.List;

enum EditProfileSocialNetworksEntityFields{
    ID(0),
    USER_ID(1),
    SOCIAL_NETWORK_URL(2),
    SOCIAL_NETWORK_IMAGE_ID(3)
    ;

    private int number;

    private EditProfileSocialNetworksEntityFields(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }
}

public class EditProfileSocialNetworksEntity {

    public static final String SELECT_ALL = "SELECT * FROM social_networks;";
    public static final String SELECT_ALL_BY_USER_ID = "SELECT * FROM social_networks WHERE user_id=%s;";
    public static final String SELECT_BY_FIELD = "SELECT * FROM social_networks WHERE %s='%s';";
    public static final String DELETE_SOCIAL_NETWORKS_BY_USER_ID = "DELETE FROM social_networks WHERE user_id=%s;";

    private long id;
    private long userId;
    private String socialNetworkUrl;
    private long socialNetworkImageId;

    public EditProfileSocialNetworksEntity(){
        socialNetworkUrl="";
    }

    public EditProfileSocialNetworksEntity(long userId, String socialNetworkUrl, long socialNetworkImageId) {
        this.userId = userId;
        this.socialNetworkUrl = socialNetworkUrl;
        this.socialNetworkImageId = socialNetworkImageId;
    }

    public long getId() {
        return id;
    }

    public EditProfileSocialNetworksEntity setId(long id) {
        this.id = id;
        return this;
    }

    public long getUserId() {
        return userId;
    }

    public EditProfileSocialNetworksEntity setUserId(long userId) {
        this.userId = userId;
        return this;
    }

    public String getSocialNetworkUrl() {
        return socialNetworkUrl;
    }

    public EditProfileSocialNetworksEntity setSocialNetworkUrl(String socialNetworkUrl) {
        this.socialNetworkUrl = socialNetworkUrl;
        return this;
    }

    public long getSocialNetworkImageId() {
        return socialNetworkImageId;
    }

    public EditProfileSocialNetworksEntity setSocialNetworkImageId(long socialNetworkImageId) {
        this.socialNetworkImageId = socialNetworkImageId;
        return this;
    }

    @Override
    public String toString() {
        return "EditProfileSocialNetworksEntity{" +
                "id=" + id +
                ", userId=" + userId +
                ", socialNetworkUrl='" + socialNetworkUrl + '\'' +
                ", socialNetworkImageId=" + socialNetworkImageId +
                '}';
    }

    public static EditProfileSocialNetworksEntity getEditProfileSocialNetworksEntity(List<String> row) {
        return new EditProfileSocialNetworksEntity()
                .setId(Long.valueOf(row.get(EditProfileSocialNetworksEntityFields.ID.getNumber())))
                .setUserId(Long.valueOf(row.get(EditProfileSocialNetworksEntityFields.USER_ID.getNumber())))
                .setSocialNetworkUrl(row.get(EditProfileSocialNetworksEntityFields.SOCIAL_NETWORK_URL.getNumber()))
                .setSocialNetworkImageId(Long.valueOf(row.get(EditProfileSocialNetworksEntityFields.SOCIAL_NETWORK_IMAGE_ID.getNumber())));

    }

    public static List<EditProfileSocialNetworksEntity> getListEditProfileSocialNetworksEntity(List<List<String>> rows) {
        List<EditProfileSocialNetworksEntity> result = new ArrayList<>();
        for (List<String> currentRow : rows) {
            result.add(getEditProfileSocialNetworksEntity(currentRow));
        }
        return result;
    }
}
