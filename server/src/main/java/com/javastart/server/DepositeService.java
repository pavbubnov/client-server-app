package com.javastart.server;

public class DepositeService {

    public void getDeposite (Account clientAccount, Bill someDeposite) {
        clientAccount.getAmount().setBill(clientAccount.getAmount().getBill() + someDeposite.getBill());
    }
}
