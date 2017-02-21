package edu.kosta.network.serverclient;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by sejun on 17. 2. 21.
 */
public class Server {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(10000);
        System.out.println("Server socket is opened..." +serverSocket);

        while(true){
            Socket socket = serverSocket.accept();
            System.out.println("Client is connected..." +socket);
            InputStream is = socket.getInputStream();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        int readByte = 0;
                        while((readByte = is.read()) != -1){
                            System.out.println(socket);
                            System.out.println((char)readByte);
                        }
                    } catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }).start();


        }
    }
}
