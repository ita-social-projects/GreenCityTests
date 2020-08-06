package com.softserve.edu.greencity.api.tests.ownsecurity.signin;

import com.softserve.edu.greencity.api.assertions.BaseAssertions;
import com.softserve.edu.greencity.api.assertions.OwnSecurityAssertions;
import com.softserve.edu.greencity.api.builders.userbuilder.UserBuilder;
import com.softserve.edu.greencity.api.builders.userbuilder.UserDirector;
import com.softserve.edu.greencity.api.client.OwnSecurityClient;
import com.softserve.edu.greencity.api.listeners.LogListener;
import com.softserve.edu.greencity.api.model.InvalidInputResponseOwnSecurity;
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
    UserBuilder userBuilder;

    @BeforeClass
    public void beforeClass() {
        ownSecurityClient = new OwnSecurityClient();
        userDirector = new UserDirector();
        userBuilder = new UserBuilder();
    }

    @Test(testName = "GC-490")
    public void signInWithEmptyCredentials() {
        userDirector.constructUserWithEmptyCreds(userBuilder);

        Response response = ownSecurityClient.signIn(userBuilder.getResult());
        List<InvalidInputResponseOwnSecurity> invalidInputResponseOwnSecurities = Arrays.asList(response.getBody().as(InvalidInputResponseOwnSecurity[].class));

        BaseAssertions.checkResponse(response, HttpURLConnection.HTTP_BAD_REQUEST);
        OwnSecurityAssertions.checkInvalidSignInResponse(invalidInputResponseOwnSecurities, "email", "must not be blank");
        OwnSecurityAssertions.checkInvalidSignInResponse(invalidInputResponseOwnSecurities, "password", "must not be blank");
    }

    @Test(testName = "GC-490")
    public void signInWithEmptyEmailField() {
        userDirector.constructUserWithEmptyEmail(userBuilder);

        Response response = ownSecurityClient.signIn(userBuilder.getResult());
        List<InvalidInputResponseOwnSecurity> invalidInputResponseOwnSecurities = Arrays.asList(response.getBody().as(InvalidInputResponseOwnSecurity[].class));

        BaseAssertions.checkResponse(response, HttpURLConnection.HTTP_BAD_REQUEST);
        OwnSecurityAssertions.checkInvalidSignInResponse(invalidInputResponseOwnSecurities, "email", "must not be blank");
    }

    @Test(testName = "GC-490")
    public void signInWithEmptyPasswordField() {
        userDirector.constructUserWithEmptyPassword(userBuilder);

        Response response = ownSecurityClient.signIn(userBuilder.getResult());
        List<InvalidInputResponseOwnSecurity> invalidInputResponseOwnSecurities = Arrays.asList(response.getBody().as(InvalidInputResponseOwnSecurity[].class));

        BaseAssertions.checkResponse(response, HttpURLConnection.HTTP_BAD_REQUEST);
        OwnSecurityAssertions.checkInvalidSignInResponse(invalidInputResponseOwnSecurities, "password", "must not be blank");
    }

    @Test(testName = "GC-495")
    public void signInWithIncorrectPasswordField() {
        userDirector.constructUserWithIncorrectPassword(userBuilder);

        Response response = ownSecurityClient.signIn(userBuilder.getResult());
        InvalidInputResponseOwnSecurity invalidInputResponseOwnSecurity = response.getBody().as(InvalidInputResponseOwnSecurity.class);

        BaseAssertions.checkResponse(response, HttpURLConnection.HTTP_BAD_REQUEST);
        OwnSecurityAssertions.checkInvalidSignInResponse(invalidInputResponseOwnSecurity, "Bad password");
    }

    @Test(testName = "GC-493")
    public void signInWithIncorrectEmail() {
        userDirector.constructUserWithIncorrectEmail(userBuilder);

        Response response = ownSecurityClient.signIn(userBuilder.getResult());
        List<InvalidInputResponseOwnSecurity> invalidInputResponseOwnSecurities = Arrays.asList(response.getBody().as(InvalidInputResponseOwnSecurity[].class));

        BaseAssertions.checkResponse(response, HttpURLConnection.HTTP_BAD_REQUEST);
        OwnSecurityAssertions.checkInvalidSignInResponse(invalidInputResponseOwnSecurities, "email", "must not be blank");
    }

    @Test(testName = "GC-493")
    public void signInWithUnregisteredCredentials() {
        userDirector.constructUserWithUnregisteredEmail(userBuilder);

        Response response = ownSecurityClient.signIn(userBuilder.getResult());
        InvalidInputResponseOwnSecurity invalidInputResponseOwnSecurity = response.as(InvalidInputResponseOwnSecurity.class);

        BaseAssertions.checkResponse(response, HttpURLConnection.HTTP_BAD_REQUEST);
        OwnSecurityAssertions.checkInvalidSignInResponse(invalidInputResponseOwnSecurity, "Bad email or password: ");
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
