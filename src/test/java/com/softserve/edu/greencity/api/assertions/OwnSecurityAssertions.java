package com.softserve.edu.greencity.api.assertions;

import com.softserve.edu.greencity.api.model.APIResponseBody;
import com.softserve.edu.greencity.api.model.SignUpRequest;
import com.softserve.edu.greencity.api.model.SuccessfulSignUp;
import org.testng.asserts.SoftAssert;

import java.util.List;

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

    public static void checkValidSignUpResponse(final SuccessfulSignUp response, SignUpRequest signUpRequest) {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.getEmail(), signUpRequest.getEmail());
        softAssert.assertEquals(response.getUsername(), signUpRequest.getName());
        softAssert.assertTrue(response.getOwnRegistrations());
        softAssert.assertAll();
    }

    public static void checkDuplicateEmailResponse(final APIResponseBody response) {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.getName(), "email");
        softAssert.assertEquals(response.getMessage(), "User with this email is already registered");
        softAssert.assertAll();
    }

    public static void checkInvalidPassResponse(final APIResponseBody response) {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.getName(), "password");
        softAssert.assertEquals(response.getMessage(), "Password has contain at least one character of Uppercase letter (A-Z), " +
                "Lowercase letter (a-z), Digit (0-9), Special character (~`!@#$%^&*()+=_-{}[]|:;”’?/<>,.).");
        softAssert.assertAll();
    }

    public static void checkInvalidNameResponse(final APIResponseBody response) {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.getName(), "name");
        softAssert.assertEquals(response.getMessage(), "The username field should be between 1 and 20 characters");
        softAssert.assertAll();
    }

    public static void checkInvalidEmailResponse(final APIResponseBody response) {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.getName(), "email");
        softAssert.assertEquals(response.getMessage(), "The email is invalid");
        softAssert.assertAll();
    }

    public static void checkEmptyEmailResponse(final APIResponseBody response) {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.getName(), "email");
        softAssert.assertEquals(response.getMessage(), "The email field can not be empty");
        softAssert.assertAll();
    }

    public static void checkEmptyPassResponse(final APIResponseBody response) {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.getName(), "password");
        boolean assertContains = response.getMessage().contains("must not be blank")
                || response.getMessage().contains("Password has contain at least one character of Uppercase letter (A-Z), " +
                "Lowercase letter (a-z), Digit (0-9), Special character (~`!@#$%^&*()+=_-{}[]|:;”’?/<>,.).");
        softAssert.assertTrue(assertContains);
        softAssert.assertAll();
    }

    public static void checkEmptyNameResponse(final APIResponseBody response) {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.getName(), "name");
        boolean assertContains = response.getMessage().contains("The username field can not be empty")
                || response.getMessage().contains("The username field should be between 1 and 20 characters");
        softAssert.assertTrue(assertContains);
        softAssert.assertAll();
    }

    public static void checkTooLongNameResponse(final APIResponseBody response) {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.getName(), "name");
        softAssert.assertEquals(response.getMessage(), "The username field should be between 1 and 20 characters");
        softAssert.assertAll();
    }

    public static void checkEmptyFieldsRegistration(List<APIResponseBody> responseObjects) {
        for (APIResponseBody respObj : responseObjects) {
            if (respObj.getName().equals("name")) {
                OwnSecurityAssertions.checkEmptyNameResponse(respObj);
            } else if (respObj.getName().equals("password")) {
                OwnSecurityAssertions.checkEmptyPassResponse(respObj);
            } else if (respObj.getName().equals("email")) {
                OwnSecurityAssertions.checkEmptyEmailResponse(respObj);
            }
        }

    }
}
