package com.javastart;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client {

    private DataOutputStream toServer;
    private InputStreamReader fromServer;

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
