package com.softserve.edu.greencity.api.tests.forgotpassword;

import com.softserve.edu.greencity.api.assertions.ArrayAssertion;
import com.softserve.edu.greencity.api.assertions.BaseAssertion;
import com.softserve.edu.greencity.api.builders.ownsecurity.ChangePasswordBuilder;
import com.softserve.edu.greencity.api.builders.ownsecurity.SignInBuilder;
import com.softserve.edu.greencity.api.builders.ownsecurity.SignUpBuilder;
import com.softserve.edu.greencity.api.builders.ownsecurity.UpdatePasswordBuilder;
import com.softserve.edu.greencity.api.clients.EcoNewsClient;
import com.softserve.edu.greencity.api.clients.OwnSecurityClient;
import com.softserve.edu.greencity.api.models.ownsecurity.*;
import com.softserve.edu.greencity.data.users.UserRepository;
import com.softserve.edu.greencity.ui.api.mail.GoogleMailAPI;
import com.softserve.edu.greencity.ui.tools.jdbc.services.EcoNewsService;
import io.qameta.allure.Description;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.softserve.edu.greencity.ui.tests.signin.SignInTexts.FORGOT_PASS_MAIL_SUBJECT;



public class ForgotPasswordApiTest {

    protected EcoNewsClient ecoNewsClient;
    protected EcoNewsService ecoNewsService;


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

    private ArrayAssertion getChangePasswordAssertion(String email, String oldPassword, String confirmNewPassword, String newPassword) {
        String url = new GoogleMailAPI().getconfirmURL(FORGOT_PASS_MAIL_SUBJECT.getText(), email,oldPassword, "https://ita-social-projects.github.io/GreenCityClient//#/auth/restore[^\"]+", 20);
        System.out.println(url);
        List<NameValuePair> params = null;
        try {
            params = URLEncodedUtils.parse(new URI(url), Charset.forName("UTF-8"));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        System.out.println(params);

        Map<String, String> map = new HashMap<String, String>();
        for (org.apache.http.NameValuePair param : params) {
            map.put(param.getName(),param.getValue());
        }
        System.out.println(map.get("token"));
        ChangePasswordDto credentials = ChangePasswordBuilder.changePasswordWith()
                .confirmPassword(confirmNewPassword)
                .password(newPassword)
                .token(map.get("token"))
                .build();
        OwnSecurityClient ownSecurityClient = new OwnSecurityClient("JSON");
        Response response = ownSecurityClient.changePassword(credentials);
        response.print();
        return new ArrayAssertion(response);
    }

    private ArrayAssertion getSignInAssertion(String email, String password) {
        SignInDto credentials = SignInBuilder.signInWith()
                .email(email)
                .password(password)
                .build();
        OwnSecurityClient ownSecurityClient = new OwnSecurityClient("JSON");
        Response response = ownSecurityClient.signIn(credentials);
        response.print();
        OwnSecurityModel userData = response.as(OwnSecurityModel.class);
        ecoNewsClient = new EcoNewsClient(ContentType.JSON, userData.accessToken);
        ecoNewsService = new EcoNewsService();
        return new ArrayAssertion(response)
                .contentType(ContentType.JSON);
    }

    private ArrayAssertion getRestorePasswordAssertion(String email) {
        OwnSecurityClient ownSecurityClient = new OwnSecurityClient("JSON");
        Response response = ownSecurityClient.restorePassword(email);
        response.print();
        return new ArrayAssertion(response);
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
        assertion.statusCode(404) //400 in Jira
                .bodyValueContains("message", message);
    }

    @Test(dataProvider = "unregisteredEmail", testName = "GC-545", description = "GC-545")
    @Description("Verify that user is registered, after he enters valid values ")
    public void restorePasswordWithUnregisteredEmail(String email, String password, String message) {
        new GoogleMailAPI().clearMail(email, password);
        ArrayAssertion assertion = getRestorePasswordAssertion(email);
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
        ArrayAssertion assertion = getRestorePasswordAssertion(email);
        assertion.statusCode(200);

        assertion = getChangePasswordAssertion(email, oldPassword, confirmNewPassword, newPassword);
        assertion.statusCode(200);

        assertion = getSignInAssertion(email, newPassword);
        System.out.println(newPassword);
        assertion.statusCode(200);

        //put old password back after test
        assertion = getUpdatePasswordAssertion(confirmPassword, newPassword, oldPassword);
        assertion.statusCode(200);


    }
}
