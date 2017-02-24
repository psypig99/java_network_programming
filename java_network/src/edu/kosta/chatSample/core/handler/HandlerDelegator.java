package edu.kosta.chatSample.core.handler;

import edu.kosta.chatSample.core.message.Message;

public interface HandlerDelegator {
    
    void delegate(Message message);
}
