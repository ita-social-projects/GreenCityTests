package com.softserve.edu.greencity.api.assertions;

import com.softserve.edu.greencity.api.model.APIResponseBody;
import org.testng.asserts.SoftAssert;

public class OwnSecurityAssertions {

    public static void checkInvalidSignInResponse(final APIResponseBody apiResponseBody, final String expectedName, final String expectedMessage) {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(apiResponseBody.getMessage(), expectedMessage);
        softAssert.assertEquals(apiResponseBody.getName(), expectedName);
        softAssert.assertAll();
    }

    public static void checkInvalidSignInResponse(final APIResponseBody apiResponseBody, final String expectedMessage) {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(apiResponseBody.getMessage(), expectedMessage);
        softAssert.assertAll();
    }

}
