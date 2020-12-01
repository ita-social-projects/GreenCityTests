package com.softserve.edu.greencity.api.tests.forgotpassword;

import com.softserve.edu.greencity.api.assertions.ArrayAssertion;
import com.softserve.edu.greencity.api.assertions.BaseAssertion;
import com.softserve.edu.greencity.api.builders.ownsecurity.SignUpBuilder;
import com.softserve.edu.greencity.api.builders.ownsecurity.UpdatePasswordBuilder;
import com.softserve.edu.greencity.api.clients.OwnSecurityClient;
import com.softserve.edu.greencity.api.models.ownsecurity.SignUpDto;
import com.softserve.edu.greencity.api.models.ownsecurity.UpdatePasswordDto;
import com.softserve.edu.greencity.data.users.UserRepository;
import com.softserve.edu.greencity.ui.api.mail.GoogleMailAPI;
import io.qameta.allure.Description;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.softserve.edu.greencity.ui.tests.signin.SignInTexts.FORGOT_PASS_MAIL_SUBJECT;

public class ForgotPasswordApiTest {
    @DataProvider(name = "invalidEmail")
    public Object[][] invalidEmail() { //GC-547
        return new Object[][]{
                {"123@", "must be a well-formed email address"},
                {"InvalidEmailgmail.com", "must be a well-formed email address"},
        };
    }

    @DataProvider(name = "emptyEmail")
    public Object[][] emptyEmail() { //GC-546
        return new Object[][]{
                {"", "The user does not exist by this email:"},
        };
    }

    @DataProvider(name = "unregisteredEmail")
    public Object[][] unregisteredEmail() { //GC-545
        return new Object[][]{
                {       UserRepository.get().defaultUserCredentials().getEmail(),
                        UserRepository.get().defaultUserCredentials().getPassword(),
                        "Test123456_",
                        UserRepository.get().defaultUserCredentials().getConfirmPassword(),
                },
        };
    }

    private ArrayAssertion getUpdatePasswordAssertion(String confirmPassword, String currentPassword, String password) {
        UpdatePasswordDto credentials = UpdatePasswordBuilder.updatePasswordWith()
                .confirmPassword(confirmPassword)
                .currentPassword(currentPassword)
                .password(password)
                .build();
        OwnSecurityClient ownSecurityClient = new OwnSecurityClient("JSON");
        Response response = ownSecurityClient.updatePassword(credentials);
        response.print();
        return new ArrayAssertion(response)
                .contentType(ContentType.JSON);
    }

    private ArrayAssertion getRestorePasswordAssertion(String email) {
        OwnSecurityClient ownSecurityClient = new OwnSecurityClient("JSON");
        Response response = ownSecurityClient.restorePassword(email);
        response.print();
        return new ArrayAssertion(response)
                .contentType(ContentType.JSON);
    }

    @Test(dataProvider = "invalidEmail", testName = "GC-547", description = "GC-547")
    @Description("Verify that user is registered, after he enters valid values ")
    public void restorePasswordWithInvalidEmail(String email, String message) {
        ArrayAssertion assertion = getRestorePasswordAssertion(email);
        assertion.statusCode(400)
                .bodyValueContains("message", message);
    }

    @Test(dataProvider = "emptyEmail", testName = "GC-546", description = "GC-546")
    @Description("Verify that user is registered, after he enters valid values ")
    public void restorePasswordWithEmptyEmailField(String email, String message) {
        ArrayAssertion assertion = getRestorePasswordAssertion(email);
        assertion.statusCode(404)
                .bodyValueContains("message", message);
    }

    @Test(dataProvider = "unregisteredEmail", testName = "GC-545", description = "GC-545")
    @Description("Verify that user is registered, after he enters valid values ")
    public void restorePasswordWithUnregisteredEmail(String email, String password, String message) {
        new GoogleMailAPI().clearMail(email, password);
        ArrayAssertion assertion = getRestorePasswordAssertion(email);
        assertion.statusCode(400)
                .bodyValueContains("message", message);

        new GoogleMailAPI().waitForMassagesWithSubject(FORGOT_PASS_MAIL_SUBJECT.getText(),
                true, 3, 10, email, password);
        int numberOfEmail = new GoogleMailAPI().getNumberMailsBySubject(email, password,
                FORGOT_PASS_MAIL_SUBJECT.getText(), 50);
        Assert.assertEquals(numberOfEmail, 0);
    }

    @Test(dataProvider = "unregisteredEmail", testName = "GC-548", description = "GC-548")
    @Description("Verify that user can Sign in with new password after restoring password via email")
    public void signInAfterRestoringPassword(String email, String oldPassword, String newPassword, String confirmPassword) {
        new GoogleMailAPI().clearMail(email, oldPassword);
        ArrayAssertion assertion = getRestorePasswordAssertion(email);
        assertion.statusCode(200)


        assertion = getUpdatePasswordAssertion(confirmPassword, newPassword, oldPassword);
        assertion.statusCode(200);


    }
}
