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
import org.testng.Assert;
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
    public void loggedUserCanDeleteHisOwnComment() {
        CommentClient commentClient = new CommentClient(ContentType.JSON, userData.accessToken);
        Response responseComment = commentClient.postComment(ecoNewsId, new CommentDto(0, "api comment"));
        parentCommentId = responseComment.as(CommentModel.class).id;
        Response responseDeleteComment = commentClient.deleteComment(parentCommentId.toString());
        BaseAssertion deleteComment = new BaseAssertion(responseDeleteComment);
        deleteComment.statusCode(200);
    }

    @Test(testName = "GC-1158", description = "GC-1158")
    @Description("Verify that logged user can`t delete not his own comment on the 'News' page.")
    public void loggedUserCanDeleteNotHisOwnComment() {
        CommentClient temporaryClient = new CommentClient(ContentType.JSON, userData.accessToken);
        Response responsePostComment = temporaryClient.postComment(ecoNewsId, new CommentDto(0, "Comment by temporary user"));
        parentCommentId = responsePostComment.as(CommentModel.class).id;
        User existUser = UserRepository.get().exist();
        OwnSecurityClient authorizationExistClient = new OwnSecurityClient(ContentType.JSON);
        Response signIn = authorizationExistClient
                .signIn(new SignInDto(existUser.getEmail(), existUser.getPassword()));
        OwnSecurityModel existUserData = signIn.as(OwnSecurityModel.class);
        CommentClient existClient = new CommentClient(ContentType.JSON, existUserData.accessToken);
        Response responseDeleteComment = existClient.deleteComment(parentCommentId.toString());
        BaseAssertion deleteComment = new BaseAssertion(responseDeleteComment);
        deleteComment.statusCode(403);
    }

    @Test(testName = "GC-1159", description = "GC-1159")
    @Description("Unregister user can`t delete any comment on the 'News' page.")
    public void unloggedUserCanNotDeleteComment() {
        CommentClient commentClient = new CommentClient(ContentType.JSON, userData.accessToken);
        Response responsePostComment = commentClient.postComment(ecoNewsId, new CommentDto(0, "api comment for 1159"));
        parentCommentId = responsePostComment.as(CommentModel.class).id;
        CommentClient unloggedClient = new CommentClient(ContentType.JSON);
        Response responseDeleteComment = unloggedClient.deleteCommentForUnloggedUser(parentCommentId.toString());
        BaseAssertion deleteComment = new BaseAssertion(responseDeleteComment);
        deleteComment.statusCode(401)
                .bodyValueContains("message", "Authorize first.");
    }

    @Test(testName = "GC-1160", description = "GC-1160")
    @Description("Verify that logged user can add comment on News Single Page")
    public void loggedUserCanAddHisOwnComment() {
        CommentClient commentClient = new CommentClient(ContentType.JSON, userData.accessToken);
        Response responseComment = commentClient.postComment(ecoNewsId, new CommentDto(0, "api comment"));
        BaseAssertion addComment = new BaseAssertion(responseComment);
        addComment.statusCode(201);
    }

    @Test(testName = "GC-1163", description = "1163")
    @Description("Verify that logged user can publish reply on News Single Page")
    public void loggedUserCanPublishReply() {
        CommentClient commentClient = new CommentClient(ContentType.JSON, userData.accessToken);
        Response responseComment = commentClient.postComment(ecoNewsId, new CommentDto(0, "api comment"));
        parentCommentId = responseComment.as(CommentModel.class).id;
        Response responseCommentReply = commentClient.postComment(ecoNewsId, new CommentDto(parentCommentId, "gc-1163_reply"));
        BaseAssertion publishReply = new BaseAssertion(responseCommentReply);
        publishReply.statusCode(201);
    }

    @Test(testName = "GC-1169", description = "1169")
    @Description("Verify that logged user cannot add reply with empty field on News Single Page")
    public void loggedUserCanNotAddReplyWithEmptyField() {
        CommentClient commentClient = new CommentClient(ContentType.JSON, userData.accessToken);
        Response responseComment = commentClient.postComment(ecoNewsId, new CommentDto(0, "api comment"));
        parentCommentId = responseComment.as(CommentModel.class).id;
        Response responseCommentReply = commentClient.postComment(ecoNewsId, new CommentDto(parentCommentId, ""));
        BaseAssertion publishReply = new BaseAssertion(responseCommentReply);
        publishReply.statusCode(400);
    }

    @Test(testName = "GC-1171", description = "1171")
    @Description("Logged users can review all active replies comments on 'News' Page")
    public void loggedUserCanReviewAllActiveReplies() {
        CommentClient commentClient = new CommentClient(ContentType.JSON, userData.accessToken);
        Response responseComment = commentClient.postComment(ecoNewsId, new CommentDto(0, "api comment"));
        parentCommentId = responseComment.as(CommentModel.class).id;
        commentClient.postComment(ecoNewsId, new CommentDto(parentCommentId, "gc-1163_reply"));
        Response responseGetAllActiveReplies = commentClient.getAllActiveReplyToComment(parentCommentId.toString());
        BaseAssertion getAllActiveReplies = new BaseAssertion(responseGetAllActiveReplies);
        getAllActiveReplies.statusCode(200);
    }

    @Test(testName = "GC-1172", description = "1172")
    @Description("Unlogged users can review all active replies comments on 'News' Page")
    public void notLoggedUserCanReviewAllActiveReplies() {
        CommentClient commentClient = new CommentClient(ContentType.JSON, userData.accessToken);
        Response responseComment = commentClient.postComment(ecoNewsId, new CommentDto(0, "api comment"));
        parentCommentId = responseComment.as(CommentModel.class).id;
        commentClient.postComment(ecoNewsId, new CommentDto(parentCommentId, "gc-1163_reply"));
        CommentClient unloggedClient = new CommentClient(ContentType.JSON);
        Response responseGetAllActiveReplies = unloggedClient.getAllActiveReplyToComment(parentCommentId.toString());
        BaseAssertion getAllActiveReplies = new BaseAssertion(responseGetAllActiveReplies);
        getAllActiveReplies.statusCode(200);
    }

    @Test(testName = "GC-1174", description = "GC-1174")
    @Description("Verify that logged user can delete his own replay on the 'News' page")
    public void loggedUserCanDeleteHisOwnReply() {
        CommentClient commentClient = new CommentClient(ContentType.JSON, userData.accessToken);
        Response responseComment = commentClient.postComment(ecoNewsId, new CommentDto(0, "api comment"));
        parentCommentId = responseComment.as(CommentModel.class).id;
        Response responseReply = commentClient.postComment(ecoNewsId, new CommentDto(parentCommentId, "commentReply"));
        Integer replyId = responseReply.as(CommentModel.class).id;
        Response responseDeleteReply = commentClient.deleteComment(replyId.toString());
        BaseAssertion deleteComment = new BaseAssertion(responseDeleteReply);
        deleteComment.statusCode(200);
    }

    @Test(testName = "GC-1173", description = "GC-1173")
    @Description("Verify that logged user cannot reply to other replies on News Single Page")
    public void loggedUserCannotReplyToOtherReplies() {
        CommentClient loggedClient = new CommentClient(ContentType.JSON, userData.accessToken);
        Response responseComment = loggedClient.postComment(ecoNewsId, new CommentDto(0, "api comment"));
        parentCommentId = responseComment.as(CommentModel.class).id;
        Response responseReply = loggedClient.postComment(ecoNewsId, new CommentDto(parentCommentId, "commentReply"));
        int replyId = responseReply.as(CommentModel.class).id;
        Response responseReplyToReplies = loggedClient.postComment(ecoNewsId, new CommentDto(replyId, "reply to other replies"));
        BaseAssertion replyToReplies = new BaseAssertion(responseReplyToReplies);
        replyToReplies.statusCode(400)
                .bodyValueContains("message", "Can not make a reply to a reply");
    }

    @Test(testName = "GC-1175", description = "GC-1175")
    @Description("Verify that unlogged user cannot reply to other replies on News Single Page")
    public void unloggedUserCannotReplyToOtherReplies() {
        CommentClient loggedClient = new CommentClient(ContentType.JSON, userData.accessToken);
        Response responseComment = loggedClient.postComment(ecoNewsId, new CommentDto(0, "api comment"));
        parentCommentId = responseComment.as(CommentModel.class).id;
        Response responseReply = loggedClient.postComment(ecoNewsId, new CommentDto(parentCommentId, "commentReply"));
        int replyId = responseReply.as(CommentModel.class).id;
        CommentClient unloggedClient = new CommentClient(ContentType.JSON);
        Response responseReplyToReplies = unloggedClient.postCommentForUnloggedUser(ecoNewsId, new CommentDto(replyId, "reply to other replies"));
        BaseAssertion replyToReplies = new BaseAssertion(responseReplyToReplies);
        replyToReplies.statusCode(401)
                .bodyValueContains("message", "Authorize first");
    }

    @Test(testName = "GC-1180", description = "GC-1180")
    @Description("Verify that logged user can like/dislike the comment/reply on News Single Page")
    public void loggedUserCanLikeTheCommentOrReply() {
        logger.info("Verify that logged user can like/dislike the comment/reply on News Single Page");
        CommentClient commentClientTemporary = new CommentClient(ContentType.JSON, userData.accessToken);
        logger.info("create comment");
        Response responseComment = commentClientTemporary.postComment(ecoNewsId, new CommentDto(0, "api comment"));
        parentCommentId = responseComment.as(CommentModel.class).id;
        logger.info("create reply");
        Response responseReply = commentClientTemporary.postComment(ecoNewsId, new CommentDto(parentCommentId, "commentReply"));
        Integer replyId = responseReply.as(CommentModel.class).id;
        logger.info("authorization by another user");
        User existUser = UserRepository.get().exist();
        OwnSecurityClient authorizationExistClient = new OwnSecurityClient(ContentType.JSON);
        Response signIn = authorizationExistClient
                .signIn(new SignInDto(existUser.getEmail(), existUser.getPassword()));
        OwnSecurityModel existUserData = signIn.as(OwnSecurityModel.class);
        CommentClient existClient = new CommentClient(ContentType.JSON, existUserData.accessToken);
        logger.info("like the comment");
        Response responsePostLikeTheComment = existClient
                .postLikeTheCommentOrReply(parentCommentId.toString());
        BaseAssertion postLikeTheComment = new BaseAssertion(responsePostLikeTheComment);
        postLikeTheComment.statusCode(200);
        logger.info("like the reply");
        Response responsePostLikeTheReply = existClient
                .postLikeTheCommentOrReply(replyId.toString());
        BaseAssertion postLikeTheReply = new BaseAssertion(responsePostLikeTheReply);
        postLikeTheReply.statusCode(200);
    }

    @Test(testName = "GC-1181", description = "GC-1181")
    @Description("Verify that unlogged user cannot like/dislike the comment/reply on 'News' Page")
    public void notLoggedUserCannotLikeTheCommentOrReply() {
        CommentClient loggedClient = new CommentClient(ContentType.JSON, userData.accessToken);
        Response responseComment = loggedClient.postComment(ecoNewsId, new CommentDto(0, "api comment"));
        parentCommentId = responseComment.as(CommentModel.class).id;
        Response responseReply = loggedClient.postComment(ecoNewsId, new CommentDto(parentCommentId, "commentReply"));
        Integer replyId = responseReply.as(CommentModel.class).id;
        CommentClient unloggedClient = new CommentClient(ContentType.JSON);
        Response responsePostLikeTheComment = unloggedClient
                .postLikeTheCommentOrReplyForUnloggedUser(parentCommentId.toString());
        BaseAssertion postLikeTheComment = new BaseAssertion(responsePostLikeTheComment);
        postLikeTheComment.statusCode(401)
                .bodyValueContains("message", "Authorize first.");
        Response responsePostLikeTheReply = unloggedClient
                .postLikeTheCommentOrReplyForUnloggedUser(replyId.toString());
        BaseAssertion postLikeTheReply = new BaseAssertion(responsePostLikeTheReply);
        postLikeTheReply.statusCode(401)
                .bodyValueContains("message", "Authorize first.");
    }

    @Test(testName = "GC-1185", description = "GC-1185")
    @Description("Verify that after comment was published, system recalculates the total comments number on News Single Page")
    public void verifyThatSystemRecalculatesCommentsNumber() {
        CommentClient commentClient = new CommentClient(ContentType.JSON, userData.accessToken);
        Response countOfComments = commentClient.getCountComments(ecoNewsId);
        int commentsNumberBeforeCreation = Integer.parseInt(countOfComments.print());
        commentClient.postComment(ecoNewsId, new CommentDto(0, "comment1"));
        countOfComments = commentClient.getCountComments(ecoNewsId);
        int commentsNumberAfterCreation = Integer.parseInt(countOfComments.print());
        Assert.assertEquals(commentsNumberAfterCreation, commentsNumberBeforeCreation + 1);
    }

    @Test(testName = "GC-1188", description = "GC-1188")
    @Description("Verify that logged user cannot add comment with empty field on News Single Page")
    public void loggedUserCannotAddCommentWithEmptyField() {
        CommentClient commentClient = new CommentClient(ContentType.JSON, userData.accessToken);
        Response responseAddComment = commentClient.postComment(ecoNewsId, new CommentDto(0, ""));
        BaseAssertion addComment = new BaseAssertion(responseAddComment);
        addComment.statusCode(400);
    }

    @Test(testName = "GC-1189", description = "GC-1189")
    @Description("Verify that logged user cannot add comment with invalid number of characters on News Single Page ")
    public void loggedUserCannotAddCommentWithInvalidNumberOfCharacters() {
        CommentClient commentClient = new CommentClient(ContentType.JSON, userData.accessToken);
        Response responseAddComment = commentClient.postComment(ecoNewsId, new CommentDto(0, CONTENT_COMMENT_8001_CHARACTERS.getString()));
        BaseAssertion addComment = new BaseAssertion(responseAddComment);
        addComment.statusCode(400);
    }

    @Test(testName = "GC-1190", description = "GC-1190")
    @Description("Unlogged user can`t add comment on the 'News' page.")
    public void unloggedUserCanNotAddComment() {
        CommentClient unloggedClient = new CommentClient(ContentType.JSON);
        Response responseAddComment = unloggedClient.postCommentForUnloggedUser(ecoNewsId, new CommentDto(0, "GC-1190"));
        BaseAssertion addComment = new BaseAssertion(responseAddComment);
        addComment.statusCode(401)
                .bodyValueContains("message", "Authorize first.");
    }

    @Test(testName = "GC-1193", description = "GC-1193")
    @Description("Verify that not logged user can’t edit comments on the ‘Eco news’ page API")
    public void notLoggedUserCanNotEditComments() {
        CommentClient commentClientLogged = new CommentClient(ContentType.JSON, userData.accessToken);
        Response responseComment = commentClientLogged.postComment(ecoNewsId, new CommentDto(0, "api comment"));
        parentCommentId = responseComment.as(CommentModel.class).id;
        CommentClient commentClientNotLogged = new CommentClient(ContentType.JSON);
        Response responseTryToEditComment = commentClientNotLogged.updateCommentByNotLoggedUser(parentCommentId.toString(), "new%20comment");
        BaseAssertion notEditedComment = new BaseAssertion(responseTryToEditComment);
        notEditedComment.statusCode(401);
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

    @Test(testName = "GC-1195", description = "GC-1195")
    @Description("Verify that logged user can see the replies to the comment on the ‘Eco news’ page")
    public void loggedUserCanSeeReplyToComment() {
        CommentClient commentClient = new CommentClient(ContentType.JSON, userData.accessToken);
        Response responseComment = commentClient.postComment(ecoNewsId, new CommentDto(0, "check api reply"));
        parentCommentId = responseComment.as(CommentModel.class).id;
        Response responseReply = commentClient.getAllActiveReplyToComment(parentCommentId.toString());
        BaseAssertion seeReply = new BaseAssertion(responseReply);
        seeReply.statusCode(200);
    }

    @Test(testName = "GC-1196", description = "GC-1196")
    @Description("Verify that unlogged user can see the replies to the comment on the ‘Eco news’ page")
    public void unloggedUserCanSeeReplyToComment() {
        CommentClient commentClient = new CommentClient(ContentType.JSON, userData.accessToken);
        Response responseComment = commentClient.postComment(ecoNewsId, new CommentDto(0, "check api reply"));
        parentCommentId = responseComment.as(CommentModel.class).id;
        CommentClient commentClientNotLogged = new CommentClient(ContentType.JSON);
        Response responseReply = commentClientNotLogged.getAllActiveReplyToComment(parentCommentId.toString());
        BaseAssertion seeReply = new BaseAssertion(responseReply);
        seeReply.statusCode(200);
    }


    @Test(testName = "GC-1201", description = "GC-1201")
    @Description("Verify that unlogged user can`t edit replay on the ‘Eco news’ page.")
    public void notLoggedUserCantEditReply() {
        CommentClient commentClientLogged = new CommentClient(ContentType.JSON, userData.accessToken);
        Response responseComment = commentClientLogged.postComment(ecoNewsId, new CommentDto(0, "API comment"));
        parentCommentId = responseComment.as(CommentModel.class).id;
        Response responseReply = commentClientLogged.postComment(ecoNewsId, new CommentDto(parentCommentId, "Comment Reply"));
        Integer replyId = responseReply.as(CommentModel.class).id;
        CommentClient commentClientNotLogged = new CommentClient(ContentType.JSON);
        Response responseTryToEditReply = commentClientNotLogged.updateCommentByNotLoggedUser(replyId.toString(), "new%20reply%20api");
        BaseAssertion notEditedReply = new BaseAssertion(responseTryToEditReply);
        notEditedReply.statusCode(401);
    }

    @Test(testName = "GC-1200", description = "GC-1200")
    @Description("Verify that logged user can edit its own replay on the ‘Eco news’ page.")
    public void loggedUserCanEditHisOwnReply() {
        CommentClient commentClient = new CommentClient(ContentType.JSON, userData.accessToken);
        Response responseComment = commentClient.postComment(ecoNewsId, new CommentDto(0, "api comment"));
        parentCommentId = responseComment.as(CommentModel.class).id;
        Response responseReply = commentClient.postComment(ecoNewsId, new CommentDto(parentCommentId, "commentReply"));
        Integer replyId = responseReply.as(CommentModel.class).id;
        Response responseEditReply = commentClient.updateComment(replyId.toString(), "new%20reply%20api");
        BaseAssertion editReply = new BaseAssertion(responseEditReply);
        editReply.statusCode(200);

    }

    @Test(testName = "GC-1202", description = "GC-1202")
    @Description("Verify that logged user can`t edit not his own replay on the ‘Eco news’ page.")
    public void loggedUserCanNotEditNotHisOwnReply() {
        CommentClient commentClientLogged = new CommentClient(ContentType.JSON, userData.accessToken);
        Response responseComment = commentClientLogged.postComment(ecoNewsId, new CommentDto(0, "api comment"));
        parentCommentId = responseComment.as(CommentModel.class).id;
        Response responseReply = commentClientLogged.postComment(ecoNewsId, new CommentDto(parentCommentId, "Comment Reply"));
        Integer replyId = responseReply.as(CommentModel.class).id;
        OwnSecurityClient authorizationClient = new OwnSecurityClient(ContentType.JSON);
        User existUser = UserRepository.get().exist();
        Response signedIn = authorizationClient
                .signIn(new SignInDto(existUser.getEmail(), existUser.getPassword()));
        OwnSecurityModel existUserData = signedIn.as(OwnSecurityModel.class);
        CommentClient commentClientExist = new CommentClient(ContentType.JSON, existUserData.accessToken);
        Response responseEdit = commentClientExist.updateComment(replyId.toString(), "new%20reply%20api");
        BaseAssertion deleteComment = new BaseAssertion(responseEdit);
        deleteComment.statusCode(400);
    }
}