package edu.kosta.chatting.chat_tuesday.framework.io;

import edu.kosta.chatting.chat_tuesday.framework.Delegator;
import edu.kosta.chatting.chat_tuesday.framework.Reactor;

import java.io.IOException;

/**
 * Created by sejun on 17. 2. 22.
 * 지속적으로 메시지를 읽어야 하므로 Thread를 생성해야 한다.
 */
public class SocketReactor implements Reactor, Runnable {

    private SocketWorker socketWorker;
    private Delegator delegator;

    public SocketReactor(SocketWorker socketWorker, Delegator delegator){
        this.socketWorker = socketWorker;
        this.delegator = delegator;
    }

    @Override
    public void receive(String message) {
        delegator.delegate(message);
    }

    @Override
    public void run() {
        while(true){
            try {
                String message = socketWorker.read();
                receive(message);
            } catch (IOException e) {
                // 예외가 발생하는 거는
                e.printStackTrace();
                break;
            }
        }
    }
}
