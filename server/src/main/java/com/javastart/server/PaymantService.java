package com.javastart.server;

public class PaymantService {


    public String getPaymant(Account clientAccount, Bill somePaymant) {

        DataBase dataBase = DataBase.getInstance();

        if (clientAccount.getAmount().getBill() + somePaymant.getBill() >= 0) {
            clientAccount.getAmount().setBill(clientAccount.getAmount().getBill() + somePaymant.getBill());
            System.out.println("Client " + clientAccount.getName() + " paid " + (-1) * somePaymant.getBill());
            return "Client " + clientAccount.getName() + " paid " + (-1) * somePaymant.getBill() + " " +
                    dataBase.updateBill(clientAccount);
        } else {
            System.out.println("Client " + clientAccount.getName() + " has not enough money for paymant");
            return "Client " + clientAccount.getName() + " has not enough money for paymant";
        }

    }
}
