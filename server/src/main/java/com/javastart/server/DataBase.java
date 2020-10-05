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

    public void addAccountDB(Account account) {

        String insertTableSQL = "INSERT INTO CLIENT"
                + "(ID, NAME, AMOUNT)" + "VALUES" +
                "(" + String.valueOf(account.getId()) + ",'" + account.getName() + "','" +
                String.valueOf(account.getAmount().getBill()) + "')";
        try {
            getDBConnection().execute(insertTableSQL);
            System.out.println("Клиент " + account.getName() + " добавлен в базу данных");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("Ошибка передачи " + account.getName() + " в базу данных");
        }
    }

    public String updateBill(Account account) {
        String updateTableSQL = "UPDATE client SET amount = "
                + String.valueOf(account.getAmount().getBill()) + "WHERE ID = " + String.valueOf(account.getId());
        try {
            getDBConnection().execute(updateTableSQL);
            System.out.println(account.getName() + " client bill is updated");
            return "Client Bill " + account.getName() + " updated";
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("There are some problems with  " + account.getName() + " client bill updating " +
                    " please, ask to support team");
            return "There are some problems with  " + account.getName() + " client bill updating " +
                    " please, ask to support team";
        }
    }


}


