package com.softserve.edu.greencity.api.tests.ownsecurity;

import com.softserve.edu.greencity.api.assertions.ArrayAssertion;
import com.softserve.edu.greencity.api.builders.ownsecurity.ChangePasswordBuilder;
import com.softserve.edu.greencity.api.builders.ownsecurity.SignInBuilder;
import com.softserve.edu.greencity.api.builders.ownsecurity.SignUpBuilder;
import com.softserve.edu.greencity.api.builders.ownsecurity.UpdatePasswordBuilder;
import com.softserve.edu.greencity.api.clients.EcoNewsClient;
import com.softserve.edu.greencity.api.clients.OwnSecurityClient;
import com.softserve.edu.greencity.api.models.ownsecurity.*;
import com.softserve.edu.greencity.api.tests.GreenCityAPITestRunner;
import com.softserve.edu.greencity.data.users.User;
import com.softserve.edu.greencity.data.users.UserRepository;
import com.softserve.edu.greencity.ui.api.mail.GoogleMailAPI;
import com.softserve.edu.greencity.ui.tools.jdbc.services.EcoNewsService;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.testng.annotations.BeforeClass;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.softserve.edu.greencity.ui.tests.signin.SignInTexts.FORGOT_PASS_MAIL_SUBJECT;

public class OwnSecurityTestRunner extends GreenCityAPITestRunner {

        static protected OwnSecurityClient ownSecurityClient;
        static public Response signInResponse;


    static public ArrayAssertion getSignInAssertion(String email, String password) {
        SignInDto credentials = SignInBuilder.signInWith()
                .email(email)
                .password(password)
                .build();
        OwnSecurityClient ownSecurityClient = new OwnSecurityClient("JSON");
        Response response = ownSecurityClient.signIn(credentials);
        signInResponse = response;
        response.print();
        return new ArrayAssertion(response)
                .contentType(ContentType.JSON);
    }

    public static ArrayAssertion getSignUpAssertion(String email, String name, String password) {
        SignUpDto credentials = SignUpBuilder.signUpWith()
                .email(email)
                .name(name)
                .password(password)
                .build();
        OwnSecurityClient ownSecurityClient = new OwnSecurityClient("JSON");
        Response response = ownSecurityClient.signUp(credentials);
        response.print();
        return new ArrayAssertion(response)
                .contentType(ContentType.JSON);
    }

    static public ArrayAssertion getVerifyEmailAssertion(String email, String password) {
       String url = new GoogleMailAPI().getconfirmURL(email,password,20);
       List<NameValuePair> params = null;
       try {
           params = URLEncodedUtils.parse(new URI(url), Charset.forName("UTF-8"));
       } catch (URISyntaxException e) {
           e.printStackTrace();
       }

       Map<String, String> map = new HashMap<String, String>();
       for (NameValuePair param : params) {
           map.put(param.getName(),param.getValue());
       }
       System.out.println(map.get("token"));
       System.out.println(map.get("user_id"));
       OwnSecurityClient ownSecurityClient = new OwnSecurityClient("JSON");
       Response response = ownSecurityClient.verifyEmail(map.get("token"), map.get("user_id"));
       response.print();
       return new ArrayAssertion(response);
    }

    static public ArrayAssertion getUpdatePasswordAssertion(String confirmPassword, String currentPassword, String password) {
        UpdatePasswordDto credentials = UpdatePasswordBuilder.updatePasswordWith()
                .confirmPassword(confirmPassword)
                .currentPassword(currentPassword)
                .password(password)
                .build();
        Response response = ownSecurityClient.updatePassword(credentials);
        response.print();
        return new ArrayAssertion(response)
                .contentType(ContentType.JSON);
    }

    static public ArrayAssertion getChangePasswordAssertion(String email, String oldPassword, String confirmNewPassword, String newPassword) {
        String url = new GoogleMailAPI().getconfirmURL(FORGOT_PASS_MAIL_SUBJECT.getText(), email,oldPassword, "https://ita-social-projects.github.io/GreenCityClient//#/auth/restore[^\"]+", 20);
        List<NameValuePair> params = null;
        try {
            params = URLEncodedUtils.parse(new URI(url), Charset.forName("UTF-8"));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        Map<String, String> map = new HashMap<String, String>();
        for (NameValuePair param : params) {
            map.put(param.getName(),param.getValue());
        }
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

    static public ArrayAssertion getRestorePasswordAssertion(String email) {
        OwnSecurityClient ownSecurityClient = new OwnSecurityClient("JSON");
        Response response = ownSecurityClient.restorePassword(email);
        response.print();
        return new ArrayAssertion(response);
    }

    static public void authorization(Response response) {
        OwnSecurityModel userData = response.as(OwnSecurityModel.class);
        ownSecurityClient = new OwnSecurityClient(ContentType.JSON, userData.accessToken);
    }
}
