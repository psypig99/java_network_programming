package edu.kosta.network.chatting;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by sejun on 17. 2. 21.
 */
public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(10000);
        System.out.println("Server socket is opened..." +serverSocket);

        ExecutorService executorService = Executors.newFixedThreadPool(5);
        List<Socket> socketList = new ArrayList<Socket>();

        while(true){
            Socket socket = serverSocket.accept();
            socketList.add(socket);

            System.out.println("Client is connected..." +socket);
            InputStream is = socket.getInputStream();
            DataInputStream dis = new DataInputStream(is);
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());


            executorService.execute(new Runnable() { // ExecutorSevice를 통해서 생성된 POOL에서 돌아가게 됨.
                @Override
                public void run() {
                    while(true){
                        try {
                            String receivedMessage = dis.readUTF();
                            System.out.println(receivedMessage);
                            System.out.println(socketList);
                            for(Socket socket : socketList){
                                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
                                dos.writeUTF(receivedMessage);
                            }

                        } catch(Exception e){
                            e.printStackTrace();
                        }
                    }
                }
            });
        }
    }
}
