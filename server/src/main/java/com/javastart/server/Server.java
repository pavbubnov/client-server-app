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

    private int port;

    public Server(int port) {
        this.port = port;
    }

    public void start() throws IOException {
        createConnection(port);
        Socket clientSocket = serverSocket.accept();
        reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        startListener();
    }

    private void readCommand() {
        String external_id;
        String message;
        String extra_params;
        String data;
        try {

            ObjectMapper objectMapper = new ObjectMapper();
            data = reader.readLine();
            StringReader stringReader = new StringReader(data);
            Account clientAccount = objectMapper.readValue(stringReader, Account.class);

            data = reader.readLine();
            StringReader depositeReader = new StringReader(data);
            Bill clientDeposite = objectMapper.readValue(depositeReader, Bill.class);

            DepositeService depositeService = new DepositeService();
            depositeService.getDeposite(clientAccount, clientDeposite);

            data = reader.readLine();
            StringReader paymantReader = new StringReader(data);
            Bill clientPaymant = objectMapper.readValue(paymantReader, Bill.class);

            PaymantService paymantService = new PaymantService();
            paymantService.getPaymant(clientAccount, clientPaymant);

            DataBase dataBase = DataBase.getInstance();
            dataBase.addAccountDB(clientAccount);

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
