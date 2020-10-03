package com.javastart.server;

public class DepositeService {
    private String message;

    public String getDeposite(Account clientAccount, Bill someDeposite) {
        clientAccount.getAmount().setBill(clientAccount.getAmount().getBill() + someDeposite.getBill());
        message = "Client " + clientAccount.getName() + " add " + someDeposite.getBill();
        System.out.println(message);
        return message;
    }
}
