package jm.task.core.jdbc.util;

import com.mysql.cj.MysqlConnection;

import java.sql.*;

public class Util {
    private final static String DRIVER = "com.mysql.cj.jdbc.Driver";
    private final static String DB_URL = "jdbc:mysql://localhost:3306/user";
    private final static String USER_NAME = "root";
    private final static String PASSWORD = "root";

    public Connection getMySQLConnection(){
        Connection connection = null;
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(DB_URL, USER_NAME,
                    PASSWORD);
            System.out.println("Connection OK!");
        } catch (SQLException|ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }
}

