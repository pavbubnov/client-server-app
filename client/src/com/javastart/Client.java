package com.javastart;

import java.io.*;
import java.net.Socket;

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

    public Client(int port) {
        try {
            Socket socket = new Socket("localhost", port);
            toServer = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.getMessage();
        }
    }

    private void sendNotification(Long external_id, String message, String extra_params) throws Exception {
        toServer.writeBytes(external_id + "\n" + message + "\n" + extra_params + "\n");
    }

    public static void main(String[] args) {
        Client client = new Client(9999);

        try {
            client.sendNotification(1L, "CHECK-1", "params1");
            client.sendNotification(2L, "CHECK-2", "params2");
        } catch (Exception e) {
            e.getMessage();
        }
    }
}
