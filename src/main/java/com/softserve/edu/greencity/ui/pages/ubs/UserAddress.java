package com.softserve.edu.greencity.ui.pages.ubs;

import com.softserve.edu.greencity.ui.locators.ubs.AddAddressPopupLocators;
import org.apache.commons.lang3.RandomStringUtils;

public class UserAddress {
    private AddAddressPopupLocators city;
    private String street;
    private String district;
    private int house;
    private String corp;
    private int entrance;

    public UserAddress(AddAddressPopupLocators city, String street, String district, int house, String corp, int entrance) {
        this.city = city;
        this.street = street;
        this.district = district;
        this.house = house;
        this.corp = corp;
        this.entrance = entrance;
    }

    public UserAddress(AddAddressPopupLocators city) {
        this.city = city;
    }

    public UserAddress generateRandomAddressValues() {
        street = RandomStringUtils.randomAlphabetic(10);
        district = RandomStringUtils.randomAlphabetic(10);
        house = Integer.parseInt(RandomStringUtils.randomNumeric(1,3));
        corp = RandomStringUtils.randomNumeric(1) + RandomStringUtils.random(1,97,104,true,false);
        entrance = Integer.parseInt(RandomStringUtils.randomNumeric(1,2));
        return this;
    }

    public AddAddressPopupLocators getCity() {
        return city;
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