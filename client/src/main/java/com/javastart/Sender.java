package com.javastart;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

public class Sender {

    private StringWriter writer;
    private ObjectMapper mapper;

    public void sendNotification(String message, Client client) throws Exception {
        client.getToServer().writeBytes(message + "\n");
        if (!message.equals("codeStopServer")) {
            Recipient.readServerAnswer(client);
        }
    }

    public void sendAccount(Account account, Client client) throws Exception {
        writer = new StringWriter();
        mapper = new ObjectMapper();
        mapper.writeValue(writer, account);
        client.getToServer().writeBytes(writer.toString() + "\n");
        Recipient.readServerAnswer(client);
    }

    public void sendDeposote(Bill deposite, Client client) throws Exception {
        writer = new StringWriter();
        mapper = new ObjectMapper();
        mapper.writeValue(writer, deposite);
        client.getToServer().writeBytes(writer.toString() + "\n");
        Recipient.readServerAnswer(client);
    }

    public void sendPaymant(Bill paymant, Client client) throws Exception {
        writer = new StringWriter();
        mapper = new ObjectMapper();
        paymant.setBill(paymant.getBill() * (-1));
        mapper.writeValue(writer, paymant);
        client.getToServer().writeBytes(writer.toString() + "\n");
        Recipient.readServerAnswer(client);
    }


}
