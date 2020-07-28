package com.softserve.edu.greencity.api.model;

public class SuccessfulSignUp {
    private String email;
    private String username;
    private String password;
    private int userId;
    private boolean ownRegistrations;

    public String getEmail(){ return email;}
    public String getUsername(){
        return username;
    }
    public String getPassword(){
        return password;
    }
    public int getUserId(){ return userId; }
    public boolean getOwnRegistrations(){ return ownRegistrations; }



}
