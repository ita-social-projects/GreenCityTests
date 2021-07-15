package com.softserve.edu.greencity.data.ubs;

import org.openqa.selenium.By;

public enum AddAddressPopupCities {
    KIEV(By.xpath("//select[@formcontrolname='city']/option[@value='Kiev']"));

    private By city;

    AddAddressPopupCities(By city) {
        this.city = city;
    }

    public By getPath() {
        return city;
    }

}
