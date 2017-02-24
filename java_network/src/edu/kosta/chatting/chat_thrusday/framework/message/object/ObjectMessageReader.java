package edu.kosta.chatting.chat_thrusday.framework.message.object;

import edu.kosta.chatting.chat_thrusday.framework.message.BlockMessage;
import edu.kosta.chatting.chat_thrusday.framework.message.MessageReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

/**
 * Created by sejun on 17. 2. 23.
 */
public class ObjectMessageReader implements MessageReader {

    private ObjectInputStream ois;

    public ObjectMessageReader(InputStream is) {
        try {
            this.ois = new ObjectInputStream(is);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public BlockMessage read() throws IOException {
        try {
           BlockMessage blockMessage = (BlockMessage)ois.readObject();
           System.out.println("read object : " + blockMessage.toString());
            return blockMessage;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
