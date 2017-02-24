package edu.kosta.chatting.chat_tuesday.framework;

/**
 * Created by sejun on 17. 2. 22.
 * Message를 보내는 것과 관련된 인터페이스
 */
public interface Dispatcher {
    void dispatch(String message);
}
