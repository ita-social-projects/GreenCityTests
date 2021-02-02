package com.softserve.edu.greencity.api.clients;

import com.softserve.edu.greencity.api.models.comments.CommentDto;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CommentTipsAndTricksClient extends BaseClient{
    private static final String url = "https://greencity.azurewebsites.net";
    private String authToken;

    public CommentTipsAndTricksClient(ContentType contentType) {
        super(contentType, "tipsandtricks/comments", url);
    }

    public CommentTipsAndTricksClient(String contentType) {
        super(contentType, "tipsandtricks/comments", url);
    }

    public CommentTipsAndTricksClient(ContentType contentType, String authToken) {
        super(contentType, "tipsandtricks/comments", url);
        this.authToken = "Bearer " + authToken;
    }

    public Response postComment(String id, CommentDto comment) {
        return prepareRequest()
                .body(comment)
                .header("Authorization", authToken)
                .pathParam("tipsAndTricksId", id)
                .post("/{entity}/{tipsAndTricksId}");
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
}

