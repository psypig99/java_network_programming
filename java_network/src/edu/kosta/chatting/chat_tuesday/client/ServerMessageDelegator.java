package edu.kosta.chatting.chat_tuesday.client;

import edu.kosta.chatting.chat_tuesday.framework.Delegator;

/**
 * Created by sejun on 17. 2. 22.
 */
public class ServerMessageDelegator implements Delegator {
    @Override
    public void delegate(String message) {
        System.out.println(message);
    }
}
