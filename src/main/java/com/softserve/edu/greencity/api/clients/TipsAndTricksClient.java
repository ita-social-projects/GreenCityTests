package com.softserve.edu.greencity.api.clients;

import io.restassured.http.ContentType;

public class TipsAndTricksClient extends BaseClient{
    private final String authToken;
    private static final String url = "https://greencity.azurewebsites.net";

    public TipsAndTricksClient(ContentType contentType) {
        super(contentType, "tipsandtricks",url);
        authToken = "";
    }

    public TipsAndTricksClient(String contentType) {
        super(contentType, "tipsandtricks",url);
        authToken = "";
    }

    /**
     * Use it to create authorized client with possibility to create, update etc.
     * @param contentType content type of response and request
     * @param authToken   unique token. Use OwnSecurityClient to get it
     */
    public TipsAndTricksClient(ContentType contentType, String authToken) {
        super(contentType, "tipsandtricks",url);
        this.authToken = "Bearer " + authToken;
    }
}
