package com.softserve.edu.greencity.ui.pages.common;

import com.softserve.edu.greencity.ui.locators.menu.MenuElementsLocators;
import com.softserve.edu.greencity.ui.pages.ubsadmin.UbsAdminPage.UbsAdminCommon;
import com.softserve.edu.greencity.ui.tools.engine.WaitsSwitcher;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Header for signed in user
 */
public class TopUserComponent {
	protected final String PROFILE_DROPDOWN_NULL_MESSAGE = "ProfileDropdown is null";
	private final String TAG_ATTRIBUTE_CLASS = "class";

	private WebDriver driver;

	private WebElement profileButton;
	private WebElement userNameButton;
	private ProfileDropdown profileDropdown;

	public TopUserComponent(WebDriver driver) {
		this.driver = driver;
		//initElements();
	}

	private void initElements() {

	}

	// Page Object

	// profileButton

	public WebElement getProfileButton() {
		profileButton = driver.findElement(MenuElementsLocators.USER_NAME_MENU.getPath());
		return profileButton;
	}

	// userNameButton

	public WebElement getUserNameButton() {
		WaitsSwitcher waitsSwitcher = new WaitsSwitcher(driver);
		waitsSwitcher.setExplicitWait(7,
				ExpectedConditions.visibilityOfElementLocated(MenuElementsLocators.USER_NAME_MENU.getPath()));

		return userNameButton = driver.findElement(MenuElementsLocators.USER_NAME_MENU.getPath());
	}

	public String getUserNameButtonText() {
		return getUserNameButton().getText();
	}

//	public void clickUserNameButton() {
//		getUserNameButton().click();
//	}

	public ProfileDropdown clickUserNameButton() {
		getUserNameButton().click();
		return new ProfileDropdown(driver);
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

//	public void clickDropdownMenu(){
//		getProfileButton().click();
//	}
	/*Removed from UI
	protected void clickProfileDropdownFavoritePlaces() {
		createProfileDropdown().clickFavoritePlaces();
		closeProfileDropdown();
	}
	*/

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
