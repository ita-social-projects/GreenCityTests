package com.softserve.edu.greencity.api.assertions;

import io.restassured.response.Response;
import org.testng.asserts.SoftAssert;

public class BaseAssertions {
    public static void checkResponse(final Response response, final int statusCode) {
        final SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.contentType(), "application/json;charset=UTF-8");
        softAssert.assertEquals(response.statusCode(), statusCode, "Error - wrong status code");
        softAssert.assertAll();
    }
}
