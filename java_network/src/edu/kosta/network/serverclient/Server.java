package edu.kosta.network.serverclient;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by sejun on 17. 2. 21.
 */
public class Server {

    public static void main(String[] args) throws IOException {
//        ServerSocket serverSocket = new ServerSocket(10000);
//        System.out.println("Server socket is opened..." +serverSocket);
//
//        while(true){
//            Socket socket = serverSocket.accept();
//            System.out.println("Client is connected..." +socket);
//            InputStream is = socket.getInputStream();
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        int readByte = 0;
//                        while((readByte = is.read()) != -1){
//                            System.out.println(socket);
//                            System.out.println((char)readByte);
//                        }
//                    } catch(Exception e){
//                        e.printStackTrace();
//                    }
//                }
//            }).start();
//        }

//        /**
//         * 받은 메시지를 다시 응답해주는 기능이 적용된 서버
//         */
//        ServerSocket serverSocket = new ServerSocket(10000);
//        System.out.println("Server socket is opened..." +serverSocket);
//
//        while(true){
//            Socket socket = serverSocket.accept();
//            System.out.println("Client is connected..." +socket);
//            InputStream is = socket.getInputStream();
//            DataInputStream dis = new DataInputStream(is);
//            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        int readByte = 0;
//                        while(true){
//                            String receivedMessage = dis.readUTF();
//                            System.out.println(socket);
//                            System.out.println((char)readByte);
//                            dos.writeUTF(receivedMessage);
//                        }
//                    } catch(Exception e){
//                        e.printStackTrace();
//                    }
//                }
//            }).start();
//        }

        /**
         * 클라이언트와 서버가 stateless상태로 유지되는 경우
         */
//        ServerSocket serverSocket = new ServerSocket(10000);
//        System.out.println("Server socket is opened..." +serverSocket);
//
//        while(true){
//            Socket socket = serverSocket.accept();
//            System.out.println("Client is connected..." +socket);
//            InputStream is = socket.getInputStream();
//            DataInputStream dis = new DataInputStream(is);
//            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
//
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        String receivedMessage = dis.readUTF();
//                        System.out.println(socket);
//                        dos.writeUTF(receivedMessage);
//                    } catch(Exception e){
//                        e.printStackTrace();
//                    }
//                }
//            }).start();
//        }

        /**
         * ExecutorService 클래스를 이용하여 ThreadPool을 생성한 후
         * ThreadPool에서 thread를 획득하여 데이터를 처리함.
         */
        ServerSocket serverSocket = new ServerSocket(10000);
        System.out.println("Server socket is opened..." +serverSocket);

        ExecutorService executorService = Executors.newFixedThreadPool(5);

        while(true){
            Socket socket = serverSocket.accept();
            System.out.println("Client is connected..." +socket);
            InputStream is = socket.getInputStream();
            DataInputStream dis = new DataInputStream(is);
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

            executorService.execute(new Runnable() { // ExecutorSevice를 통해서 생성된 POOL에서 돌아가게 됨.
                @Override
                public void run() {
                    try {
                        String receivedMessage = dis.readUTF();
                        System.out.println(socket);
                        dos.writeUTF(receivedMessage);
                    } catch(Exception e){
                        e.printStackTrace();
                    }
                }
            });
        }

    }
}
