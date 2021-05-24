package com.softserve.edu.greencity.ui.tools.jdbc.entity;

import java.util.ArrayList;
import java.util.List;

enum EditProfileEntityFields {
    ID(0),
    DATE_OF_REGISTRATION(1),
    EMAIL(2),
    EMAIL_NOTIFICATIONS(3),
    NAME(4),
    ROLE(5),
    USER_STATUS(6),
    REFRESH_TOKEN_KEY(7),
    PROFILE_PICTURE(8),
    RATING(9),
    LAST_ACTIVITY_TIME(10),
    FIRST_NAME(11),
    CITY(12),
    USER_CREDO(13),
    SHOW_LOCATION(14),
    SHOW_ECO_PLACES(15),
    SHOW_SHOPPING_LIST(16);

    private int number;

    private EditProfileEntityFields(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }
}

public class EditProfileEntity {

    public static final String SELECT_ALL = "SELECT * FROM users;";
    public static final String SELECT_BY_FIELD = "SELECT * FROM users WHERE %s='%s';";
    public static final String UPDATE_FIELD_BY_ID = "UPDATE users SET %s='%s' WHERE id='%s';";

    private long id;
    private String dateOfRegistration;
    private String email;
    private int emailNotifications;
    private String name;
    private int role;
    private int userStatus;
    private String userRefreshTokenKey;
    private String profilePicture;
    private long rating;
    private String lastActivityTime;
    private String firstName;
    private String city;
    private String userCredo;
    private String showLocation;
    private String showEcoPlaces;
    private String showShoppingList;

    public EditProfileEntity(){
        dateOfRegistration = "";
        email = "";
        emailNotifications = 0;
        name = "";
        role = 0;
        userStatus = 2;
        userRefreshTokenKey = "";
        profilePicture = "";
        rating = 0;
        lastActivityTime = "";
        firstName = "";
        city = "";
        userCredo = "";
        showLocation = "";
        showEcoPlaces = "";
        showShoppingList = "";
    }

    public EditProfileEntity(String dateOfRegistration, String email, int emailNotifications, String name, int role, int userStatus, String userRefreshKey, String profilePicture, long rating, String lastActivityTime, String firstName, String city, String userCredo, String showLocation, String showEcoPlaces, String showShoppingList) {
        this.dateOfRegistration = dateOfRegistration;
        this.email = email;
        this.emailNotifications = emailNotifications;
        this.name = name;
        this.role = role;
        this.userStatus = userStatus;
        this.userRefreshTokenKey = userRefreshKey;
        this.profilePicture = profilePicture;
        this.rating = rating;
        this.lastActivityTime = lastActivityTime;
        this.firstName = firstName;
        this.city = city;
        this.userCredo = userCredo;
        this.showLocation = showLocation;
        this.showEcoPlaces = showEcoPlaces;
        this.showShoppingList = showShoppingList;
    }

    public long getId() {
        return id;
    }

    public EditProfileEntity setId(long id) {
        this.id = id;
        return this;
    }

    public String getDateOfRegistration() {
        return dateOfRegistration;
    }

