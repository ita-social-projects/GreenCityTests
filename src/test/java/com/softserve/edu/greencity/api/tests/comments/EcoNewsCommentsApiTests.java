package com.softserve.edu.greencity.api.tests.comments;

import com.softserve.edu.greencity.api.assertions.BaseAssertion;
import com.softserve.edu.greencity.api.clients.CommentClient;
import com.softserve.edu.greencity.api.models.comments.CommentDto;
import com.softserve.edu.greencity.api.models.comments.CommentModel;
import io.qameta.allure.Description;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static com.softserve.edu.greencity.data.econews.NewsDataStrings.CONTENT_COMMENT_8001_CHARACTERS;

public class EcoNewsCommentsApiTests extends CommentsApiTestRunner {
    protected Integer parentCommentId;

    @Test(testName = "GC-1154", description = "GC-1154")
    @Description("Verify that logged user can edit its own comment on the ‘Eco news’ page.")
    public void loggedUserCanEditItsOwnComment() {
        CommentClient commentClient = new CommentClient(ContentType.JSON, userData.accessToken);
        Response responseComment = commentClient.postComment(ecoNewsId, new CommentDto(0, "api comment"));
        parentCommentId = responseComment.as(CommentModel.class).id;
        Response responseEdit = commentClient.updateComment(parentCommentId.toString(), "new%20comment%20api");
        BaseAssertion editComment = new BaseAssertion(responseEdit);
        editComment.statusCode(200);
    }

    @Test(testName = "GC-1155", description = "GC-1155")
    @Description("Verify that logged user can delete his own comment on the 'News' page.")
    public void loggedUserCanDeleteHisOwnComment(){
        CommentClient commentClient = new CommentClient(ContentType.JSON, userData.accessToken);
        Response responseComment = commentClient.postComment(ecoNewsId, new CommentDto(0, "api comment"));
        parentCommentId = responseComment.as(CommentModel.class).id;
        Response responseDeleteComment = commentClient.deleteComment(parentCommentId.toString());
        BaseAssertion deleteComment = new BaseAssertion(responseDeleteComment);
        deleteComment.statusCode(200);
    }

    @Test(testName = "GC-1189",description = "GC-1189")
    @Description("Verify that logged user cannot add comment with invalid number of characters on News Single Page ")
    public void loggedUserCannotAddCommentWithInvalidNumberOfCharacters(){
        CommentClient commentClient = new CommentClient(ContentType.JSON, userData.accessToken);
        Response responseAddComment = commentClient.postComment(ecoNewsId, new CommentDto(0, CONTENT_COMMENT_8001_CHARACTERS.getString()));
        BaseAssertion addComment = new BaseAssertion(responseAddComment);
        addComment.statusCode(400);
    }

}