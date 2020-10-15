package com.javastart.server;

import java.sql.*;

public class DataBase {

    private static DataBase INSTANCE;

    private DataBase() {
    }

    public static DataBase getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DataBase();
        }
        return INSTANCE;
    }

    public Statement getDBConnection() {

        Connection dbConnection = null;
        Statement statement = null;

        String url = "jdbc:postgresql://localhost:5432/test_base";
        String user = "postgres";
        String pass = "admin";

        try {
            Class.forName("org.postgresql.Driver");
            System.out.println("Драйвер проверен");
        } catch (ClassNotFoundException e) {
            System.out.println("Ошибка драйвера");
            e.printStackTrace();
        }

        try {

            dbConnection = DriverManager.getConnection(url, user, pass);
            statement = dbConnection.createStatement();
            System.out.println("Cвязь с БД установлена");

            return statement;
        } catch (SQLException throwables) {
            System.out.println("connection DB error");
            throwables.printStackTrace();
        }
        return statement;
    }
}


