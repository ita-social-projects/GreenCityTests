package com.softserve.edu.greencity.api.tests.signup;

import com.softserve.edu.greencity.api.assertions.ArrayAssertion;
import com.softserve.edu.greencity.api.builders.ownsecurity.SignInBuilder;
import com.softserve.edu.greencity.api.builders.ownsecurity.SignUpBuilder;
import com.softserve.edu.greencity.api.clients.OwnSecurityClient;
import com.softserve.edu.greencity.api.models.ownsecurity.SignInDto;
import com.softserve.edu.greencity.api.models.ownsecurity.SignUpDto;
import com.softserve.edu.greencity.data.users.UserRepository;
import com.softserve.edu.greencity.ui.api.mail.GoogleMailAPI;
import io.qameta.allure.Description;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SignUpApiTest {
    @DataProvider(name = "emptyCredentials")
    public Object[][] blankCredentials() { //GC-532, 534, 535
        return new Object[][]{
                {"", "", "", "must not be blank"},
                {"", "DefaultName", "Test1234_", "must not be blank"},
                {"TestEmail@gmail.com", "DefaultName", "", "must not be blank"},

        };
    }

    @DataProvider(name = "invalidCredentials")
    public Object[][] invalidCredentials() { //GC-536, 537, 540, 541
        return new Object[][]{
                {"InvalidEmail", "DefaultName", "Test1234_", "The email is invalid"},
                {"TestEmail@gmail.com", "DefaultName", "Te1_", "Password has contain at least one character of Uppercase letter (A-Z)," +
                        " Lowercase letter (a-z), Digit (0-9)," +
                        " Special character (~`!@#$%^&*()+=_-{}[]|:;\\\\�\\\\�?/<>,.)"},
                {"TestEmail@gmail.com", "InvalidVeryLongNameForSignUpTest", "Test1234_", "length must be between 6 and 30"},
                {"TestEmail@gmail.com", "DefaultName", "InvalidPassword", "Password has contain at least one character of Uppercase letter (A-Z)," +
                        " Lowercase letter (a-z), Digit (0-9)," +
                        " Special character (~`!@#$%^&*()+=_-{}[]|:;\\\\�\\\\�?/<>,.)"},
        };
    }

    @DataProvider(name = "existingEmail")
    public Object[][] existingEmail() { //GC-528
        return new Object[][]{
                {"lelekatestacc@gmail.com", "DefaultName", "Test1234_", "User with this email is already registered"},
        };
    }

    @DataProvider(name = "validCredentials")
    public Object[][] validCredentials() { //GC-533
        return new Object[][]{
                {
                        UserRepository.get().userCredentialsForRegistration().getEmail(),
                        UserRepository.get().userCredentialsForRegistration().getUserName(),
                        UserRepository.get().userCredentialsForRegistration().getPassword(),
                        "You should verify the email first, check your email box!"},
        };
    }

    @DataProvider(name = "SuccessfulRegistration")
    public Object[][] successfulRegistration() { //
        return new Object[][]{
                {
                        UserRepository.get().userCredentialsForRegistration().getEmail(),
                        UserRepository.get().userCredentialsForRegistration().getUserName(),
                        UserRepository.get().userCredentialsForRegistration().getPassword(),
                },
        };
    }

    private ArrayAssertion getCommonCredentialsAssertion(String email, String name, String password) {
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

   private ArrayAssertion getVerifyEmailAssertion(String email, String password) {
       String url = new GoogleMailAPI().getconfirmURL(email,password,20);
       List<NameValuePair> params = null;
       try {
           params = URLEncodedUtils.parse(new URI(url), Charset.forName("UTF-8"));
       } catch (URISyntaxException e) {
           e.printStackTrace();
       }

       Map<String, String> map = new HashMap<String, String>();
       for (org.apache.http.NameValuePair param : params) {
           map.put(param.getName(),param.getValue());
       }
       System.out.println(map.get("token"));
       System.out.println(map.get("user_id"));
       OwnSecurityClient ownSecurityClient = new OwnSecurityClient("JSON");
       Response response = ownSecurityClient.verifyEmail(map.get("token"), map.get("user_id"));
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
        return new ArrayAssertion(response)
                .contentType(ContentType.JSON);
    }

    @Test(dataProvider = "emptyCredentials", testName = "GC-532, GC-534, GC-535", description = "GC-532, GC-534, GC-535")
    @Description("Verify that user is not registered, when values in request are empty")
    public void blankCredentialsTest(String email, String name, String password, String message) {
        ArrayAssertion assertion = getCommonCredentialsAssertion(email, name, password);
        assertion.statusCode(400)
                .bodyArrayContains("message", message);
    }

    @Test(dataProvider = "invalidCredentials", testName = "GC-536, GC-537, GC-540, GC-541", description = "GC-536, GC-537, GC-540, GC-541")
    @Description("Verify that user is not registered, when credentials are invalid")
    public void invalidCredentialsTest(String email, String name, String password, String message) {
        ArrayAssertion assertion = getCommonCredentialsAssertion(email, name, password);
        assertion.statusCode(400)
                .bodyArrayContains("message", message);
    }

    @Test(dataProvider = "existingEmail", testName = "GC-528", description = "GC-528")
    @Description("Verify that the user cannot register with the already registered email address")
    public void existingEmailTest(String email, String name, String password, String message) {
        ArrayAssertion assertion = getCommonCredentialsAssertion(email, name, password);
        assertion.statusCode(400)
                .bodyArrayContains("message", message);
    }

    @Test(dataProvider = "validCredentials", testName = "GC-533", description = "GC-533")
    @Description("Verify that user can not Sign in if he didn’t verify email address in email box ")
    public void signInWithoutVerifyingEmailTest(String email, String name, String password, String message) {
        ArrayAssertion assertion = getCommonCredentialsAssertion(email, name, password);
        assertion.statusCode(201);
        assertion = getSignInAssertion(email, password);
        assertion.statusCode(403)
                .bodyValueContains("message", message);
    }

   /*   @Test(dataProvider = "SuccessfulRegistration", testName = "GC-531", description = "GC-531")
    @Description("Verify that user is registered, after he enters valid values ")
    public void signUpWithValidCredentialsTest(String email, String name,  String password) {
        ArrayAssertion assertion = getCommonCredentialsAssertion(email, name, password);
        assertion.statusCode(201);
       String url = new GoogleMailAPI().getconfirmURL(email,password,20);
        List<NameValuePair> params = null;
        try {
            params = URLEncodedUtils.parse(new URI(url), Charset.forName("UTF-8"));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        for (org.apache.http.NameValuePair param : params) {
            System.out.println(param.getName() + " : " + param.getValue());
        }
        Map<String, String> mappedMovies = new HashMap<String, String>();
        for (org.apache.http.NameValuePair param : params) {
            mappedMovies.put(param.getName(),param.getValue());
        }
        System.out.println(mappedMovies.get("token"));
    } */

    @Test(dataProvider = "SuccessfulRegistration", testName = "GC-531", description = "GC-531")
    @Description("Verify that user is registered, after he enters valid values ")
    public void signUpWithValidCredentialsTest(String email, String name, String password) {
        new GoogleMailAPI().clearMail(email, password);
        ArrayAssertion assertion = getCommonCredentialsAssertion(email, name, password);
        assertion.statusCode(201);

        assertion = getVerifyEmailAssertion(email, password);
        assertion.statusCode(200);
    }
      /*  URL url = null;
        try {
            url = new URL(new GoogleMailAPI().getconfirmURL(email, password, 20));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        String tokenAndUserId = url.getQuery();
        assertion = getVerifyEmailAssertion(tokenAndUserId);
        assertion.statusCode(200); */


    }

