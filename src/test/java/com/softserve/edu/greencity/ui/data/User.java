package com.softserve.edu.greencity.ui.data;

/**
 * User class.
 */
public class User {
    //
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private String confirmPassword;
    
    /**
     * Constructor.
     * @param email
     * @param password
     */
    public User(String email, String password) {
        this.email = email;
        this.firstName = "";
        this.lastName = "";
        this.password = password;
        this.confirmPassword = "";
    }

    /**
     * Constructor.
     * @param email
     * @param firstname
     * @param password
     */
    public User(String email, String firstname, String password) {
        this.email = email;
        this.firstName = firstname;
        this.lastName = "";
        this.password = password;
        this.confirmPassword = "";
    }
    
    /**
     * Constructor.
     * @param firstName
     * @param lastName
     * @param email
     * @param password
     */
    public User(String firstName, String lastName, String email,
            String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.confirmPassword = "";
    }
    
    /**
     * Constructor.
     * @param firstName
     * @param lastName
     * @param email
     * @param password
     * @param confirmPassword
     */
    public User(String firstName, String lastName, String email,
            String password, String confirmPassword) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    // setters

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstname) {
        this.firstName = firstname;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    @Override
    public String toString() {
        return "User [email=" + email + ", firstName=" + firstName
                + ", lastName=" + lastName + ", password=" + password
                + ", confirmPassword=" + confirmPassword + "]";
    }

}
