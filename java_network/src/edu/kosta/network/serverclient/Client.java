package edu.kosta.network.serverclient;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * Created by sejun on 17. 2. 21.
 */
public class Client {

    public static void main(String[] args) throws IOException {
//        Socket socket = new Socket("localhost", 10000);
//        System.out.println("Connected to server... " + socket);
//
//        OutputStream os = socket.getOutputStream();
////        for(int i=0; i < 10; i++){
////            os.write("Hello world~~~~~~".getBytes());
////        }
//        while(true){
//            os.write("Hello world~~~~~~".getBytes());
//            System.out.println("message sent ....");
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }

//        Socket socket = new Socket("localhost", 10000);
//        System.out.println("Connected to server... " + socket);
//
//        OutputStream os = socket.getOutputStream();
//        DataOutputStream dos = new DataOutputStream(os);
//        DataInputStream dis = new DataInputStream(socket.getInputStream());
//
//        while(true){
//            dos.writeUTF("Hello world~~~~~~");
//            System.out.println("message sent ....");
//            String receivedMessage = dis.readUTF();
//            System.out.println("receivedMessage ..." +receivedMessage);
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }

//        /**
//         * Scanner를 이용하여 사용자가 직접 내용을 입력하는 내용
//         */
//        Scanner scanner = new Scanner(System.in);
//
//        Socket socket = new Socket("localhost", 10000);
//        System.out.println("Connected to server... " + socket);
//
//        OutputStream os = socket.getOutputStream();
//        DataOutputStream dos = new DataOutputStream(os);
//        DataInputStream dis = new DataInputStream(socket.getInputStream());
//
//        while(true){
//            System.out.println("Input Message");
//            String inputMessage = scanner.nextLine();
//
//            dos.writeUTF(inputMessage);
//            System.out.println("message sent ....");
//            String receivedMessage = dis.readUTF();
//            System.out.println("receivedMessage ..." +receivedMessage);
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }

        /**
         * 클라이언트와 서버가 stateless상태로 유지되는 경우
         * 쓰레드가 100개가 생성되어 동시에 응답을 주고 받는 경우
         */

        for(int i=0; i<100; i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try{
                        while(true){
                            Socket socket = new Socket("localhost", 10000);
                            System.out.println("Connected to server... " + socket);

                            OutputStream os = socket.getOutputStream();
                            DataOutputStream dos = new DataOutputStream(os);
                            DataInputStream dis = new DataInputStream(socket.getInputStream());

                            dos.writeUTF("hello world");
                            System.out.println("message sent ....");
                            String receivedMessage = dis.readUTF();
                            System.out.println("receivedMessage ..." +receivedMessage);
                            socket.close();

                            Thread.sleep(1000);
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    } catch (UnknownHostException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }).start();
        }

    }

}
