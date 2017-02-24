package edu.kosta.chatting.chat_tuesday.framework.io;

import edu.kosta.chatting.chat_tuesday.framework.Delegator;
import edu.kosta.chatting.chat_tuesday.framework.Dispatcher;
import edu.kosta.chatting.chat_tuesday.framework.Receptionist;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sejun on 17. 2. 22.
 */
public class SocketReceptionist implements Receptionist {

    private int port;
    private Delegator delegator;

    private List<Dispatcher> dispatcherList = new ArrayList<>();
    public SocketReceptionist(int port, Delegator delegator){
        this.port = port;
        this.delegator = delegator;
    }

    @Override
    public void startServer() {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Server socket is opened ..." + serverSocket);

            while(true){
                Socket socket = serverSocket.accept();
                System.out.println("Client is connected ..." + socket);
                SocketWorker socketWorker = new SocketWorker(socket);
                SocketDispatcher dispatcher = new SocketDispatcher(socketWorker);
                dispatcherList.add(dispatcher);
                SocketReactor reactor = new SocketReactor(socketWorker, delegator);
                new Thread(reactor).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Fail to open server socket ...");
        }
    }

    @Override
    public void propagate(String message) {
        for(Dispatcher dispatcher : dispatcherList){
            dispatcher.dispatch(message);
        }
    }
}
