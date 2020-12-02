package com.softserve.edu.greencity.api.tests.signin;

import com.softserve.edu.greencity.api.assertions.ArrayAssertion;
import com.softserve.edu.greencity.api.builders.ownsecurity.SignInBuilder;
import com.softserve.edu.greencity.api.clients.OwnSecurityClient;
import com.softserve.edu.greencity.api.models.ownsecurity.SignInDto;
import com.softserve.edu.greencity.api.tests.GreenCityAPITestRunner;
import io.qameta.allure.Description;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SignInApiTest extends GreenCityAPITestRunner {
    @DataProvider(name = "blankCredentials")
    public Object[][] blankCredentials() { //GC-490
        return new Object[][]{
                {"", "Pa$$word1", "must not be blank"},
                {"test@test.ua", "", "must not be blank"},
                {"", "", "must not be blank"},
        };
    }

    @DataProvider(name = "invalidEmail")
    public Object[][] invalidEmail() { //GC-493, GC-494
        return new Object[][]{
                {"test#test.ua", "Test1!", "The email is invalid"},
        };
    }

    @DataProvider(name = "invalidCredentials")
    public Object[][] invalidCredentials() {
        return new Object[][]{
                {"green.city.test10@gmail.com", "Green.city#120", "The user does not exist by this email"}, //GC-493, GC-507
                {"green.city.test2@gmail.com", "123456", "Bad password"}, //GC-495
                {"green.city.test2@gmail.com", "Gr.city#100", "Bad password"} //GC-496
        };
    }

    @DataProvider(name = "validCredentials")
    public Object[][] validCredentials() { //GC-506
        return new Object[][]{
                {"green.city.test2@gmail.com", "Green.city#500"}
        };
    }

    /**
     * Does similar logic for credentials tests.
     * Also asserts that content type is JSON
     */
    private ArrayAssertion getCommonCredentialsAssertion(String email, String password) {
        SignInDto credentials = SignInBuilder.signInWith()
                .email(email)
                .password(password)
                .build();
        OwnSecurityClient ownSecurityClient = new OwnSecurityClient("JSON");
        Response response = ownSecurityClient.signIn(credentials);
        response.print();
        return new ArrayAssertion(response)
                .contentType(ContentType.JSON);
    }

    @Test(dataProvider = "blankCredentials", testName = "GC-490", description = "GC-490")
    @Description("Verify that user gets error message when sign in with empty text fields")
    public void blankCredentialsTest(String email, String password, String message) {
        ArrayAssertion assertion = getCommonCredentialsAssertion(email, password);
        assertion.statusCode(400)
                .bodyArrayContains("message", message);
    }

    @Test(dataProvider = "invalidEmail", testName = "GC-493, GC-494", description = "GC-493, GC-494")
    @Description("Verify that user gets error message and no token when sign in with incorrect email")
    public void invalidEmailTest(String email, String password, String message) {
        ArrayAssertion assertion = getCommonCredentialsAssertion(email, password);
        assertion.statusCode(400)
                .bodyArrayContains("message", message) //For some reason, they return an array here
                .bodyNoParameter("accessToken");
    }


    @Test(dataProvider = "invalidCredentials", testName = "GC-495, GC-496, GC-507", description = "GC-495, GC-496, GC-507")
    @Description("Verifies proper status codes and error messages on providing improper email/password")
    public void badCredentialsTest(String email, String password, String message) {
        ArrayAssertion assertion = getCommonCredentialsAssertion(email, password);
        assertion
                .bodyValueContains("message", message)
                .bodyNoParameter("accessToken")
                .statusCode(400); //Sometimes the status code is improper (500), so let's check it last
    }

    @Test(dataProvider = "validCredentials", testName = "GC-506", description = "GC-506")
    @Description("Verify that user can get token to authorize with valid credential")
    public void validCredentialsTest(String email, String password) {
        ArrayAssertion assertion = getCommonCredentialsAssertion(email, password);
        assertion.defaultAsserts()
                .bodyHasParameter("accessToken");
    }
}
