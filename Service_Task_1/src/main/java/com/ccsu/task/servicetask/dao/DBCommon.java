package com.ccsu.task.servicetask.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static com.ccsu.task.servicetask.utils.ConfigUtils.*;

public class DBCommon {
    public static Connection getConnection() {
        try {
            String driveClass = getValue("driver_class");
            Class.forName(driveClass);

            String ip = getValue("ip");
            String dataSchema = getValue("data_schema");
            String port = getValue("port");
            String url = "jdbc:mysql://" + ip + ":" + port + "/" + dataSchema;
            String userName = getValue("username");
            String passWord = getValue("password");

            return DriverManager.getConnection(url, userName, passWord);
        } catch (SQLException | ClassNotFoundException ignored) {
        }
        return null;
    }

    public static void closeConnection(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ignored) {
        }
    }

    public static void main(String[] args)  {
        Connection connection = getConnection();
    }
}
