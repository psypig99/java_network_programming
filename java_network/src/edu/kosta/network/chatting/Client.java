package edu.kosta.network.chatting;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by sejun on 17. 2. 21.
 */
public class Client {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        Socket socket = new Socket("localhost", 10000);
        System.out.println("Connected to server... " + socket);

        OutputStream os = socket.getOutputStream();
        DataOutputStream dos = new DataOutputStream(os);
        DataInputStream dis = new DataInputStream(socket.getInputStream());

        new Thread(new Runnable() { // 사용자의 입력을 받는 쓰레드
            @Override
            public void run() {
                while(true){
                    System.out.println("Input Message");
                    String inputMessage = scanner.nextLine();
                    try {
                        dos.writeUTF(inputMessage);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        while(true){
            String receivedMessage = dis.readUTF();
            System.out.println("receivedMessage ..." +receivedMessage);
        }
    }
}
