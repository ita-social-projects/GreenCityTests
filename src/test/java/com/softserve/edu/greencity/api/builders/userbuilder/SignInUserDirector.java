package com.softserve.edu.greencity.api.builders.userbuilder;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class SignInUserDirector {
    Properties properties = new Properties();

    public SignInUserDirector() {
        try {
            final FileInputStream fis = new FileInputStream("src/test/resources/credentials.properties");
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void constructSignInUserWithEmptyCreds(final SignInUserBuilder signInUserBuilder) {
        signInUserBuilder.setEmail("");
        signInUserBuilder.setPassword("");
    }

    public void constructSignInUserWithEmptyEmail(final SignInUserBuilder signInUserBuilder) {
        signInUserBuilder.setEmail("");
        signInUserBuilder.setPassword(properties.getProperty("temporaryPass"));
    }

    public void constructSignInUserWithEmptyPassword(final SignInUserBuilder signInUserBuilder) {
        signInUserBuilder.setEmail(properties.getProperty("temporaryEmail"));
        signInUserBuilder.setPassword("");
    }

    public void constructSignInUserWithIncorrectPassword(final SignInUserBuilder signInUserBuilder) {
        signInUserBuilder.setEmail(properties.getProperty("temporaryEmail"));
        signInUserBuilder.setPassword(properties.getProperty("validUnregisterPassword"));
    }

    public void constructSignInUserWithIncorrectEmail(final SignInUserBuilder signInUserBuilder) {
        signInUserBuilder.setEmail(properties.getProperty("incorrectFormatEmail"));
        signInUserBuilder.setPassword(properties.getProperty("temporaryPass"));
    }

    public void constructSignInUserValid(final SignInUserBuilder signInUserBuilder) {
        signInUserBuilder.setEmail(properties.getProperty("temporaryEmail"));
        signInUserBuilder.setPassword(properties.getProperty("temporaryPass"));
    }

    public void constructSignInUserWithUnregisteredCreds(final SignInUserBuilder signInUserBuilder) {
        signInUserBuilder.setEmail(properties.getProperty("validUnregisterEmail"));
        signInUserBuilder.setPassword(properties.getProperty("validUnregisterPassword"));
    }

}
