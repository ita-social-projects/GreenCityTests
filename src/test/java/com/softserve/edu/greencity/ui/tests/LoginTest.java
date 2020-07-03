package com.softserve.edu.greencity.ui.tests;

import com.softserve.edu.greencity.ui.data.User;
import com.softserve.edu.greencity.ui.data.UserRepository;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends GreenCityTestRunner {

    @Test
    public void loginWithValidCredentials() {
        User user = UserRepository.get().temporary();

        String newHabitButton = loadApplication()
                .signin()
                .getManualLoginComponent()
                .successfullyLogin(user)
                .getAddNewHabitButton()
                .getText();

        Assert.assertEquals(newHabitButton, "Add new habit");
    }

    @Test
    public void loginWithUnregisteredCredentials() {
        User user = UserRepository.get().invalidUserCredentials();

        String errorText = loadApplication()
                .signin()
                .getManualLoginComponent()
                .unsuccessfullyLogin(user)
                .getWrongCredsErrorText();

        Assert.assertEquals(errorText, "Bad email or password");
    }
}
