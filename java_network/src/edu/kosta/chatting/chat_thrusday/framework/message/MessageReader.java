package edu.kosta.chatting.chat_thrusday.framework.message;

import java.io.IOException;

/**
 * Created by sejun on 17. 2. 23.
 */
public interface MessageReader {

    BlockMessage read() throws IOException;
}
