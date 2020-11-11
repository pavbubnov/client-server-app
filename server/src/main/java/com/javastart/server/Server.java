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
    private BufferedWriter answer;


    private int port;

    public Server(int port) {
        this.port = port;
    }

    public void start() throws IOException {
        createConnection(port);
        Socket clientSocket = serverSocket.accept();
        reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        answer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
        startListener();
    }

    private void readCommand() {

        try {

            String data = reader.readLine();
            AccountDAO accountDAO = new AccountDAO();

            if (data.startsWith("{\"id\"")) {
                Account clientAccount = readAccount(data);
                if (clientAccount != null) {
                    accountDAO.addAccountDB(clientAccount);
                }
                data = reader.readLine();
                if ((data.startsWith("{\"bill\":-") || data.startsWith("{\"bill\":") && clientAccount != null)) {
                    if (data.startsWith("{\"bill\":-")) {
                        readAndReleasePaymant(data, clientAccount);
                    } else {
                        readAndReleaseDeposite(data, clientAccount);
                    }
                } else {
                    readMessage(data);
                }

            } else {
                if (data.equals("codeStopServer")) {
                    serverSocket.close();
                } else {
                    readMessage(data);
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

    private Account readAccount(String data) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            StringReader stringReader = new StringReader(data);
            Account clientAccount = objectMapper.readValue(stringReader, Account.class);
            System.out.println("Client " + clientAccount.getName() + " sent");
            answer.write("Account " + clientAccount.getName() + " sent " + "\n");
            answer.flush();
            return clientAccount;
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Account read error");
            return null;
        }
    }

    private void readAndReleasePaymant(String data, Account clientAccount) {
        ObjectMapper objectMapper = new ObjectMapper();
        StringReader paymantReader = new StringReader(data);
        Bill clientPaymant = null;

        try {
            clientPaymant = objectMapper.readValue(paymantReader, Bill.class);
            System.out.println("Client " + clientAccount.getName() + " want to pay: " + (-1) * clientPaymant.getBill());
            PaymantService paymantService = new PaymantService();
            answer.write(paymantService.getPaymant(clientAccount, clientPaymant) + "\n");
            answer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readAndReleaseDeposite(String data, Account clientAccount) {

        ObjectMapper objectMapper = new ObjectMapper();
        StringReader depositeReader = new StringReader(data);
        Bill clientDeposite = null;

        try {
            clientDeposite = objectMapper.readValue(depositeReader, Bill.class);
            System.out.println("Client " + clientAccount.getName() + " want to add : " + clientDeposite.getBill());
            DepositeService depositeService = new DepositeService();
            answer.write(depositeService.getDeposite(clientAccount, clientDeposite) + "\n");
            answer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readMessage(String data) {
        System.out.println(data);
        try {
            answer.write("Message from client sent" + "\n");
            answer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
