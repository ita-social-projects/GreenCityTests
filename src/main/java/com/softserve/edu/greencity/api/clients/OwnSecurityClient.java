package com.softserve.edu.greencity.api.clients;

import com.softserve.edu.greencity.api.models.ownsecurity.SignInDto;
import com.softserve.edu.greencity.api.models.ownsecurity.SignUpDto;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class OwnSecurityClient extends BaseClient {

    public OwnSecurityClient(ContentType contentType) {
        super(contentType, "ownSecurity");
    }

    public OwnSecurityClient(String contentType) {
        super(contentType, "ownSecurity");
    }

    public Response signIn(SignInDto credentials) {
        return prepareRequest()
                .body(credentials)
                .post("/{entity}/signIn");
    }

    public Response signUp(SignUpDto credentials) {
        return prepareRequest()
                .body(credentials)
                .post("/{entity}/signUp");
    }
}
