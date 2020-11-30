package com.softserve.edu.greencity.api.tests.signup;

import com.softserve.edu.greencity.api.assertions.ArrayAssertion;
import com.softserve.edu.greencity.api.builders.ownsecurity.SignInBuilder;
import com.softserve.edu.greencity.api.builders.ownsecurity.SignUpBuilder;
import com.softserve.edu.greencity.api.clients.OwnSecurityClient;
import com.softserve.edu.greencity.api.models.ownsecurity.SignInDto;
import com.softserve.edu.greencity.api.models.ownsecurity.SignUpDto;
import io.qameta.allure.Description;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SignUpApiTest {
    @DataProvider(name = "blankCredentials")
    public Object[][] blankCredentials() { //GC-532
        return new Object[][]{
                {"", "", "", "must not be blank"},

        };
    }

    private ArrayAssertion getCommonCredentialsAssertion(String email, String name, String password) {
        SignUpDto credentials = SignUpBuilder.signUpWith()
                .email(email)
                .name(name)
                .password(password)
                .build();
        OwnSecurityClient ownSecurityClient = new OwnSecurityClient("JSON");
        Response response = ownSecurityClient.signUp(credentials);
        response.print();
        return new ArrayAssertion(response)
                .contentType(ContentType.JSON);
    }

    @Test(dataProvider = "blankCredentials", testName = "GC-532", description = "GC-532")
    @Description("Verify that user is not registered, when all values in request are empty")
    public void blankCredentialsTest(String email, String name,  String password, String message) {
        ArrayAssertion assertion = getCommonCredentialsAssertion(email, name, password);
        assertion.statusCode(400)
                .bodyArrayContains("message", message);
    }

}
