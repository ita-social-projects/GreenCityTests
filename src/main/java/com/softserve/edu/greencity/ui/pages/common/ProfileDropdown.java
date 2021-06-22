package com.softserve.edu.greencity.ui.pages.common;

import com.softserve.edu.greencity.ui.locators.menu.MenuElementsLocators;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class 	ProfileDropdown {

	private WebDriver driver;
	private WebElement userSettings;
	private WebElement signout;

	public ProfileDropdown(WebDriver driver) {
		this.driver = driver;
	}

//	private void initElements() {
//		// init elements
//		//favoritePlaces = driver.findElement(By.cssSelector("div#user-avatar-wrapper li.tertiary-global-button + li"));
//		userSettings = driver.findElement(By.cssSelector("li.tertiary-global-button > a"));
//		signout = driver.findElement(By.cssSelector("#header_user-wrp > li:nth-child(3) > a"));
//	}

	// Page Object

	// favoritePlaces
/*Removed from UI
	public WebElement getFavoritePlaces() {
		return favoritePlaces;
	}

	public String getFavoritePlacesText() {
		return getFavoritePlaces().getText().trim();
	}

	public void clickFavoritePlaces() {
		getFavoritePlaces().click();
	}
*/
	// userSettings

	public WebElement getUserSettings() {
		if(userSettings == null){
			userSettings = driver.findElement(MenuElementsLocators.USER_SETTINGS.getPath());
		}
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
		if(signout == null){
			signout = driver.findElement(MenuElementsLocators.USER_SIGNOUT.getPath());
		}
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