    public EditProfileEntity setDateOfRegistration(String dateOfRegistration) {
        this.dateOfRegistration = dateOfRegistration;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public EditProfileEntity setEmail(String email) {
        this.email = email;
        return this;
    }

    public int getEmailNotifications() {
        return emailNotifications;
    }

    public EditProfileEntity setEmailNotifications(int emailNotifications) {
        this.emailNotifications = emailNotifications;
        return this;
    }

    public String getName() {
        return name;
    }

    public EditProfileEntity setName(String name) {
        this.name = name;
        return this;
    }

    public int getRole() {
        return role;
    }

    public EditProfileEntity setRole(int role) {
        this.role = role;
        return this;
    }

    public int getUserStatus() {
        return userStatus;
    }

    public EditProfileEntity setUserStatus(int userStatus) {
        this.userStatus = userStatus;
        return this;
    }

    public String getUserRefreshTokenKey() {
        return userRefreshTokenKey;
    }

    public EditProfileEntity setUserRefreshTokenKey(String userRefreshTokenKey) {
        this.userRefreshTokenKey = userRefreshTokenKey;
        return this;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public EditProfileEntity setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
        return this;
    }

    public long getRating() {
        return rating;
    }

    public EditProfileEntity setRating(long rating) {
        this.rating = rating;
        return this;
    }

    public String getLastActivityTime() {
        return lastActivityTime;
    }

    public EditProfileEntity setLastActivityTime(String lastActivityTime) {
        this.lastActivityTime = lastActivityTime;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public EditProfileEntity setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getCity() {
        return city;
    }

    public EditProfileEntity setCity(String city) {
        this.city = city;
        return this;
    }

    public String getUserCredo() {
        return userCredo;
    }

    public EditProfileEntity setUserCredo(String userCredo) {
        this.userCredo = userCredo;
        return this;
    }

    public String getShowLocation() {
        return showLocation;
    }

    public EditProfileEntity setShowLocation(String showLocation) {
        this.showLocation = showLocation;
        return this;
    }

    public String getShowEcoPlaces() {
        return showEcoPlaces;
    }

    public EditProfileEntity setShowEcoPlaces(String showEcoPlaces) {
        this.showEcoPlaces = showEcoPlaces;
        return this;
    }

    public String getShowShoppingList() {
        return showShoppingList;
    }

    public EditProfileEntity setShowShoppingList(String showShoppingList) {
        this.showShoppingList = showShoppingList;
        return this;
    }

    public static EditProfileEntity getEditProfileEntity(List<String> row) {
        return new EditProfileEntity()
                .setId(Long.valueOf(row.get(EditProfileEntityFields.ID.getNumber())))
                .setDateOfRegistration(row.get(EditProfileEntityFields.DATE_OF_REGISTRATION.getNumber()))
                .setEmail(row.get(EditProfileEntityFields.EMAIL.getNumber()))
                .setEmailNotifications(Integer.valueOf(row.get(EditProfileEntityFields.EMAIL_NOTIFICATIONS.getNumber())))
                .setName(row.get(EditProfileEntityFields.NAME.getNumber()))
                .setRole(Integer.valueOf(row.get(EditProfileEntityFields.ROLE.getNumber())))
                .setUserStatus(Integer.valueOf(row.get(EditProfileEntityFields.USER_STATUS.getNumber())))
                .setUserRefreshTokenKey(row.get(EditProfileEntityFields.REFRESH_TOKEN_KEY.getNumber()))
                .setProfilePicture(row.get(EditProfileEntityFields.PROFILE_PICTURE.getNumber()))
                .setRating(Integer.valueOf(row.get(EditProfileEntityFields.RATING.getNumber())))
                .setLastActivityTime(row.get(EditProfileEntityFields.LAST_ACTIVITY_TIME.getNumber()))
                .setFirstName(row.get(EditProfileEntityFields.FIRST_NAME.getNumber()))
                .setCity(row.get(EditProfileEntityFields.CITY.getNumber()))
                .setUserCredo(row.get(EditProfileEntityFields.USER_CREDO.getNumber()))
                .setShowLocation(row.get(EditProfileEntityFields.SHOW_LOCATION.getNumber()))
                .setShowEcoPlaces(row.get(EditProfileEntityFields.SHOW_ECO_PLACES.getNumber()))
                .setShowShoppingList(row.get(EditProfileEntityFields.SHOW_SHOPPING_LIST.getNumber()));
    }

    public static List<EditProfileEntity> getListEditProfileEntity(List<List<String>> rows) {
        List<EditProfileEntity> result = new ArrayList<>();
        for (List<String> currentRow : rows) {
            result.add(getEditProfileEntity(currentRow));
        }
        return result;
    }
}
