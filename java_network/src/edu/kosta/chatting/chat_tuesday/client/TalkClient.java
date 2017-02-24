package edu.kosta.chatting.chat_tuesday.client;

import edu.kosta.chatting.chat_tuesday.framework.Dispatcher;
import edu.kosta.chatting.chat_tuesday.framework.io.SocketDispatcher;
import edu.kosta.chatting.chat_tuesday.framework.io.SocketReactor;
import edu.kosta.chatting.chat_tuesday.framework.io.SocketWorker;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by sejun on 17. 2. 22.
 */
public class TalkClient {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        Socket socket = new Socket("localhost", 10000);

        System.out.println("Connected to server... " + socket);
        SocketWorker socketWorker = new SocketWorker(socket);
        Dispatcher dispatcher = new SocketDispatcher(socketWorker);

        ServerMessageDelegator delegator = new ServerMessageDelegator();
        SocketReactor reactor = new SocketReactor(socketWorker, delegator);

        new Thread(reactor).start();

        new Thread(new Runnable() { // 사용자의 입력을 받는 쓰레드
            @Override
            public void run() {
                while(true){
                    while (true){
                        System.out.println("Input Message");
                        String inputMessage = scanner.nextLine();
                        dispatcher.dispatch(inputMessage);
                    }
                }
            }
        }).start();
    }
}
