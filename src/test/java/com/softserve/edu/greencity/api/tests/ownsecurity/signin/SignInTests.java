package com.softserve.edu.greencity.api.tests.ownsecurity.signin;

import com.softserve.edu.greencity.api.assertions.BaseAssertions;
import com.softserve.edu.greencity.api.assertions.OwnSecurityAssertions;
import com.softserve.edu.greencity.api.builders.userbuilder.UserBuilderImpl;
import com.softserve.edu.greencity.api.builders.userbuilder.UserDirector;
import com.softserve.edu.greencity.api.client.OwnSecurityClient;
import com.softserve.edu.greencity.api.listeners.LogListener;
import com.softserve.edu.greencity.api.model.APIResponseOwnSecurityModel;
import com.softserve.edu.greencity.api.model.UserModel;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.net.HttpURLConnection;
import java.util.Arrays;
import java.util.List;

@Listeners(LogListener.class)
public class SignInTests {
    OwnSecurityClient ownSecurityClient;
    UserDirector userDirector;
    UserBuilderImpl userBuilder;

    @BeforeClass
    public void beforeClass() {
        ownSecurityClient = new OwnSecurityClient();
        userDirector = new UserDirector();
        userBuilder = new UserBuilderImpl();
    }

    @Test(testName = "GC-490")
    public void signInWithEmptyCredentials() {
        userDirector.constructUserWithEmptyCreds(userBuilder);

        Response response = ownSecurityClient.signIn(userBuilder.getResult());
        List<APIResponseOwnSecurityModel> apiResponseBodies = Arrays.asList(response.getBody().as(APIResponseOwnSecurityModel[].class));

        BaseAssertions.checkResponse(response, HttpURLConnection.HTTP_BAD_REQUEST);
        OwnSecurityAssertions.checkInvalidSignInResponse(apiResponseBodies.get(0), "password", "must not be blank");
        OwnSecurityAssertions.checkInvalidSignInResponse(apiResponseBodies.get(1), "email", "must not be blank");
    }

    @Test(testName = "GC-490")
    public void signInWithEmptyEmailField() {
        userDirector.constructUserWithEmptyEmail(userBuilder);

        Response response = ownSecurityClient.signIn(userBuilder.getResult());
        List<APIResponseOwnSecurityModel> apiResponseBodies = Arrays.asList(response.getBody().as(APIResponseOwnSecurityModel[].class));

        BaseAssertions.checkResponse(response, HttpURLConnection.HTTP_BAD_REQUEST);
        OwnSecurityAssertions.checkInvalidSignInResponse(apiResponseBodies.get(0), "email", "must not be blank");
    }

    @Test(testName = "GC-490")
    public void signInWithEmptyPasswordField() {
        userDirector.constructUserWithEmptyPassword(userBuilder);

        Response response = ownSecurityClient.signIn(userBuilder.getResult());
        List<APIResponseOwnSecurityModel> apiResponseBodies = Arrays.asList(response.getBody().as(APIResponseOwnSecurityModel[].class));

        BaseAssertions.checkResponse(response, HttpURLConnection.HTTP_BAD_REQUEST);
        OwnSecurityAssertions.checkInvalidSignInResponse(apiResponseBodies.get(0), "password", "must not be blank");
    }

    @Test(testName = "GC-495")
    public void signInWithIncorrectPasswordField() {
        userDirector.constructUserWithIncorrectPassword(userBuilder);

        Response response = ownSecurityClient.signIn(userBuilder.getResult());
        APIResponseOwnSecurityModel apiResponseOwnSecurityModel = response.getBody().as(APIResponseOwnSecurityModel.class);

        BaseAssertions.checkResponse(response, HttpURLConnection.HTTP_BAD_REQUEST);
        OwnSecurityAssertions.checkInvalidSignInResponse(apiResponseOwnSecurityModel, "Bad password");
    }

    @Test(testName = "GC-493")
    public void signInWithIncorrectEmail() {
        userDirector.constructUserWithIncorrectEmail(userBuilder);

        Response response = ownSecurityClient.signIn(userBuilder.getResult());
        List<APIResponseOwnSecurityModel> apiResponseBodies = Arrays.asList(response.getBody().as(APIResponseOwnSecurityModel[].class));

        BaseAssertions.checkResponse(response, HttpURLConnection.HTTP_BAD_REQUEST);
        OwnSecurityAssertions.checkInvalidSignInResponse(apiResponseBodies.get(0), "email", "The email is invalid");
    }

    @Test(testName = "GC-493")
    public void signInWithUnregisteredCredentials() {
        userDirector.constructUserWithUnregisteredEmail(userBuilder);

        Response response = ownSecurityClient.signIn(userBuilder.getResult());
        APIResponseOwnSecurityModel apiResponseOwnSecurityModel = response.as(APIResponseOwnSecurityModel.class);

        BaseAssertions.checkResponse(response, HttpURLConnection.HTTP_BAD_REQUEST);
        OwnSecurityAssertions.checkInvalidSignInResponse(apiResponseOwnSecurityModel, "Bad email or password: ");
    }

    @Test(testName = "GC-506")
    public void signInValidation() {
        userDirector.constructValidUser(userBuilder);

        Response response = ownSecurityClient.signIn(userBuilder.getResult());
        UserModel userModel = response.as(UserModel.class);

        BaseAssertions.checkResponse(response, HttpURLConnection.HTTP_OK);
        OwnSecurityAssertions.checkSignInUser(userModel, 302, "My name is User_Lviv");
    }
}
