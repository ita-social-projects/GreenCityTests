package com.softserve.edu.greencity.api.tests.ownsecurity.signin;

import com.softserve.edu.greencity.api.assertions.BaseAssertions;
import com.softserve.edu.greencity.api.builders.userbuilder.SignInUserBuilderImpl;
import com.softserve.edu.greencity.api.builders.userbuilder.SignInUserDirector;
import com.softserve.edu.greencity.api.client.OwnSecurityClient;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.HttpURLConnection;

public class SignInTests {
    OwnSecurityClient ownSecurityClient;
    SignInUserDirector signInUserDirector;
    SignInUserBuilderImpl signInUserBuilder;

    @BeforeClass
    public void beforeClass() {
        ownSecurityClient = new OwnSecurityClient();
        signInUserDirector = new SignInUserDirector();
        signInUserBuilder = new SignInUserBuilderImpl();
    }

    @Test
    public void signInValidationTest() {
        signInUserDirector.constructSignInUserValid(signInUserBuilder);

        Response response = ownSecurityClient.signIn(signInUserBuilder.getResult());

        BaseAssertions.checkResponse(response, HttpURLConnection.HTTP_OK);
    }
}
