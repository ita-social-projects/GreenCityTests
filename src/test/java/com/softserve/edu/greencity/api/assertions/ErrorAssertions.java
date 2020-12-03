package com.softserve.edu.greencity.api.assertions;

import com.softserve.edu.greencity.api.models.errors.DetailedErrorMessage;
import com.softserve.edu.greencity.api.models.errors.ErrorMessage;
import com.softserve.edu.greencity.api.models.errors.PairErrorMessage;
import io.restassured.response.Response;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.List;

public class ErrorAssertions {

    public static void errorEquals(Response error, ErrorMessage expected) {
        SoftAssert softAssert = new SoftAssert();
        ErrorMessage actual = error.as(ErrorMessage.class);
        softAssert.assertEquals(actual, expected);
        softAssert.assertAll();
    }

    public static void errorEquals(Response error, DetailedErrorMessage expected) {
        SoftAssert softAssert = new SoftAssert();
        DetailedErrorMessage actual = error.as(DetailedErrorMessage.class);
        softAssert.assertEquals(actual, expected);
        softAssert.assertAll();
    }

    public static void arrayEquals(Response array, List<PairErrorMessage> expected) {
        SoftAssert softAssert = new SoftAssert();
        ArrayList<PairErrorMessage> actual = new ArrayList<>(array.jsonPath().getList("$", PairErrorMessage.class));
        softAssert.assertEquals(actual.size(), expected.size(), "Arrays have different length");
        softAssert.assertTrue(expected.containsAll(actual));
        softAssert.assertAll();
    }
}
