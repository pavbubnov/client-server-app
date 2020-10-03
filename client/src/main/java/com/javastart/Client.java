package com.javastart;

import java.io.*;
import java.net.Socket;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Это клиент для отправки сообщений на приложение сервера
 * Здесь есть несколько проблем, которые нужно исправить
 * <p>
 * Нужно разобраться как он работает (запустить сервер в дебаге и посмотреть сообщения)
 * <p>
 * 1) Разнести отдельно класс клиента и класс с методом main
 * 2) Обеспечетить ввод сообщений как с консоли, так и при старте приложения (String[] args)
 * 3) Не завершать клиента после отправки сообщения
 * 4) Передавать сущности, осуществлять перевод денег (Account, Bill, Payment, Adjustment)
 * 5) Сделать нормальную обработку ошибок
 * 6) **(Со зведочокй) Настроить обратную связь от сервера, что перевод успешно выполнил и тд. (подтверждение операций)
 */
public class Client {

    private DataOutputStream toServer;
//    private DataInputStream fromServer;
    private ObjectOutputStream objectOutputStream;
    private StringWriter writer;
    private ObjectMapper mapper;
    private String data;


    public Client(int port) {
        try {
            Socket socket = new Socket("localhost", port);
            toServer = new DataOutputStream(socket.getOutputStream());
//            fromServer = new DataInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.getMessage();
        }
    }

    public void sendNotification(String message) throws Exception {
        toServer.writeBytes(message + "\n");
    }

    public void sendAccount(Account account) throws Exception {
        writer = new StringWriter();
        mapper = new ObjectMapper();
        mapper.writeValue(writer, account);
        data = writer.toString() + "\n";
        toServer.writeBytes(data);
    }

    public void sendDeposote(Bill deposite) throws Exception {
        writer = new StringWriter();
        mapper = new ObjectMapper();
        mapper.writeValue(writer, deposite);
        data = writer.toString() + "\n";
        toServer.writeBytes(data);
    }

    public void sendPaymant(Bill paymant) throws Exception {
        writer = new StringWriter();
        mapper = new ObjectMapper();
        paymant.setBill(paymant.getBill() * (-1));
        mapper.writeValue(writer, paymant);
        data = writer.toString() + "\n";
        toServer.writeBytes(data);
    }

//    public void readServerAnswer () throws Exception {
//        System.out.println(fromServer.readUTF());
//    }


}
