package com.javastart;

import java.util.Scanner;

public class MainClient {

    private static final String CODE_STOP_SERVER = "codeStopServer";

    public static void main(String[] args) {

        Client client = new Client(9999);
        Sender sender = new Sender();

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

            sender.sendNotification("Message before account", client);
            sender.sendAccount(pavelAccount, client);
            sender.sendDeposote(pavelDeposite, client);

            sender.sendAccount(nikitaAccount, client);
            sender.sendNotification("no client operation", client);

            sender.sendAccount(maximAccount, client);
            sender.sendPaymant(maximPaymant, client);

            sender.sendNotification("Message after account", client);

            System.out.println("Ввести сообщение? ( 1 - да / 2 - нет) ");
            question = intScanner.nextInt();

            while (question == 1) {
                System.out.println("Введите сообщение");
                message = stringScanner.nextLine();
                sender.sendNotification(message, client);
                System.out.println("Ввести еще сообщение? (1 - да / 2 - нет)");
                question = intScanner.nextInt();
            }

            sender.sendNotification(CODE_STOP_SERVER, client);

        } catch (Exception e) {
            e.getMessage();
        }
    }
}
