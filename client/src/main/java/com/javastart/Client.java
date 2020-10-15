package com.javastart;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

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
    private InputStreamReader fromServer;
    //private ObjectOutputStream objectOutputStream;

    public DataOutputStream getToServer() {
        return toServer;
    }

    public InputStreamReader getFromServer() {
        return fromServer;
    }

    public Client(int port) {
        try {
            Socket socket = new Socket("localhost", port);
            toServer = new DataOutputStream(socket.getOutputStream());
            fromServer = new InputStreamReader(socket.getInputStream());
        } catch (IOException e) {
            e.getMessage();
        }
    }


}
