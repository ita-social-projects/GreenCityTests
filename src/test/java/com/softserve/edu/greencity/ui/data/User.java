package com.softserve.edu.greencity.ui.data;

/**
 * User class.
 */
public class User {
    //
    private String email;
    private String userName;
    private String password;
    private String confirmPassword;
    
    /**
     * Constructor.
     * @param email
     * @param password
     */
    public User(String email, String password) {
        this.email = email;
        this.userName = "";
        this.password = password;
        this.confirmPassword = "";
    }

    /**
     * Constructor.
     * @param email
     * @param userName
     * @param password
     */
    public User(String email, String userName, String password) {
        this.email = email;
        this.userName = userName;
        this.password = password;
        this.confirmPassword = "";
    }
    
    /**
     * Constructor.
     * @param userName
     * @param email
     * @param password
     * @param confirmPassword
     */
    public User(String userName, String email,
                String password, String confirmPassword) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    // setters

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUserName(String firstname) {
        this.userName = firstname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
    
    // getters

    public String getEmail() {
        return email;
    }

    public String getUserName() {
        return userName;
    }


    public String getPassword() {
        return password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    @Override
    public String toString() {
        return "User [email=" + email + ", firstName=" + userName
                + ", password=" + password + ", confirmPassword=" + confirmPassword + "]";
    }

}
