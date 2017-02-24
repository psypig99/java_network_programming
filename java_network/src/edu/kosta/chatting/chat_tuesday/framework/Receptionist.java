package edu.kosta.chatting.chat_tuesday.framework;

/**
 * Created by sejun on 17. 2. 22.
 *
 */
public interface Receptionist {

    /**
     * 서버의 소켓을 여는 것과 관련된 메서드
     */
    void startServer();

    void propagate(String message);
}
