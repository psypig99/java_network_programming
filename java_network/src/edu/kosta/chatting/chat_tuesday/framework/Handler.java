package edu.kosta.chatting.chat_tuesday.framework;

/**
 * Created by sejun on 17. 2. 22.
 * message를 보내는 것과 관련된 인터페이스
 */
public interface Handler {
    void handle(String message);
}
