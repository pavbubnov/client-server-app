package com.javastart.server;

public class DepositeService {

    public String getDeposite(Account clientAccount, Bill someDeposite) {

        DataBase dataBase = DataBase.getInstance();

        clientAccount.getAmount().setBill(clientAccount.getAmount().getBill() + someDeposite.getBill());
        System.out.println("Client " + clientAccount.getName() + " add " + someDeposite.getBill());
        return "Client " + clientAccount.getName() + " add " + someDeposite.getBill() + " " + dataBase.updateBill(clientAccount);
    }
}
