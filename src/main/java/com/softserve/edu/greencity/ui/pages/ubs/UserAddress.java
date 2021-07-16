package com.softserve.edu.greencity.ui.pages.ubs;

public class UserAddress {
    private String street;
    private String district;
    private int house;
    private String corp;
    private int entrance;

    public UserAddress(String street, String district, int house, String corp, int entrance) {
        this.street = street;
        this.district = district;
        this.house = house;
        this.corp = corp;
        this.entrance = entrance;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getHouse() {
        return house;
    }

    public void setHouse(int house) {
        this.house = house;
    }

    public String getCorp() {
        return corp;
    }

    public void setCorp(String corp) {
        this.corp = corp;
    }

    public int getEntrance() {
        return entrance;
    }

    public void setEntrance(int entrance) {
        this.entrance = entrance;
    }

    @Override
    public String toString() {
        return "User [street=" + street + ", district=" + district
                + ", house=" + house + ", entrance=" + entrance + "]";
    }
}