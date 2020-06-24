package com.softserve.edu.greencity.ui.tests;

import com.softserve.edu.greencity.ui.data.User;
import com.softserve.edu.greencity.ui.data.UserRepository;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTest extends GreenCityTestRunner {
    @DataProvider
    private Object[] getTemporaryUser() {
        return new Object[]{
                UserRepository.get().temporary()
        };
    }

    @Test(dataProvider = "getTemporaryUser")
    public void loginWithValidCredentials(final User user) {
        String newHabitButton = loadApplication()
                .signin()
                .successfullyLogin(user)
                .getAddNewHabitButton()
                .getText();

        Assert.assertEquals(newHabitButton, "Add new habit");
    }
}
