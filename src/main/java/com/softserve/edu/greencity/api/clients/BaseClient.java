package com.softserve.edu.greencity.api.clients;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.restassured.RestAssured.given;

public abstract class BaseClient {
    public final static String BASE_URL = "https://greencity.azurewebsites.net";
    public final ContentType contentType;
    public final String entity; //the first part of URL after BASE_URL (without slash)
    protected Logger logger;

    public BaseClient(ContentType contentType, String entity) {
        this.contentType = contentType;
        this.entity = entity;
        logger = LoggerFactory.getLogger(entity + "Client" + contentType.toString());
    }

    public BaseClient(String contentType, String entity) {
        switch (contentType.toUpperCase()) {
            case "XML":
                this.contentType = ContentType.XML;
                break;
            case "JSON":
            default:
                this.contentType = ContentType.JSON;
                break;
        }
        this.entity = entity;
        logger = LoggerFactory.getLogger(entity + "Client" + contentType);
    }

    /**
     * Wrapper for given() with .baseUri, .contentType and so on
     * @return RequestSpecification which allows you to use .pathParams, .get and other
     */
    protected RequestSpecification prepareRequest() {
        logger.debug("Preparing request...");
        return given()
                .baseUri(BASE_URL)
                .contentType(contentType)
                .accept(contentType) //By default it doesn't accept response in the same content type
                .pathParam("entity", entity) //just write /{entity}/ instead of /user/ etc. in your requests
                .urlEncodingEnabled(false); //allows passing special characters (slash, in particular) as a parameter
    }
}
