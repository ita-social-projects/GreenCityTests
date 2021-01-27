package com.softserve.edu.greencity.api.clients;

import com.softserve.edu.greencity.api.models.comments.CommentDto;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CommentClient extends BaseClient {
    private static final String url = "https://greencity.azurewebsites.net";
    private String authToken;

    public CommentClient(ContentType contentType) {
        super(contentType, "econews/comments", url);
    }

    public CommentClient(String contentType) {
        super(contentType, "econews/comments", url);
    }

    /**
     * Use it to create authorized client with possibility to create, update etc.
     *
     * @param contentType content type of response and request
     * @param authToken   unique token. Use OwnSecurityClient to get it
     */
    public CommentClient(ContentType contentType, String authToken) {
        super(contentType, "econews/comments", url);
        this.authToken = "Bearer " + authToken;
    }

    public Response postComment(String id, CommentDto comment) {
        return prepareRequest()
                .body(comment)
                .header("Authorization", authToken)
                .pathParam("econewsId", id)
                .post("/{entity}/{econewsId}");
    }

    public Response postCommentForUnloggedUser(String id, CommentDto comment) {
        return prepareRequest()
                .body(comment)
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

    public Response updateCommentByNotLoggedUser(String commentId, String text) {
        return prepareRequest()
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

    public Response deleteCommentForUnloggedUser(String commentId) {
        return prepareRequest()
                .queryParam("id", commentId)
                .delete("/{entity}");
    }

    public Response getAllActiveReplyToComment(String commentId) {
        return prepareRequest()
                .pathParam("parentCommentId", commentId)
                .get("/{entity}/replies/active/{parentCommentId}");
    }

    public Response postLikeTheCommentOrReplyForUnloggedUser(String commentOrReplyId){
        return prepareRequest()
                .queryParam("parentCommentId", commentOrReplyId)
                .post("/{entity}/like");
    }

    public Response getCountComments(String ecoNewsId) {
        return prepareRequest()
                .pathParam("ecoNewsId",ecoNewsId)
                .get("/{entity}/count/comments/{ecoNewsId}");
    }
}
