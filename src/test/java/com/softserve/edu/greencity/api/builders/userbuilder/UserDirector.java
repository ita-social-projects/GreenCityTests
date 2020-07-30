package com.softserve.edu.greencity.api.builders.userbuilder;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class UserDirector {
    Properties properties = new Properties();

    public UserDirector() {
        try {
            final FileInputStream fis = new FileInputStream("src/test/resources/credentials.properties");
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void constructValidUser(final UserBuilder userBuilder) {
        userBuilder.setEmail(properties.getProperty("temporaryEmail"));
        userBuilder.setPassword(properties.getProperty("temporaryPass"));
    }

    public void constructUserWithEmptyCreds(final UserBuilder userBuilder) {
        userBuilder.setEmail("");
        userBuilder.setPassword("");
    }

    public void constructUserWithEmptyEmail(final UserBuilder userBuilder) {
        userBuilder.setEmail("");
        userBuilder.setPassword(properties.getProperty("temporaryPass"));
    }

    public void constructUserWithEmptyPassword(final UserBuilder userBuilder) {
        userBuilder.setEmail(properties.getProperty("temporaryEmail"));
        userBuilder.setPassword("");
    }

    public void constructUserWithIncorrectPassword(final UserBuilder userBuilder) {
        userBuilder.setEmail(properties.getProperty("temporaryEmail"));
        userBuilder.setPassword(properties.getProperty("validUnregisterPassword"));
    }

    public void constructUserWithIncorrectEmail(final UserBuilder userBuilder) {
        userBuilder.setEmail(properties.getProperty("incorrectFormatEmail"));
        userBuilder.setPassword(properties.getProperty("temporaryPass"));
    }

    public void constructUserWithUnregisteredEmail(final UserBuilder userBuilder) {
        userBuilder.setEmail(properties.getProperty("validUnregisterEmail"));
        userBuilder.setPassword(properties.getProperty("temporaryPass"));
    }

    public void constructDefaultSignUpUser(final UserBuilder userBuilder) {
        userBuilder.setEmail("user@ukr.net");
        userBuilder.setName("Liuba");
        userBuilder.setPassword("!Error911");
    }
    public void constructEmptySignUpUser(final UserBuilder userBuilder) {
        userBuilder.setEmail("");
        userBuilder.setName("");
        userBuilder.setPassword("");
    }


}
