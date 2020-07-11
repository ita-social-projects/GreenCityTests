package com.softserve.edu.greencity.ui.tools;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DBQueries {

    private Properties property = new Properties();
    private String url;
    private String userName;
    private String password;
    private String driverClass = "org.postgresql.Driver";

    public DBQueries() {
        try {
            final FileInputStream fis = new FileInputStream("src/test/resources/credentials.properties");
            property.load(fis);
            url = property.getProperty("DBProdURL");
            userName = property.getProperty("DBProdUserName");
            password = property.getProperty("DBProdUserPassword");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void deleteUserByEmail(String email) {

        try {
            Class.forName(driverClass);
            Connection connection = DriverManager.getConnection(url, userName, password);
            Statement statement = connection.createStatement();
            statement.executeQuery("DELETE FROM users WHERE email = '" + email + "'");


        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }


}
