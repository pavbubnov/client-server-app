package com.javastart.server;

public class PaymantService {

    public void getPaymant(Account clientAccount, Bill somePaymant) {
        Bill newAmount = new Bill();

        if (clientAccount.getAmount().getBill() + somePaymant.getBill() >= 0) {
            clientAccount.getAmount().setBill(clientAccount.getAmount().getBill() + somePaymant.getBill());
            System.out.println(" Клиент " + clientAccount.getName() + " оплатил " +  (-1)*somePaymant.getBill());
        } else {
            System.out.println("у клиента " + clientAccount.getName() + " недостаточно средств для совершения платежа");
        }

    }
}
