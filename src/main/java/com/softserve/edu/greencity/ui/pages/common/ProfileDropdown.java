package com.softserve.edu.greencity.ui.pages.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProfileDropdown {
	//
	private WebDriver driver;
	//
	private WebElement favoritePlaces;
	private WebElement userSettings;
	private WebElement signout;

	public ProfileDropdown(WebDriver driver) {
		this.driver = driver;
		initElements();
	}

	private void initElements() {
		// init elements
		favoritePlaces = driver.findElement(By.cssSelector("div#user-avatar-wrapper li.tertiary-global-button + li"));
		userSettings = driver.findElement(By.cssSelector("div#user-avatar-wrapper li.tertiary-global-button + li + li"));
		signout = driver.findElement(By.cssSelector("div#user-avatar-wrapper li.tertiary-global-button + li + li + li"));
	}

	// Page Object

	// favoritePlaces

	public WebElement getFavoritePlaces() {
		return favoritePlaces;
	}

	public String getFavoritePlacesText() {
		return getFavoritePlaces().getText().trim();
	}

	public void clickFavoritePlaces() {
		getFavoritePlaces().click();
	}

	// userSettings

	public WebElement getUserSettings() {
		return userSettings;
	}

	public String getUserSettingsText() {
		return getUserSettings().getText().trim();
	}

	public void clickUserSettings() {
		getUserSettings().click();
	}

	// signout

	public WebElement getSignout() {
		return signout;
	}

	public String getSignoutText() {
		return getSignout().getText().trim();
	}

	public void clickSignout() {
		getSignout().click();
	}

	// Functional

	// Business Logic

}