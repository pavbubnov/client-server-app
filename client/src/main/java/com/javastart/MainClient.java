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
        Integer question;


        Scanner intScanner = new Scanner(System.in);
        Scanner stringScanner = new Scanner(System.in);

        Bill pavelMoney = new Bill(10000);
        Account pavelAccount = new Account(1, "Pavel", pavelMoney);

        Bill nikitaMoney = new Bill(5000);
        Account nikitaAccount = new Account(2, "Nikita", nikitaMoney);

        Bill maximMoney = new Bill(15000);
        Account maximAccount = new Account(3, "Maxim", maximMoney);

        Bill pavelDeposite = new Bill(5000);
        Bill maximPaymant = new Bill(20000);


        try {

            client.sendNotification("Message before account");
//            client.readServerAnswer();

            client.sendAccount(pavelAccount);
            client.sendDeposote(pavelDeposite);

            client.sendAccount(nikitaAccount);
            client.sendNotification("no client operation");

            client.sendAccount(maximAccount);
            client.sendPaymant(maximPaymant);

            client.sendNotification("Message after account");

            System.out.println("Ввести сообщение? ( 1 - да / любая клавиша - нет) ");
            question = intScanner.nextInt();

            while (question == 1) {
                System.out.println("Введите сообщение");
                message = stringScanner.nextLine();
                client.sendNotification(message);
                System.out.println("Ввести еще сообщение? (1 - да / любая клавиша - нет)");
                question = intScanner.nextInt();
            }

            client.sendNotification("codeStopServer");

        } catch (Exception e) {
            e.getMessage();
        }
    }
}
