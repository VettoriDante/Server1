package com.server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class MioThread extends Thread{
    private Socket socket;
        
    
    public MioThread(Socket socket) {
            this.socket = socket;
    }

    @Override
    public void run() {

        try {
            String input1;
            String inputOperazione;
            boolean error = false;
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            do{
                String ans = "Risposta ";
                //leggo il primo input1
                input1 = in.readLine();
                System.out.println("String input1: " + input1);
        
                //leggo il secondo input e veridico l'input
                    inputOperazione = in.readLine();
                    System.out.println(inputOperazione);
                    switch (inputOperazione) {
                        case "M":
                            ans += input1.toUpperCase();
                        break;
                        case "m":
                            ans += input1.toLowerCase();
                        break;
                        case "r":
                            ans += new StringBuilder(input1).reverse().toString();
                        break;
                        case "c":
                            ans += Integer.toString(input1.length());
                        break;
                        case "ex":
                            ans = "!";
                        break;
                        default:
                            System.out.println("Valore errato");
                            error = true;
                        break;
                    }
                    if(ans.equals("!"))break;
                if(!error){
                    out.writeBytes(ans + "\n");
                    System.out.println(ans);
                }
                else
                {
                    out.writeBytes("!!!\n");
                }
            }while(true);
            
            socket.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
