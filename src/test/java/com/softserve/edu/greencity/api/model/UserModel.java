package com.softserve.edu.greencity.api.model;

public class UserModel {
    private String email;
    private String password;
    private String accessToken;
    private String name;
    private String refreshToken;
    private int userId;
    private boolean ownRegistrations;
    private String username;

    public UserModel() {

    }

    public UserModel(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public boolean isOwnRegistrations() {
        return ownRegistrations;
    }

    public void setOwnRegistrations(boolean ownRegistrations) {
        this.ownRegistrations = ownRegistrations;
    }

    public String getUsername(){
        return username;
    }

    public void setUsername(){
        this.username = username;
    }
}
