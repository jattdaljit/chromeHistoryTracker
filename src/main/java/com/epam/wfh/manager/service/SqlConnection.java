package com.epam.wfh.manager.service;

import java.io.IOException;
import java.sql.*;

public class SqlConnection {

    public Connection getConnection() {
        Connection conn = null;
        try {
            FileUtility fileUtility = new FileUtility();
            String filename = fileUtility.copyFile();
            conn = DriverManager.getConnection("jdbc:sqlite:/" + fileUtility.getHomePath()
                    + fileUtility.getWfhPath() + filename);

        } catch (SQLException | IOException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public ResultSet getData() {
        String sql = "SELECT urls.url AS URL, datetime(last_visit_time / 1000000 - 11644473600, 'unixepoch', 'localtime')" +
                " AS TIME, (visits.visit_duration/1000000) AS DURATION FROM urls LEFT JOIN visits ON urls.id = visits.url";

        try {
            Connection conn = getConnection();
            Statement stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql);
            return resultSet;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
