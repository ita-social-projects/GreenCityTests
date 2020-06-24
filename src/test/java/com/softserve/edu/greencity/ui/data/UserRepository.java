package com.softserve.edu.greencity.ui.data;

import com.softserve.edu.greencity.ui.tools.Randomizer;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public final class UserRepository {
    private static volatile UserRepository instance = null;

    private Properties property = new Properties();

    private UserRepository() {
        try {
            final FileInputStream fis = new FileInputStream("src/test/resources/credentials.properties");
            property.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static UserRepository get() {
        if (instance == null) {
            synchronized (UserRepository.class) {
                if (instance == null) {
                    instance = new UserRepository();
                }
            }
        }
        return instance;
    }

    public User getDefault() {
        return temporary();
    }

    public User temporary() {
        return new User(property.getProperty("temporaryLoginName"), property.getProperty("temporaryPass"));
    }

    public User defaultUserCredentials() {
        return new User(
                property.getProperty("defaultName"),
                property.getProperty("defaultLoginName"),
                property.getProperty("defaultPass"),
                property.getProperty("defaultConfirmPass"));
    }

    public User googleUserCredentials() {
        return new User(property.getProperty("googleLogin"), property.getProperty("googlePass"));
    }

    public User emptyUserCredentials() {
        return new User("", "", "", "");
    }

    public User invalidUserCredentials() {
        return new User(
                property.getProperty("invalidName"),
                property.getProperty("invalidLoginName"),
                property.getProperty("invalidPass"),
                property.getProperty("invalidConfirmPass"));
    }

    public User temporaryUserCredentialsForRegistration() {
        return new User(Randomizer.getRamdomString20Letters(), "", "A475asd123*", "A475asd123*");
    }
}
