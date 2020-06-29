package com.softserve.edu.greencity.ui.tools;

import com.softserve.edu.greencity.ui.data.UserRepository;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DBQueries {

    private static volatile DBQueries instance = null;

    private Properties property = new Properties();

    public DBQueries() {
        try {
            final FileInputStream fis = new FileInputStream("src/test/resources/credentials.properties");
            property.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static DBQueries get() {
        if (instance == null) {
            synchronized (DBQueries.class) {
                if (instance == null) {
                    instance = new DBQueries();
                }
            }
        }
        return instance;
    }
    public final String URL = property.getProperty("DBProdURL");
    public final String USER_NAME = property.getProperty("DBProdUserName");
    public final String PASSWORD = property.getProperty("DBProdUserPassword");
    public final String DRIVER_CLASS_NAME = "org.postgresql.Driver";

    public void deleteUserByEmail(String email) throws SQLException {
        Connection connection = null;
        Statement statement = null;

        try {
            Class.forName(DRIVER_CLASS_NAME);
            connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
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
