package com.softserve.edu.greencity.api.tests.ownsecurity.forgotpassword;

import com.softserve.edu.greencity.api.assertions.ArrayAssertion;
import com.softserve.edu.greencity.api.tests.ownsecurity.OwnSecurityTestRunner;
import com.softserve.edu.greencity.data.users.UserRepository;
import com.softserve.edu.greencity.ui.api.mail.GoogleMailAPI;
import io.qameta.allure.Description;
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
                {       UserRepository.get().unregisterUser().getEmail(),
                        UserRepository.get().unregisterUser().getPassword(),
                        "The user does not exist by this email",
                },
        };
    }

    @DataProvider(name = "registeredEmail")
    public Object[][] registeredEmail() { //GC-548
        return new Object[][]{
                {       UserRepository.get().defaultUserCredentials().getEmail(),
                        UserRepository.get().defaultUserCredentials().getPassword(),
                        "Test123456_",
                        UserRepository.get().defaultUserCredentials().getPassword(),
                        "Test123456_",
                },
        };
    }

    @Test(dataProvider = "invalidEmail", testName = "GC-547", description = "GC-547")
    @Description("Verify that user is registered, after he enters valid values ")
    public void restorePasswordWithInvalidEmail(String email, String message) {
        ArrayAssertion assertion = OwnSecurityTestRunner.getRestorePasswordAssertion(email);
        assertion.statusCode(400)
                .bodyValueContains("message", message);
    }

    @Test(dataProvider = "emptyEmail", testName = "GC-546", description = "GC-546")
    @Description("Verify that user is registered, after he enters valid values ")
    public void restorePasswordWithEmptyEmailField(String email, String message) {
        ArrayAssertion assertion = OwnSecurityTestRunner.getRestorePasswordAssertion(email);
        assertion.statusCode(404) //400 in Jira
                .bodyValueContains("message", message);
    }

    @Test(dataProvider = "unregisteredEmail", testName = "GC-545", description = "GC-545")
    @Description("Verify that user is registered, after he enters valid values ")
    public void restorePasswordWithUnregisteredEmail(String email, String password, String message) {
        new GoogleMailAPI().clearMail(email, password);
        ArrayAssertion assertion = OwnSecurityTestRunner.getRestorePasswordAssertion(email);
        assertion.statusCode(404) //400 in Jira
                .bodyValueContains("message", message);

        new GoogleMailAPI().waitForMassagesWithSubject(FORGOT_PASS_MAIL_SUBJECT.getText(),
                true, 3, 10, email, password);
        int numberOfEmail = new GoogleMailAPI().getNumberMailsBySubject(email, password,
                FORGOT_PASS_MAIL_SUBJECT.getText(), 50);
        Assert.assertEquals(numberOfEmail, 0);
    }

    @Test(dataProvider = "registeredEmail", testName = "GC-548", description = "GC-548")
    @Description("Verify that user can Sign in with new password after restoring password via email")
    public void signInAfterRestoringPassword(String email, String oldPassword, String newPassword, String confirmPassword, String confirmNewPassword) {
        new GoogleMailAPI().clearMail(email, oldPassword);
        ArrayAssertion assertion = OwnSecurityTestRunner.getRestorePasswordAssertion(email);
        assertion.statusCode(200);

        assertion = OwnSecurityTestRunner.getChangePasswordAssertion(email, oldPassword, confirmNewPassword, newPassword);
        assertion.statusCode(200);

        assertion = OwnSecurityTestRunner.getSignInAssertion(email, newPassword);
        assertion.statusCode(200);
        OwnSecurityTestRunner.authorization(OwnSecurityTestRunner.signInResponse);

        //put old password back after test
        assertion = OwnSecurityTestRunner.getUpdatePasswordAssertion(confirmPassword, newPassword, oldPassword);
        assertion.statusCode(200);


    }
}
