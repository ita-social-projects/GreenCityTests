package com.softserve.edu.greencity.ui.pages.cabinet.editprofile;

import com.softserve.edu.greencity.ui.tools.engine.WaitsSwitcher;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static com.softserve.edu.greencity.ui.locators.EditProfileLocators.DISPLAYED_LINKS;

import java.util.ArrayList;
import java.util.List;

/**
 * Contains all addedSocialNetworkComponents (social network links) that are present on page
 */
public class AddedSocialNetworkContainer {

    private WebDriver driver;
    private List<AddedSocialNetworksComponent> addedSocialNetworksComponents;
    private Integer socialNetworkContainerSize;

    public AddedSocialNetworkContainer(WebDriver driver) {
        this.driver = driver;
        socialNetworkContainerSize = 0;
    }

    private List<WebElement> getSocialNetworks() {
        WaitsSwitcher waitsSwitcher = new WaitsSwitcher(driver);

        return waitsSwitcher.setExplicitWait(10,
                ExpectedConditions.presenceOfAllElementsLocatedBy(DISPLAYED_LINKS.getPath()));
    }

    private List<AddedSocialNetworksComponent> getSocialNetworksComponents() {
        addedSocialNetworksComponents = new ArrayList<>();
        for (WebElement current : getSocialNetworks()) {
            addedSocialNetworksComponents.add(new AddedSocialNetworksComponent(driver, current));
        }

        return addedSocialNetworksComponents;
    }

    public int getSocialNetworksSize() {
        return getSocialNetworks().size();
    }

    public AddedSocialNetworksComponent chooseSocialNetworkByNumber(int number) {
        return getSocialNetworksComponents().get(number);
    }
}
