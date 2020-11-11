package com.javastart.server;

public class DepositeService {

    public String getDeposite(Account clientAccount, Bill someDeposite) {

        AccountDAO accountDAO = new AccountDAO();

        clientAccount.getAmount().setBill(clientAccount.getAmount().getBill() + someDeposite.getBill());
        System.out.println("Client " + clientAccount.getName() + " add " + someDeposite.getBill());
        return "Client " + clientAccount.getName() + " add " + someDeposite.getBill() + " " + accountDAO.updateBill(clientAccount);
    }
}
