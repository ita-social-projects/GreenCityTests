package com.softserve.edu.greencity.ui.pages.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TopUserComponent {
	protected final String PROFILE_DROPDOWN_NULL_MESSAGE = "ProfileDropdown is null";
	//
	private final String TAG_ATTRIBUTE_CLASS = "class";
	//
	private WebDriver driver;
	//
	private WebElement profileButton;
	private WebElement userNameButton;
	//
	private ProfileDropdown profileDropdown;

	public TopUserComponent(WebDriver driver) {
		this.driver = driver;
		initElements();
	}

	private void initElements() {
		// init elements
		profileButton = driver.findElement(By.cssSelector("div#user-avatar-wrapper > ul"));
		userNameButton = driver.findElement(By.cssSelector("div#user-avatar-wrapper li.tertiary-global-button > a"));
	}

	// Page Object

	// profileButton

	public WebElement getProfileButton() {
		return profileButton;
	}

	// userNameButton

	public WebElement getUserNameButton() {
		return userNameButton;
	}

	public String getUserNameButtonText() {
		return getUserNameButton().getText().trim();
	}

	public void clickUserNameButton() {
		getUserNameButton().click();
	}

	public boolean isDisplayedUserNameButton() {
		return getUserNameButton().isDisplayed();
	}

	// Functional

	public boolean isExpanded() {
		return getProfileButton()
				.getAttribute(TAG_ATTRIBUTE_CLASS)
				.equals("add-shadow");
	}

	// profileDropdown

	protected ProfileDropdown getProfileDropdown() {
		if (profileDropdown == null)
		{
			// TODO Develop Custom Exception
			throw new RuntimeException(PROFILE_DROPDOWN_NULL_MESSAGE);
		}
		return profileDropdown;
	}

	protected void openProfileDropdown() {
		if (!isExpanded()) {
			clickUserNameButton();
		}
	}

	protected ProfileDropdown createProfileDropdown() {
		openProfileDropdown();
		profileDropdown = new ProfileDropdown(driver);
		return getProfileDropdown();
	}

	protected void clickProfileDropdownFavoritePlaces() {
		createProfileDropdown().clickFavoritePlaces();
		closeProfileDropdown();
	}

	protected void clickProfileDropdownUserSettings() {
		createProfileDropdown().clickUserSettings();
		closeProfileDropdown();
	}

	protected void clickProfileDropdownSignout() {
		createProfileDropdown().clickSignout();
		closeProfileDropdown();
	}

	protected void closeProfileDropdown() {
		profileDropdown = null;
	}

	// Business Logic

}