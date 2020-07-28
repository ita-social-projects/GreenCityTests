package com.softserve.edu.greencity.api.client;

import com.softserve.edu.greencity.api.model.SignInUser;
import com.softserve.edu.greencity.api.model.SignUpRequest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class OwnSecurityClient extends BaseClient {
    private final String OWN_SECURITY = "/ownSecurity";
    private final String SIGN_IN_URL = OWN_SECURITY + "/signIn";
    private final String SIGN_UP_URL = OWN_SECURITY + "/signUp";

    public Response signIn(final SignInUser user) {
        return given(baseRequestSpecification(ContentType.JSON))
                .body(user)
                .post(SIGN_IN_URL);
    }

    public Response signUp(final SignUpRequest creds) {
        return given(baseRequestSpecification(ContentType.JSON))
                .body(creds)
                .post(SIGN_UP_URL);
    }
}
