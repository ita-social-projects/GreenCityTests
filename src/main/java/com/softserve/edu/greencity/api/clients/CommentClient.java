package com.softserve.edu.greencity.api.clients;

import com.softserve.edu.greencity.api.models.comments.CommentDto;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CommentClient extends BaseClient {
    private String authToken;

    public CommentClient(ContentType contentType) {
        super(contentType, "econews/comments");
    }

    public CommentClient(String contentType) {
        super(contentType, "econews/comments");
    }

    /**
     * Use it to create authorized client with possibility to create, update etc.
     *
     * @param contentType content type of response and request
     * @param authToken   unique token. Use OwnSecurityClient to get it
     */
    public CommentClient(ContentType contentType, String authToken) {
        super(contentType, "econews/comments");
        this.authToken = "Bearer " + authToken;
    }

    public Response postComment(String id, CommentDto comment) {
        return prepareRequest()
                .body(comment)
                .header("Authorization", authToken)
                .pathParam("econewsId", id)
                .post("/{entity}/{econewsId}");
    }

    public Response updateComment(String commentId, String text) {
        return prepareRequest()
                .header("Authorization", authToken)
                .queryParam("id", commentId)
                .queryParam("text", text)
                .patch("/{entity}");
    }

    public Response deleteComment(String commentId) {
        return prepareRequest()
                .header("Authorization", authToken)
                .queryParam("id", commentId)
                .delete("/{entity}");
    }

    public Response getAllActiveReplyToComment(String commentId) {
        return prepareRequest()
                .pathParam("parentCommentId", commentId)
                .get("/{entity}/replies/active/{parentCommentId}");
    }
}
