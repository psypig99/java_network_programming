package edu.kosta.chatting.chat_tuesday.framework.io;

import edu.kosta.chatting.chat_tuesday.framework.Dispatcher;

import java.io.IOException;

/**
 * Created by sejun on 17. 2. 22.
 */
public class SocketDispatcher implements Dispatcher {

    private SocketWorker socketWorker;

    public SocketDispatcher(SocketWorker socketWorker){
        this.socketWorker = socketWorker;
    }

    @Override
    public void dispatch(String message) {
        try {
            this.socketWorker.write(message);
        } catch (IOException e) {
            e.printStackTrace();
            throw  new RuntimeException(e);
        }
    }
}
