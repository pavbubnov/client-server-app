package com.javastart;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class MainClient {

    public static void main(String[] args) {
        Client client = new Client(9999);

        String message;
        String extraParams;
        Long externalIdNumber = 2l;

        Scanner scanner = new Scanner(System.in);

        Bill pavelMoney = new Bill(10000);
        Account pavelAccount = new Account(1, "Pavel", pavelMoney);

        Bill nikitaMoney = new Bill(5000);
        Account nikitaAccount = new Account(2, "Nikita", nikitaMoney);

        Bill pavelDeposite = new Bill(5000);
        Bill pavelPaymant = new Bill(2500);

        try {
            //client.sendNotification(1L, "CHECK-1", "params1");
            //client.sendNotification(2L, "CHECK-2", "params2");
            client.sendAccount(pavelAccount);
            client.sendDeposote(pavelDeposite);
            client.sendPaymant(pavelPaymant);
//            client.sendAccount(nikitaAccount);
        } catch (Exception e) {
            e.getMessage();
        }


//        for (; ; ) {
//            externalIdNumber++;
//            System.out.println("Введите сообщение");
//            message = scanner.nextLine();
//            System.out.println("Ввведите примечание");
//            extraParams = scanner.nextLine();
//            try {
//                client.sendNotification(externalIdNumber, message, extraParams);
//            } catch (Exception e) {
//                e.getMessage();
//            }
//        }

    }
}
