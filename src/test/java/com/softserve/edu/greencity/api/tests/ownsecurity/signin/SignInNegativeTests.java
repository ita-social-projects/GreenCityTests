package com.softserve.edu.greencity.api.tests.ownsecurity.signin;

import com.softserve.edu.greencity.api.assertions.BaseAssertions;
import com.softserve.edu.greencity.api.assertions.OwnSecurityAssertions;
import com.softserve.edu.greencity.api.client.OwnSecurityClient;
import com.softserve.edu.greencity.api.model.APIResponseBody;
import com.softserve.edu.greencity.ui.data.User;
import com.softserve.edu.greencity.ui.data.UserRepository;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.HttpURLConnection;
import java.util.Arrays;
import java.util.List;

public class SignInNegativeTests {
    OwnSecurityClient ownSecurityClient;
    User userWithEmptyCredentials;
    User userWithEmptyEmail;
    User userWithEmptyPassword;

    @BeforeClass
    public void beforeClass() {
        ownSecurityClient = new OwnSecurityClient();
        userWithEmptyCredentials = UserRepository.get().emptyUserCredentials();
        userWithEmptyEmail = UserRepository.get().userWithEmptyEmailField();
        userWithEmptyPassword = UserRepository.get().userWithEmptyPasswordField();
    }

    @Test
    public void signInWithEmptyCredentials() {
        Response response = ownSecurityClient.signIn(userWithEmptyCredentials);
        List<APIResponseBody> apiResponseBodies = Arrays.asList(response.getBody().as(APIResponseBody[].class));

        BaseAssertions.checkResponse(response, HttpURLConnection.HTTP_BAD_REQUEST);
        OwnSecurityAssertions.checkInvalidSignInResponse(apiResponseBodies.get(0), "password", "must not be blank");
        OwnSecurityAssertions.checkInvalidSignInResponse(apiResponseBodies.get(1), "email", "must not be blank");
    }

    @Test
    public void signInWithEmptyEmailField() {
        Response response = ownSecurityClient.signIn(userWithEmptyEmail);
        List<APIResponseBody> apiResponseBodies = Arrays.asList(response.getBody().as(APIResponseBody[].class));

        BaseAssertions.checkResponse(response, HttpURLConnection.HTTP_BAD_REQUEST);
        OwnSecurityAssertions.checkInvalidSignInResponse(apiResponseBodies.get(0), "email", "must not be blank");
    }

    @Test
    public void signInWithEmptyPasswordField() {
        Response response = ownSecurityClient.signIn(userWithEmptyPassword);
        List<APIResponseBody> apiResponseBodies = Arrays.asList(response.getBody().as(APIResponseBody[].class));

        BaseAssertions.checkResponse(response, HttpURLConnection.HTTP_BAD_REQUEST);
        OwnSecurityAssertions.checkInvalidSignInResponse(apiResponseBodies.get(0), "password", "must not be blank");
    }
}
