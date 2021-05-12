package com.softserve.edu.greencity.ui.pages.cabinet;

import com.softserve.edu.greencity.ui.pages.cabinet.editprofile.AddedSocialNetworksComponent;
import com.softserve.edu.greencity.ui.tools.engine.WaitsSwitcher;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

import static com.softserve.edu.greencity.ui.locators.MyHabitLocators.*;

/**
 * Contains all social network icons that are present on page
 */
public class SocialNetworkItemsContainer {
    private WebDriver driver;
    private List<SocialNetworkItemComponent> socialNetworkItemComponents;
    private Integer socialNetworkItemsContainerSize;

    public SocialNetworkItemsContainer(WebDriver driver) {
        this.driver = driver;
    }

    private List<WebElement> getSocialNetworkIcons() {
        WaitsSwitcher waitsSwitcher = new WaitsSwitcher(driver);

        return waitsSwitcher.setExplicitWait(30,
                ExpectedConditions.presenceOfAllElementsLocatedBy(SOCIAL_NETWORK_CONTAINER.getPath()));
    }

    public List<SocialNetworkItemComponent> getSocialNetworkItemComponents() {
        socialNetworkItemComponents = new ArrayList<>();
        for (WebElement current : getSocialNetworkIcons()) {
            socialNetworkItemComponents.add(new SocialNetworkItemComponent(driver, current));
        }
        return socialNetworkItemComponents;
    }

    public SocialNetworkItemComponent chooseSocialNetworkItemsByNumber(int number) {
        return getSocialNetworkItemComponents().get(number);
    }

    public int setSocialNetworkItemsSize(int size) {
        socialNetworkItemsContainerSize = size;
        return size;
    }

    public int getSocialNetworksSize() {
        if(socialNetworkItemsContainerSize == null){
            return getSocialNetworkIcons().size();
        }
        return socialNetworkItemsContainerSize;
    }
}
