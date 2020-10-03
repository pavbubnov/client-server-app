package com.javastart.server;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Класс сервера
 * Нужно понять как он в связке работает с клиентом
 * Так же отрефакторить, отделить от метода main
 * <p>
 * 1) Нужно добавить базу данных в памяти (в виде Singleton)
 * 2) Нужно уметь принимать сущности от клиента, обрабатывать их и сохранять в базу
 * 3) Нужно дописать обработку ошибок
 * 4) Добавить логирования важных действий
 * <p>
 * 5) **(со зведочкой) Научить сервер отвечать клиенту об успешных операциях
 */
public class Server {

    private ServerSocket serverSocket;
    private BufferedReader reader;
//    private BufferedWriter answer;


    private int port;

    public Server(int port) {
        this.port = port;
    }

    public void start() throws IOException {
        createConnection(port);
        Socket clientSocket = serverSocket.accept();
        reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
//        answer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
        startListener();
    }

    private void readCommand() {
        String message;
        String data;
        String dataCondition;
//        String answerData;

        try {

            ObjectMapper objectMapper = new ObjectMapper();
            data = reader.readLine();


            if (data.startsWith("{\"id\"")) {
                StringReader stringReader = new StringReader(data);
                Account clientAccount = objectMapper.readValue(stringReader, Account.class);
                System.out.println("Клиент " + clientAccount.getName() + " поступил в обработку");

                data = reader.readLine();

                if (data.startsWith("{\"bill\":-") || data.startsWith("{\"bill\":")) {
                    if (data.startsWith("{\"bill\":-")) {
                        StringReader paymantReader = new StringReader(data);
                        Bill clientPaymant = objectMapper.readValue(paymantReader, Bill.class);
                        System.out.println("Клиент " + clientAccount.getName() + " хочет оплатить сумму: " + (-1) * clientPaymant.getBill());
                        PaymantService paymantService = new PaymantService();
                        paymantService.getPaymant(clientAccount, clientPaymant);
                    } else {
                        StringReader depositeReader = new StringReader(data);
                        Bill clientDeposite = objectMapper.readValue(depositeReader, Bill.class);
                        System.out.println("Клиент " + clientAccount.getName() + " хочет пополнить счет на : " + clientDeposite.getBill());
                        DepositeService depositeService = new DepositeService();
                        depositeService.getDeposite(clientAccount, clientDeposite);
                    }
                } else {
                    System.out.println(data);
                }
                DataBase dataBase = DataBase.getInstance();
                dataBase.addAccountDB(clientAccount);
            } else {
                if (data.equals("codeStopServer")) {
                    serverSocket.close();
                } else {
                    System.out.println(data);
//                    answer.write("First message sent");
                }
            }


        } catch (IOException e) {
            System.err.println("Can't parse task, got message: " + e.getMessage());
        }
    }

    private void startListener() {
        new Thread(() -> {
            while (true) {
                try {
                    if (reader.ready()) {
                        readCommand();
                    }
                    if (serverSocket.isClosed()) {
                        System.out.println("Server stop");
                        break;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void createConnection(int port) {
        try {
            serverSocket = new ServerSocket(port, 10000);
            System.out.println("Server starts");
        } catch (IOException e) {
            System.out.println("Can't create connection");
            e.printStackTrace();
        }
    }


}
