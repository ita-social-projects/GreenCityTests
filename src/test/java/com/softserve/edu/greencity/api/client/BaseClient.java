package com.softserve.edu.greencity.api.client;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class BaseClient {
    private final String BASE_URL = "https://greencity.azurewebsites.net/";

    protected final RequestSpecification baseRequestSpecification(final ContentType contentType) {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder
                .setBaseUri(BASE_URL)
                .setAccept(contentType)
                .setContentType(contentType);

        return requestSpecBuilder.build();
    }
}
