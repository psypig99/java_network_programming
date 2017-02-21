package edu.kosta.network.serverclient;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by sejun on 17. 2. 21.
 */
public class Client {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 10000);
        System.out.println("Connected to server... " + socket);

        OutputStream os = socket.getOutputStream();
//        for(int i=0; i < 10; i++){
//            os.write("Hello world~~~~~~".getBytes());
//        }
        while(true){
            os.write("Hello world~~~~~~".getBytes());
            System.out.println("message sent ....");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
