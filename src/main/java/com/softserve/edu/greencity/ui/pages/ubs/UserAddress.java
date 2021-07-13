package com.softserve.edu.greencity.ui.pages.ubs;

public class UserAddress {
    private String street;
    private String district;
    private String house;
    private String corp;
    private String entrance;

    public UserAddress(String street, String district, String house, String corp, String entrance) {
        this.street = street;
        this.district = district;
        this.house = house;
        this.corp = corp;
        this.entrance = entrance;
    }

    public String getStreet() {
        return street;
    }

    // setters
    public void setStreet(String street) {
        this.street = street;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getHouse() {
        return house;
    }

    //getters

    public void setHouse(String house) {
        this.house = house;
    }

    public String getCorp() {
        return corp;
    }

    public void setCorp(String corp) {
        this.corp = corp;
    }

    public String getEntrance() {
        return entrance;
    }

    public void setEntrance(String entrance) {
        this.entrance = entrance;
    }

    @Override
    public String toString() {
        return "User [street=" + street + ", district=" + district
                + ", house=" + house + ", entrance=" + entrance + "]";
    }
}
