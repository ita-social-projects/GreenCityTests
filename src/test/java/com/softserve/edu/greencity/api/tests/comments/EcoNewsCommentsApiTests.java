package com.softserve.edu.greencity.api.tests.comments;

import com.softserve.edu.greencity.api.assertions.BaseAssertion;
import com.softserve.edu.greencity.api.clients.CommentClient;
import com.softserve.edu.greencity.api.clients.OwnSecurityClient;
import com.softserve.edu.greencity.api.models.comments.CommentDto;
import com.softserve.edu.greencity.api.models.comments.CommentModel;
import com.softserve.edu.greencity.api.models.ownsecurity.OwnSecurityModel;
import com.softserve.edu.greencity.api.models.ownsecurity.SignInDto;
import com.softserve.edu.greencity.data.users.User;
import com.softserve.edu.greencity.data.users.UserRepository;
import io.qameta.allure.Description;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

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

    @Test(testName = "GC-1194", description = "GC-1194")
    @Description("Verify that logged user can’t edit not his own comment on the ‘Eco news’ page")
    public void loggedUserCanNotEditNotHisOwnComment() {
        CommentClient commentClientTemporary = new CommentClient(ContentType.JSON, userData.accessToken);
        Response responseComment = commentClientTemporary.postComment(ecoNewsId, new CommentDto(0, "api comment"));
        parentCommentId = responseComment.as(CommentModel.class).id;
        OwnSecurityClient authorizationClient = new OwnSecurityClient(ContentType.JSON);
        User existUser = UserRepository.get().exist();
        Response signedIn = authorizationClient
                .signIn(new SignInDto(existUser.getEmail(), existUser.getPassword()));
        OwnSecurityModel existUserData = signedIn.as(OwnSecurityModel.class);
        CommentClient commentClientExist = new CommentClient(ContentType.JSON, existUserData.accessToken);
        Response responseEdit = commentClientExist.updateComment(parentCommentId.toString(), "new%20comment%20api");
        BaseAssertion deleteComment = new BaseAssertion(responseEdit);
        deleteComment.statusCode(400);
    }
}