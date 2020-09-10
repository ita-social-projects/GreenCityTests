package com.softserve.edu.greencity.ui.tools.jdbc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class ManagerDao {
    private static volatile ManagerDao instance = null;
    private String username;
    private String password;
    private String url;
    private Map<Long, Connection> connections;

    private ManagerDao() {
        connections = new HashMap<>();
        registerDriver();
        readProperties();
    }

    private void registerDriver() {
        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
//TODO hide log info
    private void readProperties() {
        username = "";
        password = "";
        url = "";
    }

    public static ManagerDao get() {
        if (instance == null) {
            synchronized (ManagerDao.class) {
                if (instance == null) {
                    instance = new ManagerDao();
                }
            }
        }
        return instance;
    }

    public static void closeAllConnection() {
        if (instance != null) {
            for (Map.Entry<Long, Connection> entry : instance.connections.entrySet()) {
                try {
                    entry.getValue().close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    throw new RuntimeException(e.getMessage());
                }
            }
        }
    }

    public static void closeStatement(Statement statement) {
        try {
            statement.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    public Connection getConnection() {
        long idThread = Thread.currentThread().getId();
        Connection result = connections.get(idThread);
        if (result == null) {
            result = createConnection();
            connections.put(idThread, result);
        }
        return result;
    }

    public Statement getStatement() {
        Statement result = null;
        try {
            result = getConnection().createStatement();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return result;
    }

    private Connection createConnection() {
        Connection result = null;
        try {
            result = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return result;
    }

    public List<List<String>> parseResultSet(ResultSet resultSet) throws SQLException {
        List<List<String>> result = new ArrayList<>();
        int columnCount = resultSet.getMetaData().getColumnCount();
        while (resultSet.next()) {
            List<String> row = new ArrayList<>();
            for (int i = 1; i <= columnCount; i++) {
                row.add(resultSet.getString(i));
                //System.out.print(resultSet.getString(i) + "\t");
            }
            result.add(row);
            //System.out.println();
        }
        return result;
    }
}
