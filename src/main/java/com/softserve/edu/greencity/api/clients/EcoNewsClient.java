package com.softserve.edu.greencity.api.clients;

import com.softserve.edu.greencity.api.models.econews.EcoNewsPOSTdto;
import io.restassured.RestAssured;
import io.restassured.builder.MultiPartSpecBuilder;
import io.restassured.config.EncoderConfig;
import io.restassured.config.LogConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.MultiPartSpecification;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import static io.restassured.RestAssured.given;

public class EcoNewsClient extends BaseClient {

    private final String authToken;

    public EcoNewsClient(ContentType contentType) {
        super(contentType, "econews");
        authToken = "";
    }

    public EcoNewsClient(String contentType) {
        super(contentType, "econews");
        authToken = "";
    }

    /**
     * Use it to create authorized client with possibility to create, update etc.
     *
     * @param contentType content type of response and request
     * @param authToken   unique token. Use OwnSecurityClient to get it
     */
    public EcoNewsClient(ContentType contentType, String authToken) {
        super(contentType, "econews");
        this.authToken = "Bearer " + authToken;
    }

    /**
     * Gives three newest news
     *
     * @return "Array" of news
     */
    public Response getNewest() {
        return prepareRequest()
                .get("{entity}/newest");
    }

    private MultiPartSpecification getMultiPart(EcoNewsPOSTdto news) {
        return new MultiPartSpecBuilder(news.toString().getBytes())
                .controlName("addEcoNewsDtoRequest")
                .fileName(null)
                .build();
    }

    /**
     * Allowed only for authorized clients
     * Pass authorization token in proper constructor
     */
    public Response postNews(EcoNewsPOSTdto news) {
        return given()
                .baseUri(BASE_URL)
                .accept(contentType)
                .pathParam("entity", entity)
                .header("Authorization", authToken)
                .config(RestAssured.config().encoderConfig(EncoderConfig.encoderConfig()
                        .encodeContentTypeAs("multipart/form-data", ContentType.TEXT)))
                .contentType("multipart/form-data; boundary=--MyBoundary")
                .multiPart(getMultiPart(news))
                .post("{entity}");
    }
}
