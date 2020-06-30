package com.softserve.edu.greencity.ui.tools;

import com.softserve.edu.greencity.ui.data.UserRepository;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
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


    public void deleteUserByEmail(String email) throws SQLException {
        Connection connection = null;
        Statement statement = null;

        try {
            Class.forName(driverClass);
            connection = DriverManager.getConnection(url, userName, password);
            statement = connection.createStatement();
            statement.executeQuery("DELETE FROM users WHERE email = '"+ email + "'");


        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            statement.close();
            connection.close();
        }
    }
}
