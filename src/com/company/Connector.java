package com.company;

import java.sql.Connection;
import java.sql.DriverManager;

public final class Connector {

    static String url = "jdbc:postgresql://localhost:3307/databeest";
    static String user = "postgres";
    static String password = "test123";

    public static Connection connect() {

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            return connection;
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return null;
    }
}
