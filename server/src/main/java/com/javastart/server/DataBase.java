package com.javastart.server;

import java.sql.*;

public class DataBase {

    private static DataBase INSTANCE;
    private String id;
    private String name;
    private String amount;
    private String infoToTable;

    private DataBase() {
    }

    public static DataBase getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DataBase();
        }
        return INSTANCE;
    }

    private Statement getDBConnection() {

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

    public void addAccountDB (Account account) {

        id = String.valueOf(account.getId());
        name = account.getName();
        amount = String.valueOf(account.getAmount().getBill());
        infoToTable = "(" + id + ",'" + name + "','" + amount + "')";

        String insertTableSQL = "INSERT INTO CLIENT"
                + "(ID, NAME, AMOUNT)" + "VALUES" +
                infoToTable;

        try {
            getDBConnection().execute(insertTableSQL);
            System.out.println("Клиент " + account.getName() + " добавлен в базу данных");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("Ошибка передачи " + account.getName() + " в базу данных");
        }


    }




}


