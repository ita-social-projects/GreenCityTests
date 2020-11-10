package com.softserve.edu.greencity.api.clients;

import com.softserve.edu.greencity.api.model.EcoNewsPOSTdto;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class EcoNewsClient extends BaseClient {

    private String authToken;

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
     * @param contentType content type of response and request
     * @param authToken unique token. Use OwnSecurityClient to get it
     */
    public EcoNewsClient(ContentType contentType, String authToken) {
        super(contentType, "econews");
        this.authToken = authToken;
    }

    /**
     * Gives three newest news
     * @return "Array" of news
     */
    public Response getNewest() {
        return prepareRequest().
                get("{entity}/newest");
    }

    /**
     * Allowed only for authorized clients
     * Pass authorization token in proper constructor
     */
    public Response postNews(EcoNewsPOSTdto news) {
        return prepareRequest().
                header("Authorization", authToken).
                body(news).
                post("{entity}");
    }
}
