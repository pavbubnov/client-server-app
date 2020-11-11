package com.javastart.server;

public class PaymantService {


    public String getPaymant(Account clientAccount, Bill somePaymant) {

        AccountDAO accountDAO = new AccountDAO();

        if (clientAccount.getAmount().getBill() + somePaymant.getBill() >= 0) {
            clientAccount.getAmount().setBill(clientAccount.getAmount().getBill() + somePaymant.getBill());
            System.out.println("Client " + clientAccount.getName() + " paid " + (-1) * somePaymant.getBill());
            return "Client " + clientAccount.getName() + " paid " + (-1) * somePaymant.getBill() + " " +
                    accountDAO.updateBill(clientAccount);
        } else {
            System.out.println("Client " + clientAccount.getName() + " has not enough money for paymant");
            return "Client " + clientAccount.getName() + " has not enough money for paymant";
        }

    }
}
