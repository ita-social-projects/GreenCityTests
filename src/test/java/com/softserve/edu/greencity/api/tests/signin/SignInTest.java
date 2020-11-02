package com.softserve.edu.greencity.api.tests.signin;

import com.softserve.edu.greencity.api.assertions.ArrayAssertion;
import com.softserve.edu.greencity.api.assertions.BaseAssertion;
import com.softserve.edu.greencity.api.builders.ownsecurity.SignInBuilder;
import com.softserve.edu.greencity.api.clients.OwnSecurityClient;
import com.softserve.edu.greencity.api.models.ownsecurity.SignInDto;
import com.softserve.edu.greencity.ui.data.UserRepository;
import io.qameta.allure.Description;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SignInTest {
    @DataProvider(name = "invalidCredentials")
    public Object[][] invalidCredentials() {
        return new Object[][]{
                {"", "", "must not be blank"}, //GC-490
                {"test#test.ua", "Test1!", "The email is invalid"}, //GC-493
                {"green.city.test10@gmail.com", "Green.city#120", "Bad email or password"}, //Also GC-493
                {"green.city.test2@gmail.com", "123456", "Bad password"}, //GC-495
        };
    }

    @Test(dataProvider = "invalidCredentials")
    @Description("Verifies proper status codes and error messages on providing improper email/password")
    public void badCredentialsTest(String email, String password, String message) {
        SignInDto credentials = SignInBuilder.signInWith()
                .email(email)
                .password(password)
                .build();
        OwnSecurityClient ownSecurityClient = new OwnSecurityClient("JSON");
        Response response = ownSecurityClient.signIn(credentials);
        response.print();
        ArrayAssertion assertion = new ArrayAssertion(response);
        assertion.statusCode(400)
                .contentType(ContentType.JSON)
                .bodyArrayEquals("message", message);
    }
}
