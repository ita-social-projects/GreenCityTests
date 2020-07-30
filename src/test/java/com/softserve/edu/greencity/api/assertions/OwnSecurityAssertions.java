package com.softserve.edu.greencity.api.assertions;

import com.softserve.edu.greencity.api.model.InvalidInputResponseOwnSecurity;
import com.softserve.edu.greencity.api.model.UserModel;
import org.testng.asserts.SoftAssert;

import java.util.List;
import java.util.stream.Collectors;

public class OwnSecurityAssertions {

    public static void checkInvalidSignInResponse(final List<InvalidInputResponseOwnSecurity> list, final String expectedName, final String expectedMessage) {
        InvalidInputResponseOwnSecurity responseJsonObject = list.stream()
                .filter(e -> e.getName().contains(expectedName))
                .collect(Collectors.toList())
                .get(0);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(responseJsonObject.getMessage(), expectedMessage);
        softAssert.assertEquals(responseJsonObject.getName(), expectedName);
        softAssert.assertAll();
    }

    public static void checkInvalidSignInResponse(final InvalidInputResponseOwnSecurity invalidInputResponseOwnSecurity, final String expectedName, final String expectedMessage) {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(invalidInputResponseOwnSecurity.getMessage(), expectedMessage);
        softAssert.assertEquals(invalidInputResponseOwnSecurity.getName(), expectedName);
        softAssert.assertAll();
    }

    public static void checkInvalidSignInResponse(final InvalidInputResponseOwnSecurity invalidInputResponseOwnSecurity, final String expectedMessage) {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(invalidInputResponseOwnSecurity.getMessage(), expectedMessage);
        softAssert.assertAll();
    }

    public static void checkSignInUser(final UserModel userModel, final int userId, final String name) {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(userModel.getUserId(), userId);
        softAssert.assertEquals(userModel.getName(), name);
        softAssert.assertTrue(userModel.isOwnRegistrations(), "Error - ownRegistration is false");
        softAssert.assertAll();
    }

    public static void checkValidSignUpResponse(final UserModel response, UserModel signUpRequest) {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.getEmail(), signUpRequest.getEmail());
        softAssert.assertEquals(response.getUsername(), signUpRequest.getName());
        softAssert.assertTrue(response.isOwnRegistrations());
        softAssert.assertAll();
    }

    public static void checkDuplicateEmailResponse(final InvalidInputResponseOwnSecurity response) {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.getName(), "email");
        softAssert.assertEquals(response.getMessage(), "User with this email is already registered");
        softAssert.assertAll();
    }

    public static void checkInvalidPassResponse(final InvalidInputResponseOwnSecurity response) {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.getName(), "password");
        softAssert.assertEquals(response.getMessage(), "Password has contain at least one character of Uppercase letter (A-Z), " +
                "Lowercase letter (a-z), Digit (0-9), Special character (~`!@#$%^&*()+=_-{}[]|:;”’?/<>,.).");
        softAssert.assertAll();
    }

    public static void checkInvalidNameResponse(final InvalidInputResponseOwnSecurity response) {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.getName(), "name");
        softAssert.assertEquals(response.getMessage(), "The username field should be between 1 and 20 characters");
        softAssert.assertAll();
    }

    public static void checkInvalidEmailResponse(final InvalidInputResponseOwnSecurity response) {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.getName(), "email");
        softAssert.assertEquals(response.getMessage(), "The email is invalid");
        softAssert.assertAll();
    }

    public static void checkEmptyEmailResponse(final InvalidInputResponseOwnSecurity response) {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.getName(), "email");
        boolean assertContains = response.getMessage().contains("The email field can not be empty")
                || response.getMessage().contains("The email is invalid");
        softAssert.assertTrue(assertContains);
        softAssert.assertAll();
    }

    public static void checkEmptyPassResponse(final InvalidInputResponseOwnSecurity response) {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.getName(), "password");
        boolean assertContains = response.getMessage().contains("must not be blank")
                || response.getMessage().contains("Password has contain at least one character of Uppercase letter (A-Z), " +
                "Lowercase letter (a-z), Digit (0-9), Special character (~`!@#$%^&*()+=_-{}[]|:;”’?/<>,.).");
        softAssert.assertTrue(assertContains);
        softAssert.assertAll();
    }

    public static void checkEmptyNameResponse(final InvalidInputResponseOwnSecurity response) {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.getName(), "name");
        boolean assertContains = response.getMessage().contains("The username field can not be empty")
                || response.getMessage().contains("The username field should be between 1 and 20 characters");
        softAssert.assertTrue(assertContains);
        softAssert.assertAll();
    }

    public static void checkTooLongNameResponse(final InvalidInputResponseOwnSecurity response) {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.getName(), "name");
        softAssert.assertEquals(response.getMessage(), "The username field should be between 1 and 20 characters");
        softAssert.assertAll();
    }

    public static void checkEmptyFieldsRegistration(List<InvalidInputResponseOwnSecurity> responseObjects) {
        for (InvalidInputResponseOwnSecurity respObj : responseObjects) {
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
