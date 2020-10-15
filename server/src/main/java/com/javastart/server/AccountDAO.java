package com.javastart.server;

import java.sql.SQLException;

public class AccountDAO {

    private DataBase dataBase = DataBase.getInstance();

    public void addAccountDB(Account account) {

        String insertTableSQL = "INSERT INTO CLIENT"
                + "(ID, NAME, AMOUNT)" + "VALUES" +
                "(" + String.valueOf(account.getId()) + ",'" + account.getName() + "','" +
                String.valueOf(account.getAmount().getBill()) + "')";
        try {
            dataBase.getDBConnection().execute(insertTableSQL);
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
            dataBase.getDBConnection().execute(updateTableSQL);
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
