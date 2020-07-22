package com.softserve.edu.greencity.ui.tools;

import org.testng.Assert;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DBQueries {

    private static Connection connection;
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

    public Connection getConnectionToGreenCityDB() {
        if(connection == null) {
            try {
                Class.forName(driverClass);
                connection =  DriverManager.getConnection(url, userName, password);
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }


    public void deleteUserByEmail(String email) {
        try {
            Connection connection = getConnectionToGreenCityDB();
            Statement statement = connection.createStatement();
            statement.executeQuery("DELETE FROM users WHERE email = '" + email + "'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteNewsByTitle(String title) {
        try {
            Connection connection = getConnectionToGreenCityDB();
            String titleInDataBase = title;
            ResultSet queryResponse = connection
                    .createStatement()
                    .executeQuery("SELECT * FROM public.eco_news WHERE title = '" + titleInDataBase + "'");
            Assert.assertTrue(queryResponse.next());
            int id = queryResponse.getInt("id");
            connection
                    .prepareStatement("DELETE FROM public.eco_news_tags * WHERE eco_news_id = " + id)
                    .execute();
            connection
                    .prepareStatement("DELETE FROM public.eco_news * WHERE id = " + id)
                    .execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
