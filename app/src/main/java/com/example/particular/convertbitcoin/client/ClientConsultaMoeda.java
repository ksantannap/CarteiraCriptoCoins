package com.example.particular.convertbitcoin.client;

import android.content.Context;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class ClientConsultaMoeda {

    public String consultaBtc() {

        StringBuilder resposta = new StringBuilder();

        try {
            URL url = new URL("https://www.mercadobitcoin.net/api/BTC/ticker/");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setConnectTimeout(5000);
            connection.connect();

            System.out.println("connection.getResponseCode(): " + connection.getResponseCode());
            System.out.println("connection.getResponseMessage(): " + connection.getResponseMessage());

            Scanner scanner = new Scanner(connection.getInputStream()!=null?connection.getInputStream():connection.getErrorStream());

            while (scanner.hasNext()) {
                resposta.append(scanner.next());
            }

            System.out.print(resposta);

        } catch (Exception e) {
            System.out.print(e.getMessage());
            e.printStackTrace();
        }
        return resposta.toString();
    }
}
