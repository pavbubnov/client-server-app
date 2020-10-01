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
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {

            dbConnection = DriverManager.getConnection(url, user, pass);
            statement = dbConnection.createStatement();

            return statement;
        } catch (SQLException throwables) {
            System.out.println("error");
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
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }




}


