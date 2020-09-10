package com.softserve.edu.greencity.ui.pages.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TopUserComponent {
	protected final String PROFILE_DROPDOWN_NULL_MESSAGE = "ProfileDropdown is null";
	//
	private final String TAG_ATTRIBUTE_CLASS = "class";
	private final String USER_NAME_CSS = "div#user-avatar-wrapper li.tertiary-global-button > a";
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

	}

	// Page Object

	// profileButton

	public WebElement getProfileButton() {

		profileButton = driver.findElement(By.cssSelector("div#user-avatar-wrapper > ul"));
		return profileButton;
	}

	// userNameButton

	public WebElement getUserNameButton() {
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(USER_NAME_CSS)));

		return userNameButton = driver.findElement(By.cssSelector(USER_NAME_CSS));
	}

	public String getUserNameButtonText() {
		return getUserNameButton().getText();
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