package com.softserve.edu.greencity.ui.locators;

import org.openqa.selenium.By;

public enum EditProfileLocators implements Locator{
    TITLE_LABEL(By.xpath("//h2[.='Edit Profile']")),

    PROFILE_AVATAR(By.cssSelector(".profile-avatar")),
    EDIT_AVATAR_BUTTON(By.cssSelector(".edit-image-button")),
    UPLOAD_NEW_PHOTO_BUTTON(By.cssSelector(".secondary-global-button")),
    DELETE_PHOTO_BUTTON(By.cssSelector(".delete")),
    CANCEL_ADDING_PHOTO_BUTTON(By.cssSelector(".primary-global-button:nth-child(1)")),

    //NAME_FIELD(By.cssSelector("#name")),
    //NAME_FIELD(By.xpath("//input[@class='ng-pristine ng-valid ng-touched']")),
    NAME_FIELD(By.id("name")),
    CITY_FIELD(By.cssSelector("#city")),
    CREDO_FIELDS(By.cssSelector("#credo")),

    ADD_SOCIAL_NETWORKS_BUTTON(By.cssSelector(".add-button")),
    SOCIAL_NETWORK_LINK_FIELD(By.cssSelector("#socialLink")),
    CANCEL_SOCIAL_NETWORK_BUTTON(By.cssSelector(".secondary-global-button")),
    ADD_SOCIAL_NETWORK_BUTTON(By.cssSelector(".primary-global-button")),
    DELETE_SOCIAL_NETWORK(By.cssSelector(".delete-button-img")),
    EDIT_SOCIAL_NETWORK(By.cssSelector(".edit-button-img")),
    SOCIAL_NETWORK_LINK(By.xpath("//app-social-networks/div/a/p")),
    DISPLAYED_LINKS(By.cssSelector(".social_network-link-text")),

    SHOW_LOCATION_CHECK_BOX(By.cssSelector("li:nth-child(1) > label > span")),
    SHOW_ECO_PLACES_CHECK_BOX(By.cssSelector("li:nth-child(2) > label > span")),
    SHOW_SHOPPING_LIST_CHECK_BOX(By.cssSelector("li:nth-child(3) > label > span")),

    CANCEL_BUTTON(By.cssSelector("div.buttons > button:nth-child(1)")),
    SAVE_BUTTON(By.xpath("//button[@type='submit']")),

    //Cancel editing pop up locators
    CONTINUE_EDITING_BUTTON(By.cssSelector("div.buttons-container > button.secondary-global-button")),
    CANCEL_EDITING(By.cssSelector("div.buttons-container > button.primary-global-button")),
    CLOSE_EDITING_BUTTON(By.cssSelector(".close")),

    //Remove social networks link pop up locators
    CANCEL_DELETING_SOCIAL_NETWORK(By.cssSelector("div.buttons-container > button.secondary-global-button")),
    YES_DELETE_SOCIAL_NETWORK(By.cssSelector("div.buttons-container > button.primary-global-button")),
    CLOSE_DELETING_BUTTON(By.cssSelector(".close")),
    CONFIRM_CANCEL_PROFILE_EDITING(By.xpath("//button[@class='primary-global-button']"))
    ;

    private final By path;

    EditProfileLocators(By path) {
        this.path = path;
    }

    @Override
    public By getPath() {
        return path;
    }
}
