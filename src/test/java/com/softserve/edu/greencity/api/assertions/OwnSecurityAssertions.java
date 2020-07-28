package com.softserve.edu.greencity.api.assertions;

import com.softserve.edu.greencity.api.model.APIResponseOwnSecurityModel;
import com.softserve.edu.greencity.api.model.UserModel;
import org.testng.asserts.SoftAssert;

public class OwnSecurityAssertions {

    public static void checkInvalidSignInResponse(final APIResponseOwnSecurityModel apiResponseOwnSecurityModel, final String expectedName, final String expectedMessage) {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(apiResponseOwnSecurityModel.getMessage(), expectedMessage);
        softAssert.assertEquals(apiResponseOwnSecurityModel.getName(), expectedName);
        softAssert.assertAll();
    }

    public static void checkInvalidSignInResponse(final APIResponseOwnSecurityModel apiResponseOwnSecurityModel, final String expectedMessage) {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(apiResponseOwnSecurityModel.getMessage(), expectedMessage);
        softAssert.assertAll();
    }

    public static void checkSignInUser(final UserModel userModel, final int userId, final String name) {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(userModel.getUserId(), userId);
        softAssert.assertEquals(userModel.getName(), name);
        softAssert.assertTrue(userModel.isOwnRegistrations(), "Error - ownRegistration is false");
        softAssert.assertAll();
    }
}
