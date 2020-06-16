package com.softserve.edu.greencity.ui.data;

import com.softserve.edu.greencity.ui.tools.Randomizer;

public final class UserRepository {
    private static volatile UserRepository instance = null;

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

    public User temporary() {
        return new User("tarasmalynovskyy@gmail.com", "!Qwerty123");
    }
    
    /**
     * Default user credentials:
     * firstName "John";
     * lastName "Wilson";
     * email "rjjztqsiayuieydfuy@awdrt.org";
     * password "A475asd123*".
     * @return UserData
     */
    public User defaultUserCredentials() {
        return new User("John", "Wilson", "rjjztqsiayuieydfuy@awdrt.org", "A475asd123*");
    }
    
    /**
     * GoogleUserCredentials:
     * email "sergtaqc@gmail.com";
     * password "123456".
     * @return UserData
     */
    public User googleUserCredentials() {
        return new User("sergii.taqc@gmail.com", "A475asd123*");
    }
    
    /**
     * WrongUserCredentials1:
     * firstName "Asdfqwe";
     * lastName "Qwerzxc";
     * email "123asd@zxc";
     * password "123Adff890*".
     * @return UserData
     */
    public User wrongUserCredentials1() {
        return new User("", "", "", "", "");
    }
    
    /**
     * WrongUserCredentials2:
     * firstName "A.";
     * lastName "22222222222222";
     * email "asdsd.1312";
     * password "".
     * @return UserData
     */
    public User wrongUserCredentials2() {
            return new User("Wrong User", "22222222222222", "123asdasd#zxcz.asd", "!A*zxc- ", "!A*zxc-");
    }
    
    /**
     * Gives random credentials:
     * first name - random 20 letters;
     * last name - random 20 letters;
     * 
     * @return
     */
    public User temporaryUserCredentialsForRegistration() {
        return new User(Randomizer.getRamdomString20Letters(), Randomizer.getRamdomString20Letters(), "", "A475asd123*", "A475asd123*");
    }

}