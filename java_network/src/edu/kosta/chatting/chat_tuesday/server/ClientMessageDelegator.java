package edu.kosta.chatting.chat_tuesday.server;

import edu.kosta.chatting.chat_tuesday.framework.Delegator;

/**
 * Created by sejun on 17. 2. 22.
 */
public class ClientMessageDelegator implements Delegator {

    private TalkMessageHandler handler;

    public ClientMessageDelegator(TalkMessageHandler handler){
        this.handler = handler;
    }

    @Override
    public void delegate(String message) {
        System.out.println("Delegate message : " + message);
        handler.handle(message);
    }
}
