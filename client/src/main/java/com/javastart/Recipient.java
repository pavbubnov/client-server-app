package com.javastart;

import java.util.ArrayList;
import java.util.List;

public class Recipient {

    public static void readServerAnswer(Client client) throws Exception {
        int flag = 0;
        List<Integer> answerData = new ArrayList<Integer>();

        while (flag == 0) {
            Integer codeData = client.fromServer.read();
            answerData.add(codeData);
            if (codeData == 10) {
                flag = 1;
            }
        }

        char[] decodeData = new char[answerData.size()];
        for (int i = 0; i < answerData.size() - 1; i++) {
            decodeData[i] = (char) answerData.get(i).intValue();
        }
        System.out.println(decodeData);
    }
}