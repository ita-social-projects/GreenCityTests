package com.softserve.edu.greencity.data.editprofile;

import java.util.List;

public class EditProfileData {

    private String name;
    private String city;
    private String credo;

    //?
    private List<String> socialNetworks;
    private String socialNetwork;

    public EditProfileData(String name, String city, String credo) {
        this.name = name;
        this.city = city;
        this.credo = credo;
    }

    public EditProfileData(String name, String city, String credo, List<String> socialNetworks) {
        this.name = name;
        this.city = city;
        this.credo = credo;
        this.socialNetworks = socialNetworks;
    }

    public EditProfileData(String name, String city, String credo, String socialNetwork) {
        this.name = name;
        this.city = city;
        this.credo = credo;
        this.socialNetwork = socialNetwork;
    }

    public EditProfileData(List<String> socialNetworks) {
        this.socialNetworks = socialNetworks;
    }

    public EditProfileData(String socialNetwork) {
        this.socialNetwork = socialNetwork;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCredo() {
        return credo;
    }

    public void setCredo(String credo) {
        this.credo = credo;
    }

    public List<String> getSocialNetworks() {
        return socialNetworks;
    }

    public void setSocialNetworks(List<String> socialNetworks) {
        this.socialNetworks = socialNetworks;
    }

    public String getSocialNetwork() {
        return socialNetwork;
    }

    public void setSocialNetwork(String socialNetwork) {
        this.socialNetwork = socialNetwork;
    }

//    @Override
//    public String toString() {
//        return "EditProfileData{" +
//                "name='" + name + '\'' +
//                ", city='" + city + '\'' +
//                ", credo='" + credo + '\'' +
//                '}';
//    }
}
