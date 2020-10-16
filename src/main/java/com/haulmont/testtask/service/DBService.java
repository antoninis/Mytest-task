package com.haulmont.testtask.service;

import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ResourceBundle;

public class DBService {


    public static Connection getConnection() {

        Connection connection = null;
        ResourceBundle resource = ResourceBundle.getBundle("database");
        String driver = resource.getString("db.driver");
        String url = resource.getString("db.url");
        String username = resource.getString("db.user");
        String password = resource.getString("db.password");

        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }


    public void createDB() {
        runSqlScript("database/file1.sql");
    }

    public void fillDB() {
        runSqlScript("database/file2.sql");
    }

    public void runSqlScript(String scriptPath) {
        File file = new File(scriptPath);
        String createQuery = null;
        try {
            createQuery = FileUtils.readFileToString(file,"utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            getConnection().createStatement().execute(createQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
