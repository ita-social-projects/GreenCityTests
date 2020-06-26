package com.softserve.edu.greencity.ui.data;

import com.softserve.edu.greencity.ui.tools.Randomizer;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public final class UserRepository {
    private static volatile UserRepository instance = null;

    FileInputStream fis;
    Properties property = new Properties();

    {
        try {
            fis = new FileInputStream("src/test/resources/credentials.properties");
            property.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private UserRepository() {
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

    //    TODO Don`t push credentials
    public User temporary() {
        return new User(property.getProperty("temporaryLoginName"), property.getProperty("temporaryPass"));
    }

    /**
     * Default user credentials:
     * firstName "John";
     * lastName "Wilson";
     * email "rjjztqsiayuieydfuy@awdrt.org";
     * password "A475asd123*".
     *
     * @return UserData
     */


    public User defaultUserCredentials() {
        return new User(
                property.getProperty("defaultName"),
                property.getProperty("defaultLoginName"),
                property.getProperty("defaultPass"),
                property.getProperty("defaultConfirmPass"));
    }

    /**
     * GoogleUserCredentials:
     * email "sergtaqc@gmail.com";
     * password "123456".
     *
     * @return UserData
     */
    public User googleUserCredentials() {
        return new User(property.getProperty("googleLogin"), property.getProperty("googlePass"));
    }

    /**
     * WrongUserCredentials1:
     * firstName "Asdfqwe";
     * lastName "Qwerzxc";
     * email "123asd@zxc";
     * password "123Adff890*".
     *
     * @return UserData
     */
    public User emptyUserCredentials() {
        return new User("", "", "", "");
    }

    /**
     * WrongUserCredentials2:
     * firstName "A.";
     * lastName "22222222222222";
     * email "asdsd.1312";
     * password "".
     *
     * @return UserData
     */
    public User wrongUserCredentials() {
        return new User("Wrong User", "123asdasd#zxcz.asd", "!A*zxc- ", "!A*zxc-");
    }

    /**
     * Gives random credentials:
     * first name - random 20 letters;
     * last name - random 20 letters;
     *
     * @return
     */
    public User temporaryUserCredentialsForRegistration() {
        return new User(Randomizer.getRamdomString20Letters(), "GCSignUpUser@gmail.com", "A475asd123*", "A475asd123*");
    }

}