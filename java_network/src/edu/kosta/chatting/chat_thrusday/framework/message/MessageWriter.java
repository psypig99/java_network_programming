package edu.kosta.chatting.chat_thrusday.framework.message;

import java.io.IOException;

/**
 * Created by sejun on 17. 2. 23.
 */
public interface MessageWriter {

    void write(BlockMessage message) throws IOException;
}
