package com.server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Server Avviato");
        ServerSocket ser1 = new ServerSocket(3000);

        //crea canali di in - out

        String input;

        do{        
        Socket socket = ser1.accept();
        System.out.println("un client si è collegato");
        
        MioThread t = new MioThread(socket);
        t.start();

        }while(true);
    }
}