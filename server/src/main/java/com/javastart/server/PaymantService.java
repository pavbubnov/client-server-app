package com.javastart.server;

public class PaymantService {

    String message;

    public String getPaymant(Account clientAccount, Bill somePaymant) {


        if (clientAccount.getAmount().getBill() + somePaymant.getBill() >= 0) {
            clientAccount.getAmount().setBill(clientAccount.getAmount().getBill() + somePaymant.getBill());
            message = "Client " + clientAccount.getName() + " paid " + (-1) * somePaymant.getBill();
            System.out.println(message);
            return message;
        } else {
            message = "Client " + clientAccount.getName() + " has not enough money for paymant";
            System.out.println(message);
            return message;
        }

    }
}
