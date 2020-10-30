package com.softserve.edu.greencity.api.assertions;

import io.restassured.response.Response;
import org.testng.Assert;

import java.util.List;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;

public class ArrayAssertion extends BaseAssertion {
    Response responseSimple;
    /**
     * Constructor from response without .then()
     */
    public ArrayAssertion(Response response) {
        super(response);
        responseSimple = response;
    }

    /**
     * Asserts status code and content type
     */
    @Override
    public ArrayAssertion defaultAsserts() {
        return (ArrayAssertion) super.defaultAsserts();
    }

    /**
     * Asserts that all values in an array equal the expected string value.
     * Use when you get an array of entities,
     * and you need to assure that each of them has the specified parameter value
     * @param bodyParameter - an array field in response body
     * @param expectedValue - a string to compare each response array element with
     */
    public ArrayAssertion bodyArrayEquals(String bodyParameter, String expectedValue) {
        List<String> list = getBodyArrayValue(bodyParameter);
        for (String actual: list) {
            Assert.assertEquals(actual, expectedValue); //or better SoftAssert?
        }
        return this;
    }

    /**
     * Asserts that all values in an array contain the expected string value.
     * Use when you get an array of entities,
     * and you need to assure that each of them has the specified parameter containing the given value
     * @param bodyParameter - an array field in response body
     * @param expectedValue - a string to compare each response array element with
     */
    public ArrayAssertion bodyArrayContains(String bodyParameter, String expectedValue) {
        List<String> list = getBodyArrayValue(bodyParameter);
        for (String actual: list) {
            Assert.assertTrue(actual.contains(expectedValue));
        }
        return this;
    }

    /**
     * Retrieves .xmlPath() or .jsonPath() depending on the .contentType()
     */
    private List<String> getBodyArrayValue(String bodyParameter) {
        List<String> list;
        switch (responseSimple.contentType().toUpperCase()) {
            case "XML":
                list = responseSimple.xmlPath().getList(bodyParameter);
                break;
            case "JSON":
            default:
                list = responseSimple.jsonPath().getList(bodyParameter);
                break;

        }
        return list;
    }

    /**
     * Asserts that an array from response body has size greater than given.
     * Use when you get an array of entities,
     * and you need to assure that there is more than N with the specified parameter
     * @param bodyParameter - an array field in response body
     * @param expectedValue - a value to compare array size with
     */
    public ArrayAssertion bodyArraySizeGreater(String bodyParameter, int expectedValue) {
        response.body(bodyParameter + ".size()", greaterThan(expectedValue));
        return this;
    }

    /**
     * Asserts that an array from response body is not empty.
     * Use when you get an array of entities,
     * and you need to assure that there is at least one with the specified parameter
     * @param bodyParameter - an array field in response body
     */
    public ArrayAssertion bodyArrayNotEmpty(String bodyParameter) {
        return bodyArraySizeGreater(bodyParameter, 0);
    }

    /**
     * Asserts that an array from response body is empty.
     * Use when you get an array of entities,
     * and you need to assure that there is no one with the specified parameter
     * @param bodyParameter - an array field in response body
     */
    public ArrayAssertion bodyArrayEmpty(String bodyParameter) {
        response.body(bodyParameter + ".size()", is(0));
        return this;
    }
}
