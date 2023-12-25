package com.vtnq.smartmoney.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection{
    private static Connection connection;
    public static Connection getConnection()throws SQLException {
        String connectString = "jdbc:mysql://localhost:3308/smartmoney";
        String username = "root";
        String password = "123456789";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(connectString, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
    public static void DisConnection() throws SQLException {
        if(connection!=null){
            try {
                connection.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
