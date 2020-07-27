package com.softserve.edu.greencity.api.tests.ownsecurity.signin;

import com.softserve.edu.greencity.api.assertions.BaseAssertions;
import com.softserve.edu.greencity.api.assertions.OwnSecurityAssertions;
import com.softserve.edu.greencity.api.builders.userbuilder.SignInUserBuilderImpl;
import com.softserve.edu.greencity.api.builders.userbuilder.SignInUserDirector;
import com.softserve.edu.greencity.api.client.OwnSecurityClient;
import com.softserve.edu.greencity.api.model.APIResponseBody;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.HttpURLConnection;
import java.util.Arrays;
import java.util.List;

public class SignInNegativeTests {
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
    public void signInWithEmptyCredentials() {
        signInUserDirector.constructSignInUserWithEmptyCreds(signInUserBuilder);

        Response response = ownSecurityClient.signIn(signInUserBuilder.getResult());
        List<APIResponseBody> apiResponseBodies = Arrays.asList(response.getBody().as(APIResponseBody[].class));

        BaseAssertions.checkResponse(response, HttpURLConnection.HTTP_BAD_REQUEST);
        OwnSecurityAssertions.checkInvalidSignInResponse(apiResponseBodies.get(0), "password", "must not be blank");
        OwnSecurityAssertions.checkInvalidSignInResponse(apiResponseBodies.get(1), "email", "must not be blank");
    }

    @Test
    public void signInWithEmptyEmailField() {
        signInUserDirector.constructSignInUserWithEmptyEmail(signInUserBuilder);

        Response response = ownSecurityClient.signIn(signInUserBuilder.getResult());
        List<APIResponseBody> apiResponseBodies = Arrays.asList(response.getBody().as(APIResponseBody[].class));

        BaseAssertions.checkResponse(response, HttpURLConnection.HTTP_BAD_REQUEST);
        OwnSecurityAssertions.checkInvalidSignInResponse(apiResponseBodies.get(0), "email", "must not be blank");
    }

    @Test
    public void signInWithEmptyPasswordField() {
        signInUserDirector.constructSignInUserWithEmptyPassword(signInUserBuilder);

        Response response = ownSecurityClient.signIn(signInUserBuilder.getResult());
        List<APIResponseBody> apiResponseBodies = Arrays.asList(response.getBody().as(APIResponseBody[].class));

        BaseAssertions.checkResponse(response, HttpURLConnection.HTTP_BAD_REQUEST);
        OwnSecurityAssertions.checkInvalidSignInResponse(apiResponseBodies.get(0), "password", "must not be blank");
    }

    @Test
    public void signInWithIncorrectPasswordField() {
        signInUserDirector.constructSignInUserWithIncorrectPassword(signInUserBuilder);

        Response response = ownSecurityClient.signIn(signInUserBuilder.getResult());
        APIResponseBody apiResponseBody = response.getBody().as(APIResponseBody.class);

        BaseAssertions.checkResponse(response, HttpURLConnection.HTTP_BAD_REQUEST);
        OwnSecurityAssertions.checkInvalidSignInResponse(apiResponseBody, "Bad password");
    }

    @Test
    public void signInWithIncorrectEmail() {
        signInUserDirector.constructSignInUserWithIncorrectEmail(signInUserBuilder);

        Response response = ownSecurityClient.signIn(signInUserBuilder.getResult());
        List<APIResponseBody> apiResponseBodies = Arrays.asList(response.getBody().as(APIResponseBody[].class));

        BaseAssertions.checkResponse(response, HttpURLConnection.HTTP_BAD_REQUEST);
        OwnSecurityAssertions.checkInvalidSignInResponse(apiResponseBodies.get(0), "email", "The email is invalid");
    }

    @Test
    public void signInWithUnregisteredCredentials() {
        signInUserDirector.constructSignInUserWithUnregisteredCreds(signInUserBuilder);

        Response response = ownSecurityClient.signIn(signInUserBuilder.getResult());
        APIResponseBody apiResponseBody = response.as(APIResponseBody.class);

        BaseAssertions.checkResponse(response, HttpURLConnection.HTTP_BAD_REQUEST);
        OwnSecurityAssertions.checkInvalidSignInResponse(apiResponseBody, "Bad email or password: ");
    }
}
